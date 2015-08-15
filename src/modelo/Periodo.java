package modelo;

import java.util.Date;
import java.util.Objects;

public class Periodo {

    private String codigoPeriodo;
    private String nombrePeriodo;
    private Integer idPeriodo;
    private Integer matricula;
    private Integer ciclo;
    private Date fechaFinalizacion;
    private Integer malla;

    public Periodo() {
    }

    public Periodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Periodo(String codigoPeriodo, String nombrePeriodo, Integer idPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
        this.nombrePeriodo = nombrePeriodo;
        this.idPeriodo = idPeriodo;
    }

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Integer getMalla() {
        return malla;
    }

    public void setMalla(Integer malla) {
        this.malla = malla;
    }

    @Override
    public String toString() {
        return  nombrePeriodo ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nombrePeriodo);
        hash = 59 * hash + Objects.hashCode(this.idPeriodo);
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
        final Periodo other = (Periodo) obj;
        if (!Objects.equals(this.nombrePeriodo, other.nombrePeriodo)) {
            return false;
        }
        if (!Objects.equals(this.idPeriodo, other.idPeriodo)) {
            return false;
        }
        return true;
    }
    
}
