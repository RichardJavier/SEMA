
package tablas;


public class DatosNota {
   private int idnota; 
   private String cedula ;
   private String nombre;
   private String semestre;
   private String especialidad;

   

    public DatosNota() {
    }

    public DatosNota(String cedula, String semestre, String especialidad) {
        this.cedula = cedula;
        this.semestre = semestre;
        this.especialidad = especialidad;
    }

    public DatosNota(int idnota, String cedula, String nombre, String semestre, String especialidad) {
        this.idnota = idnota;
        this.cedula = cedula;
        this.nombre = nombre;
        this.semestre = semestre;
        this.especialidad = especialidad;
    }
 
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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
     public int getIdnota() {
        return idnota;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
