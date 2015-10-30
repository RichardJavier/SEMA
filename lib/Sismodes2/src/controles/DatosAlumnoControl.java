package controles;

import conectar.Conexion;
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

public class DatosAlumnoControl {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();

    public synchronized ResultSet llenarTabla(Integer dato) {
        try {
            String sql = "select * from maestro_alumno";

            if (dato > 0) {
                sql += "WHERE id_alumno = " + dato;
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta de los Alumnos  ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }

    public synchronized Integer insertarAlumno(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
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
           // System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
           // System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            Logger.getLogger(DatosAlumnoControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrion un error talvez los datos no se almacenaron correctamente ","Error",JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public synchronized Integer insertarTodo(String tabla, String codigo, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
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

            sql = "insert into " + tabla + "_" + codigo + "("
                    + campos.toString().substring(0, campos.toString().length() - 1)
                    + ")values ("
                    + valores.toString().substring(0, valores.toString().length() - 1)
                    + ")";
          //  System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
           // System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            Logger.getLogger(DatosAlumnoControl.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "Ocurrion un error talvez los datos no se almacenaron correctamente ","Error",JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    
    public synchronized Integer insertarResumen(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
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
         //   System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            Logger.getLogger(DatosAlumnoControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar los valores a la tabla resumen", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    
     public synchronized Integer insertarMatricula(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
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
         //   System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            Logger.getLogger(DatosAlumnoControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar los valores a la tabla resumen", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

}
