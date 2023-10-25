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
public class Tecnico {
    private String username;
    private String password;
    private int idTecnico;
    private String numeroPersonal;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private ArrayList<CentroComputo> centrosDeComputo;

    public Tecnico() {
    }
    
    public Tecnico(String username, String password, int idTecnico, String numeroPersonal, String nombre, String apellidoPaterno, String apellidoMaterno, ArrayList<CentroComputo> centrosDeComputo) {
        this.username = username;
        this.password = password;
        this.idTecnico = idTecnico;
        this.numeroPersonal = numeroPersonal;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.centrosDeComputo = centrosDeComputo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(String numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public ArrayList<CentroComputo> getCentrosDeComputo() {
        return centrosDeComputo;
    }

    public void setCentrosDeComputo(ArrayList<CentroComputo> centrosDeComputo) {
        this.centrosDeComputo = centrosDeComputo;
    }
    
}
