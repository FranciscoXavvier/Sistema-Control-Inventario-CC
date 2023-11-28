/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.ProgramaDAO;
import sistemahwsw.pojo.Programa;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author super
 */
public class FXMLFormularioProgramaController implements Initializable {

    @FXML
    private TextField tfEdicion;

    public enum TipoOperacion{
        CONSULTA,
        CONSULTAEQUIPOCOMPUTO,
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
    private Programa programaSeleccionado;
    private ArrayList<Programa> programasRegistrados;
    @FXML
    private Button btnRegistrar;
    
    @FXML
    private void clicRegistrar(ActionEvent event) {
        switch (operacionSeleccionada){
            case REGISTRO:
                if (!validarCamposVacios()){
                Programa nuevoPrograma = new Programa();
                nuevoPrograma.setNombre(tfNombre.getText());
                nuevoPrograma.setIdioma(tfIdioma.getText());
                nuevoPrograma.setEdicion(tfEdicion.getText());
                nuevoPrograma.setVersion(tfVersion.getText());
                
                if(!existeProgramaEnBD(nuevoPrograma)){
                    if (ProgramaDAO.registrarPrograma(nuevoPrograma)){
                        Utilidades.mostrarAlertaSimple("Éxito en la operación", 
                                "El programa ha sido registrado con éxito en la base de datos", 
                                Alert.AlertType.INFORMATION);
                        Utilidades.cambiarVentana("Programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLProgramas.fxml");
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
                    Programa programaEdicion = new Programa();
                    programaEdicion.setNombre(tfNombre.getText());
                    programaEdicion.setIdioma(tfIdioma.getText());
                    programaEdicion.setEdicion(tfEdicion.getText());
                    programaEdicion.setVersion(tfVersion.getText());
                    if(!existeProgramaEnBD(programaEdicion)){
                        if (ProgramaDAO.editarPrograma(programaEdicion, programaSeleccionado)){
                            Utilidades.mostrarAlertaSimple("Éxito en la operación", 
                                    "El programa ha sido editado correctamente en la base de datos", 
                                    Alert.AlertType.INFORMATION);
                            Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLConsultarProgramas.fxml");
                        }else{
                            Utilidades.mostrarAlertaSimple("Error", 
                                    "Se produjo un error al registrar el nuevo programa, por favor intentelo de nuevo", 
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
        switch(operacionSeleccionada){
            case REGISTRO:
                Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLProgramas.fxml");
                break;
            case CONSULTA:
            case EDICION:
                Utilidades.cambiarVentana("Consultando programas", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLConsultarProgramas.fxml");
                break;
            case CONSULTAEQUIPOCOMPUTO:
                Node origen = (Node) event.getSource();
                Stage escenario = (Stage) origen.getScene().getWindow();
                escenario.close();
                break;
            default:
                System.out.println("Operacion no disponible");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void inicializarComponentes(Programa seleccion, TipoOperacion operacion){
        operacionSeleccionada = operacion;
        switch(operacion){
            case CONSULTA:
            case CONSULTAEQUIPOCOMPUTO:
                lbOperacion.setText("Consulta de Aplicación");
                lbInstruccion.setText("Mostrando información correspondiente del programa seleccionado");
                lbID.setVisible(true);
                tfID.setVisible(true);
                tfNombre.setEditable(false);
                tfVersion.setEditable(false);
                tfIdioma.setEditable(false);
                tfEdicion.setEditable(false);
                inicializarValores(seleccion);
                break;
            case EDICION:
                lbOperacion.setText("Edición de aplicación");
                lbInstruccion.setText("Por favor cambie la información necesaria para el programa");
                lbID.setVisible(true);
                tfID.setVisible(true);
                btnRegistrar.setVisible(true);
                btnRegistrar.setText("Editar");
                programaSeleccionado = seleccion;
                inicializarValores(seleccion);
                break;
            case REGISTRO:
                lbOperacion.setText("Registro de aplicación");
                lbInstruccion.setText("Por favor rellene todos los campos con la información del nuevo programa a registrar");
                programasRegistrados = ProgramaDAO.obtenerProgramas();
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
        if(tfEdicion.getText().isEmpty()){
            resaltarTfVacio(tfEdicion);
            camposVacios = true;
        }else{
            cambiarColorTf(tfEdicion);
        }
        return camposVacios;
    }
    
    private void resaltarTfVacio(TextField campoVacio){
        campoVacio.setStyle("-fx-border-color: FF0000;");
    }
    
    private void cambiarColorTf(TextField tf){
        tf.setStyle("-fx-border-color: CFCFCF;");
    }
    
    public void inicializarValores(Programa seleccion){
        tfID.setText(String.valueOf(seleccion.getIdPrograma()));
        tfNombre.setText(seleccion.getNombre());
        tfVersion.setText(seleccion.getVersion());
        tfIdioma.setText(seleccion.getIdioma());
        tfEdicion.setText(seleccion.getEdicion());
    }
    
    public boolean existeProgramaEnBD(Programa app){
        boolean existe = false;
        for (Programa ProgramaBD : programasRegistrados){
            if(ProgramaBD.equals(app)){
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    public void setListaProgramasBD(ArrayList<Programa> programas){
        this.programasRegistrados = programas;
    }
}
