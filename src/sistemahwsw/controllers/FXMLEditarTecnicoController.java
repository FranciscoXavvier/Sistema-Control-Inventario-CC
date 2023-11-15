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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.dao.TecnicoDAO;
import sistemahwsw.pojo.Tecnico;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLEditarTecnicoController implements Initializable {

    @FXML
    private Button boton_salir;
    @FXML
    private TextField tf_nombre;
    @FXML
    private TextField tf_usuario;
    @FXML
    private TextField tf_numero_personal;
    private MenuPrincipalController mimp;
    private Tecnico miUsuario;
    /**
     * Initializes the controller class.
     */
    public void parametros(String username, MenuPrincipalController mp){
            mimp = mp;
            miUsuario = TecnicoDAO.findByUsername(username);
            tf_nombre.setText(miUsuario.getNombre()+' '+miUsuario.getApellidoPaterno()+' '+miUsuario.getApellidoMaterno());
            tf_numero_personal.setText(miUsuario.getNumeroPersonal());
            tf_usuario.setText(miUsuario.getUsername());
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
   
   
}
