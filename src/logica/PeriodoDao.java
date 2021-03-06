package logica;

import conectar.Conexion;
import control.EnviaEmail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Periodo;

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
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
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
                    + "from periodo_semestre AS ps left join ciclo c on c.id_ciclo =ps.ciclo "
                    + "where id1_periodo > 36 ORDER BY ps.id1_periodo ASC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }

        return null;
    }

    public Periodo codigoPeriodo(Integer idPeriodo, Connection cn) {
        try {
            Periodo p = new Periodo();
            String sql = "SELECT * FROM periodo_semestre AS ps "
                    + "WHERE id1_periodo" + "=" + "'" + idPeriodo+ "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                p.setCodigoPeriodo( rs.getString("id_periodo"));
                p.setIdPeriodo(Integer.parseInt(rs.getString("id1_periodo")));
            }
            return p;
        } catch (SQLException | NumberFormatException e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }
}
