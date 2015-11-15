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
import javax.swing.JTextField;
import logica.ProfesorDao;
import modelo.Profesor;

/**
 *
 * @author USER
 */
public class FormularioProfesor extends javax.swing.JDialog {

    public static FrmProfesor frmProfesor;
    private Integer idProfesor;
    private Profesor profesor;
    private Map campos;
    ResultSet resultado;

    public FormularioProfesor(FrmProfesor parent, boolean modal) {
        FormularioProfesor.frmProfesor = parent;
        this.setModal(modal);
        initComponents();
        this.guardarTxt.setEnabled(false);
        profesor = new Profesor();
    }

    public FormularioProfesor(FrmProfesor parent, boolean modal, Integer idProfesor) {
        FormularioProfesor.frmProfesor = parent;
        this.setModal(modal);
        this.idProfesor = idProfesor;
        initComponents();
        this.guardarTxt.setEnabled(false);
        profesor = new Profesor();
        if (idProfesor > 0) {
            try {
                ProfesorDao profesorDao = new ProfesorDao();
                resultado = profesorDao.cargaProfesor(idProfesor);
                while (resultado.next()) {
                    cedulaTxt.setText(resultado.getString("ced_profe"));
                    nombresTxt.setText(resultado.getString("nom_profe"));
                    apellidosTxt.setText(resultado.getString("apell_profe"));
                    funcionTxt.setText(resultado.getString("funcion"));
                    fechaNacimientoChooser.setDate(resultado.getDate("fnac_profe"));
                    tituloTxt.setText(resultado.getString("titulo"));
                    tipoTxt.setText(resultado.getString("tipo"));
                    profesor.setEstado(resultado.getString("estado"));
                    if (profesor.getEstado().compareTo("AC") == 0) {
                        activoRdB.setSelected(true);
                    } else {
                        inactivoRdB.setSelected(true);
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

        estadoProfesor = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombresTxt = new javax.swing.JTextField();
        apellidosTxt = new javax.swing.JTextField();
        fechaNacimientoChooser = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tipoTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        funcionTxt = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        activoRdB = new javax.swing.JRadioButton();
        inactivoRdB = new javax.swing.JRadioButton();
        guardarTxt = new javax.swing.JButton();
        cancelarTxt = new javax.swing.JButton();
        tituloTxt = new javax.swing.JTextField();
        validarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO DE REGISTRO PROFESOR");

        jLabel1.setText("Cedula");

        cedulaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaTxtKeyTyped(evt);
            }
        });

        jLabel2.setText("Nombres ");

        jLabel3.setText("Apellidos");

        jLabel4.setText("Fecha Nacimiento");

        jLabel5.setText("Titulo");

        jLabel6.setText("Tipo");

        jLabel7.setText("Funcion");

        funcionTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionTxtActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado Docente"));

        estadoProfesor.add(activoRdB);
        activoRdB.setText("Activo");
        activoRdB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activoRdBActionPerformed(evt);
            }
        });

        estadoProfesor.add(inactivoRdB);
        inactivoRdB.setText("Inactivo");
        inactivoRdB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inactivoRdBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(activoRdB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inactivoRdB)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inactivoRdB)
                    .addComponent(activoRdB))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        guardarTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarTxt.setText("Guardar");
        guardarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarTxtActionPerformed(evt);
            }
        });

        cancelarTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarTxt.setText("Cancelar");
        cancelarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarTxtActionPerformed(evt);
            }
        });

        tituloTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloTxtActionPerformed(evt);
            }
        });

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(nombresTxt))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(cedulaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(26, 26, 26)
                                    .addComponent(funcionTxt))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(21, 21, 21)
                                    .addComponent(apellidosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardarTxt)
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6))
                            .addGap(14, 14, 14)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tipoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(fechaNacimientoChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(validarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarTxt))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tituloTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))
                    .addComponent(fechaNacimientoChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(tituloTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tipoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(funcionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarTxt)
                    .addComponent(guardarTxt)
                    .addComponent(validarBtn))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarTxtActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarTxtActionPerformed

    private void funcionTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_funcionTxtActionPerformed

    private void cedulaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaTxtKeyTyped
        validaNum(evt, cedulaTxt);
    }//GEN-LAST:event_cedulaTxtKeyTyped

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        if (validaForm() == true) {
            guardarTxt.setEnabled(true);
            ocultaCampos();
            validarBtn.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_validarBtnActionPerformed

    private void activoRdBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activoRdBActionPerformed
        profesor.setEstado("AC");
    }//GEN-LAST:event_activoRdBActionPerformed
    private void ocultaCampos() {
        cedulaTxt.setEnabled(false);
        nombresTxt.setEnabled(false);
        apellidosTxt.setEnabled(false);
        funcionTxt.setEnabled(false);
        fechaNacimientoChooser.setEnabled(false);
        tituloTxt.setEnabled(false);
        tipoTxt.setEnabled(false);
        activoRdB.setEnabled(false);
        inactivoRdB.setEnabled(false);
    }
    private void inactivoRdBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inactivoRdBActionPerformed
        profesor.setEstado("DS");
    }//GEN-LAST:event_inactivoRdBActionPerformed

    private void guardarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarTxtActionPerformed
        if (validaForm() == true) {
            cargarCampos();
            Crud crud = new Crud();
            Calendar cal = Calendar.getInstance();
            if (idProfesor == 0) {
                crud.insertarM("datos_profesor", campos,Ingreso.getUsuario().getNombre());
                if (Crud.buscarUsuario(profesor.getCedula())==true) {
                Map camposA = new HashMap ();
                camposA.put("documento",profesor.getCedula());
                camposA.put("nombre",profesor.getNombreProfesor());
                camposA.put("fecha_ingreso", cal.getTime());
                camposA.put("usuario",profesor.getCedula());
                camposA.put("clave",profesor.getCedula());
                camposA.put("perfil","PROFESOR");
                camposA.put("estado","AC");
                Crud.insertarUsuario("usuario", camposA,Ingreso.usuario.getNombre());
            }
                this.dispose();
            } else {
                crud.actualizarM("datos_profesor", "id1_profe", idProfesor, campos,Ingreso.getUsuario().getNombre());
                this.dispose();
            }

        }
    }//GEN-LAST:event_guardarTxtActionPerformed

    private void tituloTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tituloTxtActionPerformed
    private Boolean validaForm() {
        boolean resul = false;
        JTextField[] texto = {cedulaTxt, nombresTxt, apellidosTxt, funcionTxt, tituloTxt, tipoTxt};
        for (JTextField texto1 : texto) {
            if (!texto1.getText().trim().isEmpty()) {
                resul = true;
            } else {
                resul = false;
                break;
            }
        }
        if (estadoProfesor.isSelected(null)) {
            resul = false;
        } else if (fechaNacimientoChooser.getDate() == null) {
            resul = false;
        }
        return resul;
    }

    private Map cargarCampos() {
        campos = new HashMap();
        seteaCampos();
        campos.put("ced_profe", profesor.getCedula());
        campos.put("nom_profe", profesor.getNombreProfesor().toUpperCase());
        campos.put("apell_profe", profesor.getApellidoProfesor().toUpperCase());
        campos.put("fnac_profe", profesor.getFechaNacimiento());
        campos.put("fec_ing", profesor.getFechaIngreso());
        campos.put("fec_sal", "0000-00-00 00:00:00");
        campos.put("titulo", profesor.getTitulo().toUpperCase());
        campos.put("cat_profe", "0");
        campos.put("magisterio", "N");
        campos.put("tipo", profesor.getTipoProfesor().toUpperCase());
        campos.put("funcion", profesor.getFuncion().toUpperCase());
        campos.put("responsable", 0);
        campos.put("login", "");
        campos.put("estado", profesor.getEstado().toUpperCase());
        return campos;
    }

    private void seteaCampos() {
        Calendar cal = Calendar.getInstance();
        profesor.setCedula(cedulaTxt.getText());
        profesor.setNombreProfesor(nombresTxt.getText());
        profesor.setApellidoProfesor(apellidosTxt.getText());
        profesor.setFechaNacimiento(fechaNacimientoChooser.getDate());
        profesor.setFechaIngreso(cal.getTime());
        profesor.setTitulo(tituloTxt.getText());
        profesor.setCategoria("0");
        profesor.setMagisterio("N");
        profesor.setTipoProfesor(tipoTxt.getText());
        profesor.setFuncion(funcionTxt.getText());
        profesor.setEstado(profesor.getEstado());
    }

    private void validaNum(java.awt.event.KeyEvent evt, JTextField field) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9'))) {
            evt.consume();
        } else if (field.getText().length() >= 10) {
            evt.consume();
        }
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
            java.util.logging.Logger.getLogger(FormularioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioProfesor dialog = new FormularioProfesor(frmProfesor, true);
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
    private javax.swing.JRadioButton activoRdB;
    private javax.swing.JTextField apellidosTxt;
    private javax.swing.JButton cancelarTxt;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.ButtonGroup estadoProfesor;
    private com.toedter.calendar.JDateChooser fechaNacimientoChooser;
    private javax.swing.JTextField funcionTxt;
    private javax.swing.JButton guardarTxt;
    private javax.swing.JRadioButton inactivoRdB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextField tipoTxt;
    private javax.swing.JTextField tituloTxt;
    private javax.swing.JButton validarBtn;
    // End of variables declaration//GEN-END:variables
}
