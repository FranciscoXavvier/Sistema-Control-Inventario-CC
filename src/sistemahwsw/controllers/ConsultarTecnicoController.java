/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemahwsw.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modelo.dao.TecnicoDAO;
import sistemahwsw.pojo.Tecnico;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConsultarTecnicoController implements Initializable {

    @FXML private javafx.scene.control.Button boton_salir;    
    @FXML private Label lbl_nombre;
    @FXML private Label lbl_numero_personal;
    @FXML private Label lbl_usuario;
    private String miUsername;
    private MenuPrincipalController mimp;
    private Tecnico miUsuario;
    
    public void parametros(String username, MenuPrincipalController mp){
        mimp = mp;
        miUsuario = TecnicoDAO.findByUsername(username);
        lbl_nombre.setText(miUsuario.getNombre()+' '+miUsuario.getApellidoPaterno()+' '+miUsuario.getApellidoMaterno());
        lbl_numero_personal.setText(miUsuario.getNumeroPersonal());
        lbl_usuario.setText(miUsuario.getUsername());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void salir(ActionEvent event){
        try {
            Stage stage = (Stage) boton_salir.getScene().getWindow();

            stage.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sistemahwsw/vistas/FXMLEditarTecnico.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) boton_salir.getScene().getWindow();
            stage.setScene(scene);
            
            FXMLEditarTecnicoController controlador1 = (FXMLEditarTecnicoController) fxmlLoader.getController();
            controlador1.parametros(miUsuario.getUsername(), this);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
       
}
