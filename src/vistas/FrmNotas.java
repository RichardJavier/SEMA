/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.MallaDao;
import logica.MetodosGeneralesDao;
import logica.NotaDao;
import modelo.Alumno;
import modelo.Campo;
import modelo.ConfiguracionMateria;
import modelo.Estado;
import modelo.Malla;
import modelo.Materia;
import modelo.Nota;
import modelo.Periodo;
import modelo.Semestre;

/**
 *
 * @author USER
 */
public class FrmNotas extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    DefaultTableModel modelo;
    ResultSet resultSet, resultSet1;
    Nota nota;
    MetodosGeneralesDao metodosGeneralesDao;
    List<Nota> listaNotas;
    NotaDao notaDao;
    Alumno alumnno;
    Periodo periodo;
    Semestre semestre;
    Materia materia;
    ConfiguracionMateria configuracionMateria;
    List<Campo> lista;
    Campo campo;
    Map campos;
    Malla malla;

    public FrmNotas() {
        initComponents();
        alumnno = new Alumno();
        metodosGeneralesDao = new MetodosGeneralesDao();
        semestre = new Semestre();
        notaDao = new NotaDao();
        cargarSemestre();
        this.nombresTxt.setEnabled(false);
        promedioTxt.setEnabled(false);
        recuperacionTxt.setEnabled(false);
        ocultaCampos();

    }

    private void cargarDatos(final String periodo, final String cedula, final Integer idSemestre) {
        String[] col = {"PK", "CEDULA", "NOMBRE MATERIA", "SEMESTRE", "ESPECIALIDAD", "IDMATERIA"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        this.notasTabla.setModel(modelo);
        notasTabla.getColumnModel().getColumn(0).setMaxWidth(40);
        notasTabla.getColumnModel().getColumn(0).setMinWidth(40);
        notasTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
        notasTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(40);

        notasTabla.getColumnModel().getColumn(1).setMaxWidth(100);
        notasTabla.getColumnModel().getColumn(1).setMinWidth(100);
        notasTabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(100);
        notasTabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(100);

        notasTabla.getColumnModel().getColumn(2).setMaxWidth(260);
        notasTabla.getColumnModel().getColumn(2).setMinWidth(260);
        notasTabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(260);
        notasTabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(260);

        notasTabla.getColumnModel().getColumn(3).setMaxWidth(100);
        notasTabla.getColumnModel().getColumn(3).setMinWidth(100);
        notasTabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(100);
        notasTabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(100);

        notasTabla.getColumnModel().getColumn(5).setMaxWidth(0);
        notasTabla.getColumnModel().getColumn(5).setMinWidth(0);
        notasTabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        notasTabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        notasTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));

        new Thread(new Runnable() {

            @Override
            public void run() {
                nota = new Nota();
                int i = 0;
                if (listaNotas == null) {
                    listaNotas = new ArrayList<>();
                } else {
                    listaNotas.clear();
                }
                try {
                    resultSet = notaDao.consultaNotas(periodo, cedula, idSemestre);
                    while (resultSet.next()) {
                        nota.setIdNota(Integer.parseInt(resultSet.getString("id_nota")));
                        nota.setCedula(resultSet.getString("cedula"));
                        nota.setNombreMateria(resultSet.getString("materia"));
                        nota.setSemestre(resultSet.getString("semestre"));
                        nota.setEspecialidad(resultSet.getString("especialidad"));
                        nota.setIdMateria(Integer.parseInt(resultSet.getString("id_materia")));
                        modelo.insertRow(i, new Object[]{
                            nota.getIdNota(),
                            nota.getCedula(),
                            nota.getNombreMateria(),
                            nota.getSemestre(),
                            nota.getEspecialidad(),
                            nota.getIdMateria()
                        });
                    }
                } catch (SQLException | NumberFormatException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error la cargar", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        notasTabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        semestreCmb = new javax.swing.JComboBox();
        buscarBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nombresTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nota3Lbl = new javax.swing.JLabel();
        nota1Lbl = new javax.swing.JLabel();
        nota1Txt = new javax.swing.JTextField();
        nota5Lbl = new javax.swing.JLabel();
        nota7Lbl = new javax.swing.JLabel();
        nota9Lbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        asignaturaTxt = new javax.swing.JTextField();
        nota3Txt = new javax.swing.JTextField();
        nota5Txt = new javax.swing.JTextField();
        nota7Txt = new javax.swing.JTextField();
        nota9Txt = new javax.swing.JTextField();
        nota2Txt = new javax.swing.JTextField();
        nota2Lbl = new javax.swing.JLabel();
        nota4Lbl = new javax.swing.JLabel();
        nota4Txt = new javax.swing.JTextField();
        nota6Lbl = new javax.swing.JLabel();
        nota6Txt = new javax.swing.JTextField();
        nota8Lbl = new javax.swing.JLabel();
        nota8Txt = new javax.swing.JTextField();
        nota10Lbl = new javax.swing.JLabel();
        nota10Txt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        asistenciaTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        promedioTxt = new javax.swing.JTextField();
        calcularBtn = new javax.swing.JButton();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        activaRecuperacionCkb = new javax.swing.JCheckBox();
        recuperacionTxt = new javax.swing.JTextField();

        setClosable(true);

        notasTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        notasTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notasTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(notasTabla);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda de Alumno"));

        jLabel1.setText("Cedula");

        jLabel2.setText("Semestre");

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });

        buscarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombres");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombresTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarBtn)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarBtn)
                    .addComponent(jLabel3)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Ingreso de Notas"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores de Notas de Materia"));

        nota3Lbl.setText("nota3");

        nota1Lbl.setText("nota1");

        nota5Lbl.setText("nota5");

        nota7Lbl.setText("nota7");

        nota9Lbl.setText("nota9");

        jLabel4.setText("Asignatura");

        nota2Lbl.setText("nota2");

        nota4Lbl.setText("nota4");

        nota6Lbl.setText("nota6");

        nota8Lbl.setText("nota8");

        nota10Lbl.setText("nota10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nota1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nota9Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nota7Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nota5Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nota3Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota9Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nota4Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nota2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nota6Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nota8Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nota10Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(78, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(asignaturaTxt)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(asignaturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nota1Lbl)
                    .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nota2Lbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nota4Lbl))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nota3Lbl)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nota6Lbl))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nota5Lbl)
                        .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nota7Lbl)
                    .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nota8Lbl)
                    .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nota10Lbl)
                        .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nota9Lbl)
                        .addComponent(nota9Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel15.setText("Asistencia");

        jLabel16.setText("Promedio");

        calcularBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Calculator.png"))); // NOI18N
        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Recuperacion"));

        activaRecuperacionCkb.setText("Activar");
        activaRecuperacionCkb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activaRecuperacionCkbActionPerformed(evt);
            }
        });

        recuperacionTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recuperacionTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activaRecuperacionCkb)
                    .addComponent(recuperacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(activaRecuperacionCkb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recuperacionTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(calcularBtn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(asistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(guardarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelarBtn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(promedioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(asistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(promedioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(cancelarBtn)
                    .addComponent(calcularBtn))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        periodo = metodosGeneralesDao.codigoPeriodoActivo();
        ocultaCampos();
        limpiaCampos();
        if (!cedulaTxt.getText().trim().isEmpty() && semestreCmb.getSelectedIndex() != 0) {
            nombresTxt.setText(notaDao.nombreAlumno(cedulaTxt.getText(), semestre.getIdSemestre()));
            cargarDatos(periodo.getCodigoPeriodo(), cedulaTxt.getText(), semestre.getIdSemestre());
            if (nombresTxt.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay informacion de estos datos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_buscarBtnActionPerformed

    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        try {
            Semestre seme = (Semestre) semestreCmb.getSelectedItem();
            semestre.setIdSemestre(seme.getIdSemestre());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void notasTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notasTablaMouseClicked
        limpiaCampos();
        ocultaCampos();
        calcularBtn.setEnabled(true);
        asistenciaTxt.setEnabled(true);
        int i = notasTabla.getSelectedRow();
        metodosGeneralesDao = new MetodosGeneralesDao();
        periodo = metodosGeneralesDao.codigoPeriodoActivo();
        nota.setIdNota((Integer) notasTabla.getValueAt(i, 0));
        nota.setIdMateria((Integer) notasTabla.getValueAt(i, 5));
        nota.setNombreMateria((String) notasTabla.getValueAt(i, 2));
        asignaturaTxt.setText(nota.getNombreMateria());
        resultSet1 = notaDao.cargarValoresMateria(periodo.getCodigoPeriodo(), nota.getIdNota(), nota.getIdMateria());
        try {
            materia = new Materia();
            configuracionMateria = new ConfiguracionMateria();
            MallaDao mallaDao = new MallaDao();
            while (resultSet1.next()) {
                configuracionMateria.setNumeroAportes(resultSet1.getString("num_aporte"));
                materia.setIdConfiguracionMateria(Integer.parseInt(resultSet1.getString("id_config_materia")));
                materia.setIdDescripcionMateria(Integer.parseInt(resultSet1.getString("id_desc_materia")));
                materia.setIdMalla(Integer.parseInt(resultSet1.getString("id_malla")));
                cargaCampo(Integer.parseInt(configuracionMateria.getNumeroAportes()));
                setNota(resultSet1, configuracionMateria.getNumeroAportes());
                setDescripcion(resultSet1, configuracionMateria.getNumeroAportes());
                setConfigMateria(resultSet1, configuracionMateria.getNumeroAportes());
            }
            malla = mallaDao.getMalla(materia.getIdMalla());
        } catch (SQLException ex) {
            Logger.getLogger(FrmNotas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
    }//GEN-LAST:event_notasTablaMouseClicked

    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        if (validaForm(Integer.parseInt(configuracionMateria.getNumeroAportes()))) {
            cargaNota(Integer.parseInt(configuracionMateria.getNumeroAportes()));
            if (calculaNotas(Integer.parseInt(configuracionMateria.getNumeroAportes()))) {
                ocultaCampos();
                guardarBtn.setEnabled(true);
                activaRecuperacionCkb.setEnabled(true);
            } else {
                ocultaCampos();
                guardarBtn.setEnabled(false);
                calcularBtn.setEnabled(true);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_calcularBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void activaRecuperacionCkbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activaRecuperacionCkbActionPerformed
        if (nota.getPromedio().compareTo(new BigDecimal("12.5")) > 0) {
            if (activaRecuperacionCkb.isSelected() == true) {
                recuperacionTxt.setEnabled(true);
                calcularBtn.setEnabled(true);
                guardarBtn.setEnabled(false);
            } else {
                recuperacionTxt.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error valor minimo de promedio invalido para acceder a recuperacion", "Error", JOptionPane.ERROR_MESSAGE);
            activaRecuperacionCkb.setEnabled(false);
            activaRecuperacionCkb.setSelected(false);
            guardarBtn.setEnabled(false);
            recuperacionTxt.setEnabled(false);

        }


    }//GEN-LAST:event_activaRecuperacionCkbActionPerformed

    private void recuperacionTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recuperacionTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recuperacionTxtActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        try {
            cargarNotaLista(Integer.parseInt(configuracionMateria.getNumeroAportes()));
            Calendar cal = Calendar.getInstance();
            campos = new HashMap();
            for (@SuppressWarnings("LocalVariableHidesMemberVariable") Campo lista1 : lista) {
                campos.put(lista1.getLlave(), lista1.getObjeto());
            }
            if (!recuperacionTxt.getText().trim().isEmpty()) {
                campos.put("recuperacion", nota.getRecuperacion());
            }
            campos.put("fecha_modificacion", cal.getTime());
            nota.setAsistencia(Integer.parseInt(asistenciaTxt.getText()));
            revisaEstado();
            campos.put("estado_nota", nota.getEstadoNota());
            campos.put("estado_asistencia", nota.getEstadoAsistencia());
            System.out.println(campos);
            notaDao.actualizarNota("nota", periodo.getNombrePeriodo(), "id_nota", nota.getIdNota(), campos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarBtnActionPerformed
    private void revisaEstado() {
        if (nota.getAsistencia() < malla.getValorMinimoAsistencia()) {
            nota.setEstadoAsistencia(Estado.RP.name());
        } else {
            nota.setEstadoAsistencia(Estado.AP.name());
        }
        if (nota.getPromedio().compareTo(new BigDecimal(malla.getValorMinimoPromedioMateria())) <= 0) {
            nota.setEstadoNota(Estado.RP.name());
        } else {
            nota.setEstadoNota(Estado.AP.name());
        }
    }

    private boolean validaForm(int valorCampos) {
        boolean resultado = true;
        JTextField[] camp = {nota1Txt, nota2Txt, nota3Txt, nota4Txt,
            nota5Txt, nota6Txt, nota7Txt, nota8Txt, nota9Txt, nota10Txt, asistenciaTxt};
        int i = 0;
        for (JTextField campo6 : camp) {
            if (i < valorCampos) {
                if (campo6.getText().trim().length() == 0) {
                    resultado = false;
                }
            }
            i++;
        }
        int val = Integer.parseInt(asistenciaTxt.getText().trim());
        if (val > 100) {
            JOptionPane.showMessageDialog(null, "Error valor invalido para asistencia", "Error", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        } else if (val < 0) {
            JOptionPane.showMessageDialog(null, "Error valor invalido para asistencia", "Error", JOptionPane.ERROR_MESSAGE);
            resultado = false;
        }

        return resultado;
    }

    private boolean calculaNotas(int numeroCampos) {
        boolean resultado = false;
        Double[] valor = {nota.getNota1(), nota.getNota2(), nota.getNota3(), nota.getNota4(), nota.getNota5(),
            nota.getNota6(), nota.getNota6(), nota.getNota7(), nota.getNota8(), nota.getNota9(), nota.getNota10()};
        Double[] porcentaje = {configuracionMateria.getAporte1(), configuracionMateria.getAporte2(), configuracionMateria.getAporte3(), configuracionMateria.getAporte4(), configuracionMateria.getAporte5(), configuracionMateria.getAporte6(),
            configuracionMateria.getAporte7(), configuracionMateria.getAporte8(), configuracionMateria.getAporte9(),
            configuracionMateria.getAporte10()};
        int i = 0;
        double recu;
        try {
            BigDecimal promedio = new BigDecimal(BigInteger.ZERO);
            double temp = 0;
            double por = 0;
            for (Double valor1 : valor) {
                if (i < numeroCampos) {
                    por = porcentaje[i];
                    temp = valor1 * por;
                    promedio = promedio.add(new BigDecimal(temp));
                    promedio = promedio.setScale(2, RoundingMode.HALF_UP);
                    promedioTxt.setText(String.valueOf(promedio));
                    nota.setPromedio(promedio);
                    resultado = true;

                    i++;

                } else {
                    if (activaRecuperacionCkb.isSelected() == true && i == numeroCampos) {
                        if (recuperacionTxt.getText().trim().length() != 0) {
                            promedio = promedio.subtract(new BigDecimal(temp));
                            recu = Double.valueOf(recuperacionTxt.getText()) * por;
                            promedio = promedio.add(new BigDecimal(recu));
                            promedio = promedio.setScale(2, RoundingMode.HALF_UP);
                            promedioTxt.setText(String.valueOf(promedio));
                            nota.setPromedio(promedio);
                            resultado = true;
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Error campo recuperacion vacio", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    resultado = true;
                    break;
                }

            }
            return resultado;
        } catch (NumberFormatException | HeadlessException e) {
            System.out.println(e);
        }
        return resultado;

    }

    private List<Campo> cargarNotaLista(int numCampos) {
        lista = new ArrayList<>();
        Double[] notas = {nota.getNota1(), nota.getNota2(), nota.getNota3(), nota.getNota4(), nota.getNota5(), nota.getNota6(), nota.getNota7(), nota.getNota8(), nota.getNota9(), nota.getNota10()};
        String[] llave = {"nota1", "nota2", "nota3", "nota4", "nota5", "nota6",
            "nota7", "nota8", "nota9", "nota10"};
        int i = 0;
        for (Double dato1 : notas) {
            if (i < numCampos) {
                campo = new Campo();
                campo.setLlave(llave[i]);
                campo.setObjeto(dato1);
                lista.add(campo);
            }
            if (i >= numCampos) {
                break;
            }
            i++;
        }
        return lista;
    }

    private void cargaNota(int numeroAportes) {
        switch (numeroAportes) {
            case 1:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                break;
            case 2:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                break;
            case 3:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                break;
            case 4:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                break;
            case 5:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                nota.setNota5(Double.parseDouble(nota5Txt.getText().replaceAll(",", ".")));
                break;
            case 6:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                nota.setNota5(Double.parseDouble(nota5Txt.getText().replaceAll(",", ".")));
                nota.setNota6(Double.parseDouble(nota6Txt.getText().replaceAll(",", ".")));
                break;

            case 7:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                nota.setNota5(Double.parseDouble(nota5Txt.getText().replaceAll(",", ".")));
                nota.setNota6(Double.parseDouble(nota6Txt.getText().replaceAll(",", ".")));
                nota.setNota7(Double.parseDouble(nota7Txt.getText().replaceAll(",", ".")));
                break;

            case 8:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                nota.setNota5(Double.parseDouble(nota5Txt.getText().replaceAll(",", ".")));
                nota.setNota6(Double.parseDouble(nota6Txt.getText().replaceAll(",", ".")));
                nota.setNota7(Double.parseDouble(nota7Txt.getText().replaceAll(",", ".")));
                nota.setNota8(Double.parseDouble(nota8Txt.getText().replaceAll(",", ".")));
                break;
            case 9:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                nota.setNota5(Double.parseDouble(nota5Txt.getText().replaceAll(",", ".")));
                nota.setNota6(Double.parseDouble(nota6Txt.getText().replaceAll(",", ".")));
                nota.setNota7(Double.parseDouble(nota7Txt.getText().replaceAll(",", ".")));
                nota.setNota8(Double.parseDouble(nota8Txt.getText().replaceAll(",", ".")));
                nota.setNota9(Double.parseDouble(nota9Txt.getText().replaceAll(",", ".")));
                break;
            case 10:
                nota.setNota1(Double.parseDouble(nota1Txt.getText().replaceAll(",", ".")));
                nota.setNota2(Double.parseDouble(nota2Txt.getText().replaceAll(",", ".")));
                nota.setNota3(Double.parseDouble(nota3Txt.getText().replaceAll(",", ".")));
                nota.setNota4(Double.parseDouble(nota4Txt.getText().replaceAll(",", ".")));
                nota.setNota5(Double.parseDouble(nota5Txt.getText().replaceAll(",", ".")));
                nota.setNota6(Double.parseDouble(nota6Txt.getText().replaceAll(",", ".")));
                nota.setNota7(Double.parseDouble(nota7Txt.getText().replaceAll(",", ".")));
                nota.setNota8(Double.parseDouble(nota8Txt.getText().replaceAll(",", ".")));
                nota.setNota9(Double.parseDouble(nota9Txt.getText().replaceAll(",", ".")));
                nota.setNota10(Double.parseDouble(nota10Txt.getText().replaceAll(",", ".")));
                break;
        }
    }

    private void cargarSemestre() {
        try {
            resultSet = metodosGeneralesDao.cargaComboSemestre();
            while (resultSet.next()) {
                Semestre semes = new Semestre();
                semes.setIdSemestre(resultSet.getInt("id1_semestre"));
                semes.setSemestre(resultSet.getString("semestre"));
                semestreCmb.addItem(semes);

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private void ocultaCampos() {
        asignaturaTxt.setEnabled(false);
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
        asistenciaTxt.setEnabled(false);
        calcularBtn.setEnabled(false);
        guardarBtn.setEnabled(false);
        activaRecuperacionCkb.setEnabled(false);

    }

    private void limpiaCampos() {
        asignaturaTxt.setText(null);
        nota1Txt.setText(null);
        nota2Txt.setText(null);
        nota3Txt.setText(null);
        nota4Txt.setText(null);
        nota5Txt.setText(null);
        nota6Txt.setText(null);
        nota7Txt.setText(null);
        nota8Txt.setText(null);
        nota9Txt.setText(null);
        nota10Txt.setText(null);
        promedioTxt.setText(null);
        recuperacionTxt.setText(null);
        activaRecuperacionCkb.setSelected(false);
        nota1Lbl.setText("nota1");
        nota2Lbl.setText("nota2");
        nota3Lbl.setText("nota3");
        nota4Lbl.setText("nota4");
        nota5Lbl.setText("nota5");
        nota6Lbl.setText("nota6");
        nota7Lbl.setText("nota7");
        nota8Lbl.setText("nota8");
        nota9Lbl.setText("nota9");
        nota10Lbl.setText("nota10");

    }

    private void cargaCampo(int valorCampos) {
        JTextField[] camp = {nota1Txt, nota2Txt, nota3Txt, nota4Txt, nota5Txt, nota6Txt,
            nota7Txt, nota7Txt, nota8Txt, nota9Txt, nota10Txt};
        int i = 0;
        for (JTextField campo4 : camp) {
            if (i < valorCampos) {
                campo4.setEnabled(true);
                i++;
            } else {
                break;
            }

        }

    }

    private void setDescripcion(ResultSet resultSet, String numCampos) {
        try {
            switch (numCampos) {
                case "1":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    break;
                case "2":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    break;
                case "3":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    break;
                case "4":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    break;
                case "5":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    break;
                case "6":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    break;
                case "7":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    nota7Lbl.setText(resultSet.getString("dm.aporte7"));
                    break;
                case "8":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    nota7Lbl.setText(resultSet.getString("dm.aporte7"));
                    nota8Lbl.setText(resultSet.getString("dm.aporte8"));
                    break;
                case "9":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    nota7Lbl.setText(resultSet.getString("dm.aporte7"));
                    nota8Lbl.setText(resultSet.getString("dm.aporte8"));
                    nota9Lbl.setText(resultSet.getString("dm.aporte9"));
                    break;
                case "10":
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    nota7Lbl.setText(resultSet.getString("dm.aporte7"));
                    nota8Lbl.setText(resultSet.getString("dm.aporte8"));
                    nota9Lbl.setText(resultSet.getString("dm.aporte9"));
                    nota10Lbl.setText(resultSet.getString("dm.aporte9"));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    private void setNota(ResultSet resultSet, String numCampos) {
        try {
            switch (numCampos) {
                case "1":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    break;
                case "2":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    break;
                case "3":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    break;
                case "4":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    break;
                case "5":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    nota5Txt.setText(resultSet.getString("nota5"));
                    break;
                case "6":
                    nota1Txt.setText(resultSet.getString("nota"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    nota5Txt.setText(resultSet.getString("nota5"));
                    nota6Txt.setText(resultSet.getString("nota6"));
                    break;
                case "7":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    nota5Txt.setText(resultSet.getString("nota5"));
                    nota6Txt.setText(resultSet.getString("nota6"));
                    nota7Txt.setText(resultSet.getString("nota7"));
                    break;
                case "8":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    nota5Txt.setText(resultSet.getString("nota5"));
                    nota6Txt.setText(resultSet.getString("nota6"));
                    nota7Txt.setText(resultSet.getString("nota7"));
                    nota8Txt.setText(resultSet.getString("nota8"));
                    break;
                case "9":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    nota5Txt.setText(resultSet.getString("nota5"));
                    nota6Txt.setText(resultSet.getString("nota6"));
                    nota7Txt.setText(resultSet.getString("nota7"));
                    nota8Txt.setText(resultSet.getString("nota8"));
                    nota9Txt.setText(resultSet.getString("nota9"));
                    break;
                case "10":
                    nota1Txt.setText(resultSet.getString("nota1"));
                    nota2Txt.setText(resultSet.getString("nota2"));
                    nota3Txt.setText(resultSet.getString("nota3"));
                    nota4Txt.setText(resultSet.getString("nota4"));
                    nota5Txt.setText(resultSet.getString("nota5"));
                    nota6Txt.setText(resultSet.getString("nota6"));
                    nota7Txt.setText(resultSet.getString("nota7"));
                    nota8Txt.setText(resultSet.getString("nota8"));
                    nota9Txt.setText(resultSet.getString("nota9"));
                    nota10Txt.setText(resultSet.getString("nota10"));
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setConfigMateria(ResultSet resultSet1, String numeroAportes) {

        try {
            switch (numeroAportes) {
                case "1":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    break;
                case "2":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    break;
                case "3":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    break;
                case "4":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    break;
                case "5":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    configuracionMateria.setAporte5(Double.valueOf(resultSet1.getString("aporte5")) / 100);
                    break;
                case "6":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    configuracionMateria.setAporte5(Double.valueOf(resultSet1.getString("aporte5")) / 100);
                    configuracionMateria.setAporte6(Double.valueOf(resultSet1.getString("aporte6")) / 100);
                    break;
                case "7":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    configuracionMateria.setAporte5(Double.valueOf(resultSet1.getString("aporte5")) / 100);
                    configuracionMateria.setAporte6(Double.valueOf(resultSet1.getString("aporte6")) / 100);
                    configuracionMateria.setAporte7(Double.valueOf(resultSet1.getString("aporte7")) / 100);
                    break;
                case "8":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    configuracionMateria.setAporte5(Double.valueOf(resultSet1.getString("aporte5")) / 100);
                    configuracionMateria.setAporte6(Double.valueOf(resultSet1.getString("aporte6")) / 100);
                    configuracionMateria.setAporte7(Double.valueOf(resultSet1.getString("aporte7")) / 100);
                    configuracionMateria.setAporte8(Double.valueOf(resultSet1.getString("aporte8")) / 100);
                    break;
                case "9":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    configuracionMateria.setAporte5(Double.valueOf(resultSet1.getString("aporte5")) / 100);
                    configuracionMateria.setAporte6(Double.valueOf(resultSet1.getString("aporte6")) / 100);
                    configuracionMateria.setAporte7(Double.valueOf(resultSet1.getString("aporte7")) / 100);
                    configuracionMateria.setAporte8(Double.valueOf(resultSet1.getString("aporte8")) / 100);
                    configuracionMateria.setAporte9(Double.valueOf(resultSet1.getString("aporte9")) / 100);
                    break;
                case "10":
                    configuracionMateria.setAporte1(Double.valueOf(resultSet1.getString("aporte1")) / 100);
                    configuracionMateria.setAporte2(Double.valueOf(resultSet1.getString("aporte2")) / 100);
                    configuracionMateria.setAporte3(Double.valueOf(resultSet1.getString("aporte3")) / 100);
                    configuracionMateria.setAporte4(Double.valueOf(resultSet1.getString("aporte4")) / 100);
                    configuracionMateria.setAporte5(Double.valueOf(resultSet1.getString("aporte5")) / 100);
                    configuracionMateria.setAporte6(Double.valueOf(resultSet1.getString("aporte6")) / 100);
                    configuracionMateria.setAporte7(Double.valueOf(resultSet1.getString("aporte7")) / 100);
                    configuracionMateria.setAporte8(Double.valueOf(resultSet1.getString("aporte8")) / 100);
                    configuracionMateria.setAporte9(Double.valueOf(resultSet1.getString("aporte9")) / 100);
                    configuracionMateria.setAporte10(Double.valueOf(resultSet1.getString("aporte10")) / 100);
                    break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FrmNotas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activaRecuperacionCkb;
    private javax.swing.JTextField asignaturaTxt;
    private javax.swing.JTextField asistenciaTxt;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton calcularBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JLabel nota10Lbl;
    private javax.swing.JTextField nota10Txt;
    private javax.swing.JLabel nota1Lbl;
    private javax.swing.JTextField nota1Txt;
    private javax.swing.JLabel nota2Lbl;
    private javax.swing.JTextField nota2Txt;
    private javax.swing.JLabel nota3Lbl;
    private javax.swing.JTextField nota3Txt;
    private javax.swing.JLabel nota4Lbl;
    private javax.swing.JTextField nota4Txt;
    private javax.swing.JLabel nota5Lbl;
    private javax.swing.JTextField nota5Txt;
    private javax.swing.JLabel nota6Lbl;
    private javax.swing.JTextField nota6Txt;
    private javax.swing.JLabel nota7Lbl;
    private javax.swing.JTextField nota7Txt;
    private javax.swing.JLabel nota8Lbl;
    private javax.swing.JTextField nota8Txt;
    private javax.swing.JLabel nota9Lbl;
    private javax.swing.JTextField nota9Txt;
    private javax.swing.JTable notasTabla;
    private javax.swing.JTextField promedioTxt;
    private javax.swing.JTextField recuperacionTxt;
    private javax.swing.JComboBox semestreCmb;
    // End of variables declaration//GEN-END:variables

}
