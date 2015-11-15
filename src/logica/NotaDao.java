package logica;

import conectar.Conexion;
import control.Crud;
import control.EnviaEmail;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Matricula;

public class NotaDao {

    public ResultSet consultaNotas(String periodo, Integer idSemestre) {
        Conexion cc = Conexion.getInstance();
        Connection cn = cc.Conectar();
        try {
            String sql = "SELECT * FROM nota" + "_" + periodo + " AS n "
                    + "RIGHT JOIN nombre_materia m "
                    + "ON  n.id_materia = m.id1_nombre_materia "
                    + "RIGHT JOIN semestre AS s "
                    + "ON m.id1_semestre = s.id1_semestre "
                    + "RIGHT JOIN especialidad AS e "
                    + "ON m.id1_especialidad=e.id1_especialidad "
                    + "right join matricula as ma "
                    + "on n.id_matricula=ma.id_matricula "
                    + "WHERE "
                    + " m.id1_semestre" + "=" + "'" + idSemestre + "'" + " ORDER BY id_nota DESC";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    public String nombreAlumno(String cedula, Integer idSemestre) {
        Matricula matricula = new Matricula();
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select nombre_completo from matricula where cedula " + "=" + "'" + cedula + "'"
                    + " and " + "id_semestre " + "=" + "'" + idSemestre + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                matricula.setNombreCompleto(rs.getString("nombre_completo"));
            }
            return matricula.getNombreCompleto();
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    public ResultSet cargarValoresMateria(String codigo, Integer idNota, Integer idMateria) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nota_" + codigo + " AS nota "
                    + "LEFT JOIN  nombre_materia AS nombre "
                    + "ON nota.id_materia = nombre.id1_nombre_materia "
                    + "LEFT JOIN config_materia AS config "
                    + "ON nota.id_config_materia = config.id_config_materia "
                    + "LEFT JOIN desc_materia AS dm "
                    + "ON nota.id_desc_materia=dm.id_desc_materia "
                    + "LEFT JOIN configuracion as c "
                    + "ON nota.id_configuracion=c.id_configuracion "
                    + "WHERE nota.id_nota " + "=" + "'" + idNota + "'" + " and nota.id_materia " + "=" + "'" + idMateria + "'" + ";";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException | NumberFormatException e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }

    public synchronized Integer actualizarNota(String tabla, String periodo, String pkTabla, Integer pkDato, Map datos,String usuario) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            StringBuilder campos = new StringBuilder();
            StringBuilder coma = new StringBuilder();
            for (Iterator it = datos.keySet().iterator(); it.hasNext();) {
                String llave = (String) it.next();
                campos.append(llave).append("=");
                if (datos.get(llave) instanceof Date) {
                    campos.append("'").append(new java.sql.Date(((Date) datos.get(llave)).getTime()).toString()).append("',");
                } else {
                    campos.append("'").append(datos.get(llave).toString()).append("',");
                }
            }
            String sql = "UPDATE" + "  " + tabla + "_" + periodo + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";

            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("actualizar", tabla,usuario, datos);
            JOptionPane.showMessageDialog(null, "Registros ingresados");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return 0;
    }

    public ResultSet consultaNotasProfesor(String periodo, Integer idSemestre, Integer idMateria) {
        Conexion cc = Conexion.getInstance();
        Connection cn = cc.Conectar();
        try {
            String sql = "SELECT * FROM nota" + "_" + periodo + " AS n "
                    + "RIGHT JOIN nombre_materia m "
                    + "ON  n.id_materia = m.id1_nombre_materia "
                    + "RIGHT JOIN semestre AS s "
                    + "ON m.id1_semestre = s.id1_semestre "
                    + "RIGHT JOIN especialidad AS e "
                    + "ON m.id1_especialidad=e.id1_especialidad "
                    + "right join matricula as ma "
                    + "on n.id_matricula=ma.id_matricula "
                    + "WHERE "
                    + " m.id1_semestre" + "=" + "'" + idSemestre + "'"
                    + "AND "
                    + "m.id1_nombre_materia" + "=" + "'" + idMateria + "'"
                    + " ORDER BY id_nota DESC";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return null;
    }
}
