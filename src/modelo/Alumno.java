/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;


public class Alumno {
 private String cedula;
 private String apellidoPaterno;
 private String apellidoMaterno;
 private String nombreCompleto;
 private String sexo;
 private String estadoCivil;
 private String paisNacimiento;
 private Date fechaNacimiento;
 private String ciudadNacimiento;
 private String ciudadDomicilio;
 private String direccionDomicilio;
 private String telefonoFijo;
 private String celular;
 private String emailAlumno;
 private String emailAlternativo;
 private String discapacidad;
 private String numeroConadis;
 private String etnia;
 private String nombreColegio;
 private String paisEstudio;
 private String ciudadColegio ;
 private String personaContacto;
 private String parentesco;
 private String numeroFijoFamiliar;
 private String numeroCelularFamiliar;
 private String emailFamiliar;

    public Alumno() {
    }

    public Alumno(String cedula, String apellidoPaterno, String apellidoMaterno, String nombreCompleto, String sexo, String estadoCivil, String paisNacimiento, Date fechaNacimiento, String ciudadNacimiento, String ciudadDomicilio, String direccionDomicilio, String telefonoFijo, String celular, String emailAlumno, String emailAlternativo, String discapacidad, String numeroConadis, String etnia, String nombreColegio, String paisEstudio, String ciudadColegio, String personaContacto, String parentesco, String numeroFijoFamiliar, String numeroCelularFamiliar, String emailFamiliar) {
        this.cedula = cedula;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreCompleto = nombreCompleto;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.paisNacimiento = paisNacimiento;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudadNacimiento = ciudadNacimiento;
        this.ciudadDomicilio = ciudadDomicilio;
        this.direccionDomicilio = direccionDomicilio;
        this.telefonoFijo = telefonoFijo;
        this.celular = celular;
        this.emailAlumno = emailAlumno;
        this.emailAlternativo = emailAlternativo;
        this.discapacidad = discapacidad;
        this.numeroConadis = numeroConadis;
        this.etnia = etnia;
        this.nombreColegio = nombreColegio;
        this.paisEstudio = paisEstudio;
        this.ciudadColegio = ciudadColegio;
        this.personaContacto = personaContacto;
        this.parentesco = parentesco;
        this.numeroFijoFamiliar = numeroFijoFamiliar;
        this.numeroCelularFamiliar = numeroCelularFamiliar;
        this.emailFamiliar = emailFamiliar;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getCiudadDomicilio() {
        return ciudadDomicilio;
    }

    public void setCiudadDomicilio(String ciudadDomicilio) {
        this.ciudadDomicilio = ciudadDomicilio;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmailAlumno() {
        return emailAlumno;
    }

    public void setEmailAlumno(String emailAlumno) {
        this.emailAlumno = emailAlumno;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getNumeroConadis() {
        return numeroConadis;
    }

    public void setNumeroConadis(String numeroConadis) {
        this.numeroConadis = numeroConadis;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getNombreColegio() {
        return nombreColegio;
    }

    public void setNombreColegio(String nombreColegio) {
        this.nombreColegio = nombreColegio;
    }

    public String getPaisEstudio() {
        return paisEstudio;
    }

    public void setPaisEstudio(String paisEstudio) {
        this.paisEstudio = paisEstudio;
    }

    public String getCiudadColegio() {
        return ciudadColegio;
    }

    public void setCiudadColegio(String ciudadColegio) {
        this.ciudadColegio = ciudadColegio;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getNumeroFijoFamiliar() {
        return numeroFijoFamiliar;
    }

    public void setNumeroFijoFamiliar(String numeroFijoFamiliar) {
        this.numeroFijoFamiliar = numeroFijoFamiliar;
    }

    public String getNumeroCelularFamiliar() {
        return numeroCelularFamiliar;
    }

    public void setNumeroCelularFamiliar(String numeroCelularFamiliar) {
        this.numeroCelularFamiliar = numeroCelularFamiliar;
    }

    public String getEmailFamiliar() {
        return emailFamiliar;
    }

    public void setEmailFamiliar(String emailFamiliar) {
        this.emailFamiliar = emailFamiliar;
    }
 
}
