
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.DescripcionMateria;


public class DescripcionMateriaDao {
  public Integer cargaSecuencialMateria(){   
   Conexion cc = Conexion.getInstance();
   Connection cn = cc.Conectar();
   DescripcionMateria mat = new DescripcionMateria();
      try {
          String sql ="SELECT * FROM desc_materia ORDER BY id_desc_materia DESC LIMIT 0,1 " ;
          Statement st = cn.createStatement();
          ResultSet rs= st.executeQuery(sql);
          while(rs.next()){
            mat.setIdConfiguracionMateria(Integer.parseInt(rs.getString("id_desc_materia")));            
          }
           mat.getIdConfiguracionMateria();
      } catch (SQLException | NumberFormatException e) {
      }
   return mat.getIdConfiguracionMateria();
  } 
  
}
