package vistas;

import conectar.Conexion;
import control.Crud;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.MallaDao;
import logica.MetodosGeneralesDao;
import modelo.Especialidad;
import modelo.Estado;
import modelo.Malla;
import modelo.Periodo;
import modelo.Semestre;

public class FormularioMalla extends javax.swing.JDialog {

    private static FrmMalla frmMalla;
    Conexion cc;
    Connection cn;
    Crud crud;
    private String activada;
    private Integer idMalla;
    MetodosGeneralesDao metodosGeneralesDao;
    ResultSet resultSet;
    private static final Integer valor = 100;
    Malla malla;
    Especialidad especialidad;
    Periodo periodo;
    Semestre semestre;
    Map campos;

    public FormularioMalla(FrmMalla parent, boolean modal) {
        FormularioMalla.frmMalla = parent;
        this.setModal(modal);
        initComponents();
        malla = new Malla();
        metodosGeneralesDao = new MetodosGeneralesDao();
        this.gurdarBtn.setEnabled(false);
        especialidad = new Especialidad();
        periodo = new Periodo();
        semestre = new Semestre();
        cargarEspecialidad();
        cargarPeriodo();
        cargarSemestre();
    }

    public FormularioMalla(FrmMalla parent, boolean modal, Integer idMalla) {
        FormularioMalla.frmMalla = parent;
        this.setModal(modal);
        initComponents();
        malla = new Malla();
        this.idMalla = idMalla;
        metodosGeneralesDao = new MetodosGeneralesDao();
        especialidad = new Especialidad();
        periodo = new Periodo();
        semestre = new Semestre();
        this.gurdarBtn.setEnabled(false);
        cargarEspecialidad();
        cargarPeriodo();
        cargarSemestre();

        MallaDao mallaDao = new MallaDao();
        if (idMalla > 0) {
            try {
                resultSet = mallaDao.consulta(idMalla);
                while (resultSet.next()) {
                    especialidad.setEspecialidad(resultSet.getString("especialidad"));
                    especialidad.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
                    especialidadCmb.setSelectedItem(especialidad);

                    periodo.setIdPeriodo(Integer.parseInt(resultSet.getString("id1_periodo")));
                    periodo.setNombrePeriodo(resultSet.getString("periodo"));
                    periodoCmb.setSelectedItem(periodo);

                    semestre.setIdSemestre(Integer.valueOf(resultSet.getString("id1_semestre")));
                    semestre.setSemestre(resultSet.getString("semestre"));
                    semestreCmb.setSelectedItem(semestre);

                    malla.setNombreMalla(resultSet.getString("nombre_malla"));
                    malla.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fecha_creacion")));
                    nombreMallaTxt.setText(malla.getNombreMalla());
                    porcentajePonderadoNota.setText(resultSet.getString("porc_ponderado_nota"));
                    porcentajeTutoriaIntegrada.setText(resultSet.getString("porc_integrada"));
                    valorMinimoPromedioNotaTxt.setText(resultSet.getString("valor_min_promedio"));
                    valorIngresoNotaTxt.setText(resultSet.getString("valor_calf_nota"));
                    valorMinimoAsistenciaTxt.setText(resultSet.getString("valor_min_asistencia"));
                    valorMinimoPromedioNotaTxt.setText(resultSet.getString("valor_min_promedio"));
                    valorMinimoRecuperacionTxt.setText(resultSet.getString("valor_min_recuperacion"));
                    activada = resultSet.getString("estado");
                    if (!activada.equals(Estado.AC.name())) {
                        desactivadaRdb.setSelected(true);
                    } else {
                        activadaRdb.setSelected(true);
                    }

                }
            } catch (SQLException | ParseException | NumberFormatException e) {
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estadoMallaGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreMallaTxt = new javax.swing.JTextField();
        porcentajePonderadoNota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        porcentajeTutoriaIntegrada = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        activadaRdb = new javax.swing.JRadioButton();
        desactivadaRdb = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        semestreCmb = new javax.swing.JComboBox();
        especialidadCmb = new javax.swing.JComboBox();
        periodoCmb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        gurdarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        validarBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        valorMinimoPromedioNotaTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        valorMinimoAsistenciaTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        valorIngresoNotaTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        valorMinimoRecuperacionTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FORMULARIO DE MALLA");
        setUndecorated(true);

        jLabel1.setText("Nombre Malla");

        jLabel2.setText("Porcentaje Ponderado Nota");

        porcentajePonderadoNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajePonderadoNotaKeyTyped(evt);
            }
        });

        jLabel3.setText("Porcentaje Tutoria Integrada");

        porcentajeTutoriaIntegrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeTutoriaIntegradaKeyTyped(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado Malla"));

        estadoMallaGroup.add(activadaRdb);
        activadaRdb.setText("Activada");
        activadaRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activadaRdbActionPerformed(evt);
            }
        });

        estadoMallaGroup.add(desactivadaRdb);
        desactivadaRdb.setText("Desactivada");
        desactivadaRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desactivadaRdbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(activadaRdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(desactivadaRdb)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activadaRdb)
                    .addComponent(desactivadaRdb))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jLabel8.setText("Semestre");

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });
        semestreCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semestreCmbActionPerformed(evt);
            }
        });

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        especialidadCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                especialidadCmbItemStateChanged(evt);
            }
        });

        periodoCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        periodoCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                periodoCmbItemStateChanged(evt);
            }
        });
        periodoCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodoCmbActionPerformed(evt);
            }
        });

        jLabel9.setText("Especialidad");

        jLabel10.setText("Periodo");

        gurdarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        gurdarBtn.setText("Guardar");
        gurdarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gurdarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        jLabel12.setText("Valor Minimo Promedio Materia");

        valorMinimoPromedioNotaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorMinimoPromedioNotaTxtKeyTyped(evt);
            }
        });

        jLabel13.setText("Valor Minimo Asistencia");

        valorMinimoAsistenciaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorMinimoAsistenciaTxtActionPerformed(evt);
            }
        });
        valorMinimoAsistenciaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorMinimoAsistenciaTxtKeyTyped(evt);
            }
        });

        jLabel14.setText("Valor de Ingreso Nota");

        valorIngresoNotaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorIngresoNotaTxtKeyTyped(evt);
            }
        });

        jLabel15.setText("Valor Minimo Promedio Recuperacion");

        valorMinimoRecuperacionTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorMinimoRecuperacionTxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(77, 77, 77)
                            .addComponent(porcentajeTutoriaIntegrada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(84, 84, 84)
                            .addComponent(porcentajePonderadoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(29, 29, 29)
                            .addComponent(nombreMallaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12))))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valorMinimoPromedioNotaTxt)
                            .addComponent(valorMinimoAsistenciaTxt)
                            .addComponent(valorMinimoRecuperacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorIngresoNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(validarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gurdarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarBtn))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(43, 43, 43)
                            .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(periodoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreMallaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(porcentajePonderadoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(porcentajeTutoriaIntegrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(valorIngresoNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valorMinimoPromedioNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(valorMinimoRecuperacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(valorMinimoAsistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel8))
                                    .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(periodoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(validarBtn)
                                    .addComponent(gurdarBtn)
                                    .addComponent(cancelarBtn)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void periodoCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodoCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_periodoCmbActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        crud = new Crud();
        if (validaForm()) {
            if (validaValor()) {
                ocultaForm();
                validarBtn.setEnabled(false);
                this.gurdarBtn.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error en configuracion de la Malla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_validarBtnActionPerformed
    private void ocultaForm() {
        nombreMallaTxt.setEnabled(false);
        porcentajePonderadoNota.setEnabled(false);
        porcentajeTutoriaIntegrada.setEnabled(false);
        valorMinimoPromedioNotaTxt.setEnabled(false);
        semestreCmb.setEnabled(false);
        especialidadCmb.setEnabled(false);
        periodoCmb.setEnabled(false);
        activadaRdb.setEnabled(false);
        desactivadaRdb.setEnabled(false);
        valorIngresoNotaTxt.setEnabled(false);
        valorMinimoAsistenciaTxt.setEnabled(false);
        valorMinimoPromedioNotaTxt.setEnabled(false);
        valorMinimoRecuperacionTxt.setEnabled(false);
    }
    private void activadaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activadaRdbActionPerformed
        activada = Estado.AC.name();
    }//GEN-LAST:event_activadaRdbActionPerformed

    private void desactivadaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desactivadaRdbActionPerformed
        activada = Estado.DS.name();
    }//GEN-LAST:event_desactivadaRdbActionPerformed

    private void gurdarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gurdarBtnActionPerformed
        cargarDatos();
        if (idMalla == 0) {
            crud.insertarM("malla", campos);
            this.dispose();
        } else {
            crud.actualizarM("malla", "id_malla", idMalla, campos);
            this.dispose();
        }

    }//GEN-LAST:event_gurdarBtnActionPerformed

    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        Semestre semes = (Semestre) semestreCmb.getSelectedItem();
        semestre.setIdSemestre(semes.getIdSemestre());

    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_especialidadCmbItemStateChanged
        Especialidad espe = (Especialidad) especialidadCmb.getSelectedItem();
        especialidad.setIdEspecialidad(espe.getIdEspecialidad());
    }//GEN-LAST:event_especialidadCmbItemStateChanged

    private void periodoCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodoCmbItemStateChanged
        Periodo perio = (Periodo) periodoCmb.getSelectedItem();
        periodo.setIdPeriodo(perio.getIdPeriodo());
    }//GEN-LAST:event_periodoCmbItemStateChanged

    private void porcentajePonderadoNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajePonderadoNotaKeyTyped
        validaNumero(evt, porcentajePonderadoNota, 2);
    }//GEN-LAST:event_porcentajePonderadoNotaKeyTyped

    private void porcentajeTutoriaIntegradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeTutoriaIntegradaKeyTyped
        validaNumero(evt, porcentajeTutoriaIntegrada, 2);
    }//GEN-LAST:event_porcentajeTutoriaIntegradaKeyTyped

    private void valorMinimoAsistenciaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorMinimoAsistenciaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorMinimoAsistenciaTxtActionPerformed

    private void semestreCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semestreCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semestreCmbActionPerformed

    private void valorIngresoNotaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorIngresoNotaTxtKeyTyped
        validaNumero(evt, valorIngresoNotaTxt, 2);
    }//GEN-LAST:event_valorIngresoNotaTxtKeyTyped

    private void valorMinimoPromedioNotaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorMinimoPromedioNotaTxtKeyTyped
        validaDec(evt, valorMinimoPromedioNotaTxt);
    }//GEN-LAST:event_valorMinimoPromedioNotaTxtKeyTyped

    private void valorMinimoRecuperacionTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorMinimoRecuperacionTxtKeyTyped
        validaDec(evt, valorMinimoRecuperacionTxt);
    }//GEN-LAST:event_valorMinimoRecuperacionTxtKeyTyped

    private void valorMinimoAsistenciaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorMinimoAsistenciaTxtKeyTyped
        validaNumero(evt,valorMinimoAsistenciaTxt,2);
    }//GEN-LAST:event_valorMinimoAsistenciaTxtKeyTyped
    private void validaDec(java.awt.event.KeyEvent evt, JTextField field) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
            evt.consume();
        } else if (c == '.' && field.getText().contains(".")) {
            evt.consume();
        } else if (field.getText().length() >= 5) {
            evt.consume();
        }
    }

    private void validaNumero(java.awt.event.KeyEvent evt, JTextField field, int valor) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if (field.getText().length() >= valor) {
            evt.consume();
        }
    }

    private boolean validaValor() {
        boolean resultado = true;
        malla.setPorcentajePonderacionNota(Integer.parseInt(porcentajePonderadoNota.getText()));
        malla.setPorcentajeTutoriaIntegrada(Integer.parseInt(porcentajeTutoriaIntegrada.getText()));
        malla.setValorMinimoPromedio(Double.valueOf((valorMinimoPromedioNotaTxt.getText().replaceAll(",", "."))));
        malla.setValorMinimoAsistencia(Integer.parseInt(valorMinimoAsistenciaTxt.getText()));
        malla.setValorNota(Double.valueOf((valorIngresoNotaTxt.getText().replaceAll(",", "."))));
        malla.setValorRecuperacion(Double.valueOf((valorMinimoRecuperacionTxt.getText().replaceAll(",", "."))));
        int sumaNotaPonderada = malla.getPorcentajePonderacionNota() + malla.getPorcentajeTutoriaIntegrada();

        if (valorIngresoNotaTxt.getText().compareTo("20") != 0 && valorIngresoNotaTxt.getText().compareTo("10") != 0) {
            resultado = false;
        } else if (malla.getValorNota() != 20 && malla.getValorNota() != 10) {
            resultado = false;
        } else if (malla.getValorMinimoAsistencia() >= 100 || malla.getValorMinimoAsistencia() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 20 && malla.getValorMinimoPromedio() > 20 || malla.getValorNota() == 20 && malla.getValorMinimoPromedio() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 10 && malla.getValorRecuperacion() > 10 || malla.getValorNota() == 10 && malla.getValorMinimoPromedio() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 20 && malla.getValorRecuperacion() > 20 || malla.getValorNota() == 20 && malla.getValorRecuperacion() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 10 && malla.getValorRecuperacion() > 10 || malla.getValorNota() == 10 && malla.getValorRecuperacion() <= 0) {
            resultado = false;
        } else if (sumaNotaPonderada > valor || sumaNotaPonderada < valor) {
            resultado = false;
        } 

        return resultado;

    }

    private Map cargarDatos() {
        campos = new HashMap();
        Calendar cal = Calendar.getInstance();
        malla.setNombreMalla(nombreMallaTxt.getText().toUpperCase());
        malla.setEstado(activada);
        campos.put("nombre_malla", malla.getNombreMalla());
        campos.put("porc_ponderado_nota", malla.getPorcentajePonderacionNota());
        campos.put("porc_integrada", malla.getPorcentajeTutoriaIntegrada());
        campos.put("valor_min_promedio", malla.getValorMinimoPromedio());
        campos.put("valor_min_asistencia", malla.getValorMinimoAsistencia());
        campos.put("valor_min_recuperacion", malla.getValorRecuperacion());
        campos.put("valor_calf_nota", malla.getValorNota());
        campos.put("porcentaje", "100");
        campos.put("id1_especialidad", especialidad.getIdEspecialidad());
        campos.put("id1_periodo", periodo.getIdPeriodo());
        campos.put("id1_semestre", semestre.getIdSemestre());
        campos.put("estado", malla.getEstado());
        if (idMalla == 0) {
            campos.put("fecha_creacion", cal.getTime());
        } else {
            campos.put("fecha_creacion", malla.getFechaCreacion());
        }
        campos.put("fecha_modificacion", cal.getTime());
        return campos;
    }

    private void cargarEspecialidad() {
        try {

            resultSet = metodosGeneralesDao.cargaComboEspecialidad();
            while (resultSet.next()) {
                Especialidad espe = new Especialidad();
                espe.setEspecialidad(resultSet.getString("especialidad"));
                espe.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
                especialidadCmb.addItem(espe);

            }
        } catch (SQLException | NumberFormatException e) {
        }
    }

    private void cargarPeriodo() {
        try {
            resultSet = metodosGeneralesDao.cargaComboPeriodo();
            while (resultSet.next()) {
                Periodo perio = new Periodo();
                perio.setIdPeriodo(Integer.parseInt(resultSet.getString("id1_periodo")));
                perio.setNombrePeriodo(resultSet.getString("periodo"));
                periodoCmb.addItem(perio);
            }
        } catch (SQLException | NumberFormatException e) {
        }

    }

    private void cargarSemestre() {
        try {
            resultSet = metodosGeneralesDao.cargaComboSemestre();
            while (resultSet.next()) {
                Semestre semes = new Semestre();
                semes.setIdSemestre(resultSet.getInt("id1_semestre"));
                semes.setSemestre(resultSet.getString("semestre"));
                semestreCmb.addItem(semes);

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private boolean validaForm() {
        boolean resultado = true;
        JTextField[] yu = {nombreMallaTxt,
            porcentajePonderadoNota,
            porcentajeTutoriaIntegrada,
            valorMinimoPromedioNotaTxt,
            valorMinimoAsistenciaTxt,
            valorIngresoNotaTxt,
            valorMinimoRecuperacionTxt};

        for (JTextField yu1 : yu) {
            if (yu1.getText().isEmpty() || yu1.getText().compareTo("0") == 0) {
                resultado = false;
                break;
            } else {
                resultado = true;
            }
        }
        if (estadoMallaGroup.isSelected(null)) {
            resultado = false;
        } else if (semestreCmb.getSelectedIndex() == 0 || especialidadCmb.getSelectedIndex() == 0 || periodoCmb.getSelectedIndex() == 0) {
            resultado = false;
        }

        return resultado;
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
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioMalla dialog = new FormularioMalla(frmMalla, true);
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
    private javax.swing.JRadioButton activadaRdb;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JRadioButton desactivadaRdb;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.ButtonGroup estadoMallaGroup;
    private javax.swing.JButton gurdarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreMallaTxt;
    private javax.swing.JComboBox periodoCmb;
    private javax.swing.JTextField porcentajePonderadoNota;
    private javax.swing.JTextField porcentajeTutoriaIntegrada;
    private javax.swing.JComboBox semestreCmb;
    private javax.swing.JButton validarBtn;
    private javax.swing.JTextField valorIngresoNotaTxt;
    private javax.swing.JTextField valorMinimoAsistenciaTxt;
    private javax.swing.JTextField valorMinimoPromedioNotaTxt;
    private javax.swing.JTextField valorMinimoRecuperacionTxt;
    // End of variables declaration//GEN-END:variables
}
