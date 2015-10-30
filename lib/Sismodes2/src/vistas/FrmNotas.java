package vistas;

import conectar.Conexion;
import controles.DatosNotaControl;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import metodos.CargarDatos;
import metodos.CargarDatosNotas;
import tablas.DatosNota;

public class FrmNotas extends javax.swing.JInternalFrame {
    
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idNota;
    private DefaultTableModel modelo;
    CargarDatos cd;
    private String codigoPeriodo;
    private List<DatosNota> listaNotas;
    private int idsemestre;
    public static FormularioNotas formularioNotas;
    
    private String idPeriodo, numaportes, cedula;
    
    CargarDatosNotas cdn;
    Map campos;
    BigDecimal promedio;
    private double numhoras, nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10, proPonderado,
            porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6,
            porcentaje7, porcentaje8, porcentaje9, porcentaje10, ponderacion;
    private int idconfimaterias, idmateria, idconfigmaterias, idespecialidad, idmalla, idalumno, porAsistencia;
    private static final BigDecimal valor = new BigDecimal(14);
    BigDecimal notaAsistencia = new BigDecimal("00.00");
    
    public FrmNotas() throws SQLException {
        initComponents();
        cd = new CargarDatos();
        cargarPk();
        cargarSemestre();
        idPeriodo = cd.codigo();
        cdn = new CargarDatosNotas();
        this.setSize(1240, 620);
        removerCampos();
        guardarBtn.setEnabled(false);
    }
    
    private void cargarPk() throws SQLException {
        codigoPeriodo = cd.codigo();
    }
    
    private void mostrarDatos() {
        
        String[] col = {"PK", "CEDULA", "NOMBRES", "ESPECIALIDAD", "MATERIA"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        tablaNotas.setModel(modelo);
        idsemestre = cmbsemestre.getSelectedIndex();
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                int i = 0;
                if (listaNotas == null) {
                    listaNotas = new ArrayList<>();
                } else {
                    listaNotas.clear();
                }
                String idnota;
                String cedula;
                String nombre;
                String especialidad;
                String materia;
                try {
                    DatosNotaControl notaconsulta = new DatosNotaControl();
                    ResultSet rs = notaconsulta.llenaNotas(0, codigoPeriodo, idsemestre);
                    while (rs.next()) {
                        Thread.sleep(10);
                        cedula = rs.getString("cedula");
                        nombre = rs.getString("nombres");
                        especialidad = rs.getString("especialidad");
                        materia = rs.getString("materia");
                        
                        if (filtroTxt.equals("") || cedula.contains(filtroTxt.getText())) {
                            int idalumno = (int) rs.getObject("id_nota");
                            listaNotas.add(new DatosNota(idalumno,
                                    cedula,
                                    nombre,
                                    materia,
                                    especialidad));
                            
                            modelo.insertRow(i++, new Object[]{
                                rs.getObject("id_nota"),
                                cedula,
                                nombre,
                                especialidad,
                                materia
                            });
                            
                        }
                    }
                } catch (SQLException | InterruptedException e) {
                    Logger.getLogger(FrmEspecialidad.class.getName()).log(Level.SEVERE, null, e);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (SQLException ex) {
                        Logger.getLogger(FrmNotas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        }).start();
        
    }
    
    private void removerCampos() {
        cedulaTxt.setEnabled(false);
        materiaTxt.setEnabled(false);
        semestreTxt.setEnabled(false);
        estadoTxt.setEnabled(false);
        promedioTxt.setEnabled(false);
        numHorasTxt.setEnabled(false);
        nota1Txt.setEnabled(false);
        nota2Txt.setEnabled(false);
        nota3Txt.setEnabled(false);
        nota4Txt.setEnabled(false);
        nota5Txt.setEnabled(false);
        nota6Txt.setEnabled(false);
        nota7Txt.setEnabled(false);
        nota8Txt.setEnabled(false);
        nota9Txt.setEnabled(false);
        nota10Txt.setEnabled(false);
        proPonderadoTxt.setEnabled(false);
        porAsistenciaTxt.setEnabled(false);
        
    }
    
    private void cargarSemestre() {
        try {
            ResultSet rs = cd.semestre();
            while (rs.next()) {
                String semestre = rs.getString("semestre");
                cmbsemestre.addItem(semestre);
                
            }
        } catch (Exception e) {
            Logger.getLogger(FrmNotas.class
                    .getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotas = new javax.swing.JTable();
        cmbsemestre = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        filtroTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nota1Txt = new javax.swing.JTextField();
        nota2Txt = new javax.swing.JTextField();
        nota3Txt = new javax.swing.JTextField();
        nota4Txt = new javax.swing.JTextField();
        nota5Txt = new javax.swing.JTextField();
        por1lbl = new javax.swing.JLabel();
        por2lbl = new javax.swing.JLabel();
        por3lbl = new javax.swing.JLabel();
        por4lbl = new javax.swing.JLabel();
        por5lbl = new javax.swing.JLabel();
        nota6Txt = new javax.swing.JTextField();
        nota7Txt = new javax.swing.JTextField();
        nota8Txt = new javax.swing.JTextField();
        nota9Txt = new javax.swing.JTextField();
        nota10Txt = new javax.swing.JTextField();
        por6lbl = new javax.swing.JLabel();
        por7lbl = new javax.swing.JLabel();
        por8lbl = new javax.swing.JLabel();
        por9lbl = new javax.swing.JLabel();
        por10lbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        promedioTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        numHorasTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        porAsistenciaTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        proPonderadoTxt = new javax.swing.JTextField();
        estadoTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        semestreTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        materiaTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        numApoLbl = new javax.swing.JLabel();
        calcularBtn = new javax.swing.JButton();
        guardarBtn = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1230, 470));

        tablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaNotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaNotas);

        cmbsemestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        cmbsemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsemestreActionPerformed(evt);
            }
        });

        jButton1.setText("Cargar Alumno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Cedula");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas de Materia"));

        nota1Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota1TxtMouseClicked(evt);
            }
        });

        nota2Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota2TxtMouseClicked(evt);
            }
        });

        nota3Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota3TxtMouseClicked(evt);
            }
        });

        nota4Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota4TxtMouseClicked(evt);
            }
        });

        nota5Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota5TxtMouseClicked(evt);
            }
        });

        por1lbl.setText("%");

        por2lbl.setText("%");

        por3lbl.setText("%");

        por4lbl.setText("%");

        por5lbl.setText("%");

        nota6Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota6TxtMouseClicked(evt);
            }
        });

        nota7Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota7TxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nota7TxtMouseEntered(evt);
            }
        });

        nota8Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota8TxtMouseClicked(evt);
            }
        });
        nota8Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nota8TxtActionPerformed(evt);
            }
        });

        nota9Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota9TxtMouseClicked(evt);
            }
        });
        nota9Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nota9TxtActionPerformed(evt);
            }
        });

        nota10Txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nota10TxtMouseClicked(evt);
            }
        });

        por6lbl.setText("%");

        por7lbl.setText("%");

        por8lbl.setText("%");

        por9lbl.setText("%");

        por10lbl.setText("%");

        jLabel5.setText("Nota 1");

        jLabel6.setText("Nota 6");

        jLabel7.setText("Nota 2");

        jLabel8.setText("Nota 7");

        jLabel9.setText("Nota 3");

        jLabel10.setText("Nota 8");

        jLabel11.setText("Nota 4");

        jLabel12.setText("Nota 5");

        jLabel13.setText("Nota 9");

        jLabel14.setText("Nota 10");

        jLabel2.setText("Promedio ");

        jLabel16.setText("Nº  Horas");

        jLabel17.setText(" Asistencia %");

        porAsistenciaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porAsistenciaTxtActionPerformed(evt);
            }
        });

        jLabel18.setText("Promedio %");

        proPonderadoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proPonderadoTxtActionPerformed(evt);
            }
        });

        jLabel15.setText("Estado ");

        semestreTxt.setText(" ");

        jLabel4.setText("Semestre");

        jLabel3.setText("Cedula");

        jLabel19.setText("Materia");

        numApoLbl.setText("Numero de Aportes");

        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(15, 15, 15)
                        .addComponent(semestreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addGap(32, 32, 32)
                        .addComponent(materiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(numApoLbl))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(por1lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(por6lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(promedioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(por2lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(por7lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(34, 34, 34)
                        .addComponent(numHorasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28)
                        .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(por3lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(por8lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(porAsistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(guardarBtn)
                        .addGap(69, 69, 69)
                        .addComponent(calcularBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(28, 28, 28)
                                .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(por4lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(nota9Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(por9lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(proPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(28, 28, 28)
                                .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(por5lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel14)
                                .addGap(12, 12, 12)
                                .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(por10lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addGap(44, 44, 44)
                                .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(semestreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addComponent(materiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(numApoLbl)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por1lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por6lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(promedioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por2lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por7lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(numHorasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por3lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por8lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(porAsistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por4lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(nota9Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(por9lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(proPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por5lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por10lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarBtn)
                    .addComponent(calcularBtn)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(cmbsemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbsemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaNotasMouseClicked
        limpiar();
        int i = tablaNotas.getSelectedRow();
        idNota = (Integer) tablaNotas.getValueAt(i, 0);
        confMaterias(idNota);
    }//GEN-LAST:event_tablaNotasMouseClicked

    private void nota8TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nota8TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nota8TxtActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        Calendar cal = Calendar.getInstance();
        if (promedioTxt.getText().length() == 0) {
            
            JOptionPane.showMessageDialog(null, "Promedio vacio para ingreso", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                cargarDatos();
                campos.put("estado", estadoTxt.getText());
                campos.put("promedio", promedioTxt.getText());
                campos.put("pro_ponderado", proPonderadoTxt.getText());
                campos.put("fecha_modificacion", cal.getTime());
                campos.put("por_asistencia", porAsistencia);
                campos.put("asistencia", notaAsistencia);
                cdn.actualizar("nota", idPeriodo, "id_nota", idNota, campos);
                
                double resultado = cdn.proPonderado(idalumno,cedula, idPeriodo, idespecialidad, idsemestre);
                Map camposResumen = new HashMap();
                int porNotaMalla = cdn.porNotaFinal(idmalla);
                double con = 100;
                double total = porNotaMalla / con;
                
                BigDecimal porNotaResumen = BigDecimal.valueOf(resultado).multiply(BigDecimal.valueOf(total));
                BigDecimal proPonderadoNota = BigDecimal.valueOf(resultado);
                proPonderadoNota = proPonderadoNota.setScale(1, RoundingMode.HALF_UP);
                porNotaResumen = porNotaResumen.setScale(1, RoundingMode.HALF_UP);
                double notaFin = cdn.notaFinal(cedula, idespecialidad, idsemestre);
                
                BigDecimal nota = new BigDecimal(notaFin).add(porNotaResumen);
                nota = nota.setScale(1, RoundingMode.HALF_UP);
                String estado = "";
                camposResumen.put("pro_ponderado_nota", proPonderadoNota);
                camposResumen.put("porcentaje_nota", porNotaResumen);
                camposResumen.put("nota_final", nota);
                
                if (nota.compareTo(valor) < 0) {
                    estado = "REPROBADO";
                } else {
                    estado = "APROBADO";
                }
                
                camposResumen.put("fecha_modificacion", cal.getTime());
                camposResumen.put("estado", estado);
                camposResumen.put("asistencia", cdn.asistencia(cedula, idPeriodo, idespecialidad, idsemestre));
                cdn.actualizarResumen("resumen", "id_resumen", cedula, idalumno, idespecialidad, idsemestre, camposResumen);
                JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
                limpiar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de guardar las notas en BD", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            
        }

    }//GEN-LAST:event_guardarBtnActionPerformed
    private void limpiar() {
        cedulaTxt.setText(null);
        semestreTxt.setText(null);
        materiaTxt.setText(null);
        nota1Txt.setText(null);
        nota2Txt.setText(null);
        nota3Txt.setText(null);
        nota3Txt.setText(null);
        nota4Txt.setText(null);
        nota5Txt.setText(null);
        nota6Txt.setText(null);
        nota7Txt.setText(null);
        nota8Txt.setText(null);
        nota9Txt.setText(null);
        nota10Txt.setText(null);
        promedioTxt.setText(null);
        numHorasTxt.setText(null);
        porAsistenciaTxt.setText(null);
        proPonderadoTxt.setText(null);
        estadoTxt.setText(null);
    }
    
    private Map cargarDatos() {
        campos = new HashMap();
        switch (numaportes) {
            case "1":
                campos.put("nota1", nota1Txt.getText());
                break;
            case "2":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                break;
            case "3":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                break;
            case "4":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                break;
            case "5":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                campos.put("nota5", nota5Txt.getText());
                break;
            case "6":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                campos.put("nota5", nota5Txt.getText());
                campos.put("nota6", nota6Txt.getText());
                break;
            case "7":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                campos.put("nota5", nota5Txt.getText());
                campos.put("nota6", nota6Txt.getText());
                campos.put("nota7", nota7Txt.getText());
                break;
            case "8":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                campos.put("nota5", nota5Txt.getText());
                campos.put("nota6", nota6Txt.getText());
                campos.put("nota7", nota7Txt.getText());
                campos.put("nota8", nota8Txt.getText());
                break;
            case "9":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                campos.put("nota5", nota5Txt.getText());
                campos.put("nota6", nota6Txt.getText());
                campos.put("nota7", nota7Txt.getText());
                campos.put("nota8", nota8Txt.getText());
                campos.put("nota9", nota9Txt.getText());
                break;
            case "10":
                campos.put("nota1", nota1Txt.getText());
                campos.put("nota2", nota2Txt.getText());
                campos.put("nota3", nota3Txt.getText());
                campos.put("nota4", nota4Txt.getText());
                campos.put("nota5", nota5Txt.getText());
                campos.put("nota6", nota6Txt.getText());
                campos.put("nota7", nota7Txt.getText());
                campos.put("nota8", nota8Txt.getText());
                campos.put("nota9", nota9Txt.getText());
                campos.put("nota10", nota10Txt.getText());
                break;
        }
        return campos;
    }
    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        try {
            double[] porcentajes = {porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6,
                porcentaje7, porcentaje8, porcentaje9, porcentaje10};
            if (validaCampos()) {
                nota1 = Double.valueOf(nota1Txt.getText().replaceAll(",", "."));
                nota2 = Double.valueOf(nota2Txt.getText().replaceAll(",", "."));
                nota3 = Double.valueOf(nota3Txt.getText().replaceAll(",", "."));
                nota4 = Double.valueOf(nota4Txt.getText().replaceAll(",", "."));
                nota5 = Double.valueOf(nota5Txt.getText().replaceAll(",", "."));
                nota6 = Double.valueOf(nota6Txt.getText().replaceAll(",", "."));
                nota7 = Double.valueOf(nota7Txt.getText().replaceAll(",", "."));
                nota8 = Double.valueOf(nota8Txt.getText().replaceAll(",", "."));
                nota9 = Double.valueOf(nota9Txt.getText().replaceAll(",", "."));
                nota10 = Double.valueOf(nota10Txt.getText().replaceAll(",", "."));
                
                double[] notas = {nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10};
                double resultadoPromedio = 0;
                int porcentaje = 100;
                double resul1 = 0;
                double notaFinal = 0;
                for (int i = 0; i < porcentajes.length; i++) {
                    resultadoPromedio = porcentajes[i] / porcentaje;
                    resul1 = notas[i] * resultadoPromedio;
                    notaFinal = notaFinal + resul1;
                }
                promedio = new BigDecimal(notaFinal);
                
                promedio = promedio.setScale(1, RoundingMode.HALF_UP);
                if (promedio.compareTo(valor) < 0) {
                    estadoTxt.setText("REPROBADO");
                } else {
                    estadoTxt.setText("APROBADO");
                }
                
                double resul = ponderacion / 100;
                BigDecimal proPonderado = new BigDecimal("00.00");
                proPonderado = promedio.multiply(BigDecimal.valueOf(resul));
                proPonderado = proPonderado.setScale(1, RoundingMode.HALF_UP);
                porAsistencia = Integer.parseInt(porAsistenciaTxt.getText());
                
                if (porAsistencia > 0) {
                    porAsistenciaTxt.setEnabled(false);
                    if (porAsistencia > 100) {
                        JOptionPane.showMessageDialog(null, "Error porcentaje de asistencia no valido", "Error", JOptionPane.ERROR_MESSAGE);
                        porAsistenciaTxt.setText(null);
                        porAsistenciaTxt.setEnabled(true);
                        return;
                    } else if (porAsistencia == 100) {
                        notaAsistencia = new BigDecimal("1.0");
                        proPonderado = proPonderado.add(notaAsistencia);
                    }
                } else {
                    notaAsistencia = new BigDecimal("0.0");
                }
                proPonderadoTxt.setText(String.valueOf(proPonderado));
                promedioTxt.setText(String.valueOf(promedio));
                removerCampos();
                guardarBtn.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error campos vacios para calcular", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de calcular los valores", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_calcularBtnActionPerformed
    private boolean validaCampos() {
        boolean resultado = true;
        if (nota1Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota2Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota3Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota4Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota5Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota6Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota7Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota8Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota9Txt.getText().isEmpty()) {
            resultado = false;
        } else if (nota10Txt.getText().isEmpty()) {
            resultado = false;
        }
        
        return resultado;
    }

    private void proPonderadoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proPonderadoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proPonderadoTxtActionPerformed

    private void porAsistenciaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porAsistenciaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_porAsistenciaTxtActionPerformed

    private void nota9TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nota9TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nota9TxtActionPerformed

    private void nota1TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota1TxtMouseClicked
        limpia(nota1Txt);
    }//GEN-LAST:event_nota1TxtMouseClicked
    private void limpia(JTextField j) {
        j.setText(null);
    }
    
    private void nota2TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota2TxtMouseClicked
        limpia(nota2Txt);
    }//GEN-LAST:event_nota2TxtMouseClicked

    private void nota3TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota3TxtMouseClicked
        limpia(nota3Txt);
    }//GEN-LAST:event_nota3TxtMouseClicked

    private void nota4TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota4TxtMouseClicked
        limpia(nota4Txt);
    }//GEN-LAST:event_nota4TxtMouseClicked

    private void nota5TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota5TxtMouseClicked
        limpia(nota5Txt);
    }//GEN-LAST:event_nota5TxtMouseClicked

    private void nota6TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota6TxtMouseClicked
        limpia(nota6Txt);
    }//GEN-LAST:event_nota6TxtMouseClicked

    private void nota7TxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota7TxtMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nota7TxtMouseEntered

    private void nota7TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota7TxtMouseClicked
        limpia(nota7Txt);
    }//GEN-LAST:event_nota7TxtMouseClicked

    private void nota8TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota8TxtMouseClicked
        limpia(nota8Txt);
    }//GEN-LAST:event_nota8TxtMouseClicked

    private void nota9TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota9TxtMouseClicked
        limpia(nota9Txt);
    }//GEN-LAST:event_nota9TxtMouseClicked

    private void nota10TxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nota10TxtMouseClicked
        limpia(nota10Txt);
    }//GEN-LAST:event_nota10TxtMouseClicked

    private void cmbsemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsemestreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbsemestreActionPerformed
//    private void cargarjDialog() throws SQLException {
////        formularioNotas = new FormularioNotas(FrmNotas.this, true, idNota);
////        formularioNotas.setLocationRelativeTo(FrmNotas.this);
////        formularioNotas.setVisible(true);
////        mostrarDatos();
//
//    }

    private void confMaterias(int idnota) {
        try {
            ResultSet rs = cdn.cargarNotas(idPeriodo, idnota);
            while (rs.next()) {
                numhoras = Double.valueOf(rs.getString("num_hora"));
                nota1 = Double.valueOf(rs.getString("nota1"));
                nota2 = Double.valueOf(rs.getString("nota2"));
                nota3 = Double.valueOf(rs.getString("nota3"));
                nota4 = Double.valueOf(rs.getString("nota4"));
                nota5 = Double.valueOf(rs.getString("nota5"));
                nota6 = Double.valueOf(rs.getString("nota6"));
                nota7 = Double.valueOf(rs.getString("nota7"));
                nota8 = Double.valueOf(rs.getString("nota8"));
                nota9 = Double.valueOf(rs.getString("nota9"));
                nota10 = Double.valueOf(rs.getString("nota10"));
                cedula = rs.getString("cedula");

                // proPonderado = Double.valueOf(rs.getString("proPonderado"));
                cedulaTxt.setText(cedula);
                idconfimaterias = Integer.parseInt(rs.getString("id_config_materia"));
                idmateria = Integer.parseInt(rs.getString("id_materia"));
                idsemestre = Integer.parseInt(rs.getString("id_semestre"));
                idespecialidad = Integer.parseInt(rs.getString("id_especialidad"));
                ponderacion = Double.valueOf(rs.getString("ponderacion"));
                idmalla = Integer.parseInt(rs.getString("id_malla"));
                idalumno = Integer.parseInt(rs.getString("id_alumno"));
                estadoTxt.setText(rs.getString("estado"));
                proPonderadoTxt.setText(rs.getString("pro_ponderado"));
                porAsistenciaTxt.setText(rs.getString("por_asistencia"));
                numHorasTxt.setText(String.valueOf(numhoras));
                nota1Txt.setText(String.valueOf(nota1));
                nota2Txt.setText(String.valueOf(nota2));
                nota3Txt.setText(String.valueOf(nota3));
                nota4Txt.setText(String.valueOf(nota4));
                nota5Txt.setText(String.valueOf(nota5));
                nota6Txt.setText(String.valueOf(nota6));
                nota7Txt.setText(String.valueOf(nota7));
                nota8Txt.setText(String.valueOf(nota8));
                nota9Txt.setText(String.valueOf(nota9));
                nota10Txt.setText(String.valueOf(nota10));
                promedioTxt.setText(rs.getString("promedio"));
                
            }
            materiaTxt.setText(cdn.cargarNombreMateria(idmateria));
            semestreTxt.setText(cdn.cargarNombreSemestre(idsemestre));
            configPorcentaje();
            validaCampos(numaportes);
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al cargar los datos principales ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configPorcentaje() {
        try {
            ResultSet rs = cdn.configuracionMaterias(idconfimaterias);
            while (rs.next()) {
                porcentaje1 = Double.valueOf(rs.getString("aporte1"));
                porcentaje2 = Double.valueOf(rs.getString("aporte2"));
                porcentaje3 = Double.valueOf(rs.getString("aporte3"));
                porcentaje4 = Double.valueOf(rs.getString("aporte4"));
                porcentaje5 = Double.valueOf(rs.getString("aporte5"));
                porcentaje6 = Double.valueOf(rs.getString("aporte6"));
                porcentaje7 = Double.valueOf(rs.getString("aporte7"));
                porcentaje8 = Double.valueOf(rs.getString("aporte8"));
                porcentaje9 = Double.valueOf(rs.getString("aporte9"));
                porcentaje10 = Double.valueOf(rs.getString("aporte10"));
                
                numaportes = rs.getString("num_aporte");
                numApoLbl.setText("Numero de Aportes: " + "  " + numaportes);
                
                por1lbl.setText(String.valueOf(porcentaje1) + "%");
                por2lbl.setText(String.valueOf(porcentaje2) + "%");
                por3lbl.setText(String.valueOf(porcentaje3) + "%");
                por4lbl.setText(String.valueOf(porcentaje4) + "%");
                por5lbl.setText(String.valueOf(porcentaje5) + "%");
                por6lbl.setText(String.valueOf(porcentaje6) + "%");
                por7lbl.setText(String.valueOf(porcentaje7) + "%");
                por8lbl.setText(String.valueOf(porcentaje8) + "%");
                por9lbl.setText(String.valueOf(porcentaje9) + "%");
                por10lbl.setText(String.valueOf(porcentaje10) + "%");
                
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ocurrion un error al cargar los valores de la notas ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void validaCampos(String numaportes) {
        switch (Integer.parseInt(numaportes)) {
            case 1:
                nota1Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 2:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
            
            case 3:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                
                break;
            case 4:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 5:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                nota5Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 6:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                nota5Txt.setEnabled(true);
                nota6Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 7:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                nota5Txt.setEnabled(true);
                nota6Txt.setEnabled(true);
                nota7Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 8:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                nota5Txt.setEnabled(true);
                nota6Txt.setEnabled(true);
                nota7Txt.setEnabled(true);
                nota8Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 9:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                nota5Txt.setEnabled(true);
                nota6Txt.setEnabled(true);
                nota7Txt.setEnabled(true);
                nota8Txt.setEnabled(true);
                nota9Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
            case 10:
                nota1Txt.setEnabled(true);
                nota2Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota3Txt.setEnabled(true);
                nota4Txt.setEnabled(true);
                nota5Txt.setEnabled(true);
                nota6Txt.setEnabled(true);
                nota7Txt.setEnabled(true);
                nota8Txt.setEnabled(true);
                nota9Txt.setEnabled(true);
                nota10Txt.setEnabled(true);
                porAsistenciaTxt.setEnabled(true);
                break;
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcularBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JComboBox cmbsemestre;
    private javax.swing.JTextField estadoTxt;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField materiaTxt;
    private javax.swing.JTextField nota10Txt;
    private javax.swing.JTextField nota1Txt;
    private javax.swing.JTextField nota2Txt;
    private javax.swing.JTextField nota3Txt;
    private javax.swing.JTextField nota4Txt;
    private javax.swing.JTextField nota5Txt;
    private javax.swing.JTextField nota6Txt;
    private javax.swing.JTextField nota7Txt;
    private javax.swing.JTextField nota8Txt;
    private javax.swing.JTextField nota9Txt;
    private javax.swing.JLabel numApoLbl;
    private javax.swing.JTextField numHorasTxt;
    private javax.swing.JLabel por10lbl;
    private javax.swing.JLabel por1lbl;
    private javax.swing.JLabel por2lbl;
    private javax.swing.JLabel por3lbl;
    private javax.swing.JLabel por4lbl;
    private javax.swing.JLabel por5lbl;
    private javax.swing.JLabel por6lbl;
    private javax.swing.JLabel por7lbl;
    private javax.swing.JLabel por8lbl;
    private javax.swing.JLabel por9lbl;
    private javax.swing.JTextField porAsistenciaTxt;
    private javax.swing.JTextField proPonderadoTxt;
    private javax.swing.JTextField promedioTxt;
    private javax.swing.JTextField semestreTxt;
    private javax.swing.JTable tablaNotas;
    // End of variables declaration//GEN-END:variables
}
