package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.EquipoComputo;

/**
 *
 * @author ABF26
 */
public class EquipoComputoDAO {
    private static ConexionBD conexionBD= ConexionBD.getInstancia();
    public static EquipoComputo registrar(EquipoComputo equipoComputo){
        try (Connection connection = conexionBD.abrirConexionBD()){
            String query = "INSERT INTO `SistemaControlHardwareSoftware`.`Equipo_Computo` (`procesador`,`tarjeta_madre`, `memoria_ram`, `almacenamiento`, `lector_optico`, `codigo_de_barras`, `fila`, `columna`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, equipoComputo.getProcesador());
            statement.setString(2, equipoComputo.getTarjetaMadre());
            statement.setString(3, equipoComputo.getMemoriaRam());
            statement.setString(4, equipoComputo.getAlmacenamiento());
            statement.setString(5, equipoComputo.getLectorOptico());
            statement.setString(6, equipoComputo.getCodigoDeBarras());
            statement.setString(7, equipoComputo.getFila());
            statement.setString(8, equipoComputo.getColumna());
            
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
