/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

/**
 *
 * @author super
 */
public class Aplicacion {
    protected int idAplicacion;
    protected String nombre;
    protected String version;
    protected String idioma;

    public Aplicacion() {
    }
    
    public Aplicacion(int idAplicacion, String nombre, String version, String idioma) {
        this.idAplicacion = idAplicacion;
        this.nombre = nombre;
        this.version = version;
        this.idioma = idioma;
    }

    public int getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(int idAplicacion) {
        this.idAplicacion = idAplicacion;
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
    
    
}
