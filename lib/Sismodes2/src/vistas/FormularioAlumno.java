/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosAlumnoControl;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metodos.CargarDatos;
import metodos.Proceso;
import metodos.VerificadorCedula;
import tablas.Materia;
import tablas.Resumen;

/**
 *
 * @author Toshiba
 */
public class FormularioAlumno extends javax.swing.JDialog {

    private int pkAlumno;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private DatosAlumnoControl alumnoControl;
    public static FrmAlumno alumno;
    private Materia mat;
    private Resumen resumen;
    ArrayList<Materia> materias;
    ArrayList<Resumen> listaResumen;
    VerificadorCedula vc;
    CargarDatos cd;
    Calendar cal = Calendar.getInstance();
    private String cedula, nombres, direccion, celular, telefono, colegio, provColegio,
            ciuColegio, tipColegio, tituloBachiller,
            nomPadre, nomMadre, sexo, estadoCivil, email, representante,
            ocuPadre, ocuMadre, codigoPeriodo, valSexo, ciclo, tipoMatricula, creditos;
    private int edad, pais, provincia, canton, ciudad, especialidad, semestre, paralelo, idAlumno, idconMat;
    private int idMateria, idProfe, idMatricula, idconfigNotas, idmalla,ponderacion;
    private Date fechaNacimiento, fechaIng, fechaPadre, fechaMadre;
    private double porNota, porTuto;
    Map camposAlumno;
    Map camposMatricula;
    boolean formValido;

    @SuppressWarnings("static-access")
    public FormularioAlumno(FrmAlumno parent, boolean modal) throws SQLException {
        this.alumno = parent;
        this.setModal(modal);
        initComponents();
        vc = new VerificadorCedula();
        alumnoControl = new DatosAlumnoControl();
        cd = new CargarDatos();
        this.setSize(1180, 1100);
        fechaIngresoChooser.setDate(cal.getTime());
        fechaIngresoChooser.setEnabled(false);
        cargarPais();
        cargarMadre();
        cargarPadre();
        cargarEspecialidad();
        cargarSemestre();
        cargarParalelo();
        edadSpinner.setRequestFocusEnabled(true);
        fechaIngresoChooser.setDate(cal.getTime());
        fechaIngresoChooser.setEnabled(false);
    }

    public FormularioAlumno(FrmAlumno parent, boolean modal, Integer pkAlumno) throws SQLException {
        this.alumno = parent;
        this.setModal(modal);
        initComponents();
        alumnoControl = new DatosAlumnoControl();
        this.pkAlumno = pkAlumno;
        vc = new VerificadorCedula();
        cd = new CargarDatos();
        fechaIngresoChooser.setDate(cal.getTime());
        fechaIngresoChooser.setEnabled(false);
        this.setSize(1200, 1100);
        cargarPais();
        cargarMadre();
        cargarPadre();
        cargarEspecialidad();
        cargarSemestre();
        cargarParalelo();
        edadSpinner.setRequestFocusEnabled(true);
    }

    private void iniciaCreacion() {
        cedula = cedulaTxt.getText();
        nombres = nombreTxt.getText();
        direccion = direcciontxt.getText();
        celular = celulartxt.getText();
        telefono = telefonotxt.getText();
        colegio = nomClgotxt.getText();
        provColegio = prvClgotxt.getText();
        ciuColegio = ciudadClgotxt.getText();
        tipColegio = (String) cmbTipColg.getSelectedItem();
        tituloBachiller = (String) cmbBachiller.getSelectedItem();
        nomPadre = nomPadretxt.getText();
        representante = representantetxt.getText();
        email = emailtxt.getText();
        nomMadre = nomMadretxt.getText();
        edad = (int) edadSpinner.getValue();
        sexo = valSexo;
        estadoCivil = (String) cmbEstadoCivil.getSelectedItem();
        pais = cmbPais.getSelectedIndex();
        provincia = cmbProvincia.getSelectedIndex();
        canton = cmbCanton.getSelectedIndex();
        ciudad = cmbCiudad.getSelectedIndex();
        especialidad = cmbEspecialidad.getSelectedIndex();
        semestre = cmbSemestre.getSelectedIndex();
        tipoMatricula = (String) cmbMatricula.getSelectedItem();
        paralelo = cmbParalelo.getSelectedIndex();
        ocuPadre = (String) cmbOcupacionPadre.getSelectedItem();
        ocuMadre = cmbOcuMadre.getSelectedItem().toString();
        fechaNacimiento = fecNacimientoChooser.getDate();
        fechaIng = fechaIngresoChooser.getDate();
        fechaPadre = fecNacPadreChooser.getDate();
        fechaMadre = fecNacMadreChooser.getDate();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoSexo = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        cedulaTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fecNacimientoChooser = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        rdbFemenino = new javax.swing.JRadioButton();
        rdbMasculino = new javax.swing.JRadioButton();
        direcciontxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbEstadoCivil = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        telefonotxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        celulartxt = new javax.swing.JTextField();
        cmbPais = new javax.swing.JComboBox();
        cmbProvincia = new javax.swing.JComboBox();
        cmbCanton = new javax.swing.JComboBox();
        cmbCiudad = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        representantetxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        emailtxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cmbBachiller = new javax.swing.JComboBox();
        fechaIngresoChooser = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        cmbEspecialidad = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        edadSpinner = new javax.swing.JSpinner();
        cmbSemestre = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        cmbMatricula = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cmbParalelo = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nomClgotxt = new javax.swing.JTextField();
        prvClgotxt = new javax.swing.JTextField();
        ciudadClgotxt = new javax.swing.JTextField();
        cmbTipColg = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        nomPadretxt = new javax.swing.JTextField();
        fecNacPadreChooser = new com.toedter.calendar.JDateChooser();
        cmbOcupacionPadre = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cmbOcuMadre = new javax.swing.JComboBox();
        fecNacMadreChooser = new com.toedter.calendar.JDateChooser();
        nomMadretxt = new javax.swing.JTextField();
        ingresarBtn = new javax.swing.JButton();
        borrarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Alumno"));

        cedulaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaTxtActionPerformed(evt);
            }
        });
        cedulaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaTxtKeyTyped(evt);
            }
        });

        jLabel1.setText("Cedula");

        jLabel3.setText("Fecha Nacimiento");

        jLabel4.setText("Nombre");

        nombreTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreTxtKeyTyped(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sexo"));

        grupoSexo.add(rdbFemenino);
        rdbFemenino.setText("Femenino");
        rdbFemenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbFemeninoActionPerformed(evt);
            }
        });

        grupoSexo.add(rdbMasculino);
        rdbMasculino.setText("Masculino");
        rdbMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMasculinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbFemenino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdbMasculino)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbFemenino)
                    .addComponent(rdbMasculino)))
        );

        direcciontxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direcciontxtKeyTyped(evt);
            }
        });

        jLabel5.setText("Direccion");

        jLabel6.setText("Estado Civil");

        cmbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "SOLTERO/A", "CASADO/A", "UNION LIBRE", "VIUDO" }));

        jLabel7.setText("Telefono");

        telefonotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonotxtKeyTyped(evt);
            }
        });

        jLabel8.setText("Celular");

        celulartxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celulartxtKeyTyped(evt);
            }
        });

        cmbPais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        cmbPais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPaisItemStateChanged(evt);
            }
        });

        cmbProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProvinciaItemStateChanged(evt);
            }
        });

        cmbCanton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCantonItemStateChanged(evt);
            }
        });

        jLabel13.setText("Pais");

        jLabel14.setText("Provincia");

        jLabel15.setText("Canton");

        jLabel16.setText("Ciudad");

        jLabel17.setText("Representante");

        representantetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                representantetxtKeyTyped(evt);
            }
        });

        jLabel18.setText("Email");

        jLabel19.setText("Tipo Bachiller");

        cmbBachiller.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "BACHILLER EN CIENCIAS", "BACHILLER TECNICO", " " }));

        jLabel20.setText("Fecha Ingreso");

        cmbEspecialidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        jLabel27.setText("Especialidad");

        jLabel28.setText("Edad");

        edadSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        edadSpinner.setFocusable(false);
        edadSpinner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                edadSpinnerMousePressed(evt);
            }
        });
        edadSpinner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edadSpinnerKeyTyped(evt);
            }
        });

        cmbSemestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        cmbSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemestreActionPerformed(evt);
            }
        });

        jLabel29.setText("Semestre");

        cmbMatricula.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "ORDINARIA", "EXTRAORDINARIA" }));

        jLabel30.setText("Tipo Matricula");

        jLabel31.setText("Paralelo");

        cmbParalelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel27)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbMatricula, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEspecialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cedulaTxt)
                            .addComponent(nombreTxt)
                            .addComponent(fecNacimientoChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))))
                .addGap(112, 112, 112)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailtxt)
                            .addComponent(representantetxt)
                            .addComponent(celulartxt)
                            .addComponent(telefonotxt)
                            .addComponent(cmbEstadoCivil, 0, 143, Short.MAX_VALUE)
                            .addComponent(direcciontxt)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(30, 30, 30)
                        .addComponent(edadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel29)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbBachiller, 0, 165, Short.MAX_VALUE)
                                    .addComponent(fechaIngresoChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cmbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(cmbParalelo, 0, 159, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbCanton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direcciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cmbCanton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecNacimientoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(celulartxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(representantetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbBachiller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaIngresoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(edadSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel31)
                            .addComponent(cmbParalelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Colegio"));

        jLabel9.setText("Nombre colegio ");

        jLabel10.setText("Provincia Colegio");

        jLabel11.setText("Ciudad Colegio");

        jLabel12.setText("Tipo Colegio ");

        nomClgotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomClgotxtKeyTyped(evt);
            }
        });

        prvClgotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prvClgotxtActionPerformed(evt);
            }
        });
        prvClgotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prvClgotxtKeyTyped(evt);
            }
        });

        ciudadClgotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ciudadClgotxtKeyTyped(evt);
            }
        });

        cmbTipColg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "PARTICULAR", "FISCOMICIONAL", "FISCAL", "MUNICIPAL", " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipColg, javax.swing.GroupLayout.Alignment.TRAILING, 0, 127, Short.MAX_VALUE)
                    .addComponent(ciudadClgotxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(prvClgotxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nomClgotxt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(nomClgotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(prvClgotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ciudadClgotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbTipColg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Padres"));

        jLabel21.setText("Nombres Padre");

        jLabel22.setText("Fecha Nacimiento");

        jLabel23.setText("Ocupacion");

        nomPadretxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomPadretxtKeyTyped(evt);
            }
        });

        cmbOcupacionPadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        jLabel24.setText("Nombres Madre");

        jLabel25.setText("Fecha Nacimiento");

        jLabel26.setText("Ocupacion ");

        cmbOcuMadre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        nomMadretxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nomMadretxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomMadretxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fecNacPadreChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(cmbOcupacionPadre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomPadretxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fecNacMadreChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomMadretxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbOcuMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(nomMadretxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(fecNacMadreChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(cmbOcuMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(nomPadretxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(fecNacPadreChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(cmbOcupacionPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        ingresarBtn.setText("Ingresar");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });

        borrarBtn.setText("Borrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(ingresarBtn)
                        .addGap(69, 69, 69)
                        .addComponent(borrarBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingresarBtn)
                    .addComponent(borrarBtn))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cedulaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaTxtActionPerformed
    private boolean validaFormulario() {
        formValido = true;
        String val = String.valueOf(edadSpinner.getValue());
        if (cedulaTxt.getText().length() > 0 && nombreTxt.getText().length() > 0
                && direcciontxt.getText().length() > 0 && telefonotxt.getText().length() > 0
                && celulartxt.getText().length() > 0 && representantetxt.getText().length() > 0
                && emailtxt.getText().length() > 0 && nomClgotxt.getText().length() > 0 && prvClgotxt.getText().length() > 0
                && ciudadClgotxt.getText().length() > 0 && nomPadretxt.getText().length() > 0 && nomMadretxt.getText().length() > 0) {
            formValido = true;
            if (cmbEstadoCivil.getSelectedIndex() > 0 && cmbPais.getSelectedIndex() > 0 && cmbProvincia.getSelectedIndex() > 0
                    && cmbCanton.getSelectedIndex() > 0 && cmbCiudad.getSelectedIndex() > 0 && cmbBachiller.getSelectedIndex() > 0
                    && cmbSemestre.getSelectedIndex() > 0 && cmbParalelo.getSelectedIndex() > 0 && cmbTipColg.getSelectedIndex() > 0
                    && cmbOcupacionPadre.getSelectedIndex() > 0 && cmbOcuMadre.getSelectedIndex() > 0) {
                formValido = true;
            } else {
                JOptionPane.showMessageDialog(null, "Verifique que los combos estes seleccionados", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (rdbFemenino.isSelected() == false && rdbMasculino.isSelected() == false) {
                formValido = false;
                JOptionPane.showMessageDialog(null, "Debe escoger un sexo para el alumno", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (val.compareTo("0") == 0) {
                formValido = false;
                JOptionPane.showMessageDialog(null, "Debe ingresar la edad del alumno", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            formValido = false;
        }
        return formValido;
    }
    private void prvClgotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prvClgotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prvClgotxtActionPerformed

    private void cmbPaisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPaisItemStateChanged
        cmbProvincia.removeAllItems();
        cmbProvincia.addItem("SELECCIONE");
        try {

            @SuppressWarnings("LocalVariableHidesMemberVariable")
            int pais;
            pais = cmbPais.getSelectedIndex();
            ResultSet rs = cd.provincia(pais);
            while (rs.next()) {
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                String provincia = rs.getString("provincia");
                cmbProvincia.addItem(provincia);
            }

        } catch (SQLException e) {
            Logger.getLogger(FormularioAlumno.class.getName()).log(Level.SEVERE, null, e);
        }


    }//GEN-LAST:event_cmbPaisItemStateChanged

    private void cmbProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProvinciaItemStateChanged
        cmbCanton.removeAllItems();
        cmbCanton.addItem("SELECCIONE");
        try {

            @SuppressWarnings("LocalVariableHidesMemberVariable")
            int pais, provincia;
            pais = cmbPais.getSelectedIndex();
            provincia = cmbProvincia.getSelectedIndex();
            ResultSet rs = cd.canton(pais, provincia);
            while (rs.next()) {
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                String canton;
                canton = rs.getString("canton");
                cmbCanton.addItem(canton);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbProvinciaItemStateChanged

    private void cmbCantonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCantonItemStateChanged
        try {
            cmbCiudad.removeAllItems();
            cmbCiudad.addItem("SELECCIONE");
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            int pais, provincia, canton;
            pais = cmbPais.getSelectedIndex();
            provincia = cmbProvincia.getSelectedIndex();
            canton = cmbCanton.getSelectedIndex();
            ResultSet rs = cd.parroquia(pais, provincia, canton);
            while (rs.next()) {
                String parroquia = rs.getString("parroquia");
                cmbCiudad.addItem(parroquia);
            }
        } catch (SQLException e) {
            Logger.getLogger(FormularioAlumno.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_cmbCantonItemStateChanged

    private void rdbFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbFemeninoActionPerformed
        valSexo = "F";
    }//GEN-LAST:event_rdbFemeninoActionPerformed

    private void rdbMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMasculinoActionPerformed
        valSexo = "M";
    }//GEN-LAST:event_rdbMasculinoActionPerformed
    @SuppressWarnings("UnusedAssignment")
    private boolean valida() {
        @SuppressWarnings("UnusedAssignment")
        boolean respuesta;
        respuesta = true;
        camposAlumno = new HashMap();
        camposMatricula = new HashMap();
        try {
            if (validaFormulario()) {
                iniciaCreacion();
                cargarPk();
                camposAlumno.put("cedula", cedula);
                camposAlumno.put("nom_completos", nombres);
                camposAlumno.put("fecha_nacimiento", fechaNacimiento);
                camposAlumno.put("edad", edad);
                camposAlumno.put("sexo", sexo);
                camposAlumno.put("direccion", direccion);
                camposAlumno.put("estado_civil", estadoCivil);
                camposAlumno.put("telefono", telefono);
                camposAlumno.put("celular", celular);
                camposAlumno.put("colegio", colegio);
                camposAlumno.put("provincia_clg", provColegio);
                camposAlumno.put("ciudad_clg", ciuColegio);
                camposAlumno.put("tipo_clg", tipColegio);
                camposAlumno.put("nombre_padre", nomPadre);
                camposAlumno.put("fec_nac_padre", fechaPadre);
                camposAlumno.put("ocupacion_padre", ocuPadre);
                camposAlumno.put("nombre_madre", nomMadre);
                camposAlumno.put("fec_nac_madre", fechaMadre);
                camposAlumno.put("ocu_madre", ocuMadre);
                camposAlumno.put("representante", representante);
                camposAlumno.put("email_alum", email);
                camposAlumno.put("titulo_bachiller", tituloBachiller);
                camposAlumno.put("fecha_inscripcion", fechaIng);
                camposAlumno.put("id_parroquia", ciudad);
                camposAlumno.put("id_canton", canton);
                camposAlumno.put("id_provincia", provincia);
                camposAlumno.put("id_pais", pais);
                camposMatricula.put("cedula", cedula);
                camposMatricula.put("id_semestre", semestre);
                camposMatricula.put("id_especialidad", especialidad);
                camposMatricula.put("id_paralelo", paralelo);
                camposMatricula.put("tipo_matricula", tipoMatricula);
                camposMatricula.put("fecha_creacion", fechaIng);
                respuesta = true;
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son Obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                respuesta = false;
            }

        } catch (SQLException | NumberFormatException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
            respuesta = true;
        }

        return respuesta;
    }
    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed

        Map camposNota = new HashMap();
        Map camposResumen = new HashMap();
        try {

            if (valida()) {

                alumnoControl.insertarAlumno("maestro_alumno", camposAlumno);
                idAlumno = cd.idAlumno();
                camposMatricula.put("id_alumno", idAlumno);
                alumnoControl.insertarMatricula("matricula", camposMatricula);
                //alumnoControl.insertarTodo("matricula", codigoPeriodo, camposMatricula);
                cargarMateria();
                idMatricula = cd.idMatricula();
                for (int i = 0; i < materias.size(); i++) {
                    idMateria = materias.get(i).getIdMateria();
                    idProfe = materias.get(i).getIdProfe();
                    idconMat = materias.get(i).getIdconfigmateria();
                    creditos = materias.get(i).getCreditos();
                    ponderacion= materias.get(i).getPonderacion();
                    idmalla=materias.get(i).getIdmalla();
                    camposNota.put("estado", " ");
                    camposNota.put("id_alumno", idAlumno);
                    camposNota.put("cedula", cedula);
                    camposNota.put("id_matricula", idMatricula);
                    camposNota.put("id_config_materia", idconMat);
                    camposNota.put("id_periodo", cd.getIdPeriodo());
                    camposNota.put("id_semestre", semestre);
                    camposNota.put("id_especialidad", especialidad);
                    camposNota.put("id_materia", idMateria);
                    camposNota.put("id_profe", idProfe);
                    camposNota.put("num_hora", creditos);
                    camposNota.put("ponderacion", ponderacion);
                    camposNota.put("id_malla", idmalla);
                    camposNota.put("fecha_ingreso", fechaIng);
                    camposNota.put("fecha_modificacion", fechaIng);
                    alumnoControl.insertarTodo("nota", codigoPeriodo, camposNota);
                }
                //cargarResumen();
                camposResumen.put("id_alumno", idAlumno);
                camposResumen.put("cedula", cedula);
                camposResumen.put("nombres", nombres);
                camposResumen.put("id_periodo", cd.getIdPeriodo());
                camposResumen.put("id_semestre", semestre);
                camposResumen.put("id_especialidad",especialidad);
                camposResumen.put("id_malla", cd.cargarMalla(especialidad, semestre));
                camposResumen.put("fecha_creacion", fechaIng);
                camposResumen.put("fecha_modificacion", fechaIng);
                alumnoControl.insertarResumen("resumen", camposResumen);

                mostrarDialogo();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
            }
        } catch (SQLException | HeadlessException ex) {
            Logger.getLogger(FormularioAlumno.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error al guradar toda la informacion");
        }

        //alumnoControl.insertarAlumno(null, null, camposNota);
    }//GEN-LAST:event_ingresarBtnActionPerformed
    private void mostrarDialogo() {
        Proceso p = Proceso.getInstance();
        p.cargarJDialog();
    }
    private void cmbSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemestreActionPerformed

    }//GEN-LAST:event_cmbSemestreActionPerformed

    private void cedulaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaTxtKeyTyped
        validaNum(evt);
        if (cedulaTxt.getText().length() == 10) {
            JOptionPane.showMessageDialog(null, "solo se perminten 10 cacarteres", "advertencia", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_cedulaTxtKeyTyped

    private void telefonotxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonotxtKeyTyped
        validaNum(evt);
    }//GEN-LAST:event_telefonotxtKeyTyped

    private void celulartxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celulartxtKeyTyped
        validaNum(evt);
    }//GEN-LAST:event_celulartxtKeyTyped

    private void nombreTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreTxtKeyTyped

    }//GEN-LAST:event_nombreTxtKeyTyped

    private void direcciontxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direcciontxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_direcciontxtKeyTyped

    private void representantetxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_representantetxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_representantetxtKeyTyped

    private void nomClgotxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomClgotxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_nomClgotxtKeyTyped

    private void prvClgotxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prvClgotxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_prvClgotxtKeyTyped

    private void ciudadClgotxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ciudadClgotxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_ciudadClgotxtKeyTyped

    private void nomPadretxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomPadretxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_nomPadretxtKeyTyped

    private void nomMadretxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomMadretxtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nomMadretxtKeyReleased

    private void nomMadretxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomMadretxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_nomMadretxtKeyTyped

    private void edadSpinnerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edadSpinnerKeyTyped

    }//GEN-LAST:event_edadSpinnerKeyTyped

    private void edadSpinnerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edadSpinnerMousePressed
        validaLetras(null);
    }//GEN-LAST:event_edadSpinnerMousePressed
    private void validaNum(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }

    private void validaLetras(java.awt.event.KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')) {
            evt.consume();
        }
    }

    private void cargarPk() throws SQLException {
        codigoPeriodo = cd.codigo();
    }

    private void cargarPais() throws SQLException {

        try {
            ResultSet rs = cd.pais();
            while (rs.next()) {
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                String pais;
                pais = rs.getString("pais");
                cmbPais.addItem(pais);

            }
        } catch (SQLException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    private void cargarMateria() throws SQLException {
        int idm, idp, idcm,pon,malla;
        String cre;
        try {
            materias = new ArrayList<>();
            ResultSet rs = cd.materia(semestre, especialidad);
            while (rs.next()) {
                idm = Integer.parseInt(rs.getString("id1_nombre_materia"));
                idp = Integer.parseInt(rs.getString("id1_profe"));
                idcm = Integer.parseInt(rs.getString("idconfigmaterias"));
                cre = rs.getString("num_hora");
                pon = Integer.valueOf(rs.getString("ponderacion"));
                malla = Integer.valueOf(rs.getString("id_malla"));
                mat = new Materia(idm, idp, cre, idcm,pon,malla);
                materias.add(mat);
            }
        } catch (SQLException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    private void cargarConfigEspecialidad() {
        try {
            ResultSet rs = cd.cargarPonderacion(especialidad);
            while (rs.next()) {
                porNota = Double.parseDouble(rs.getString("por_nota"));
                porTuto = Double.valueOf(rs.getString("por_tuto"));
            }
        } catch (SQLException | NumberFormatException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }
//
//    private void cargarResumen() {
//        int idnota;
//        try {
//            //idAlumno = cd.idAlumno(codigoPeriodo);
//            listaResumen = new ArrayList<>();
//            ResultSet rs = cd.resumen(codigoPeriodo, idAlumno);
//            while (rs.next()) {
//                idnota = Integer.parseInt(rs.getString("idnota"));
//                resumen = new Resumen(idnota);
//                listaResumen.add(resumen);
//            }
//        } catch (SQLException | NumberFormatException e) {
//            Logger.getLogger(FrmPeriodo.class
//                    .getName()).log(Level.SEVERE, null, e);
//        }
//
//    }

    private void cargarMadre() throws SQLException {

        try {
            ResultSet rs = cd.ocupacionMadre();
            while (rs.next()) {
                String madre = rs.getString("ocupacion_madre");
                cmbOcuMadre.addItem(madre);

            }
        } catch (SQLException e) {
            Logger.getLogger(FormularioAlumno.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    private void cargarPadre() throws SQLException {

        try {
            ResultSet rs = cd.ocupacionPadre();
            while (rs.next()) {
                String padre = rs.getString("ocupacion_padre");
                cmbOcupacionPadre.addItem(padre);

            }
        } catch (SQLException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    private void cargarEspecialidad() throws SQLException {

        try {
            ResultSet rs = cd.especialidad();
            while (rs.next()) {
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                String especialidad;
                especialidad = rs.getString("especialidad");
                cmbEspecialidad.addItem(especialidad);

            }
        } catch (SQLException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    private void cargarSemestre() throws SQLException {

        try {
            ResultSet rs = cd.semestre();
            while (rs.next()) {
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                String semestre;
                semestre = rs.getString("semestre");
                cmbSemestre.addItem(semestre);

            }
        } catch (SQLException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    private void cargarParalelo() throws SQLException {

        try {
            ResultSet rs = cd.paralelo();
            while (rs.next()) {
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                String paralelo;
                paralelo = rs.getString("paralelo");
                cmbParalelo.addItem(paralelo);

            }
        } catch (SQLException e) {
            Logger.getLogger(FrmPeriodo.class
                    .getName()).log(Level.SEVERE, null, e);
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioAlumno.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings({"override", "null"})
            public void run() {
                FormularioAlumno dialog = null;
                try {
                    dialog = new FormularioAlumno(alumno, true);

                } catch (SQLException ex) {
                    Logger.getLogger(FormularioAlumno.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JButton borrarBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JTextField celulartxt;
    private javax.swing.JTextField ciudadClgotxt;
    private javax.swing.JComboBox cmbBachiller;
    private javax.swing.JComboBox cmbCanton;
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbEspecialidad;
    private javax.swing.JComboBox cmbEstadoCivil;
    private javax.swing.JComboBox cmbMatricula;
    private javax.swing.JComboBox cmbOcuMadre;
    private javax.swing.JComboBox cmbOcupacionPadre;
    private javax.swing.JComboBox cmbPais;
    private javax.swing.JComboBox cmbParalelo;
    private javax.swing.JComboBox cmbProvincia;
    private javax.swing.JComboBox cmbSemestre;
    private javax.swing.JComboBox cmbTipColg;
    private javax.swing.JTextField direcciontxt;
    private javax.swing.JSpinner edadSpinner;
    private javax.swing.JTextField emailtxt;
    private com.toedter.calendar.JDateChooser fecNacMadreChooser;
    private com.toedter.calendar.JDateChooser fecNacPadreChooser;
    private com.toedter.calendar.JDateChooser fecNacimientoChooser;
    private com.toedter.calendar.JDateChooser fechaIngresoChooser;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.JButton ingresarBtn;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JTextField nomClgotxt;
    private javax.swing.JTextField nomMadretxt;
    private javax.swing.JTextField nomPadretxt;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField prvClgotxt;
    private javax.swing.JRadioButton rdbFemenino;
    private javax.swing.JRadioButton rdbMasculino;
    private javax.swing.JTextField representantetxt;
    private javax.swing.JTextField telefonotxt;
    // End of variables declaration//GEN-END:variables
}
