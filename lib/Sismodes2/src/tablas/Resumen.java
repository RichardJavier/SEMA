
package tablas;


public class Resumen {
private int idnota ;
private int idalumno ;
private String cedula;

    public Resumen() {
    }

    public Resumen(int idnota) {
        this.idnota = idnota;
    }

    public Resumen(int idnota, int idalumno, String cedula) {
        this.idnota = idnota;
        this.idalumno = idalumno;
        this.cedula = cedula;
    }

    public int getIdnota() {
        return idnota;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
        
}
