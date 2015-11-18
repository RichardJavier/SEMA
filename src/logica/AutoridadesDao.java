package logica;

import conectar.Conexion;
import control.EnviaEmail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutoridadesDao {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();

    public ResultSet cargaAutoridades() {
         try {
             String sql ="select * from autoridades";
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql);
             return  rs;
        } catch (Exception e) {
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
             System.out.println("error"+e.toString());
        }
         return  null;
    }
    public ResultSet cargaAutoridades(Integer idAutoridad) {
         try {
             String sql ="select * from autoridades where idautoridades ="+"'"+idAutoridad+"'"+";";
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql);
             return  rs;
        } catch (Exception e) {
             EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
             System.out.println("error"+e.toString());
        }
         return  null;
    }
    
}
