package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Alumno;

public class AlumnoDao {
    
    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from maestro_alumno order by id_alumno asc";
            
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        
        return null;
    }
    
    public synchronized ResultSet consulta(Integer idAlumno) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from maestro_alumno where id_alumno" + "=" + "'" + idAlumno + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        
        return null;
    }
    
    public Alumno buscarAlumno(String cedula) {
        Alumno alumno = new Alumno();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from maestro_alumno where cedula" + "=" + "'" + cedula + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                alumno.setIdAlumno(Integer.parseInt(resultado.getString("id_alumno")));
                alumno.setCedula(resultado.getString("cedula"));
                alumno.setApellidoPaterno(resultado.getString("apellido_paterno"));
                alumno.setApellidoMaterno(resultado.getString("apellido_materno"));
                alumno.setNombreCompleto(resultado.getString("nombre_completo"));
            }
            return alumno;
        } catch (SQLException | NumberFormatException e) {
        }
        return null;
    }
    
}
