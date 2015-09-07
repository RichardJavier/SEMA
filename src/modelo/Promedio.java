
package modelo;

import java.math.BigDecimal;
import java.util.Objects;


public class Promedio {
 private Integer idMateria;
 private BigDecimal porcentaje;
 private BigDecimal promedio;
 private BigDecimal valor;

    public Promedio() {
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idMateria);
        hash = 97 * hash + Objects.hashCode(this.porcentaje);
        hash = 97 * hash + Objects.hashCode(this.promedio);
        hash = 97 * hash + Objects.hashCode(this.valor);
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
        final Promedio other = (Promedio) obj;
        if (!Objects.equals(this.idMateria, other.idMateria)) {
            return false;
        }
        if (!Objects.equals(this.porcentaje, other.porcentaje)) {
            return false;
        }
        if (!Objects.equals(this.promedio, other.promedio)) {
            return false;
        }
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        return true;
    }

   
}
