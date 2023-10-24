/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.Tecnico;

/**
 *
 * @author HP
 */
public class TecnicoDAO {
    private static ConexionBD conexionBD= ConexionBD.getInstancia();
    

    public static Tecnico findByUsername(String username) {
        
        try (Connection connection = conexionBD.abrirConexionBD()){
            String query = "SELECT * FROM Tecnico WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Tecnico tecnico = new Tecnico();
                tecnico.setIdTecnico(resultSet.getInt("idTecnico"));
                tecnico.setNumeroPersonal(resultSet.getString("numero_personal"));
                tecnico.setNombre(resultSet.getString("nombre"));
                tecnico.setApellidoPaterno(resultSet.getString("apellido_paterno"));
                tecnico.setApellidoMaterno(resultSet.getString("apellido_materno"));
                tecnico.setUsername(resultSet.getString("username"));
                tecnico.setPassword(resultSet.getString("password"));
                return tecnico;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
