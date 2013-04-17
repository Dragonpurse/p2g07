000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. LogHandler.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT uitvoer ASSIGN TO "logfile.txt"
           ORGANIZATION IS LINE SEQUENTIAL.
       DATA DIVISION.
       FILE SECTION.
       FD uitvoer.
       01 melding          PIC X(255)      VALUE SPACE.
       LINKAGE SECTION.
       01 foutmelding      PIC X(230)      VALUE SPACE.
       WORKING-STORAGE SECTION.
       01 datum.
           05 dag          PIC 9(2).
           05              PIC X           VALUE "/".
           05 maand        PIC 9(2).
           05              PIC X           VALUE "/".
           05 jaar         PIC 9(4).
       01 tijd.
           05 uur          PIC 9(2).
           05              PIC X           VALUE ":".
           05 minuten      PIC 9(2).
           05              PIC X           VALUE ":".
           05 seconden     PIC 9(2).
       
       PROCEDURE DIVISION USING foutmelding.
       PGM.
           MOVE FUNCTION CURRENT-DATE (1:4) TO jaar
           MOVE FUNCTION CURRENT-DATE (5:2) TO maand
           MOVE FUNCTION CURRENT-DATE (7:2) TO dag
           MOVE FUNCTION CURRENT-DATE (9:2) TO uur
           MOVE FUNCTION CURRENT-DATE (11:2) TO minuten
           MOVE FUNCTION CURRENT-DATE (13:2) TO seconden

           STRING datum         DELIMITED BY SPACE
                  " "           DELIMITED BY SIZE
                  tijd          DELIMITED BY SPACE 
                  "  :  "       DELIMITED BY SIZE
                  foutmelding   DELIMITED BY SIZE
                  INTO melding 

           OPEN EXTEND uitvoer
           WRITE melding END-WRITE
           CLOSE uitvoer
           EXIT PROGRAM
       .