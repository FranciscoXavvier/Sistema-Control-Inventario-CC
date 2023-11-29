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
import javafx.stage.Stage;
import sistemahwsw.pojo.EquipoComputo;

/**
 * FXML Controller class
 *
 * @author aaron
 */
public class FXMLTipoConsultaEquipoComputoController implements Initializable {

    private EquipoComputo equipoSeleccionado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickConsultarPerifericos(ActionEvent event) {
        try{
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarEquipoComputo.fxml"));
            Parent vista = accesoControlador.load();

            FXMLConsultarEquipoComputoController controlador = accesoControlador.getController();

            controlador.configurarEquipoSeleccionado(equipoSeleccionado);
            
            Node origen = (Node) event.getSource();
            Stage escenarioActual = (Stage) origen.getScene().getWindow();
            Scene escenaConsultarEquipoComputo = new Scene(vista);
            escenarioActual.setScene(escenaConsultarEquipoComputo);
            escenarioActual.setTitle("Consultando periféricos");
            
            controlador.inicializarDatos();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void clicConsultarProgramas(ActionEvent event) {
        try{
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarProgramasEquipoComputo.fxml"));
            Parent vista = accesoControlador.load();
            
            FXMLConsultarProgramasEquipoComputoController controlador = accesoControlador.getController();
            
            controlador.setEquipoComputo(equipoSeleccionado);
            
            Node origen = (Node) event.getSource();
            Stage escenarioActual = (Stage) origen.getScene().getWindow();
            Scene escenaConsultarProgramas = new Scene (vista);
            escenarioActual.setScene(escenaConsultarProgramas);
            escenarioActual.setTitle("Consultando programas instalados");
            
            controlador.configurarTabla();
            controlador.cargarDatosTabla();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void clicVolver(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenaActual = (Stage) origen.getScene().getWindow();
        escenaActual.close();
    }

    public void asignarComputadora(EquipoComputo seleccion) {
        equipoSeleccionado = seleccion;
    }
    
}
