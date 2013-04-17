000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. PWGenerator.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 paswoord              PIC X(8).
       01 bereik.
           05 totaal            PIC X(62).
           05 hoofdletters      PIC X(26).
       01 waarde.
           05 randomvalue       PIC 9(2).
           05 hulpvalue         PIC X.
       01 conditie.
           05 aantalHoofdletters     PIC 9.
           05 aantalCijfers          PIC 9.
       01 tellers.
           05 j                 PIC 9.
       PROCEDURE DIVISION RETURNING paswoord.
       PGM.
          MOVE "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                &"abcdefghijklmnopqrstuvwxyz1234567890" TO totaal
          MOVE "ABCDEFGHIJKLMNOPQRSTUVWXYZ" TO hoofdletters

          INITIALIZE aantalHoofdletters aantalCijfers paswoord
          PERFORM VARYING j FROM 1 BY 1 UNTIL j > 8
              COMPUTE randomvalue = 1 + FUNCTION RANDOM * 62
              MOVE totaal(randomvalue:1) TO hulpvalue 
              
              EVALUATE randomvalue
              WHEN 1 THRU 26
               ADD 1 TO aantalHoofdletters
              WHEN 53 THRU 62
               ADD 1 TO aantalCijfers
              END-EVALUATE

              IF aantalCijfers < 2 AND j > 5 AND randomvalue < 53
              THEN COMPUTE randomvalue = FUNCTION RANDOM * 10
                   MOVE randomvalue (2:1) TO hulpvalue
                   ADD 1 TO aantalCijfers
              END-IF   
              
              IF aantalHoofdletters < 1 AND j > 7 AND randomvalue > 27
              THEN COMPUTE randomvalue = 1 + FUNCTION RANDOM * 26
                   MOVE hoofdletters (randomvalue:1) TO hulpvalue
                   ADD 1 TO aantalHoofdletters
              END-IF
              
              STRING paswoord  DELIMITED BY SPACE
                     hulpvalue    DELIMITED BY SPACE
                     INTO paswoord
                     
          END-PERFORM
          EXIT PROGRAM
       .