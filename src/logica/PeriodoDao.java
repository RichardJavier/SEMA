package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class PeriodoDao {

    public synchronized ResultSet consulta(int pkDato) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT ps.id_periodo,ps.periodo,ps.id1_periodo,ps.matricula,ps.nueva_malla,"
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

    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT ps.id_periodo,ps.periodo,ps.id1_periodo,ps.matricula,ps.nueva_malla,"
                    + "IF(c.ciclo IS NULL OR c.ciclo = '', 'vacio', c.ciclo)AS ciclo,"
                    + "IF(ps.fecha_fin IS NULL OR ps.fecha_fin = '', '1970-01-01 00:00:00', ps.fecha_fin)AS fecha_fin "
                    + "from periodo_semestre AS ps left join ciclo c on c.id_ciclo =ps.ciclo ORDER BY ps.id1_periodo ASC";

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
