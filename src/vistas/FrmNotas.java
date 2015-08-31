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

        setClosable(true);

        notasTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(542, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        periodo = metodosGeneralesDao.codigoPeriodoActivo();
        if (!cedulaTxt.getText().trim().isEmpty() && semestreCmb.getSelectedIndex() != 0) {
            nombresTxt.setText(notaDao.nombreAlumno(cedulaTxt.getText(),semestre.getIdSemestre()));
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTable notasTabla;
    private javax.swing.JComboBox semestreCmb;
    // End of variables declaration//GEN-END:variables
}
