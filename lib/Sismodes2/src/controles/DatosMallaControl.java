/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class DatosMallaControl {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();

    public  synchronized ResultSet consultaOrdenada() {
        try {
            String sql = "SELECT m.id_malla,m.nombre_malla,s.semestre,e.especialidad,p.periodo,m.por_nota,m.por_tuto,m.num_hora,m.porcentaje,m.fecha_creacion,m.estado "
                    + "FROM malla m "
                    + "LEFT JOIN semestre s "
                    + "ON m.id1_semestre=s.id1_semestre "
                    + "LEFT JOIN especialidad e "
                    + "ON m.id1_especialidad=e.id1_especialidad "
                    + "LEFT JOIN periodo_semestre p "
                    + "ON m.id1_periodo = p.id1_periodo "
                    + "ORDER BY id_malla ASC ";
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            System.out.println(""+sql);
            return resultado;
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar la Malla","Error",JOptionPane.ERROR_MESSAGE);
           System.out.println("Error en la consulta" + e);
        }
        return null;

    }

    public synchronized ResultSet consulta(Integer pkDato) {
        try {
            String sql = "SELECT m.idmalla,m.nombreMalla,s.semestre,e.especialidad,p.periodo,m.por_nota,m.por_tuto,m.num_horas,m.porcentaje,m.fecha_creacion,m.estado "
                    + "FROM malla m "
                    + "LEFT JOIN semestre s "
                    + "ON m.id1_semestre=s.id1_semestre "
                    + "LEFT JOIN especialidad e "
                    + "ON m.id1_especialidad=e.id1_especialidad "
                    + "LEFT JOIN periodo_semestre p "
                    + "ON m.id1_periodo = p.id1_periodo ";
                  
            if (pkDato > 0) {
                sql += "WHERE id_malla = " + pkDato;
            }
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar la Malla","Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("Error en la consulta" + e);
        }
        return null;

    }

    public synchronized Integer insertar(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
            @SuppressWarnings("LocalVariableHidesMemberVariable")
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
            System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
            System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
            return registrosAfectados;

        } catch (SQLException ex) {
            Logger.getLogger(DatosAlumnoControl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error los datos no se ingresaron correctamente ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public Double actualizar(String tabla, String pkTabla, Double pkDato, Map datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Double borrar(Double pkPrincipal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet consultaOrdenada(Double pkDato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized ResultSet especialidad() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM especialidad ORDER BY id1_especialidad ASC ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar la especialidad","Error",JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }

    public  synchronized ResultSet semestre() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM semestre ORDER BY id1_semestre ASC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar semestre","Error",JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }

    public ResultSet periodo() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM periodo_semestre WHERE matricula = '1' ";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar el periodo","Error",JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }

    public ResultSet ciclo() {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM ciclo ORDER BY id_ciclo ASC";

            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);

            return resultado;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al consultar el ciclo","Error",JOptionPane.ERROR_MESSAGE);

        }
        return null;
    }

//    public ArrayList<DatosPeriodoSemestre> cargarListaperiodos() {
//  try {
//            Conexion cc = Conexion.getInstance();
//            Connection cn = cc.Conectar();
//            String sql = "SELECT * FROM ciclo ORDER BY id_ciclo ASC";
//
//            Statement st = cn.createStatement();
//            ResultSet resultado = st.executeQuery(sql);
//
//            ArrayList<DatosPeriodoSemestre>listaTemporal=new ArrayList<>();
//            DatosPeriodoSemestre lista = new DatosPeriodoSemestre(resultado.getString("periodo"),Double.valueOf(resultado.getString("id1_periodo")) );
//            while(resultado.next()){
//             listaTemporal.add(lista);
//            }
//         return  listaTemporal;
//        } catch (SQLException | NumberFormatException e) {
//            System.out.println("Error en la consulta  padre" + e);
//
//        }
//        return null;
//    }
//    public int cambioEstadoMalla (){
//        int idmalla=0;
//        try {
//            Conexion cc = Conexion.getInstance();
//            Connection cn = cc.Conectar();
//            String sql = "SELECT * FROM malla ORDER BY idmalla DESC LIMIT 0,1";
//            Statement st = cn.createStatement();
//            ResultSet resul = st.executeQuery(sql);
//            while (resul.next()){
//                idmalla=Integer.parseInt(resul.getString("idmalla"));
//            }
//            
//            return  idmalla;
//        } catch (SQLException ex) {
//            Logger.getLogger(DatosMallaControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return  idmalla;
//    }
}
