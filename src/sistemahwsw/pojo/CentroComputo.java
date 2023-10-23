/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

import java.util.ArrayList;

/**
 *
 * @author super
 */
public class CentroComputo {
    private int idCentroComputo;
    private String nombreCentroComputo;
    private ArrayList<EquipoComputo> equiposDeComputo;

    public CentroComputo(int idCentroComputo, String nombreCentroComputo, ArrayList<EquipoComputo> equiposDeComputo) {
        this.idCentroComputo = idCentroComputo;
        this.nombreCentroComputo = nombreCentroComputo;
        this.equiposDeComputo = equiposDeComputo;
    }

    public int getIdCentroComputo() {
        return idCentroComputo;
    }

    public void setIdCentroComputo(int idCentroComputo) {
        this.idCentroComputo = idCentroComputo;
    }

    public String getNombreCentroComputo() {
        return nombreCentroComputo;
    }

    public void setNombreCentroComputo(String nombreCentroComputo) {
        this.nombreCentroComputo = nombreCentroComputo;
    }

    public ArrayList<EquipoComputo> getEquiposDeComputo() {
        return equiposDeComputo;
    }

    public void setEquiposDeComputo(ArrayList<EquipoComputo> equiposDeComputo) {
        this.equiposDeComputo = equiposDeComputo;
    }
    
    
}
