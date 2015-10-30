package modelo;

import java.util.Date;

public class Matricula extends Alumno {

    private Integer idMatricula;
    private String tipoMatricula;
    private Date fechaCreacion;
    private Integer idSemestre;
    private String semestre;
    private Integer idParalelo;
    private String paralelo;
    private Integer idEspecialidad;
    private String especialidad;
    private Integer idPeriodo;

    public Matricula() {
    }

    public Matricula(String semestre, String especialidad) {
        this.semestre = semestre;
        this.especialidad = especialidad;
    }

    public Matricula(String semestre, String especialidad, String tipoMatricula) {
        this.semestre = semestre;
        this.especialidad = especialidad;
        this.tipoMatricula = tipoMatricula;
    }

    public Matricula(Integer idMatricula, String tipoMatricula, String semestre, String paralelo, String especialidad, String cedula, String nombreCompleto) {
        super(cedula, nombreCompleto);
        this.idMatricula = idMatricula;
        this.tipoMatricula = tipoMatricula;
        this.semestre = semestre;
        this.paralelo = paralelo;
        this.especialidad = especialidad;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(String tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public Integer getIdParalelo() {
        return idParalelo;
    }

    public void setIdParalelo(Integer idParalelo) {
        this.idParalelo = idParalelo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

}
