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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.dao.ProgramaDAO;
import modelo.dao.EquipoComputoDAO;
import sistemahwsw.pojo.Programa;
import sistemahwsw.pojo.EquipoComputo;
import sistemahwsw.utilidades.Utilidades;

/**
 * FXML Controller class
 *
 * @author ABF26
 */
public class FXMLMenuEquipoComputoController implements Initializable {
    private int idCentroComputo;
    
    @FXML
    private TableView<EquipoComputo> tvEquipoComputo;
    private ObservableList<EquipoComputo> equiposDeComputoBD;
    private ArrayList<EquipoComputo> equiposDeComputoRespuesta;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<?, ?> tcIdEquipoComputo;
    @FXML
    private TableColumn<?, ?> tcFila;
    @FXML
    private TableColumn<?, ?> tcColumna;
    @FXML
    private TableColumn<?, ?> tcCodigoBarras;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicRegistrar(ActionEvent event) {
        try{
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLTipoRegistroHardware.fxml"));
            Parent vista = accesoControlador.load();
            
            FXMLTipoRegistroHardwareController menu = accesoControlador.getController();
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) tvEquipoComputo.getScene().getWindow();
            
            menu.inicializaridCC(idCentroComputo);
            escenario.setScene(escena);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void inicializarIdCC(int idCC) {
        this.idCentroComputo = idCC;
    }

    public void configurarTabla(){
        tcIdEquipoComputo.setCellValueFactory(new PropertyValueFactory("idEquipo"));
        tcFila.setCellValueFactory(new PropertyValueFactory("fila"));
        tcColumna.setCellValueFactory(new PropertyValueFactory("columna"));
        tcCodigoBarras.setCellValueFactory(new PropertyValueFactory("codigoDeBarras"));
        agregarBotones();
    }
    
    public void cargarDatosTabla(){
        equiposDeComputoBD= FXCollections.observableArrayList();
        equiposDeComputoRespuesta= EquipoComputoDAO.getEquiposDeComputoPorIdCC(idCentroComputo);
        for (EquipoComputo ec : equiposDeComputoRespuesta){
            equiposDeComputoBD.add(ec);
            System.out.println(ec.getCodigoDeBarras());
        }
        tvEquipoComputo.setItems(equiposDeComputoBD);
    }
    
    private void agregarBotones(){
        TableColumn<EquipoComputo,String> colBotones = new TableColumn("Opciones");
        colBotones.setPrefWidth(190);
        Callback<TableColumn<EquipoComputo, String>, TableCell<EquipoComputo, String>> cellFactory = new Callback<TableColumn<EquipoComputo, String>, TableCell<EquipoComputo, String>>() {
            @Override
            public TableCell<EquipoComputo, String> call(final TableColumn<EquipoComputo, String> param) {
                final TableCell<EquipoComputo, String> cell = new TableCell<EquipoComputo, String>() {

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
                        btnConsultar.setPrefWidth(190);
                        btnModificar.setPrefWidth(190);
                        btnEliminar.setPrefWidth(190);
                        btnConsultar.setOnAction((ActionEvent event) -> {
                            try{
                                EquipoComputo seleccion = getTableView().getItems().get(getIndex());
                                FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLTipoConsultaEquipoComputo.fxml"));
                                Parent vista = accesoControlador.load();
                                FXMLTipoConsultaEquipoComputoController ventanaConsulta = accesoControlador.getController();

                                ventanaConsulta.asignarComputadora(seleccion);

                                Stage nuevoEscenario = new Stage();
                                Scene escenarioConsulta = new Scene(vista);
                                nuevoEscenario.setScene(escenarioConsulta);
                                nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
                                nuevoEscenario.showAndWait();                                
                            }catch(IOException ex){
                                ex.printStackTrace();
                            }
                        });
                        btnModificar.setOnAction((ActionEvent event) ->{
                            EquipoComputo seleccion = getTableView().getItems().get(getIndex());
                            System.out.println("b");
                        });
                        btnEliminar.setOnAction((ActionEvent event) ->{
                            EquipoComputo seleccion = getTableView().getItems().get(getIndex());
                            System.out.println("c");
//                            boolean confirmarEliminacion = Utilidades.mostrarDialogoConfirmacion("Confirmación de eliminación de aplicación", 
//                                    "El programa será eliminado y cualquier equipo de cómputo que se encuentre con la "
//                                            + "aplicación registrada será eliminada ¿Desea continuar con la acción?");
//                            if (confirmarEliminacion){
//                                if(ProgramaDAO.eliminarPrograma(seleccion.getIdPrograma())){
//                                    Utilidades.mostrarAlertaSimple("Éxito en la operación", 
//                                            "Aplicación eliminado de manera exitosa", 
//                                            Alert.AlertType.INFORMATION);
//                                    cargarDatosTabla();
//                                }else{
//                                    Utilidades.mostrarAlertaSimple("Error en la eliminación del programa", 
//                                            "Hubo un error al momento de eliminar la aplicación, por favor vuelva a intentarlo más tarde", 
//                                            Alert.AlertType.ERROR);
//                                }
//                            }
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

        tvEquipoComputo.getColumns().add(colBotones);

    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        Utilidades.cambiarVentana("Menú equipo de cómputo", tvEquipoComputo, "/sistemahwsw/vistas/FXMLConsultarCC.fxml");
    }

    
}
