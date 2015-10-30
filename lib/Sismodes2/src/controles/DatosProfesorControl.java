package controles;

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
import metodos.Crud;

public class DatosProfesorControl implements Crud {

    @Override
    public synchronized ResultSet consulta(Double pkDato) {

        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT ced_profe,nom_profe,apell_profe,IF(fnac_profe IS NULL OR fnac_profe = '', '1970-01-01 00:00:00', fnac_profe)as fnac_profe, "
                    + "IF(fec_ing IS NULL OR fec_ing = '', '1970-01-01 00:00:00', fec_ing)as fec_ing,titulo, "
                    + "IF(tipo IS NULL OR tipo ='','vacio',tipo)AS tipo, "
                    + "IF(funcion IS NULL OR funcion='','vacio',funcion)AS funcion, "
                    + "id1_profe FROM datos_profesor  ";
            if (pkDato > 0) {
                sql += "WHERE id1_profe=" + pkDato;
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return null;

    }

    @Override
    public synchronized Double insertar(String tabla, Map datos) {
        try {


            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();

            // cargar la tabla y los campos para su ingreso 
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

            // sql para la insercion de datos con em map de la tabla y valores 
            sql = "insert into " + tabla + "("
                    + campos.toString().substring(0, campos.toString().length() - 1)
                    + ")values ("
                    + valores.toString().substring(0, valores.toString().length() - 1)
                    + ")";
            double registrosAfectados = st.executeUpdate(sql);
           JOptionPane.showMessageDialog(null, "Registros ingresados Correctamente");
            System.out.println("Registros afectados:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;
        } catch (NullPointerException | SQLException  e) {
            System.out.println("Error en la insercion" + e);
            JOptionPane.showMessageDialog(null, "Error en la insercion:" + e);
            Logger.getLogger(DatosProfesorControl.class.getName()).log(Level.SEVERE, null, e);
        } 
        return 0.0;

    }

    @Override
        public synchronized Double actualizar(String tabla, String pkTabla, Double pkDato, Map datos) {
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
                System.out.println(sql);
                Statement st = cn.createStatement();
                double registrosAfectados = st.executeUpdate(sql);
                System.out.println("Registros afectados: " + registrosAfectados + " registros");
                JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
                cc.desconectar();
                return registrosAfectados;
            } catch (SQLException | HeadlessException e) {
                System.out.println("Error en la actualizacion: " + e.toString());
                JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());
            }
            return 0.0;
        }

    @Override
    public synchronized Double borrar(Double pkDato) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "DELETE FROM datos_profesor WHERE id1_profe = '" + pkDato + "'";
            double registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados: " + registrosAfectados + " registros");
            JOptionPane.showMessageDialog(null, "Registros Borrados Correctamente");
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en el borrado:" + e.toString());
            JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());

        }

        return 0.0;
    }

    @Override
    public synchronized ResultSet consultaOrdenada(Double pkDato) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT ced_profe,nom_profe,apell_profe,IF(fnac_profe IS NULL OR fnac_profe = '', '1970-01-01 00:00:00', fnac_profe)as fnac_profe,"
                    + "IF(fec_ing IS NULL OR fec_ing = '', '0000-00-00 00:00:00', fec_ing)as fec_ing,titulo,tipo,funcion,id1_profe FROM datos_profesor ORDER BY apell_profe ";

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
