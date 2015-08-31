package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Nota extends Materia {

    private Integer idNota;
    private String cedula;
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private Double nota4;
    private Double nota5;
    private Double nota6;
    private Double nota7;
    private Double nota8;
    private Double nota9;
    private Double nota10;
    private Double recuperacion;
    private Integer asistencia;
    private String estadoAsistencia;
    private BigDecimal promedio;
    private String estadoNota;
    private Date fechaIngreso;
    private Date fechaModificacion;

    public Nota() {
    }

    public Nota(Integer idNota, String cedula, String nombreMateria, String semestre, String especialidad) {
        super(nombreMateria, semestre, especialidad);
        this.idNota = idNota;
        this.cedula = cedula;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    public Double getNota4() {
        return nota4;
    }

    public void setNota4(Double nota4) {
        this.nota4 = nota4;
    }

    public Double getNota5() {
        return nota5;
    }

    public void setNota5(Double nota5) {
        this.nota5 = nota5;
    }

    public Double getNota6() {
        return nota6;
    }

    public void setNota6(Double nota6) {
        this.nota6 = nota6;
    }

    public Double getNota7() {
        return nota7;
    }

    public void setNota7(Double nota7) {
        this.nota7 = nota7;
    }

    public Double getNota8() {
        return nota8;
    }

    public void setNota8(Double nota8) {
        this.nota8 = nota8;
    }

    public Double getNota9() {
        return nota9;
    }

    public void setNota9(Double nota9) {
        this.nota9 = nota9;
    }

    public Double getNota10() {
        return nota10;
    }

    public void setNota10(Double nota10) {
        this.nota10 = nota10;
    }

    public Double getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(Double recuperacion) {
        this.recuperacion = recuperacion;
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public String getEstadoNota() {
        return estadoNota;
    }

    public void setEstadoNota(String estadoNota) {
        this.estadoNota = estadoNota;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    @Override
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
