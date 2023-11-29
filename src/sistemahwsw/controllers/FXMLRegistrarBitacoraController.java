/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.BitacoraDAO;

import sistemahwsw.pojo.Bitacora;
import sistemahwsw.pojo.Programa;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLRegistrarBitacoraController implements Initializable {

    @FXML
    private Button boton_cancelar;
    @FXML
    private TextField tf_bitacora;
    
    private Bitacora bitacoraDAO;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Label lbTitulo;

    private TipoOperacion operacionSeleccionada;
    @FXML
    private Button btn_registrar;
    public enum TipoOperacion{
        EDICION
    }
      private Bitacora bitacoraSeleccionada;
      private ArrayList<Bitacora> bitacorasRegistradas;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrar(ActionEvent event) {
         String descripcion = tf_bitacora.getText();

        BitacoraDAO.registrar( descripcion);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText("Centro de cómputo registrado correctamente.");
        alert.setContentText("La información se ha registrado correctamente .");
        alert.showAndWait();
        cerrarVentana();
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            cerrarVentana();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void inicializarComponentes(Bitacora seleccion, TipoOperacion operacion){
        operacionSeleccionada = operacion;
        switch(operacion){
         
            case EDICION:
                lbTitulo.setText("Bitácora");
                lbDescripcion.setVisible(false);
                tf_bitacora.setEditable(false);
                btn_registrar.setVisible(false);
                bitacoraSeleccionada = seleccion;
                inicializarValores(seleccion);
                break;
           
            default:
                System.out.println("No se puede desplegar el formulario");
        }
    }
    
    public void inicializarValores(Bitacora seleccion){
        tf_bitacora.setText(seleccion.getDescripcion());
    }
    
    
    private void cerrarVentana() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLBitacora.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            BitacoraController controlador = fxmlLoader.getController();
            controlador.configurarTabla();
            controlador.cargarDatosTabla();
            stage.show();

            Stage escenarioActual = (Stage) tf_bitacora.getScene().getWindow();
            escenarioActual.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     public void setBitacorasBD(ArrayList<Bitacora> bitacoras){
        this.bitacorasRegistradas = bitacoras;
    }
    
}
