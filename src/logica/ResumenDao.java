package logica;

import conectar.Conexion;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Estado;
import modelo.Malla;
import modelo.Materia;
import modelo.Nota;
import modelo.Promedio;
import modelo.Resumen;

public class ResumenDao {

    public ResultSet cargarResumen(String cedula) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from resumen as r "
                    + "left  join matricula as m  "
                    + "on r.id_matricula = m.id_matricula "
                    + "left join semestre as s "
                    + "on r.id_semestre=s.id1_semestre "
                    + "left join especialidad as e  "
                    + "on r.id_especialidad = e.id1_especialidad "
                    + "where r.cedula" + "=" + "'" + cedula + "'";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public ResultSet cargarValores(Integer idResumen) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from resumen "
                         + "where id_resumen" + "=" + "'" + idResumen + "'";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error al cargar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public synchronized Integer actualizarResumen(String cedula, int idPeriodo, int idSemestre, int idEspecialidad, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            StringBuilder campos = new StringBuilder();
            StringBuilder coma = new StringBuilder();
            for (Iterator it = datos.keySet().iterator(); it.hasNext();) {
                String llave = (String) it.next();
                campos.append(llave).append("=");
                if (datos.get(llave) instanceof Date) {
                    campos.append("'").append(new java.sql.Date(((Date) datos.get(llave)).getTime()).toString()).append("',");
                } else {
                    campos.append("'").append(datos.get(llave).toString()).append("',");
                }
            }
            String sql = "UPDATE" + "  " + "resumen " + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + "cedula " + "=" + "'" + cedula + "'" + " and id_periodo " + "=" + "'" + idPeriodo + "'"
                    + " and id_semestre " + "=" + "'" + idSemestre + "'" + " and id_especialidad " + "=" + "'" + idEspecialidad + "'" + ";";
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error en la actualizacion de los datos de la WEB", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public void calculaResumen(String codigoPeriodo, String cedula, Integer idmalla, Integer idPeriodo, Integer idSemestre, Integer idEspecialidad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from nota_" + codigoPeriodo + " as n "
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
                malla.setValorMinimoPromedio(Double.valueOf(rs.getString("valor_min_promedio")));
                porTemp = (double) ((materia.getCreditos() * 100) / (double) malla.getCreditoTeoria());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (!materia.getNombreMateria().contains("EMPRESA")) {
                    listaPrimedio.add(promedio);
                    i++;
                } else {
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
            // System.out.println(resumen.getAsistencia());
            //variables de calculo
            BigDecimal nt, pnt, pne;
            //promedio ponderada de nota calculado ya 
            // System.out.println(resumen.getPromedioPonderadoNota());
            //calculo nota total teorica       
            nt = new BigDecimal(malla.getPorcentajePonderacionNota()).divide(new BigDecimal(100));
            nt = nt.multiply(resumen.getPromedioPonderadoNota());
            nt = nt.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaTotalTeorica(nt);
            //setear
            // System.out.println(resumen.getNotaTotalTeorica().toString());
            //calculo de porcentaje de la nota empresa
            pnt = new BigDecimal(malla.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
            pnt = resumen.getNotaTotalTeorica().multiply(pnt);
            pnt = pnt.setScale(2, RoundingMode.HALF_UP);
            pne = new BigDecimal(malla.getPorcentajeNotaEmpresa()).divide(new BigDecimal(100));
            pne = pne.multiply(resumen.getNotaEmpresa());
            pne = pne.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaEmpresa(pne);
            //setear 
            // System.out.println(resumen.getNotaEmpresa());
            //nota final de calculada
            resumen.setNotaFinal(pnt.add(pne));
            //System.out.println(resumen.getNotaFinal());
            if (resumen.getNotaFinal().compareTo(new BigDecimal(malla.getValorMinimoPromedio())) > 0) {
                resumen.setAprobacion(Estado.APRUEBA.name());
            } else {
                resumen.setAprobacion(Estado.PIERDE.name());
            }
            Map campos = new HashMap();
            campos.put("nota_empresa", resumen.getNotaEmpresa());
            campos.put("pro_ponderado_nota", resumen.getPromedioPonderadoNota());
            campos.put("nota_total_teorica", resumen.getNotaTotalTeorica());
            campos.put("asistencia", resumen.getAsistencia());
            campos.put("nota_final", resumen.getNotaFinal());
            campos.put("aprobacion", resumen.getAprobacion());
            actualizarResumen(cedula, idPeriodo, idSemestre, idEspecialidad, campos);
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }

    }
}
