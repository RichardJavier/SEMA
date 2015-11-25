package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CiudadDao {

    public ResultSet listadoPais() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "select * from ciudad order by id_ciudad desc";
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public ResultSet consultaCiudad(Integer idCiudad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "select * from ciudad where id_ciudad =" + "'" + idCiudad + "'" + ";";
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
