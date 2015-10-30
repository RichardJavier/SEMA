
package tablas;

import java.util.Date;



public class DatosProfesor {
   private String cedProfe ;
   private String nomProfe ;
   private String apellProfe ;
   private Date fechaNacimiento;
   private Date fechaIngreso;
   private String titulo;
   private String asignacion;
   private String funcion;
   private Double id1Profe;

    public DatosProfesor() {
    }

    public DatosProfesor(String nomProfe, String apellProfe) {
        this.nomProfe = nomProfe;
        this.apellProfe = apellProfe;
    }

    public DatosProfesor(String cedProfe, String nomProfe, String apellProfe,Date fechaNacimiento, Date fechaIngreso, String titulo, String asignacion, String funcion, Double id1Profe) {
        this.cedProfe = cedProfe;
        this.nomProfe = nomProfe;
        this.apellProfe = apellProfe;
        this.fechaNacimiento=fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.titulo = titulo;
        this.asignacion = asignacion;
        this.funcion = funcion;
        this.id1Profe = id1Profe;
    }

    public String getCedProfe() {
        return cedProfe;
    }

    public void setCedProfe(String cedProfe) {
        this.cedProfe = cedProfe;
    }

    public String getNomProfe() {
        return nomProfe;
    }

    public void setNomProfe(String nomProfe) {
        this.nomProfe = nomProfe;
    }

    public String getApellProfe() {
        return apellProfe;
    }

    public void setApellProfe(String apellProfe) {
        this.apellProfe = apellProfe;
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

    public String getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(String asignacion) {
        this.asignacion = asignacion;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public Double getId1Profe() {
        return id1Profe;
    }

    public void setId1Profe(Double id1Profe) {
        this.id1Profe = id1Profe;
    }
   
}
