
package modelo;

import java.util.Objects;


public class Paralelo {
    private Integer idParelelo;
    private String paralelo;
    private String codigoParalelo;

    public Paralelo() {
    }

    public Paralelo(Integer idParelelo, String paralelo, String codigoParalelo) {
        this.idParelelo = idParelelo;
        this.paralelo = paralelo;
        this.codigoParalelo = codigoParalelo;
    }

    public Integer getIdParelelo() {
        return idParelelo;
    }

    public void setIdParelelo(Integer idParelelo) {
        this.idParelelo = idParelelo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public String getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(String codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    @Override
    public String toString() {
        return  paralelo ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idParelelo);
        hash = 29 * hash + Objects.hashCode(this.paralelo);
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
        final Paralelo other = (Paralelo) obj;
        if (!Objects.equals(this.idParelelo, other.idParelelo)) {
            return false;
        }
        if (!Objects.equals(this.paralelo, other.paralelo)) {
            return false;
        }
        return true;
    }
    
}
