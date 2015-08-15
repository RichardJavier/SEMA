package modelo;

public class Campo {

    private String llave;
    private Integer objeto;
    private String objeto1;

    public Campo(String llave, Integer objeto) {
        this.llave = llave;
        this.objeto = objeto;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public Integer getObjeto() {
        return objeto;
    }

    public void setObjeto(Integer objeto) {
        this.objeto = objeto;
    }

    public String getObjeto1() {
        return objeto1;
    }

    public void setObjeto1(String objeto1) {
        this.objeto1 = objeto1;
    }

    public Campo() {
    }

}
