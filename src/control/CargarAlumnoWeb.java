package control;

import conectar.Conexion;
import conectar.Conexion1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Alumno;

public class CargarAlumnoWeb extends Thread {

    private List<Alumno> listaAlumnos;
    private static final String descripcionConexion = "inicia conexion";
    private static final String descripcionIngreso = "Se realizo una insercion de una alumno con cedula:";
    private static final String descripcionActualizacion = "se realizo la actualizacion de un alumno con cedula:";
    private static final String descripcionDesconexion= "Se cerro la conexion";
    private static  final SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date fechaLog;
    private final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private Date fechaNacimiento = null;
    private Crud crud;

    @Override
    public void run() {
        try {
            iniciaCarga();
        } catch (ParseException ex) {
            Logger.getLogger(CargarAlumnoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void iniciaCarga() throws ParseException {
        Calendar cal = Calendar.getInstance();
        fechaLog = cal.getTime();
        cargarLogConexion(descripcionConexion, formato1.format(fechaLog));
        Conexion1 cc = Conexion1.getInstance();
        Connection cn = cc.Conectar();

        String sql = "SELECT * FROM  maestro_alumno ";
        try {
            Statement st = cn.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            while (resultado.next()) {
                listaAlumnos = new ArrayList<>();
                Alumno alumno = new Alumno();
                alumno.setCedula(resultado.getString("cedula"));
                alumno.setApellidoPaterno(resultado.getString("apellido_paterno"));
                alumno.setApellidoMaterno(resultado.getString("apellido_materno"));
                alumno.setNombreCompleto(resultado.getString("nombre_completo"));
                alumno.setSexo(resultado.getString("sexo"));
                alumno.setEstadoCivil(resultado.getString("estado_civil"));
                alumno.setPaisNacimiento(resultado.getString("pais_nacimiento"));
                setFechaNacimiento(formato.parse(resultado.getString("fecha_nacimiento")));
                alumno.setFechaNacimiento(getFechaNacimiento());
                alumno.setCiudadNacimiento(resultado.getString("ciudad_nacimiento"));
                alumno.setCiudadDomicilio(resultado.getString("ciudad_domicilio"));
                alumno.setDireccionDomicilio(resultado.getString("direccion_domicilio"));
                alumno.setTelefonoFijo(resultado.getString("telefono_fijo"));
                alumno.setCelular(resultado.getString("celular"));
                alumno.setEmailAlumno(resultado.getString("email_alumno"));
                alumno.setEmailAlternativo(resultado.getString("email_alternativo"));
                alumno.setDiscapacidad(resultado.getString("discapacidad"));
                alumno.setNumeroConadis(resultado.getString("numero_conadis"));
                alumno.setEtnia(resultado.getString("etnia"));
                alumno.setNombreColegio(resultado.getString("nombre_colegio"));
                alumno.setPaisEstudio(resultado.getString("pais_estudio"));
                alumno.setCiudadColegio(resultado.getString("ciudad_colegio"));
                alumno.setPersonaContacto(resultado.getString("persona_contacto"));
                alumno.setParentesco(resultado.getString("parentesco"));
                alumno.setNumeroFijoFamiliar(resultado.getString("numero_fijo_familiar"));
                alumno.setNumeroCelularFamiliar(resultado.getString("numero_celular_familiar"));
                alumno.setEmailFamiliar(resultado.getString("email_familiar"));
                listaAlumnos.add(alumno);
                if (listaAlumnos.isEmpty()) {
                    System.out.println("no hay campos");
                }else {
                    for (Alumno listaAlumno : listaAlumnos) {
                    String alumnoBuscado = buscarAlumno(listaAlumno.getCedula());
                    if (alumnoBuscado == null) {
                        cargarAlumno(listaAlumnos); 
                        cargarLog(descripcionIngreso,listaAlumno.getCedula(), formato1.format(fechaLog));
                   } else {
                        actulizarAlumno(listaAlumnos);
                        cargarLog(descripcionActualizacion,listaAlumno.getCedula(), formato1.format(fechaLog));
                    }
                }
                }
                

            }

        } catch (SQLException ex) {
            Logger.getLogger(CargarAlumnoWeb.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cc.desconectar();
                cargarLogConexion(descripcionDesconexion, formato1.format(fechaLog));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }

    private String buscarAlumno(String cedula) {
        String resultado = null;
        Conexion cc = Conexion.getInstance();
        Connection cn = cc.Conectar();
        try {
            String sql = "SELECT * FROM maestro_alumno where cedula " + "=" + "'" + cedula + "';";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resultado = rs.getString("cedula");
            }
        } catch (Exception e) {

        }
        return resultado;
    }

    private void cargarAlumno(List<Alumno> listAlumno) {
        crud = new Crud();
        try {
            Map campos = new HashMap();
            for (Alumno listAlumno1 : listAlumno) {
                campos.put("cedula", listAlumno1.getCedula());
                campos.put("apellido_paterno", listAlumno1.getApellidoPaterno());
                campos.put("apellido_materno", listAlumno1.getApellidoMaterno());
                campos.put("nombre_completo", listAlumno1.getNombreCompleto());
                campos.put("sexo", listAlumno1.getSexo());
                campos.put("estado_civil", listAlumno1.getEstadoCivil());
                campos.put("pais_nacimiento", listAlumno1.getPaisNacimiento());
                campos.put("fecha_nacimiento", listAlumno1.getFechaNacimiento());
                campos.put("ciudad_nacimiento", listAlumno1.getCiudadNacimiento());
                campos.put("ciudad_domicilio", listAlumno1.getCiudadDomicilio());
                campos.put("direccion_domicilio", listAlumno1.getDireccionDomicilio());
                campos.put("telefono_fijo", listAlumno1.getTelefonoFijo());
                campos.put("celular", listAlumno1.getCelular());
                campos.put("email_alumno", listAlumno1.getEmailAlumno());
                campos.put("email_alternativo", listAlumno1.getEmailAlternativo());
                campos.put("discapacidad", listAlumno1.getDiscapacidad());
                campos.put("numero_conadis", listAlumno1.getNumeroConadis());
                campos.put("etnia", listAlumno1.getEtnia());
                campos.put("nombre_colegio", listAlumno1.getNombreColegio());
                campos.put("pais_estudio", listAlumno1.getPaisEstudio());
                campos.put("ciudad_colegio", listAlumno1.getCiudadColegio());
                campos.put("persona_contacto", listAlumno1.getPersonaContacto());
                campos.put("parentesco", listAlumno1.getParentesco());
                campos.put("numero_fijo_familiar", listAlumno1.getNumeroFijoFamiliar());
                campos.put("numero_celular_familiar", listAlumno1.getNumeroCelularFamiliar());
                campos.put("email_familiar", listAlumno1.getEmailFamiliar());
                crud.insertar("maestro_alumno", campos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar los datos del alumno  WEB ", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }

    }

    private void actulizarAlumno(List<Alumno> listAlumno) {
        crud = new Crud();
        try {
            Map campos = new HashMap();
            for (Alumno listAlumno1 : listAlumno) {
                campos.put("cedula", listAlumno1.getCedula());
                campos.put("apellido_paterno", listAlumno1.getApellidoPaterno());
                campos.put("apellido_materno", listAlumno1.getApellidoMaterno());
                campos.put("nombre_completo", listAlumno1.getNombreCompleto());
                campos.put("sexo", listAlumno1.getSexo());
                campos.put("estado_civil", listAlumno1.getEstadoCivil());
                campos.put("pais_nacimiento", listAlumno1.getPaisNacimiento());
                campos.put("fecha_nacimiento", listAlumno1.getFechaNacimiento());
                campos.put("ciudad_nacimiento", listAlumno1.getCiudadNacimiento());
                campos.put("ciudad_domicilio", listAlumno1.getCiudadDomicilio());
                campos.put("direccion_domicilio", listAlumno1.getDireccionDomicilio());
                campos.put("telefono_fijo", listAlumno1.getTelefonoFijo());
                campos.put("celular", listAlumno1.getCelular());
                campos.put("email_alumno", listAlumno1.getEmailAlumno());
                campos.put("email_alternativo", listAlumno1.getEmailAlternativo());
                campos.put("discapacidad", listAlumno1.getDiscapacidad());
                campos.put("numero_conadis", listAlumno1.getNumeroConadis());
                campos.put("etnia", listAlumno1.getEtnia());
                campos.put("nombre_colegio", listAlumno1.getNombreColegio());
                campos.put("pais_estudio", listAlumno1.getPaisEstudio());
                campos.put("ciudad_colegio", listAlumno1.getCiudadColegio());
                campos.put("persona_contacto", listAlumno1.getPersonaContacto());
                campos.put("parentesco", listAlumno1.getParentesco());
                campos.put("numero_fijo_familiar", listAlumno1.getNumeroFijoFamiliar());
                campos.put("numero_celular_familiar", listAlumno1.getNumeroCelularFamiliar());
                campos.put("email_familiar", listAlumno1.getEmailFamiliar());
                crud.actualizarWeb("maestro_alumno", "cedula", listAlumno1.getCedula(), campos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un erro al actualizar alumno de la WEB", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarLog(String descripcion,String cedula, String fechaLog) {
        try {
            String ingreso = descripcion.concat(cedula);
            crud = new Crud();
            Map campoLog = new HashMap();
            campoLog.put("descripcion", descripcion.concat(cedula));
            campoLog.put("fecha_creacion", fechaLog);
            crud.insertar("log", campoLog);
        } catch (Exception e) {
            System.out.println("Error al guardar el log");
        }
    }
 private void cargarLogConexion(String descripcion, String fechaLog) {
        try {
            crud = new Crud();
            Map campoLog = new HashMap();
            campoLog.put("descripcion", descripcion);
            campoLog.put("fecha_creacion", fechaLog);
            crud.insertar("log", campoLog);
        } catch (Exception e) {
            System.out.println("Error al guardar el log");
        }
    }
    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
