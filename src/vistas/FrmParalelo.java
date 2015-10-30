/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.ParaleloDao;
import modelo.Paralelo;

/**
 *
 * @author Ricardo.Bravo
 */
public final class FrmParalelo extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private List<Paralelo> listaParalelo;
    ResultSet resultSet;
    DefaultTableModel modelo;
    private Integer idParalelo;
    Paralelo paralelo;
    public static FormularioParalelo formularioParalelo;

    public FrmParalelo() {
        initComponents();
        paralelo = new Paralelo();
        this.setLocation(390, 110);
        cargaDatos();
    }

    public void cargaDatos() {
        String col[] = {"PK", "CODIGO", "NOMBRE PARALELO"};
        String data[][] = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            ;
        @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        paraleloTabla.setModel(modelo);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    int i = 0;
                    if (listaParalelo == null) {
                        listaParalelo = new ArrayList<>();
                    } else {
                        listaParalelo.clear();
                    }
                    ParaleloDao paraleloDao = new ParaleloDao();
                    resultSet = paraleloDao.cargaParalelo();
                    while (resultSet.next()) {
                        paralelo.setIdParelelo(Integer.parseInt(resultSet.getString("id1_paralelo")));
                        paralelo.setCodigoParalelo(resultSet.getString("id_paralelo"));
                        paralelo.setParalelo(resultSet.getString("paralelo"));
                        modelo.insertRow(i, new Object[]{
                            paralelo.getIdParelelo(),
                            paralelo.getCodigoParalelo(),
                            paralelo.getParalelo()
                        });
                    }
                } catch (SQLException | NumberFormatException e) {
                    System.out.println("error al cargar" + e.getMessage());
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    private void cargarFormularioParalelo() {
        formularioParalelo = new FormularioParalelo(FrmParalelo.this, true, idParalelo);
        formularioParalelo.setLocationRelativeTo(FrmParalelo.this);
        formularioParalelo.setVisible(true);
        cargaDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        paraleloTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);

        paraleloTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        paraleloTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paraleloTablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paraleloTablaMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(paraleloTabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        jButton1.setText("Ingresar");
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
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        idParalelo = 0;
        cargarFormularioParalelo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void paraleloTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paraleloTablaMouseClicked
        Integer i = paraleloTabla.getSelectedRow();
        idParalelo = (Integer) paraleloTabla.getValueAt(i, 0);
        cargarFormularioParalelo();
    }//GEN-LAST:event_paraleloTablaMouseClicked

    private void paraleloTablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paraleloTablaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_paraleloTablaMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable paraleloTabla;
    // End of variables declaration//GEN-END:variables
}
