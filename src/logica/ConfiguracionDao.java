package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Configuracion;

public class ConfiguracionDao {

    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM malla AS m "
                    + "LEFT JOIN periodo_semestre AS ps "
                    + "ON m.`id1_periodo`=ps.`id1_periodo` ORDER BY id_malla ASC ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }

        return null;
    }

    public synchronized ResultSet consulta(Integer idMalla) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM malla AS m "
                    + "LEFT JOIN periodo_semestre AS ps "
                    + "ON m.`id1_periodo`=ps.`id1_periodo` "
                    + "where id_malla " + "=" + "'" + idMalla + "'";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }

        return null;
    }

    public Configuracion getMalla(int idMalla) {
        Configuracion malla = new Configuracion();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM malla "
                    + "where id_malla " + "=" + "'" + idMalla + "'";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                malla.setIdConfiguracion(Integer.valueOf(resultado.getString("id_malla")));
                malla.setValorMinimoAsistencia(Integer.parseInt(resultado.getString("valor_min_asistencia")));
                malla.setValorMinimoPromedio(Double.valueOf((resultado.getString("valor_min_promedio"))));
                malla.setValorNota(Double.valueOf((resultado.getString("valor_calf_nota"))));
                malla.setValorRecuperacion(Double.valueOf(resultado.getString("valor_min_recuperacion")));
                malla.setIdPeriodo(Integer.parseInt(resultado.getString("id1_periodo")));
            }
            return malla;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }

        return null;

    }
}