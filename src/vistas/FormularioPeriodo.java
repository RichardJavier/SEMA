/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import control.Crud;
import control.EjecutarScript;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logica.PeriodoDao;
import modelo.Periodo;

public class FormularioPeriodo extends javax.swing.JDialog {

    public static FrmPeriodo periodo;
    Conexion cc;
    Connection cn;
    private Periodo periodo1;
    Crud crud;
    private Integer matricula, malla;
    private Integer idPeriodo;
    ResultSet resultSet;
    PeriodoDao periodoDao;

    public FormularioPeriodo(FrmPeriodo parent, boolean modal) throws SQLException {
        FormularioPeriodo.periodo = parent;
        this.setModal(modal);
        initComponents();
        periodo1 = new Periodo();
        crud = new Crud();
        cargarCodigo();
        codigoPeriodoTxt.setEnabled(false);
        this.setLocationRelativeTo(null);

    }

    public FormularioPeriodo(FrmPeriodo parent, boolean modal, Integer idPeriodo) throws SQLException {
        FormularioPeriodo.periodo = parent;
        this.setModal(modal);
        initComponents();
        this.idPeriodo = idPeriodo;
        periodo1 = new Periodo();
        crud = new Crud();
        periodo1 = new Periodo();
        cargarCodigo();
        codigoPeriodoTxt.setEnabled(false);
        periodoDao = new PeriodoDao();
        this.setLocationRelativeTo(null);
        if (idPeriodo > 0) {
            resultSet = periodoDao.consulta(idPeriodo);

            try {
                while (resultSet.next()) {
                    periodo1.setCodigoPeriodo(resultSet.getString("id_periodo"));
                    periodo1.setNombrePeriodo(resultSet.getString("periodo"));
                    periodo1.setMatricula(Integer.parseInt(resultSet.getString("matricula")));
                    periodo1.setFechaFinalizacion(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fecha_fin")));
                    periodo1.setMalla(Integer.parseInt(resultSet.getString("nueva_malla")));
                }
                setearCampos();
            } catch (ParseException ex) {
                Logger.getLogger(FormularioPeriodo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void setearCampos() {
        codigoPeriodoTxt.setText(periodo1.getCodigoPeriodo());
        nombrePeriodoTxt.setText(periodo1.getNombrePeriodo());
        if (periodo1.getMatricula() == 1) {
            activaMatriculaRdb.setSelected(true);
            matricula = 1;
        } else {
            desactivaMatriculaRdb.setSelected(true);
            matricula = 0;
        }
        if (periodo1.getMalla() == 1) {
            nuevaMallaSiRdb.setSelected(true);
            malla = 1;
        } else {
            nuevaMallaNoRdb.setSelected(true);
            malla = 0;
        }
        fechaFinalChoosser.setDate(periodo1.getFechaFinalizacion());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        matriculaGroup = new javax.swing.ButtonGroup();
        mallaGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        activaMatriculaRdb = new javax.swing.JRadioButton();
        desactivaMatriculaRdb = new javax.swing.JRadioButton();
        fechaFinalChoosser = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        nombrePeriodoTxt = new javax.swing.JTextField();
        codigoPeriodoTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        nuevaMallaSiRdb = new javax.swing.JRadioButton();
        nuevaMallaNoRdb = new javax.swing.JRadioButton();
        guardarBtn = new javax.swing.JButton();
        CancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO DE REGISTRO DE PERIODO");

        jLabel1.setText("Codigo Periodo");

        jLabel2.setText("Nombre Periodo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones Matricula"));

        matriculaGroup.add(activaMatriculaRdb);
        activaMatriculaRdb.setText("Acitvar ");
        activaMatriculaRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activaMatriculaRdbActionPerformed(evt);
            }
        });

        matriculaGroup.add(desactivaMatriculaRdb);
        desactivaMatriculaRdb.setText("Desactivar");
        desactivaMatriculaRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desactivaMatriculaRdbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(activaMatriculaRdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desactivaMatriculaRdb)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activaMatriculaRdb)
                    .addComponent(desactivaMatriculaRdb)))
        );

        fechaFinalChoosser.setMinimumSize(new java.awt.Dimension(28, 20));

        jLabel3.setText("Fecha de Finalizacion");

        codigoPeriodoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoPeriodoTxtActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Malla "));

        mallaGroup.add(nuevaMallaSiRdb);
        nuevaMallaSiRdb.setText("SI");
        nuevaMallaSiRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaMallaSiRdbActionPerformed(evt);
            }
        });

        mallaGroup.add(nuevaMallaNoRdb);
        nuevaMallaNoRdb.setText("NO");
        nuevaMallaNoRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaMallaNoRdbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(nuevaMallaSiRdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nuevaMallaNoRdb)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevaMallaSiRdb)
                    .addComponent(nuevaMallaNoRdb))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        CancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        CancelarBtn.setText("Cancelar");
        CancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(guardarBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CancelarBtn))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(fechaFinalChoosser, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(22, 22, 22)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombrePeriodoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(codigoPeriodoTxt))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(codigoPeriodoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombrePeriodoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3))
                    .addComponent(fechaFinalChoosser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(guardarBtn)
                    .addComponent(CancelarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoPeriodoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoPeriodoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoPeriodoTxtActionPerformed

    private void CancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        Map campos = new HashMap();
        if (validaForm()) {
            try {
                cambiar();
                cargarDatos();
                campos.put("id_periodo", periodo1.getCodigoPeriodo());
                campos.put("periodo", periodo1.getNombrePeriodo());
                campos.put("matricula", periodo1.getMatricula());
                campos.put("ciclo", periodo1.getCiclo());
                campos.put("fecha_fin", periodo1.getFechaFinalizacion());
                campos.put("nueva_malla", periodo1.getMalla());
                if (idPeriodo == 0) {
                    crud.insertarM("periodo_semestre", campos,Login.getUsuario().getNombre());
                    EjecutarScript jr = new EjecutarScript();
                    jr.crearTabla();
                    cambiarNombre();
                    this.dispose();

                } else {
                    crud.actualizar("periodo_semestre", "id1_periodo", idPeriodo, campos,Login.getUsuario().getNombre());
                    this.dispose();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al ingresar", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void activaMatriculaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activaMatriculaRdbActionPerformed
        matricula = 1;
    }//GEN-LAST:event_activaMatriculaRdbActionPerformed

    private void desactivaMatriculaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desactivaMatriculaRdbActionPerformed
        matricula = 0;
    }//GEN-LAST:event_desactivaMatriculaRdbActionPerformed

    private void nuevaMallaSiRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaMallaSiRdbActionPerformed
        malla = 1;
    }//GEN-LAST:event_nuevaMallaSiRdbActionPerformed

    private void nuevaMallaNoRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaMallaNoRdbActionPerformed
        malla = 0;
    }//GEN-LAST:event_nuevaMallaNoRdbActionPerformed
    private void cargarDatos() {
        if (validaForm()) {
            periodo1.setCodigoPeriodo(codigoPeriodoTxt.getText());
            periodo1.setNombrePeriodo(nombrePeriodoTxt.getText().toUpperCase());
            periodo1.setMatricula(matricula);
            periodo1.setCiclo(0);
            periodo1.setFechaFinalizacion(fechaFinalChoosser.getDate());
            periodo1.setMalla(malla);

        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarCodigo() throws SQLException {
        cc = Conexion.getInstance();
        cn = cc.Conectar();
        String sql = "SELECT * FROM periodo_semestre";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String fila[] = new String[6];
        int index = 2;
        while (rs.next()) {
            fila[3] = String.valueOf("PE" + index);
            index++;
            codigoPeriodoTxt.setText(fila[3]);
            periodo1.setIdPeriodo(Integer.parseInt(rs.getString("id1_periodo")));
        }

    }

    private boolean validaForm() {
        boolean resultado = true;
        if (nombrePeriodoTxt.getText().trim().length() == 0) {
            resultado = false;
        } else if (matriculaGroup.isSelected(null)) {
            resultado = false;
        } else if (fechaFinalChoosser.getDate() == null) {
            resultado = false;
        } else if (mallaGroup.isSelected(null)) {
            resultado = false;
        }

        return resultado;
    }

    private void cambiar() {
        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql4 = "UPDATE `sismodes_1`.`periodo_semestre` SET `matricula`='0' WHERE `id1_periodo`=" + periodo1.getIdPeriodo() + "";
            Statement st = cn.createStatement();
            st.executeUpdate(sql4);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar " + e);
        }
    }

    private void cambiarNombre() {

        try {
            cc = Conexion.getInstance();
            cn = cc.Conectar();
            String sql1 = "ALTER TABLE `sismodes_1`.`nota` RENAME TO nota_" + codigoPeriodoTxt.getText() + "";
            Statement st = cn.createStatement();
            st.executeUpdate(sql1);
        } catch (SQLException e) {
            System.out.println("error al ejecutar el cambio de nombre" + e);
            JOptionPane.showMessageDialog(null,"Error al ejecutar script de tabla","Error",JOptionPane.ERROR_MESSAGE);

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
            java.util.logging.Logger.getLogger(FormularioPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormularioPeriodo dialog = new FormularioPeriodo(periodo, true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormularioPeriodo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarBtn;
    private javax.swing.JRadioButton activaMatriculaRdb;
    private javax.swing.JTextField codigoPeriodoTxt;
    private javax.swing.JRadioButton desactivaMatriculaRdb;
    private com.toedter.calendar.JDateChooser fechaFinalChoosser;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.ButtonGroup mallaGroup;
    private javax.swing.ButtonGroup matriculaGroup;
    private javax.swing.JTextField nombrePeriodoTxt;
    private javax.swing.JRadioButton nuevaMallaNoRdb;
    private javax.swing.JRadioButton nuevaMallaSiRdb;
    // End of variables declaration//GEN-END:variables
}
