
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Periodo;


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
    public ResultSet cargacomboParalelo(){
        try {
            cc=Conexion.getInstance();
            cn=cc.Conectar();
            String sql ="SELECT * FROM paralelo order by id1_paralelo  ";
            Statement st = cn.createStatement();
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
        }
    return null;
    }
    public Periodo codigoPeriodoActivo() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            Periodo periodo = new Periodo();
            String sql = "SELECT * FROM periodo_semestre  WHERE matricula='1' ORDER BY id_periodo DESC LIMIT 0,1 ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                periodo.setCodigoPeriodo(resultado.getString("id_periodo"));
                periodo.setIdPeriodo(Integer.parseInt(resultado.getString("id1_periodo")));

            }
            return periodo;

        } catch (SQLException | NumberFormatException | NullPointerException e) {

            System.out.println("Error en la consulta  codigo" + e);

        }
        return null;
    }

}
