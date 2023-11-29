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
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author super
 */
public class ProgramaDAO {
    private static final ConexionBD conexion = ConexionBD.getInstancia();
    
    public static ArrayList<Programa> obtenerProgramas() {
        ArrayList<Programa> ProgramasEncontradas = new ArrayList<>();
        Connection conexionBD = conexion.abrirConexionBD();
        if (conexionBD != null){
            try {
                String consulta = "SELECT idPrograma, nombre, version, edicion, idioma FROM Programa";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = sentenciaPreparada.executeQuery();
                while(resultadoConsulta.next()){
                    Programa ProgramaAux = new Programa();
                    ProgramaAux.setIdPrograma(resultadoConsulta.getInt("idPrograma"));
                    ProgramaAux.setNombre(resultadoConsulta.getString("nombre"));
                    ProgramaAux.setEdicion(resultadoConsulta.getString("edicion"));
                    ProgramaAux.setVersion(resultadoConsulta.getString("version"));
                    ProgramaAux.setIdioma(resultadoConsulta.getString("idioma"));
                    ProgramasEncontradas.add(ProgramaAux);
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
        return ProgramasEncontradas;
    }

    public static boolean registrarPrograma(Programa nuevoPrograma) {
        boolean seRegistro = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "INSERT INTO Programa (nombre, version, edicion, idioma) VALUES (?,?,?,?)";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setString(1, nuevoPrograma.getNombre());
                sentenciaPreparada.setString(2, nuevoPrograma.getVersion());
                sentenciaPreparada.setString(3, nuevoPrograma.getEdicion());
                sentenciaPreparada.setString(4, nuevoPrograma.getIdioma());
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
    //Hacer que se eliminen las Programaes de las computadoras instaladas
    public static boolean eliminarPrograma (int idPrograma){
        boolean seElimino = false;
        ProgramaInstaladoDAO.eliminarECRelacionados(idPrograma);
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "DELETE FROM Programa WHERE idPrograma = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, idPrograma);
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
    
    public static boolean editarPrograma(Programa ProgramaEditado, Programa ProgramaSeleccionado) {
        boolean seEdito = false;
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "UPDATE Programa SET nombre = ?, version = ?, "
                        + "edicion = ?,idioma = ? WHERE idPrograma = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setString(1, ProgramaEditado.getNombre());
                sentenciaPreparada.setString(2, ProgramaEditado.getVersion());
                sentenciaPreparada.setString(3, ProgramaEditado.getEdicion());
                sentenciaPreparada.setString(4, ProgramaEditado.getIdioma());
                sentenciaPreparada.setInt(5, ProgramaSeleccionado.getIdPrograma());
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

    public static ArrayList<Programa> obtenerProgramasInstalados(int idEquipoComputo) {
        ArrayList<Programa> programasInstalados = new ArrayList<>();
        Connection conexionBD = conexion.abrirConexionBD();
        if(conexionBD != null){
            try {
                String consulta = "SELECT\n"
                        + "	p.nombre, \n"
                        + "	p.idPrograma, \n"
                        + "	p.edicion, \n"
                        + "     p.version, \n"
                        + "     p.idioma \n"
                        + "FROM\n"
                        + "	Programa p\n"
                        + "INNER JOIN \n"
                        + "	Programa_Instalado pins \n"
                        + "ON \n"
                        + "	p.idPrograma = pins.Programa_idPrograma\n"
                        + "INNER JOIN \n"
                        + "	Equipo_Computo ec \n"
                        + "ON \n"
                        + "	pins.CPU_idCPU = ec.idCPU\n"
                        + "WHERE\n"
                        + "	ec.idCPU = ?";
                PreparedStatement sentenciaPreparada = conexionBD.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, idEquipoComputo);
                ResultSet resultadoConsulta = sentenciaPreparada.executeQuery();
                while(resultadoConsulta.next()){
                    Programa programaAux = new Programa();
                    programaAux.setIdPrograma(resultadoConsulta.getInt("idPrograma"));
                    programaAux.setEdicion(resultadoConsulta.getString("edicion"));
                    programaAux.setNombre(resultadoConsulta.getString("nombre"));
                    programaAux.setVersion(resultadoConsulta.getString("version"));
                    programaAux.setIdioma(resultadoConsulta.getString("idioma"));
                    programasInstalados.add(programaAux);
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Error", "No hay conexión a la base de datos", 
                        Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
        return programasInstalados;
    }
}
