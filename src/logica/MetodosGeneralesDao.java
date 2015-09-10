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
            int i = 0;
            int asistencia = 0;
            while (rs.next()) {
                nota = new Nota();
                promedio = new Promedio();
                nota.setPromedio(new BigDecimal(Double.valueOf(rs.getString("promedio"))));
                nota.setAsistencia(Integer.parseInt(rs.getString("asistencia")));
                materia = new Materia();
                materia.setNombreMateria(rs.getString("materia"));
                materia.setCreditos(Integer.valueOf(rs.getString("creditos")));
                malla.setCreditoTeoria(Integer.valueOf(rs.getString("cred_teorica")));
                malla.setPorcentajePonderacionNota(Integer.parseInt(rs.getString("porc_ponderado_nota")));
                malla.setPorcentajeNotaTeorica(Integer.valueOf(rs.getString("porc_nota_teorica")));
                malla.setPorcentajeNotaEmpresa(Integer.valueOf(rs.getString("porc_nota_empresa")));
                porTemp = (double) ((materia.getCreditos() * 100) / (double) malla.getCreditoTeoria());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (!materia.getNombreMateria().contains("EMPRESA")) {
                    listaPrimedio.add(promedio);
                    i++;
                }else {
                  resumen.setNotaEmpresa(nota.getPromedio());
                }
            }

            BigDecimal vp = new BigDecimal(BigInteger.ZERO);
            for (Promedio listaPrimedio1 : listaPrimedio) {
                //   System.out.println(listaPrimedio1.getPorcentaje().toString() + "promedio" + listaPrimedio1.getPromedio());
                vp = vp.add(listaPrimedio1.getPromedio());
                vp.setScale(2, RoundingMode.HALF_UP);
                resumen.setPromedioPonderadoNota(vp);

            }
            //asistencia calculada
            resumen.setAsistencia(asistencia / i);
            
            //setear
            System.out.println(resumen.getAsistencia());
            //variables de calculo
            BigDecimal nt, pnt,pnem;
            //promedio ponderada de nota calculado ya 
            System.out.println(resumen.getPromedioPonderadoNota());
            //calculo nota total teorica       
            nt = new BigDecimal(malla.getPorcentajePonderacionNota()).divide(new BigDecimal(100));
            nt = nt.multiply(resumen.getPromedioPonderadoNota());
            nt = nt.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaTotalTeorica(nt);
            //setear
            System.out.println(resumen.getNotaTotalTeorica().toString());
            //calculo de porcentaje de la nota empresa
            pnt = new BigDecimal(malla.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
            pnt = resumen.getNotaTotalTeorica().multiply(pnt);
            pnt = pnt.setScale(2, RoundingMode.HALF_UP);
            pnem=new BigDecimal(malla.getPorcentajeNotaEmpresa()).divide(new BigDecimal(100));
            pnem=pnem.multiply(resumen.getNotaEmpresa());
            pnem=pnem.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaEmpresa(pnem);
            //setear 
            System.out.println(resumen.getNotaEmpresa());
            //nota final de calculada
            resumen.setNotaFinal(pnt.add(pnem));
            System.out.println(resumen.getNotaFinal());
            
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }

    }
}
