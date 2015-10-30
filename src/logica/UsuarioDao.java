/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.Usuario;

/**
 *
 * @author Ricardo.Bravo
 */
public class UsuarioDao {

    public ResultSet listarUsuarios() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from usuario";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return null;
    }

    public ResultSet cargaUsuario(Integer idUsuario) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from usuario where idusuario" + "=" + "'" + idUsuario + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return null;
    }

    public static Usuario login(String usuario) {
        try {
            Usuario usu = new Usuario();
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from usuario where usuario " + "=" + "'" + usuario + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                usu.setDocumento(rs.getString("documento"));
                usu.setNombre(rs.getString("nombre"));
                usu.setFechaIngreso(rs.getDate("fecha_ingreso"));
                usu.setUsuario(rs.getString("usuario"));
                usu.setClave(rs.getString("clave"));
                usu.setPerfil(rs.getString("perfil"));
                usu.setEstado(rs.getString("estado"));
            }
            return usu;
        } catch (Exception e) {
        }
        return null;
    }
}
