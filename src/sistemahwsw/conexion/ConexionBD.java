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
        
    private static String driver = "com.mysql.jdbc.Driver";
    
    private static String bd = "sistemacontrolhardwaresoftware";
    
    private static String ip = "localhost";
    
    private static String puerto = "3306";
    
    private static String urlConexion = "jdbc:mysql://"+ip+":"+puerto
            +"/"+bd+"?allowPublicKeyRetrieval=true&useSSL=false";
    
    private static String usuario = "tecnico";
    private static String password = "tecUV2023";
    private static ConexionBD instanciaDeConexion = new ConexionBD();
    
    private ConexionBD (){
        
    }
    
    public static ConexionBD getConexionBD (){
        return instanciaDeConexion;
    }
    
    public static Connection abrirConexionBD(){
        Connection conexionBD = null;
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
}
