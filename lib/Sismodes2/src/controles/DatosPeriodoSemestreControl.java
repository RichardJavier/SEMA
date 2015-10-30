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

public class DatosPeriodoSemestreControl implements Crud {

    @Override
    public synchronized ResultSet consulta(Double pkDato) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT ps.id_periodo,ps.periodo,ps.id1_periodo,ps.matricula,"
                    + "IF(c.ciclo IS NULL OR c.ciclo = '', 'vacio', c.ciclo)AS ciclo,"
                    + "IF(ps.fecha_fin IS NULL OR ps.fecha_fin = '', '1970-01-01 00:00:00', ps.fecha_fin)AS fecha_fin "
                    + "from periodo_semestre AS ps left join ciclo c on c.id_ciclo =ps.ciclo";
            if (pkDato > 0) {
                sql += " where id1_periodo = " + pkDato;
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
    public Double insertar(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            // cargar la tabla y los campos para su registro
            String sql ;
            StringBuilder campos = new StringBuilder();
            StringBuilder valores =new StringBuilder();
            
            for (Iterator it = datos.keySet().iterator(); it.hasNext();) {
                String llave = (String) it.next();
                campos.append(llave).append(",");
                if (datos.get(llave) instanceof Date) {
                    valores.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format((Date) datos.get(llave))).append("',");
                } else {
                    valores.append("'").append(datos.get(llave).toString()).append("',");
                }

            }
            sql = "insert into " + tabla + "( "
                    +campos.toString().substring(0,campos.toString().length()-1 )
                    +")values ( "
                    + valores.toString().substring(0,valores.toString().length()-1 )
                    +")";
            double registrosAfectados = st.executeUpdate(sql);
            
           // JOptionPane.showMessageDialog(null, "Registros ingresados Correctamente");
            System.out.println("Registros afectados:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;
            
        } catch (Exception e) {
            System.out.println("Error en la insercion" + e);
            JOptionPane.showMessageDialog(null, "Error en la insercion:" + e);
            Logger.getLogger(DatosProfesorControl.class.getName()).log(Level.SEVERE, null, e);
        }
        return  0.0;
    }

    @Override
    public Double actualizar(String tabla, String pkTabla, Double pkDato, Map datos) {
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
    public Double borrar(Double pkPrincipal) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql ="DELETE FROM periodo_semestre WHERE id1_periodo ='"+pkPrincipal+"'";
             double registrosAfectados = st.executeUpdate(sql);
              System.out.println(sql);
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
    public ResultSet consultaOrdenada(Double pkDato) {
         try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT ps.id_periodo,ps.periodo,ps.id1_periodo,ps.matricula,ps.nueva_malla, "
                    + "IF(c.ciclo IS NULL OR c.ciclo = '', 'vacio', c.ciclo)AS ciclo, "
                    + "IF(ps.fecha_fin IS NULL OR ps.fecha_fin = '', '1970-01-01 00:00:00', ps.fecha_fin)AS fecha_fin "
                    + "from periodo_semestre AS ps left join ciclo c on c.id_ciclo =ps.ciclo where id1_periodo = '"+ pkDato +"'";
           
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
