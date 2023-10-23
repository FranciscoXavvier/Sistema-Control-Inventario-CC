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
public class EquipoComputo {
    private int idEquipo;
    private String procesador;
    private String tarjetaMadre;
    private String memoriaRam;
    private String almacenamiento;
    private String lectorOptico;
    private String codigoDeBarras;
    private String fila;
    private String columna;
    private ArrayList<Aplicacion> aplicaciones;
    private SistemaOperativo sistemaOperativoInstalado;

    public EquipoComputo() {
    }

    public EquipoComputo(int idEquipo, String procesador, String tarjetaMadre, String memoriaRam, String almacenamiento, String lectorOptico, String codigoDeBarras, String fila, String columna, ArrayList<Aplicacion> aplicaciones, SistemaOperativo sistemaOperativoInstalado) {
        this.idEquipo = idEquipo;
        this.procesador = procesador;
        this.tarjetaMadre = tarjetaMadre;
        this.memoriaRam = memoriaRam;
        this.almacenamiento = almacenamiento;
        this.lectorOptico = lectorOptico;
        this.codigoDeBarras = codigoDeBarras;
        this.fila = fila;
        this.columna = columna;
        this.aplicaciones = aplicaciones;
        this.sistemaOperativoInstalado = sistemaOperativoInstalado;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getTarjetaMadre() {
        return tarjetaMadre;
    }

    public void setTarjetaMadre(String tarjetaMadre) {
        this.tarjetaMadre = tarjetaMadre;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getLectorOptico() {
        return lectorOptico;
    }

    public void setLectorOptico(String lectorOptico) {
        this.lectorOptico = lectorOptico;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public ArrayList<Aplicacion> getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(ArrayList<Aplicacion> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    public SistemaOperativo getSistemaOperativoInstalado() {
        return sistemaOperativoInstalado;
    }

    public void setSistemaOperativoInstalado(SistemaOperativo sistemaOperativoInstalado) {
        this.sistemaOperativoInstalado = sistemaOperativoInstalado;
    }
    
    
    
}
