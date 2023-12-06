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
import sistemahwsw.pojo.Bitacora;
import sistemahwsw.pojo.CentroComputo;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author HP
 */
public class BitacoraDAO {
     private static ConexionBD conexion= ConexionBD.getInstancia(); 

    public static Bitacora registrar(String descripcion){
        try (Connection connection = conexion.abrirConexionBD()){
            String query = "INSERT INTO `SistemaControlHardwareSoftware`.`bitacora` (`descripcion`) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, descripcion);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
     public static ArrayList<Bitacora> obtenerBitacora() {
        ArrayList<Bitacora> bitacoraEncontradas = new ArrayList<>();
        Connection conexionBD = conexion.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "SELECT idBitacora, descripcion FROM bitacora";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentenciaPreparada.executeQuery();
                while(resultadoConsulta.next()){
                    Bitacora ccAux = new Bitacora();
                    ccAux.setIdBitacora(resultadoConsulta.getInt("idBitacora"));
                    ccAux.setDescripcion(resultadoConsulta.getString("descripcion"));
                    bitacoraEncontradas.add(ccAux);
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
        return bitacoraEncontradas;
    }

      public static boolean eliminarBitacora (int idBitacora){
        boolean seElimino = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "DELETE FROM bitacora WHERE idBitacora = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, idBitacora);
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
    
}
