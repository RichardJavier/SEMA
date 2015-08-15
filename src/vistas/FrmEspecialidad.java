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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import logica.EspecialidadDao;
import modelo.Especialidad;

public class FrmEspecialidad extends javax.swing.JInternalFrame {

    private final Especialidad especialidad;
    private List<Especialidad> listaEspecilidad;
    public static FormularioEspecialidad formularioEspecialidad;
    ResultSet resultSet;
    DefaultTableModel modelo;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idEspecialidad;

    public FrmEspecialidad() {
        initComponents();
        especialidad = new Especialidad();
        cargarDatos();
    }

    private void cargarDatos() {
        String[] col = {"PK", "CODIGO", "ESPECIALIDAD"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        especialidadTabla.setModel(modelo);
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                if (listaEspecilidad == null) {
                    listaEspecilidad = new ArrayList<>();
                } else {
                    listaEspecilidad.clear();
                }
                try {
                    EspecialidadDao especialidadDao = new EspecialidadDao();
                    resultSet = especialidadDao.consultaOrdenada();
                    while (resultSet.next()) {
                        especialidad.setCodigoEspecialidad(resultSet.getString("id_especial"));
                        especialidad.setEspecialidad(resultSet.getString("especialidad"));
                        especialidad.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
                        modelo.insertRow(i, new Object[]{
                            especialidad.getIdEspecialidad(),
                            especialidad.getCodigoEspecialidad(),
                            especialidad.getEspecialidad()
                        });
                    }
                } catch (SQLException | NumberFormatException e) {
                    Logger.getLogger(FrmEspecialidad.class.getName()).log(Level.SEVERE, null, e);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        especialidadTabla = new javax.swing.JTable();
        ingresarBtn = new javax.swing.JButton();

        setClosable(true);

        especialidadTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        especialidadTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                especialidadTablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                especialidadTablaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(especialidadTabla);

        ingresarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        ingresarBtn.setText("Ingresar");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingresarBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ingresarBtn)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
   idEspecialidad=0;
   mostrarFormularioEspecialdad();
    }//GEN-LAST:event_ingresarBtnActionPerformed

    private void especialidadTablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_especialidadTablaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_especialidadTablaMouseEntered

    private void especialidadTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_especialidadTablaMouseClicked
        Integer i = especialidadTabla.getSelectedRow();
        idEspecialidad=(Integer)especialidadTabla.getValueAt(i,0);
        mostrarFormularioEspecialdad();
    }//GEN-LAST:event_especialidadTablaMouseClicked
    public void mostrarFormularioEspecialdad() {
        formularioEspecialidad = new FormularioEspecialidad(FrmEspecialidad.this, true, idEspecialidad);
        formularioEspecialidad.setLocationRelativeTo(FrmEspecialidad.this);
        formularioEspecialidad.setVisible(true);
        cargarDatos();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable especialidadTabla;
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
