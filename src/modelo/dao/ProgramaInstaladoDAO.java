/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ProgramaInstaladoDAO {
    private static final ConexionBD conexion = ConexionBD.getInstancia();
    
    public static boolean eliminarAplicacionesRelacionadas(int idEquipoComputo) {
        boolean seEliminoEquipoComputo = false;
        try(Connection connection = conexion.abrirConexionBD()){
            String consulta = "DELETE FROM Programa_Instalado WHERE Equipo_Computo_idEquipo_Computo = ?";
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
            String consulta = "DELETE FROM Programa_Instalado WHERE Equipo_Computo_idEquipo_Computo = ?";
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
    
    public static boolean desinstalarAplicaciones (ArrayList<Programa> programas, int idEc){
        boolean seDesinstalaronProgramas = true;
        try (Connection connection = conexion.abrirConexionBD()){
            for (Programa p: programas){
                String consulta = "DELETE FROM Programa_Instalado WHERE Programa_idPrograma = ? "
                        + "AND Equipo_Computo_idEquipo_Computo = ?";
                PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
                consultaPreparada.setInt(1, p.getIdPrograma());
                consultaPreparada.setInt(2, idEc);
                int filasAfectadas = consultaPreparada.executeUpdate();
                if (filasAfectadas < 0){
                    seDesinstalaronProgramas = false;
                    break;
                }
            }
        } catch (Exception ex) {
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return seDesinstalaronProgramas;
    }
    
    public static boolean instalarAplicaciones (ArrayList<Programa> programas, int idEc){
        boolean seInstalaronProgramas = true;
        try (Connection connection = conexion.abrirConexionBD()){
            for (Programa p: programas){
                String consulta = "INSERT INTO Programa_Instalado (Programa_idPrograma, Equipo_Computo_idEquipo_Computo) VALUES (?,?)";
                PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
                consultaPreparada.setInt(1, p.getIdPrograma());
                consultaPreparada.setInt(2, idEc);
                int filasAfectadas = consultaPreparada.executeUpdate();
                if (filasAfectadas < 0){
                    seInstalaronProgramas = false;
                    break;
                }
            }
        } catch (Exception ex) {
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return seInstalaronProgramas;        
    }
}
