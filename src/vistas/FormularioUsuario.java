/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.Crud;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import logica.UsuarioDao;
import modelo.Usuario;

/**
 *
 * @author USER
 */
public class FormularioUsuario extends javax.swing.JDialog {
    
    Usuario usuario;
    public static FrmUsuario frmUsuario;
    private Integer idUsuario;
    ResultSet resultSet;
    
    public FormularioUsuario(FrmUsuario parent, boolean modal) {
        FormularioUsuario.frmUsuario = parent;
        this.setModal(modal);
        initComponents();
        usuario = new Usuario();
    }
    
    public FormularioUsuario(FrmUsuario parent, boolean modal, Integer idUsuario) {
        FormularioUsuario.frmUsuario = parent;
        this.setModal(modal);
        initComponents();
        this.idUsuario = idUsuario;
        usuario = new Usuario();
        if (idUsuario > 0) {
            try {
                UsuarioDao usuarioDao = new UsuarioDao();
                resultSet = usuarioDao.cargaUsuario(idUsuario);
                while (resultSet.next()) {
                    usuario.setDocumento(resultSet.getString("documento"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setClave(resultSet.getString("clave"));
                    usuario.setPerfil(resultSet.getString("perfil"));
                    usuario.setEstado(resultSet.getString("estado"));
                    cedulaTxt.setText(usuario.getDocumento());
                    nombreTxt.setText(usuario.getNombre());
                    passwordTxt.setText(usuario.getClave());
                    perfilCmb.setSelectedItem(usuario.getPerfil());
                    if (usuario.getEstado().compareTo("AC") == 0) {
                        activoRdb.setSelected(true);
                    } else {
                        inactivoRdb.setSelected(true);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estadoGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        activoRdb = new javax.swing.JRadioButton();
        inactivoRdb = new javax.swing.JRadioButton();
        ingresarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        cedulaTxt = new javax.swing.JTextField();
        nombreTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        perfilCmb = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO USUARIO");

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombres ");

        jLabel3.setText("Clave");

        jLabel4.setText("Perfil");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado Usuario"));

        estadoGroup.add(activoRdb);
        activoRdb.setText("Activo");
        activoRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activoRdbActionPerformed(evt);
            }
        });

        estadoGroup.add(inactivoRdb);
        inactivoRdb.setText("Inactivo");
        inactivoRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inactivoRdbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(activoRdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inactivoRdb))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activoRdb)
                    .addComponent(inactivoRdb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ingresarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        ingresarBtn.setText("Ingresar");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        passwordTxt.setText("jPasswordField1");

        perfilCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "ADMINISTRADOR", "PROFESOR", "ALUMNO" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ingresarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarBtn))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(perfilCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(perfilCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingresarBtn)
                    .addComponent(cancelarBtn))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void activoRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activoRdbActionPerformed
        usuario.setEstado("AC");
    }//GEN-LAST:event_activoRdbActionPerformed

    private void inactivoRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inactivoRdbActionPerformed
        usuario.setEstado("DS");
    }//GEN-LAST:event_inactivoRdbActionPerformed

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        if (validaForm() == true) {
            Calendar cal = Calendar.getInstance();
            Map campos = new HashMap();
            campos.put("documento", cedulaTxt.getText());
            campos.put("nombre", nombreTxt.getText().toUpperCase());
            campos.put("fecha_ingreso", cal.getTime());
            campos.put("usuario", cedulaTxt.getText());
            campos.put("clave", passwordTxt.getText());
            campos.put("perfil", perfilCmb.getSelectedItem());
            campos.put("estado", usuario.getEstado());
            Crud crud = new Crud();
            if (idUsuario == 0) {
                crud.insertarM("usuario", campos);
                this.dispose();
            } else {
                crud.actualizarM("usuario", "idusuario", idUsuario, campos);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ingresarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed
    private Boolean validaForm() {
        boolean re = false;
        if (cedulaTxt.getText().trim().isEmpty()) {
            re = false;
        } else if (nombreTxt.getText().trim().isEmpty()) {
            re = false;
        } else if (passwordTxt.getText().trim().isEmpty()) {
            re = false;
        } else if (perfilCmb.getSelectedIndex() == 0) {
            re = false;
        } else if (estadoGroup.isSelected(null)) {
            re = false;
        } else {
            re = true;
        }
        return re;
    }

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
            java.util.logging.Logger.getLogger(FormularioUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioUsuario dialog = new FormularioUsuario(frmUsuario, true);
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
    private javax.swing.JRadioButton activoRdb;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.ButtonGroup estadoGroup;
    private javax.swing.JRadioButton inactivoRdb;
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JComboBox perfilCmb;
    // End of variables declaration//GEN-END:variables
}
