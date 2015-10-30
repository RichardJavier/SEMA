package tablas;

public class DatosResumen {

    private int idresumen;
    private String cedula;
    private String nombres;
    private Double proPonNota;
    private Double porNota;
    private Double notaTuto;
    private Double porTuto;
    private Double notaFinal;    
    private String estado;
    private String asistencia;

    public DatosResumen() {
    }

    public DatosResumen( Double proPonNota, Double porNota, Double notaTuto, Double porTuto, Double notaFinal, String estado, String asistencia) {
        this.proPonNota = proPonNota;
        this.porNota = porNota;
        this.notaTuto = notaTuto;
        this.porTuto = porTuto;
        this.notaFinal = notaFinal;
        this.estado = estado;
        this.asistencia = asistencia;
    }
    
    
    public DatosResumen(int idresumen, String cedula, String nombres, double notaFinal, String estado) {
        this.idresumen = idresumen;
        this.cedula = cedula;
        this.nombres = nombres;
        this.notaFinal = notaFinal;
        this.estado = estado;
    }

    public DatosResumen(String cedula, String nombres, double notaFinal, String estado, String asistencia) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.notaFinal = notaFinal;
        this.estado = estado;
        this.asistencia = asistencia;
    }

    public int getIdresumen() {
        return idresumen;
    }

    public void setIdresumen(int idresumen) {
        this.idresumen = idresumen;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
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

    /**
     * @return the proPonNota
     */
    public Double getProPonNota() {
        return proPonNota;
    }

    /**
     * @param proPonNota the proPonNota to set
     */
    public void setProPonNota(Double proPonNota) {
        this.proPonNota = proPonNota;
    }

    /**
     * @return the porNota
     */
    public Double getPorNota() {
        return porNota;
    }

    /**
     * @param porNota the porNota to set
     */
    public void setPorNota(Double porNota) {
        this.porNota = porNota;
    }

    /**
     * @return the notaTuto
     */
    public Double getNotaTuto() {
        return notaTuto;
    }

    /**
     * @param notaTuto the notaTuto to set
     */
    public void setNotaTuto(Double notaTuto) {
        this.notaTuto = notaTuto;
    }

    /**
     * @return the porTuto
     */
    public Double getPorTuto() {
        return porTuto;
    }

    /**
     * @param porTuto the porTuto to set
     */
    public void setPorTuto(Double porTuto) {
        this.porTuto = porTuto;
    }

}
