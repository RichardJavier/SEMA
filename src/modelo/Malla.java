package modelo;

import java.util.Date;


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
    private Integer porcentaje;
    private String estado;
    private Date fechaCreacion;
    private Integer idEspecialidad;
    private Integer idPeriodo;

    public Malla() {
    }

    public Malla(Integer idMalla) {
        this.idMalla = idMalla;
    }

    public Malla(Integer idMalla, String nombreMalla, Integer idSemestre) {
        super(idSemestre);
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

    
}
