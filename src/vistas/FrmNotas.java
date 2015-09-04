/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import groovy.inspect.swingui.TableSorter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.MetodosGeneralesDao;
import logica.NotaDao;
import modelo.Alumno;
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
    ResultSet resultSet;
    Nota nota;
    MetodosGeneralesDao metodosGeneralesDao;
    List<Nota> listaNotas;
    NotaDao notaDao;
    Alumno alumnno;
    Periodo periodo;
    Semestre semestre;

    public FrmNotas() {
        initComponents();
        alumnno = new Alumno();
        metodosGeneralesDao = new MetodosGeneralesDao();
        semestre = new Semestre();
        notaDao = new NotaDao();
        cargarSemestre();
        this.nombresTxt.setEnabled(false);
        ocultaCampos();
    }

    private void cargarDatos(final String periodo, final String cedula, final Integer idSemestre) {
        String[] col = {"PK", "CEDULA", "NOMBRE MATERIA", "SEMESTRE", "ESPECIALIDAD"};
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
                            nota.getEspecialidad()
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
        calcularTxt = new javax.swing.JButton();
        guardarTxt = new javax.swing.JButton();
        cancelarTxt = new javax.swing.JButton();

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(asignaturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
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

        calcularTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Calculator.png"))); // NOI18N
        calcularTxt.setText("Calcular");

        guardarTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarTxt.setText("Guardar");

        cancelarTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarTxt.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(calcularTxt))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(26, 26, 26)
                                .addComponent(asistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(promedioTxt))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(guardarTxt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(cancelarTxt))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(asistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(promedioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calcularTxt)
                    .addComponent(guardarTxt)
                    .addComponent(cancelarTxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        periodo = metodosGeneralesDao.codigoPeriodoActivo();
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
        
        int i = notasTabla.getSelectedRow();
        
              
    }//GEN-LAST:event_notasTablaMouseClicked
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
    }
   private void cargaCampo(int valorCampos) {
        JTextField[] camp = {nota1Txt, nota2Txt,nota3Txt,nota4Txt,nota5Txt,nota6Txt,
            nota7Txt,nota7Txt,nota8Txt,nota9Txt,nota10Txt};
        int i = 0;
        for (JTextField campo4 : camp) {
            if (i < valorCampos) {
                campo4.setEnabled(true);
                i++;
            }
        }
       
    }
      private void seteaDescripcion(ResultSet resultSet, int numCampos) {
        try {
            switch (numCampos) {
                case 1:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    break;
                case 2:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    break;
                case 3:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    break;
                case 4:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    break;
                case 5:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    break;
                case 6:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    break;
                case 7:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    nota7Lbl.setText(resultSet.getString("dm.aporte7"));
                    break;
                case 8:
                    nota1Lbl.setText(resultSet.getString("dm.aporte1"));
                    nota2Lbl.setText(resultSet.getString("dm.aporte2"));
                    nota3Lbl.setText(resultSet.getString("dm.aporte3"));
                    nota4Lbl.setText(resultSet.getString("dm.aporte4"));
                    nota5Lbl.setText(resultSet.getString("dm.aporte5"));
                    nota6Lbl.setText(resultSet.getString("dm.aporte6"));
                    nota7Lbl.setText(resultSet.getString("dm.aporte7"));
                    nota8Lbl.setText(resultSet.getString("dm.aporte8"));
                    break;
                case 9:
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
                case 10:
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
        }

    }
      private void seteaConfigMateria(ResultSet resultSet, int numCampos) {
        try {
            switch (numCampos) {
                case 1:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    break;
                case 2:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    break;
                case 3:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    break;
                case 4:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    break;
                case 5:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    nota5Txt.setText(resultSet.getString("aporte5"));
                    break;
                case 6:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    nota5Txt.setText(resultSet.getString("aporte5"));
                    nota6Txt.setText(resultSet.getString("aporte6"));
                    break;
                case 7:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    nota5Txt.setText(resultSet.getString("aporte5"));
                    nota6Txt.setText(resultSet.getString("aporte6"));
                    nota7Txt.setText(resultSet.getString("aporte7"));
                    break;
                case 8:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    nota5Txt.setText(resultSet.getString("aporte5"));
                    nota6Txt.setText(resultSet.getString("aporte6"));
                    nota7Txt.setText(resultSet.getString("aporte7"));
                    nota8Txt.setText(resultSet.getString("aporte8"));
                    break;
                case 9:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    nota5Txt.setText(resultSet.getString("aporte5"));
                    nota6Txt.setText(resultSet.getString("aporte6"));
                    nota7Txt.setText(resultSet.getString("aporte7"));
                    nota8Txt.setText(resultSet.getString("aporte8"));
                    nota9Txt.setText(resultSet.getString("aporte9"));
                    break;
                case 10:
                    nota1Txt.setText(resultSet.getString("aporte1"));
                    nota2Txt.setText(resultSet.getString("aporte2"));
                    nota3Txt.setText(resultSet.getString("aporte3"));
                    nota4Txt.setText(resultSet.getString("aporte4"));
                    nota5Txt.setText(resultSet.getString("aporte5"));
                    nota6Txt.setText(resultSet.getString("aporte6"));
                    nota7Txt.setText(resultSet.getString("aporte7"));
                    nota8Txt.setText(resultSet.getString("aporte8"));
                    nota9Txt.setText(resultSet.getString("aporte9"));
                    nota10Txt.setText(resultSet.getString("aporte10"));
                    break;
            }

        } catch (Exception e) {
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asignaturaTxt;
    private javax.swing.JTextField asistenciaTxt;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton calcularTxt;
    private javax.swing.JButton cancelarTxt;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JButton guardarTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JComboBox semestreCmb;
    // End of variables declaration//GEN-END:variables
}
