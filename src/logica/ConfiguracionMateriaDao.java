
package logica;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.ConfiguracionMateria;



public class ConfiguracionMateriaDao {
  public Integer cargaSecuencialMateria(){
   
   Conexion cc = Conexion.getInstance();
   Connection cn = cc.Conectar();
   ConfiguracionMateria mat = new ConfiguracionMateria();
      try {
          String sql ="SELECT * FROM config_materia ORDER BY id_config_materia DESC LIMIT 0,1 " ;
          Statement st = cn.createStatement();
          ResultSet rs= st.executeQuery(sql);
          while(rs.next()){
            mat.setIdConfiguracionMateria(Integer.parseInt(rs.getString("id_config_materia")));            
          }
          if (true) {
              
          }
          mat.getIdConfiguracionMateria();
      } catch (SQLException | NumberFormatException e) {
      }
   return  mat.getIdConfiguracionMateria();
  } 
}
