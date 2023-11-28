/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.dao.ProgramaDAO;
import sistemahwsw.pojo.EquipoComputo;
import sistemahwsw.pojo.Programa;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author aaron
 */
public class FXMLConsultarProgramasEquipoComputoController implements Initializable {

    private EquipoComputo equipoSeleccionado;
    private boolean esEdicion = false;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colEdicion;
    @FXML
    private TableView<Programa> tvProgramas;

    private ObservableList<Programa> programasAsignados;
    private ObservableList<Programa> programasBD;
    private ArrayList<Programa> resultadoProgramasAsignados;
    private ArrayList<Programa> resultadoProgramasBD;
    @FXML
    private TableColumn<Programa, String> colOpciones;
    @FXML
    private Button btnAsignar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setEquipoComputo(EquipoComputo equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }

    public void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colEdicion.setCellValueFactory(new PropertyValueFactory("edicion"));
        recuperarProgramasBD();
        agregarBotones();
    }

    public void cargarDatosTabla() {
        if (!esEdicion) {
            resultadoProgramasAsignados = ProgramaDAO.obtenerProgramasInstalados(equipoSeleccionado.getIdEquipo());
            programasAsignados = FXCollections.observableArrayList();
            for (Programa p : resultadoProgramasAsignados) {
                programasAsignados.add(p);
            }
            tvProgramas.setItems(programasAsignados);

        } else {
            tvProgramas.setItems(programasBD);
        }

    }

    private void recuperarProgramasBD() {
        resultadoProgramasBD = ProgramaDAO.obtenerProgramas();
        programasBD = FXCollections.observableArrayList(resultadoProgramasBD);
    }

    private void agregarBotones() {
        Callback<TableColumn<Programa, String>, TableCell<Programa, String>> cellFactory = new Callback<TableColumn<Programa, String>, TableCell<Programa, String>>() {
            @Override
            public TableCell<Programa, String> call(final TableColumn<Programa, String> param) {
                final TableCell<Programa, String> cell = new TableCell<Programa, String>() {

                    private final Button btnConsultar = new Button("Consultar");

                    {
                        btnConsultar.setStyle("-fx-background-color: #00BCD4;"
                                + "-fx-cursor: hand;"
                                + "-fx-text-fill: white;");
                        btnConsultar.setPrefWidth(150);
                        btnConsultar.setOnAction((ActionEvent event) -> {
                            Programa seleccion = getTableView().getItems().get(getIndex());
                            irAFormularioPrograma(seleccion, FXMLFormularioProgramaController.TipoOperacion.CONSULTAEQUIPOCOMPUTO);
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
                            setGraphic(botones);
                        }
                    }
                };
                return cell;
            }
        };

        colOpciones.setCellFactory(cellFactory);

    }

    private void irAFormularioPrograma(Programa app, FXMLFormularioProgramaController.TipoOperacion operacion) {
        try {
            FXMLLoader accesoControlador
                    = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLFormularioPrograma.fxml"));
            Parent vista = accesoControlador.load();

            FXMLFormularioProgramaController formulario
                    = accesoControlador.getController();
            Scene escenaFormulario = new Scene(vista);
            Stage escenario = new Stage();
            escenario.setScene(escenaFormulario);
            formulario.inicializarComponentes(app, operacion);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la pantalla de formulario",
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicAsignarProgramas(ActionEvent event) {
        if (esEdicion) {
            btnAsignar.setText("Asignar aplicaciones");
            esEdicion = false;
            cargarDatosTabla();
            agregarBotones();
        } else {
            esEdicion = true;
            cargarDatosTabla();
            agregarBotonesEdicion();
            
            btnAsignar.setText("Actualizar aplicaciones");
        }
    }

    private void agregarBotonesEdicion() {
        colOpciones.setVisible(false);
    }

    @FXML
    private void clicVolver(ActionEvent event) {
    }

}
