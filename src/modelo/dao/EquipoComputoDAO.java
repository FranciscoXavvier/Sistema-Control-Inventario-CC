package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.EquipoComputo;
import sistemahwsw.pojo.SistemaOperativo;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author ABF26
 */
public class EquipoComputoDAO {
    private static ConexionBD conexionBD= ConexionBD.getInstancia();
    public static EquipoComputo registrar(EquipoComputo equipoComputo){
        try (Connection connection = conexionBD.abrirConexionBD()){
            String query = "INSERT INTO `SistemaControlHardwareSoftware`.`Equipo_Computo` (`Sistema_Operativo_idSistema_Operativo`,`Centro_Computo_idCentro_Computo`,"
                    + "`procesador`, `tarjeta_madre`, `memoria_ram`, `almacenamiento`, `lector_optico`, `codigo_de_barras`, `fila`, `columna`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, equipoComputo.getSistemaOperativoInstalado().getIdSistemaOperativo());
            statement.setInt(2, equipoComputo.getIdCentroComputo());
            statement.setString(3, equipoComputo.getProcesador());
            statement.setString(4, equipoComputo.getTarjetaMadre());
            statement.setString(5, equipoComputo.getMemoriaRam());
            statement.setString(6, equipoComputo.getAlmacenamiento());
            statement.setString(7, equipoComputo.getLectorOptico());
            statement.setString(8, equipoComputo.getCodigoDeBarras());
            statement.setString(9, equipoComputo.getFila());
            statement.setString(10, equipoComputo.getColumna());
            
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<EquipoComputo> getEquiposDeComputoPorIdCC(int idCC){
        ArrayList<EquipoComputo> listaEquipoDeComputo = new ArrayList();
        try(Connection connection = conexionBD.abrirConexionBD()){
            String consulta = "SELECT * FROM Equipo_Computo WHERE Centro_Computo_idCentro_Computo = ?";
            PreparedStatement sentencia = connection.prepareStatement(consulta);
            
            sentencia.setInt(1, idCC);
            
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()){
                EquipoComputo ecAux = new EquipoComputo();
                SistemaOperativo osAux = new SistemaOperativo();
                ecAux.setIdEquipo(resultado.getInt("idEquipo_Computo"));
                ecAux.setProcesador(resultado.getString("procesador"));
                ecAux.setTarjetaMadre(resultado.getString("tarjeta_madre"));
                ecAux.setMemoriaRam(resultado.getString("memoria_ram"));
                ecAux.setAlmacenamiento(resultado.getString("almacenamiento"));
                ecAux.setLectorOptico(resultado.getString("lector_optico"));
                ecAux.setCodigoDeBarras(resultado.getString("codigo_de_barras"));
                osAux.setIdSistemaOperativo(resultado.getInt("Sistema_Operativo_idSistema_Operativo"));
                ecAux.setSistemaOperativoInstalado(osAux);
                ecAux.setFila(resultado.getString("fila"));
                ecAux.setColumna(resultado.getString("columna"));

                listaEquipoDeComputo.add(ecAux);
            }
        }catch(SQLException e){
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            e.printStackTrace();    
        }
        
        return listaEquipoDeComputo;
    }
    
    public static boolean eliminarEquipoComputoPorID (int idEquipoComputo){
        boolean seEliminoEquipo = false;
        ProgramaInstaladoDAO.eliminarAplicacionesRelacionadas(idEquipoComputo);
        try (Connection connection = conexionBD.abrirConexionBD()){
            String consulta = "DELETE FROM Equipo_Computo WHERE idEquipo_Computo = ?";
            PreparedStatement sentenciaPreparada = connection.prepareStatement(consulta);
            sentenciaPreparada.setInt(1, idEquipoComputo);
            int resultado = sentenciaPreparada.executeUpdate();
            if (resultado > 0 ){
                seEliminoEquipo = true;
            }
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }
        
        return seEliminoEquipo;
    }

    public static boolean editarEc(EquipoComputo ecNuevo, EquipoComputo ecACambiar) {
        boolean seEdito = false;
        try ( Connection connection = conexionBD.abrirConexionBD()) {
            String consulta = "UPDATE equipo_computo SET Sistema_Operativo_idSistema_Operativo = ?, "
                    + "procesador = ?, tarjeta_madre = ?, memoria_ram = ?, almacenamiento = ?,"
                    + "lector_optico = ?, codigo_de_barras = ?, fila = ?, columna = ? WHERE idEquipo_Computo = ?";
            PreparedStatement sentenciaPreparada = connection.prepareStatement(consulta);
            sentenciaPreparada.setInt(1, ecNuevo.getSistemaOperativoInstalado().getIdSistemaOperativo());
            sentenciaPreparada.setString(2, ecNuevo.getProcesador());
            sentenciaPreparada.setString(3, ecNuevo.getTarjetaMadre());
            sentenciaPreparada.setString(4, ecNuevo.getMemoriaRam());
            sentenciaPreparada.setString(5, ecNuevo.getAlmacenamiento());
            sentenciaPreparada.setString(6, ecNuevo.getLectorOptico());
            sentenciaPreparada.setString(7, ecNuevo.getCodigoDeBarras());
            sentenciaPreparada.setString(8, ecNuevo.getFila());
            sentenciaPreparada.setString(9, ecNuevo.getColumna());
            sentenciaPreparada.setInt(10, ecACambiar.getIdEquipo());
            int resultado = sentenciaPreparada.executeUpdate();
            if (resultado > 0) {
                seEdito = true;
            }
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "No hay conexión con la base de datos",
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        return seEdito;  
    }
    
}
