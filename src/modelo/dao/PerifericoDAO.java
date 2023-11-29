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
import sistemahwsw.pojo.Periferico;
import sistemahwsw.pojo.TipoPeriferico;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author super
 */
public class PerifericoDAO {
    private static ConexionBD conexionBD = ConexionBD.getInstancia();
    
    public static ArrayList<TipoPeriferico> recuperarTipoDePerifericos(){
        ArrayList<TipoPeriferico> tiposDePerifericos= new ArrayList<>();
        try(Connection conexion = conexionBD.abrirConexionBD()){
            String consulta = "SELECT * FROM tipo_periferico";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();
            while (resultadoConsulta.next()){
                TipoPeriferico aux = new TipoPeriferico();
                aux.setIdTipoPeriferico(resultadoConsulta.getInt("idTipo_Periferico"));
                aux.setTipoPeriferico(resultadoConsulta.getString("tipo_periferico"));
                tiposDePerifericos.add(aux);
            }
        }catch(SQLException ex){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
        return tiposDePerifericos;
    }
    
    public static ArrayList<Periferico> recuperarPerifericosPorIDEC(int idEc){
        ArrayList <Periferico> perifericosRecuperados = new ArrayList<>();
        try ( Connection conexion = conexionBD.abrirConexionBD()) {
            String consulta = "SELECT periferico.idPeriferico, "
                    + "periferico.marca, \n"
                    + "	periferico.modelo, \n"
                    + "	periferico.codigo_de_barras,\n"
                    + "	tipo_periferico.idTipo_Periferico,\n"
                    + "	tipo_periferico.tipo_periferico \n"
                    + "	FROM \n"
                    + "		`periferico` \n"
                    + "	INNER JOIN \n"
                    + "		tipo_periferico \n"
                    + "	ON \n"
                    + "		periferico.Tipo_Periferico_idTipo_Periferico = tipo_periferico.idTipo_Periferico \n"
                    + "	WHERE \n"
                    + "		periferico.Equipo_Computo_idEquipo_Computo = ?";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);
            consultaPreparada.setInt(1, idEc);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery();
            while (resultadoConsulta.next()) {
                Periferico perifericoAux= new Periferico();
                perifericoAux.setEquipoComputo(idEc);
                perifericoAux.setIdPeriferico(resultadoConsulta.getInt("idPeriferico"));
                perifericoAux.setMarca(resultadoConsulta.getString("marca"));
                perifericoAux.setModelo(resultadoConsulta.getString("modelo"));
                perifericoAux.setCodigoDeBarras(resultadoConsulta.getString("codigo_de_barras"));
                
                TipoPeriferico tipo = new TipoPeriferico();
                tipo.setIdTipoPeriferico(resultadoConsulta.getInt("idTipo_Periferico"));
                tipo.setTipoPeriferico(resultadoConsulta.getString("tipo_periferico"));
                        
                perifericoAux.setTipo(tipo);
                
                perifericosRecuperados.add(perifericoAux);
            }
        } catch (SQLException ex) {
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
        return perifericosRecuperados;
    }
    
    public static boolean registrarPeriferico(Periferico nuevoPeriferico, int idEc){
        boolean seRegistro = false;
        try(Connection conexion = conexionBD.abrirConexionBD()){
            String consulta = "INSERT INTO Periferico (`Tipo_Periferico_idTipo_Periferico`, "
                    + "`Equipo_Computo_idEquipo_Computo`, `marca`, `modelo`, `codigo_de_barras`)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);
            consultaPreparada.setInt(1, nuevoPeriferico.getTipo().getIdTipoPeriferico());
            consultaPreparada.setInt(2, idEc);
            consultaPreparada.setString(3, nuevoPeriferico.getMarca());
            consultaPreparada.setString(4, nuevoPeriferico.getModelo());
            consultaPreparada.setString(5, nuevoPeriferico.getCodigoDeBarras());
            int filasAfectadas = consultaPreparada.executeUpdate();
            if (filasAfectadas >0){
                seRegistro = true;
            }
        } catch  (SQLException ex){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();         
        }
        return seRegistro;
    }    
    
    public static boolean editarPeriferico(Periferico nuevoPeriferico, Periferico perifericoAEditar){
        boolean seEdito = false;
        try(Connection conexion = conexionBD.abrirConexionBD()){
            String consulta = "UPDATE periferico SET Tipo_Periferico_idTipo_Periferico = ?, "
                    + "marca = ?, modelo = ?, codigo_de_barras = ? WHERE idPeriferico = ?";
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);
            consultaPreparada.setInt(1, nuevoPeriferico.getTipo().getIdTipoPeriferico());
            consultaPreparada.setString(2, nuevoPeriferico.getMarca());
            consultaPreparada.setString(3, nuevoPeriferico.getModelo());
            consultaPreparada.setString(4, nuevoPeriferico.getCodigoDeBarras());
            consultaPreparada.setInt(5, perifericoAEditar.getIdPeriferico());
            int filasAfectadas = consultaPreparada.executeUpdate();
            if (filasAfectadas >0){
                seEdito = true;
            }
        } catch  (SQLException ex){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();         
        }
        return seEdito;        
    }
    
    public static boolean eliminarPerifericoPorId(int idPeriferico) {
        boolean seElimino = false;
        try(Connection conexion = conexionBD.abrirConexionBD()){
            String consulta = "DELETE FROM periferico WHERE idPeriferico = ?" ;
            PreparedStatement consultaPreparada = conexion.prepareStatement(consulta);
            consultaPreparada.setInt(1, idPeriferico);
            int filasAfectadas = consultaPreparada.executeUpdate();
            if (filasAfectadas > 0){
                seElimino = true;
            }
        } catch  (SQLException ex){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            ex.printStackTrace();         
        }        
        return seElimino;
    }
}
