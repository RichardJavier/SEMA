 
package metodos;

import java.sql.ResultSet;
import java.util.Map;
//0a9b6c9d0e2f6g9h6i7  codigo de verificacion
public interface Crud {
     public  ResultSet consulta(Double pkDato);
     public Double insertar(String tabla, Map datos);
     public Double actualizar(String tabla, String pkTabla, Double pkDato, Map datos);
     public Double borrar(Double pkPrincipal);
     public  ResultSet consultaOrdenada(Double pkDato);
}
