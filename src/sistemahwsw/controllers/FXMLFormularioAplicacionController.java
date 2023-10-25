/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.dao.AplicacionDAO;
import sistemahwsw.pojo.Aplicacion;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author super
 */
public class FXMLFormularioAplicacionController implements Initializable {

    public enum TipoOperacion{
        CONSULTA,
        EDICION,
        REGISTRO;
    }
    
    @FXML
    private Label lbOperacion;
    @FXML
    private Label lbInstruccion;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfVersion;
    @FXML
    private TextField tfID;
    @FXML
    private Label lbID;
    @FXML
    private TextField tfIdioma;

    private TipoOperacion operacionSeleccionada;
    private Aplicacion aplicacionSeleccionada;
    private ArrayList<Aplicacion> aplicacionesRegistradas;
    @FXML
    private Button btnRegistrar;
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        switch (operacionSeleccionada){
            case REGISTRO:
                if (!validarCamposVacios()){
                Aplicacion nuevaAplicacion = new Aplicacion();
                nuevaAplicacion.setNombre(tfNombre.getText());
                nuevaAplicacion.setIdioma(tfIdioma.getText());
                nuevaAplicacion.setVersion(tfVersion.getText());
                if(!existeAplicacionEnBD(nuevaAplicacion)){
                    if (AplicacionDAO.registrarAplicacion(nuevaAplicacion)){
                        Utilidades.mostrarAlertaSimple("Éxito en la operación", 
                                "El programa ha sido registrado con éxito en la base de datos", 
                                Alert.AlertType.INFORMATION);
                        Utilidades.cambiarVentana("Aplicaciones", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLAplicaciones.fxml");
                    }else{
                        Utilidades.mostrarAlertaSimple("Error", 
                                "Se produjo un error al registrar la nueva aplicación, por favor intentelo de nuevo", 
                                Alert.AlertType.ERROR);
                    }
                }else{
                    Utilidades.mostrarAlertaSimple("Error", 
                            "El programa ya se encuentra registrado en la base de datos, "
                                    + "por favor introduzca de nuevo la información correspondiente al nuevo programa", 
                            Alert.AlertType.ERROR);
                }
                }else{
                    Utilidades.mostrarAlertaSimple("Error", 
                            "Por favor rellene todos los campos y vuelva a intentarlo", 
                            Alert.AlertType.ERROR);
                }
                break;
            case EDICION:
                if (!validarCamposVacios()){
                Aplicacion aplicacionEdicion = new Aplicacion();
                aplicacionEdicion.setNombre(tfNombre.getText());
                aplicacionEdicion.setIdioma(tfIdioma.getText());
                aplicacionEdicion.setVersion(tfVersion.getText());
                if(!existeAplicacionEnBD(aplicacionEdicion)){
                    if (AplicacionDAO.editarAplicacion(aplicacionEdicion, aplicacionSeleccionada)){
                        Utilidades.mostrarAlertaSimple("Éxito en la operación", 
                                "El programa ha sido editado correctamente en la base de datos", 
                                Alert.AlertType.INFORMATION);
                        Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLConsultarAplicaciones.fxml");
                    }else{
                        Utilidades.mostrarAlertaSimple("Error", 
                                "Se produjo un error al registrar la nueva aplicación, por favor intentelo de nuevo", 
                                Alert.AlertType.ERROR);
                    }
                }else{
                    Utilidades.mostrarAlertaSimple("Error", 
                            "El programa ya se encuentra registrado en la base de datos, "
                                    + "por favor introduzca de nuevo la información correspondiente al nuevo programa", 
                            Alert.AlertType.ERROR);
                }
                }else{
                    Utilidades.mostrarAlertaSimple("Error", 
                            "Por favor rellene todos los campos y vuelva a intentarlo", 
                            Alert.AlertType.ERROR);
                }
                break;
            default:
                System.out.println("Operacion no disponible");
        }
    }

    @FXML
    private void clicVolver(ActionEvent event) {
        if (operacionSeleccionada.equals(TipoOperacion.REGISTRO)){
            Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLAplicaciones.fxml");
        }else{
            Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLConsultarAplicaciones.fxml");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void inicializarComponentes(Aplicacion seleccion, TipoOperacion operacion){
        operacionSeleccionada = operacion;
        switch(operacion){
            case CONSULTA:
                lbOperacion.setText("Consulta de Aplicación");
                lbInstruccion.setText("Mostrando información correspondiente de la aplicación seleccionada");
                lbID.setVisible(true);
                tfID.setVisible(true);
                tfNombre.setEditable(false);
                tfVersion.setEditable(false);
                tfIdioma.setEditable(false);
                inicializarValores(seleccion);
                break;
            case EDICION:
                lbOperacion.setText("Edición de aplicación");
                lbInstruccion.setText("Por favor cambie la información necesaria para la aplicación");
                lbID.setVisible(true);
                tfID.setVisible(true);
                btnRegistrar.setVisible(true);
                btnRegistrar.setText("Editar");
                aplicacionSeleccionada = seleccion;
                inicializarValores(seleccion);
                break;
            case REGISTRO:
                lbOperacion.setText("Registro de aplicación");
                lbInstruccion.setText("Por favor rellene todos los campos con la información nueva de la aplicación a registrar");
                aplicacionesRegistradas = AplicacionDAO.obtenerAplicaciones();
                btnRegistrar.setVisible(true);
                break;
            default:
                System.out.println("No se puede desplegar el formulario");
        }
    }
    
    public boolean validarCamposVacios(){
        boolean camposVacios = false;
        if (tfNombre.getText().isEmpty()){
            resaltarTfVacio(tfNombre);
            camposVacios = true;
        }else{
            cambiarColorTf(tfNombre);
        }
        if(tfVersion.getText().isEmpty()){
            resaltarTfVacio(tfVersion);
            camposVacios = true;
        }else{
            cambiarColorTf(tfVersion);
        }
        if(tfIdioma.getText().isEmpty()){
            resaltarTfVacio(tfIdioma);
            camposVacios = true;
        }else{
            cambiarColorTf(tfIdioma);
        }
        return camposVacios;
    }
    
    private void resaltarTfVacio(TextField campoVacio){
        campoVacio.setStyle("-fx-border-color: FF0000;");
    }
    
    private void cambiarColorTf(TextField tf){
        tf.setStyle("-fx-border-color: CFCFCF;");
    }
    
    public void inicializarValores(Aplicacion seleccion){
        tfID.setText(String.valueOf(seleccion.getIdAplicacion()));
        tfNombre.setText(seleccion.getNombre());
        tfVersion.setText(seleccion.getVersion());
        tfIdioma.setText(seleccion.getIdioma());
    }
    
    public boolean existeAplicacionEnBD(Aplicacion app){
        boolean existe = false;
        for (Aplicacion aplicacionBD : aplicacionesRegistradas){
            if(aplicacionBD.equals(app)){
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public void setListaAplicacionesBD(ArrayList<Aplicacion> aplicaciones){
        this.aplicacionesRegistradas = aplicaciones;
    }
}
