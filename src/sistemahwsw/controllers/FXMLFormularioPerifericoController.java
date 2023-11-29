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

    @FXML
    private TextField tfCodigoDeBarras;
    @FXML
    private TextField tfID;
    @FXML
    private Label lbId;

    public static enum TipoOperacion {
        EDICION,
        CONSULTA,
        REGISTRO;
    }

    private EquipoComputo seleccionEc;
    private TipoOperacion operacionSeleccionada;
    private ArrayList<Periferico> perifericosBD;
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
    private Label lbTipoVentana;
    @FXML
    private TextField tfEquipoDeComputoAsignado;
    @FXML
    private ComboBox<TipoPeriferico> cbTipoPeriferico;

    private Periferico seleccionPeriferico;
    private ArrayList<TipoPeriferico> tipoPerifericoRespuesta;
    private ObservableList<TipoPeriferico> tipoPerifericoBD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    @FXML
    private void clicGuardar(ActionEvent event) {
        switch (operacionSeleccionada) {
            case REGISTRO:
                if (!camposVacios() && validarSeleccion() != null) {
                    Periferico nuevoPeriferico = new Periferico();
                    nuevoPeriferico.setCodigoDeBarras(tfCodigoDeBarras.getText());
                    nuevoPeriferico.setMarca(tfMarca.getText());
                    nuevoPeriferico.setModelo(tfModelo.getText());
                    nuevoPeriferico.setTipo(validarSeleccion());
                    if (validarNuevoPeriferico(nuevoPeriferico)) {
                        if (PerifericoDAO.registrarPeriferico(nuevoPeriferico, seleccionEc.getIdEquipo())){
                            Utilidades.mostrarAlertaSimple("Registro exitoso", 
                                    "El periférico ha sido registrado con éxito", 
                                    Alert.AlertType.INFORMATION);
                            volverAConsultarEquipoComputo();
                        }
                    }else{
                        Utilidades.mostrarAlertaSimple("Error de registro", 
                                "Por favor seleccione un nuevo tipo de periférico, ya que se encuentra un periférico del mismo tipo registrado", 
                                Alert.AlertType.ERROR);
                    }
                } else {
                    Utilidades.mostrarAlertaSimple("Campos vacíos",
                            "Debe llenar todos los campos para el registro",
                            Alert.AlertType.ERROR);
                }
                break;
            case EDICION:
                if (!camposVacios() && validarSeleccion() != null) {
                    Periferico nuevoPeriferico = new Periferico();
                    nuevoPeriferico.setCodigoDeBarras(tfCodigoDeBarras.getText());
                    nuevoPeriferico.setMarca(tfMarca.getText());
                    nuevoPeriferico.setModelo(tfModelo.getText());
                    nuevoPeriferico.setTipo(validarSeleccion());
                    if (validarEdicionPeriferico(nuevoPeriferico)) {
                        if (PerifericoDAO.editarPeriferico(nuevoPeriferico, seleccionPeriferico)) {
                            Utilidades.mostrarAlertaSimple("Periférico modificado exitosamente",
                                    "El periférico ha sido registrado con éxito",
                                    Alert.AlertType.INFORMATION);
                            volverAConsultarEquipoComputo();
                        }
                    } else {
                        Utilidades.mostrarAlertaSimple("Error de edición",
                                "Por favor seleccione un nuevo tipo de periférico, ya que se encuentra un periférico del mismo tipo registrado",
                                Alert.AlertType.ERROR);
                    }
                } else {
                    Utilidades.mostrarAlertaSimple("Campos vacíos",
                            "Debe llenar todos los campos para la edición",
                            Alert.AlertType.ERROR);
                }
                break;
            case CONSULTA:
                break;
            default:
                System.out.println("Error al realizar accion");
        }
    }

    @FXML
    private void clicSalir(ActionEvent event) {
        volverAConsultarEquipoComputo();
    }
    
    public void inicializarDatos(Periferico seleccion, TipoOperacion tipoOperacion, EquipoComputo seleccionEc) {
        this.operacionSeleccionada = tipoOperacion;
        switch (tipoOperacion) {
            case REGISTRO:
                lbTipoVentana.setText("Registro de periférico");
                lbInstruccion.setText("Ingrese la información del periférico");
                tfEquipoDeComputoAsignado.setText(seleccionEc.toString());
                this.seleccionEc = seleccionEc;
                inicializarTipoPeriferico();

                break;

            case EDICION:
                lbTipoVentana.setText("Modificar periférico");
                lbInstruccion.setText("Ingrese la nueva información del periférico");
                tfEquipoDeComputoAsignado.setText(seleccionEc.toString());
                tfMarca.setText(seleccion.getMarca());
                tfModelo.setText(seleccion.getModelo());
                tfCodigoDeBarras.setText(seleccion.getCodigoDeBarras());
                cbTipoPeriferico.getSelectionModel().select(seleccion.getTipo());
                this.seleccionPeriferico = seleccion;
                this.seleccionEc = seleccionEc;
                inicializarTipoPeriferico();
                break;

            case CONSULTA:
                lbTipoVentana.setText("Consulta periférico");
                lbInstruccion.setText("Mostrando información del peroférico");
                tfEquipoDeComputoAsignado.setText(seleccionEc.toString());
                tfMarca.setText(seleccion.getMarca());
                tfModelo.setText(seleccion.getModelo());
                tfCodigoDeBarras.setText(seleccion.getCodigoDeBarras());
                cbTipoPeriferico.getSelectionModel().select(seleccion.getTipo());
                tfID.setText(Integer.toString(seleccion.getIdPeriferico()));
                tfID.setVisible(true);
                lbId.setVisible(true);
                
                tfEquipoDeComputoAsignado.setEditable(false);
                tfMarca.setEditable(false);
                tfModelo.setEditable(false);
                tfCodigoDeBarras.setEditable(false);
                cbTipoPeriferico.setEditable(false);
                
                btnGuardar.setVisible(false);
                this.seleccionEc = seleccionEc;
                
                
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

    public void inicializarPerifericos(ArrayList<Periferico> perifericos){
        this.perifericosBD = perifericos;
    }
    
    public TipoPeriferico validarSeleccion() {
        TipoPeriferico tipoPerifericoSeleccionado = cbTipoPeriferico.getSelectionModel().getSelectedItem();
        System.out.println(cbTipoPeriferico);
        return tipoPerifericoSeleccionado;
    }

    private boolean camposVacios() {
        boolean camposVacios = false;
        if (tfMarca.getText().isEmpty()) {
            resaltarTfVacio(tfMarca);
            camposVacios = true;
        } else {
            cambiarColorTf(tfMarca);
        }
        if (tfModelo.getText().isEmpty()) {
            resaltarTfVacio(tfModelo);
            camposVacios = true;
        } else {
            cambiarColorTf(tfModelo);
        }
        if (tfCodigoDeBarras.getText().isEmpty()){
            resaltarTfVacio(tfCodigoDeBarras);
            camposVacios = true;
        }else{
            cambiarColorTf(tfCodigoDeBarras);
        }
        return camposVacios;
    }
    
    private boolean validarNuevoPeriferico(Periferico nuevoPeriferico){
        boolean perifericoValido = true;
        for (Periferico p: perifericosBD){
            if (nuevoPeriferico.getTipo().equals(p.getTipo())){
                perifericoValido = false;
            }
        }
        return perifericoValido;
    }
    
    private Boolean validarEdicionPeriferico(Periferico nuevoPeriferico){
        boolean perifericoValido = true;
        for (Periferico p: perifericosBD){
            if (seleccionPeriferico.getEquipoComputo() == p.getEquipoComputo() && nuevoPeriferico.getTipo().equals(p.getTipo())){
                if(p.getIdPeriferico() == seleccionPeriferico.getIdPeriferico()){
                    perifericoValido = true;
                }else{
                    perifericoValido = false;
                }
                break;
            }
        }
        return perifericoValido;
    }
    
    private void volverAConsultarEquipoComputo() {
        FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLConsultarEquipoComputo.fxml"));
        try {
            Parent vista = accesoControlador.load();

            FXMLConsultarEquipoComputoController controlador = accesoControlador.getController();

            Stage stageActual = (Stage) tfEquipoDeComputoAsignado.getScene().getWindow();
            Scene vistaConsultaEc = new Scene(vista);
            stageActual.setTitle("Consultando periféricos");
            stageActual.setScene(vistaConsultaEc);

            controlador.configurarEquipoSeleccionado(seleccionEc);
            controlador.inicializarDatos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
