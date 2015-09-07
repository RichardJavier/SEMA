package logica;

import conectar.Conexion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Malla;
import modelo.Materia;
import modelo.Nota;
import modelo.Periodo;
import modelo.Promedio;
import modelo.Resumen;

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
            String sql = "SELECT * FROM malla";
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
            String sql = "SELECT * FROM datos_profesor where estado != 'D' ORDER BY id1_profe  ";
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

    public void calculaResumen(String cedula, Integer idmalla) {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "select * from nota_pe23 as n "
                    + "left join nombre_materia as m "
                    + "on n.id_materia=m.id1_nombre_materia "
                    + "left join malla as ma "
                    + "on n.id_malla=ma.id_malla "
                    + "where cedula" + "=" + "'" + cedula + "'" + "and n.id_malla " + "=" + "'" + idmalla + "'" + " order by id_nota asc;";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Materia materia;
            Malla malla = new Malla();
            Resumen resumen = new Resumen();
            Nota nota;
            Promedio promedio;
            List<Promedio> listaPrimedio = new ArrayList<>();
            Double porTemp;
            while (rs.next()) {
                nota = new Nota();
                promedio = new Promedio();
                nota.setPromedio(new BigDecimal(Double.valueOf(rs.getString("promedio"))));
                materia = new Materia();
                materia.setNombreMateria(rs.getString("materia"));
                materia.setCreditos(Integer.valueOf(rs.getString("creditos")));
                malla.setCreditoTeoria(Integer.valueOf(rs.getString("cred_teorica")));
                porTemp = (double) ((materia.getCreditos() * 100) / (double) malla.getCreditoTeoria());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                if (!materia.getNombreMateria().contains("EMPRESA")) {
                    listaPrimedio.add(promedio);
                }
            }
            BigDecimal vp=new BigDecimal(BigInteger.ZERO);
            for (Promedio listaPrimedio1 : listaPrimedio) {
                System.out.println(listaPrimedio1.getPorcentaje().toString()+"promedio"+listaPrimedio1.getPromedio());
                vp=vp.add(listaPrimedio1.getPromedio());
                vp.setScale(2, RoundingMode.HALF_UP);
                resumen.setPromedioPonderadoNota(vp);
               
            }
             System.out.println(resumen.getPromedioPonderadoNota());
        } catch (SQLException | NumberFormatException e) {
        }

    }
}
