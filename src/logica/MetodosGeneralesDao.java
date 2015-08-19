
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MetodosGeneralesDao {
    Conexion cc ;
    Connection cn ;
    
    public ResultSet cargaComboEspecialidad(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM especialidad";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
        return  null;
    }
    public ResultSet cargaComboPeriodo(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM periodo_semestre where matricula = '1'";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
        return  null;
    }
    public ResultSet cargaComboSemestre(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM semestre";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
        return  null;
    }
    public ResultSet cargaComboMalla(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM malla";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
        return  null;
    }
    public ResultSet cargaComboEje(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM ejes ";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
        return  null;
    } 
    public ResultSet cargaComboProfesor(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM datos_profesor where estado != 'D' ORDER BY id1_profe  ";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
        return  null;
    } 
    
}
