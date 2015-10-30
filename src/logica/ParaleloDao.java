/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class ParaleloDao {

    public ResultSet cargaParalelo() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from paralelo";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet cargaParalelo(Integer idParalelo) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from paralelo where id1_paralelo" + "=" + "'" + idParalelo + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Integer cargaCodigo() {
        int co = 0;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
        } catch (Exception e) {
        }
        return null;
    }public Integer codigoMax(){
        int co=0;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT MAX(id1_paralelo)AS codigo FROM paralelo";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                co=Integer.valueOf(rs.getString("codigo"));
            }
            return co;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al cargar codigo"+e.getMessage());
        }
        return null;
    }
    

}
