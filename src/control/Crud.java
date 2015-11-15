package control;

import conectar.Conexion;
import java.awt.HeadlessException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.LoginTransaccion;

public class Crud {

    private static final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public synchronized Integer insertar(String tabla, Map datos, String usuario) {
        try {
            Conexion cc = Conexion.getInstance();
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
            System.out.println(sql);
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("insert", tabla, usuario, datos);
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",ex.toString());
            
        }
        return 0;
    }

    public synchronized Integer insertarM(String tabla, Map datos, String usuario) {
        try {
            Conexion cc = Conexion.getInstance();
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

            int registrosAfectados = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Registros ingresados");
            Crud.loginTransaccion("insert", tabla, usuario, datos);
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta", "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",ex.toString());
        }
        return 0;
    }

    public synchronized Integer actualizarWeb(String tabla, String pkTabla, String pkDato, Map datos) {
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
            String sql = "UPDATE" + "  " + tabla + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion de los datos de la WEB", "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return 0;
    }

    public synchronized Integer actualizarM(String tabla, String pkTabla, Integer pkDato, Map datos, String usuario) {
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
            String sql = "UPDATE" + "  " + tabla + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";
            System.out.println(sql);
            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("insert", tabla, usuario, datos);
            JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
            cc.desconectar();
            return registrosAfectados;
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return 0;
    }

    public synchronized Integer actualizar(String tabla, String pkTabla, Integer pkDato, Map datos, String usuario) {
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
            String sql = "UPDATE" + "  " + tabla + " " + "SET" + " "
                    + campos.toString().substring(0, campos.toString().length() - 1) + " "
                    + "where" + " " + pkTabla + "=" + "'" + pkDato + "'" + ";";

            Statement st = cn.createStatement();
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("insert", tabla, usuario, datos);
            cc.desconectar();
            return registrosAfectados;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion:" + e.toString());
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return 0;
    }

    public synchronized Integer insertarMaterias(String tabla, String codigo, Map datos, String usuario) {
        try {
            Conexion cc = Conexion.getInstance();
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

            sql = "insert into " + tabla + "_" + codigo + "("
                    + campos.toString().substring(0, campos.toString().length() - 1)
                    + ")values ("
                    + valores.toString().substring(0, valores.toString().length() - 1)
                    + ")";
            System.out.println("este es el sql " + sql);
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("insert", tabla, usuario, datos);
            // System.out.println("Registros afectados alumno:" + registrosAfectados + "registros");
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error talvez los datos no se almacenaron correctamente ", "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",ex.toString());
        }
        return 0;
    }

    private static Integer insertarLog(String tabla, Map datos) {
        try {
            Conexion cc = Conexion.getInstance();
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
            System.out.println(sql);
            int registrosAfectados = st.executeUpdate(sql);
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",ex.toString());
        }
        return 0;
    }

    public static void loginTransaccion(String accion, String tabla, String usuario, Map registro) {
        try {
            Calendar cal = Calendar.getInstance();
            LoginTransaccion loginTransaccion = new LoginTransaccion();
            InetAddress localHost = InetAddress.getLocalHost();
            loginTransaccion.setIp(localHost.getHostAddress());
            loginTransaccion.setMaquina(localHost.getHostName());
            loginTransaccion.setAccion(accion);
            loginTransaccion.setFecha(formato.format(cal.getTime()));
            loginTransaccion.setRegistro(String.valueOf(registro));
            loginTransaccion.setTabla(tabla);
            loginTransaccion.setUsuario(usuario);
            Map campos = new HashMap();
            campos.put("ip", loginTransaccion.getIp());
            campos.put("maquina", loginTransaccion.getMaquina());
            campos.put("accion", loginTransaccion.getAccion());
            campos.put("tabla", loginTransaccion.getTabla());
            campos.put("usuario", loginTransaccion.getUsuario());
            campos.put("fecha", loginTransaccion.getFecha());
            campos.put("registro", loginTransaccion.getRegistro());
            Crud.insertarLog("log_transaccion", campos);
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
    }
    public static void loginTransaccion2(String accion, String tabla, String usuario, String a) {
        try {
            Calendar cal = Calendar.getInstance();
            LoginTransaccion loginTransaccion = new LoginTransaccion();
            InetAddress localHost = InetAddress.getLocalHost();
            loginTransaccion.setIp(localHost.getHostAddress());
            loginTransaccion.setMaquina(localHost.getHostName());
            loginTransaccion.setAccion(accion);
            loginTransaccion.setFecha(formato.format(cal.getTime()));
            loginTransaccion.setRegistro(a);
            loginTransaccion.setTabla(tabla);
            loginTransaccion.setUsuario(usuario);
            Map campos = new HashMap();
            campos.put("ip", loginTransaccion.getIp());
            campos.put("maquina", loginTransaccion.getMaquina());
            campos.put("accion", loginTransaccion.getAccion());
            campos.put("tabla", loginTransaccion.getTabla());
            campos.put("usuario", loginTransaccion.getUsuario());
            campos.put("fecha", loginTransaccion.getFecha());
            campos.put("registro", loginTransaccion.getRegistro());
            Crud.insertarLog("log_transaccion", campos);
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
    }

    public static Integer insertarUsuario(String tabla, Map datos, String usuario) {
        try {
            Conexion cc = Conexion.getInstance();
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
            System.out.println(sql);
            int registrosAfectados = st.executeUpdate(sql);
            Crud.loginTransaccion("insert", tabla, usuario, datos);
            cc.desconectar();
            return registrosAfectados;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error,Verifique la informacion que este correcta" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",ex.toString());
        }
        return 0;
    }

    public static boolean buscarUsuario(String usuario) {
        boolean flag = true;
        try {    
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
             String sql = "SELECT * FROM usuario WHERE documento ="+"'"+usuario+"'"+" OR usuario="+"'"+usuario+"'"+";";
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(sql);
             
             while (rs.next()) {
                flag=false;
                break;
            }
            return  flag;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com",e.toString());
        }
        return flag;
    }

}
