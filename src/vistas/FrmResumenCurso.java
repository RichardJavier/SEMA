/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import control.Crud;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.MetodosGeneralesDao;
import logica.ResumenDao;
import modelo.Especialidad;
import modelo.Estado;
import modelo.Configuracion;
import modelo.Resumen;
import modelo.Semestre;

public class FrmResumenCurso extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    DefaultTableModel modelo;
    List<Resumen> listaResumen;
    Resumen resumen;
    ResultSet resultSet, resultSet1;
    ResumenDao resumenDao;
    Configuracion malla;
    MetodosGeneralesDao metodosGeneralesDao;
    Semestre semestre;
    Especialidad especialidad;

    public FrmResumenCurso() {
        initComponents();
        ocultaCampos();
        guardarBtn.setEnabled(false);
        metodosGeneralesDao = new MetodosGeneralesDao();
        semestre = new Semestre();
        especialidad = new Especialidad();
        cargarEspecialidad();
        cargarSemestre();
        this.setLocation(110, 60);

    }

    private void cargaDatos(final Integer idSemestre, final Integer idEspecialidad) {
        String[] col = {"PK", "NOMBRES COMPLETOS", "APROBACION", "SEMESTRE", "ESPECIALIDAD"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        modelo.setRowCount(0);
        this.resumenTabla.setModel(modelo);
        resumenTabla.getColumnModel().getColumn(0).setMaxWidth(40);
        resumenTabla.getColumnModel().getColumn(0).setMinWidth(40);
        resumenTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
        resumenTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(40);

        resumenTabla.getColumnModel().getColumn(1).setMaxWidth(220);
        resumenTabla.getColumnModel().getColumn(1).setMinWidth(220);
        resumenTabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(220);
        resumenTabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(220);

        resumenTabla.getColumnModel().getColumn(2).setMaxWidth(115);
        resumenTabla.getColumnModel().getColumn(2).setMinWidth(115);
        resumenTabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(115);
        resumenTabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(115);

        resumenTabla.getColumnModel().getColumn(3).setMaxWidth(90);
        resumenTabla.getColumnModel().getColumn(3).setMinWidth(90);
        resumenTabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(90);
        resumenTabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(90);

        resumenTabla.getColumnModel().getColumn(4).setMaxWidth(235);
        resumenTabla.getColumnModel().getColumn(4).setMinWidth(235);
        resumenTabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(235);
        resumenTabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(235);

        resumenTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        new Thread(new Runnable() {
            @Override
            public void run() {
                resumen = new Resumen();
                int i = 0;
                if (listaResumen == null) {
                    listaResumen = new ArrayList<>();
                } else {
                    listaResumen.clear();
                }
                try {
                    resumenDao = new ResumenDao();
                    resultSet = resumenDao.cargarResumenCursoEspecialidad(idSemestre, idEspecialidad);
                    while (resultSet.next()) {
                        resumen.setIdResumen(Integer.parseInt(resultSet.getString("id_resumen")));
                        resumen.setNombreCompleto(resultSet.getString("nombre_completo"));
                        resumen.setAprobacion(resultSet.getString("aprobacion"));
                        resumen.setSemestre(resultSet.getString("semestre"));
                        resumen.setEspecialidad(resultSet.getString("especialidad"));
                        if (filtroTxt.equals("") || resumen.getNombreCompleto().contains(filtroTxt.getText())) {
                            listaResumen.add(new Resumen(resumen.getIdResumen(),
                                    resumen.getNombreCompleto(),
                                    resumen.getAprobacion(),
                                    resumen.getSemestre(),
                                    resumen.getEspecialidad()));
                            modelo.insertRow(i, new Object[]{
                                resumen.getIdResumen(),
                                resumen.getNombreCompleto(),
                                resumen.getAprobacion(),
                                resumen.getSemestre(),
                                resumen.getEspecialidad()
                            });
                        }

                    }

                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error la cargar", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                    }
                }

            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cargarBtn = new javax.swing.JButton();
        semestreCmb = new javax.swing.JComboBox();
        especialidadCmb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buscarBtn = new javax.swing.JButton();
        filtroTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resumenTabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tutoriaIntegradaTxt = new javax.swing.JTextField();
        promedioPonderadoTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        notaEmpresa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        notaTotalTeorica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        asistencia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        notaFinal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        aprobacionTxt = new javax.swing.JTextField();
        validarBtn = new javax.swing.JButton();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("REGISTRO DE NOTA FINAL POR CURSO Y ESPECIALIDAD");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda de Alumno"));

        cargarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Lightning.png"))); // NOI18N
        cargarBtn.setText("Cargar");
        cargarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarBtnActionPerformed(evt);
            }
        });

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        especialidadCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                especialidadCmbItemStateChanged(evt);
            }
        });

        jLabel9.setText("* Semestre");

        jLabel10.setText("* Especialidad");

        buscarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        buscarBtn.setText("Buscar");

        filtroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroTxtKeyReleased(evt);
            }
        });

        jLabel1.setText("* Nombre del Alumno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscarBtn)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addComponent(cargarBtn)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarBtn)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cargarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resumenTabla.setAutoCreateRowSorter(true);
        resumenTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        resumenTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        resumenTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resumenTablaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resumenTablaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(resumenTabla);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores de Promedio Final"));

        jLabel2.setText("Promedio Ponderado");

        jLabel3.setText("Tutoria Integrada");

        tutoriaIntegradaTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tutoriaIntegradaTxtMouseClicked(evt);
            }
        });
        tutoriaIntegradaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tutoriaIntegradaTxtKeyTyped(evt);
            }
        });

        jLabel4.setText("Nota Total Teorica");

        jLabel5.setText("Nota Empresa");

        jLabel6.setText("Asistencia");

        jLabel7.setText("Nota Final");

        jLabel8.setText("Aprobacion");

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar ");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(validarBtn)
                                .addGap(18, 18, 18)
                                .addComponent(guardarBtn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(asistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(promedioPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tutoriaIntegradaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(notaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(notaTotalTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(notaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(28, 28, 28)
                                .addComponent(aprobacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(cancelarBtn)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(promedioPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tutoriaIntegradaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(notaTotalTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(notaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(asistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(notaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(aprobacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validarBtn)
                    .addComponent(guardarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)))
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarBtnActionPerformed
        if (semestreCmb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Error combo semestre vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (especialidadCmb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Error combo especialidad vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            cargaDatos(semestre.getIdSemestre(), especialidad.getIdEspecialidad());
        }
    }//GEN-LAST:event_cargarBtnActionPerformed

    private void resumenTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resumenTablaMouseClicked
        limpiaCampos();
        int i = resumenTabla.getSelectedRow();
        resumen.setIdResumen((Integer) resumenTabla.getValueAt(i, 0));

        try {
            resultSet1 = resumenDao.cargarValores(resumen.getIdResumen());
            malla = new Configuracion();
            while (resultSet1.next()) {
                promedioPonderadoTxt.setText(resultSet1.getString("pro_ponderado_nota"));
                tutoriaIntegradaTxt.setText(resultSet1.getString("nota_tutoria"));
                notaTotalTeorica.setText(resultSet1.getString("nota_total_teorica"));
                notaEmpresa.setText(resultSet1.getString("nota_empresa"));
                asistencia.setText(resultSet1.getString("asistencia"));
                notaFinal.setText(resultSet1.getString("nota_final"));
                aprobacionTxt.setText(resultSet1.getString("aprobacion"));
                malla.setPorcentajeTutoriaIntegrada(Integer.parseInt(resultSet1.getString("porc_integrada")));
                malla.setValorMinimoPromedio(Double.valueOf(resultSet1.getString("valor_min_promedio")));
                malla.setValorNota(Double.valueOf(resultSet1.getString("valor_calf_nota")));
                malla.setValorMinimoAsistencia(Integer.parseInt(resultSet1.getString("valor_min_asistencia")));
                malla.setPorcentajePonderacionNota(Integer.parseInt(resultSet1.getString("porc_ponderado_nota")));
                resumen.setPorcentajeNotaTeorica(Integer.parseInt(resultSet1.getString("porc_nota_teorica")));
                resumen.setPorcentajeNotaEmpresa(Integer.parseInt(resultSet1.getString("porc_nota_empresa")));
            }
            tutoriaIntegradaTxt.setEnabled(true);
            validarBtn.setEnabled(true);
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_resumenTablaMouseClicked

    private void resumenTablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resumenTablaMouseEntered

    }//GEN-LAST:event_resumenTablaMouseEntered

    private void tutoriaIntegradaTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tutoriaIntegradaTxtMouseClicked
        if (tutoriaIntegradaTxt.getText().trim().compareTo("0") == 0) {
            tutoriaIntegradaTxt.setText(null);
        }
    }//GEN-LAST:event_tutoriaIntegradaTxtMouseClicked

    private void tutoriaIntegradaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tutoriaIntegradaTxtKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
            evt.consume();
        } else if (c == '.' && tutoriaIntegradaTxt.getText().contains(".")) {
            evt.consume();
        } else if (tutoriaIntegradaTxt.getText().length() >= 5) {
            evt.consume();
        }

    }//GEN-LAST:event_tutoriaIntegradaTxtKeyTyped

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        calcularCampos();
    }//GEN-LAST:event_validarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        Map campos = new HashMap();
        campos.put("nota_tutoria", tutoriaIntegradaTxt.getText());
        campos.put("nota_total_teorica", notaTotalTeorica.getText());
        campos.put("nota_final", notaFinal.getText());
        campos.put("asistencia", asistencia.getText());
        campos.put("aprobacion", aprobacionTxt.getText());
        Crud crud = new Crud();
        crud.actualizarM("resumen", "id_resumen", resumen.getIdResumen(), campos,Login.getUsuario().getNombre());
        limpiaCampos();
        ocultaCampos();
        cargaDatos(semestre.getIdSemestre(), especialidad.getIdEspecialidad());
        semestreCmb.setSelectedIndex(0);
        especialidadCmb.setSelectedIndex(0);
      

    }//GEN-LAST:event_guardarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        try {
            Semestre sm = (Semestre) semestreCmb.getSelectedItem();
            semestre.setIdSemestre(sm.getIdSemestre());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_especialidadCmbItemStateChanged
        try {
            Especialidad es = (Especialidad) especialidadCmb.getSelectedItem();
            especialidad.setIdEspecialidad(es.getIdEspecialidad());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_especialidadCmbItemStateChanged

    private void filtroTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroTxtKeyReleased
        convertiraMayusculasEnJtextfield(filtroTxt);
    }//GEN-LAST:event_filtroTxtKeyReleased
    public void convertiraMayusculasEnJtextfield(javax.swing.JTextField jTextfieldS) {
        String cadena = (jTextfieldS.getText()).toUpperCase();
        jTextfieldS.setText(cadena);
    }

    private void calcularCampos() {
        if (!tutoriaIntegradaTxt.getText().trim().isEmpty()) {
            resumen.setNotaTutoria(new BigDecimal(tutoriaIntegradaTxt.getText()));
            if (resumen.getNotaTutoria().compareTo(new BigDecimal(malla.getValorNota())) >= 0) {
                JOptionPane.showMessageDialog(null, "Error valor de ingresado superior al adminitido maximo:" + " " + malla.getValorNota() + " ", "Error", JOptionPane.ERROR_MESSAGE);
                tutoriaIntegradaTxt.setText(null);
            } else {
                resumen.setPromedioPonderadoNota(new BigDecimal(promedioPonderadoTxt.getText()));
                resumen.setNotaTotalTeorica(new BigDecimal(notaTotalTeorica.getText()));
                resumen.setNotaEmpresa(new BigDecimal(notaEmpresa.getText()));
                resumen.setAsistencia(Integer.parseInt(asistencia.getText()));
                resumen.setNotaFinal(new BigDecimal(notaFinal.getText()));

                BigDecimal ppn, nti, ntt, nf;
                ppn = new BigDecimal(malla.getPorcentajePonderacionNota()).divide(new BigDecimal(100));
                ppn = ppn.multiply(resumen.getPromedioPonderadoNota());

                nti = new BigDecimal(malla.getPorcentajeTutoriaIntegrada()).divide(new BigDecimal(100));
                nti = nti.multiply(resumen.getNotaTutoria());

                ntt = ppn.add(nti);
                ntt = ntt.setScale(2, RoundingMode.HALF_UP);
                resumen.setNotaTotalTeorica(ntt);
                notaTotalTeorica.setText(String.valueOf(resumen.getNotaTotalTeorica()));

                nf = new BigDecimal(resumen.getPorcentajeNotaTeorica()).divide(new BigDecimal(100));
                nf = nf.multiply(resumen.getNotaTotalTeorica());
                nf = nf.add(resumen.getNotaEmpresa());
                nf = nf.setScale(2, RoundingMode.HALF_UP);
                resumen.setNotaFinal(nf);
                notaFinal.setText(String.valueOf(resumen.getNotaFinal()));
                if (resumen.getNotaFinal().compareTo(new BigDecimal(malla.getValorMinimoPromedio())) >= 0) {
                    resumen.setAprobacion(Estado.APRUEBA.name());
                    aprobacionTxt.setText(resumen.getAprobacion());
                }
                tutoriaIntegradaTxt.setEnabled(false);
                validarBtn.setEnabled(false);
                guardarBtn.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campo de tutoria vacio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiaCampos() {
        promedioPonderadoTxt.setText(null);
        tutoriaIntegradaTxt.setText(null);
        notaTotalTeorica.setText(null);
        notaEmpresa.setText(null);
        asistencia.setText(null);
        notaFinal.setText(null);
        aprobacionTxt.setText(null);
    }

    private void ocultaCampos() {
        promedioPonderadoTxt.setEnabled(false);
        tutoriaIntegradaTxt.setEnabled(false);
        notaTotalTeorica.setEnabled(false);
        notaEmpresa.setEnabled(false);
        asistencia.setEnabled(false);
        notaFinal.setEnabled(false);
        aprobacionTxt.setEnabled(false);
        guardarBtn.setEnabled(false);
        validarBtn.setEnabled(false);
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
            System.out.println(e);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aprobacionTxt;
    private javax.swing.JTextField asistencia;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton cargarBtn;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField notaEmpresa;
    private javax.swing.JTextField notaFinal;
    private javax.swing.JTextField notaTotalTeorica;
    private javax.swing.JTextField promedioPonderadoTxt;
    private javax.swing.JTable resumenTabla;
    private javax.swing.JComboBox semestreCmb;
    private javax.swing.JTextField tutoriaIntegradaTxt;
    private javax.swing.JButton validarBtn;
    // End of variables declaration//GEN-END:variables
}
