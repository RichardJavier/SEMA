
package modelo;

import java.math.BigDecimal;
import java.util.Date;


public class Resumen {
  private Integer idResumen;
  private Integer idAlumno;
  private String cedula;
  private String nombreCompleto;
  private BigDecimal promedioPonderadoNota;
  private Double notaTutoria;
  private Double notaTotalTeorica;
  private Double notaEmpresa;
  private Integer asistencia;
  private Double notaFinal;
  private String aprobacion;
  private Date fechaCreacion;
  private Date fechaModificacion;
  private Integer idPeriodo;
  private Integer idSemestre;
  private Integer idEspecialidad;
  private Integer idMalla;

    public Resumen() {
    }

    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public BigDecimal getPromedioPonderadoNota() {
        return promedioPonderadoNota;
    }

    public void setPromedioPonderadoNota(BigDecimal promedioPonderadoNota) {
        this.promedioPonderadoNota = promedioPonderadoNota;
    }

    public Double getNotaTutoria() {
        return notaTutoria;
    }

    public void setNotaTutoria(Double notaTutoria) {
        this.notaTutoria = notaTutoria;
    }

    public Double getNotaTotalTeorica() {
        return notaTotalTeorica;
    }

    public void setNotaTotalTeorica(Double notaTotalTeorica) {
        this.notaTotalTeorica = notaTotalTeorica;
    }

    public Double getNotaEmpresa() {
        return notaEmpresa;
    }

    public void setNotaEmpresa(Double notaEmpresa) {
        this.notaEmpresa = notaEmpresa;
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
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

    public Integer getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getIdMalla() {
        return idMalla;
    }

    public void setIdMalla(Integer idMalla) {
        this.idMalla = idMalla;
    }
    
}
