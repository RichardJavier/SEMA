package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class EspecialidadDao {

    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from especialidad order by id1_especialidad asc";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }

        return null;
    }
    public synchronized ResultSet consulta(Integer idEspecialidad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from especialidad where id1_especialidad =" + "'"+ idEspecialidad+ "';";
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
