/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.dao.EquipoComputoDAO;
import modelo.dao.SistemaOperativoDAO;
import sistemahwsw.pojo.EquipoComputo;
import sistemahwsw.pojo.SistemaOperativo;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author ABF26
 */
public class FXMLRegistrarEquipoComputoController implements Initializable {

    private int idCC;
    private ArrayList<SistemaOperativo> osRespuesta;
    private ObservableList<SistemaOperativo> osBd;
    private TipoOperacion operacionSeleccionada;


    
    public static enum TipoOperacion{
        CONSULTA,
        EDICION,
        REGISTRO;
    }
    
    @FXML
    private TextField tf_procesador;
    @FXML
    private TextField tf_tarjetaMadre;
    @FXML
    private TextField tf_memoriaRam;
    @FXML
    private TextField tf_almacenamiento;
    @FXML
    private TextField tf_lectorOptico;
    @FXML
    private TextField tf_codigoBarras;
    @FXML
    private TextField tf_fila;
    @FXML
    private TextField tf_columna;
    @FXML
    private ComboBox<SistemaOperativo> cbSistemaOperativo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clicRegistrar(ActionEvent event) {
        
        switch (operacionSeleccionada){
            case REGISTRO:
                String procesador = tf_procesador.getText();
                String tarjeta_madre = tf_tarjetaMadre.getText();
                String memoria_ram = tf_memoriaRam.getText();
                String almacenamiento = tf_almacenamiento.getText();
                String lector_optico = tf_lectorOptico.getText();
                String codigo_de_barras = tf_codigoBarras.getText();
                String fila = tf_fila.getText();
                String columna = tf_columna.getText();
                SistemaOperativo sistemaOperativo = validarSeleccion();

                if(!camposVacios() && sistemaOperativo != null){
                    EquipoComputo equipoComputo = new EquipoComputo();
                    equipoComputo.setSistemaOperativoInstalado(sistemaOperativo);
                    equipoComputo.setIdCentroComputo(idCC);
                    equipoComputo.setProcesador(procesador);
                    equipoComputo.setTarjetaMadre(tarjeta_madre);
                    equipoComputo.setMemoriaRam(memoria_ram);
                    equipoComputo.setAlmacenamiento(almacenamiento);
                    equipoComputo.setLectorOptico(lector_optico);
                    equipoComputo.setCodigoDeBarras(codigo_de_barras);
                    equipoComputo.setFila(fila);
                    equipoComputo.setColumna(columna);        
                    if (Utilidades.mostrarDialogoConfirmacion("Confirmación de registro", "Está seguro de queres registrar este centro de cómputo")){
                        EquipoComputoDAO.registrar(equipoComputo);
                        Utilidades.mostrarAlertaSimple("Registro exitoso", 
                                "El equipo de cómputo ha sido registrado correctamente", 
                                Alert.AlertType.INFORMATION);
                    }
                }else{
                    Utilidades.mostrarAlertaSimple("Error", 
                            "Por favor rellene todos los campos y vuelva a intentarlo", 
                            Alert.AlertType.ERROR);
                }
                break;
            case EDICION:
                break;
            case CONSULTA:
                break;
        }
        
        
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        
    }

    public void inicializarIdCC(int idCC) {
        this.idCC=idCC;
    }
    
    public void inicializarSistemasOperativos(){
        osRespuesta = SistemaOperativoDAO.obtenerProgramaes();
        osBd = FXCollections.observableList(osRespuesta);
        cbSistemaOperativo.setItems(osBd);
    }
    
    public SistemaOperativo validarSeleccion(){
        SistemaOperativo osSeleccionado = cbSistemaOperativo.getValue();
        System.out.println(osSeleccionado);
        return osSeleccionado;
    }
    
    private boolean camposVacios() {
        boolean camposVacios = false;
        if(tf_procesador.getText().isEmpty()){
            resaltarTfVacio(tf_procesador);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_procesador);
        }
        if(tf_tarjetaMadre.getText().isEmpty()){
            resaltarTfVacio(tf_tarjetaMadre);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_tarjetaMadre);
        }
        if(tf_memoriaRam.getText().isEmpty()){
            resaltarTfVacio(tf_memoriaRam);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_memoriaRam);
        }
        if(tf_almacenamiento.getText().isEmpty()){
            resaltarTfVacio(tf_almacenamiento);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_almacenamiento);
        }
        if (tf_lectorOptico.getText().isEmpty()){
            resaltarTfVacio(tf_lectorOptico);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_lectorOptico);
        }
        if (tf_codigoBarras.getText().isEmpty()){
            resaltarTfVacio(tf_codigoBarras);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_codigoBarras);
        }
        if (tf_fila.getText().isEmpty()){
            resaltarTfVacio(tf_fila);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_fila);
        }
        if (tf_columna.getText().isEmpty()){
            resaltarTfVacio(tf_columna);
            camposVacios = true;
        }else{
            cambiarColorTf(tf_columna);
        }
        return camposVacios;
    }
    
    private void resaltarTfVacio(TextField campoVacio){
        campoVacio.setStyle("-fx-border-color: FF0000;");
    }
    
    private void cambiarColorTf(TextField tf){
        tf.setStyle("-fx-border-color: CFCFCF;");
    }
    
    void setOperacion(TipoOperacion tipoOperacion) {
        this.operacionSeleccionada = tipoOperacion;
    }
    
}
