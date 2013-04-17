000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. CSVReader.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT invoer ASSIGN TO filename
           ORGANIZATION IS LINE SEQUENTIAL.
       DATA DIVISION.
       FILE SECTION.
       FD invoer.
       01 CSVdata               PIC X(255).
       LINKAGE SECTION.
       01 filename              PIC X(25).
       WORKING-STORAGE SECTION.
       01 CSVtabel.
           05 kolommen          PIC X(255)      OCCURS 999 TIMES.
           05 #rijen            PIC 9(3).
       01 conditie              PIC X.
           88 eof                               VALUE HIGH-VALUES. 
       01 msg                   PIC X(230)      VALUE SPACE.
       01 teller                PIC 9(2).
       PROCEDURE DIVISION USING filename RETURNING CSVtabel.
       PGM.
           OPEN INPUT invoer
           INITIALIZE msg conditie #rijen
           READ invoer
                AT END MOVE HIGH-VALUES TO conditie
                STRING "Het CSV-bestand '"            DELIMITED BY SIZE
                       filename                       DELIMITED BY SPACE
                       "' bestaat niet of is leeg"    DELIMITED BY SIZE
                       INTO msg
                CALL "LogHandler" USING msg
           END-READ

           PERFORM VARYING teller FROM 1 BY 1 UNTIL eof
           MOVE CSVdata TO kolommen(teller)
           READ invoer
                AT END MOVE HIGH-VALUES TO conditie
                       MOVE teller TO #rijen
           END-READ
           END-PERFORM       
           CLOSE invoer
           EXIT PROGRAM
       .