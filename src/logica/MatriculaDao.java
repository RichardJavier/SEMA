package logica;

import conectar.Conexion;
import control.EnviaEmail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Matricula;

public class MatriculaDao {

    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM matricula AS m "
                    + "INNER JOIN maestro_alumno a "
                    + "ON m.id_alumno=a.id_alumno "
                    + "INNER JOIN semestre AS s  "
                    + "ON m.id_semestre=s.id1_semestre "
                    + "INNER JOIN especialidad AS e "
                    + "ON m.id_especialidad=e.id1_especialidad "
                    + "INNER JOIN paralelo AS p  "
                    + "ON m.id_paralelo=p.id1_paralelo "
                    + "order by id_matricula DESC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

        return null;
    }

    public synchronized ResultSet consulta(Integer idMatricula) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM matricula AS m "
                    + "INNER JOIN maestro_alumno a "
                    + "ON m.id_alumno=a.id_alumno "
                    + "INNER JOIN semestre AS s "
                    + "ON m.id_semestre=s.id1_semestre "
                    + "INNER JOIN especialidad AS e "
                    + "ON m.id_especialidad=e.id1_especialidad "
                    + "INNER JOIN paralelo AS p  "
                    + "ON m.id_paralelo=p.id1_paralelo "
                    + "where id_matricula " + "=" + "'" + idMatricula + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

        return null;
    }

    public Integer cargaIdMatricula() {
        Matricula matricula = new Matricula();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM matricula  ORDER BY id_matricula DESC LIMIT 0,1 ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                matricula.setIdMatricula(Integer.parseInt(rs.getString("id_matricula")));
            }
            return matricula.getIdMatricula();
        } catch (SQLException | NumberFormatException e) {
        }
        return null;
    }

    public Boolean verificaMatricula(String cedula, Integer idPeriodo) {
        boolean flag = true;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql ="Select * from matricula where cedula ="+"'"+cedula+"'"+"and id_periodo="+"'"+idPeriodo+"'"+";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                flag=false;
                break;
            }
            return flag;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
