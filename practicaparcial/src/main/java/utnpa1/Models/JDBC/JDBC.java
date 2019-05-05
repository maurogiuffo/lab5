package utnpa1.Models.JDBC;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JDBC {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_Name = "ahorcado";
    static final String DB_Server = "localhost:3306";
    static final String DB_URL = "jdbc:mysql://" + DB_Server +"/" + DB_Name +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    //Singleton
    private static JDBC driver;

    public static JDBC getInstance() {

        if (driver==null) {

            driver = new JDBC();
        }
        return driver;
    }

    private JDBC(){
    }

    //Public

    public String getWord() {
        Connection conn = null;
        Statement stmt = null;
        String palabra = "";

        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT LOWER(palabra) as palabra FROM palabras ORDER BY RAND() LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                palabra = rs.getString("palabra");
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return palabra;
    }


    public void insertResults(String ganador, String palabra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.prepareStatement("INSERT INTO ganadores(nombre,palabra,fecha) " +
                    "VALUES ( ?, ?, NOW())");
            stmt.setString(1,ganador);
            stmt.setString(2,palabra);

            stmt.execute();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

}