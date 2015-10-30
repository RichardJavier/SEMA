package metodos;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class CargarDatos {

    private String valcodigo;
    private int idAlumno;
    private int idPeriodo;
    private int idMatricula;
    private int idconfigNotas;
    private int idNota;
    private int idmalla;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();

    public ResultSet pais() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "select * from pais_n";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println("Error en la consulta pais " + e);
        }
        return null;
    }

    public ResultSet provincia(Integer pais) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from provincia_n where id_pais = " + pais + "";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta provincia" + e);
        }
        return null;
    }

    public ResultSet canton(Integer pais, Integer provincia) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from canton_n where id_pais = " + pais + " and id_provincia = " + provincia + "";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta canton " + e);
        }
        return null;
    }

    public ResultSet parroquia(int pais, int provincia, int canton) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from parroquia where id_pais = '" + pais + "'" + " and id_provincia = '" + provincia + "'" + "and idcanton = '" + canton + "'" + "";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  parroquia" + e);

        }
        return null;
    }

    public ResultSet ocupacionMadre() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from ocupacion_madre";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  madre" + e);

        }
        return null;
    }

    public ResultSet ocupacionPadre() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM ocupacion_padre";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public ResultSet especialidad() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM especialidad";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public String codigo() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql = "SELECT * FROM periodo_semestre  ORDER BY id_periodo DESC LIMIT 0,1 ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                valcodigo = resultado.getString("id_periodo");
                idPeriodo = Integer.parseInt(resultado.getString("id1_periodo"));

            }
            return valcodigo;

        } catch (SQLException | NumberFormatException | NullPointerException e) {

            System.out.println("Error en la consulta  codigo" + e);

        }
        return valcodigo;
    }

    public int idAlumno() {
        try {

            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT  * FROM maestro_alumno  ORDER BY id_alumno DESC LIMIT 0,1 ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                idAlumno = Integer.parseInt(resultado.getString("id_alumno"));

            }
            return idAlumno;

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Error en la consulta  codigo alumno" + e);

        }
        return idAlumno;
    }

    public int idMatricula() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT  * FROM matricula  ORDER BY id_matricula DESC LIMIT 0,1 ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                idMatricula = Integer.parseInt(resultado.getString("id_matricula"));

            }
            return idMatricula;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error en la consulta  codigo alumno" + e);

        }
        return idMatricula;
    }

    public int idNota(String periodo) {
        try {

            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT  * FROM nota_" + periodo + "  ORDER BY id_nota DESC LIMIT 0,1 ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                idNota = Integer.parseInt(resultado.getString("id_nota"));

            }
            return idNota;

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error en la consulta  codigo alumno" + e);

        }
        return idNota;
    }

    public ResultSet semestre() throws SQLException {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM semestre";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (NullPointerException  e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public ResultSet paralelo() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM paralelo";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            System.out.println("Error en la consulta pais " + e);
        }
        return null;
    }

    public ResultSet materia(int semestre, int especialidad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nombre_materia WHERE id1_semestre=" + semestre + " AND id1_especialidad=" + especialidad + " and activa_mat ='SI'";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("sql materia" + sql);
            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public ResultSet resumen(String codigo, int alumno) {
        try {

            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nota_" + codigo + " WHERE id_alumno='" + alumno + "'ORDER BY idnota ASC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("sql resumen" + sql);
            return resultado;

        } catch (Exception e) {
            System.out.println("Error en la consulta  padre" + e);

        }
        return null;
    }

    public ResultSet cargarPonderacion(int especialidad) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM especialidad where id1_especialidad= " + especialidad;
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println("cargar configuaracion de especialidad" + sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }

    public int cargarMalla(int especialidad, int semestre) {
        Conexion cc = Conexion.getInstance();
        Connection cn = cc.Conectar();
        try {

            String sql = "SELECT id_malla FROM malla WHERE id1_semestre =" + semestre + " AND id1_especialidad=" + especialidad + "";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                idmalla = Integer.parseInt(resultado.getString("id_malla"));
            }
            System.out.println("sql configmaterias" + sql);
            return idmalla;
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return idmalla;
    }

    public String getValCodigo() {
        return valcodigo;
    }

    public void setValcodigo(String valcodigo) {
        this.valcodigo = valcodigo;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

}
