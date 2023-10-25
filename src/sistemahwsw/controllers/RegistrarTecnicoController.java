/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.TecnicoDAO;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.Tecnico;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegistrarTecnicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf_nombre;
    @FXML
    private TextField tf_paterno;
    @FXML
    private TextField tf_materno;
    @FXML
    private TextField tf_numero_personal;
    @FXML
    private TextField tf_usuario;
    @FXML
    private TextField tf_contrasena;
    
    private TecnicoDAO tecnicoDAO;
    private ConexionBD connection = ConexionBD.getInstancia(); // Conexión a la base de datos

    public RegistrarTecnicoController() {
        Connection conexion = connection.abrirConexionBD();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void registrar(ActionEvent event) throws Exception {               
        
        String nombre=tf_nombre.getText();
        String apellido_paterno= tf_paterno.getText();
        String apellido_materno=tf_materno.getText();
        String numero_personal = tf_numero_personal.getText();
        String usuario = tf_usuario.getText();
        String password = tf_contrasena.getText();
        
        Tecnico tecnico = new Tecnico();
        tecnico.setNumeroPersonal(numero_personal);
        tecnico.setNombre(nombre);
        tecnico.setApellidoPaterno(apellido_paterno);
        tecnico.setApellidoMaterno(apellido_materno);
        tecnico.setUsername(usuario);
        tecnico.setPassword(password);
        TecnicoDAO.registrar(tecnico);

        // Las credenciales son inválidas
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registro exitoso");
            alert.setHeaderText("Técnico registrado correctamente.");
            alert.setContentText("La información se ha registrado correctamente y ahora puedes hacer uso de tu usuario.");
            alert.showAndWait();
            System.out.println("Inicio de sesión fallido");
       
    }   
    
}
