/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

public class ReporteNota {

    private String promedio;
    private String porNota;
    private String notaTuto;
    private String porTuto;
    private String notaFinal;
    private String estado;
    private String asistencia;

    public ReporteNota() {
    }

    public ReporteNota(String promedio, String porNota, String notaTuto, String porTuto, String notaFinal, String estado, String asistencia) {
        this.promedio = promedio;
        this.porNota = porNota;
        this.notaTuto = notaTuto;
        this.porTuto = porTuto;
        this.notaFinal = notaFinal;
        this.estado = estado;
        this.asistencia = asistencia;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getPorNota() {
        return porNota;
    }

    public void setPorNota(String porNota) {
        this.porNota = porNota;
    }

    public String getNotaTuto() {
        return notaTuto;
    }

    public void setNotaTuto(String notaTuto) {
        this.notaTuto = notaTuto;
    }

    public String getPorTuto() {
        return porTuto;
    }

    public void setPorTuto(String porTuto) {
        this.porTuto = porTuto;
    }

    public String getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }
    
}
