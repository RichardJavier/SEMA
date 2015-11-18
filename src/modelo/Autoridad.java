package modelo;

import java.util.Date;

public class Autoridad {

    private Integer idAutoridades;
    private String nombre;
    private String cargo;
    private Date fechaCreacion;

    public Autoridad() {
    }

    public Autoridad(Integer idAutoridades, String nombre, String cargo, Date fechaCreacion) {
        this.idAutoridades = idAutoridades;
        this.nombre = nombre;
        this.cargo = cargo;
        this.fechaCreacion = fechaCreacion;
    }
    
    public Integer getIdAutoridades() {
        return idAutoridades;
    }

    public void setIdAutoridades(Integer idAutoridades) {
        this.idAutoridades = idAutoridades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
