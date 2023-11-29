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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import modelo.dao.CentroComputoDAO;

import sistemahwsw.pojo.CentroComputo;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MenuCentroComputoController implements Initializable {

      @FXML private javafx.scene.control.Button boton_registrar;
    @FXML
    private TableView<CentroComputo> tvCentroComputo;
    @FXML
    private TableColumn<?, ?> colFoto;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colNombre;
    private ArrayList<CentroComputo> ccRespuesta;
    
    private ObservableList<CentroComputo> ccBd;
    /** 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosTabla();
    }    
    
    @FXML
    public void registrar(ActionEvent event){
        try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLRegistrarCentroComputo.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));  
                stage.show();
                //cerrar ventana actual
                Node origen = (Node) event.getSource();
                Stage stageActual = (Stage) origen.getScene().getWindow();
                stageActual.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory("idCentroComputo"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombreCentroComputo"));
        agregarBotones();
    }

    public void cargarDatosTabla() {
        ccBd = FXCollections.observableArrayList();
        ccRespuesta = CentroComputoDAO.obtenerCentroComputo();
        for (CentroComputo cc : ccRespuesta){
            ccBd.add(cc);
        }
        tvCentroComputo.setItems(ccBd);
    }

    private void agregarBotones() {
        TableColumn<CentroComputo,String> colBotones = new TableColumn("Opciones");
        colBotones.setPrefWidth(175.19998168945312);
        Callback<TableColumn<CentroComputo, String>, TableCell<CentroComputo, String>> cellFactory = new Callback<TableColumn<CentroComputo, String>, TableCell<CentroComputo, String>>() {
            @Override
            public TableCell<CentroComputo, String> call(final TableColumn<CentroComputo, String> param) {
                final TableCell<CentroComputo, String> cell = new TableCell<CentroComputo, String>() {

                    private final Button btnConsultar = new Button("Consultar");
                    private final Button btnModificar = new Button("Modificar");
                    private final Button btnEliminar = new Button ("Eliminar");

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
                        btnConsultar.setPrefWidth(175.19998168945312);
                        btnModificar.setPrefWidth(175.19998168945312);
                        btnEliminar.setPrefWidth(175.19998168945312);
                        btnConsultar.setOnAction((ActionEvent event) -> {
                           CentroComputo seleccion = getTableView().getItems().get(getIndex());
                            irAFormularioCC(seleccion, RegistrarCentroComputoController.TipoOperacion.CONSULTA);
                        });
                        btnModificar.setOnAction((ActionEvent event) ->{
                             CentroComputo seleccion = getTableView().getItems().get(getIndex());
                            irAFormularioCC(seleccion, RegistrarCentroComputoController.TipoOperacion.EDICION);
                        });
                        btnEliminar.setOnAction((ActionEvent event) ->{
                            CentroComputo seleccion = getTableView().getItems().get(getIndex());
                            boolean confirmarEliminacion = Utilidades.mostrarDialogoConfirmacion("Confirmación de eliminación de aplicación", 
                                    "El programa será eliminado y cualquier equipo de cómputo que se encuentre con la "
                                            + "aplicación registrada será eliminada ¿Desea continuar con la acción?");
                            if (confirmarEliminacion){
                                if(CentroComputoDAO.eliminarCC(seleccion.getIdCentroComputo())){
                                    Utilidades.mostrarAlertaSimple("Éxito en la operación", 
                                            "Aplicación eliminado de manera exitosa", 
                                            Alert.AlertType.INFORMATION);
                                    cargarDatosTabla();
                                }else{
                                    Utilidades.mostrarAlertaSimple("Error en la eliminación del programa", 
                                            "Hubo un error al momento de eliminar la aplicación, por favor vuelva a intentarlo más tarde", 
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

        tvCentroComputo.getColumns().add(colBotones);

    }

    @FXML
    private void regresar(ActionEvent event) {
        Node origen = (Node) event.getSource();
        Stage escenarioActual = (Stage) origen.getScene().getWindow();
        escenarioActual.close();
    }
    
    
     private void irAFormularioCC(CentroComputo app, RegistrarCentroComputoController.TipoOperacion operacion){
        try {
            FXMLLoader accesoControlador = 
                    new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLRegistrarCentroComputo.fxml"));
            Parent vista = accesoControlador.load();
            
            RegistrarCentroComputoController formulario = 
                    accesoControlador.getController();
            Scene escenaFormulario = new Scene(vista);
            Stage escenario = (Stage) tvCentroComputo.getScene().getWindow();
            escenario.setScene(escenaFormulario);
            formulario.setCCBD(ccRespuesta);
            formulario.inicializarComponentes(app, operacion);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la pantalla de formulario", 
                    Alert.AlertType.ERROR);
        }
    }
}