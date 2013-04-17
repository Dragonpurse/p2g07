000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. LogHandler.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT uitvoer ASSIGN TO "logfile.txt"
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS logfile-status.
       DATA DIVISION.
       FILE SECTION.
       FD uitvoer.
       01 logmessage.
           05 datum.
               10 dag                   PIC 9(2).
               10                       PIC X          VALUE "-".
               10 maand                 PIC 9(2).
               10                       PIC X          VALUE "-".
               10 jaar                  PIC 9(4).
               10                       PIC X          VALUE SPACES.
           05 tijd.
               10 uur                   PIC 9(2).
               10                       PIC X          VALUE ":".
               10 minuten               PIC 9(2).
               10                       PIC X          VALUE ":".
               10 seconden              PIC 9(2).
               10                       PIC X          VALUE SPACES.
           05 errormessage              PIC X(235)     VALUE SPACES.
       LINKAGE SECTION.
       01 foutmelding                   PIC X(100).
       WORKING-STORAGE SECTION.
       01 logfile-status                PIC X(2).
       
       PROCEDURE DIVISION USING foutmelding.
       MAIN-PARAGRAPH.
       MOVE FUNCTION CURRENT-DATE (1:4) TO jaar
       MOVE FUNCTION CURRENT-DATE (5:2) TO maand
       MOVE FUNCTION CURRENT-DATE (7:2) TO dag
       MOVE FUNCTION CURRENT-DATE (9:2) TO uur
       MOVE FUNCTION CURRENT-DATE (11:2) TO minuten
       MOVE FUNCTION CURRENT-DATE (13:2) TO seconden
       MOVE foutmelding TO errormessage
       
       OPEN EXTEND uitvoer
       PERFORM 1000-evaluatie-logfile-status
       WRITE logmessage 
       END-WRITE
       CLOSE uitvoer
       GOBACK
       .
       
       1000-evaluatie-logfile-status.
       EVALUATE logfile-status
         WHEN "35" OPEN OUTPUT uitvoer
       END-EVALUATE
       .