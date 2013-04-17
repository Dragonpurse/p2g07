       IDENTIFICATION DIVISION.
       PROGRAM-ID. dbConn.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       LINKAGE SECTION.
       01 ConnGeg PIC X(255).
       EXEC SQL
        BEGIN DECLARE SECTION
       END-EXEC
      * SQLCODE is 0 for success, 100 for no data, -1 for failure
       01 SQLCODE PIC S9(3).
      * SQLSTATE is a 5 character communication code; 00xxx is success.
       01 SQLSTATE PIC X(5).
       01 JdbcString PIC X(255).
       EXEC SQL
        END DECLARE SECTION
       END-EXEC
       PROCEDURE DIVISION USING ConnGeg.
       
       MAIN-PARAGRAPH.
      * Initial code
       PERFORM DO-CONNECT
      
        DISPLAY "After connecting to the database:"
      
        DISPLAY "SQLCODE= " + SQLCODE.
        DISPLAY "SQLSTATE= " + SQLSTATE
      * Use the database       
        PERFORM DO-DISCONNECT
        ACCEPT SQLSTATE
      * Terminate the program    
        GOBACK
      * The SQL connect statement must be completed with the information
      * appropriate to the actual JDBC driver in use.  JDBC stands for
      * Java DataBase Connectivity, and it is the method by which PERCobol
      * accesses databases and database-like data sources.
      *
      * The JDBC driver itself must be included in the Java library path
      * in order to successfully connect to the database.  The JDBC driver
      * is generally included with the database itself; see the database
      * documentation for more details.
      *
      * When connecting to a datasource, the jdbc:url may be 
      * ds:data-source-name.
      *
      * jdbc:url The JDBC url to the database itself     
      
      * com.driver.name This is the classname of the driver
      *
       DO-CONNECT.
       MOVE ConnGeg to JdbcString
      * STRING "jdbc:sqlserver://localhost\SQLEXPRESS;" 
      *   DELIMITED BY SIZE
      *   "databaseName=p2g7;"
      *   DELIMITED BY SIZE
      *   "userName=admin;password=admin"
      *   DELIMITED BY SIZE
      *   INTO JdbcString
      
       EXEC SQL
        CONNECT
        TO :JdbcString         
        DRIVER "com.microsoft.sqlserver.jdbc.SQLServerDriver"                 
       END-EXEC.
       .
      * Disconnect from the SQL database connection.  This allows the
      * JDBC driver to free any resources required for the connection.
       DO-DISCONNECT.
       EXEC SQL
       DISCONNECT
       END-EXEC.