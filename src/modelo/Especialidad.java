package modelo;

import java.util.Objects;

public class Especialidad {

    private Integer idEspecialidad;
    private String especialidad;
    private String codigoEspecialidad;

    public Especialidad() {
    }

    public Especialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Especialidad(Integer idEspecialidad, String especialidad, String codigoEspecialidad) {
        this.idEspecialidad = idEspecialidad;
        this.especialidad = especialidad;
        this.codigoEspecialidad = codigoEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.idEspecialidad);
        hash = 29 * hash + Objects.hashCode(this.especialidad);
        return hash;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(String codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    @Override
    public String toString() {
        return especialidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Especialidad other = (Especialidad) obj;
        if (!Objects.equals(this.idEspecialidad, other.idEspecialidad)) {
            return false;
        }
        if (!Objects.equals(this.especialidad, other.especialidad)) {
            return false;
        }
        return true;
    }

}
