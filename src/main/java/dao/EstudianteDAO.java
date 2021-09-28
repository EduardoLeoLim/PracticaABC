package dao;

import dao.conexion.Conexion_PostgresSQL;
import modelo.Estudiante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public static List<Estudiante>ObtenerEstudiantes(){
        List<Estudiante> lista = new ArrayList<Estudiante>();

        try {
            Connection conexionBD = Conexion_PostgresSQL.getConexion();
            Statement statement = conexionBD.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM estudiante WHERE activo = true ORDER BY \"idEstudiante\" ASC ");

            while (resultado.next()){
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(resultado.getInt("idEstudiante"));
                estudiante.setPrimerApellido(resultado.getString("primerApellido"));
                estudiante.setSegundoApellido(resultado.getString("segundoApellido"));
                estudiante.setPrimerNombre(resultado.getString("primerNombre"));
                estudiante.setSegundoNombre(resultado.getString("segundoNombre"));
                lista.add(estudiante);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean Registrar(Estudiante estudiante){
        String consulta = "INSERT INTO estudiante(\"primerApellido\", \"segundoApellido\", \"primerNombre\", \"segundoNombre\", activo) " +
                "VALUES('" + estudiante.getPrimerApellido() + "', '" + estudiante.getSegundoApellido() + "', '" +
                estudiante.getPrimerNombre() + "', '" + estudiante.getSegundoNombre() + "', true)";
        int resultado = 0;
        try {
            Connection conexionBD = Conexion_PostgresSQL.getConexion();
            Statement statement = conexionBD.createStatement();
            resultado = statement.executeUpdate(consulta);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultado == 1;
    }

    public static boolean Editar(Estudiante estudiante){
        String consulta = "UPDATE estudiante SET " +
                "\"primerApellido\" = '" + estudiante.getPrimerApellido() + "', "+
                "\"segundoApellido\" = '" + estudiante.getSegundoApellido() + "', "+
                "\"primerNombre\" = '" + estudiante.getPrimerNombre() + "', "+
                "\"segundoNombre\" = '" + estudiante.getSegundoNombre() + "'"+
                " WHERE \"idEstudiante\" = " + estudiante.getIdEstudiante();
        int resultado = 0;
        try {
            Connection conexionBD = Conexion_PostgresSQL.getConexion();
            Statement statement = conexionBD.createStatement();
            resultado = statement.executeUpdate(consulta);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultado == 1;
    }

    public static boolean Eliminar(int idEstudiante){
        String consulta = "UPDATE estudiante SET activo = false WHERE \"idEstudiante\" = " + idEstudiante;

        int resultado = 0;
        try {
            Connection conexionBD = Conexion_PostgresSQL.getConexion();
            Statement statement = conexionBD.createStatement();
            resultado = statement.executeUpdate(consulta);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado == 1;
    }
}
