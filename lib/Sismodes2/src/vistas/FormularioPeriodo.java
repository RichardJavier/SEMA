package vistas;

import conectar.Conexion;
import controles.DatosPeriodoSemestreControl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metodos.EjecutarScript;
import metodos.Proceso;

public class FormularioPeriodo extends javax.swing.JDialog {

    private Double pkPeriodo;
    private DatosPeriodoSemestreControl semestreControl;
    private Date fechaFinal;
    private Integer matricula1;
    private Integer malla1;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    public static FrmPeriodo periodo;
    private String codigo;

    public FormularioPeriodo(FrmPeriodo parent, boolean modal) {
        FormularioPeriodo.periodo = parent;
        this.setModal(modal);
        initComponents();
        codigoPeridotxt.setEnabled(false);
        btbBorrar.setEnabled(false);
    
        try {
            cargarCodigo();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioPeriodo.class.getName()).log(Level.SEVERE, null, ex);
        }
        semestreControl = new DatosPeriodoSemestreControl();

    }

    public FormularioPeriodo(FrmPeriodo parent, boolean modal, Double pkPeriodo) throws SQLException, ParseException {
        FormularioPeriodo.periodo = parent;
        this.setModal(modal);
        initComponents();
        codigoPeridotxt.setEnabled(false);
        btbBorrar.setEnabled(false);
        semestreControl = new DatosPeriodoSemestreControl();
        this.pkPeriodo = pkPeriodo;
       
        cargarCodigo();
        ResultSet rs;
        if (pkPeriodo > 0) {
            rs = semestreControl.consultaOrdenada(pkPeriodo);
        
            while (rs.next()) {
                int matri = 0;
                int malla = 0;
                fechaFinal = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fecha_fin").toString());
                codigoPeridotxt.setText(rs.getString("id_periodo"));
                periodotxt.setText(rs.getString("periodo"));
                
                matri = Integer.parseInt(rs.getString("matricula"));
                malla = Integer.parseInt(rs.getString("nueva_malla"));
                if (matri == 1) {
                    rdbMsi.setSelected(true);
                    matricula1 = 1;
                } else if (matri == 0) {
                    rdbMno.setSelected(true);
                    matricula1 =0;
                }

                if (malla == 1) {
                    rdbMEsi.setSelected(true);
                    malla1=1;
                } else if (malla == 0) {
                    rdbMEno.setSelected(true);
                    malla1 = 0;
                }
                ffinalChooser.setDate(fechaFinal);
                break;
            }
            cc.desconectar();
            btbBorrar.setEnabled(true);
         
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

        matricula = new javax.swing.ButtonGroup();
        malla = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codigoPeridotxt = new javax.swing.JTextField();
        periodotxt = new javax.swing.JTextField();
        ffinalChooser = new com.toedter.calendar.JDateChooser();
        panelPeriodo = new javax.swing.JPanel();
        rdbMsi = new javax.swing.JRadioButton();
        rdbMno = new javax.swing.JRadioButton();
        panelMalla = new javax.swing.JPanel();
        rdbMEsi = new javax.swing.JRadioButton();
        rdbMEno = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        btbGuardar = new javax.swing.JButton();
        btbBorrar = new javax.swing.JButton();
        mensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FORMULARIO DE PERIODO ESCOLAR");
        setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Codigo Periodo");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Periodo ");

        codigoPeridotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoPeridotxtActionPerformed(evt);
            }
        });

        periodotxt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        ffinalChooser.setMinimumSize(new java.awt.Dimension(28, 20));

        panelPeriodo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Activar Matricula ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        matricula.add(rdbMsi);
        rdbMsi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbMsi.setText("SI");
        rdbMsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMsiActionPerformed(evt);
            }
        });

        matricula.add(rdbMno);
        rdbMno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbMno.setText("NO");
        rdbMno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPeriodoLayout = new javax.swing.GroupLayout(panelPeriodo);
        panelPeriodo.setLayout(panelPeriodoLayout);
        panelPeriodoLayout.setHorizontalGroup(
            panelPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPeriodoLayout.createSequentialGroup()
                .addContainerGap(193, Short.MAX_VALUE)
                .addComponent(rdbMsi)
                .addGap(75, 75, 75)
                .addComponent(rdbMno)
                .addContainerGap())
        );
        panelPeriodoLayout.setVerticalGroup(
            panelPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdbMsi)
                .addComponent(rdbMno))
        );

        panelMalla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva Malla Escolar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelMalla.setPreferredSize(new java.awt.Dimension(368, 54));

        malla.add(rdbMEsi);
        rdbMEsi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbMEsi.setText("SI");
        rdbMEsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMEsiActionPerformed(evt);
            }
        });

        malla.add(rdbMEno);
        rdbMEno.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbMEno.setText("NO");
        rdbMEno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMEnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMallaLayout = new javax.swing.GroupLayout(panelMalla);
        panelMalla.setLayout(panelMallaLayout);
        panelMallaLayout.setHorizontalGroup(
            panelMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMallaLayout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(rdbMEsi)
                .addGap(71, 71, 71)
                .addComponent(rdbMEno)
                .addContainerGap())
        );
        panelMallaLayout.setVerticalGroup(
            panelMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMallaLayout.createSequentialGroup()
                .addGroup(panelMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbMEno)
                    .addComponent(rdbMEsi))
                .addGap(14, 14, 14))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Finalizacion ");

        btbGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btbGuardar.setText("Guardar");
        btbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbGuardarActionPerformed(evt);
            }
        });

        btbBorrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btbBorrar.setText("Borrar");
        btbBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbBorrarActionPerformed(evt);
            }
        });

        mensaje.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(btbGuardar)
                        .addGap(60, 60, 60)
                        .addComponent(btbBorrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelMalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ffinalChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panelPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(72, 72, 72)
                                        .addComponent(codigoPeridotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(115, 115, 115)
                                        .addComponent(periodotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(33, 33, 33)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1))
                    .addComponent(codigoPeridotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2))
                    .addComponent(periodotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(panelPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffinalChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelMalla, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btbGuardar)
                    .addComponent(btbBorrar))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbMsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMsiActionPerformed
        matricula1 = 1;
    }//GEN-LAST:event_rdbMsiActionPerformed

    private void rdbMnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMnoActionPerformed
        matricula1 = 0;
    }//GEN-LAST:event_rdbMnoActionPerformed

    private void rdbMEsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMEsiActionPerformed
        malla1 = 1;
    }//GEN-LAST:event_rdbMEsiActionPerformed

    private void rdbMEnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMEnoActionPerformed
        malla1 = 0;
    }//GEN-LAST:event_rdbMEnoActionPerformed

    private void codigoPeridotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoPeridotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoPeridotxtActionPerformed

    private void btbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbGuardarActionPerformed

        Map campos = new HashMap();
        Map campos1 = new HashMap();
        try {
            if (periodotxt.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Campo Periodo esta Vacio ", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (rdbMsi.isSelected() == false && rdbMno.isSelected() == false) {
                JOptionPane.showMessageDialog(this, "Deve selecionar una accion para Matricula ", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (ffinalChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "La fecha de Final esta vacio ", "error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            if (rdbMEno.isSelected() == false && rdbMEsi.isSelected() == false) {
                JOptionPane.showMessageDialog(this, "Deve selecionar una accion para Malla ", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            campos.put("id_periodo", codigoPeridotxt.getText());
            campos.put("periodo", periodotxt.getText());
            campos.put("matricula", matricula1);
            campos.put("fecha_fin", ffinalChooser.getCalendar().getTime());
            campos.put("nueva_malla", malla1);
            //modificacion 

            if (pkPeriodo == 0.0) {
                cambiar();
                semestreControl.insertar("periodo_semestre", campos);
                EjecutarScript jr = new EjecutarScript();
                jr.crearTabla();
                cambiarNombre();
                verDialog();
                this.dispose();

            } else {
                String c=codigoPeridotxt.getText();
                String p=periodotxt.getText();
                Date d = ffinalChooser.getCalendar().getTime();
                int  m = matricula1;
                int t = malla1;
                campos1.put("id_periodo", c);
                campos1.put("periodo", p);
                campos1.put("matricula", m);
                campos.put("ciclo",0);
                campos1.put("fecha_fin", d);
                campos1.put("nueva_malla", t);
                semestreControl.actualizar("periodo_semestre", "id1_periodo", pkPeriodo, campos1);
                this.dispose();
            }

        } catch (Exception e) {
            Logger.getLogger(FormularioPeriodo.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btbGuardarActionPerformed

    private void btbBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbBorrarActionPerformed
        if (JOptionPane.showConfirmDialog(this, "¿Esta seguro de borrar el Periodo", "Advertencia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            semestreControl.borrar(pkPeriodo);
            this.dispose();
        }
    }//GEN-LAST:event_btbBorrarActionPerformed
    private void verDialog() {
        Proceso p = new Proceso();
        p.cargarJDialog();
    }

                private void cambiarNombre() {
                    new Thread(
                            new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        String sql1 = "ALTER TABLE `sismodes_1`.`nota` RENAME TO nota_" + codigoPeridotxt.getText() + "";
                                        Conexion cc = Conexion.getInstance();
                                        Connection cn = cc.Conectar();
                                        Statement st = cn.createStatement();
                                        st.executeUpdate(sql1);

                                    } catch (SQLException e) {
                                        System.out.println("error al ejecutar el cambio de nombre" + e);
                                    } finally {
                                        try {
                                            cc.desconectar();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(FormularioPeriodo.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                    ).start();

                }

    private void cambiar() {
        try {
            String sql4 = "UPDATE `sismodes_1`.`periodo_semestre` SET `matricula`='0' WHERE `id1_periodo`=" + codigo + "";
            Conexion cc = Conexion.getInstance();
            Connection cn = cc.Conectar();
            Statement st = cn.createStatement();
            System.out.println(sql4);
            st.executeUpdate(sql4);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar " + e);
        }
    }

    private void cargarCodigo() throws SQLException {
        String sql = "SELECT * FROM periodo_semestre";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String fila[] = new String[6];
        int index = 2;
        while (rs.next()) {
            fila[3] = String.valueOf("PE" + index);
            index++;
            codigoPeridotxt.setText(fila[3]);
            codigo = rs.getString("id1_periodo");
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioPeriodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioPeriodo dialog = new FormularioPeriodo(periodo, true);
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
    private javax.swing.JButton btbBorrar;
    private javax.swing.JButton btbGuardar;
    private javax.swing.JTextField codigoPeridotxt;
    private com.toedter.calendar.JDateChooser ffinalChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.ButtonGroup malla;
    private javax.swing.ButtonGroup matricula;
    private javax.swing.JLabel mensaje;
    private javax.swing.JPanel panelMalla;
    private javax.swing.JPanel panelPeriodo;
    private javax.swing.JTextField periodotxt;
    public static javax.swing.JRadioButton rdbMEno;
    public static javax.swing.JRadioButton rdbMEsi;
    public static javax.swing.JRadioButton rdbMno;
    public static javax.swing.JRadioButton rdbMsi;
    // End of variables declaration//GEN-END:variables
}