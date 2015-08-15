
package control;

import conectar.Conexion;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;
import com.ibatis.common.jdbc.ScriptRunner;
import java.io.IOException;
import javax.swing.JOptionPane;


public class EjecutarScript {
    
   public  void crearTabla(){
    
   String archivo = "src/script/alumno.sql";
       try {
                
           Conexion cc = Conexion.getInstance();
           Connection cn = cc.Conectar();
           ScriptRunner src = new ScriptRunner(cn,false,false);
           Reader r = new BufferedReader(new FileReader(archivo));
           src.runScript(r);
       } catch (IOException | SQLException e) {
             System.err.println("Failed to Execute" + archivo
					+ " The error is " + e.getMessage());
           JOptionPane.showMessageDialog(null,"Ocurrio un error al cargar el Script","Error",JOptionPane.ERROR_MESSAGE);
             
       }
     
       
   }
}
