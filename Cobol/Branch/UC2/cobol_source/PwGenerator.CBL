000100 IDENTIFICATION DIVISION.
000200 PROGRAM-ID. PwGenerator.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01 wachtwoord                    PIC X(8).
       01 bereik.
           05 totaal                    PIC X(62).
           05 hoofdletters              PIC X(26).
       01 waarde.
           05 random-waarde             PIC 9(2).
           05 hulpwaarde                PIC X.
       01 tellers.
           05 aantal-hoofdletters       PIC 9.
           05 aantal-cijfers            PIC 9.
           05 i                         PIC 9.
       PROCEDURE DIVISION RETURNING wachtwoord.
       MAIN-PARAGRAPH.
       MOVE "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
             &"1234567890" TO totaal
       MOVE "ABCDEFGHIJKLMNOPQRSTUVWXYZ" TO hoofdletters

       INITIALIZE aantal-hoofdletters aantal-cijfers wachtwoord
       PERFORM VARYING i FROM 1 BY 1 UNTIL i > 8
         COMPUTE random-waarde = 1 + FUNCTION RANDOM * 62
         MOVE totaal(random-waarde:1) TO hulpwaarde 
              
         PERFORM 1000-evaluatie-random-waarde
         PERFORM 1100-evaluatie-tellers
              
         STRING wachtwoord DELIMITED BY SPACE
                hulpwaarde DELIMITED BY SPACE
                INTO wachtwoord
                     
       END-PERFORM
       GOBACK
       .
       
       1000-evaluatie-random-waarde.
       EVALUATE random-waarde
         WHEN 1 THRU 26
           ADD 1 TO aantal-hoofdletters
         WHEN 53 THRU 62
           ADD 1 TO aantal-cijfers
       END-EVALUATE
       .
       
       1100-evaluatie-tellers.
       IF aantal-cijfers < 2 AND i > 5 AND random-waarde < 53
       THEN COMPUTE random-waarde = FUNCTION RANDOM * 10
            MOVE random-waarde (2:1) TO hulpwaarde
            ADD 1 TO aantal-cijfers
       END-IF   
             
       IF aantal-hoofdletters < 1 AND i > 7 AND random-waarde > 27
       THEN COMPUTE random-waarde = 1 + FUNCTION RANDOM * 26
            MOVE hoofdletters (random-waarde:1) TO hulpwaarde
            ADD 1 TO aantal-hoofdletters
       END-IF
       .