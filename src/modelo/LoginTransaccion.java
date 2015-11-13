
package modelo;

/**
 *
 * @author USER
 */
public class LoginTransaccion {
   private Integer idTransaccion;
   private String ip;
   private String maquina;
   private String accion;
   private String tabla;
   private String usuario;
   private String fecha;
   private String registro;

    public LoginTransaccion() {
    }

    public LoginTransaccion(Integer idTransaccion, String ip, String maquina, String accion, String tabla, String usuario, String fecha, String registro) {
        this.idTransaccion = idTransaccion;
        this.ip = ip;
        this.maquina = maquina;
        this.accion = accion;
        this.tabla = tabla;
        this.usuario = usuario;
        this.fecha = fecha;
        this.registro = registro;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
   
}
