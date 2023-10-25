/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.CentroComputo;


/**
 *
 * @author HP
 */
public class CentroComputoDAO {
    private static ConexionBD conexionBD= ConexionBD.getInstancia(); 

    public static CentroComputo registrar(int idTecnico,CentroComputo centroComputo){
        try (Connection connection = conexionBD.abrirConexionBD()){
            String query = "INSERT INTO `sistemacontrolhardwaresoftware`.`centro_computo` (`Tecnico_idTecnico`,`nombre_centro_computo`) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, 1); 
            statement.setString(2, centroComputo.getNombreCentroComputo() ); 
            
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
