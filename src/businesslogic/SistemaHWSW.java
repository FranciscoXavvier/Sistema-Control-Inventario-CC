/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sistemahwsw.dao.AplicacionDAO;
import sistemahwsw.pojo.Aplicacion;

/**
 *
 * @author HP
 */
public class SistemaHWSW extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<Aplicacion> aplicaciones = AplicacionDAO.obtenerAplicaciones();
        for (Aplicacion app : aplicaciones){
            System.out.println(app.getNombre());
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("/sistemahwsw/vistas/FXMLmenuPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
