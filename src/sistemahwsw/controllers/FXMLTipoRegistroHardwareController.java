/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author ABF26
 */
public class FXMLTipoRegistroHardwareController implements Initializable {

    @FXML
    private Button boton_cc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clicEquipoComputo(ActionEvent event) {
        try{
            Utilidades.cambiarVentana("Registrar equipo de cómputo", boton_cc, "/sistemahwsw/vistas/FXMLRegistrarEquipoComputo.fxml");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clicPeriferico(ActionEvent event) {
        
    }
    
}
