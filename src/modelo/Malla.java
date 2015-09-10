package modelo;

import java.util.Date;
import java.util.Objects;


public class Malla extends Semestre {

    private Integer idMalla;
    private String nombreMalla;
    private Integer porcentajePonderacionNota;
    private Integer porcentajeTutoriaIntegrada;
    private Integer porcentajeNotaTeorica;
    private Integer porcentajeNotaEmpresa;
    private Integer creditoTeoria;
    private Integer creditosTeoricaDisponibles;
    private Integer creditoCiclo;
    private Integer creditoNotaEmpresa;
    private Double valorMinimoPromedio;
    private Integer valorMinimoAsistencia;
    private Double valorNota;
    private Double valorRecuperacion;
    private Integer porcentaje;
    private String estado;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Integer idEspecialidad;
    private Integer idPeriodo;

    public Malla() {
    }

    public Malla(String semestre) {
        super(semestre);
    }

    public Malla(Integer idMalla) {
        this.idMalla = idMalla;
    }
    
    public Malla(Integer idMalla, String nombreMalla,String semestre) {
        super(semestre);
        this.idMalla = idMalla;
        this.nombreMalla = nombreMalla;
    }
    public Malla(Integer idMalla, String nombreMalla, Integer idSemestre,String semestre) {
        super(idSemestre,semestre);
        this.idMalla = idMalla;
        this.nombreMalla = nombreMalla;
    }

    public Integer getIdMalla() {
        return idMalla;
    }

    public void setIdMalla(Integer idMalla) {
        this.idMalla = idMalla;
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

    public Integer getPorcentajeNotaTeorica() {
        return porcentajeNotaTeorica;
    }

    public void setPorcentajeNotaTeorica(Integer porcentajeNotaTeorica) {
        this.porcentajeNotaTeorica = porcentajeNotaTeorica;
    }

    public Integer getPorcentajeNotaEmpresa() {
        return porcentajeNotaEmpresa;
    }

    public void setPorcentajeNotaEmpresa(Integer porcentajeNotaEmpresa) {
        this.porcentajeNotaEmpresa = porcentajeNotaEmpresa;
    }

    public Integer getCreditoTeoria() {
        return creditoTeoria;
    }

    public void setCreditoTeoria(Integer creditoTeoria) {
        this.creditoTeoria = creditoTeoria;
    }

    public Integer getCreditoCiclo() {
        return creditoCiclo;
    }

    public void setCreditoCiclo(Integer creditoCiclo) {
        this.creditoCiclo = creditoCiclo;
    }
   
    public Integer getCreditosTeoricaDisponibles() {
        return creditosTeoricaDisponibles;
    }

    public void setCreditosTeoricaDisponibles(Integer creditosTeoricaDisponibles) {
        this.creditosTeoricaDisponibles = creditosTeoricaDisponibles;
    }

    public Integer getCreditoNotaEmpresa() {
        return creditoNotaEmpresa;
    }

    public void setCreditoNotaEmpresa(Integer creditoNotaEmpresa) {
        this.creditoNotaEmpresa = creditoNotaEmpresa;
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

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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
        hash = 53 * hash + Objects.hashCode(this.idMalla);
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
        final Malla other = (Malla) obj;
        if (!Objects.equals(this.idMalla, other.idMalla)) {
            return false;
        }
        if (!Objects.equals(this.nombreMalla, other.nombreMalla)) {
            return false;
        }
        return true;
    }

    
}
