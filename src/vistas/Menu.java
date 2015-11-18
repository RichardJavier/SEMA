/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.ImagenFondo;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import control.Proceso;


/**
 *
 * @author Ricardo
 */
public final class Menu extends javax.swing.JFrame {

    Proceso p;

    public Menu() {
//        Ingreso lg = new Ingreso(Menu.this, true);
//        lg.setLocationRelativeTo(Menu.this);
//        lg.setVisible(true);

        initComponents();
        escritorio.setBorder(new ImagenFondo());
        this.setExtendedState(MAXIMIZED_BOTH);
        usuarioLbl.setText(Ingreso.getUsuario().getNombre());
        cerrar();
        p = new Proceso();

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
            System.out.println(e);
        }
    }
//confirmar salida

    public void confirmarSalida() {
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de cerrar la sesion", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
           Ingreso.guardarLogin("MenuAdministrador",Ingreso.getUsuario(),"CIERRE DE SESION",Boolean.TRUE);
           this.dispose();
           Ingreso ingreso = new Ingreso();
           ingreso.setVisible(true);
                   
           
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

        escritorio = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        usuarioLbl = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        gestionEscolarMenu = new javax.swing.JMenu();
        alumnoMenu = new javax.swing.JMenuItem();
        matriculaItem = new javax.swing.JMenuItem();
        actualizarItem = new javax.swing.JMenuItem();
        catalogosMenu = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        mallaMenuItem = new javax.swing.JMenuItem();
        profesorMenuItem = new javax.swing.JMenuItem();
        ejeItem = new javax.swing.JMenuItem();
        especialidadMenuItem = new javax.swing.JMenuItem();
        periodoMenuItem = new javax.swing.JMenuItem();
        paraleloItem = new javax.swing.JMenuItem();
        materiaMenuItem = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        notasMenu = new javax.swing.JMenu();
        notasPeriodoActualMenuItem = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        ingresoPromedioMenuItem = new javax.swing.JMenuItem();
        reportesMenu = new javax.swing.JMenu();
        notaFinalMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/recursos/iconPr.png")).getImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Boss.png"))); // NOI18N
        jLabel1.setText("Usuario");

        usuarioLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuarioLbl.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(usuarioLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(418, Short.MAX_VALUE))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarioLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        escritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(usuarioLbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        menuBar.setMaximumSize(new java.awt.Dimension(235, 32769));

        gestionEscolarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/process.png"))); // NOI18N
        gestionEscolarMenu.setMnemonic('f');
        gestionEscolarMenu.setText("Gestion Escolar");
        gestionEscolarMenu.setBorderPainted(true);
        gestionEscolarMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        alumnoMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alumnoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/user_add.png"))); // NOI18N
        alumnoMenu.setMnemonic('o');
        alumnoMenu.setText("Alumno");
        alumnoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoMenuActionPerformed(evt);
            }
        });
        gestionEscolarMenu.add(alumnoMenu);

        matriculaItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        matriculaItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Help book.png"))); // NOI18N
        matriculaItem.setText("Matricula");
        matriculaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculaItemActionPerformed(evt);
            }
        });
        gestionEscolarMenu.add(matriculaItem);

        actualizarItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        actualizarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Sync.png"))); // NOI18N
        actualizarItem.setText("Actualizacion de Datos");
        actualizarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarItemActionPerformed(evt);
            }
        });
        gestionEscolarMenu.add(actualizarItem);

        menuBar.add(gestionEscolarMenu);

        catalogosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Table.png"))); // NOI18N
        catalogosMenu.setText("Catalogos");
        catalogosMenu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/User group.png"))); // NOI18N
        jMenuItem5.setText("Autoridades");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        catalogosMenu.add(jMenuItem5);

        mallaMenuItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mallaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/List.png"))); // NOI18N
        mallaMenuItem.setText("Configuracion");
        mallaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mallaMenuItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(mallaMenuItem);

        profesorMenuItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        profesorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/business_user_add.png"))); // NOI18N
        profesorMenuItem.setMnemonic('a');
        profesorMenuItem.setText("Docente");
        profesorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profesorMenuItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(profesorMenuItem);

        ejeItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ejeItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Briefcase.png"))); // NOI18N
        ejeItem.setText("Eje");
        ejeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejeItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(ejeItem);

        especialidadMenuItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        especialidadMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/note_book_add.png"))); // NOI18N
        especialidadMenuItem.setText("Especialidad");
        especialidadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                especialidadMenuItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(especialidadMenuItem);

        periodoMenuItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        periodoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Wallet.png"))); // NOI18N
        periodoMenuItem.setMnemonic('s');
        periodoMenuItem.setText("Periodo");
        periodoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodoMenuItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(periodoMenuItem);

        paraleloItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        paraleloItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Door.png"))); // NOI18N
        paraleloItem.setText("Paralelo");
        paraleloItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paraleloItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(paraleloItem);

        materiaMenuItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        materiaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/note_book_add.png"))); // NOI18N
        materiaMenuItem.setText("Materia");
        materiaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiaMenuItemActionPerformed(evt);
            }
        });
        catalogosMenu.add(materiaMenuItem);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/notes.png"))); // NOI18N
        jMenuItem2.setText("Malla");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        catalogosMenu.add(jMenuItem2);

        menuBar.add(catalogosMenu);

        notasMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/notes.png"))); // NOI18N
        notasMenu.setMnemonic('h');
        notasMenu.setText("Notas");
        notasMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        notasPeriodoActualMenuItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        notasPeriodoActualMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Notes_1.png"))); // NOI18N
        notasPeriodoActualMenuItem.setMnemonic('c');
        notasPeriodoActualMenuItem.setText("Notas Grupo Periodo Actual");
        notasPeriodoActualMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notasPeriodoActualMenuItemActionPerformed(evt);
            }
        });
        notasMenu.add(notasPeriodoActualMenuItem);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Notes_1.png"))); // NOI18N
        jMenuItem4.setText("Notas Materia Periodo Actual");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        notasMenu.add(jMenuItem4);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Notes_1.png"))); // NOI18N
        jMenuItem3.setText("Notas Recalificacion");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        notasMenu.add(jMenuItem3);

        ingresoPromedioMenuItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ingresoPromedioMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Address book.png"))); // NOI18N
        ingresoPromedioMenuItem.setMnemonic('a');
        ingresoPromedioMenuItem.setText("Ingreso Promedio");
        ingresoPromedioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresoPromedioMenuItemActionPerformed(evt);
            }
        });
        notasMenu.add(ingresoPromedioMenuItem);

        menuBar.add(notasMenu);

        reportesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Report.png"))); // NOI18N
        reportesMenu.setText("Reportes");
        reportesMenu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        notaFinalMenuItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        notaFinalMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/3d bar chart.png"))); // NOI18N
        notaFinalMenuItem.setText("Nota Final");
        notaFinalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaFinalMenuItemActionPerformed(evt);
            }
        });
        reportesMenu.add(notaFinalMenuItem);

        menuBar.add(reportesMenu);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Boss.png"))); // NOI18N
        jMenu1.setText("Usuarios");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/User group.png"))); // NOI18N
        jMenuItem1.setText("Administrar Usuarios");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profesorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profesorMenuItemActionPerformed
        FrmProfesor pr = new FrmProfesor();
        escritorio.add(pr);
        pr.show();


    }//GEN-LAST:event_profesorMenuItemActionPerformed

    private void especialidadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_especialidadMenuItemActionPerformed
        FrmEspecialidad es = new FrmEspecialidad();
        escritorio.add(es);
        es.show();
    }//GEN-LAST:event_especialidadMenuItemActionPerformed

    private void periodoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodoMenuItemActionPerformed
        FrmPeriodo fp = new FrmPeriodo();
        escritorio.add(fp);
        fp.show();
    }//GEN-LAST:event_periodoMenuItemActionPerformed

    private void materiaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiaMenuItemActionPerformed
        FrmMateria fm = new FrmMateria();
        escritorio.add(fm);
        fm.show();
    }//GEN-LAST:event_materiaMenuItemActionPerformed

    private void alumnoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoMenuActionPerformed

        FrmAlumno fa = new FrmAlumno();
        escritorio.add(fa);
        fa.show();

    }//GEN-LAST:event_alumnoMenuActionPerformed

    private void notasPeriodoActualMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notasPeriodoActualMenuItemActionPerformed
        FrmNotas fn = new FrmNotas();
        escritorio.add(fn);
        fn.show();

    }//GEN-LAST:event_notasPeriodoActualMenuItemActionPerformed

    private void mallaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mallaMenuItemActionPerformed
        FrmConfiguracion fm = new FrmConfiguracion();
        escritorio.add(fm);
        fm.show();
    }//GEN-LAST:event_mallaMenuItemActionPerformed

    private void ingresoPromedioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresoPromedioMenuItemActionPerformed
        FrmResumen fr = new FrmResumen();
        escritorio.add(fr);
        fr.show();
    }//GEN-LAST:event_ingresoPromedioMenuItemActionPerformed

    private void notaFinalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaFinalMenuItemActionPerformed
        ReporteAlumno alumno = new ReporteAlumno();
        escritorio.add(alumno);
        alumno.show();

    }//GEN-LAST:event_notaFinalMenuItemActionPerformed

    private void matriculaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculaItemActionPerformed
        FrmMatricu ma = new FrmMatricu();
        escritorio.add(ma);
        ma.show();
    }//GEN-LAST:event_matriculaItemActionPerformed

    private void ejeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejeItemActionPerformed
      FrmEje eje=new FrmEje();
      escritorio.add(eje);
      eje.show();
    }//GEN-LAST:event_ejeItemActionPerformed

    private void paraleloItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paraleloItemActionPerformed
        FrmParalelo fp =new FrmParalelo();
        escritorio.add(fp);
        fp.setVisible(true);
    }//GEN-LAST:event_paraleloItemActionPerformed

    private void actualizarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarItemActionPerformed
        ActualizacionDatos ad = new ActualizacionDatos();
        escritorio.add(ad);
        ad.setVisible(true);
    }//GEN-LAST:event_actualizarItemActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        FrmAutoridad fa = new FrmAutoridad();
        escritorio.add(fa);
        fa.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       FrmUsuario fu = new FrmUsuario();
       escritorio.add(fu);
       fu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       FrmMalla fm = new FrmMalla();
       escritorio.add(fm);
       fm.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FrmNotas2 notas2 = new FrmNotas2();
        escritorio.add(notas2);
        notas2.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        FrmNotasProfesor fnp = new FrmNotasProfesor();
        escritorio.add(fnp);
        fnp.setVisible(true);
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
    private javax.swing.JMenuItem actualizarItem;
    private javax.swing.JMenuItem alumnoMenu;
    private javax.swing.JMenu catalogosMenu;
    private javax.swing.JMenuItem ejeItem;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem especialidadMenuItem;
    private javax.swing.JMenu gestionEscolarMenu;
    private javax.swing.JMenuItem ingresoPromedioMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem mallaMenuItem;
    private javax.swing.JMenuItem materiaMenuItem;
    private javax.swing.JMenuItem matriculaItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem notaFinalMenuItem;
    private javax.swing.JMenu notasMenu;
    private javax.swing.JMenuItem notasPeriodoActualMenuItem;
    private javax.swing.JMenuItem paraleloItem;
    private javax.swing.JMenuItem periodoMenuItem;
    private javax.swing.JMenuItem profesorMenuItem;
    private javax.swing.JMenu reportesMenu;
    private javax.swing.JLabel usuarioLbl;
    // End of variables declaration//GEN-END:variables
}
