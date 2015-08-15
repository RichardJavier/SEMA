package modelo;

public class Eje {

    private Integer idEje;
    private String codigoEje;
    private String nombreEje;

    public Eje() {
    }

    public Eje(Integer idEje, String codigoEje, String nombreEje) {
        this.idEje = idEje;
        this.codigoEje = codigoEje;
        this.nombreEje = nombreEje;
    }

    public Integer getIdEje() {
        return idEje;
    }

    public void setIdEje(Integer idEje) {
        this.idEje = idEje;
    }

    public String getCodigoEje() {
        return codigoEje;
    }

    public void setCodigoEje(String codigoEje) {
        this.codigoEje = codigoEje;
    }

    public String getNombreEje() {
        return nombreEje;
    }

    public void setNombreEje(String nombreEje) {
        this.nombreEje = nombreEje;
    }

    @Override
    public String toString() {
        return  nombreEje ;
    }

}
