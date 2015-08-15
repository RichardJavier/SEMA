package control;

import conectar.Conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

public class Crud {

    public synchronized Integer insertar(String tabla, Map datos) {
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
            System.out.println(sql);
            int registrosAfectados = st.executeUpdate(sql);            
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta"+ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);         
            System.out.println(ex);
        }
        return 0;
    }

    public synchronized Integer insertarM(String tabla, Map datos) {
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
            
            int registrosAfectados = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registros ingresados");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta", "Error", JOptionPane.ERROR_MESSAGE);         
            System.out.println(ex);
        }
        return 0;
    }


    public synchronized Integer actualizarWeb(String tabla, String pkTabla, String pkDato, Map datos) {
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
                    + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error en la actualizacion de los datos de la WEB","Error",JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    public synchronized Integer actualizar(String tabla, String pkTabla, Integer pkDato, Map datos) {
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
                        + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";
                
                Statement st = cn.createStatement();
                int  registrosAfectados = st.executeUpdate(sql);                
                JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
                cc.desconectar();
                return registrosAfectados;
            } catch (SQLException | HeadlessException e) {
                System.out.println("Error en la actualizacion: " + e.toString());
                JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());
            }
            return 0;
        }
}
