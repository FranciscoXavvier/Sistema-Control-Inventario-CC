/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
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

    @FXML
    private Button btn_registro;

    public static enum TipoOperacion {
        CONSULTA,
        EDICION,
        REGISTRO;
    }

    private int idCC;
    private ArrayList<SistemaOperativo> osRespuesta;
    private ObservableList<SistemaOperativo> osBd;
    private TipoOperacion operacionSeleccionada;
    private EquipoComputo equipoSeleccionado;

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
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {

        switch (operacionSeleccionada) {
            case REGISTRO:
                SistemaOperativo sistemaOperativo = validarSeleccion();
                if (!camposVacios() && sistemaOperativo != null) {
                    String procesador = tf_procesador.getText();
                    String tarjeta_madre = tf_tarjetaMadre.getText();
                    String memoria_ram = tf_memoriaRam.getText();
                    String almacenamiento = tf_almacenamiento.getText();
                    String lector_optico = tf_lectorOptico.getText();
                    String codigo_de_barras = tf_codigoBarras.getText();
                    String fila = tf_fila.getText();
                    String columna = tf_columna.getText();
                    EquipoComputo equipoComputo = new EquipoComputo();
                    equipoComputo.setSistemaOperativoInstalado(sistemaOperativo);
                    equipoComputo.setIdCentroComputo(idCC);
                    equipoComputo.setProcesador(procesador);
                    equipoComputo.setTarjetaMadre(tarjeta_madre);
                    equipoComputo.setMemoriaRam(memoria_ram);
                    equipoComputo.setAlmacenamiento(almacenamiento);
                    equipoComputo.setLectorOptico(lector_optico);
                    equipoComputo.setCodigoDeBarras(codigo_de_barras);
                    equipoComputo.setFila(fila);//agregar validacion de fila y columna
                    equipoComputo.setColumna(columna);
                    if (Utilidades.mostrarDialogoConfirmacion("Confirmación de registro", "Está seguro de querer registrar este equipo de cómputo")) {
                        EquipoComputoDAO.registrar(equipoComputo);
                        Utilidades.mostrarAlertaSimple("Registro exitoso",
                                "El equipo de cómputo ha sido registrado correctamente",
                                Alert.AlertType.INFORMATION);
                        irAMenuEquipoComputo();
                    }
                } else {
                    Utilidades.mostrarAlertaSimple("Error",
                            "Por favor rellene todos los campos y vuelva a intentarlo",
                            Alert.AlertType.ERROR);
                }
                break;
            case EDICION:
                SistemaOperativo nuevoOS = validarSeleccion();
                if (!camposVacios() && nuevoOS != null) {
                    String procesador = tf_procesador.getText();
                    String tarjeta_madre = tf_tarjetaMadre.getText();
                    String memoria_ram = tf_memoriaRam.getText();
                    String almacenamiento = tf_almacenamiento.getText();
                    String lector_optico = tf_lectorOptico.getText();
                    String codigo_de_barras = tf_codigoBarras.getText();
                    String fila = tf_fila.getText();
                    String columna = tf_columna.getText();
                    EquipoComputo equipoComputo = new EquipoComputo();
                    equipoComputo.setSistemaOperativoInstalado(nuevoOS);
                    equipoComputo.setIdCentroComputo(idCC);
                    equipoComputo.setProcesador(procesador);
                    equipoComputo.setTarjetaMadre(tarjeta_madre);
                    equipoComputo.setMemoriaRam(memoria_ram);
                    equipoComputo.setAlmacenamiento(almacenamiento);
                    equipoComputo.setLectorOptico(lector_optico);
                    equipoComputo.setCodigoDeBarras(codigo_de_barras);
                    equipoComputo.setFila(fila);//agregar validacion de fila y columna
                    equipoComputo.setColumna(columna);
                    if (Utilidades.mostrarDialogoConfirmacion("Confirmación de edición", "Está seguro de querer editar este equipo de cómputo")) {
                        boolean seEdito = EquipoComputoDAO.editarEc(equipoComputo, equipoSeleccionado);
                        if(seEdito){
                            Utilidades.mostrarAlertaSimple("Modificación exitosa",
                                    "El equipo de cómputo ha sido modificado correctamente",
                                    Alert.AlertType.INFORMATION);
                            irAMenuEquipoComputo();
                        }else{
                            Utilidades.mostrarAlertaSimple("Error al modificar equipo de cómputo", 
                                    "El equipo de cómputo no ha sido modificado", 
                                    Alert.AlertType.ERROR);
                        }
                    }
                } else {
                    Utilidades.mostrarAlertaSimple("Error",
                            "Por favor rellene todos los campos y vuelva a intentarlo",
                            Alert.AlertType.ERROR);
                }
                break;
            default:
                System.out.println("Error de controlador");
        }

    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        irAMenuEquipoComputo();
    }

    @FXML
    private void permitirSoloLetras(KeyEvent event) {
        String entrada = event.getCharacter();
        if (!"qwertyuiopasdfghjklzxcvbnm".contains(entrada)) {
            event.consume();
        }
    }

    @FXML
    private void permitirSoloNumeros(KeyEvent event) {
        String entrada = event.getCharacter();
        if (!"0123456789".contains(entrada)) {
            event.consume();
        }
    }

    public void inicializarIdCC(int idCC) {
        this.idCC = idCC;
    }

    public void setEquipoComputo(EquipoComputo seleccion) {
        btn_registro.setText("Editar");
        this.equipoSeleccionado = seleccion;
    }

    public void inicializarEquipoComputo() {
        tf_procesador.setText(equipoSeleccionado.getProcesador());
        tf_tarjetaMadre.setText(equipoSeleccionado.getTarjetaMadre());
        tf_memoriaRam.setText(equipoSeleccionado.getMemoriaRam());
        tf_almacenamiento.setText(equipoSeleccionado.getAlmacenamiento());
        tf_lectorOptico.setText(equipoSeleccionado.getLectorOptico());
        tf_codigoBarras.setText(equipoSeleccionado.getCodigoDeBarras());
        tf_fila.setText(equipoSeleccionado.getFila());
        tf_columna.setText(equipoSeleccionado.getColumna());
        cbSistemaOperativo.getSelectionModel().select(equipoSeleccionado.getSistemaOperativoInstalado());
    }

    private void irAMenuEquipoComputo() {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLMenuEquipoComputo.fxml"));
            Parent vista = accesoControlador.load();

            FXMLMenuEquipoComputoController controlador = accesoControlador.getController();

            controlador.inicializarIdCC(idCC);
            controlador.configurarTabla();
            controlador.cargarDatosTabla();

            Stage stageActual = (Stage) tf_almacenamiento.getScene().getWindow();
            Scene ventanaMenuEC = new Scene(vista);

            stageActual.setScene(ventanaMenuEC);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void inicializarSistemasOperativos() {
        osRespuesta = SistemaOperativoDAO.obtenerSistemasOperativos();
        osBd = FXCollections.observableList(osRespuesta);
        cbSistemaOperativo.setItems(osBd);
    }

    public SistemaOperativo validarSeleccion() {
        SistemaOperativo osSeleccionado = cbSistemaOperativo.getValue();
        System.out.println(osSeleccionado);
        return osSeleccionado;
    }

    private boolean camposVacios() {
        boolean camposVacios = false;
        if (tf_procesador.getText().isEmpty()) {
            resaltarTfVacio(tf_procesador);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_procesador);
        }
        if (tf_tarjetaMadre.getText().isEmpty()) {
            resaltarTfVacio(tf_tarjetaMadre);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_tarjetaMadre);
        }
        if (tf_memoriaRam.getText().isEmpty()) {
            resaltarTfVacio(tf_memoriaRam);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_memoriaRam);
        }
        if (tf_almacenamiento.getText().isEmpty()) {
            resaltarTfVacio(tf_almacenamiento);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_almacenamiento);
        }
        if (tf_lectorOptico.getText().isEmpty()) {
            resaltarTfVacio(tf_lectorOptico);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_lectorOptico);
        }
        if (tf_codigoBarras.getText().isEmpty()) {
            resaltarTfVacio(tf_codigoBarras);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_codigoBarras);
        }
        if (tf_fila.getText().isEmpty()) {
            resaltarTfVacio(tf_fila);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_fila);
        }
        if (tf_columna.getText().isEmpty()) {
            resaltarTfVacio(tf_columna);
            camposVacios = true;
        } else {
            cambiarColorTf(tf_columna);
        }
        return camposVacios;
    }

    private void resaltarTfVacio(TextField campoVacio) {
        campoVacio.setStyle("-fx-border-color: FF0000;");
    }

    private void cambiarColorTf(TextField tf) {
        tf.setStyle("-fx-border-color: CFCFCF;");
    }

    public void setOperacion(TipoOperacion tipoOperacion) {
        this.operacionSeleccionada = tipoOperacion;
    }

    public void limitarTexto() {
        listenerLimitarTexto(tf_fila);
        listenerLimitarTexto(tf_columna);
    }

    private void listenerLimitarTexto(TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > 1) {
                    String s = tf.getText().substring(0, 1);
                    tf.setText(s);
                }
            }
        });
    }
}
