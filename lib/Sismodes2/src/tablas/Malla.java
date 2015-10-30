/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import java.util.Date;


public class Malla {
    
    private int idmalla;
    private String nombreMalla;
    private String semestre;
    private String especialidad;
    private String periodo;
    private String ciclo;
    private Double porNOta;
    private Double porTuto;
    private Double numHoras;
    private Double porcentaje;
    private Date fechaCreacion;
    private String estado;

   

    public Malla() {
    }

    public Malla(int idmalla, String nombreMalla, String semestre, String especialidad, String periodo, String ciclo, Double porNOta, Double porTuto, Double numHoras, Double porcentaje, Date fechaCreacion, String estado) {
        this.idmalla = idmalla;
        this.nombreMalla = nombreMalla;
        this.semestre = semestre;
        this.especialidad = especialidad;
        this.periodo = periodo;
        this.ciclo = ciclo;
        this.porNOta = porNOta;
        this.porTuto = porTuto;
        this.numHoras = numHoras;
        this.porcentaje = porcentaje;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public int getIdmalla() {
        return idmalla;
    }

    public void setIdmalla(int idmalla) {
        this.idmalla = idmalla;
    }

    public String getNombreMalla() {
        return nombreMalla;
    }

    public void setNombreMalla(String nombreMalla) {
        this.nombreMalla = nombreMalla;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Double getPorNOta() {
        return porNOta;
    }

    public void setPorNOta(Double porNOta) {
        this.porNOta = porNOta;
    }

    public Double getPorTuto() {
        return porTuto;
    }

    public void setPorTuto(Double porTuto) {
        this.porTuto = porTuto;
    }

    public Double getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(Double numHoras) {
        this.numHoras = numHoras;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return  nombreMalla ;
    } 
    
}
