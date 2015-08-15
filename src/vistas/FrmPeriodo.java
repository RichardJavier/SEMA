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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.PeriodoDao;
import modelo.Periodo;

/**
 *
 * @author andre_000
 */
public class FrmPeriodo extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo;
    private List<Periodo> listaPeriodo;
    private Periodo periodo;
    ResultSet resultSet;
    private PeriodoDao periodoDao;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    public static FormularioPeriodo formularioPeriodo;
    private Integer idPeriodo;

    public FrmPeriodo() {
        initComponents();
        this.setLocation(310, 110);
        cargarDatos();
    }

    private void cargarDatos() {
        String[] culumnas = {"PK", "NOMBRE PERIODO", "CODIGO PERIODO"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, culumnas);
        modelo.setRowCount(0);
        this.periodoTabla.setModel(modelo);
        periodoTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        this.mensajeLbl.setText("PROCESANDO....");
        new Thread(new Runnable() {

            @Override
            public void run() {
                periodo = new Periodo();
                int i = 0;
                if (listaPeriodo == null) {
                    listaPeriodo = new ArrayList<>();
                } else {
                    listaPeriodo.clear();
                }
                try {
                    periodoDao = new PeriodoDao();
                    resultSet = periodoDao.consultaOrdenada();
                    while (resultSet.next()) {
                        periodo.setCodigoPeriodo(resultSet.getString("id_periodo"));
                        periodo.setFechaFinalizacion(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fecha_fin")));
                        periodo.setNombrePeriodo(resultSet.getString("periodo"));
                        periodo.setIdPeriodo(Integer.valueOf(resultSet.getString("id1_periodo")));
                        modelo.insertRow(i++, new Object[]{
                            periodo.getIdPeriodo(),
                            periodo.getNombrePeriodo(),
                            periodo.getCodigoPeriodo()
                        });
                    }
                } catch (SQLException | ParseException e) {
                    JOptionPane.showMessageDialog(null, "Error la cargar", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                mensajeLbl.setText("TERMINADO.....");
            }
        }).start();

    }

    public void mostrarDialog() throws SQLException, ParseException {
        formularioPeriodo = new FormularioPeriodo(FrmPeriodo.this, true, idPeriodo);
        formularioPeriodo.setLocationRelativeTo(FrmPeriodo.this);
        formularioPeriodo.setVisible(true);
        cargarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        periodoTabla = new javax.swing.JTable();
        ingresarBtn = new javax.swing.JButton();
        mensajeLbl = new javax.swing.JLabel();

        setClosable(true);

        periodoTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        periodoTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                periodoTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(periodoTabla);

        ingresarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        ingresarBtn.setText("Ingresar");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });

        mensajeLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ingresarBtn)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(mensajeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(mensajeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ingresarBtn)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void periodoTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_periodoTablaMouseClicked
        try {
            Integer i = periodoTabla.getSelectedRow();
            idPeriodo = (Integer) periodoTabla.getValueAt(i, 0);
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_periodoTablaMouseClicked

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        try {
            idPeriodo = 0;
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ingresarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mensajeLbl;
    private javax.swing.JTable periodoTabla;
    // End of variables declaration//GEN-END:variables
}
