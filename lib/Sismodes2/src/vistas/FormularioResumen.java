package vistas;

import conectar.Conexion;
import controles.DatosResumenControl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo.Bravo
 */
public class FormularioResumen extends javax.swing.JDialog {

    private static FrmResumen frmResumen;
    private int idresumen;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    DatosResumenControl resumenControl;
    private double proPonderado, porcentajeNota, notaFinal,porTutoria,notaTutoria;
    private String aprobado, nombres, cedula, estado;
    private int idmalla;
    int porTuto;
    private static final BigDecimal valor = new BigDecimal(14);

    public FormularioResumen(FrmResumen parent, boolean modal) {
        FormularioResumen.frmResumen = parent;
        this.setModal(modal);
        this.setLocationRelativeTo(null);
        initComponents();
        ocultaCampos();
    }

    public FormularioResumen(FrmResumen parent, boolean modal, Integer pkResumen) {
        try {
            FormularioResumen.frmResumen = parent;
            this.setModal(modal);
            initComponents();
            ocultaCampos();
            this.setLocationRelativeTo(null);
            this.idresumen = pkResumen;
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
                porTutoria= Double.valueOf(rs.getString("porcentaje_tutoria"));
                notaTutoria = Double.valueOf(rs.getString("nota_tutoria"));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        proPonderadoTxt = new javax.swing.JTextField();
        nombresTxt = new javax.swing.JTextField();
        porNotaTxt = new javax.swing.JTextField();
        notaFinalTxt = new javax.swing.JTextField();
        estadoTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        asistenciaTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        calcularBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        notaTutoriaTxt = new javax.swing.JTextField();
        porTutoriaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombres");

        jLabel3.setText("Promedio Ponderado ");

        jLabel4.setText("Nota Final");

        jLabel5.setText("Porcentaje Nota Final");

        jLabel6.setText("Estado");

        jLabel7.setText("Asistencia");

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

        jLabel8.setText("Porcentaje Tutoria");

        jLabel9.setText("Nota Tutoria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cedulaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(notaFinalTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(proPonderadoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(asistenciaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(porTutoriaTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(71, 71, 71)
                                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(porNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(notaTutoriaTxt)
                                    .addComponent(estadoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(guardarBtn)
                        .addGap(37, 37, 37)
                        .addComponent(calcularBtn)))
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(proPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(porNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(notaFinalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(asistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(notaTutoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(porTutoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(calcularBtn))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        if (notaTutoriaTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error campo nota tutoria vacìo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
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

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        try {
            Map campos = new HashMap();
            campos.put("nota_tutoria", notaTutoriaTxt.getText());
            campos.put("porcentaje_tutoria", porTutoriaTxt.getText());
            campos.put("nota_final", notaFinalTxt.getText());
            campos.put("estado", estadoTxt.getText());
            campos.put("fecha_modificacion", Calendar.getInstance().getTime());
            resumenControl.actualizar("resumen", "id_resumen", idresumen, campos);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al  guardar ","Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_guardarBtnActionPerformed

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

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioResumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioResumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioResumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioResumen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioResumen dialog = new FormularioResumen(frmResumen, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asistenciaTxt;
    private javax.swing.JButton calcularBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JTextField estadoTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextField notaFinalTxt;
    private javax.swing.JTextField notaTutoriaTxt;
    private javax.swing.JTextField porNotaTxt;
    private javax.swing.JTextField porTutoriaTxt;
    private javax.swing.JTextField proPonderadoTxt;
    // End of variables declaration//GEN-END:variables
}
