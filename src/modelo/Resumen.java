package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Resumen extends Matricula {

    private Integer idResumen;
    private Integer idAlumno;
    private String cedula;
    private String nombreCompleto;
    private Integer numeroCreditosCiclo;
    private Integer numeroCreditosTeorica;
    private Integer porcentajeNotaTeorica;
    private Integer porcentajeNotaEmpresa;
    private BigDecimal promedioPonderadoNota;
    private BigDecimal notaTutoria;
    private BigDecimal notaTotalTeorica;
    private BigDecimal notaEmpresa;
    private Integer asistencia;
    private BigDecimal notaFinal;
    private String aprobacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Integer idSemestre;
    private Integer idEspecialidad;
    private Integer idMalla;

    public Resumen() {
    }

    public Resumen(Integer idResumen, String nombreCompleto, String aprobacion, String semestre, String especialidad) {
        super(semestre, especialidad);
        this.idResumen = idResumen;
        this.nombreCompleto = nombreCompleto;
        this.aprobacion = aprobacion;
    }
    public Integer getIdResumen() {
        return idResumen;
    }

    public void setIdResumen(Integer idResumen) {
        this.idResumen = idResumen;
    }

    @Override
    public Integer getIdAlumno() {
        return idAlumno;
    }

    @Override
    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public String getCedula() {
        return cedula;
    }

    @Override
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getNumeroCreditosCiclo() {
        return numeroCreditosCiclo;
    }

    public void setNumeroCreditosCiclo(Integer numeroCreditosCiclo) {
        this.numeroCreditosCiclo = numeroCreditosCiclo;
    }

    public Integer getNumeroCreditosTeorica() {
        return numeroCreditosTeorica;
    }

    public void setNumeroCreditosTeorica(Integer numeroCreditosTeorica) {
        this.numeroCreditosTeorica = numeroCreditosTeorica;
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

    public BigDecimal getPromedioPonderadoNota() {
        return promedioPonderadoNota;
    }

    public void setPromedioPonderadoNota(BigDecimal promedioPonderadoNota) {
        this.promedioPonderadoNota = promedioPonderadoNota;
    }

    public BigDecimal getNotaTutoria() {
        return notaTutoria;
    }

    public void setNotaTutoria(BigDecimal notaTutoria) {
        this.notaTutoria = notaTutoria;
    }

    public BigDecimal getNotaTotalTeorica() {
        return notaTotalTeorica;
    }

    public void setNotaTotalTeorica(BigDecimal notaTotalTeorica) {
        this.notaTotalTeorica = notaTotalTeorica;
    }

    public BigDecimal getNotaEmpresa() {
        return notaEmpresa;
    }

    public void setNotaEmpresa(BigDecimal notaEmpresa) {
        this.notaEmpresa = notaEmpresa;
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
    }

    @Override
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public Integer getIdSemestre() {
        return idSemestre;
    }

    @Override
    public void setIdSemestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    @Override
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    @Override
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
