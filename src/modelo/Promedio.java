
package modelo;

import java.math.BigDecimal;
import java.util.Objects;


public class Promedio {
 private Integer idMateria;
 private BigDecimal porcentaje;
 private BigDecimal promedio;
 private BigDecimal valorPonderado;
 private BigDecimal valorEmpresa;
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

    public BigDecimal getValorPonderado() {
        return valorPonderado;
    }

    public void setValorPonderado(BigDecimal valorPonderado) {
        this.valorPonderado = valorPonderado;
    }

    public BigDecimal getValorEmpresa() {
        return valorEmpresa;
    }

    public void setValorEmpresa(BigDecimal valorEmpresa) {
        this.valorEmpresa = valorEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idMateria);
        hash = 97 * hash + Objects.hashCode(this.porcentaje);
        hash = 97 * hash + Objects.hashCode(this.promedio);
        hash = 97 * hash + Objects.hashCode(this.valorPonderado);
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
        if (!Objects.equals(this.valorPonderado, other.valorPonderado)) {
            return false;
        }
        return true;
    }

   
}
