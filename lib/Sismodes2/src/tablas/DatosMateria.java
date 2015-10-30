
package tablas;

import java.util.Date;


public class DatosMateria extends DatosProfesor {
    private Integer pkMateria;
    private String materia ;
    private Integer creditos;
    private String activacion ;
    private String idSemestre;
    private String idEspecialidad;
    private String idEje ;
    private String idCiclo;
    private Date fechaCreacion;
    private String profesor;

    public DatosMateria() {
    }

    public DatosMateria(Integer pkMateria, String materia, Integer creditos, String activacion, String idSemestre, String idEspecialidad, String idEje, String idCiclo, Date fechaCreacion, String profesor) {
        this.pkMateria = pkMateria;
        this.materia = materia;
        this.creditos = creditos;
        this.activacion = activacion;
        this.idSemestre = idSemestre;
        this.idEspecialidad = idEspecialidad;
        this.idEje = idEje;
        this.idCiclo = idCiclo;
        this.fechaCreacion = fechaCreacion;
        this.profesor = profesor;
    }

    public Integer getPkMateria() {
        return pkMateria;
    }

    public void setPkMateria(Integer pkMateria) {
        this.pkMateria = pkMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getActivacion() {
        return activacion;
    }

    public void setActivacion(String activacion) {
        this.activacion = activacion;
    }

    public String getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(String idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getIdEje() {
        return idEje;
    }

    public void setIdEje(String idEje) {
        this.idEje = idEje;
    }

    public String getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(String idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

   
   

    
}
