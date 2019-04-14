package Database;

import Models.Humano;

import java.sql.*;

public class JDBC {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/TorneoFrescas?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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

    public void getResults() {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, nombre_ganador, bebida_en_cuerpo FROM resultados";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nHistorial de ganadores:\n");

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String nombre_ganador = rs.getString("nombre_ganador");
                int bebida_en_cuerpo = rs.getInt("bebida_en_cuerpo");

                //Display values
                System.out.println(String.format(" %s %s %s", id, nombre_ganador,bebida_en_cuerpo));
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
    }

    public void insertResults(Humano ganador) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = String.format("INSERT INTO resultados(nombre_ganador,bebida_en_cuerpo) " +
                    "VALUES ( '%s', '%s')", ganador.getNombre(),ganador.getBebidaEnElCuerpo());
            stmt.executeUpdate(sql);

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