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
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.Programa;
import sistemahwsw.pojo.CentroComputo;
import sistemahwsw.utilidades.Utilidades;


/**
 *
 * @author HP
 */
public class CentroComputoDAO {
    private static ConexionBD conexion= ConexionBD.getInstancia(); 

    public static CentroComputo registrar(int idTecnico,CentroComputo centroComputo){
        try (Connection connection = conexion.abrirConexionBD()){
            String query = "INSERT INTO `SistemaControlHardwareSoftware`.`Centro_Computo` (`Tecnico_idTecnico`,`nombre_centro_computo`) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, 1); 
            statement.setString(2, centroComputo.getNombreCentroComputo() ); 

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
     public static ArrayList<CentroComputo> obtenerCentroComputo() {
        ArrayList<CentroComputo> ccEncontrados = new ArrayList<>();
        Connection conexionBD = conexion.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "SELECT idCentro_Computo, Tecnico_idTecnico, nombre_centro_computo FROM Centro_Computo";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentenciaPreparada.executeQuery();
                while(resultadoConsulta.next()){
                    CentroComputo ccAux = new CentroComputo();
                    ccAux.setNombreCentroComputo(resultadoConsulta.getString("nombre_centro_computo"));
                    ccAux.setIdTecnico(resultadoConsulta.getInt("Tecnico_idTecnico"));
                    ccAux.setIdCentroComputo(resultadoConsulta.getInt("idCentro_Computo"));
                    ccEncontrados.add(ccAux);
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos", 
                    Alert.AlertType.ERROR);
                e.printStackTrace();
            }finally{
                conexion.cerrarConexion();
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión a la base de datos", Alert.AlertType.ERROR);
        }
        return ccEncontrados;
    }
     
     public static boolean eliminarCC (int idCC){
        boolean seElimino = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "DELETE FROM Centro_Computo WHERE idCentro_Computo = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, idCC);
                int filasAfectadas = sentenciaPreparada.executeUpdate();
                if (filasAfectadas == 1){
                    seElimino = true;
                }
            }catch(SQLException e){
                 Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos", 
                    Alert.AlertType.ERROR);
                e.printStackTrace();               
            }
        }
        return seElimino;
    }
     
     
     public static boolean editarCC(String nombreCC, int CCSeleccionado) {
        boolean seEdito = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "UPDATE Centro_Computo SET nombre_centro_computo = ?"
                        + "WHERE idCentro_Computo = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setString(1, nombreCC);
                sentenciaPreparada.setInt(2, CCSeleccionado);
                int filasAfectadas = sentenciaPreparada.executeUpdate();
                if(filasAfectadas == 1){
                    seEdito = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos", 
                    Alert.AlertType.ERROR);
                e.printStackTrace();       
            }
        }
        return seEdito;
    }
}
