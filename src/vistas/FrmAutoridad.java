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
import logica.AutoridadesDao;
import modelo.Autoridad;

/**
 *
 * @author Ricardo.Bravo
 */
public class FrmAutoridad extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    ResultSet resultSet;
    private Integer idAutoridad;
    DefaultTableModel modelo;
    Autoridad autoridad;
    List<Autoridad> listaAutoridades;
    public static  FormularioAutoridad formularioAutoridad;
    public FrmAutoridad() {
        initComponents();
        autoridad = new Autoridad();
        this.setLocation(450, 170);
        cargaDatos();
    }

    private void cargaDatos() {
        String [] col = {"PK", "NOMBRES", "CARGO", "FECHA DE CREACION"};
        String [][] dat = {{"", "", ""}};
        modelo = new DefaultTableModel(dat, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        autoridadesTabla.setModel(modelo);
        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                try {
                    if (listaAutoridades == null) {
                        listaAutoridades = new ArrayList<>();
                    } else {
                        listaAutoridades.clear();
                    }
                    AutoridadesDao autoridadesDao = new AutoridadesDao();
                    resultSet = autoridadesDao.cargaAutoridades();
                    while (resultSet.next()) {
                        autoridad.setIdAutoridades(resultSet.getInt("idautoridades"));
                        autoridad.setNombre(resultSet.getString("nombre"));
                        autoridad.setCargo(resultSet.getString("cargo"));
                        autoridad.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                        modelo.insertRow(i, new Object[]{
                            autoridad.getIdAutoridades(),
                            autoridad.getNombre(),
                            autoridad.getCargo(),
                            autoridad.getFechaCreacion()
                        });
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        autoridadesTabla = new javax.swing.JTable();
        ingresarBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("Listado de Autoridades");

        autoridadesTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        autoridadesTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autoridadesTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(autoridadesTabla);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingresarBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ingresarBtn)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        idAutoridad=0;
        mostrarFormularioAutoridad();
    }//GEN-LAST:event_ingresarBtnActionPerformed

    private void autoridadesTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autoridadesTablaMouseClicked
      Integer i=(Integer)autoridadesTabla.getSelectedRow();
      idAutoridad=(Integer)autoridadesTabla.getValueAt(i,0);
      mostrarFormularioAutoridad();
       
    }//GEN-LAST:event_autoridadesTablaMouseClicked
 public void mostrarFormularioAutoridad() {
        formularioAutoridad = new FormularioAutoridad(FrmAutoridad.this,true, idAutoridad);
        formularioAutoridad.setLocationRelativeTo(FrmAutoridad.this);
        formularioAutoridad.setVisible(true);
        cargaDatos();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable autoridadesTabla;
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
