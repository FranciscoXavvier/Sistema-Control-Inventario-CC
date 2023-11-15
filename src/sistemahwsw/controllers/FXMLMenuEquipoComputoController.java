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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sistemahwsw.pojo.EquipoComputo;

/**
 * FXML Controller class
 *
 * @author ABF26
 */
public class FXMLMenuEquipoComputoController implements Initializable {

    @FXML
    private TableView<EquipoComputo> tvEquipoComputo;
    @FXML
    private TableColumn<?, ?> tcId;
    @FXML
    private TableColumn<?, ?> tcNombre;
    @FXML
    private TableColumn<?, ?> tcOpciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicRegistrar(ActionEvent event) {
        try{
            Stage stageConsultar = (Stage) tvEquipoComputo.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLTipoRegistroHardware.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
