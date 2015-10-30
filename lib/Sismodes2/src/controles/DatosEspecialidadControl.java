/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * //0969026967 private void number
 */
package controles;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metodos.Crud;

/**
 *
 * @author Ricardo
 */
public class DatosEspecialidadControl implements Crud{

    @Override
    public synchronized ResultSet consulta(Double pkDato) {
         try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from especialidad ";
            if (pkDato > 0) {
              sql += "WHERE id1_especialidad="+" " + pkDato;
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar la especialidad","Error",JOptionPane.ERROR_MESSAGE);
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
                valores.append("'").append(datos.get(llave).toString()).append("',");
                /*
                if (datos.get(llave) instanceof Date) {
                    valores.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format((Date) datos.get(llave))).append("',");
                } else {
                    valores.append("'").append(datos.get(llave).toString()).append("',");
                }
              */
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
        } catch (NullPointerException |SQLException e ) {
            Logger.getLogger(DatosProfesorControl.class.getName()).log(Level.SEVERE, null, e);
            //System.out.println("Error en la insercion" + e);
            JOptionPane.showMessageDialog(null, "Ocurrion un error problamente los datos no se guardaron correctamente","Error",JOptionPane.ERROR_MESSAGE);
        } 
        return 0.0;

    }

    @Override
    public Double actualizar(String tabla, String pkTabla, Double pkPrincipal, Map datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double borrar(Double pkPrincipal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized ResultSet consultaOrdenada(Double pkDato) {
      try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from especialidad ORDER BY id_especial";
            
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar la especialidad","Error",JOptionPane.ERROR_MESSAGE);
          //  System.out.println("Error en la consulta" + e);
        }
        return null;
    
    }
    
}
