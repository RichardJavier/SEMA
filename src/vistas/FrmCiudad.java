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
import logica.CiudadDao;
import modelo.Ciudad;

/**
 *
 * @author Ricardo.Bravo
 */
public class FrmCiudad extends javax.swing.JInternalFrame {

    private Integer idCiudad;
    public static FormularioCiudad formularioCiudad;
    private Ciudad ciudad;
    DefaultTableModel modelo;
    ResultSet resultSet;
    List<Ciudad> listaCiudades;
    CiudadDao ciudadDao;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer cod;
    public FrmCiudad() {
        initComponents();
        setLocation(420, 150);
        ciudadDao = new CiudadDao();
        ciudad = new Ciudad();
        cargaDatos();
    }

    private void cargaDatos() {
        String col[] = {"PK", "CIUDAD"};
        String data[][] = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        ciudadTabla.setModel(modelo);
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                if (listaCiudades == null) {
                    listaCiudades = new ArrayList<>();
                } else {
                    listaCiudades.clear();
                }

                try {
                    resultSet = ciudadDao.listadoPais();
                    cod=1;
                    while (resultSet.next()) {
                        ciudad.setIdCiudad(resultSet.getInt("id_ciudad"));
                        ciudad.setCiudad(resultSet.getString("ciudad").toUpperCase());

                        modelo.insertRow(i, new Object[]{
                            ciudad.getIdCiudad(),
                            ciudad.getCiudad()
                        });
                        cod++;
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

    private void llamaFormCiudad() {
        formularioCiudad = new FormularioCiudad(FrmCiudad.this, true, idCiudad);
        formularioCiudad.setLocationRelativeTo(FrmCiudad.this);
        formularioCiudad.setVisible(true);
        cargaDatos();
    }

    public Integer getCod() {
        return cod;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ciudadTabla = new javax.swing.JTable();
        ingresarBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("LISTADO DE CIUDAD");

        ciudadTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ciudadTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ciudadTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ciudadTabla);

        ingresarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        ingresarBtn.setText("Insertar");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingresarBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ingresarBtn)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        idCiudad = 0;
        llamaFormCiudad();
    }//GEN-LAST:event_ingresarBtnActionPerformed

    private void ciudadTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ciudadTablaMouseClicked
        int i = ciudadTabla.getSelectedRow();
        idCiudad = (Integer) ciudadTabla.getValueAt(i, 0);
        llamaFormCiudad();
    }//GEN-LAST:event_ciudadTablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ciudadTabla;
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
