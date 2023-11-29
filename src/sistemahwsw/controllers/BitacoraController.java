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
import modelo.dao.BitacoraDAO;
import modelo.dao.ProgramaDAO;
import sistemahwsw.pojo.Bitacora;
import sistemahwsw.pojo.Programa;
import sistemahwsw.utilidades.Utilidades;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class BitacoraController implements Initializable {

    @FXML
    private Button btnRegistrar;
    @FXML
    private TableView<Bitacora> tvBitacora;
    @FXML
    private TableColumn<?, ?> colBitacora;
    @FXML
    private TableColumn<?, ?> colDescripcion;
    @FXML
    private TableColumn<?, ?> colHerramientas;
    
    private ObservableList<Bitacora> bitacoraBd;    
    private ArrayList<Bitacora> BitacoraRespuesta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosTabla();
    }    

     public void configurarTabla() {
        colBitacora.setCellValueFactory(new PropertyValueFactory("idBitacora"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        agregarBotones();
    }

    public void cargarDatosTabla() {
        bitacoraBd = FXCollections.observableArrayList();
        BitacoraRespuesta= BitacoraDAO.obtenerBitacora();
        for (Bitacora bitacora : BitacoraRespuesta){
            bitacoraBd.add(bitacora);
        }
        tvBitacora.setItems(bitacoraBd);
    }
    
    @FXML
    private void registrar(ActionEvent event) {
         try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLRegistrarBitacora.fxml"));
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
    
     private void agregarBotones() {
        TableColumn<Bitacora,String> colBotones = new TableColumn("Opciones");
         colBotones.setPrefWidth(175.19998168945312);
        Callback<TableColumn<Bitacora, String>, TableCell<Bitacora, String>> cellFactory = new Callback<TableColumn<Bitacora, String>, TableCell<Bitacora, String>>() {
            @Override
            public TableCell<Bitacora, String> call(final TableColumn<Bitacora, String> param) {
                final TableCell<Bitacora, String> cell = new TableCell<Bitacora, String>() {

                    private final Button btnConsultar = new Button("Consultar");
                    private final Button btnEliminar = new Button ("Eliminar");

                    {
                        btnConsultar.setStyle("-fx-background-color: #00BCD4;"
                                + "-fx-cursor: hand;"
                                + "-fx-text-fill: white;");

                        btnEliminar.setStyle("-fx-background-color: #FF0000;"
                                + "-fx-cursor: hand;"
                                + "-fx-text-fill: white;");
                        btnConsultar.setPrefWidth(175.19998168945312);
                        btnEliminar.setPrefWidth(175.19998168945312);
                        btnConsultar.setOnAction((ActionEvent event) -> {
                            Bitacora seleccion = getTableView().getItems().get(getIndex());
                            irAFormularioBitacora(seleccion, FXMLRegistrarBitacoraController.TipoOperacion.EDICION);
                        });

                        btnEliminar.setOnAction((ActionEvent event) ->{
                            Bitacora seleccion = getTableView().getItems().get(getIndex());
                            boolean confirmarEliminacion = Utilidades.mostrarDialogoConfirmacion("Confirmación de eliminación de aplicación", 
                                    "El programa será eliminado y cualquier equipo de cómputo que se encuentre con la "
                                            + "aplicación registrada será eliminada ¿Desea continuar con la acción?");
                            if (confirmarEliminacion){
                                if(BitacoraDAO.eliminarBitacora(seleccion.getIdBitacora())){
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
                            botones.getChildren().add(btnEliminar);
                            setGraphic(botones);
                        }
                    }
                };
                return cell;
            }
        };

        colBotones.setCellFactory(cellFactory);
        tvBitacora.getColumns().add(colBotones);
    }
     
     private void irAFormularioBitacora(Bitacora app, FXMLRegistrarBitacoraController.TipoOperacion operacion){
        try {
            FXMLLoader accesoControlador = 
                    new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLRegistrarBitacora.fxml"));
            Parent vista = accesoControlador.load();
            
            FXMLRegistrarBitacoraController formulario = 
                    accesoControlador.getController();
            Scene escenaFormulario = new Scene(vista);
            Stage escenario = (Stage) tvBitacora.getScene().getWindow();
            escenario.setScene(escenaFormulario);
            formulario.setBitacorasBD(BitacoraRespuesta);
            formulario.inicializarComponentes(app, operacion);
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la pantalla de formulario", 
                    Alert.AlertType.ERROR);
        }
    }
    
    
    
}
