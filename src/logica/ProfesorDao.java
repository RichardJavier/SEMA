package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfesorDao {

    public ResultSet cargaProfesor() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from datos_profesor  where estado='AC' order by id1_profe DESC";
            Statement st = cn.createStatement();
            ResultSet resul = st.executeQuery(sql);
            return resul;
        } catch (Exception e) {
        }
        return null;
    }

    public ResultSet cargaProfesor(Integer idProfesor) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from datos_profesor  where id1_profe " + "=" + "'" + idProfesor + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet resul = st.executeQuery(sql);
            return resul;
        } catch (Exception e) {
        }
        return null;
    }

}
