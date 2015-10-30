
package tablas;

import java.sql.ResultSet;
import java.util.Map;
import metodos.Crud;


public class DatosEspecialidad {

  private String idEspecial ;
  private String especialidad;
  private  Double id1_especialidad;

    public DatosEspecialidad() {
    }

    public DatosEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

  
    public DatosEspecialidad(String idEspecial, String especialidad, Double id1_especialidad) {
        this.idEspecial = idEspecial;
        this.especialidad = especialidad;
        this.id1_especialidad = id1_especialidad;
    }

    public String getIdEspecial() {
        return idEspecial;
    }

    public void setIdEspecial(String idEsopecial) {
        this.idEspecial = idEsopecial;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Double getId1_especialidad() {
        return id1_especialidad;
    }

    public void setId1_especialidad(Double id1_especialidad) {
        this.id1_especialidad = id1_especialidad;
    }
    
}
