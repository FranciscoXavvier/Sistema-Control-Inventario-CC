/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author super
 */
public class ConexionBD {
    
    private static Connection conexionBD = null;
        
    private static String driver = "com.mysql.jdbc.Driver";
    
    private static String bd = "SistemaControlHardwareSoftware";
    
    private static String ip = "localhost";
    
    private static String puerto = "3306";
    
    private static String urlConexion = "jdbc:mysql://"+ip+":"+puerto
            +"/"+bd+"?allowPublicKeyRetrieval=true&useSSL=false";
    
    private static String usuario = "root";
    private static String password = "Traker117!";
    private static ConexionBD instanciaDeConexion = new ConexionBD();
    
    
    public static ConexionBD getInstancia (){
        return instanciaDeConexion;
    }
    
    public static Connection abrirConexionBD(){
        conexionBD = null;
        try {
            Class.forName(driver);
            conexionBD = DriverManager.getConnection(urlConexion, usuario, password);
        } catch (ClassNotFoundException ex ) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexionBD;
    }
    
    public static void cerrarConexion(){
        try{
            conexionBD.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
