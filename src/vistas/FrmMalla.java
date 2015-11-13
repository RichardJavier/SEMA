/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.MallaDao;
import modelo.Malla;

/**
 *
 * @author USER
 */
public class FrmMalla extends javax.swing.JInternalFrame {

    Malla malla;
    DefaultTableModel modelo;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    List<Malla> listaMalla;
    ResultSet resultSet;
    private Integer idMalla;
    public static FormularioMalla formularioMalla;

    public FrmMalla() {
        initComponents();
        malla = new Malla();
        setLocation(310,100);
        cargaDatos();
        
    }

    private void cargaDatos() {
        String[] col = {"PK", "DESCRIPCION", "FECHA CREACION"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        mallaTabla.setModel(modelo);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    int i = 0;
                    if (listaMalla == null) {
                        listaMalla = new ArrayList<>();
                    } else {
                        listaMalla.clear();
                    }
                    MallaDao mallaDao = new MallaDao();
                    resultSet = mallaDao.listaMalla();
                    while (resultSet.next()) {
                        malla.setIdMalla(resultSet.getInt("id_malla"));
                        malla.setNombreMalla(resultSet.getString("nombre_malla"));
                        malla.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                        modelo.insertRow(i, new Object[]{
                            malla.getIdMalla(),
                            malla.getNombreMalla(),
                            malla.getFechaCreacion()
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Error al cargar" + e.getMessage());
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println("Error al cargar" + e.getMessage());
                    }
                }
            }
        }).start();

    }

    private void llamaFormularioMalla() {
        formularioMalla = new FormularioMalla(FrmMalla.this, true, idMalla);
        formularioMalla.setLocationRelativeTo(FrmMalla.this);
        formularioMalla.setVisible(true);
        cargaDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mallaTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        mallaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        mallaTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mallaTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mallaTabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        idMalla = 0;
        llamaFormularioMalla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mallaTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mallaTablaMouseClicked
        int i = mallaTabla.getSelectedRow();
        idMalla = (Integer) mallaTabla.getValueAt(i, 0);
        llamaFormularioMalla();
    }//GEN-LAST:event_mallaTablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mallaTabla;
    // End of variables declaration//GEN-END:variables
}
