package controles;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatosNotaControl {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();

    public ResultSet llenaNotas(Integer dato, String periodo, int semestre) {
        try {
            String sql = "SELECT n.id_nota,CONCAT(a.nomcompletos)AS nombres,n.cedula,s.semestre, "
                    + "e.especialidad,m.materia FROM nota_" + periodo + " AS n "
                    + "LEFT JOIN maestro_alumno AS a "
                    + "ON n.id_alumno = a.id_alumno "
                    + "LEFT JOIN semestre  AS s "
                    + "ON n.id_semestre = s.id1_semestre "
                    + "LEFT JOIN especialidad AS e "
                    + "ON n.ide_specialidad = e.id1_especialidad "
                    + "LEFT JOIN nombre_materia AS m "
                    + "ON n.id_materia = m.id1_nombre_materia "
                    + "where id_semestre =" + semestre + "";

            if (dato > 0) {
                sql += "WHERE id_alumno = " + dato;
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("sql todo" + sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return null;

    }

 

    
}
