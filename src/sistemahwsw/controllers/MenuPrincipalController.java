
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sistemahwsw.pojo.CentroComputo;
import sistemahwsw.pojo.Tecnico;

/**
 *
 * @author HP
 */
public class MenuPrincipalController implements Initializable {
        
    
    @FXML private javafx.scene.control.Button boton_cc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void usuario(ActionEvent event){
        try{
                Stage stageConsultar = (Stage) boton_cc.getScene().getWindow();
 
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
    
    
    public void salir(ActionEvent event){
        try {
            Stage stage = (Stage) boton_cc.getScene().getWindow();

            stage.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicIrEquipoComputo(ActionEvent event) {
        try{
            Stage stageConsultar = (Stage) boton_cc.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLMenuEquipoComputo.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

}
