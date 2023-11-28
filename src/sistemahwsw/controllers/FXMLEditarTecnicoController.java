/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.TecnicoDAO;
import sistemahwsw.pojo.Tecnico;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLEditarTecnicoController implements Initializable {

    @FXML
    private Button boton_salir;
    @FXML
    private TextField tf_nombre;
    @FXML
    private TextField tf_usuario;
    @FXML
    private TextField tf_numero_personal;
    
    private ConsultarTecnicoController mimp;
    private Tecnico miUsuario;
    /**
     * Initializes the controller class.
     */
    public void parametros(String username, ConsultarTecnicoController mp){
            mimp = mp;
            miUsuario = TecnicoDAO.findByUsername(username);
            
            tf_numero_personal.setText(miUsuario.getNumeroPersonal());
            tf_usuario.setText(miUsuario.getUsername());
            tf_nombre.setPromptText(miUsuario.getNombre()+' '+miUsuario.getApellidoPaterno()+' '+miUsuario.getApellidoMaterno());
           
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void salir(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
        try {

             FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLmenuPrincipal.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) tf_usuario.getScene().getWindow();
            stage.setScene(scene);

            MenuPrincipalController controlador = (MenuPrincipalController) fxmlLoader.getController();
            controlador.parametrosDos(tf_usuario.getText(), this);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        TecnicoDAO.updateTecnico( Integer.parseInt(tf_numero_personal.getText()),tf_usuario.getText(), miUsuario.getIdTecnico());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Operación exitosa");
        alert.setHeaderText("Información guardada correctamente.");
        alert.setContentText("La información se ha registrado correctamente y ahora puedes haciendo uso de tu usuario.");
        alert.showAndWait();
    }
   
   
}
