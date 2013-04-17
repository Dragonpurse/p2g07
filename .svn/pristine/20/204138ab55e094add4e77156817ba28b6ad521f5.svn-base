       IDENTIFICATION DIVISION.
       PROGRAM-ID. DeactivatieTrajecten.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.      
       FILE-CONTROL.
           SELECT parameter-invoer 
           ASSIGN TO "parameters.txt"
           ORGANIZATION LINE SEQUENTIAL
           FILE STATUS IS parameter-status.
       DATA DIVISION.
       FILE SECTION.
       FD parameter-invoer.
       copy parameter_record.
       WORKING-STORAGE SECTION.
       01 statuscodes.
           05 parameter-status          PIC X(2).
       01 jdbc-string                   PIC X(100).
       01 conditie1                 PIC X. 
           88 eot                                VALUE HIGH-VALUES.   
       01 errormessage                  PIC X(235).
       01 DSQLCODE                      PIC +9(3).
       EXEC SQL BEGIN DECLARE SECTION
       END-EXEC.
      * 0 = succes , 100 = no data, -1 = failure
       01 SQLCODE                       PIC S9(3).
      * 00xxx is succes
       01 SQLSTATE                      PIC X(5).
       01 trajectcode                   PIC X(50).
       01 email                         PIC X(50).
       EXEC SQL END DECLARE SECTION
       END-EXEC.
       PROCEDURE DIVISION.
       MAIN-PARAGRAPH.
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
       CLOSE parameter-invoer
       PERFORM 3000-connectDatabase
       
       EXEC SQL DECLARE trajectlist CURSOR FOR
         SELECT LeertrajectCode
         FROM tblLeertraject
         WHERE actief = 1 AND gearchiveerd = 1
       END-EXEC
   
       EXEC SQL 
         OPEN trajectlist 
       END-EXEC
       
       PERFORM 3200-haal-traject-op
       PERFORM UNTIL eot
       PERFORM 3400-verwijder-users
       PERFORM 3300-deactiveer-traject
       PERFORM 3200-haal-traject-op
       END-PERFORM
       
       EXEC SQL CLOSE trajectlist END-EXEC
       EXEC SQL DEALLOCATE CURSOR trajectlist END-EXEC
       EXEC SQL COMMIT WORK END-EXEC
              
       PERFORM 3100-disconnectDatabase
       STOP RUN
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
       
       IF SQLCODE NOT = 0 
       THEN INITIALIZE errormessage
            MOVE SQLCODE TO DSQLCODE        
            STRING "Fout met de communicatie met de databank "
                                  DELIMITED BY SIZE
                   "SQLCODE = "   DELIMITED BY SIZE
                   DSQLCODE       DELIMITED BY SPACE
                   " SQLSTATE = " DELIMITED BY SIZE
                   SQLSTATE       DELIMITED BY SPACE
            INTO errormessage
            CALL "LogHandler" USING errormessage
       END-IF
       .
       
       3100-disconnectDatabase.
       EXEC SQL
        DISCONNECT
       END-EXEC
       
       IF SQLCODE NOT = 0 
       THEN INITIALIZE errormessage
            MOVE SQLCODE TO DSQLCODE        
            STRING "Fout met de communicatie met de databank "
                                  DELIMITED BY SIZE
                   "SQLCODE = "   DELIMITED BY SIZE
                   DSQLCODE       DELIMITED BY SPACE
                   " SQLSTATE = " DELIMITED BY SIZE
                   SQLSTATE       DELIMITED BY SPACE
            INTO errormessage
            CALL "LogHandler" USING errormessage
       END-IF
       .
       
       3200-haal-traject-op.       
       EXEC SQL 
        FETCH trajectlist INTO :trajectcode
       END-EXEC
       
       EVALUATE SQLCODE
         WHEN 100 SET eot TO TRUE
         WHEN 0   SET eot TO FALSE
         WHEN OTHER SET eot TO TRUE
                    INITIALIZE errormessage
                    MOVE SQLCODE TO DSQLCODE        
                    STRING "Fout met de communicatie met de databank "
                                         DELIMITED BY SIZE
                          "SQLCODE = "   DELIMITED BY SIZE
                          DSQLCODE       DELIMITED BY SPACE
                          " SQLSTATE = " DELIMITED BY SIZE
                          SQLSTATE       DELIMITED BY SPACE
                    INTO errormessage
                    CALL "LogHandler" USING errormessage
       END-EVALUATE    
       .
       
       
       3300-deactiveer-traject.
       EXEC SQL tblLeertraject
         UPDATE tblLeertraject
         SET Actief = 0
         WHERE Leertrajectcode = :trajectcode
       END-EXEC
       
       IF SQLCODE NOT = -809 AND SQLCODE NOT = 0 
       THEN INITIALIZE errormessage
            MOVE SQLCODE TO DSQLCODE        
            STRING "Fout met de communicatie met de databank "
                                  DELIMITED BY SIZE
                   "SQLCODE = "   DELIMITED BY SIZE
                   DSQLCODE       DELIMITED BY SPACE
                   " SQLSTATE = " DELIMITED BY SIZE
                   SQLSTATE       DELIMITED BY SPACE
            INTO errormessage
            CALL "LogHandler" USING errormessage
       ELSE INITIALIZE errormessage     
            STRING "Het traject "      DELIMITED BY SIZE
                   trajectcode         DELIMITED BY SPACE
                   " is gedeactiveerd" DELIMITED BY SIZE
            INTO errormessage
            CALL "LogHandler" USING errormessage
       END-IF  
       .
       
       3400-verwijder-users.
       EXEC SQL 
          DELETE FROM tblLeertrajectDeelnemer
          WHERE LeertrajectCode = :trajectcode
       END-EXEC
      
       IF SQLCODE NOT =-547 AND SQLCODE NOT = 0 AND SQLCODE NOT = 100
       THEN INITIALIZE errormessage
            MOVE SQLCODE TO DSQLCODE        
            STRING "Fout met de communicatie met de databank "
                                  DELIMITED BY SIZE
                   "SQLCODE = "   DELIMITED BY SIZE
                   DSQLCODE       DELIMITED BY SPACE
                   " SQLSTATE = " DELIMITED BY SIZE
                   SQLSTATE       DELIMITED BY SPACE
            INTO errormessage
            CALL "LogHandler" USING errormessage
       END-IF
       
       EXEC SQL                     
          DELETE FROM tblDeelnemer
          WHERE NOT EXISTS 
               (SELECT * FROM tblLeertrajectDeelnemer
               WHERE tblDeelnemer.Email = tblLeertrajectDeelnemer.Email)                         
       END-EXEC
       
       IF SQLCODE NOT =-547 AND SQLCODE NOT = 0 AND SQLCODE NOT = 100
       THEN INITIALIZE errormessage
            MOVE SQLCODE TO DSQLCODE        
            STRING "Fout met de communicatie met de databank "
                                  DELIMITED BY SIZE
                   "SQLCODE = "   DELIMITED BY SIZE
                   DSQLCODE       DELIMITED BY SPACE
                   " SQLSTATE = " DELIMITED BY SIZE
                   SQLSTATE       DELIMITED BY SPACE
            INTO errormessage
            CALL "LogHandler" USING errormessage
       END-IF
       .