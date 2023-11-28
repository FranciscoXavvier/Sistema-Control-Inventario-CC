/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

/**
 *
 * @author super
 */
public class SistemaOperativo extends Programa{
    private String arquitectura;
    private String tipo;
    
    public SistemaOperativo(){
    }

    public SistemaOperativo(String arquitectura, String tipo, int idPrograma, String nombre, String version, String idioma, String edicion) {
        super(idPrograma, nombre, version, idioma, edicion);
        this.arquitectura = arquitectura;
        this.tipo = tipo;
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
     
    public String getArquitectura() {
        return arquitectura;
    }

    public void setArquitectura(String arquitectura) {
        this.arquitectura = arquitectura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombre + " " + version + " " + arquitectura + " " + tipo;
    }
    
    
    
}
