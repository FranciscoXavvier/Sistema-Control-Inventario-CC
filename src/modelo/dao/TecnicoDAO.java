<<<<<<< HEAD
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

    public static Tecnico registrar(Tecnico tecnico){
        try (Connection connection = conexionBD.abrirConexionBD()){
            String query = "INSERT INTO `SistemaControlHardwareSoftware`.`Tecnico` (`numero_personal`, `nombre`, `apellido_paterno`, `apellido_materno`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, Integer.parseInt(tecnico.getNumeroPersonal())); 
            statement.setString(2, tecnico.getNombre()); 
            statement.setString(3, tecnico.getApellidoPaterno() );
            statement.setString(4, tecnico.getApellidoMaterno()); 
            statement.setString(5, tecnico.getUsername());
            statement.setString(6, tecnico.getPassword());
            
           statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
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
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import static java.sql.DriverManager.println;
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

    public static Tecnico registrar(Tecnico tecnico){
        try (Connection connection = conexionBD.abrirConexionBD()){
            String query = "INSERT INTO `SistemaControlHardwareSoftware`.`Tecnico` (`numero_personal`, `nombre`, `apellido_paterno`, `apellido_materno`, `username`, `password`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, Integer.parseInt(tecnico.getNumeroPersonal())); 
            statement.setString(2, tecnico.getNombre()); 
            statement.setString(3, tecnico.getApellidoPaterno() );
            statement.setString(4, tecnico.getApellidoMaterno()); 
            statement.setString(5, tecnico.getUsername());
            statement.setString(6, tecnico.getPassword());
            
           statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
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
    
    public static Tecnico updateTecnico(int numeroPersonal,String usernameViejo, int idTecnico ){
        try (Connection connection = conexionBD.abrirConexionBD()){
        
            String query = "UPDATE Tecnico SET numero_personal = ?,username = ? WHERE idTecnico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, numeroPersonal);
            statement.setString(2,usernameViejo);
            statement.setInt(3,idTecnico);
            int resultSet = statement.executeUpdate();
            if(resultSet == 1){
                     println("Se editó");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
>>>>>>> a3807e47c525ade9cc2f6b46468e1480ccd1f197
}