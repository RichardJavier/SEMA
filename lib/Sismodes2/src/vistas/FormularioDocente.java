/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosProfesorControl;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashAttributeSet;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import metodos.VerificadorCedula;

/**
 *
 * @author Ricardo
 */
public class FormularioDocente extends javax.swing.JDialog {

    private Double pkProfesor;
    private DatosProfesorControl profesorControl;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    public static FrmProfesor profesor;
    VerificadorCedula vc;
    public FormularioDocente(FrmProfesor parent, boolean modal) {
        this.profesor = parent;
        this.setModal(modal);
        initComponents();
        borrar.setEnabled(false);
        this.setLocationRelativeTo(null);
        profesorControl = new DatosProfesorControl();
        vc= new VerificadorCedula();
    }

    public FormularioDocente(FrmProfesor parent, boolean modal, Double pkProfesor) throws SQLException, ParseException,NullPointerException {
        this.profesor = parent;
        this.setModal(modal);
        initComponents();
        this.setLocationRelativeTo(null);
        borrar.setEnabled(false);
        profesorControl = new DatosProfesorControl();
        this.pkProfesor = pkProfesor;
        vc= new VerificadorCedula();
        ResultSet rs;
        if (pkProfesor > 0) {
            rs = profesorControl.consulta(pkProfesor);
            cmbTipo.removeAllItems();
            cmbFuncion.removeAllItems();

            while (rs.next()) {
                String tip ="";
                String fun=" ";
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fnac_profe"));
                fechaIngreso = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fec_ing"));
                cedulaTxt.setText(rs.getString("ced_profe"));
                nombreTxt.setText(rs.getString("nom_profe"));
                apeTxt.setText(rs.getString("apell_profe"));
                nacimientoChooser.setDate(fechaNacimiento);
                ingresoChooser.setDate(fechaIngreso);
                tituloTxt.setText(rs.getString("titulo"));
                tip = rs.getString("tipo");
                fun = rs.getString("funcion");
                if (tip.compareTo(" ")==0) {
                    cmbTipo.setSelectedIndex(0);
                }else {
                    cmbTipo.addItem(tip);
                }
                if(fun.compareTo(" ")==0){
                    cmbFuncion.setSelectedIndex(0);
                }else{
                    cmbFuncion.addItem(fun);
                }
                break;
            }
            cc.desconectar();
            borrar.setEnabled(true);
            cmbFuncion.setEnabled(false);
            cmbTipo.setEnabled(false);
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

        jPanel1 = new javax.swing.JPanel();
        cedulaTxt = new javax.swing.JTextField();
        nombreTxt = new javax.swing.JTextField();
        apeTxt = new javax.swing.JTextField();
        nacimientoChooser = new com.toedter.calendar.JDateChooser();
        ingresoChooser = new com.toedter.calendar.JDateChooser();
        tituloTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cmbFuncion = new javax.swing.JComboBox();
        guardar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();

        setTitle("Formulario Profesores");

        cedulaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cedulaTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulaTxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaTxtKeyTyped(evt);
            }
        });

        nombreTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreTxtKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cedula");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombres");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Apellidos");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Fecha Nacimiento");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Ingreso");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Titulo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tipo");

        cmbTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "DOCENTE", "ADMIISTRATIVO" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Funcion");

        cmbFuncion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbFuncion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "PROFESOR", "DOCENTE", "COORDINADOR ACADEMICO", "SECRETARIA", "MANTENIMIENTO LABORATORIO" }));
        cmbFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFuncionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(apeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nacimientoChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ingresoChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloTxt)
                    .addComponent(cmbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbFuncion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(24, 24, 24)
                        .addComponent(nacimientoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingresoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        guardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        borrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(guardar)
                        .addGap(18, 18, 18)
                        .addComponent(borrar)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFuncionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFuncionActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        Map campos = new HashMap();
        Map campos1 = new HashMap();

        try {
            if (cedulaTxt.getText().length()==0) {
                JOptionPane.showMessageDialog(this, "El campo cedula esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                cedulaTxt.requestFocus();
                return;
            }
            if (nombreTxt.getText().length()==0) {
                JOptionPane.showMessageDialog(this, "El campo nombre esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                nombreTxt.requestFocus();
                return;
            }
            if (apeTxt.getText().length()==0) {
                JOptionPane.showMessageDialog(this, "El campo cedula esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                apeTxt.requestFocus();
                return;
            }
            if (nacimientoChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "La fecha de nacimiento esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                nacimientoChooser.requestFocus();
                return;
            }
            if (ingresoChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "la fecha de nacimiento esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                ingresoChooser.requestFocus();
                return;
            }
            
            campos.put("ced_profe", cedulaTxt.getText().toUpperCase());
            campos.put("nom_profe", nombreTxt.getText().toUpperCase());
            campos.put("apell_profe", apeTxt.getText().toUpperCase());
            campos.put("fnac_profe", nacimientoChooser.getCalendar().getTime());
            campos.put("fec_ing", ingresoChooser.getCalendar().getTime());
            campos.put("titulo", tituloTxt.getText().toUpperCase());
            campos.put("tipo", cmbTipo.getSelectedItem().toString());
            campos.put("funcion", cmbFuncion.getSelectedItem().toString());
           
            /// para la modificacion de los campos excepto los combos
            campos1.put("ced_profe", cedulaTxt.getText().toUpperCase());
            campos1.put("nom_profe", nombreTxt.getText().toUpperCase());
            campos1.put("apell_profe", apeTxt.getText().toUpperCase());
            campos1.put("fnac_profe", nacimientoChooser.getCalendar().getTime());
            campos1.put("titulo", tituloTxt.getText().toUpperCase());
            campos1.put("fec_ing", ingresoChooser.getCalendar().getTime());
            
            if (pkProfesor == 0.0) {
                if (cmbTipo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "El campo tipo  esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                    cmbTipo.requestFocus();
                    return;
                }
                if (cmbFuncion.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "La campo funcion  esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                    cmbFuncion.requestFocus();
                    return;
                }
                  String numero = cedulaTxt.getText();
                  if (vc.verificaCedula(numero)) {
                     profesorControl.insertar("datos_profesor", campos);
                      this.dispose();
                }
                 else {
                JOptionPane.showMessageDialog(null, "Cedula incorrecta" );
                this.cedulaTxt.requestFocus();
                this.cedulaTxt.setText(null);
                return;
            }
            } else {
                profesorControl.actualizar("datos_profesor", "id1_profe", pkProfesor, campos1);
                this.dispose();
            }

        } catch (Exception e) {
            Logger.getLogger(FormularioDocente.class.getName()).log(Level.SEVERE, null, e);
        }
      
    }//GEN-LAST:event_guardarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Esta seguro de borrar el alumno", "Advertencia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            profesorControl.borrar(pkProfesor);
            this.dispose();

        }
    }//GEN-LAST:event_borrarActionPerformed

    private void nombreTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreTxtKeyReleased
    }//GEN-LAST:event_nombreTxtKeyReleased

    private void cedulaTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaTxtKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          String numero = cedulaTxt.getText();
            if (vc.verificaCedula(numero)) { 
                nombreTxt.requestFocus();
            }else {
            JOptionPane.showMessageDialog(null, "Cedula incorrecta" );
                this.cedulaTxt.requestFocus();
                this.cedulaTxt.setText(null);
            }

         
        }

    }//GEN-LAST:event_cedulaTxtKeyPressed

    private void cedulaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaTxtKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
        if (cedulaTxt.getText().length() == 11) {
            JOptionPane.showMessageDialog(null, "Solo se perminten 10 cacarteres", "advertencia", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_cedulaTxtKeyTyped

    private void cedulaTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaTxtKeyReleased
    }//GEN-LAST:event_cedulaTxtKeyReleased

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
            java.util.logging.Logger.getLogger(FormularioDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioDocente dialog = new FormularioDocente(profesor, true);
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
    private javax.swing.JTextField apeTxt;
    private javax.swing.JButton borrar;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JComboBox cmbFuncion;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JButton guardar;
    private com.toedter.calendar.JDateChooser ingresoChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser nacimientoChooser;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField tituloTxt;
    // End of variables declaration//GEN-END:variables
}
