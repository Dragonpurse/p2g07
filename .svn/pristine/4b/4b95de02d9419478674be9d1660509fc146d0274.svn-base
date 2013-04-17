000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. usecase1.
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
           SELECT invoer ASSIGN TO "parameters.txt"
           ORGANIZATION IS LINE SEQUENTIAL.
           SELECT uitvoer ASSIGN TO naamNieuweCSV
           ORGANIZATION IS LINE SEQUENTIAL.
       DATA DIVISION.
       FILE SECTION.
       FD invoer.
       copy parameters.
       FD uitvoer.
       copy usersCSV.
       WORKING-STORAGE SECTION.
       01 CSVtabel.
           05 kolommen          PIC X(255)      OCCURS 999 TIMES.
           05 #rijen            PIC 9(3).
       01 lijstWW.
           05 randomWW          PIC X(8)        OCCURS 999 TIMES.
       01 conditie              PIC X.
            88 eof                              VALUE HIGH-VALUES.
       01 tellers.
           05 i                 PIC 9(2).
           05 j                 PIC 9(2).
       01 JdbcString            PIC X(150)      VALUE SPACE.
       01 naamNieuweCSV         Pic X(25).
       PROCEDURE DIVISION.
       PGM.
           OPEN INPUT invoer
            READ invoer
                AT END 
                 MOVE HIGH-VALUES to conditie
                 CALL 'LogHandler' 
                 USING "Het bestand kan niet worden gevonden of is leeg"
            END-READ
            MOVE ConnString TO JdbcString           
            
            PERFORM VARYING i FROM 1 BY 1 until eof
            IF NaamCSV (i) = SPACE OR NaamCSV (i) = LOW-VALUES
            THEN MOVE HIGH-VALUES TO conditie
            ELSE
              CALL 'CSVReader' USING NaamCSV(i) RETURNING CSVtabel
              IF #rijen > 0 PERFORM GENCSV END-IF
            END-IF
            END-PERFORM
           CLOSE invoer
           STOP RUN
       .
       CONNECTDB.
           CALL 'dbConn' USING JdbcString
       .
       
       GENCSV.
           CAll 'PWGenerator' USING #rijen RETURNING lijstWW
           STRING NaamCSV (i) DELIMITED BY SPACE
                  ".OUT"      DELIMITED BY SIZE
                  INTO naamNieuweCSV
           OPEN OUTPUT uitvoer
           PERFORM VARYING j FROM 1 BY 1 UNTIL j > #rijen 
           UNSTRING kolommen(j) DELIMITED BY ";" INTO Userdata
           MOVE randomWW(j) TO wachtwoord
           WRITE Userdata
           END-PERFORM
           CLOSE uitvoer
       .
       
       CREATESTUFF.
       OPEN OUTPUT invoer
       STRING "jdbc:sqlserver://localhost\SQLEXPRESS;" 
              DELIMITED BY SIZE
              "databaseName=p2g7;userName=admin;password=admin"
              DELIMITED BY SIZE
              INTO JdbcString
       MOVE JdbcString to ConnString
       MOVE "00001" to ProjectCode (1)
       MOVE "test.csv" to NaamCSV  (1)
       MOVE "00002" to ProjectCode (2)
       MOVE "test2.csv" to NaamCSV (2)
       MOVE "00003" to ProjectCode (3)
       MOVE "test3.csv" to NaamCSV (3)
       MOVE "00004" to ProjectCode (4)
       MOVE "test4.csv" to NaamCSV (4)
       Write Gegevens
       CLOSE invoer
       .
