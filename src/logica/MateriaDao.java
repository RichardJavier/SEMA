package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MateriaDao {

    ResultSet resultSet;

    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nombre_materia AS nm "
                    + "INNER JOIN especialidad AS e "
                    + "ON nm.id1_especialidad = e.id1_especialidad "
                    + "INNER JOIN semestre AS s "
                    + "ON nm.id1_semestre = s.id1_semestre ORDER BY id1_nombre_materia DESC ";
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
            String sql = "select * from especialidad where id1_especialidad =" + "'" + idEspecialidad + "';";
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
