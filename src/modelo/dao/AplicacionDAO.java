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
import sistemahwsw.pojo.Aplicacion;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author super
 */
public class AplicacionDAO {
    private static ConexionBD conexion = ConexionBD.getInstancia();
    
    public static ArrayList<Aplicacion> obtenerAplicaciones() {
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

    public static boolean registrarAplicacion(Aplicacion nuevaAplicacion) {
        boolean seRegistro = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "INSERT INTO aplicaciones (nombre, version, idioma) VALUES (?,?,?)";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setString(1, nuevaAplicacion.getNombre());
                sentenciaPreparada.setString(2, nuevaAplicacion.getVersion());
                sentenciaPreparada.setString(3, nuevaAplicacion.getIdioma());
                int filasAfectadas = sentenciaPreparada.executeUpdate();
                if (filasAfectadas == 1){
                    seRegistro = true;
                }
            }catch(SQLException e){
                Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos", 
                    Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
        return seRegistro;
    }
    
    //TODO
    //Hacer que se eliminen las aplicaciones de las computadoras instaladas
    public static boolean eliminarAplicacion (int idAplicacion){
        boolean seElimino = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "DELETE FROM aplicaciones WHERE idSoftware = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, idAplicacion);
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
    
    public static boolean editarAplicacion(Aplicacion aplicacionEditada, Aplicacion aplicacionSeleccionada) {
        boolean seEdito = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "UPDATE aplicaciones SET nombre = ?, version = ?, "
                        + "idioma =? WHERE idSoftware = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setString(1, aplicacionEditada.getNombre());
                sentenciaPreparada.setString(2, aplicacionEditada.getVersion());
                sentenciaPreparada.setString(3, aplicacionEditada.getIdioma());
                sentenciaPreparada.setInt(4, aplicacionSeleccionada.getIdAplicacion());
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
