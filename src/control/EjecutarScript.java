package control;

import conectar.Conexion;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;
import com.ibatis.common.jdbc.ScriptRunner;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class EjecutarScript {

    public Boolean crearTabla() {     
        try {
            File path = new File("script/alumno.sql");
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            ScriptRunner src = new ScriptRunner(cn, false, false);
            Reader r = new BufferedReader(new FileReader(path.getAbsoluteFile()));
            src.runScript(r);
            return  true;
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el Script"+e, "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
            return false;
        }

    }
}
