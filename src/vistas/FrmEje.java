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
import logica.EjeDao;
import modelo.Eje;

/**
 *
 * @author Ricardo.Bravo
 */
public class FrmEje extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    List<Eje> listaEjes;
    DefaultTableModel modelo;
    ResultSet resultSet;
    Eje eje;
    private Integer idEje;
    public static FormularioEje formularioEje;
    public FrmEje() {
        initComponents();
        eje = new Eje();
        this.setLocation(390, 110);
        cargaDatos();
    }

    private void cargaDatos() {
        String[] col = {"PK", "CODIGO", "NOMBRE EJE"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        ejeTabla.setModel(modelo);
        ejeTabla.getColumnModel().getColumn(0).setMaxWidth(30);
        ejeTabla.getColumnModel().getColumn(0).setMinWidth(30);
        ejeTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(30);
        ejeTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(30);
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                if (listaEjes == null) {
                    listaEjes = new ArrayList<>();
                } else {
                    listaEjes.clear();
                }
                try {
                    EjeDao ejeDao = new EjeDao();
                    resultSet = ejeDao.cargaEje();
                    while (resultSet.next()) {
                        eje.setIdEje(Integer.parseInt(resultSet.getString("id1_ejes")));
                        eje.setCodigoEje(resultSet.getString("id_ejes"));
                        eje.setNombreEje(resultSet.getString("nom_ejes"));
                        modelo.insertRow(i, new Object[]{
                            eje.getIdEje(),
                            eje.getCodigoEje(),
                            eje.getNombreEje()
                        });
                    }
                } catch (SQLException | NumberFormatException e) {
                    System.out.println("Error" + e.getMessage());
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

    }
    public void mostrarFormularioEje() {
        formularioEje=new FormularioEje(FrmEje.this, true, idEje);
        formularioEje.setLocationRelativeTo(FrmEje.this);
        formularioEje.setVisible(true);
        cargaDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ejeTabla = new javax.swing.JTable();
        insertarBtn = new javax.swing.JButton();

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
        setResizable(true);
        setTitle("LISTADO DE EJES");

        ejeTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ejeTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ejeTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ejeTabla);

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
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertarBtn)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertarBtn)
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBtnActionPerformed
       idEje=0;
       mostrarFormularioEje();
    }//GEN-LAST:event_insertarBtnActionPerformed

    private void ejeTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ejeTablaMouseClicked
        Integer i = ejeTabla.getSelectedRow();
        idEje=(Integer)ejeTabla.getValueAt(i,0);
        mostrarFormularioEje();
    }//GEN-LAST:event_ejeTablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ejeTabla;
    private javax.swing.JButton insertarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
