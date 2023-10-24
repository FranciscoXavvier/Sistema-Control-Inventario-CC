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
import javafx.scene.control.PasswordField;
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
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML private javafx.scene.control.Button boton_salir;
    
    @FXML
    private TextField tf_usuario;
    @FXML
    private PasswordField tf_password;
    
    private TecnicoDAO tecnicoDAO;
    private ConexionBD connection = ConexionBD.getInstancia(); // Conexión a la base de datos

    public LoginController() {
        Connection conexion = connection.abrirConexionBD();
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void iniciarSesion(ActionEvent event) throws Exception {               
        
       
        String username = tf_usuario.getText();
        String password = tf_password.getText();

        Tecnico tecnico = TecnicoDAO.findByUsername(username);

        if (tecnico != null && tecnico.getPassword().equals(password))
        {
            try 
            {    
                Stage stageLogin = (Stage) boton_salir.getScene().getWindow();
                stageLogin.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLmenuPrincipal.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();

            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println("Inicio de sesión exitoso");
        } else {
            // Las credenciales son inválidas
            System.out.println("Inicio de sesión fallido");
        }
        
        
        
        //SI CREDENCIALES SON CORRECTAS
       
    }   
    
    public void salir(ActionEvent event) throws Exception {               
    try {
         Stage stage = (Stage) boton_salir.getScene().getWindow();
        
        stage.close();
        
    } catch(Exception e) {
        e.printStackTrace();
    }
    
    }

    
}
