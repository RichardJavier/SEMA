/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosResumenControl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tablas.DatosResumen;

/**
 *
 * @author Ricardo.Bravo
 */
public class FrmResumen extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private List<DatosResumen> listaResumen;
    private DatosResumenControl resumenControl;
    ResultSet rs;
    private int idresumen;
    public static FormularioResumen formularioResumen;

    private double proPonderado, porcentajeNota, notaFinal, porTutoria, notaTutoria;
    private String aprobado, nombres, cedula, estado;
    private int idmalla;
    int porTuto;
    private static final BigDecimal valor = new BigDecimal(14);

    public FrmResumen() {
        initComponents();
        resumenControl = new DatosResumenControl();
        cargarDatos();
        ocultaCampos();
    }

    private void cargarDatos() {
        String[] campos = {"PK", "CEDULA", "NOMBRES COMPLETOS", "NOTA FINAL", "ESTADO", "ASISTENCIA"};
        String[][] data = {{" ", " ", " "}};
        modelo = new DefaultTableModel(data, campos);
        modelo.setRowCount(0);
        this.tablaResumen.setModel(modelo);
        this.tablaResumen.setRowSorter(new TableRowSorter<TableModel>(modelo));
        new Thread(new Runnable() {

            @Override
            public void run() {
                String cedula;
                String nombres;
                double notaFinal;
                String estado;
                String asistencia;
                int i = 0;
                if (listaResumen == null) {
                    listaResumen = new ArrayList<>();
                } else {
                    listaResumen.clear();
                }
                try {
                    rs = resumenControl.listaResumen(0);
                    while (rs.next()) {
                        cedula = rs.getString("cedula");
                        nombres = rs.getString("nombres").toUpperCase();
                        notaFinal = Double.valueOf(rs.getString("nota_Final"));
                        estado = rs.getString("estado").toUpperCase();
                        asistencia = rs.getString("asistencia").toUpperCase();
                        if (filtroTxt.equals("")|| cedula.contains(filtroTxt.getText())) {
                            int idresumen = (int)rs.getObject("id_resumen");
                            listaResumen.add(new DatosResumen(idresumen, cedula, nombres, notaFinal, estado));
                            modelo.insertRow(i++, new Object[]{
                                rs.getObject("id_resumen"),
                                cedula,
                                nombres,
                                notaFinal,
                                estado,
                                asistencia
                            });
                        }
                    }
                } catch (SQLException | NumberFormatException e) {
                    Logger.getLogger(FrmResumen.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, "Error al cargar la Lista", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        Logger.getLogger(FrmResumen.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResumen = new javax.swing.JTable();
        panelResumen = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        nombresTxt = new javax.swing.JTextField();
        porNotaTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        estadoTxt = new javax.swing.JTextField();
        notaTutoriaTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        asistenciaTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        notaFinalTxt = new javax.swing.JTextField();
        proPonderadoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        porTutoriaTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        calcularBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buscarBtn = new javax.swing.JButton();
        filtroTxt = new javax.swing.JTextField();

        setClosable(true);

        tablaResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaResumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaResumenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaResumen);

        panelResumen.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso Nota Final"));

        jLabel1.setText("Cedula");

        jLabel5.setText("Ponderacion Nota");

        jLabel6.setText("Estado");

        jLabel9.setText("Nota Tutoria");

        jLabel7.setText("Asistencia");

        jLabel4.setText("Nota Final");

        jLabel3.setText("Promedio Ponderado ");

        jLabel8.setText("Porcentaje Tutoria");

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombres");

        javax.swing.GroupLayout panelResumenLayout = new javax.swing.GroupLayout(panelResumen);
        panelResumen.setLayout(panelResumenLayout);
        panelResumenLayout.setHorizontalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResumenLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cedulaTxt)
                            .addComponent(notaFinalTxt)
                            .addComponent(proPonderadoTxt)
                            .addComponent(asistenciaTxt)
                            .addComponent(porTutoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(71, 71, 71)
                                .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelResumenLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(porNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResumenLayout.createSequentialGroup()
                                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(notaTutoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(guardarBtn)
                        .addGap(37, 37, 37)
                        .addComponent(calcularBtn)))
                .addContainerGap())
        );
        panelResumenLayout.setVerticalGroup(
            panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(proPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelResumenLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(porNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(29, 29, 29)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(notaFinalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(asistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(notaTutoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(porTutoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(panelResumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(calcularBtn))
                .addContainerGap())
        );

        jLabel10.setText("Cedula");

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(panelResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel10)
                .addGap(34, 34, 34)
                .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(buscarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(buscarBtn)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelResumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void cargarPanel(int idresumen) {
        try {
            resumenControl = new DatosResumenControl();
            ResultSet rs = resumenControl.listaResumen(idresumen);
            while (rs.next()) {
                proPonderado = Double.valueOf(rs.getString("pro_ponderado_nota"));
                porcentajeNota = Double.valueOf(rs.getString("porcentaje_nota"));
                notaFinal = Double.valueOf(rs.getString("nota_final"));
                aprobado = rs.getString("estado");
                nombres = rs.getString("nombres");
                cedula = rs.getString("cedula");
                estado = rs.getString("estado");
                idmalla = Integer.valueOf(rs.getString("id_malla"));
                porTutoria = Double.valueOf(rs.getString("porcentaje_tutoria"));
                notaTutoria = Double.valueOf(rs.getString("nota_tutoria"));
                asistenciaTxt.setText(rs.getString("asistencia"));
            }
            cedulaTxt.setText(cedula);
            nombresTxt.setText(nombres);
            proPonderadoTxt.setText(String.valueOf(proPonderado));
            porNotaTxt.setText(String.valueOf(porcentajeNota));
            notaFinalTxt.setText(String.valueOf(notaFinal));
            estadoTxt.setText(estado);
            porTutoriaTxt.setText(String.valueOf(porTutoria));
            notaTutoriaTxt.setText(String.valueOf(notaTutoria));
        } catch (SQLException ex) {
            Logger.getLogger(FormularioResumen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void tablaResumenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaResumenMouseClicked
        int i = tablaResumen.getSelectedRow();
        idresumen = (Integer) tablaResumen.getValueAt(i, 0);
        cargarPanel(idresumen);

    }//GEN-LAST:event_tablaResumenMouseClicked

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        try {
            Map campos = new HashMap();
            campos.put("nota_tutoria", notaTutoriaTxt.getText());
            campos.put("porcentaje_tutoria", porTutoriaTxt.getText());
            campos.put("nota_final", notaFinalTxt.getText());
            campos.put("estado", estadoTxt.getText());
            campos.put("fecha_modificacion", Calendar.getInstance().getTime());
            resumenControl.actualizar("resumen", "id_resumen", idresumen, campos);
            limpia();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al  guardar ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarBtnActionPerformed
    private void limpia() {
        cedulaTxt.setText(null);
        nombresTxt.setText(null);
        proPonderadoTxt.setText(null);
        porNotaTxt.setText(null);
        porTutoriaTxt.setText(null);
        estadoTxt.setText(null);
        asistenciaTxt.setText(null);
        notaTutoriaTxt.setText(null);
        notaFinalTxt.setText(null);
    }

    private void ocultaCampos() {
        cedulaTxt.setEnabled(false);
        nombresTxt.setEnabled(false);
        proPonderadoTxt.setEnabled(false);
        porNotaTxt.setEnabled(false);
        notaFinalTxt.setEnabled(false);
        estadoTxt.setEnabled(false);
        asistenciaTxt.setEnabled(false);
        porTutoriaTxt.setEnabled(false);
        guardarBtn.setEnabled(false);
    }
    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        if (notaTutoriaTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error campo nota tutoria vacìo", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            notaTutoria = Double.parseDouble(notaTutoriaTxt.getText());
            int por = 100;
            porTuto = resumenControl.porcentajeTutoria(idmalla);
            BigDecimal res = new BigDecimal(porTuto).divide(new BigDecimal(por));
            BigDecimal porTutoria = new BigDecimal(notaTutoria).multiply(res);
            porTutoria = porTutoria.setScale(1, RoundingMode.HALF_UP);
            BigDecimal notaTotal = new BigDecimal(porcentajeNota).add(porTutoria);
            notaTotal = notaTotal.setScale(1, RoundingMode.HALF_UP);
            
            if (notaTotal.compareTo(valor) < 0) {
                estado = "REPROBADO";
            } else {
                estado = "APROBADO";
            }
            porTutoriaTxt.setText(String.valueOf(porTutoria));
            notaFinalTxt.setText(String.valueOf(notaTotal));
            estadoTxt.setText(estado);
            guardarBtn.setEnabled(true);
            notaTutoriaTxt.setEnabled(false);
        }
    }//GEN-LAST:event_calcularBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        cargarDatos();
    }//GEN-LAST:event_buscarBtnActionPerformed
    private void llamarForm() {
        formularioResumen = new FormularioResumen(FrmResumen.this, true, idresumen);
        formularioResumen.setLocationRelativeTo(FrmResumen.this);
        formularioResumen.setVisible(true);
        cargarDatos();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asistenciaTxt;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton calcularBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JTextField estadoTxt;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextField notaFinalTxt;
    private javax.swing.JTextField notaTutoriaTxt;
    private javax.swing.JPanel panelResumen;
    private javax.swing.JTextField porNotaTxt;
    private javax.swing.JTextField porTutoriaTxt;
    private javax.swing.JTextField proPonderadoTxt;
    private javax.swing.JTable tablaResumen;
    // End of variables declaration//GEN-END:variables
}
