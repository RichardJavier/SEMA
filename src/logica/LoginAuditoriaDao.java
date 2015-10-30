/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo.Bravo
 */
public class LoginAuditoriaDao {
    public static synchronized Integer insertar(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql;
            StringBuilder campos = new StringBuilder();
            StringBuilder valores = new StringBuilder();

            for (Iterator it = datos.keySet().iterator(); it.hasNext();) {
                String llave = (String) it.next();
                campos.append(llave).append(",");
                if (datos.get(llave) instanceof Date) {
                    valores.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format((Date) datos.get(llave))).append("',");
                } else {
                    valores.append("'").append(datos.get(llave).toString()).append("',");
                }

            }
            sql = "insert into " + tabla + "("
                    + campos.toString().substring(0, campos.toString().length() - 1)
                    + ")values ("
                    + valores.toString().substring(0, valores.toString().length() - 1)
                    + ")";
            System.out.println(sql);
            int registrosAfectados = st.executeUpdate(sql);
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return 0;
    }

}
