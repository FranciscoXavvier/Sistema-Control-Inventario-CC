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
import javafx.scene.Node;
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

    private int idCC;
    @FXML
    private Button boton_cc;
    @FXML
    private Button clicRegresar;

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
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLRegistrarEquipoComputo.fxml"));
            Parent vista = accesoControlador.load();
            FXMLRegistrarEquipoComputoController registro = accesoControlador.getController();
            
            registro.inicializarIdCC(idCC);
            registro.inicializarSistemasOperativos();
            registro.setOperacion(FXMLRegistrarEquipoComputoController.TipoOperacion.REGISTRO);
            
            Scene escena = new Scene(vista);
            Node nodo = (Node) event.getSource();
            Stage escenario = (Stage) nodo.getScene().getWindow();
            escenario.setScene(escena);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clicPeriferico(ActionEvent event) {
        
    }
    
    public void inicializaridCC(int idCC){
        this.idCC = idCC;
    }
}
