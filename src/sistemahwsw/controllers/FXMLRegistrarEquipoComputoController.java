/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sistemahwsw.pojo.EquipoComputo;

/**
 * FXML Controller class
 *
 * @author ABF26
 */
public class FXMLRegistrarEquipoComputoController implements Initializable {

    private TextField tf_procesador;
    private TextField tf_tarjetaMadre;
    private TextField tf_memoriaRam;
    private TextField tf_almacenamiento;
    private TextField tf_lectorOptico;
    private TextField tf_codigoBarras;
    private TextField tf_fila;
    private TextField tf_columna;
    @FXML
    private Button boton_cancelar;
    @FXML
    private TextField tf_centro_computo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    private void clicRegistrar(ActionEvent event) {
        String procesador = tf_procesador.getText();
        String tarjeta_madre = tf_tarjetaMadre.getText();
        String memoria_ram = tf_memoriaRam.getText();
        String almacenamiento = tf_almacenamiento.getText();
        String lector_optico = tf_lectorOptico.getText();
        String codigo_de_barras = tf_codigoBarras.getText();
        String fila = tf_fila.getText();
        String columna = tf_columna.getText();
        
        EquipoComputo equipoComputo = new EquipoComputo();
        equipoComputo.setProcesador(procesador);
        equipoComputo.setTarjetaMadre(tarjeta_madre);
        equipoComputo.setMemoriaRam(memoria_ram);
        equipoComputo.setAlmacenamiento(almacenamiento);
        equipoComputo.setLectorOptico(lector_optico);
        equipoComputo.setCodigoDeBarras(codigo_de_barras);
        equipoComputo.setFila(fila);
        equipoComputo.setColumna(columna);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registro exitoso");
            alert.setHeaderText("Equipo de cómputo registrado correctamente.");
            alert.setContentText("La información se ha registrado correctamente.");
            alert.showAndWait();
    }

    @FXML
    private void registrar(ActionEvent event) {
    }
    
}
