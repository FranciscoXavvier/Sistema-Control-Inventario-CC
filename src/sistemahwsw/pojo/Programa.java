/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

/**
 *
 * @author super
 */
public class Programa {

    protected int idPrograma;
    protected String nombre;
    protected String version;
    protected String idioma;
    protected String edicion;
    @FXML
    private CheckBox instalado;

    public Programa() {
    }

    public Programa(int idPrograma, String nombre, String version, String idioma, String edicion) {
        this.idPrograma = idPrograma;
        this.nombre = nombre;
        this.version = version;
        this.idioma = idioma;
        this.edicion = edicion;
        this.instalado = new CheckBox();
    }

    public Programa(int idPrograma, String nombre, String version, String idioma, String edicion, CheckBox instalado) {
        this.idPrograma = idPrograma;
        this.nombre = nombre;
        this.version = version;
        this.idioma = idioma;
        this.edicion = edicion;
        this.instalado = instalado;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public CheckBox getInstalado() {
        return instalado;
    }

    public void setInstalado(CheckBox instalado) {
        this.instalado = instalado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programa other = (Programa) obj;
        if (this.idPrograma != other.idPrograma) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.idioma, other.idioma)) {
            return false;
        }
        return Objects.equals(this.edicion, other.edicion);
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + ", version:" + version + ", edicion:" + edicion;
    }

    
}
