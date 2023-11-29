/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author super
 */
class ProgramaInstaladoDAO {
    private static final ConexionBD conexion = ConexionBD.getInstancia();
    
    public static boolean eliminarAplicacionesRelacionadas(int idEquipoComputo) {
        boolean seEliminoEquipoComputo = false;
        try(Connection connection = conexion.abrirConexionBD()){
            String consulta = "DELETE FROM Programa_Instalado WHERE idEquipo_Computo = ?";
            PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
            consultaPreparada.setInt(1, idEquipoComputo);
            int filasAfectadas = consultaPreparada.executeUpdate();
            if (filasAfectadas >= 0){
                seEliminoEquipoComputo = true;
            }
        }catch(SQLException ex){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();
        }finally{
            conexion.cerrarConexion();
        }
        return seEliminoEquipoComputo;
    }
    
    public static boolean eliminarECRelacionados(int idPrograma) {
        boolean seEliminoEquipoComputo = false;
        try(Connection connection = conexion.abrirConexionBD()){
            String consulta = "DELETE FROM Programa_Instalado WHERE Programa_idPrograma = ?";
            PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
            consultaPreparada.setInt(1, idPrograma);
            int filasAfectadas = consultaPreparada.executeUpdate();
            if (filasAfectadas >= 0){
                seEliminoEquipoComputo = true;
            }
        }catch(SQLException ex){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();
        }finally{
            conexion.cerrarConexion();
        }
        return seEliminoEquipoComputo;
    }
}
