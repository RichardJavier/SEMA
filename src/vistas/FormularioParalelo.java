/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.Crud;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.ParaleloDao;

/**
 *
 * @author Ricardo.Bravo
 */
public class FormularioParalelo extends javax.swing.JDialog {
    
    private Integer idParalelo;
    public static FrmParalelo frmParalelo;
    ParaleloDao paraleloDao;
    ResultSet resultSet;
    public FormularioParalelo(FrmParalelo parent, boolean modal) {
        FormularioParalelo.frmParalelo = parent;
        this.setModal(modal);
        initComponents();
        codigoPaTxt.setEnabled(false);
        paraleloDao = new ParaleloDao();
        cargaCodigo();
    }
    
    public FormularioParalelo(FrmParalelo parent, boolean modal, Integer idParalelo) {
        FormularioParalelo.frmParalelo = parent;
        this.setModal(modal);
        this.idParalelo = idParalelo;
        initComponents();
        codigoPaTxt.setEnabled(false);
        paraleloDao = new ParaleloDao();
        cargaCodigo();
        if (idParalelo > 0) {
            try {
                paraleloDao=new ParaleloDao();
                resultSet=paraleloDao.cargaParalelo(idParalelo);
                while(resultSet.next()){
                    codigoPaTxt.setText(resultSet.getString("id_paralelo"));
                    nombrePaTxt.setText(resultSet.getString("paralelo"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cargaCodigo() {
        int codigo = paraleloDao.codigoMax();
        codigoPaTxt.setText("PA0".concat(String.valueOf(codigo + 1)));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombrePaTxt = new javax.swing.JTextField();
        codigoPaTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        cancelarBtnç = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO PARALELO");
        setIconImage(new ImageIcon(getClass().getResource("/recursos/iconPr.png")).getImage());

        jLabel3.setText("Codigo Paralelo");

        jLabel4.setText("Nombre Paralelo");

        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarBtn.setText("Ingresar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtnç.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtnç.setText("Cancelar");
        cancelarBtnç.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnçActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codigoPaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guardarBtn)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nombrePaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelarBtnç))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(codigoPaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombrePaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(cancelarBtnç))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        Map campos = new HashMap();
        if (!nombrePaTxt.getText().trim().isEmpty()) {
            campos.put("id_paralelo", codigoPaTxt.getText());
            campos.put("paralelo", nombrePaTxt.getText().toUpperCase());
            Crud crud = new Crud();
            if (idParalelo == 0) {
                crud.insertarM("paralelo", campos,Ingreso.getUsuario().getNombre());
                this.dispose();
            } else {
                crud.actualizarM("paralelo", "id1_paralelo", idParalelo, campos,Ingreso.getUsuario().getNombre());
                this.dispose();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacio", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_guardarBtnActionPerformed

    private void cancelarBtnçActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnçActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnçActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormularioParalelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioParalelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioParalelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioParalelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioParalelo dialog = new FormularioParalelo(frmParalelo, true);
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
    private javax.swing.JButton cancelarBtnç;
    private javax.swing.JTextField codigoPaTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombrePaTxt;
    // End of variables declaration//GEN-END:variables
}
