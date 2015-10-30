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
 * @author Ricardo.Bravo
 */
public class EjeDao {

    public ResultSet cargaEje() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "select * from ejes";
            ResultSet resul = st.executeQuery(sql);
            return resul;
        } catch (Exception e) {
            System.out.println("Error en la consulta"+e.getMessage());
        }
        return null;
    }
    public Integer codigoMax(){
        int co=0;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT MAX(id1_ejes)AS codigo FROM ejes";
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
     public ResultSet cargaEje(Integer idEje) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "select * from ejes  where id1_ejes "+"="+"'"+idEje+"'"+";";
            ResultSet resul = st.executeQuery(sql);
            return resul;
        } catch (Exception e) {
            System.out.println("Error en la consulta"+e.getMessage());
        }
        return null;
    }
}
