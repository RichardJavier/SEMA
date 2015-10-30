package controles;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metodos.Crud;

public class DatosMateriaControl implements Crud {

    private String numAportes;
    private double numHorasMalla;

    @Override
    public synchronized ResultSet consulta(Double pkDato) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT m.id1_nombre_materia,m.materia,m.numHoras,m.activa_mat,s.semestre,e.especialidad,m.idconfigmaterias, "
                    + "IFNULL(ej.nom_ejes,'vacio')AS nom_ejes, "
                    + "c.ciclo,IF(m.f_crea IS NULL OR m.f_crea = '','1970-01-01 00:00:00', m.f_crea)AS f_crea, "
                    + "IF(CONCAT(d.nom_profe ,' ' ,d.apell_profe) IS NULL OR '','SIN PROFESOR',CONCAT(d.nom_profe ,' ',d.apell_profe))AS profesor, "
                    + "ma.nombreMalla,m.idmalla,m.ponderacion "
                    + "FROM nombre_materia AS m "
                    + "LEFT JOIN semestre s ON s.id1_semestre = m.id1_semestre "
                    + "LEFT JOIN especialidad e ON e.id1_especialidad = m.id1_especialidad "
                    + "LEFT JOIN ejes ej ON m.id1_eje = ej.id1_ejes "
                    + "LEFT JOIN ciclo c ON m.id_ciclo = c.id_ciclo "
                    + "LEFT JOIN datos_profesor d ON m.id1_profe = d.id1_profe "
                    + "LEFT JOIN malla ma ON m.id_malla = ma.id_malla ";

            if (pkDato > 0) {
                sql += "WHERE id1_nombre_materia =" + pkDato;
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar la Materias","Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }

    @Override
    public synchronized Double insertar(String tabla, Map datos) {

        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();

            // cargar la tabla y los campos para su ingreso 
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
            double registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados:" + registrosAfectados + "registros");
            System.out.println("sql par verficar la configmaterias"+sql);
            cc.desconectar();
            return registrosAfectados;
        } catch (NullPointerException | SQLException e) {
            //System.out.println("Error en la insercion" + e);
           JOptionPane.showMessageDialog(null, "Error los datos no se ingresaron correctamente","Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DatosMateriaControl.class.getName()).log(Level.SEVERE, null, e);
        }

        return 0.0;
    }

    @Override
    public Double actualizar(String tabla, String pkTabla, Double pkDato, Map datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double borrar(Double pkPrincipal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet consultaOrdenada(Double pkDato) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT m.id1_nombre_materia,m.materia,m.numhora,m.activa_mat,s.semestre,e.especialidad, "
                    + "IFNULL(ej.nom_ejes,'vacio')AS nom_ejes, "
                    + "c.ciclo,IF(m.f_crea IS NULL OR m.f_crea = '','1970-01-01 00:00:00', m.f_crea)AS f_crea, "
                    + "IF(CONCAT(d.nom_profe ,' ' ,d.apell_profe) IS NULL OR '','SIN PROFESOR',CONCAT(d.nom_profe ,' ',d.apell_profe))AS profesor "
                    + "FROM nombre_materia AS m "
                    + "LEFT JOIN semestre s ON s.id1_semestre = m.id1_semestre "
                    + "LEFT JOIN especialidad e ON e.id1_especialidad = m.id1_especialidad "
                    + "LEFT JOIN ejes ej ON m.id1_eje = ej.id1_ejes "
                    + "LEFT JOIN ciclo c ON m.id_ciclo = c.id_ciclo "
                    + "LEFT JOIN datos_profesor d ON m.id1_profe = d.id1_profe "
                    + "ORDER BY m.id1_semestre ASC ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }

    public ResultSet cargarConfigMateria(int idconfigmateria) {
        try {

            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM config_materia where id_config_materia "+"=" +"'"+ idconfigmateria + "'";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            
            return resultado;
        } catch (SQLException e) {
        }
        return null;
    }

    public double cargarNumHorasMalla(int idmalla) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();

            String sql = "SELECT * FROM malla WHERE  id_malla = " + idmalla + " ";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                numHorasMalla = Double.valueOf(rs.getString("cred_ciclo"));
            }
            return numHorasMalla;
        } catch (SQLException | NumberFormatException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return numHorasMalla;

    }

    public int numeroHorasMaterias(int malla) {
        int resultado = 0;
        int valor =0;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from nombre_materia where id_malla = " + malla + "";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resultado=Integer.parseInt(rs.getString("numhora"));
                valor=valor+resultado;
                
            }
            return valor;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return valor;
    }
    public int numeroPonderacionMalla(int malla) {
        int resultado = 0;
        int valor =0;
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "select * from nombre_materia where id_malla = " + malla + "";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resultado=Integer.parseInt(rs.getString("ponderacion"));
                valor=valor+resultado;
                
            }
            return valor;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return valor;
    }

    public ResultSet cargarIdentificadores(int idmalla) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "SELECT * FROM malla WHERE id_malla="+idmalla+"";
            ResultSet resultado=st.executeQuery(sql);
            return  resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la Consulta" + e);
            System.out.println("Error en la consulta" + e);
        }
        return null;
    }

    public ResultSet numeroHorasEspeciliadad(int selectedIndex, int selectedIndex0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int idconfigMateria() {
        int resultado = 0;
        try {

            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            String sql = "SELECT * FROM config_materia  ORDER BY id_config_materia DESC LIMIT 0,1  ";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                resultado = Integer.valueOf(rs.getString("id_config_materia"));
            }
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(DatosMateriaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
