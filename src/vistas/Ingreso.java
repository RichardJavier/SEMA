/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;
import logica.LoginAuditoriaDao;
import logica.UsuarioDao;
import modelo.LoginAuditoria;
import modelo.Usuario;

/**
 *
 * @author USER
 */
public class Ingreso extends javax.swing.JFrame {

    public static Usuario usuario;
    public Ingreso() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
     public void cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                     confirmarSalida();
                }

            });
            this.setVisible(true);
        } catch (Exception e) {
        }
    }
     public void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de cerrar la Aplicacion", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);

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

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        usuarioTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO DE INGRESO");
        setIconImage(new ImageIcon(getClass().getResource("/recursos/iconPr.png")).getImage());
        setUndecorated(true);

        jLabel3.setBackground(new java.awt.Color(102, 255, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconPr.png"))); // NOI18N
        jLabel3.setText(" INGRESO AL SISTEMA");

        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lock1_1.png"))); // NOI18N
        jLabel1.setText("Password");

        passwordTxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        passwordTxt.setText("jPasswordField1");
        passwordTxt.setFocusCycleRoot(true);
        passwordTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordTxtMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/user_unlock.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/application_delete.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        usuarioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioTxtKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Boss.png"))); // NOI18N
        jLabel2.setText("Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel1))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("SISMA VERSION 1.0.3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordTxtMouseClicked
        passwordTxt.setText(null);
    }//GEN-LAST:event_passwordTxtMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (usuarioTxt.getText().trim().isEmpty() && passwordTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
            usuarioTxt.requestFocus();
        } else if (usuarioTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error campo usuario vacio", "Error", JOptionPane.ERROR_MESSAGE);
            usuarioTxt.requestFocus();
        } else if (passwordTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error campo password vacio", "Error", JOptionPane.ERROR_MESSAGE);
            passwordTxt.requestFocus();
        } else {
            Usuario temp = new Usuario();
            temp.setUsuario(usuarioTxt.getText().trim());
            temp.setClave(passwordTxt.getText().trim());
            usuario = UsuarioDao.login(temp.getUsuario());
            if (usuario.getUsuario() != null) {
                if (temp.getUsuario().compareTo(usuario.getUsuario()) != 0) {
                    JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                    usuarioTxt.setText(null);
                    usuarioTxt.requestFocus();
                    Ingreso.guardarLogin("VACIO", temp,"ERROR",Boolean.FALSE);
                } else if (temp.getClave().compareTo(usuario.getClave()) != 0) {
                    JOptionPane.showMessageDialog(null, "Estimado " + " " + usuario.getNombre() + " " + "su clave es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    passwordTxt.setText(null);
                    passwordTxt.requestFocus();
                    Ingreso.guardarLogin("INTENTO", usuario,"INCORRECTA",Boolean.FALSE);
                } else if (usuario.getEstado().compareTo("DS") == 0) {
                    JOptionPane.showMessageDialog(null, "Estimado" + "" + usuario.getNombre() + " " + "su usuario esta desactivado", "Informacion", JOptionPane.WARNING_MESSAGE);
                    Ingreso.guardarLogin("INTENTO", usuario,"BLOQUEADO",Boolean.FALSE);
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema" + " " + usuario.getNombre());
                    if (usuario.getPerfil().compareTo("ADMINISTRADOR") == 0) {
                        Menu m = new Menu();
                        m.setVisible(true);
                        Ingreso.guardarLogin("MenuAdministrador", usuario,"INICIO DE SESION",Boolean.TRUE);
                        this.dispose();
                    } else if (usuario.getPerfil().compareTo("PROFESOR") == 0) {
                        usuario.setNombre(usuario.getNombre());
                        usuario.setPerfil(usuario.getPerfil());
                        MenuProfesor mn = new MenuProfesor();
                        guardarLogin("MenuProfesor", usuario,"INICIO DE SESION",Boolean.TRUE);
                        mn.setVisible(true);
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ingresado no existe", "Informacion", JOptionPane.WARNING_MESSAGE);
                usuarioTxt.setText(null);
                usuarioTxt.requestFocus();
                Ingreso.guardarLogin("INTENTO", temp,"NO EXISTE",Boolean.FALSE);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        confirmarSalida();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usuarioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioTxtKeyTyped
        validaNum(evt, usuarioTxt);
    }//GEN-LAST:event_usuarioTxtKeyTyped
     public static void guardarLogin(String menu, Usuario usuario,String accion,Boolean ingreso) {
        try {
            Map campos = new HashMap();
            InetAddress localHost = InetAddress.getLocalHost();
            SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Date fecha = cal.getTime();
            LoginAuditoria log = new LoginAuditoria();
            log.setIp(localHost.getHostAddress());
            log.setMaquina(localHost.getHostName());
            log.setFechaLogin(formato1.format(fecha));
            log.setIngreso(ingreso);
            log.setDescripcion(accion);
            log.setUsuario(usuario.getUsuario());
            log.setAplicacion(menu);
            campos.put("ip",log.getIp());
            campos.put("maquina", log.getMaquina());
            campos.put("fecha_login", log.getFechaLogin());
            campos.put("descripcion", log.getDescripcion());
            campos.put("ingreso",log.getIngreso());
            campos.put("aplicacion", menu);
            campos.put("usuario", log.getUsuario());
            LoginAuditoriaDao.insertar("loginaudit", campos);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    /**
     * @param args the command line arguments
     */
     private void validaNum(java.awt.event.KeyEvent evt, JTextField field) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if (field.getText().length() >= 10) {
            evt.consume();
        }
    }
     public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Ingreso.usuario = usuario;
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
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingreso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField usuarioTxt;
    // End of variables declaration//GEN-END:variables
}
