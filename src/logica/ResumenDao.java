package logica;

import conectar.Conexion;
import control.Crud;
import control.EnviaEmail;
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
import modelo.Configuracion;
import modelo.Materia;
import modelo.Nota;

import modelo.Promedio;
import modelo.Resumen;

public class ResumenDao {

    List<Promedio> listaPromedio;

    public ResultSet cargarResumen() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM resumen AS r "
                    + "LEFT JOIN semestre AS s "
                    + "ON r.id_semestre=s.id1_semestre "
                    + "LEFT JOIN especialidad AS e "
                    + "ON r.id_especialidad=e.id1_especialidad "
                    + "ORDER BY id_resumen ASC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar", "Error", JOptionPane.ERROR_MESSAGE);
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    public ResultSet cargarValores(Integer idResumen) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from resumen as r "
                    + "inner join configuracion as c "
                    + "on r.id_configuracion=c.id_configuracion "
                    + "where id_resumen" + "=" + "'" + idResumen + "'";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar", "Error", JOptionPane.ERROR_MESSAGE);
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    public synchronized Integer actualizarResumen(String cedula, int idSemestre, int idEspecialidad, Map datos,String usuario) {
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
                    + "where" + " " + "cedula " + "=" + "'" + cedula + "'"
                    + " and id_semestre " + "=" + "'" + idSemestre + "'" + " and id_especialidad " + "=" + "'" + idEspecialidad + "'" + ";";
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("actualiza","resumen", usuario, datos);
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
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
                resumen.setPorcentajeNotaTeorica(Integer.parseInt(rs.getString("porc_nota_teorica")));
                resumen.setPorcentajeNotaEmpresa(Integer.parseInt(rs.getString("porc_nota_empresa")));
            }
            return resumen;
        } catch (SQLException | NumberFormatException e) {
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    private List<Promedio> cargaMateriasArrastre(Connection cn, String cedula, String codigo, Integer idEspecialidad, Integer idSemestre, Integer idMateria) {
        try {
            String sql = "SELECT * FROM nota_" + codigo + " AS n "
                    + " LEFT JOIN nombre_materia AS m "
                    + " ON n.id_materia=m.id1_nombre_materia "
                    + " LEFT JOIN malla AS ma  "
                    + " ON n.id_malla=ma.id_malla "
                    + " WHERE cedula" + "=" + "'" + cedula + "'" + " "
                    + " AND ma.id1_semestre" + "=" + "'" + idSemestre + "'" + ""
                    + " AND ma.id1_especialidad" + "=" + "'" + idEspecialidad + "'" + ""
                    + "ORDER BY id_nota ASC";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Nota nota;
            Promedio promedio;
            Materia materia;
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
                materia.setIdMateria(Integer.parseInt(rs.getString("id1_nombre_materia")));
                materia.setNombreMateria(rs.getString("materia"));
                materia.setCreditos(Integer.valueOf(rs.getString("creditos")));

                porTemp = (double) ((materia.getCreditos() * 100) / (double) resumen.getNumeroCreditosTeorica());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (materia.getIdMateria().equals(idMateria) && !materia.getNombreMateria().contains("EMPRESA")) {
                    listaPromedio.add(promedio);
                }

                i++;
            }
            return listaPromedio;
        } catch (SQLException | NumberFormatException e) {
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }

        return null;
    }

    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    public void calculaResumenArrastre(String cedula, String codigo, Integer idConfiguracion, Integer idEspecialidad, Integer idSemestre, Integer idMateria,String usuario) {
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDao();
            Configuracion configuracion = configuracionDao.getConfiguracion(idConfiguracion);
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            //buscar materia antes para calculo
            PeriodoDao periodoDao = new PeriodoDao();

            String sql = "SELECT * FROM nota_" + 23 + " AS n "
                    + " LEFT JOIN nombre_materia AS m "
                    + " ON n.id_materia=m.id1_nombre_materia "
                    + " LEFT JOIN configuracion AS c  "
                    + " ON n.id_configuracion=ma.id_configuracion "
                    + " WHERE cedula" + "=" + "'" + cedula + "'" + " "
                    + " AND n.id_configuracion" + "=" + "'" + idConfiguracion + "'" + " "
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
                materia.setIdMateria(Integer.parseInt(rs.getString("id1_nombre_materia")));
                materia.setNombreMateria(rs.getString("materia"));
                materia.setCreditos(Integer.valueOf(rs.getString("creditos")));

                configuracion.setPorcentajePonderacionNota(Integer.parseInt(rs.getString("porc_ponderado_nota")));
                configuracion.setValorMinimoPromedio(Double.valueOf(rs.getString("valor_min_promedio")));
                configuracion.setValorMinimoAsistencia(Integer.parseInt(rs.getString("valor_min_asistencia")));
                configuracion.setPorcentajeTutoriaIntegrada(Integer.parseInt(rs.getString("porc_integrada")));

                porTemp = (double) ((materia.getCreditos() * 100) / (double) resumen.getNumeroCreditosTeorica());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (!materia.getIdMateria().equals(idMateria) && !materia.getNombreMateria().contains("EMPRESA")) {
                    listaPromedio.add(promedio);
                } else if (materia.getNombreMateria().contains("EMPRESA")) {
                    resumen.setNotaEmpresa(nota.getPromedio());
                }
                i++;
            }
            cargaMateriasArrastre(cn, cedula, codigo, idEspecialidad, idSemestre, idMateria);
            BigDecimal vp = new BigDecimal(BigInteger.ZERO);
            for (Promedio listaPrimedio1 : listaPromedio) {
                System.out.println(listaPrimedio1.getPorcentaje().toString() + "promedio" + listaPrimedio1.getPromedio());
                vp = vp.add(listaPrimedio1.getPromedio());
                vp.setScale(2, RoundingMode.HALF_UP);
                resumen.setPromedioPonderadoNota(vp);

            }

            //asistencia calculada
            resumen.setAsistencia(asistencia / i);
            //setear
            // System.out.println(resumen.getAsistencia());
            //variables de calculo
            BigDecimal nt, pnt, pne, ti;
            ti = new BigDecimal(configuracion.getPorcentajeTutoriaIntegrada()).divide(new BigDecimal(100));
            ti = ti.multiply(resumen.getNotaTutoria());
            ti = ti.setScale(2, RoundingMode.HALF_UP);
            //promedio ponderada de nota calculado ya 
            // System.out.println(resumen.getPromedioPonderadoNota());
            //calculo nota total teorica       
            nt = new BigDecimal(configuracion.getPorcentajePonderacionNota()).divide(new BigDecimal(100));
            nt = nt.multiply(resumen.getPromedioPonderadoNota());
            nt = nt.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaTotalTeorica(nt.add(ti));
            //setear
            // System.out.println(resumen.getNotaTotalTeorica().toString());
            //  calculo de porcentaje de la nota empresa
            pnt = new BigDecimal(resumen.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
            pnt = resumen.getNotaTotalTeorica().multiply(pnt);
            pnt = pnt.setScale(2, RoundingMode.HALF_UP);
            pne = new BigDecimal(resumen.getPorcentajeNotaEmpresa()).divide(new BigDecimal(100));
            pne = pne.multiply(resumen.getNotaEmpresa());
            pne = pne.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaEmpresa(pne);
            //setear 
            //  System.out.println(resumen.getNotaEmpresa());
            //nota final de calculada
            resumen.setNotaFinal(pnt.add(pne).setScale(1, RoundingMode.UP));
            // System.out.println(resumen.getNotaFinal());
            if (resumen.getNotaFinal().compareTo(new BigDecimal(configuracion.getValorMinimoPromedio())) >= 0 && resumen.getAsistencia() >= configuracion.getValorMinimoAsistencia()) {
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
            actualizarResumen(cedula, idSemestre, idEspecialidad, campos,usuario);
        } catch (SQLException | NumberFormatException e) {
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
    }

    public void calculaResumenNormal(String cedula, String codigo, Integer idNota, Integer idConfiguracion, Integer idEspecialidad, Integer idSemestre,String usuario) {
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDao();
            Configuracion configuracion = configuracionDao.getConfiguracion(idConfiguracion);
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
//            PeriodoDao periodoDao = new PeriodoDao();
//            Periodo p = periodoDao.codigoPeriodo(malla.getIdPeriodo(), cn);
            String sql = "SELECT * FROM nota_" + codigo + " AS n "
                    + " LEFT JOIN nombre_materia AS m "
                    + " ON n.id_materia=m.id1_nombre_materia "
                    + " LEFT JOIN matricula AS ma  "
                    + " ON n.id_matricula=ma.id_matricula "
                    + " LEFT JOIN configuracion AS c  "
                    + " ON n.id_configuracion=c.id_configuracion "
                    + " WHERE n.cedula" + "=" + "'" + cedula + "'" + " "
                    + " AND n.id_configuracion" + "=" + "'" + idConfiguracion + "'" + " "
                    + " AND ma.id_semestre" + "=" + "'" + idSemestre + "'" + ""
                    + " AND ma.id_especialidad" + "=" + "'" + idEspecialidad + "'" + ""
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

                configuracion.setPorcentajePonderacionNota(Integer.parseInt(rs.getString("porc_pon_nota")));
                configuracion.setValorMinimoPromedio(Double.valueOf(rs.getString("valor_min_promedio")));
                configuracion.setValorMinimoAsistencia(Integer.parseInt(rs.getString("valor_min_asistencia")));
                configuracion.setPorcentajeTutoriaIntegrada(Integer.parseInt(rs.getString("porc_integrada")));

                porTemp = (double) ((materia.getCreditos() * 100) / (double) resumen.getNumeroCreditosTeorica());
                promedio.setPorcentaje(new BigDecimal(porTemp).setScale(0, RoundingMode.HALF_UP));
                promedio.setPromedio(nota.getPromedio().multiply(promedio.getPorcentaje()));
                promedio.setPromedio(promedio.getPromedio().divide(new BigDecimal(100)));
                promedio.setPromedio(promedio.getPromedio().setScale(2, RoundingMode.HALF_UP));
                asistencia = asistencia + nota.getAsistencia();
                if (!materia.getNombreMateria().contains("EMPRESA")) {
                    listaPromedio.add(promedio);

                } else {
                    resumen.setNotaEmpresa(nota.getPromedio());
                }
                i++;
            }
            BigDecimal vp = new BigDecimal(BigInteger.ZERO);
            for (Promedio listaPrimedio1 : listaPromedio) {
                System.out.println(listaPrimedio1.getPorcentaje().toString() + "promedio" + listaPrimedio1.getPromedio());
                vp = vp.add(listaPrimedio1.getPromedio());
                vp.setScale(2, RoundingMode.HALF_UP);
                resumen.setPromedioPonderadoNota(vp);

            }
            //asistencia calculada
            resumen.setAsistencia(asistencia / i);
            //setear
            // System.out.println(resumen.getAsistencia());
            //variables de calculo
            BigDecimal nt, pnt, pne, ti;
            ti = new BigDecimal(configuracion.getPorcentajeTutoriaIntegrada()).divide(new BigDecimal(100));
            ti = ti.multiply(resumen.getNotaTutoria());
            ti = ti.setScale(2, RoundingMode.HALF_UP);
            //promedio ponderada de nota calculado ya 
            // System.out.println(resumen.getPromedioPonderadoNota());
            //calculo nota total teorica       
            nt = new BigDecimal(configuracion.getPorcentajePonderacionNota()).divide(new BigDecimal(100));
            nt = nt.multiply(resumen.getPromedioPonderadoNota());
            nt = nt.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaTotalTeorica(nt.add(ti));
            //setear
            // System.out.println(resumen.getNotaTotalTeorica().toString());
            //  calculo de porcentaje de la nota empresa
            pnt = new BigDecimal(resumen.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
            pnt = resumen.getNotaTotalTeorica().multiply(pnt);
            pnt = pnt.setScale(2, RoundingMode.HALF_UP);
            pne = new BigDecimal(resumen.getPorcentajeNotaEmpresa()).divide(new BigDecimal(100));
            pne = pne.multiply(resumen.getNotaEmpresa());
            pne = pne.setScale(2, RoundingMode.HALF_UP);
            resumen.setNotaEmpresa(pne);
            //setear 
            // System.out.println(resumen.getNotaEmpresa());
            //nota final de calculada
            resumen.setNotaFinal(pnt.add(pne));
            // System.out.println(resumen.getNotaFinal());
            if (resumen.getNotaFinal().compareTo(new BigDecimal(configuracion.getValorMinimoPromedio())) >= 0 && resumen.getAsistencia() >= configuracion.getValorMinimoAsistencia()) {
                resumen.setAprobacion(Estado.APRUEBA.name());
            } else {
                resumen.setAprobacion(Estado.PIERDE.name());
            }
            Map campos = new HashMap();
            campos.put("nota_empresa", resumen.getNotaEmpresa());
            campos.put("pro_ponderado_nota", resumen.getPromedioPonderadoNota());
            campos.put("nota_total_teorica", resumen.getNotaTotalTeorica());
            campos.put("asistencia", resumen.getAsistencia());
            campos.put("aprobacion", resumen.getAprobacion());
            campos.put("nota_final", resumen.getNotaFinal());

            actualizarResumen(cedula, idSemestre, idEspecialidad, campos,usuario);
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Error al calcular nota final","Error",JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
    }

    public ResultSet cargarResumenCursoEspecialidad(Integer idSemestre, Integer idEspecialidad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from resumen as r "
                    + "left join semestre as s "
                    + "on r.id_semestre=s.id1_semestre "
                    + "left join especialidad as e  "
                    + "on r.id_especialidad = e.id1_especialidad "
                    + "where "
                    + "r.id_semestre " + "=" + "'" + idSemestre + "'" + " "
                    + "AND "
                    + "r.id_especialidad " + "=" + "'" + idEspecialidad + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar", "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }
}
