/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import control.Crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.EspecialidadDao;
import modelo.Especialidad;

/**
 *
 * @author andre_000
 */
public class FormularioEspecialidad extends javax.swing.JDialog {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    public static FrmEspecialidad frmEspecialidad;
    private Integer idEspecialidad;
    EspecialidadDao especialidadDao;
    ResultSet resultSet;
    Especialidad especialidad;
    Crud crud;
    Map campos;

    public FormularioEspecialidad(FrmEspecialidad parent, boolean modal) {
        FormularioEspecialidad.frmEspecialidad = parent;
        this.setModal(modal);
        initComponents();
        codigoTxt.setEnabled(false);
        especialidad = new Especialidad();
        cargarCodigo();
    }

    public FormularioEspecialidad(FrmEspecialidad parent, boolean modal, Integer idEspecialidad) {
        FormularioEspecialidad.frmEspecialidad = parent;
        this.setModal(modal);
        initComponents();
        this.idEspecialidad = idEspecialidad;
        codigoTxt.setEnabled(false);
        especialidad = new Especialidad();
        especialidadDao = new EspecialidadDao();
        cargarCodigo();
        try {
            if (idEspecialidad > 0) {
                resultSet = especialidadDao.consulta(idEspecialidad);
                while (resultSet.next()) {
                    especialidad.setCodigoEspecialidad(resultSet.getString("id_especial"));
                    especialidad.setEspecialidad(resultSet.getString("especialidad"));
                    codigoTxt.setText(especialidad.getCodigoEspecialidad());
                    nombreEspecialidadTxt.setText(especialidad.getEspecialidad());
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codigoTxt = new javax.swing.JTextField();
        nombreEspecialidadTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO DE REGISTRO ESPECIALIDAD");
        setIconImage(new ImageIcon(getClass().getResource("/recursos/iconPr.png")).getImage());

        jLabel1.setText("Codifo Especialidad");

        jLabel2.setText("Nombre Especialidad");

        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(guardarBtn)
                        .addGap(50, 50, 50)
                        .addComponent(cancelarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreEspecialidadTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreEspecialidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(cancelarBtn))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        crud = new Crud();
        if (validaForm()) {
            cargaDatos();
            if (idEspecialidad == 0) {
                crud.insertarM("especialidad", campos,Ingreso.getUsuario().getNombre());
                this.dispose();
            } else {
                crud.actualizarM("especialidad", "id1_especialidad", idEspecialidad, campos,Ingreso.getUsuario().getNombre());
                this.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarBtnActionPerformed
    private boolean validaForm() {
        boolean resultado = true;
        if (codigoTxt.getText().trim().isEmpty()) {
            resultado = false;
        }
        if (nombreEspecialidadTxt.getText().trim().isEmpty()) {
            resultado = false;
        }
        return resultado;
    }

    private Map cargaDatos() {
        campos = new HashMap();
        especialidad.setCodigoEspecialidad(codigoTxt.getText());
        especialidad.setEspecialidad(nombreEspecialidadTxt.getText().toUpperCase());
        campos.put("id_especial", especialidad.getCodigoEspecialidad());
        campos.put("especialidad", especialidad.getEspecialidad());
        return campos;
    }

    private void cargarCodigo() {
        try {
            especialidadDao = new EspecialidadDao();
            resultSet = especialidadDao.consultaOrdenada();
            String fila[] = new String[6];
            int index = 2;
            while (resultSet.next()) {
                fila[3] = String.valueOf("ES0" + index);
                index++;
                codigoTxt.setText(fila[3]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(FormularioEspecialidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioEspecialidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioEspecialidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioEspecialidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioEspecialidad dialog = new FormularioEspecialidad(frmEspecialidad, true);
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
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nombreEspecialidadTxt;
    // End of variables declaration//GEN-END:variables
}
