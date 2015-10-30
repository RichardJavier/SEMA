package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Periodo;

public class MetodosGeneralesDao {

    Conexion cc;
    Connection cn;

    public ResultSet cargaComboEspecialidad() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM especialidad";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargaComboPeriodo() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM periodo_semestre where matricula = '1'";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargaComboSemestre() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM semestre";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargaComboMalla() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM malla ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargaComboEje() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM ejes ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargaComboProfesor() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM datos_profesor where estado != 'DS' ORDER BY id1_profe  ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargacomboParalelo() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM paralelo order by id1_paralelo  ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
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

    public String codigoPeriodoBusacado() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            Periodo periodo = new Periodo();
            String sql = "SELECT * FROM periodo_semestre  WHERE id_periodo<(SELECT MAX(id_periodo)FROM `periodo_semestre`)ORDER BY id_periodo DESC LIMIT 0,1";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                periodo.setCodigoPeriodo(resultado.getString("id_periodo"));
            }
            return periodo.getCodigoPeriodo();

        } catch (SQLException | NumberFormatException | NullPointerException e) {

            System.out.println("Error en la consulta  codigo" + e);

        }
        return null;
    }

    public ResultSet cargaPeriodo() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "SELECT * from periodo_semestre where id1_periodo > 36";
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
     public synchronized ResultSet cargaMateria() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nombre_materia "
                    + " where activa_mat='AC' ORDER BY id1_nombre_materia DESC ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }

        return null;
    }
}
