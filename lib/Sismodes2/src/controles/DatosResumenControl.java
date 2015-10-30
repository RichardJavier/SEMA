package controles;


import conectar.Conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

public class DatosResumenControl {

    public ResultSet listaResumen(int idResumen) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM resumen ";
            if (idResumen > 0) {
                sql += "WHERE id_resumen " + "=" +"'"+ idResumen +"'"+ ";";
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al listar el Resumen ","Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }
    
     public ResultSet listaReporte(String cedula,int especialidad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM resumen where cedula "+"="+"'"+cedula+"'"+"and id_especialidad "+"="+"'"+especialidad+"'"+";";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al listar el Resumen ","Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }
    public int porcentajeTutoria(int idmalla){
        int porTuto= 0;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "Select por_tuto from malla where id_malla "+"="+"'"+idmalla+"'"+";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               porTuto =Integer.parseInt(rs.getString("por_tuto"));
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al encontrar el porcentaje de Tutoria ","Error",JOptionPane.ERROR_MESSAGE);
        }
    
    return porTuto;
    }
    
    
    public synchronized int actualizar(String tabla, String pkTabla, int pkDato, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            StringBuilder campos = new StringBuilder();
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
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados: " + registrosAfectados + " registros");
            JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            System.out.println("Error en la actualizacion: " + e.toString());
            JOptionPane.showMessageDialog(null, "Error en la actualizacion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;

    }
 public String buscarEstudiante (String cedula) {
 String nombre="";
     Conexion cc = Conexion.getInstance();
     Connection cn=cc.Conectar();
     
     try {
         String sql = "select nom_completos from maestro_alumno where cedula "+"="+"'"+cedula+"';";
         Statement st = cn.createStatement();
         ResultSet rs = st.executeQuery(sql);
         while (rs.next()){
             nombre=rs.getString("nom_completos");
         }
         return  nombre;
     } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion", "Error", JOptionPane.ERROR_MESSAGE);
     }
 return  nombre;
         
 }
}
