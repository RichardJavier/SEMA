package metodos;

import conectar.Conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CargarDatosNotas {

    private String nombreMateria, cedula, nombreSemestre;
    Conexion cc;
    Connection cn;

    public Integer insertarAlumno(String tabla, Map datos) {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql;
            StringBuilder campos = new StringBuilder();
            StringBuilder valores = new StringBuilder();

            for (Iterator it = datos.keySet().iterator(); it.hasNext();) {
                String llave = (String) it.next();
                campos.append(llave).append(",");
                if (datos.get(llave) instanceof Date) {
                    valores.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format((Date) datos.get(llave))).append("',");
                } else {
                    valores.append("'").append(datos.get(llave).toString()).append("',");
                }

            }

            sql = "insert into " + tabla + "("
                    + campos.toString().substring(0, campos.toString().length() - 1)
                    + ")values ("
                    + valores.toString().substring(0, valores.toString().length() - 1)
                    + ")";
            System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            Logger.getLogger(CargarDatosNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String cargarNombreMateria(int idmateria) {
        cc = Conexion.getInstance();
        cn = cc.Conectar();
        try {

            String sql = "select * from nombre_materia where id1_nombre_materia = " + idmateria + "";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreMateria = rs.getString("materia");
            }
            System.out.println("sql configmaterias" + sql);
            return nombreMateria;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return nombreMateria;
    }

    public String cargarNombreSemestre(int idsemestre) {
        cc = Conexion.getInstance();
        cn = cc.Conectar();
        try {

            String sql = "select * from semestre where id1_semestre = " + idsemestre + "";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreSemestre = rs.getString("semestre");
            }
            System.out.println("sql configmaterias" + sql);
            return nombreSemestre;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return nombreSemestre;
    }

    public ResultSet cargarNotas(String codigo, int idnota) {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM nota_" + codigo + " WHERE id_nota='" + idnota + "'ORDER BY id_nota ASC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("sql resumen" + sql);
            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public ResultSet configuracionMaterias(int idconfigmaterias) {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM config_materia WHERE id_config_materia= " + idconfigmaterias + " ORDER BY id_config_materia DESC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("sql configmaterias" + sql);
            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public ResultSet cargarNota(String periodo, int idnota) {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM nota_" + periodo + " WHERE id_config_materia= " + idnota + " ORDER BY id_config_materia ASC";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("sql configmaterias" + sql);
            return resultado;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }

    public synchronized int actualizar(String tabla, String codigo, String pkTabla, int pkDato, Map datos) {
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
            String sql = "UPDATE" + "  " + tabla + "_" + codigo + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";
            System.out.println(sql);
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados: " + registrosAfectados + " registros");
            //JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error en la actualizacion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;

    }

    public double proPonderado(int idalumno,String cedula, String periodo, int idespecialidad, int idsemestre) {
        double resultado = 0;
        double valor = 0;
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT pro_ponderado FROM nota_" + periodo + " WHERE  id_alumno "+"="+"'"+idalumno+"'"+" and cedula= " + cedula + " AND id_especialidad=" + idespecialidad + " AND id_semestre =" + idsemestre + " ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resultado = Double.valueOf(rs.getString("pro_ponderado"));
                valor = valor + resultado;

            }
            return valor;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return valor;
    }

     public double notaFinal(String cedula, int idespecialidad, int idsemestre) {
        double resultado = 0;
        double valor = 0;
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT porcentaje_tutoria FROM resumen  WHERE cedula= " + cedula + " AND id_especialidad=" + idespecialidad + " AND id_semestre =" + idsemestre + " ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resultado = Double.valueOf(rs.getString("porcentaje_tutoria"));
                valor = valor + resultado;

            }
            return valor;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return valor;
    }
    public String asistencia(String cedula, String periodo, int idespecialidad, int idsemestre) {
        String estado = "";
        int asistencia=0;
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT por_asistencia FROM nota_" + periodo + " WHERE cedula= " + cedula + " AND id_especialidad=" + idespecialidad + " AND id_semestre =" + idsemestre + " ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asistencia =Integer.parseInt( rs.getString("por_asistencia"));
                if (asistencia < 75 ) {
                    estado = "REPROBADO";
                    break;
                } else {
                estado = "APROBADO";
                }
            }
            return  estado;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta de asistencia","Error",JOptionPane.ERROR_MESSAGE);
                    
        }
        return estado;
    }

    public int porNotaFinal(int idmalla) {

        int valor = 0;
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "select * from malla where id_malla = " + idmalla + "";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                valor = Integer.parseInt(rs.getString("por_nota"));

            }
            return valor;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return valor;
    }

    public synchronized int actualizarResumen(String tabla, String pkTabla, String cedula, int idalumno, int idespecialidad, int idsemestre, Map datos) {
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
            String sql = "UPDATE" + "  " + tabla + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + pkTabla + "=" + "'" + idalumno + "'" + "and cedula " + "=" + "'" + cedula + "'"
                    + "and idespecialidad " + "=" + "'" + idespecialidad + "'" + "and idsemestre " + "= " + "'" + idsemestre + "'" + ";";
            System.out.println(sql);
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados: " + registrosAfectados + " registros");
            //JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());
        }
        return 0;

    }
}
