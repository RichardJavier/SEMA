
package tablas;

import java.util.Date;


public class DatosPeriodoSemestre extends DatosCiclo{
   private String codigo;
   private String periodo;
   private Double id1Periodo;
   private Integer matricula;
   private Date fechaFinalizacion;

    public DatosPeriodoSemestre() {
    }

    public DatosPeriodoSemestre(String periodo, Double id1Periodo) {
        this.periodo = periodo;
        this.id1Periodo = id1Periodo;
    }

    

    public DatosPeriodoSemestre(String codigo, String periodo, Double id1Periodo, Integer matricula, Date fechaFinalizacion, String ciclo) {
        super(ciclo);
        this.codigo = codigo;
        this.periodo = periodo;
        this.id1Periodo = id1Periodo;
        this.matricula = matricula;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Double getId1Periodo() {
        return id1Periodo;
    }

    public void setId1Periodo(Double id1Periodo) {
        this.id1Periodo = id1Periodo;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
   
   @Override
    public String toString() {
        return  periodo ;
    }
}
