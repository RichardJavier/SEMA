/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.Crud;
import control.EnviaEmail;
import control.VerificadorCedula;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.AlumnoDao;
import logica.MetodosGeneralesDao;
import modelo.Alumno;

public class FormularioAlumno extends javax.swing.JDialog {

    public static FrmAlumno frmAlumno;
    private Integer idAlumno;
    ResultSet resultSet;
    Map campos;
    AlumnoDao alumnoDao;
    Alumno alumno;
    Crud crud;
    MetodosGeneralesDao generalesDao;
    private boolean ced;

    public FormularioAlumno(FrmAlumno parent, boolean modal) {
        FormularioAlumno.frmAlumno = parent;
        this.setModal(modal);
        initComponents();
        alumno = new Alumno();
        guardarBtn.setEnabled(false);
        generalesDao = new MetodosGeneralesDao();
        cargaCiudad();
        cargaPais();
    }

    public FormularioAlumno(FrmAlumno parent, boolean modal, Integer idAlumno) {
        FormularioAlumno.frmAlumno = parent;
        this.setModal(modal);
        this.idAlumno = idAlumno;
        initComponents();
        guardarBtn.setEnabled(false);
        alumno = new Alumno();
        generalesDao = new MetodosGeneralesDao();
        cargaCiudad();
        cargaPais();
        if (idAlumno > 0) {
            alumnoDao = new AlumnoDao();
            try {
                resultSet = alumnoDao.consulta(idAlumno);
                while (resultSet.next()) {
                    alumno.setIdAlumno(Integer.parseInt(resultSet.getString("id_alumno")));
                    cedulaTxt.setText(resultSet.getString("cedula"));
                    apellidoPaternoTxt.setText(resultSet.getString("apellido_paterno"));
                    apellidoMaternoTxt.setText(resultSet.getString("apellido_materno"));
                    nombresCompletosTxt.setText(resultSet.getString("nombre_completo"));
                    fechaNacimientoChooser.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fecha_nacimiento")));
                    sexoCmb.setSelectedItem(resultSet.getString("sexo"));
                    estadoCivilCmb.setSelectedItem(resultSet.getString("estado_civil"));
                    paisNaciCmb.setSelectedItem(resultSet.getString("pais_nacimiento"));
                    ciudadNaciCmb.setSelectedItem(resultSet.getString("ciudad_nacimiento"));
                    etniaTxt.setText(resultSet.getString("etnia"));
                    telefonoFijoAlumnoTxt.setText(resultSet.getString("telefono_fijo"));
                    celularAlumnoTxt.setText(resultSet.getString("celular"));
                    emailAlumnoTxt.setText(resultSet.getString("email_alumno"));
                    emailAlternativoTxt.setText(resultSet.getString("email_alternativo"));
                    direccionTxt.setText(resultSet.getString("direccion_domicilio"));
                    ciudadDomicilioTxt.setText(resultSet.getString("ciudad_domicilio"));
                    personaContactoTxt.setText(resultSet.getString("persona_contacto"));
                    tlfFamiliarTxt.setText(resultSet.getString("numero_fijo_familiar"));
                    parentescoTxt.setText(resultSet.getString("parentesco"));
                    celularFamiliarTxt.setText(resultSet.getString("numero_celular_familiar"));
                    emailFamiliarTxt.setText(resultSet.getString("email_familiar"));
                    discapacidadTxt.setText(resultSet.getString("discapacidad"));
                    numeroConadisTxt.setText(resultSet.getString("numero_conadis"));
                    nombreColegioTxt.setText(resultSet.getString("nombre_colegio"));
                    paisEstCmb.setSelectedItem(resultSet.getString("pais_estudio"));
                    ciudadEstCmb.setSelectedItem(resultSet.getString("ciudad_colegio"));

                }
            } catch (SQLException | ParseException e) {
                EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apellidoPaternoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellidoMaternoTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombresCompletosTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fechaNacimientoChooser = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        etniaTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        sexoCmb = new javax.swing.JComboBox();
        estadoCivilCmb = new javax.swing.JComboBox();
        paisNaciCmb = new javax.swing.JComboBox();
        ciudadNaciCmb = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        telefonoFijoAlumnoTxt = new javax.swing.JTextField();
        celularAlumnoTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        emailAlumnoTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        emailAlternativoTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tlfFamiliarTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        parentescoTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        celularFamiliarTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        emailFamiliarTxt = new javax.swing.JTextField();
        personaContactoTxt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        direccionTxt = new javax.swing.JTextField();
        ciudadDomicilioTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        discapacidadTxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        numeroConadisTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        xNCO = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        nombreColegioTxt = new javax.swing.JTextField();
        paisEstCmb = new javax.swing.JComboBox();
        ciudadEstCmb = new javax.swing.JComboBox();
        validarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO DE REGISTRO DE ALUMNO");
        setIconImage(new ImageIcon(getClass().getResource("/recursos/iconPr.png")).getImage());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales"));

        jLabel1.setText("Cedula");

        jLabel2.setText("Apellido Paterno");

        jLabel3.setText("Apellido Materno");

        jLabel4.setText("Nombres Completos");

        jLabel5.setText("Sexo");

        jLabel6.setText("Estado Civil");

        jLabel7.setText("Pais Nacimiento");

        jLabel8.setText("Fecha Nacimiento");

        jLabel11.setText("Etnia");

        jLabel19.setText("Ciudad Nacimiento");

        sexoCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "MASCULINO", "FEMENINO" }));

        estadoCivilCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "CASADO", "SOLTERO", "VIUDO", "DIVORCIADO", "UNION DE HECHO", "OTRO" }));

        paisNaciCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        ciudadNaciCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nombresCompletosTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cedulaTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(apellidoPaternoTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(apellidoMaternoTxt, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(fechaNacimientoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(sexoCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(etniaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(estadoCivilCmb, 0, 129, Short.MAX_VALUE)
                            .addComponent(paisNaciCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ciudadNaciCmb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(apellidoPaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellidoMaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(paisNaciCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nombresCompletosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(ciudadNaciCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(sexoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(estadoCivilCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etniaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(jLabel8)
                    .addComponent(fechaNacimientoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Contactos"));

        jLabel13.setText("Telf Fijo Alumno");

        jLabel12.setText("Celular Alumno");

        jLabel9.setText("Email Alumno");

        jLabel10.setText("Email Alternativo");

        jLabel14.setText("Persona Contacto");

        jLabel15.setText("Parentesco");

        jLabel16.setText("Tlf Familiar");

        jLabel17.setText("Celular Familiar");

        jLabel18.setText("Email Familiar");

        jLabel23.setText("Direcccion");

        jLabel24.setText("Ciudad Domicilio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailAlumnoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(emailAlternativoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(celularAlumnoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(telefonoFijoAlumnoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(direccionTxt)
                    .addComponent(ciudadDomicilioTxt))
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(parentescoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailFamiliarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tlfFamiliarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(celularFamiliarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 78, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(personaContactoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefonoFijoAlumnoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personaContactoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel13))
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(celularAlumnoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(parentescoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailAlumnoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17)
                    .addComponent(celularFamiliarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailAlternativoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel18)
                    .addComponent(emailFamiliarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(direccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(tlfFamiliarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(ciudadDomicilioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Adicionales"));

        jLabel20.setText("Discapacidad");

        jLabel21.setText("Numero de Conadis");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(discapacidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(numeroConadisTxt)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(discapacidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(numeroConadisTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Institucion"));

        xNCO.setText("Nombre Colegio");

        jLabel25.setText("Pais Estudio");

        jLabel26.setText("Ciudad Colegio");

        paisEstCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        ciudadEstCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xNCO)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreColegioTxt)
                            .addComponent(paisEstCmb, 0, 138, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ciudadEstCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xNCO)
                    .addComponent(nombreColegioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(paisEstCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(ciudadEstCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(validarBtn)
                        .addGap(26, 26, 26)
                        .addComponent(guardarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardarBtn)
                            .addComponent(cancelarBtn)
                            .addComponent(validarBtn))
                        .addGap(39, 39, 39)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        if (validaForm() == false) {
            if (ced != false) {
                JOptionPane.showMessageDialog(null, "Se encuentran campos vacíos por favor ingresar información", "Error campos vacios", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_validarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        crud = new Crud();
        cargarAlumno();
        if (idAlumno == 0) {
            crud.insertarM("maestro_alumno", campos, Ingreso.getUsuario().getNombre());
            this.dispose();
        } else {
            crud.actualizarM("maestro_alumno", "id_alumno", alumno.getIdAlumno(), campos, Ingreso.getUsuario().getNombre());
            this.dispose();
        }
    }//GEN-LAST:event_guardarBtnActionPerformed
    private Map cargarAlumno() {
        try {
            campos = new HashMap();
            alumno.setCedula(cedulaTxt.getText());
            alumno.setApellidoPaterno(apellidoPaternoTxt.getText().toUpperCase());
            alumno.setApellidoMaterno(apellidoMaternoTxt.getText().toUpperCase());
            alumno.setCelular(celularAlumnoTxt.getText().toUpperCase());
            alumno.setCiudadColegio(ciudadEstCmb.getSelectedItem().toString());
            alumno.setCiudadDomicilio(ciudadDomicilioTxt.getText().toUpperCase());
            alumno.setCiudadNacimiento(ciudadNaciCmb.getSelectedItem().toString());
            alumno.setDireccionDomicilio(direccionTxt.getText().toUpperCase());
            alumno.setDiscapacidad(discapacidadTxt.getText().toUpperCase());
            alumno.setEmailAlternativo(emailAlternativoTxt.getText().toLowerCase());
            alumno.setEmailAlumno(emailAlumnoTxt.getText().toLowerCase());
            alumno.setEmailFamiliar(emailFamiliarTxt.getText().toLowerCase());
            alumno.setEstadoCivil((String.valueOf(estadoCivilCmb.getSelectedItem())));
            alumno.setEtnia(etniaTxt.getText());
            alumno.setFechaNacimiento(fechaNacimientoChooser.getDate());
            alumno.setNombreColegio(nombreColegioTxt.getText().toUpperCase());
            alumno.setNombreCompleto(nombresCompletosTxt.getText().toUpperCase());
            alumno.setNumeroCelularFamiliar(celularFamiliarTxt.getText().toUpperCase());
            alumno.setNumeroConadis(numeroConadisTxt.getText().toUpperCase());
            alumno.setNumeroFijoFamiliar(tlfFamiliarTxt.getText().toUpperCase());
            alumno.setPaisEstudio(paisEstCmb.getSelectedItem().toString());
            alumno.setPaisNacimiento(paisEstCmb.getSelectedItem().toString());
            alumno.setParentesco(parentescoTxt.getText().toUpperCase());
            alumno.setPersonaContacto(personaContactoTxt.getText().toUpperCase());
            alumno.setSexo(String.valueOf(sexoCmb.getSelectedItem()));
            alumno.setTelefonoFijo(telefonoFijoAlumnoTxt.getText().toUpperCase());

            campos.put("apellido_materno", alumno.getApellidoMaterno());
            campos.put("apellido_paterno", alumno.getApellidoPaterno());
            campos.put("cedula", alumno.getCedula());
            campos.put("celular", alumno.getCelular());
            campos.put("ciudad_colegio", alumno.getCiudadColegio());
            campos.put("ciudad_domicilio", alumno.getCiudadDomicilio());
            campos.put("ciudad_nacimiento", alumno.getCiudadNacimiento());
            campos.put("direccion_domicilio", alumno.getDireccionDomicilio());
            campos.put("discapacidad", alumno.getDiscapacidad());
            campos.put("email_alternativo", alumno.getEmailAlternativo());
            campos.put("email_alumno", alumno.getEmailAlumno());
            campos.put("email_familiar", alumno.getEmailFamiliar());
            campos.put("estado_civil", alumno.getEstadoCivil());
            campos.put("etnia", alumno.getEtnia());
            campos.put("fecha_nacimiento", alumno.getFechaNacimiento());
            campos.put("nombre_colegio", alumno.getNombreColegio());
            campos.put("nombre_completo", alumno.getNombreCompleto());
            campos.put("numero_celular_familiar", alumno.getNumeroCelularFamiliar());
            campos.put("numero_conadis", alumno.getNumeroConadis());
            campos.put("numero_fijo_familiar", alumno.getNumeroFijoFamiliar());
            campos.put("pais_estudio", alumno.getPaisEstudio());
            campos.put("pais_nacimiento", alumno.getPaisNacimiento());
            campos.put("parentesco", alumno.getParentesco());
            campos.put("persona_contacto", alumno.getPersonaContacto());
            campos.put("sexo", alumno.getSexo());
            campos.put("telefono_fijo", alumno.getTelefonoFijo());
            return campos;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }
        return campos;
    }

    private boolean validaForm() {
        boolean resultado = true;
        VerificadorCedula verificador = new VerificadorCedula();
        JTextField[] texto = {apellidoPaternoTxt, apellidoMaternoTxt, nombresCompletosTxt,
            etniaTxt, telefonoFijoAlumnoTxt, emailAlumnoTxt, direccionTxt, ciudadDomicilioTxt, personaContactoTxt,
            parentescoTxt, tlfFamiliarTxt, nombreColegioTxt};
        JComboBox[] combo = {sexoCmb, estadoCivilCmb, paisNaciCmb, ciudadNaciCmb, paisEstCmb, ciudadEstCmb};
        if (idAlumno == 0) {
            for (JTextField texto1 : texto) {
                if (texto1.getText().trim().isEmpty()) {
                    texto1.setBackground(Color.red);
                    resultado = false;
                } else {
                    texto1.setBackground(null);
                    resultado = true;
                }

            }
        } else {
            for (JTextField texto1 : texto) {
                if (texto1.getText().trim().isEmpty()) {
                    texto1.setBackground(Color.red);
                    resultado = false;
                    break;
                } else {
                    texto1.setBackground(null);
                    resultado = true;
                }

            }
        }

        if (resultado == true) {
            if (idAlumno == 0) {
                for (JComboBox combo1 : combo) {
                    if (combo1.getSelectedIndex() == 0) {
                        combo1.setBackground(Color.red);
                        resultado = false;
                    } else {
                        combo1.setBackground(null);
                        resultado = true;
                    }
                }
            } else {
                for (JComboBox combo1 : combo) {
                    if (combo1.getSelectedIndex() == 0) {
                        combo1.setBackground(Color.red);
                        resultado = false;
                        break;
                    } else {
                        combo1.setBackground(null);
                        resultado = true;
                    }
                }

            }

        }
        if (!cedulaTxt.getText().trim().isEmpty()) {
            if (cedulaTxt.getText().length() == 10) {
                if (verificador.verificaCedula(cedulaTxt.getText()) == true) {
                    resultado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Cedula incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                    ced = false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "La cedula debe tener 10 digitos", "Error cedula", JOptionPane.ERROR_MESSAGE);
                resultado = false;
                ced = false;
            }

        }
        if (resultado == true) {
            ocultaCampos();
            guardarBtn.setEnabled(true);
            validarBtn.setEnabled(false);
        }
        return resultado;
    }

    private void ocultaCampos() {
        JTextField[] texto = {cedulaTxt, apellidoPaternoTxt, apellidoMaternoTxt,
            nombresCompletosTxt, celularAlumnoTxt, celularFamiliarTxt,
            ciudadDomicilioTxt, direccionTxt, discapacidadTxt,
            emailAlternativoTxt, emailAlumnoTxt, emailFamiliarTxt, etniaTxt, nombreColegioTxt,
            numeroConadisTxt, parentescoTxt, personaContactoTxt,
            telefonoFijoAlumnoTxt, tlfFamiliarTxt};
        for (JTextField texto1 : texto) {
            texto1.setEnabled(false);
        }
        paisEstCmb.setEnabled(false);
        paisNaciCmb.setEnabled(false);
        ciudadEstCmb.setEnabled(false);
        ciudadNaciCmb.setEnabled(false);
        estadoCivilCmb.setEnabled(false);
        sexoCmb.setEnabled(false);
        fechaNacimientoChooser.setEnabled(false);
    }

    private void cargaCiudad() {
        try {
            resultSet = generalesDao.cargaCiudad();
            while (resultSet.next()) {
                String ciudad = resultSet.getString("ciudad");
                ciudadNaciCmb.addItem(ciudad);
                ciudadEstCmb.addItem(ciudad);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void cargaPais() {
        try {
            resultSet = generalesDao.cargaPais();
            while (resultSet.next()) {
                String pais = resultSet.getString("pais");
                paisNaciCmb.addItem(pais);
                paisEstCmb.addItem(pais);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioAlumno dialog = new FormularioAlumno(frmAlumno, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoMaternoTxt;
    private javax.swing.JTextField apellidoPaternoTxt;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JTextField celularAlumnoTxt;
    private javax.swing.JTextField celularFamiliarTxt;
    private javax.swing.JTextField ciudadDomicilioTxt;
    private javax.swing.JComboBox ciudadEstCmb;
    private javax.swing.JComboBox ciudadNaciCmb;
    private javax.swing.JTextField direccionTxt;
    private javax.swing.JTextField discapacidadTxt;
    private javax.swing.JTextField emailAlternativoTxt;
    private javax.swing.JTextField emailAlumnoTxt;
    private javax.swing.JTextField emailFamiliarTxt;
    private javax.swing.JComboBox estadoCivilCmb;
    private javax.swing.JTextField etniaTxt;
    private com.toedter.calendar.JDateChooser fechaNacimientoChooser;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nombreColegioTxt;
    private javax.swing.JTextField nombresCompletosTxt;
    private javax.swing.JTextField numeroConadisTxt;
    private javax.swing.JComboBox paisEstCmb;
    private javax.swing.JComboBox paisNaciCmb;
    private javax.swing.JTextField parentescoTxt;
    private javax.swing.JTextField personaContactoTxt;
    private javax.swing.JComboBox sexoCmb;
    private javax.swing.JTextField telefonoFijoAlumnoTxt;
    private javax.swing.JTextField tlfFamiliarTxt;
    private javax.swing.JButton validarBtn;
    private javax.swing.JLabel xNCO;
    // End of variables declaration//GEN-END:variables
}
