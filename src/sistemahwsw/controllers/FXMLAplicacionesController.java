/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sistemahwsw.pojo.Aplicacion;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author super
 */
public class FXMLAplicacionesController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }       
    
    @FXML
    private void clicConsultar(ActionEvent event) {
        Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLConsultarAplicaciones.fxml");
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
        try{
            FXMLLoader accesoControlador = new FXMLLoader(getClass().
                    getResource("/sistemahwsw/vistas/FXMLFormularioAplicacion.fxml"));
            Parent vista = accesoControlador.load();
            
            FXMLFormularioAplicacionController formulario = 
                    accesoControlador.getController();
            Scene escenaFormulario = new Scene(vista);
            Node origen = (Node) event.getSource();
            Stage escenario = (Stage) origen.getScene().getWindow();
            escenario.setScene(escenaFormulario);
            formulario.inicializarComponentes(null, FXMLFormularioAplicacionController.TipoOperacion.REGISTRO);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la pantalla de formulario", 
                    Alert.AlertType.ERROR);
        }       
    }

    
    @FXML
    private void clicVolver(ActionEvent event) {
        Utilidades.cambiarVentana("Menú principal", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLmenuPrincipal.fxml");
    }
    
    
}
