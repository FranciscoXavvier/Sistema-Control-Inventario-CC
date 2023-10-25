/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.Aplicacion;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author super
 */
public class AplicacionDAO {
    private static ConexionBD conexion = ConexionBD.getInstancia();
    public static ArrayList<Aplicacion> obtenerAplicaciones(){
        ArrayList<Aplicacion> aplicacionesEncontradas = new ArrayList<>();
        Connection conexionBD = conexion.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "SELECT idSoftware, nombre, version, idioma FROM aplicaciones";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentenciaPreparada.executeQuery();
                while(resultadoConsulta.next()){
                    Aplicacion aplicacionAux = new Aplicacion();
                    aplicacionAux.setIdAplicacion(resultadoConsulta.getInt("idSoftware"));
                    aplicacionAux.setNombre(resultadoConsulta.getString("nombre"));
                    aplicacionAux.setVersion(resultadoConsulta.getString("version"));
                    aplicacionAux.setIdioma(resultadoConsulta.getString("idioma"));
                    aplicacionesEncontradas.add(aplicacionAux);
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
        return aplicacionesEncontradas;
    }
    
}
