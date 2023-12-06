/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sistemahwsw.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import modelo.dao.CentroComputoDAO;
import sistemahwsw.pojo.CentroComputo;

/**
 * FXML Controller class
 *
 * @author aaron
 */
public class FXMLConsultarCCController implements Initializable {

    @FXML
    private Button clicVolver;
    @FXML
    private HBox hBoxCC;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarCCs();
    }    
    
    void inicializarCCs(){
        ArrayList<CentroComputo> listaCC = CentroComputoDAO.obtenerCentroComputo();
        for (CentroComputo cc: listaCC){
            Button btn = new Button(cc.toString());
            StackPane pane = new StackPane();
            pane.setMinSize(100, 100);
            pane.setPadding(new Insets(15));
            pane.getChildren().add(btn);
            hBoxCC.getChildren().add(pane);
            btn.setOnAction((ActionEvent) -> {
                irAConsultaCC(cc.getIdCentroComputo());
            });
        }
    }

    @FXML
    private void clicVolver(ActionEvent event) {
        Node origen = (Node)event.getSource();
        Stage stage = (Stage) origen.getScene().getWindow();
        stage.close();
    }

    private void irAConsultaCC(int idCentroComputo) {
        try{
            FXMLLoader accesoControlador = new FXMLLoader(getClass().getResource("/sistemahwsw/vistas/FXMLMenuEquipoComputo.fxml"));
            Parent vista = accesoControlador.load();
            
            FXMLMenuEquipoComputoController menu = accesoControlador.getController();
            Scene escena = new Scene(vista);
            Stage escenario = (Stage) clicVolver.getScene().getWindow();
            menu.inicializarIdCC(idCentroComputo);
            menu.configurarTabla();
            menu.cargarDatosTabla();
            escenario.setScene(escena);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }
}
