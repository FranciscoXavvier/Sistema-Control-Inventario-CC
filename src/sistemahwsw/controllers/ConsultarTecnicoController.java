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
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConsultarTecnicoController implements Initializable {

    @FXML private javafx.scene.control.Button boton_salir;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void salir(ActionEvent event){
        try {
            Stage stage = (Stage) boton_salir.getScene().getWindow();

            stage.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
