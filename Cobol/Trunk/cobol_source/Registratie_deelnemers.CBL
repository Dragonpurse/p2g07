000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. Registratie_deelnemers.
      *
      *UC1: (Batch)registratie gebruikers cursussen/workshops
      *UC1B: (Batch)registratie interne medewerkers
      *
      *TODO UC1: 4B: De leertrajectcode hoort bij een gesloten project 
      *(einddatum is verstreken)
      *
      *TODO UC1: 5A. De gebruiker bestaat reeds in de databank
      *1: Het systeem legt de link van de deelnemer met de 
      *nieuwe leertrajectcode
      *
      *TODO UC1 (Uitbreiding): De paswoorden zijn geëncrypteerd opgeslagen
      *
      *...
      *
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.      
       FILE-CONTROL.
       SELECT parameterInvoer 
       ASSIGN TO "parameters.txt"
       ORGANIZATION LINE SEQUENTIAL
       FILE STATUS IS parameter-status.
       
       SELECT deelnemersBestand 
       ASSIGN TO naamDeelnemersBestand
       ORGANIZATION LINE SEQUENTIAL
       FILE STATUS IS deelnemer-status.
       
       SELECT nieuwDeelnemersBestand 
       ASSIGN TO naamNieuwDeelnemersBestand
       ORGANIZATION LINE SEQUENTIAL.
       
       DATA DIVISION.
       FILE SECTION.
       FD parameterInvoer.
       COPY "parameterInvoer.cpy".
       
       FD deelnemersBestand.
       COPY "deelnemersBestand.cpy".
       
       FD nieuwDeelnemersBestand.
       COPY "nieuwDeelnemersBestand.cpy".
       WORKING-STORAGE SECTION.
       
       01 projectRecord.
           02 naamDeelnemersBestand             PIC X(50) VALUE SPACES.
           02 naamNieuwDeelnemersBestand        PIC X(50) VALUE SPACES.  
           88 onbestaandeProjectCode             VALUE LOW-VALUES.
           88 einddatumVerstreken               VALUE LOW-VALUES.
       01 lengtePaswoord                        PIC 99.
       01 aantalHoofdletters                    PIC 99.
       01 aantalCijfers                         PIC 99.
           
       01 JdbcString                            PIC X(100) VALUE SPACES.
       01 Statuscodes.
           02 parameter-status.
               05 parameter-status-key1         PIC X.
               05 parameter-status-key2         PIC X.
               05 binary-parameter-status 
               REDEFINES parameter-status-key2  PIC 99.
           02 deelnemer-status.
               05 deelnemer-status-key1         PIC X.
               05 deelnemer-status-key2         PIC X.
               05 binary-deelnemer-status
               REDEFINES deelnemer-status-key2  PIC 99.
           02 inv-key-status.
               05 inv-key-status-key1         PIC X.
               05 inv-key-status-key2         PIC X.
               05 binary-inv-key-status
               REDEFINES inv-key-status-key2  PIC 99.
           88 ontbrekende-csv-status            VALUE HIGH-VALUES.
       01 fileNotFoundMsg                       PIC X(100).

       
       EXEC SQL
        BEGIN DECLARE SECTION
       END-EXEC
      * SQLCODE is 0 for success, 100 for no data, -1 for failure
       01 SQLCODE PIC S9(3).
      * SQLSTATE is a 5 character communication code; 00xxx is success.
       01 SQLSTATE PIC X(5).
       01 JdbcString PIC X(255).
       01 naam                              PIC X(30) VALUE SPACES.
       01 paswoord                          PIC X(30) VALUE SPACES.
       01 projectCode                       PIC X(5) VALUE SPACES.

       EXEC SQL
        END DECLARE SECTION
       END-EXEC

               
       PROCEDURE DIVISION.
       
       pgm.
           OPEN INPUT parameterInvoer
           EVALUATE parameter-status-key1
               WHEN "3" PERFORM 2000-check-perm-err-parameter
           END-EVALUATE
           READ parameterInvoer
               AT END SET eof IN parameterRecord to TRUE
                      CALL "LogHandler" USING "Parameterfile is leeg."
                      STOP RUN
           END-READ
           MOVE parameterLijn TO JdbcString
           
           READ parameterInvoer
               AT END SET eof IN parameterRecord to TRUE
           END-READ
           
           PERFORM UNTIL eof IN parameterRecord
           
               MOVE parameterLijn to projectCode
               
               PERFORM 6000-connecteerMetDatabase
               PERFORM 4000-evaluatieProjectCode
               PERFORM 2100-evaluatieEinddatum
               PERFORM 7000-disconnecteerDatabase              
               READ parameterInvoer
                   AT END SET eof IN parameterRecord to TRUE
               END-READ
               
               MOVE parameterLijn to naamDeelnemersBestand
               
               OPEN INPUT deelnemersBestand
               EVALUATE deelnemer-status-key1
                   WHEN "3" PERFORM 3000-check-perm-err-deelnemer
                   WHEN OTHER SET ontbrekende-csv-status TO FALSE
               END-EVALUATE
               
               IF NOT ontbrekende-csv-status
               THEN
                   PERFORM 1000-Verwerking-usersbestand
               END-IF

               
               READ parameterInvoer
                   AT END SET eof IN parameterRecord to TRUE
               END-READ             

           END-PERFORM
           
       .
       
       1000-Verwerking-usersbestand.
           READ deelnemersBestand
              AT END SET eof IN deelnemersRecord TO TRUE
           END-READ
           
           STRING naamDeelnemersBestand DELIMITED BY "."
                  ".OUT.csv"                DELIMITED BY SIZE
           INTO naamNieuwDeelnemersBestand
           OPEN OUTPUT nieuwDeelnemersBestand
           
           PERFORM UNTIL eof IN deelnemersRecord 
              CALL 'PWGenerator' RETURNING paswoord
              MOVE deelnemersLijn to naam
              STRING naam DELIMITED BY SPACES
                      ";" DELIMITED BY SIZE
                 paswoord DELIMITED BY SPACES 
              INTO nieuwDeelnemersLijn
              WRITE nieuwDeelnemersRecord
              EVALUATE inv-key-status-key1
                 WHEN "2" PERFORM 5000-check-inv-key-dataSchrijven
              END-EVALUATE
              PERFORM 8000-schrijfGebruikerInDatabase
              INITIALIZE nieuwDeelnemersLijn
              READ deelnemersBestand
                  AT END SET eof IN deelnemersRecord TO TRUE
              END-READ
           END-PERFORM
       .
       
       
       2000-check-perm-err-parameter.
       EVALUATE parameter-status-key2
           WHEN "5" CALL "LogHandler" 
                    USING "Parameterfile niet gevonden."
                    SET ontbrekende-csv-status TO TRUE
           WHEN OTHER SET ontbrekende-csv-status TO FALSE
       END-EVALUATE
       .
       
       3000-check-perm-err-deelnemer.
       EVALUATE deelnemer-status-key2
           WHEN "5" 
           STRING "deelnemerFile " DELIMITED BY SIZE
                  naamDeelnemersBestand DELIMITED BY SPACES
                  " niet gevonden." DELIMITED BY SIZE
                  INTO fileNotFoundMsg
           CALL "LogHandler" 
              USING fileNotFoundMsg
                    STOP RUN
       END-EVALUATE
       .
       
       4000-evaluatieProjectCode.
       EXEC SQL
           SELECT LeertrajectCode
           FROM tblLeertraject
           WHERE LeertrajectCode = :projectCode
           INTO :projectCode
       END-EXEC

       EVALUATE SQLCODE
          WHEN 100 SET onbestaandeProjectCode TO TRUE
          WHEN 0 SET onbestaandeProjectCode TO FALSE
       END-EVALUATE

       .
 
       5000-check-inv-key-dataSchrijven.
       EVALUATE inv-key-status-key2
           WHEN "2" CALL "LogHandler" 
                    USING "Attempt to write duplicated key"
           WHEN "3" CALL "LogHandler" 
                    USING "No record found."
       END-EVALUATE
       .
       
       6000-connecteerMetDatabase.     
       EXEC SQL
        CONNECT
        TO :JdbcString  
        DRIVER "com.microsoft.sqlserver.jdbc.SQLServerDriver"                 
       END-EXEC
       .
       
       7000-disconnecteerDatabase.
       EXEC SQL
       DISCONNECT
       END-EXEC
       .
       
       8000-schrijfGebruikerInDatabase.
       
       PERFORM 6000-connecteerMetDatabase
       
       EXEC SQL
       INSERT INTO tblGebruiker (GebruikerID, Wachtwoord, Intern)
       VALUES (:naam, :paswoord, 0)
       END-EXEC
       
       EXEC SQL
       COMMIT
       END-EXEC
       
       PERFORM 9000-controleerOfGebruikerBestaat
       PERFORM 7000-disconnecteerDatabase

       .
       
       9000-controleerOfGebruikerBestaat.
       IF SQLCODE = 627
          DISPLAY "Gebruiker bestaat al"
          PERFORM 1100-linkGebruikerTraject
       END-IF
       .
       
      *TODO 5A. De gebruiker bestaat reeds in de databank.
1     *1: Het systeem legt de link van de deelnemer met de 
      *nieuwe leertrajectcode.
       1100-linkGebruikerTraject.
       PERFORM 6000-connecteerMetDatabase

       EXEC SQL             
       INSERT INTO tblLeertrajectGebruiker (LeertrajectCode, GebruikerID)
       VALUES (:projectCode, :naam)
       END-EXEC
       
       DISPLAY projectCode
       DISPLAY naam
       ACCEPT naam
       DISPLAY SQLCODE
       
       EXEC SQL
       COMMIT
       END-EXEC
       PERFORM 7000-disconnecteerDatabase
       .
       
      *TODO 4B: De leertrajectcode hoort bij een gesloten project 
      *(einddatum is verstreken).
       2100-evaluatieEinddatum.
       
       .

       