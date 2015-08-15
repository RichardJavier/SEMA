
package modelo;

public class DescripcionMateria extends ConfiguracionMateria{
   private Integer idDescripcionMateria;
   private String descripcion1;
   private String descripcion2;
   private String descripcion3;
   private String descripcion4;
   private String descripcion5;
   private String descripcion6;
   private String descripcion7;
   private String descripcion8;
   private String descripcion9;
   private String descripcion10;

    public DescripcionMateria() {
    }

    public DescripcionMateria(Integer idDescripcionMateria, String descripcion1, String descripcion2, String descripcion3, String descripcion4, String descripcion5, String descripcion6, String descripcion7, String descripcion8, String descripcion9, String descripcion10, Integer idConfiguracionMateria) {
        super(idConfiguracionMateria);
        this.idDescripcionMateria = idDescripcionMateria;
        this.descripcion1 = descripcion1;
        this.descripcion2 = descripcion2;
        this.descripcion3 = descripcion3;
        this.descripcion4 = descripcion4;
        this.descripcion5 = descripcion5;
        this.descripcion6 = descripcion6;
        this.descripcion7 = descripcion7;
        this.descripcion8 = descripcion8;
        this.descripcion9 = descripcion9;
        this.descripcion10 = descripcion10;
    }

    public Integer getIdDescripcionMateria() {
        return idDescripcionMateria;
    }

    public void setIdDescripcionMateria(Integer idDescripcionMateria) {
        this.idDescripcionMateria = idDescripcionMateria;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }

    public String getDescripcion4() {
        return descripcion4;
    }

    public void setDescripcion4(String descripcion4) {
        this.descripcion4 = descripcion4;
    }

    public String getDescripcion5() {
        return descripcion5;
    }

    public void setDescripcion5(String descripcion5) {
        this.descripcion5 = descripcion5;
    }

    public String getDescripcion6() {
        return descripcion6;
    }

    public void setDescripcion6(String descripcion6) {
        this.descripcion6 = descripcion6;
    }

    public String getDescripcion7() {
        return descripcion7;
    }

    public void setDescripcion7(String descripcion7) {
        this.descripcion7 = descripcion7;
    }

    public String getDescripcion8() {
        return descripcion8;
    }

    public void setDescripcion8(String descripcion8) {
        this.descripcion8 = descripcion8;
    }

    public String getDescripcion9() {
        return descripcion9;
    }

    public void setDescripcion9(String descripcion9) {
        this.descripcion9 = descripcion9;
    }

    public String getDescripcion10() {
        return descripcion10;
    }

    public void setDescripcion10(String descripcion10) {
        this.descripcion10 = descripcion10;
    }
   
}
