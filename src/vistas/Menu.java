/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import control.Proceso;
import modelo.Matricula;

/**
 *
 * @author Ricardo
 */
public class Menu extends javax.swing.JFrame {

    Proceso p;

    public Menu() {
        Login lg = new Login(Menu.this, true);
        lg.setLocationRelativeTo(Menu.this);
        lg.setVisible(true);

        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        cerrar();
        p = new Proceso();

    }

    public void cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    confirmarSalida();

                }
            });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//confirmar salida

    public void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de cerrar la ventana", "Advertencia", JOptionPane.YES_NO_OPTION);
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

        jCheckBox1 = new javax.swing.JCheckBox();
        escritorio = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        alumnoMenu = new javax.swing.JMenuItem();
        cursoMenu = new javax.swing.JMenuItem();
        especialidadMenu = new javax.swing.JMenuItem();
        profesorMenu = new javax.swing.JMenuItem();
        materiaMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/Icono.png")).getImage());

        menuBar.setMaximumSize(new java.awt.Dimension(235, 32769));

        fileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/process.png"))); // NOI18N
        fileMenu.setMnemonic('f');
        fileMenu.setText("Gestion Escolar");
        fileMenu.setBorderPainted(true);
        fileMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        alumnoMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alumnoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/user_add.png"))); // NOI18N
        alumnoMenu.setMnemonic('o');
        alumnoMenu.setText("Alumno");
        alumnoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoMenuActionPerformed(evt);
            }
        });
        fileMenu.add(alumnoMenu);

        cursoMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cursoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Wallet.png"))); // NOI18N
        cursoMenu.setMnemonic('s');
        cursoMenu.setText("Periodo");
        cursoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cursoMenuActionPerformed(evt);
            }
        });
        fileMenu.add(cursoMenu);

        especialidadMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        especialidadMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/note_book_add.png"))); // NOI18N
        especialidadMenu.setText("Especialidad");
        especialidadMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                especialidadMenuActionPerformed(evt);
            }
        });
        fileMenu.add(especialidadMenu);

        profesorMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        profesorMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/business_user_add.png"))); // NOI18N
        profesorMenu.setMnemonic('a');
        profesorMenu.setText("Docente");
        profesorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profesorMenuActionPerformed(evt);
            }
        });
        fileMenu.add(profesorMenu);

        materiaMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        materiaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/note_book_add.png"))); // NOI18N
        materiaMenu.setText("Materia");
        materiaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiaMenuActionPerformed(evt);
            }
        });
        fileMenu.add(materiaMenu);

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/List.png"))); // NOI18N
        jMenuItem1.setText("Malla");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Help book.png"))); // NOI18N
        jMenuItem4.setText("Matricula");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem4);

        menuBar.add(fileMenu);

        editMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        editMenu.setMnemonic('e');
        editMenu.setText("Busqueda");
        editMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Alumno");
        editMenu.add(cutMenuItem);

        menuBar.add(editMenu);

        helpMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/notes.png"))); // NOI18N
        helpMenu.setMnemonic('h');
        helpMenu.setText("Notas");
        helpMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Notas Periodo Actual");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Ingreso Promedio");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuItem2.setText("Ingreso de Notas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem2);

        menuBar.add(helpMenu);

        jMenu1.setText("Reportes");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setText("Nota Final");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profesorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profesorMenuActionPerformed
//        FrmProfesor pr = new FrmProfesor();
//        escritorio.add(pr);
//        pr.show();


    }//GEN-LAST:event_profesorMenuActionPerformed

    private void especialidadMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_especialidadMenuActionPerformed
        FrmEspecialidad es = new FrmEspecialidad();
        escritorio.add(es);
        es.show();
    }//GEN-LAST:event_especialidadMenuActionPerformed

    private void cursoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cursoMenuActionPerformed
        FrmPeriodo fp = new FrmPeriodo();
        escritorio.add(fp);
        fp.show();
    }//GEN-LAST:event_cursoMenuActionPerformed

    private void materiaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiaMenuActionPerformed
        FrmMateria fm = new FrmMateria();
        escritorio.add(fm);
        fm.show();
    }//GEN-LAST:event_materiaMenuActionPerformed

    private void alumnoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoMenuActionPerformed

        FrmAlumno fa = new FrmAlumno();
        escritorio.add(fa);
        fa.show();

    }//GEN-LAST:event_alumnoMenuActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        FrmNotas fn = new FrmNotas();
        escritorio.add(fn);
        fn.show();

    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrmMalla fm = new FrmMalla();
        escritorio.add(fm);
        fm.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
//        FrmResumen fr = new FrmResumen();
//        escritorio.add(fr);
//        fr.show();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//        ReporteNotaFinal notaFinal  = new ReporteNotaFinal();
//        escritorio.add(notaFinal);
//        notaFinal.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        FrmMatricula ma = new FrmMatricula();
        escritorio.add(ma);
        ma.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem alumnoMenu;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cursoMenu;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenu editMenu;
    public javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem especialidadMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem materiaMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem profesorMenu;
    // End of variables declaration//GEN-END:variables
}
