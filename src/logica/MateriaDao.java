package logica;

import conectar.Conexion;
import control.EnviaEmail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Materia;

public class MateriaDao {

    ResultSet resultSet;

    public synchronized ResultSet consultaOrdenada() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nombre_materia AS nm "
                    + "INNER JOIN especialidad AS e "
                    + "ON nm.id1_especialidad = e.id1_especialidad "
                    + "INNER JOIN semestre AS s "
                    + "ON nm.id1_semestre = s.id1_semestre where activa_mat='AC' ORDER BY id1_nombre_materia DESC ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }

        return null;
    }

    public synchronized ResultSet consulta(int idMateria) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nombre_materia AS nm "
                    + "INNER JOIN malla m  "
                    + "ON nm.id_malla= m.id_malla "
                    + "INNER JOIN semestre s  "
                    + "ON nm.id1_semestre= s.id1_semestre "
                    + "INNER JOIN especialidad e "
                    + "ON nm.id1_especialidad=e.id1_especialidad "
                    + "INNER JOIN ejes j  "
                    + "ON nm.id1_eje = j.id1_ejes "
                    + "INNER JOIN datos_profesor p  "
                    + "ON nm.id1_profe = p.id1_profe "
                    + "INNER JOIN config_materia cm "
                    + "ON nm.id_config_materia = cm.id_config_materia "
                    + "INNER JOIN desc_materia dm "
                    + "ON nm.id_desc_materia = dm.id_desc_materia "
                    + "INNER JOIN configuracion as c "
                    + "ON  nm.id_configuracion=c.id_configuracion "
                    + "WHERE  nm.id1_nombre_materia " + "=" + "'" + idMateria + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }

        return null;
    }

    public List<Materia> valorCreditos(int idmalla) {

        String sql = "SELECT * FROM nombre_materia nm "
                + "WHERE activa_mat ='A' AND id_malla " + "=" + "'" + idmalla + "'" + ";";
        Conexion cc = Conexion.getInstance();
        Connection cn = cc.Conectar();
        try {
            Statement st = cn.createStatement();
            resultSet = st.executeQuery(sql);
            List<Materia> listaMaterias = new ArrayList<>();
            while (resultSet.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(Integer.parseInt(resultSet.getString("id1_nombre_materia")));
                materia.setNombreMateria(resultSet.getString("materia"));
                materia.setCreditos(Integer.parseInt(resultSet.getString("creditos")));
                listaMaterias.add(materia);
            }
            return listaMaterias;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }

        return null;
    }

    public List<Materia> listaMaterias(Integer idEspecialidad, Integer idSemestre) {
        List<Materia> listaMaterias = new ArrayList<>();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from nombre_materia where id1_especialidad " + "=" + "'" + idEspecialidad + "'"
                    + " and id1_semestre " + "=" + "'" + idSemestre + "'" + " and activa_mat = 'AC' ";
            Statement st = cn.createStatement();
            resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                Materia mat = new Materia();
                mat.setIdMateria(Integer.parseInt(resultSet.getString("id1_nombre_materia")));
                mat.setNombreMateria(resultSet.getString("materia"));
                mat.setIdConfiguracionMateria(resultSet.getInt("id_config_materia"));
                mat.setIdDescripcion(resultSet.getInt("id_desc_materia"));
                mat.setIdMalla(resultSet.getInt("id_malla"));
                mat.setCreditos(resultSet.getInt("creditos"));
                mat.setIdConfiguracion(resultSet.getInt("id_configuracion"));
                listaMaterias.add(mat);
            }
            return listaMaterias;
        } catch (SQLException | NumberFormatException e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    public List<Materia> listaMateriasArrastre(String antesPeriodo, int idEspecialidad, int idSemestreAntes,String cedula) {
        List<Materia> listaMateriasArrastre = new ArrayList<>();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from nota_" + antesPeriodo + " as n "
                    + "inner join nombre_materia as nm "
                    + "on n.id_materia=nm.id1_nombre_materia "
                    + "where estado_nota = 'RP' "
                    + "and cedula="+"'"+cedula+"'"+";";
            Statement st = cn.createStatement();
            resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                Materia mat = new Materia();
                mat.setIdMateria(Integer.parseInt(resultSet.getString("id1_nombre_materia")));
                mat.setIdConfiguracion(Integer.parseInt(resultSet.getString("id_config_materia")));
                mat.setIdDescripcion(Integer.parseInt(resultSet.getString("id_desc_materia")));
                mat.setIdMalla(Integer.parseInt(resultSet.getString("id_malla")));
                mat.setCreditos(Integer.parseInt(resultSet.getString("creditos")));
                listaMateriasArrastre.add(mat);
            }
            return listaMateriasArrastre;
        } catch (SQLException | NumberFormatException e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }
}
