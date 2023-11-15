package sistemahwsw.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.dao.CentroComputoDAO;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.CentroComputo;


public class RegistrarCentroComputoController implements Initializable {
    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField tf_centro_computo;

    private CentroComputo centroComputoDAO;
    private ConexionBD connection = ConexionBD.getInstancia(); // Conexión a la base de datos
    @FXML
    private Button boton_cancelar;

    public RegistrarCentroComputoController() {
        Connection conexion = connection.abrirConexionBD();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void registrar(ActionEvent event) throws Exception {               

        String nombre=tf_centro_computo.getText();


        CentroComputo centroComputo = new CentroComputo();
        centroComputo.setNombreCentroComputo(nombre);
        centroComputo.setIdTecnico(1);
        CentroComputoDAO.registrar(1,centroComputo);

        // Las credenciales son inválidas
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registro exitoso");
            alert.setHeaderText("Centro de cómputo registrado correctamente.");
            alert.setContentText("La información se ha registrado correctamente .");
            alert.showAndWait();
    }   
    
    public void cancelar(ActionEvent event) throws Exception{
         try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLMenuCentroComputo.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            MenuCentroComputoController controlador = (MenuCentroComputoController) fxmlLoader.getController();
            controlador.configurarTabla();
            controlador.cargarDatosTabla();
            stage.showAndWait();
         
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }

    @FXML
    private void regresar(ActionEvent event) {
    }


}