
package modelo;

import java.util.Date;
import java.util.Objects;

public class Profesor {
  private Integer idProfesor;
  private String cedula;
  private String nombreProfesor;
  private String apellidoProfesor;
  private Date fechaNacimiento ;
  private Date fechaIngreso;
  private String titulo;
  private String categoria;
  private String magisterio;
  private String tipoProfesor;
  private String funcion;

    public Profesor() {
    }

    public Profesor(Integer idProfesor, String cedula, String nombreProfesor, String apellidoProfesor, Date fechaNacimiento, Date fechaIngreso, String titulo, String categoria, String magisterio, String tipoProfesor, String funcion) {
        this.idProfesor = idProfesor;
        this.cedula = cedula;
        this.nombreProfesor = nombreProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.titulo = titulo;
        this.categoria = categoria;
        this.magisterio = magisterio;
        this.tipoProfesor = tipoProfesor;
        this.funcion = funcion;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMagisterio() {
        return magisterio;
    }

    public void setMagisterio(String magisterio) {
        this.magisterio = magisterio;
    }

    public String getTipoProfesor() {
        return tipoProfesor;
    }

    public void setTipoProfesor(String tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    @Override
    public String toString() {
        return nombreProfesor + " " + apellidoProfesor ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.idProfesor);
        hash = 73 * hash + Objects.hashCode(this.nombreProfesor);
        hash = 73 * hash + Objects.hashCode(this.apellidoProfesor);
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.idProfesor, other.idProfesor)) {
            return false;
        }
        if (!Objects.equals(this.nombreProfesor, other.nombreProfesor)) {
            return false;
        }
        if (!Objects.equals(this.apellidoProfesor, other.apellidoProfesor)) {
            return false;
        }
        return true;
    }

 

   
  
}
