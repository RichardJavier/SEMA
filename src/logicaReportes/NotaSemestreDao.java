/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaReportes;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class NotaSemestreDao {

    public ResultSet cargarNotaSemestre(String codigoPeriodo, String cedula, Integer idEspecialidad, Integer idSemestre) {
        try {
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            String sql = "SELECT * FROM nota_" + codigoPeriodo + " AS n "
                    + "LEFT JOIN matricula AS ma "
                    + "ON n.id_matricula=ma.id_matricula "
                    + "LEFT JOIN periodo_semestre AS p "
                    + "ON ma.id_periodo=p.id1_periodo "
                    + "LEFT JOIN nombre_materia AS nm "
                    + "ON n.id_materia=nm.id1_nombre_materia "
                    + "LEFT JOIN especialidad AS e "
                    + "ON ma.id_especialidad=e.id1_especialidad "
                    + "lEFT JOIN semestre AS s "
                    + "ON ma.id_semestre=s.id1_semestre "
                    + "LEFT JOIN paralelo AS pa "
                    + "ON ma.id_paralelo=pa.id1_paralelo "
                    + "WHERE n.cedula " + "=" + cedula + " "
                    + "and ma.id_especialidad " + "=" + idEspecialidad + " "
                    + "and ma.id_semestre " + "=" + idSemestre + ";";
            Statement st = cn.createStatement();
            ResultSet resul = st.executeQuery(sql);
            return resul;
        } catch (Exception e) {
            System.out.println("ocurrio un error");
        }

        return null;
    }

}
