000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. PWGenerator.
       DATA DIVISION.
       LINKAGE SECTION.
       01 #rijen                PIC 9(3).  
       WORKING-STORAGE SECTION.
       01 lijstWW.
           05 randomWW          PIC X(8)        OCCURS 999 TIMES. 
       01 bereik.
           05 totaal            PIC X(62).
           05 hoofdletters      PIC X(26).
       01 waarde.
           05 randomvalue       PIC 9(2).
           05 hulpvalue         PIC X           OCCURS 8 TIMES.
       01 conditie.
           05 #hoofdletters     PIC 9.
           05 #cijfers          PIC 9.
       01 tellers.
           05 i                 PIC 9(2).
           05 j                 PIC 9.
       PROCEDURE DIVISION USING #rijen RETURNING lijstWW.
       PGM.
          MOVE "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                &"abcdefghijklmnopqrstuvwxyz1234567890" TO totaal
          MOVE "ABCDEFGHIJKLMNOPQRSTUVWXYZ" TO hoofdletters

          PERFORM VARYING i FROM 1 BY 1 UNTIL i > #rijen
          INITIALIZE #Hoofdletters #Cijfers
          PERFORM VARYING j FROM 1 BY 1 UNTIL j > 8
              COMPUTE randomvalue = 1 + FUNCTION RANDOM * 62
              MOVE totaal(randomvalue:1) TO hulpvalue(j) 

              IF randomvalue < 27 
              THEN ADD 1 TO #Hoofdletters
              END-IF
              
              IF randomvalue > 52 
              THEN ADD 1 TO #Cijfers
              END-IF
              
              IF #Cijfers < 2 AND j > 5 AND randomvalue < 53
              THEN COMPUTE randomvalue = FUNCTION RANDOM * 10
                   MOVE randomvalue (2:1) TO hulpvalue (j)
                   ADD 1 TO #Cijfers
              END-IF   
              
              IF #Hoofdletters < 1 AND j > 7 AND randomvalue > 27
              THEN COMPUTE randomvalue = 1 + FUNCTION RANDOM * 26
                   MOVE hoofdletters (randomvalue:1) TO hulpvalue (j)
                   ADD 1 TO #Hoofdletters
              END-IF   
          END-PERFORM
          PERFORM STRINGPW
          END-PERFORM
          EXIT PROGRAM
       .
       
       STRINGPW.
          STRING hulpvalue(1) DELIMITED BY SPACE
                 hulpvalue(2) DELIMITED BY SPACE
                 hulpvalue(3) DELIMITED BY SPACE
                 hulpvalue(4) DELIMITED BY SPACE
                 hulpvalue(5) DELIMITED BY SPACE
                 hulpvalue(6) DELIMITED BY SPACE
                 hulpvalue(7) DELIMITED BY SPACE
                 hulpvalue(8) DELIMITED BY SPACE
                 INTO randomWW(i)
       .