
package modelo;

import java.util.Objects;


public class Semestre {
private Integer idSemestre;
private String semestre;
private String codigoSemestre;

    public Semestre() {
    }


    public Semestre(String semestre) {
        this.semestre = semestre;
    }

    public Semestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Semestre(Integer idSemestre, String semestre, String codigoSemestre) {
        this.idSemestre = idSemestre;
        this.semestre = semestre;
        this.codigoSemestre = codigoSemestre;
    }

    public Integer getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCodigoSemestre() {
        return codigoSemestre;
    }

    public void setCodigoSemestre(String codigoSemestre) {
        this.codigoSemestre = codigoSemestre;
    }

    @Override
    public String toString() {
        return  semestre ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idSemestre);
        hash = 23 * hash + Objects.hashCode(this.semestre);
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
        final Semestre other = (Semestre) obj;
        if (!Objects.equals(this.idSemestre, other.idSemestre)) {
            return false;
        }
        if (!Objects.equals(this.semestre, other.semestre)) {
            return false;
        }
        return true;
    }

   

}
