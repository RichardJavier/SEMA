/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;



/**
 *
 * @author Ricardo.Bravo
 */
public class LoginAuditoria {
    private Integer idLoginAuditoria;
    private String ip;
    private String maquina;
    private String fechaLogin;
    private Boolean ingreso;
    private String descripcion;
    private String aplicacion;
    private String usuario;

    public Integer getIdLoginAuditoria() {
        return idLoginAuditoria;
    }

    public void setIdLoginAuditoria(Integer idLoginAuditoria) {
        this.idLoginAuditoria = idLoginAuditoria;
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

    public String getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(String fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public Boolean getIngreso() {
        return ingreso;
    }

    public void setIngreso(Boolean ingreso) {
        this.ingreso = ingreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
