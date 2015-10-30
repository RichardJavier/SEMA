/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosPeriodoSemestreControl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tablas.DatosPeriodoSemestre;

/**
 *
 * @author Ricardo
 */
public class FrmPeriodo extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Double id1periodo;
    private DefaultTableModel modelo;
    public static FormularioPeriodo formularioPeriodo;
    private List<DatosPeriodoSemestre> listaPeriodo;
  
    

    public FrmPeriodo() {
        super();
        initComponents();
        mostrarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPeriodo = new javax.swing.JTable();
        insertar = new javax.swing.JButton();
        mensaje = new javax.swing.JLabel();
        idcodigo = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);

        tablaPeriodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPeriodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPeriodoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPeriodo);

        insertar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        insertar.setText("Insertar ");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });

        mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(insertar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(idcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(277, 277, 277))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(idcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(insertar)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
      id1periodo = 0.0;
        try {
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_insertarActionPerformed

    private void tablaPeriodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPeriodoMouseClicked
       double i = tablaPeriodo.getSelectedRow();
       int y = (int)Math.round(i);
       id1periodo=Double.parseDouble((String)tablaPeriodo.getValueAt(y, 2));
        try {
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaPeriodoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idcodigo;
    private javax.swing.JButton insertar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTable tablaPeriodo;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatos() {
        String[] col = {"CODIGO", "PERIODO", "PKPERIODO", "MATRICULA", "CICLO", "FECHA FINAL"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        this.tablaPeriodo.setModel(this.modelo);

        //deifinir dimension de columna fecha
        tablaPeriodo.getColumnModel().getColumn(5).setMaxWidth(130);
        tablaPeriodo.getColumnModel().getColumn(5).setMinWidth(130);
        tablaPeriodo.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(130);
        tablaPeriodo.getTableHeader().getColumnModel().getColumn(5).setMinWidth(130);

        tablaPeriodo.setRowSorter(new TableRowSorter<TableModel>(this.modelo));

        this.mensaje.setText("ESPERE.....");

        //inicio del hilo
        new Thread(new Runnable() {

            @Override
            public void run() {
                String codigo;
                String periodo;
                String ciclo;
                String cod;
                Date fechaFinal;
                int i = 0;
                if (listaPeriodo == null) {
                    listaPeriodo = new ArrayList<>();
                } else {
                    listaPeriodo.clear();
                }
                try {
                    DatosPeriodoSemestreControl dpsc = new DatosPeriodoSemestreControl();
                    ResultSet rs = dpsc.consulta(0.0);

                    while (rs.next()) {
                        DatosPeriodoSemestre dp = new DatosPeriodoSemestre();
                        codigo = rs.getString("id_periodo");
                            periodo = rs.getString("periodo");
                        ciclo = rs.getString("ciclo");
                        fechaFinal = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fecha_fin").toString());
                        cod = rs.getString("id1_periodo");
                     //   idcodigo.setText(cod);
                        modelo.insertRow(i++, new Object[]{
                            codigo,
                            periodo,
                            rs.getObject("id1_periodo").toString(),
                            rs.getObject("matricula").toString(),
                            ciclo,
                            new SimpleDateFormat("dd/MM/yyyy").format(fechaFinal)

                        });
                    }
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(FrmPeriodo.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                mensaje.setText("Terminado....");
            }
        }).start();

    }
    public void mostrarDialog() throws SQLException, ParseException{
        formularioPeriodo=new FormularioPeriodo(FrmPeriodo.this,true,id1periodo);
        formularioPeriodo.setLocationRelativeTo(FrmPeriodo.this);
        formularioPeriodo.setVisible(true);
        mostrarDatos();
    }
    
     
}
