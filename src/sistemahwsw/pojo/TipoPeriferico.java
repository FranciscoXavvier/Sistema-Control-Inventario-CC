/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahwsw.pojo;

/**
 *
 * @author super
 */
public class TipoPeriferico {
    private int idTipoPeriferico;
    private String tipoPeriferico;

    public int getIdTipoPeriferico() {
        return idTipoPeriferico;
    }

    public void setIdTipoPeriferico(int idTipoPeriferico) {
        this.idTipoPeriferico = idTipoPeriferico;
    }

    public String getTipoPeriferico() {
        return tipoPeriferico;
    }

    public void setTipoPeriferico(String tipoPeriferico) {
        this.tipoPeriferico = tipoPeriferico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final TipoPeriferico other = (TipoPeriferico) obj;
        return this.idTipoPeriferico == other.idTipoPeriferico;
    }

    @Override
    public String toString() {
        return tipoPeriferico;
    }
    
    
}
