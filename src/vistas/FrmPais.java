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
import logica.PaisDao;
import modelo.Pais;

/**
 *
 * @author Ricardo.Bravo
 */
public class FrmPais extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    List<Pais> listadoPais;
    PaisDao paisDao;
    Pais pais;
    ResultSet resultSet;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idPais;
    private static FormularioPais formularioPais;
    private static String paisN;

    public FrmPais() {
        initComponents();
        this.setLocation(430, 150);
        paisDao = new PaisDao();
        pais = new Pais();
        cargaDatos();
    }

    private void cargaDatos() {
        String col[] = {"PK", "PAIS", "NACIONALIDAD"};
        String data[][] = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        paisTabla.setModel(modelo);
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                if (listadoPais == null) {
                    listadoPais = new ArrayList<>();
                } else {
                    listadoPais.clear();
                }

                try {
                    resultSet = paisDao.listadoPais();
                    while (resultSet.next()) {
                        pais.setIdPais(resultSet.getInt("id_pais"));
                        pais.setPais(resultSet.getString("pais"));
                        pais.setNacionalidad(resultSet.getString("nacion").toUpperCase());

                        modelo.insertRow(i, new Object[]{
                            pais.getIdPais(),
                            pais.getPais(),
                            pais.getNacionalidad()});
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }).start();
    }

    private void llamaFormPais() {
        formularioPais = new FormularioPais(FrmPais.this, true, idPais, paisN);
        formularioPais.setLocationRelativeTo(FrmPais.this);
        formularioPais.setVisible(true);
        cargaDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        paisTabla = new javax.swing.JTable();
        insertarBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("LISTADO DE PAIS");

        paisTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        paisTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paisTablaMouseClicked(evt);
            }
        });
        paisTabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paisTablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(paisTabla);

        insertarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        insertarBtn.setText("Insertar");
        insertarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertarBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(insertarBtn)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paisTablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paisTablaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_paisTablaKeyPressed

    private void paisTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paisTablaMouseClicked
        int i = paisTabla.getSelectedRow();
        idPais = (Integer) paisTabla.getValueAt(i, 0);
        paisN ="a";
        llamaFormPais();


    }//GEN-LAST:event_paisTablaMouseClicked

    private void insertarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBtnActionPerformed
        idPais=0;
        paisN="i";
        llamaFormPais();
    }//GEN-LAST:event_insertarBtnActionPerformed

    public static String getPaisN() {
        return paisN;
    }

    public static void setPaisN(String paisN) {
        FrmPais.paisN = paisN;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable paisTabla;
    // End of variables declaration//GEN-END:variables
}
