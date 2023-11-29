package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.CentroComputoDAO;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.CentroComputo;
import sistemahwsw.utilidades.Utilidades;

public class RegistrarCentroComputoController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField tf_centro_computo;

     private CentroComputo ccSeleccionado;
    private CentroComputo centroComputoDAO;
    private ConexionBD connection = ConexionBD.getInstancia(); // Conexión a la base de datos
    @FXML
    private Button boton_cancelar;
    
    private TipoOperacion operacionSeleccionada;
    private ArrayList<CentroComputo> ccRegistrados;
    @FXML
    private Button btn_registrar;
    @FXML
    private Label lbTItle;
    @FXML
    private Button btn_editar;
    @FXML
    private Label lb_instrucciones;

    @FXML
    private void editar(ActionEvent event) {
        if (CentroComputoDAO.editarCC(tf_centro_computo.getText(),ccSeleccionado.getIdCentroComputo() )){
                            Utilidades.mostrarAlertaSimple("Éxito en la operación", 
                                    "El programa ha sido editado correctamente en la base de datos", 
                                    Alert.AlertType.INFORMATION);
                            
                        }else{
                            Utilidades.mostrarAlertaSimple("Error", 
                                    "Se produjo un error al registrar el nuevo programa, por favor intentelo de nuevo", 
                                    Alert.AlertType.ERROR);
                        }
    }
    
    public enum TipoOperacion{
        CONSULTA,
        EDICION
    }
    
    public RegistrarCentroComputoController() {
        Connection conexion = connection.abrirConexionBD();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_editar.setVisible(false);
    }
    
    public void inicializarValores(CentroComputo seleccion){
        tf_centro_computo.setText(seleccion.getNombreCentroComputo());
    }
    
    public void inicializarComponentes(CentroComputo seleccion, TipoOperacion operacion){
        operacionSeleccionada = operacion;
        switch(operacion){
         
            case CONSULTA:
                lbTItle.setText("Centró de cómputo");
                lb_instrucciones.setVisible(false);
                tf_centro_computo.setEditable(false);
                btn_editar.setVisible(false);
                btn_registrar.setVisible(false);
                ccSeleccionado = seleccion;
                inicializarValores(seleccion);
                break;
            
            case EDICION:
                
                lbTItle.setText("Editar Centro Cómputo");
                btn_editar.setVisible(true);
                btn_registrar.setVisible(false);
                ccSeleccionado = seleccion;
                inicializarValores(seleccion);
                break;
           
            default:
                System.out.println("No se puede desplegar el formulario");
        }
    }

    @FXML
    public void registrar(ActionEvent event) throws Exception {

        String nombre = tf_centro_computo.getText();
        
        CentroComputo centroComputo = new CentroComputo();
        centroComputo.setNombreCentroComputo(nombre);
        centroComputo.setIdTecnico(1);
        CentroComputoDAO.registrar(1, centroComputo);

        // Las credenciales son inválidas
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText("Centro de cómputo registrado correctamente.");
        alert.setContentText("La información se ha registrado correctamente .");
        alert.showAndWait();
        cerrarVentana();
    }

    @FXML
    public void regresar(ActionEvent event) throws Exception {
        try {
            cerrarVentana();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cerrarVentana() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLMenuCentroComputo.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            MenuCentroComputoController controlador = fxmlLoader.getController();
            controlador.configurarTabla();
            controlador.cargarDatosTabla();
            stage.show();

            Stage escenarioActual = (Stage) tf_centro_computo.getScene().getWindow();
            escenarioActual.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

     public void setCCBD(ArrayList<CentroComputo> centroComputos){
        this.ccRegistrados = centroComputos;
    }
    
    
}
