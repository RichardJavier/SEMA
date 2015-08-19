package modelo;

import java.util.Date;
import java.util.Objects;

public class Materia extends Malla {

    private Integer idMateria;
    private String nombreMateria;
    private Integer numeroHoras;
    private Integer creditos;
    private String activaMateria;
    private Integer idEspecialidad;
    private String especialidad;
    private Integer idEje;
    private Integer idProfesor;
    private Integer idDescripcion;
    private Integer idConfiguracion;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public Materia() {
    }

    public Materia(Integer idMateria, String nombreMateria, Integer numeroHoras, Integer creditos, String activaMateria, Integer idEspecialidad, Integer idEje, Integer idProfesor, Integer idDescripcion, Date fechaCreacion, Date fechaModificacion, Integer idMalla, String nombreMalla, Integer idSemestre) {
        super(idMalla, nombreMalla, idSemestre);
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.numeroHoras = numeroHoras;
        this.creditos = creditos;
        this.activaMateria = activaMateria;
        this.idEspecialidad = idEspecialidad;
        this.idEje = idEje;
        this.idProfesor = idProfesor;
        this.idDescripcion = idDescripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Materia(Integer idMateria, String nombreMateria, Integer numeroHoras, Integer creditos, String activaMateria, Integer idEspecialidad, Integer idEje, Integer idProfesor,Integer idDescripcion, Date fechaCreacion, Date fechaModificacion, Integer idMalla) {
        super(idMalla);
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.numeroHoras = numeroHoras;
        this.creditos = creditos;
        this.activaMateria = activaMateria;
        this.idEspecialidad = idEspecialidad;
        this.idEje = idEje;
        this.idProfesor = idProfesor;
        this.idDescripcion = idDescripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getActivaMateria() {
        return activaMateria;
    }

    public void setActivaMateria(String activaMateria) {
        this.activaMateria = activaMateria;
    }

    public Integer getIdEje() {
        return idEje;
    }

    public void setIdEje(Integer idEje) {
        this.idEje = idEje;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }
    public Integer getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(Integer idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    public Integer getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }
    @Override
    public String toString() {
        return nombreMateria;
    }
     
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.idEspecialidad);
        hash = 17 * hash + Objects.hashCode(this.idEje);
        hash = 17 * hash + Objects.hashCode(this.idProfesor);
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
        final Materia other = (Materia) obj;
        if (!Objects.equals(this.idEspecialidad, other.idEspecialidad)) {
            return false;
        }
        if (!Objects.equals(this.idEje, other.idEje)) {
            return false;
        }
        if (!Objects.equals(this.idProfesor, other.idProfesor)) {
            return false;
        }
        return true;
    }

}
