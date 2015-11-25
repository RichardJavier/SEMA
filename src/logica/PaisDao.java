package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaisDao {

    public ResultSet listadoPais() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "select * from pais order by id_pais desc";
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public ResultSet consultaPais(double idPais) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "select * from pais where id_pais =" + "'" + idPais + "'" + ";";
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
