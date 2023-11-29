/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.PerifericoDAO;
import sistemahwsw.pojo.EquipoComputo;
import sistemahwsw.pojo.Periferico;
import sistemahwsw.pojo.TipoPeriferico;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author super
 */
public class FXMLFormularioPerifericoController implements Initializable {

    public static enum TipoOperacion{
        EDICION,
        CONSULTA,
        REGISTRO;
    }
    
    private EquipoComputo seleccionEc;
    private TipoOperacion operacionSeleccionada;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private Label lbInstruccion;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnSalir;

    @FXML
    private void clicGuardar(ActionEvent event) {
        switch(operacionSeleccionada){
            case REGISTRO:
                
                break;
            case EDICION:
                break;
            case CONSULTA:
                break;
            default:
                System.out.println("Error al realizar accion");
        }
    }

    @FXML
    private void clicSalir(ActionEvent event) {
        FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarEquipoComputo.fxml"));
        try {
            Parent vista = accesoControlador.load();

            FXMLConsultarEquipoComputoController controlador = accesoControlador.getController();
            controlador.configurarEquipoSeleccionado(seleccionEc);

            Stage stageActual = (Stage) tfEquipoDeComputoAsignado.getScene().getWindow();
            Scene vistaConsultaEc = new Scene(vista);
            stageActual.setTitle("Consultando periféricos");
            stageActual.setScene(vistaConsultaEc);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private Label lbTipoVentana;
    @FXML
    private TextField tfEquipoDeComputoAsignado;
    @FXML
    private ComboBox<TipoPeriferico> cbTipoPeriferico;

    private ArrayList<TipoPeriferico> tipoPerifericoRespuesta;
    private ObservableList<TipoPeriferico> tipoPerifericoBD;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inicializarDatos(Periferico seleccion, TipoOperacion tipoOperacion, EquipoComputo seleccionEc) {
        this.operacionSeleccionada = tipoOperacion;
        switch (tipoOperacion) {
            case REGISTRO:
                lbTipoVentana.setText("Registro de periférico");
                lbInstruccion.setText("Ingrese la información del periférico");
                tfEquipoDeComputoAsignado.setText(seleccionEc.toString());
                inicializarTipoPeriferico();
                
                
                break;
            case EDICION:
                lbTipoVentana.setText("Modificar periférico");
                lbInstruccion.setText("Ingrese la nueva información del periférico");
                inicializarTipoPeriferico();
                
                break;
            case CONSULTA:
                
                break;
            default:
                System.out.println("No se puede acceder a la ventana");
        }
    }
    
    private void resaltarTfVacio(TextField campoVacio) {
        campoVacio.setStyle("-fx-border-color: FF0000;");
    }

    private void cambiarColorTf(TextField tf) {
        tf.setStyle("-fx-border-color: CFCFCF;");
    }

    public void inicializarTipoPeriferico() {
        tipoPerifericoRespuesta = PerifericoDAO.recuperarTipoDePerifericos();
        tipoPerifericoBD = FXCollections.observableList(tipoPerifericoRespuesta);
        cbTipoPeriferico.setItems(tipoPerifericoBD);
    }

    public TipoPeriferico validarSeleccion() {
        TipoPeriferico tipoPerifericoSeleccionado = cbTipoPeriferico.getValue();
        System.out.println(cbTipoPeriferico);
        return tipoPerifericoSeleccionado;
    }
    
    private boolean camposVacios() {
        boolean camposVacios = false;
        if(tfMarca.getText().isEmpty()){
            resaltarTfVacio(tfMarca);
            camposVacios = true;
        }else{
            cambiarColorTf(tfMarca);
        }
        if(tfModelo.getText().isEmpty()){
            resaltarTfVacio(tfModelo);
            camposVacios = true;
        }else{
            cambiarColorTf(tfModelo);
        }
        return camposVacios;
    }
    
}
