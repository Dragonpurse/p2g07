        |Pseudocode UC3
        |
        |OPEN INPUT parametersCasusOverzicht
        |PERFORM connectie-database
        |PERFORM UNTIL eof-parametersCasusOverzicht
        |READ casusid
        |EVALUATE casusid
        |PERFORM maakRapport
        |END-PERFORM
        |CLOSE parametersCasusOverzicht
        |
        |maakRapport.
        |OPEN OUTPUT rapport
        |SELECT titel,omschrijving,kernwoorden,doelgroep,locatiefilm,situatieschets
        |FROM tblcasus
        |WHERE casusid = :casusid
        |UNSTRING situatieschets INTO ...
        |WRITE...
        |Vragen + antwoorden (feedback + volgnummer) ophalen
        |WRITE...
        |CLOSE rapport

000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. OverzichtCasus.
       ENVIRONMENT DIVISION.
       FILE-CONTROL.
           SELECT parameter-invoer
           ASSIGN TO "parametersCasusOverzicht.txt"
           ORGANIZATION LINE SEQUENTIAL
           FILE STATUS IS parameter-status.
       DATA DIVISION.
       FILE SECTION.
       FD parameter-invoer.
       copy parameter_record.
       WORKING-STORAGE SECTION.
       01 parameterfilenaam                PIC X(255).
       01 statuscodes.
           05 parameter-status             PIC X(2).
       01 jdbc-string                      PIC X(100).
       01 casusID                          PIC 9(4).
       01 condities                        PIC X.
           05 conditie1                    PIC X.
               88 eindeAntwoorden          VALUE HIGH-VALUES.
           05 conditie2                    PIC X.
               88 eindeVragen              VALUE HIGH-VALUES.
           05 conditie3                    PIC X.
               88 bestaande-casus          VALUE HIGH-VALUES.
       01 errormessage                  PIC X(235).
       EXEC SQL BEGIN DECLARE SECTION
       END-EXEC.
      * 0 = succes , 100 = no data, -1 = failure
       01 SQLCODE                       PIC S9(3).
      * 00xxx is succes
       01 SQLSTATE                      PIC X(5).
       01 algemeen.
           02 titel                         PIC X(50).
           02 omschrijving                  PIC X(255).
           02 kernwoorden                   PIC X(255).
           02 doelgroep                     PIC X(50).
           02 locatie                       PIC X(255).
           02 situatieschets                PIC X(255).
       01 vraag.
           02 vraagID                       PIC 9(4).
           02 vraagTekst                    PIC X(1000).
       01 antwoord. 
           02 antwoordID                    PIC 9(4).
           02 antwoordTekst                 PIC X(255).
           02 antwoordBeschrijving          PIC X(255).
           02 volgendeVraag                 PIC 9(4).
       
       
       EXEC SQL END DECLARE SECTION
       END-EXEC.
       PROCEDURE DIVISION.
       OPEN INPUT parameter-invoer
       PERFORM 2000-evaluatie-parameter-status
       
      *initial read - haalt jdbc-string uit parameters.txt
       READ parameter-invoer
         AT END SET eof OF parameter-record TO TRUE
           CALL "LogHandler" 
           USING "Het bestand 'parameters.txt' is leeg."
           STOP RUN
       END-READ
       MOVE parameter-record TO jdbc-string
       PERFORM 3000-connectDatabase
       
       READ parameter-invoer
         AT END SET eof OF parameter-record TO TRUE
       END-READ
       
       PERFORM UNTIL eof OF parameter-record
         MOVE parameter-record TO casusID
         PERFORM 1000-leesCasus
         READ parameter-invoer
           AT END SET eof OF parameter-record TO TRUE
             CALL "LogHandler"
             USING "Einde parameterbestand is bereikt."
         END-READ
       END-PERFORM
       
       ACCEPT casusID
       .
       
       1000-leesCasus.
       EXEC SQL
           SELECT Titel, Omschrijving, LocatieFilm, Doelgroep,
           Kernwoorden, EersteVraag, Situatieschets
           INTO :titel, :omschrijving, :locatie, :doelgroep,
           :kernwoorden, :vraagID, :situatieschets
           FROM tblCasus
           WHERE CasusID = :casusID
       END-EXEC
       
       EVALUATE SQLCODE
        WHEN 100 SET bestaande-casus TO FALSE
                 INITIALIZE errormessage
                 STRING "De casus met casusID " DELIMITED BY SIZE
                        casusID      DELIMITED BY SPACE
                        " bestaat niet." DELIMITED BY SIZE
                       INTO errormessage
                 CALL "LogHandler" USING errormessage
        WHEN 0 SET bestaande-casus TO TRUE
        WHEN OTHER SET bestaande-casus TO FALSE
                   INITIALIZE errormessage
                   STRING "Fout met de communicatie met de databank "
                                         DELIMITED BY SIZE
                          "SQLCODE = "   DELIMITED BY SIZE
                          SQLCODE        DELIMITED BY SPACE
                          " SQLSTATE = " DELIMITED BY SIZE
                          SQLSTATE       DELIMITED BY SPACE
                   INTO errormessage
                   CALL "LogHandler" USING errormessage
        
       END-EVALUATE
       IF bestaande-casus
         DISPLAY casusID
         PERFORM 1100-maakRapport
       END-IF
       .
       1100-maakRapport.
       PERFORM 1200-leesVragen
       .
       
       1200-leesVragen.
       EXEC SQL DECLARE vragenlijst CURSOR FOR
         SELECT VraagID, VraagTekst
         FROM tblVraag
         WHERE CasusID = :casusID
       END-EXEC
       EXEC SQL
         OPEN vragenlijst
       END-EXEC
       
       PERFORM 1210-haalVraagOp
       
       PERFORM UNTIL eindeVragen
         PERFORM 1210-haalVraagOp
       END-PERFORM
       
       EXEC SQL CLOSE vragenlijst END-EXEC
       EXEC SQL DEALLOCATE CURSOR vragenlijst END-EXEC
       .
       
       1210-haalVraagOp.
       EXEC SQL
         FETCH vragenlijst
         INTO :vraagID, :vraagTekst
         FROM tblVraag
       END-EXEC
       
       EVALUATE SQLCODE
         WHEN 100 SET eindeVragen TO TRUE
         WHEN 0   SET eindeVragen TO FALSE
         WHEN OTHER SET eindeVragen TO TRUE
                  INITIALIZE errormessage
                  STRING "Fout met de communicatie met de databank "
                                        DELIMITED BY SIZE
                         "SQLCODE = "   DELIMITED BY SIZE
                         SQLCODE        DELIMITED BY SPACE
                         " SQLSTATE = " DELIMITED BY SIZE
                         SQLSTATE       DELIMITED BY SPACE
                  INTO errormessage
                  CALL "LogHandler" USING errormessage
       END-EVALUATE
       
       IF NOT eindeVragen
         DISPLAY "Vraag: " + vraagID
         PERFORM 1300-leesAntwoorden
       END-IF
       .
       
       1300-leesAntwoorden.
       EXEC SQL DECLARE antwoordenlijst CURSOR FOR
         SELECT AntwoordID , AntwoordTekst, AntwoordBeschrijving,
         VolgendeVraag
         FROM tblAntwoordCasus
                  WHERE VraagID = :vraagID
       END-EXEC
       
       EXEC SQL 
         OPEN antwoordenlijst
       END-EXEC
       
       PERFORM 1310-haalAntwoordOp
       
       PERFORM UNTIL eindeAntwoorden
         PERFORM 1310-haalAntwoordOp
       END-PERFORM
       
       EXEC SQL CLOSE antwoordenlijst END-EXEC
       EXEC SQL DEALLOCATE CURSOR antwoordenlijst END-EXEC
       .
       
       1310-haalAntwoordOp.
       EXEC SQL
        FETCH antwoordenlijst
        INTO :antwoordID , :antwoordTekst, :antwoordBeschrijving, 
        :volgendeVraag
       END-EXEC
       
       EVALUATE SQLCODE
         WHEN 100 SET eindeAntwoorden TO TRUE
         WHEN 0   INITIALIZE conditie1
         WHEN OTHER SET eindeAntwoorden TO TRUE
                  INITIALIZE errormessage
                  STRING "Fout met de communicatie met de databank "
                                        DELIMITED BY SIZE
                         "SQLCODE = "   DELIMITED BY SIZE
                         SQLCODE        DELIMITED BY SPACE
                         " SQLSTATE = " DELIMITED BY SIZE
                         SQLSTATE       DELIMITED BY SPACE
                  INTO errormessage
                  CALL "LogHandler" USING errormessage
       END-EVALUATE
       
       IF NOT eindeAntwoorden
       DISPLAY "Antwoord: " + antwoordID "   Volgende vraag: " 
       + volgendeVraag
       END-IF
       .
       
       
       2000-evaluatie-parameter-status.
       EVALUATE parameter-status
         WHEN "35" CALL "LogHandler"
                  USING "Het bestand 'parameters.txt' is niet gevonden."
                  STOP RUN
       END-EVALUATE
       .
       
       3000-connectDatabase.
       EXEC SQL
        CONNECT
        TO :jdbc-string  
        DRIVER "com.microsoft.sqlserver.jdbc.SQLServerDriver"                 
       END-EXEC
       .
