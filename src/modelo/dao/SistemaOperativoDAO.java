/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import sistemahwsw.pojo.SistemaOperativo;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author aaron
 */
public class SistemaOperativoDAO {
private static ConexionBD conexion = ConexionBD.getInstancia();
    
    public static ArrayList<SistemaOperativo> obtenerProgramaes() {
        ArrayList<SistemaOperativo> osEncontrados = new ArrayList<>();
        Connection conexionBD = conexion.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "SELECT * FROM Sistema_Operativo";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentenciaPreparada.executeQuery();
                while(resultadoConsulta.next()){
                    SistemaOperativo osAux = new SistemaOperativo();
                    osAux.setIdPrograma(resultadoConsulta.getInt("idSistema_Operativo"));
                    osAux.setVersion(resultadoConsulta.getString("version"));
                    osAux.setNombre(resultadoConsulta.getString("nombre"));
                    osAux.setArquitectura(resultadoConsulta.getString("arquitectura"));
                    osAux.setTipo(resultadoConsulta.getString("tipo"));
                    osEncontrados.add(osAux);
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
        return osEncontrados;
    }    
}
