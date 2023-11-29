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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.dao.EquipoComputoDAO;
import modelo.dao.PerifericoDAO;
import sistemahwsw.pojo.EquipoComputo;
import sistemahwsw.pojo.Periferico;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author aaron
 */
public class FXMLConsultarEquipoComputoController implements Initializable {

    private EquipoComputo seleccionEc;

    @FXML
    private Label lbEquipoDeComputo;
    @FXML
    private Label lbProcesador;
    @FXML
    private Label lbTarjetaMadre;
    @FXML
    private Label lbMemoria;
    @FXML
    private Label lbCodigoBarras;
    @FXML
    private Label lbLectorOptico;
    @FXML
    private Label lbColumna;
    @FXML
    private TableView<Periferico> tvPerifericos;
    @FXML
    private TableColumn<?, ?> colCodigoDeBarras;
    @FXML
    private TableColumn<?, ?> colTipoPeriferico;
    @FXML
    private TableColumn<?, ?> colMarca;
    private ArrayList<Periferico> perifericosRespuesta;
    private ObservableList<Periferico> perifericosBD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
        FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLFormularioPeriferico.fxml"));
        try {
            Parent vista = accesoControlador.load();
            FXMLFormularioPerifericoController controlador = accesoControlador.getController();
            
            Stage stageActual = (Stage) tvPerifericos.getScene().getWindow();
            Scene vistaRegistro = new Scene(vista);
            stageActual.setTitle("Registrar periférico");
            
            controlador.inicializarDatos(null, FXMLFormularioPerifericoController.TipoOperacion.REGISTRO, seleccionEc);
            controlador.inicializarPerifericos(perifericosRespuesta);
            stageActual.setScene(vistaRegistro);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void clicVolver(ActionEvent event) {
        FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLTipoConsultaEquipoComputo.fxml"));
        try {
            Parent vista = accesoControlador.load();

            FXMLTipoConsultaEquipoComputoController controlador = accesoControlador.getController();
            controlador.asignarComputadora(seleccionEc);

            Node origen = (Node) event.getSource();
            Stage stageActual = (Stage) origen.getScene().getWindow();
            Scene vistaTipoConsulta = new Scene(vista);
            
            stageActual.setTitle("Seleccionar tipo de consulta");
            stageActual.setScene(vistaTipoConsulta);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void inicializarDatos() {
        configurarTabla();
        cargarDatosTabla();
        cargarDatosLabel();
    }

    private void agregarBotones() {
        TableColumn<Periferico, String> colBotones = new TableColumn("Opciones");
        colBotones.setPrefWidth(112.5);
        Callback<TableColumn<Periferico, String>, TableCell<Periferico, String>> cellFactory = new Callback<TableColumn<Periferico, String>, TableCell<Periferico, String>>() {
            @Override
            public TableCell<Periferico, String> call(final TableColumn<Periferico, String> param) {
                final TableCell<Periferico, String> cell = new TableCell<Periferico, String>() {

                    private final Button btnConsultar = new Button("Consultar");
                    private final Button btnModificar = new Button("Modificar");
                    private final Button btnEliminar = new Button("Eliminar");

                    {
                        btnConsultar.setStyle("-fx-background-color: #00BCD4;"
                                + "-fx-cursor: hand;"
                                + "-fx-text-fill: white;");
                        btnModificar.setStyle("-fx-background-color: #FF9400;"
                                + "-fx-cursor: hand;"
                                + "-fx-text-fill: white;");
                        btnEliminar.setStyle("-fx-background-color: #FF0000;"
                                + "-fx-cursor: hand;"
                                + "-fx-text-fill: white;");
                        btnConsultar.setPrefWidth(112.5);
                        btnModificar.setPrefWidth(112.5);
                        btnEliminar.setPrefWidth(112.5);
                        btnConsultar.setOnAction((ActionEvent event) -> {
                            Periferico seleccion = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLFormularioPeriferico.fxml"));
                                Parent vista = accesoControlador.load();
                                FXMLFormularioPerifericoController ventanaConsulta = accesoControlador.getController();

                                ventanaConsulta.inicializarDatos(seleccion, FXMLFormularioPerifericoController.TipoOperacion.CONSULTA, seleccionEc);

                                Stage escenarioActual = (Stage) tvPerifericos.getScene().getWindow();
                                Scene escenarioConsulta = new Scene(vista);
                                escenarioActual.setTitle("Consulta periférico");
                                
                                escenarioActual.setScene(escenarioConsulta);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                        btnModificar.setOnAction((ActionEvent event) -> {
                            Periferico seleccion = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLFormularioPeriferico.fxml"));
                                Parent vista = accesoControlador.load();

                                FXMLFormularioPerifericoController vistaEdicion = accesoControlador.getController();
                                Scene escena = new Scene(vista);
                                Stage escenario = (Stage) tvPerifericos.getScene().getWindow();
                                escenario.setTitle("Modificar periférico");

                                vistaEdicion.inicializarDatos(seleccion, FXMLFormularioPerifericoController.TipoOperacion.EDICION, seleccionEc);
                                vistaEdicion.inicializarPerifericos(perifericosRespuesta);
                                escenario.setScene(escena);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        btnEliminar.setOnAction((ActionEvent event) -> {
                            Periferico seleccion = getTableView().getItems().get(getIndex());
                            boolean confirmarEliminacion = Utilidades.mostrarDialogoConfirmacion("Ventana de confirmación",
                                    "¿Está seguro que desea eliminar el periférico?");
                            if (confirmarEliminacion) {
                                if (PerifericoDAO.eliminarPerifericoPorId(seleccion.getIdPeriferico())) {
                                    Utilidades.mostrarAlertaSimple("Éxito en la operación",
                                            "periférico eliminado de manera exitosa",
                                            Alert.AlertType.INFORMATION);
                                    cargarDatosTabla();
                                } else {
                                    Utilidades.mostrarAlertaSimple("Error en la eliminación del periferico",
                                            "Hubo un error al momento de eliminar el periférico, por favor vuelva a intentarlo más tarde",
                                            Alert.AlertType.ERROR);
                                }
                            }
                        });

                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            VBox botones = new VBox();
                            botones.getChildren().add(btnConsultar);
                            botones.getChildren().add(btnModificar);
                            botones.getChildren().add(btnEliminar);
                            setGraphic(botones);
                        }
                    }
                };
                return cell;
            }
        };

        colBotones.setCellFactory(cellFactory);

        tvPerifericos.getColumns().add(colBotones);

    }

    private void cargarDatosTabla() {
        perifericosBD = FXCollections.observableArrayList();
        perifericosRespuesta = PerifericoDAO.recuperarPerifericosPorIDEC(seleccionEc.getIdEquipo());
        for (Periferico p : perifericosRespuesta) {
            perifericosBD.add(p);
        }
        tvPerifericos.setItems(perifericosBD);
    }

    public void configurarEquipoSeleccionado(EquipoComputo seleccion) {
        this.seleccionEc = seleccion;
    }

    private void configurarTabla() {
        colCodigoDeBarras.setCellValueFactory(new PropertyValueFactory("codigoDeBarras"));
        colTipoPeriferico.setCellValueFactory(new PropertyValueFactory("tipo"));
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        agregarBotones();
    }

    private void cargarDatosLabel() {
        lbEquipoDeComputo.setText("Equipo de cómputo: " + Integer.toString(seleccionEc.getIdEquipo()));
        lbProcesador.setText("Procesador: " + seleccionEc.getProcesador());
        lbTarjetaMadre.setText("Tarjeta madre: " + seleccionEc.getTarjetaMadre());
        lbMemoria.setText("Memoria RAM: " + seleccionEc.getMemoriaRam());
        lbCodigoBarras.setText("Código de barras: " + seleccionEc.getCodigoDeBarras());
        lbLectorOptico.setText("Lector óptico: " + seleccionEc.getLectorOptico());
        lbColumna.setText("Columna: " + seleccionEc.getColumna() + " Fila: " + seleccionEc.getFila());
    }
}
