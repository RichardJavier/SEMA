package modelo;

import java.util.Date;
import java.util.Objects;


public class Configuracion {

    private Integer idConfiguracion;
    private String nombreMalla;
    private Integer porcentajePonderacionNota;
    private Integer porcentajeTutoriaIntegrada;
    private Double valorMinimoPromedio;
    private Integer valorMinimoAsistencia;
    private Double valorNota;
    private Double valorRecuperacion;
    private Integer porcentaje;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Integer idPeriodo;

    public Configuracion() {
    }

    public Configuracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }
    
    public Configuracion(Integer idConfiguracion, String nombreMalla) {
        this.idConfiguracion = idConfiguracion;
        this.nombreMalla = nombreMalla;
    }

    public Integer getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getNombreMalla() {
        return nombreMalla;
    }

    public void setNombreMalla(String nombreMalla) {
        this.nombreMalla = nombreMalla;
    }

    public Integer getPorcentajePonderacionNota() {
        return porcentajePonderacionNota;
    }

    public void setPorcentajePonderacionNota(Integer porcentajePonderacionNota) {
        this.porcentajePonderacionNota = porcentajePonderacionNota;
    }

    public Integer getPorcentajeTutoriaIntegrada() {
        return porcentajeTutoriaIntegrada;
    }

    public void setPorcentajeTutoriaIntegrada(Integer porcentajeTutoriaIntegrada) {
        this.porcentajeTutoriaIntegrada = porcentajeTutoriaIntegrada;
    }

    public Double getValorMinimoPromedio() {
        return valorMinimoPromedio;
    }

    public void setValorMinimoPromedio(Double valorMinimoPromedio) {
        this.valorMinimoPromedio = valorMinimoPromedio;
    }
    
    public Integer getValorMinimoAsistencia() {
        return valorMinimoAsistencia;
    }

    public void setValorMinimoAsistencia(Integer valorMinimoAsistencia) {
        this.valorMinimoAsistencia = valorMinimoAsistencia;
    }

    public Double getValorNota() {
        return valorNota;
    }

    public void setValorNota(Double valorNota) {
        this.valorNota = valorNota;
    }

    public Double getValorRecuperacion() {
        return valorRecuperacion;
    }

    public void setValorRecuperacion(Double valorRecuperacion) {
        this.valorRecuperacion = valorRecuperacion;
    }
    
    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public String toString() {
        return nombreMalla ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idConfiguracion);
        hash = 53 * hash + Objects.hashCode(this.nombreMalla);
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
        final Configuracion other = (Configuracion) obj;
        if (!Objects.equals(this.idConfiguracion, other.idConfiguracion)) {
            return false;
        }
        if (!Objects.equals(this.nombreMalla, other.nombreMalla)) {
            return false;
        }
        return true;
    }

    
}
