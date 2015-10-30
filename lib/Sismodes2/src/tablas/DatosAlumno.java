
package tablas;

import java.util.Date;


public class DatosAlumno extends DatosEspecialidad{
private Double idAlumno;
private String cedula;
private String apellido;
private String nombre;
private Date fechaNacimiento;
private String sexo;
private String edad;
private String nacionalidad;
private String ciudad;
private String direccion;
private Date fechaInscripcion;


    public DatosAlumno() {
    }

    public DatosAlumno(Double idAlumno, String cedula, String apellido, String nombre, Date fechaNacimiento, String sexo, String edad, String nacionalidad, String ciudad, String direccion, String especialidad,Date fechaInscripcion) {
        super(especialidad);
        this.idAlumno = idAlumno;
        this.cedula = cedula;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Double getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Double idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }


}
