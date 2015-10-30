
package tablas;
public class Materia {
    private int idMateria;
    private int idProfe;
    private String creditos;
    private int idconfigmateria;
    private int numHoras;
    private int ponderacion;
    private int idmalla;
    
    public Materia() {
    }
     public Materia(int numHoras) {
        this.numHoras = numHoras;
    }

    public Materia(int idMateria, int idProfe, String creditos) {
        this.idMateria = idMateria;
        this.idProfe = idProfe;
        this.creditos = creditos;
    }
    public Materia(int idMateria, int idProfe, String creditos, int idconfigmateria,int ponderacion,int idmalla) {
        this.idMateria = idMateria;
        this.idProfe = idProfe;
        this.creditos = creditos;
        this.idconfigmateria = idconfigmateria;
        this.ponderacion = ponderacion;
        this.idmalla = idmalla;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdProfe() {
        return idProfe;
    }

    public void setIdProfe(int idProfe) {
        this.idProfe = idProfe;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public int getIdconfigmateria() {
        return idconfigmateria;
    }

    public void setIdconfigmateria(int idconfigmateria) {
        this.idconfigmateria = idconfigmateria;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    public int getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public int getIdmalla() {
        return idmalla;
    }

    public void setIdmalla(int idmalla) {
        this.idmalla = idmalla;
    }
    
}
