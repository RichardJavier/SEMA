/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author Ricardo.Bravo
 */
public class LoginAuditoria {
    private Integer idLoginAuditoria;
    private String fechaLogin;
    private Boolean ingreso;
    private String aplicacion;
    private String usuario;

    public Integer getIdLoginAuditoria() {
        return idLoginAuditoria;
    }

    public void setIdLoginAuditoria(Integer idLoginAuditoria) {
        this.idLoginAuditoria = idLoginAuditoria;
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
