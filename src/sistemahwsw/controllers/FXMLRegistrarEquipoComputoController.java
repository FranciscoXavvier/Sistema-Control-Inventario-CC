/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.dao.CentroComputoDAO;
import sistemahwsw.conexion.ConexionBD;
import sistemahwsw.pojo.CentroComputo;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLRegistrarEquipoComputoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField tf_centro_computo;
    
    private CentroComputo centroComputoDAO;
    private ConexionBD connection = ConexionBD.getInstancia(); // Conexión a la base de datos

    public FXMLRegistrarEquipoComputoController() {
        Connection conexion = connection.abrirConexionBD();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void registrar(ActionEvent event) throws Exception {               
        
        String nombre=tf_centro_computo.getText();

        
        CentroComputo centroComputo = new CentroComputo();
        centroComputo.setNombreCentroComputo(nombre);
        centroComputo.setIdTecnico(1);
        CentroComputoDAO.registrar(1,centroComputo);

        // Las credenciales son inválidas
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registro exitoso");
            alert.setHeaderText("Técnico registrado correctamente.");
            alert.setContentText("La información se ha registrado correctamente y ahora puedes hacer uso de tu usuario.");
            alert.showAndWait();
            System.out.println("Inicio de sesión fallido");
        
        
        
        //SI CREDENCIALES SON CORRECTAS
       
    }   
   
    
}
