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
import modelo.Periodo;
import modelo.Promedio;
import modelo.Resumen;

public class ResumenDao {
    List<Promedio>listaPromedio;
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
            String sql = "select * from resumen as r "
                    + "inner join malla as m "
                    + "on r.id_malla=m.id_malla "
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

    private Resumen valoresResumen(String cedula, Integer idSemestre, Integer idEspecialidad, Connection cn) {
        try {
            Resumen resumen = new Resumen();
            String sql = "select * from resumen where cedula " + "=" + "'" + cedula + "'" + "and id_semestre" + "=" + "'" + idSemestre + "'"
                    + "and id_especialidad" + "=" + "'" + idEspecialidad + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resumen.setNumeroCreditosCiclo(Integer.parseInt(rs.getString("cred_ciclo")));
                resumen.setNumeroCreditosTeorica(Integer.parseInt(rs.getString("cred_teorica")));
                resumen.setPromedioPonderadoNota(new BigDecimal(rs.getString("pro_ponderado_nota")));
                resumen.setNotaTutoria(new BigDecimal(rs.getString("nota_tutoria")));
                resumen.setNotaTotalTeorica(new BigDecimal(rs.getString("nota_total_teorica")));
                resumen.setNotaEmpresa(new BigDecimal(rs.getString("nota_empresa")));
                resumen.setAsistencia(Integer.parseInt(rs.getString("asistencia")));
            }
            return resumen;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
        return null;
    }

    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    public void calculaResumenArrastre(String cedula, String codigo, Integer idMalla, Integer idEspecialidad, Integer idSemestre, Integer idMateria) {
        try {
            MallaDao mallaDao = new MallaDao();
            Malla malla = mallaDao.getMalla(idMalla);
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            PeriodoDao periodoDao = new PeriodoDao();
            Periodo p = periodoDao.codigoPeriodo(malla.getIdPeriodo(), cn);
            String sql = "SELECT * FROM nota_" + p.getCodigoPeriodo() + " AS n "
                    + " LEFT JOIN nombre_materia AS m "
                    + " ON n.id_materia=m.id1_nombre_materia "
                    + " LEFT JOIN malla AS ma  "
                    + " ON n.id_malla=ma.id_malla "
                    + " WHERE cedula" + "=" + "'" + cedula + "'" + " "
                    + " AND n.id_malla" + "=" + "'" + idMalla + "'" + " "
                    + " AND ma.id1_semestre" + "=" + "'" + idSemestre + "'" + ""
                    + " AND ma.id1_especialidad" + "=" + "'" + idEspecialidad + "'" + ""
                    + "ORDER BY id_nota ASC";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Nota nota;
            Promedio promedio;
            Materia materia;
            listaPromedio = new ArrayList<>();
            Double porTemp;
            Resumen resumen = valoresResumen(cedula, idSemestre, idEspecialidad, cn);
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

                malla.setPorcentajePonderacionNota(Integer.parseInt(rs.getString("porc_ponderado_nota")));
                malla.setValorMinimoPromedio(Double.valueOf(rs.getString("valor_min_promedio")));
                malla.setValorMinimoAsistencia(Integer.parseInt(rs.getString("valor_min_asistencia")));
                malla.setPorcentajeNotaTeorica(Integer.parseInt(rs.getString("porc_nota_teorica")));
                malla.setPorcentajeNotaEmpresa(Integer.parseInt(rs.getString("porc_nota_empresa")));

                porTemp = (double) ((materia.getCreditos() * 100) / (double) resumen.getNumeroCreditosTeorica());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (materia.getIdMateria() != idMateria) {
                    listaPromedio.add(promedio);
                    i++;
                } else if (materia.getNombreMateria().contains("EMPRESA")) {
                    resumen.setNotaEmpresa(nota.getPromedio());
                } else {
                    
                }
            }
           BigDecimal vp = new BigDecimal(BigInteger.ZERO);
            for (Promedio listaPrimedio1 : listaPromedio) {
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
            //  calculo de porcentaje de la nota empresa
            pnt = new BigDecimal(malla.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
            pnt = resumen.getNotaTotalTeorica().multiply(pnt);
            pnt = pnt.setScale(2, RoundingMode.HALF_UP);
            pne = new BigDecimal(malla.getPorcentajeNotaEmpresa()).divide(new BigDecimal(100));
            pne = pne.multiply(resumen.getNotaEmpresa());
            pne = pne.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaEmpresa(pne);
            //setear 
            //  System.out.println(resumen.getNotaEmpresa());
            //nota final de calculada
            resumen.setNotaFinal(pnt.add(pne));
            // System.out.println(resumen.getNotaFinal());
            if (resumen.getNotaFinal().compareTo(new BigDecimal(malla.getValorMinimoPromedio())) >= 0 && resumen.getAsistencia() >= malla.getValorMinimoAsistencia()) {
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
            System.out.println(campos);
            //  actualizarResumen(cedula, p.getIdPeriodo(), idSemestre, idEspecialidad, campos);
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    public void calculaResumenNormal(String cedula, String codigo, Integer idMalla, Integer idEspecialidad, Integer idSemestre) {
        try {
            MallaDao mallaDao = new MallaDao();
            Malla malla = mallaDao.getMalla(idMalla);
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
//            PeriodoDao periodoDao = new PeriodoDao();
//            Periodo p = periodoDao.codigoPeriodo(malla.getIdPeriodo(), cn);
            String sql = "SELECT * FROM nota_" + codigo + " AS n "
                    + " LEFT JOIN nombre_materia AS m "
                    + " ON n.id_materia=m.id1_nombre_materia "
                    + " LEFT JOIN malla AS ma  "
                    + " ON n.id_malla=ma.id_malla "
                    + " WHERE cedula" + "=" + "'" + cedula + "'" + " "
                    + " AND n.id_malla" + "=" + "'" + idMalla + "'" + " "
                    + " AND ma.id1_semestre" + "=" + "'" + idSemestre + "'" + ""
                    + " AND ma.id1_especialidad" + "=" + "'" + idEspecialidad + "'" + ""
                    + "ORDER BY id_nota ASC";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Nota nota;
            Promedio promedio;
            Materia materia;
            listaPromedio = new ArrayList<>();
            Double porTemp;
            Resumen resumen = valoresResumen(cedula, idSemestre, idEspecialidad, cn);
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

                malla.setPorcentajePonderacionNota(Integer.parseInt(rs.getString("porc_ponderado_nota")));
                malla.setValorMinimoPromedio(Double.valueOf(rs.getString("valor_min_promedio")));
                malla.setValorMinimoAsistencia(Integer.parseInt(rs.getString("valor_min_asistencia")));
                malla.setPorcentajeNotaTeorica(Integer.parseInt(rs.getString("porc_nota_teorica")));
                malla.setPorcentajeNotaEmpresa(Integer.parseInt(rs.getString("porc_nota_empresa")));

                porTemp = (double) ((materia.getCreditos() * 100) / (double) resumen.getNumeroCreditosTeorica());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (!materia.getNombreMateria().contains("EMPRESA")) {
                    listaPromedio.add(promedio);
                    i++;
                } else {
                    resumen.setNotaEmpresa(nota.getPromedio());
                }
            }
            BigDecimal vp = new BigDecimal(BigInteger.ZERO);
            for (Promedio listaPrimedio1 : listaPromedio) {
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
            //  calculo de porcentaje de la nota empresa
            pnt = new BigDecimal(malla.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
            pnt = resumen.getNotaTotalTeorica().multiply(pnt);
            pnt = pnt.setScale(2, RoundingMode.HALF_UP);
            pne = new BigDecimal(malla.getPorcentajeNotaEmpresa()).divide(new BigDecimal(100));
            pne = pne.multiply(resumen.getNotaEmpresa());
            pne = pne.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaEmpresa(pne);
            //setear 
            //  System.out.println(resumen.getNotaEmpresa());
            //nota final de calculada
            resumen.setNotaFinal(pnt.add(pne));
            // System.out.println(resumen.getNotaFinal());
            if (resumen.getNotaFinal().compareTo(new BigDecimal(malla.getValorMinimoPromedio())) >= 0 && resumen.getAsistencia() >= malla.getValorMinimoAsistencia()) {
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
            MetodosGeneralesDao metodosGeneralesDao = new MetodosGeneralesDao();
            Periodo p = metodosGeneralesDao.codigoPeriodoActivo();
            actualizarResumen(cedula, p.getIdPeriodo(), idSemestre, idEspecialidad, campos);
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }
}
