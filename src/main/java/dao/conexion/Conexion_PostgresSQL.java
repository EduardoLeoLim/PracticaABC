package dao.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion_PostgresSQL {
    public static Connection getConexion(){
        Connection conn = null;
        String ip = "127.0.0.1";
        int puerto = 5432;
        String usuario = "postgres";
        String baseDatos = "PracticaABC";
        String password = "root";
        try{
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://" + ip + ":" + puerto + "/" + baseDatos,
                    usuario, password);
        }catch (Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
        return conn;
    }
}
