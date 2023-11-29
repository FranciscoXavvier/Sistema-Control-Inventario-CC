/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import modelo.dao.TecnicoDAO;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.Tecnico;
import sistemahwsw.utilidades.Utilidades;

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
    @FXML private javafx.scene.control.Button boton_registrar_tecnico;
    
    
    
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
    }    
    
    public void iniciarSesion(ActionEvent event) throws Exception {               
        String username = tf_usuario.getText();
        String password = tf_password.getText();

        Tecnico tecnico = TecnicoDAO.findByUsername(username);

        if (tecnico != null && tecnico.getPassword().equals(password))
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLmenuPrincipal.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) tf_usuario.getScene().getWindow();
            stage.setTitle("Menú principal");
            stage.setScene(scene);

            MenuPrincipalController controlador = fxmlLoader.getController();
            controlador.parametros(username, this);
            
            
        } else {
            // Las credenciales son inválidas
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Campos vacíos");
            alert.setHeaderText("Campos inválidos o vacíos.");
            alert.setContentText("Asegurate de que ingreses la información que se necesita.");
            alert.showAndWait();
            System.out.println("Inicio de sesión fallido");
        } 
    }   
    

    
    public void registrar(ActionEvent event) throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLRegistrarTecnico.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            Scene escena = new Scene(root1);
            stage.setScene(escena);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
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