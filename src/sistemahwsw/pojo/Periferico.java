/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

/**
 *
 * @author super
 */
public class Periferico {
    private String marca;
    private String modelo;
    private String tipoPeriferico;

    public Periferico() {
    }

    public Periferico(String marca, String modelo, String tipoPeriferico) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipoPeriferico = tipoPeriferico;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoPeriferico() {
        return tipoPeriferico;
    }

    public void setTipoPeriferico(String tipoPeriferico) {
        this.tipoPeriferico = tipoPeriferico;
    }
    
    
}
