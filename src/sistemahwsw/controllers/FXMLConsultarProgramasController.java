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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.dao.ProgramaDAO;
import sistemahwsw.pojo.Programa;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author super
 */
public class FXMLConsultarProgramasController implements Initializable {

    @FXML
    private TableView<Programa> tvProgramas;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colNombre;
    
    private ObservableList<Programa> ProgramasBD;
    private ArrayList<Programa> ProgramasRespuesta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosTabla();
    }    

    private void configurarTabla(){
        colID.setCellValueFactory(new PropertyValueFactory("idPrograma"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        agregarBotones();
    }
    
    private void cargarDatosTabla(){
        ProgramasBD = FXCollections.observableArrayList();
        ProgramasRespuesta = ProgramaDAO.obtenerProgramas();
        for (Programa app : ProgramasRespuesta){
            ProgramasBD.add(app);
        }
        tvProgramas.setItems(ProgramasBD);
    }
    
    private void agregarBotones(){
        TableColumn<Programa,String> colBotones = new TableColumn("Opciones");
        colBotones.setPrefWidth(150);
        Callback<TableColumn<Programa, String>, TableCell<Programa, String>> cellFactory = new Callback<TableColumn<Programa, String>, TableCell<Programa, String>>() {
            @Override
            public TableCell<Programa, String> call(final TableColumn<Programa, String> param) {
                final TableCell<Programa, String> cell = new TableCell<Programa, String>() {

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
                        btnConsultar.setPrefWidth(150);
                        btnModificar.setPrefWidth(150);
                        btnEliminar.setPrefWidth(150);
                        btnConsultar.setOnAction((ActionEvent event) -> {
                            Programa seleccion = getTableView().getItems().get(getIndex());
                            irAFormularioPrograma(seleccion, FXMLFormularioProgramaController.TipoOperacion.CONSULTA);
                        });
                        btnModificar.setOnAction((ActionEvent event) ->{
                            Programa seleccion = getTableView().getItems().get(getIndex());
                            irAFormularioPrograma(seleccion, FXMLFormularioProgramaController.TipoOperacion.EDICION);
                        });
                        btnEliminar.setOnAction((ActionEvent event) ->{
                            Programa seleccion = getTableView().getItems().get(getIndex());
                            boolean confirmarEliminacion = Utilidades.mostrarDialogoConfirmacion("Confirmación de eliminación de aplicación", 
                                    "El programa será eliminado y cualquier equipo de cómputo que se encuentre con la "
                                            + "aplicación registrada será eliminada ¿Desea continuar con la acción?");
                            if (confirmarEliminacion){
                                if(ProgramaDAO.eliminarPrograma(seleccion.getIdPrograma())){
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
        tvProgramas.getColumns().add(colBotones);
    }
    
    private void irAFormularioPrograma(Programa app, FXMLFormularioProgramaController.TipoOperacion operacion){
        try {
            FXMLLoader accesoControlador = 
                    new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLFormularioPrograma.fxml"));
            Parent vista = accesoControlador.load();
            
            FXMLFormularioProgramaController formulario = 
                    accesoControlador.getController();
            Scene escenaFormulario = new Scene(vista);
            Stage escenario = (Stage) tvProgramas.getScene().getWindow();
            escenario.setScene(escenaFormulario);
            formulario.setListaProgramasBD(ProgramasRespuesta);
            formulario.inicializarComponentes(app, operacion);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la pantalla de formulario", 
                    Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void clicVolver(ActionEvent event) {
        Utilidades.cambiarVentana("Programaes", (Node) event.getSource(), "/sistemahwsw/vistas/FXMLProgramas.fxml");
    }
    
}
