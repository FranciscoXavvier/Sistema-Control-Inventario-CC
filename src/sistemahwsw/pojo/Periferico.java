/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

import java.util.Objects;

/**
 *
 * @author super
 */
public class Periferico {
    private int idPeriferico;
    private String marca;
    private String modelo;
    private TipoPeriferico tipo;
    private String codigoDeBarras;
    private int equipoComputo;

    public Periferico() {
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

    public TipoPeriferico getTipo() {
        return tipo;
    }

    public void setTipo(TipoPeriferico tipo) {
        this.tipo = tipo;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getEquipoComputo() {
        return equipoComputo;
    }

    public void setEquipoComputo(int equipoComputo) {
        this.equipoComputo = equipoComputo;
    }   

    public int getIdPeriferico() {
        return idPeriferico;
    }

    public void setIdPeriferico(int idPeriferico) {
        this.idPeriferico = idPeriferico;
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
        final Periferico other = (Periferico) obj;
        return this.idPeriferico == other.idPeriferico;
    }
    
    
}
