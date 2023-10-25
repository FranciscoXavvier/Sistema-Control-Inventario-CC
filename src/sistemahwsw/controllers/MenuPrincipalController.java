
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sistemahwsw.pojo.CentroComputo;
import sistemahwsw.pojo.Tecnico;
import sistemahwsw.utilidades.Utilidades;

/**
 *
 * @author HP
 */
public class MenuPrincipalController implements Initializable {
        
    
    private javafx.scene.control.Button boton_usuario;
    private Label label;
    
    @FXML
    private Button boton_cc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void usuario(ActionEvent event){
        try{
                Stage stageConsultar = (Stage) boton_usuario.getScene().getWindow();
 
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarTecnico.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    @FXML
    private void ClicProgramas(ActionEvent event) {
        Utilidades.cambiarVentana("Aplicaciones", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLAplicaciones.fxml");
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        Utilidades.cambiarVentana("Inicio de sesión", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLLogin.fxml");
    }

}
