
package tablas;

import java.util.Date;

public class Matricula {
private Integer idAlumno;
private String cedula;
private Integer idSemestre;
private Integer idCiclo;
private Integer idParalelo;
private String tipoMatricula;
private Date fechaCreacion;

    public Matricula() {
    }

    public Matricula(Integer idAlumno, String cedula, Integer idSemestre, Integer idCiclo, Integer idParalelo, String tipoMatricula, Date fechaCreacion) {
        this.idAlumno = idAlumno;
        this.cedula = cedula;
        this.idSemestre = idSemestre;
        this.idCiclo = idCiclo;
        this.idParalelo = idParalelo;
        this.tipoMatricula = tipoMatricula;
        this.fechaCreacion = fechaCreacion;
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

    public Integer getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Integer idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Integer getIdParalelo() {
        return idParalelo;
    }

    public void setIdParalelo(Integer idParalelo) {
        this.idParalelo = idParalelo;
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


}
