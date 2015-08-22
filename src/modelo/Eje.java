package modelo;

import java.util.Objects;

public class Eje {

    private Integer idEje;
    private String codigoEje;
    private String nombreEje;

    public Eje() {
    }

    public Eje(Integer idEje, String codigoEje, String nombreEje) {
        this.idEje = idEje;
        this.codigoEje = codigoEje;
        this.nombreEje = nombreEje;
    }

    public Integer getIdEje() {
        return idEje;
    }

    public void setIdEje(Integer idEje) {
        this.idEje = idEje;
    }

    public String getCodigoEje() {
        return codigoEje;
    }

    public void setCodigoEje(String codigoEje) {
        this.codigoEje = codigoEje;
    }

    public String getNombreEje() {
        return nombreEje;
    }

    public void setNombreEje(String nombreEje) {
        this.nombreEje = nombreEje;
    }

    @Override
    public String toString() {
        return  nombreEje ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idEje);
        hash = 97 * hash + Objects.hashCode(this.codigoEje);
        hash = 97 * hash + Objects.hashCode(this.nombreEje);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Eje other = (Eje) obj;
        if (!Objects.equals(this.idEje, other.idEje)) {
            return false;
        }
        if (!Objects.equals(this.codigoEje, other.codigoEje)) {
            return false;
        }
        if (!Objects.equals(this.nombreEje, other.nombreEje)) {
            return false;
        }
        return true;
    }

}
