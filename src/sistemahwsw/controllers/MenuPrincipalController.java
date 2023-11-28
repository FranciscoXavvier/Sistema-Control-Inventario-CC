
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
    private LoginController milc;
    private String miUsername;
    
    @FXML
    private Button boton_cc;
    
     @FXML
    private Label lbl_bienvenido;
        
    
    public void parametros(String username, LoginController lc){
        milc = lc;
        miUsername = username;
        lbl_bienvenido.setText("Bienvenido "+miUsername+"!");
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void usuario(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarTecnico.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            ConsultarTecnicoController controlador = (ConsultarTecnicoController) fxmlLoader.getController();
            controlador.parametros(miUsername, this);
            stage.showAndWait();
         
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

    
    @FXML
    private void ClicProgramas(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLProgramas.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        Utilidades.cambiarVentana("Inicio de sesión", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLLogin.fxml");
    }
    @FXML
    public void centro_computo(ActionEvent event){
        try{
                Stage stageConsultar = (Stage) boton_cc.getScene().getWindow();
 
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLMenuCentroComputo.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      
    @FXML
    private void clicIrEquipoComputo(ActionEvent event) {
        try{
            Stage stageConsultar = (Stage) boton_cc.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarCC.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menú equipo de cómputo");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
       
