package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.Matricula;

public class NotaDao {

    public ResultSet consultaNotas(String periodo, String cedula, Integer idSemestre) {
        Conexion cc = Conexion.getInstance();
        Connection cn = cc.Conectar();
        try {
            String sql = "SELECT * FROM nota" + "_" + periodo + " AS n "
                    + "LEFT JOIN nombre_materia m "
                    + "ON  n.id_materia = m.id1_nombre_materia "
                    + "RIGHT JOIN semestre AS s "
                    + "ON m.id1_semestre = s.id1_semestre "
                    + "RIGHT JOIN especialidad AS e "
                    + "ON m.id1_especialidad=e.id1_especialidad "
                    + "WHERE n.cedula " + "=" + "'" + cedula + "'" + "and "
                    + " m.id1_semestre" + "=" + "'" + idSemestre + "'" + " ORDER BY id_nota DESC";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String nombreAlumno(String cedula, Integer idSemestre) {
        Matricula matricula = new Matricula();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select nombre_completo from matricula where cedula " + "=" + "'" + cedula + "'"
                    + " and "+"id_semestre "+"="+"'"+idSemestre+"'"+";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                matricula.setNombreCompleto(rs.getString("nombre_completo"));
            }
            return matricula.getNombreCompleto();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
