/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import control.Crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.AlumnoDao;
import logica.MateriaDao;
import logica.MatriculaDao;
import logica.MetodosGeneralesDao;
import modelo.Alumno;
import modelo.Especialidad;
import modelo.Estado;
import modelo.Materia;
import modelo.Matricula;
import modelo.Paralelo;
import modelo.Periodo;
import modelo.Semestre;

public class FrmMatricula extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    DefaultTableModel modelo;
    ResultSet resultSet;
    Matricula matricula;
    List<Matricula> listaMatricula;
    MatriculaDao matriculaDao;
    AlumnoDao alumnoDao;
    Alumno alumno;
    MetodosGeneralesDao metodosGeneralesDao;
    Periodo periodo;
    List<Materia> listaMaterias;
    Especialidad especialidad;
    Semestre semestre;
    Paralelo paralelo;
    Map campos, campos1, campos2;
    List<Map> mapMaterias;
    Calendar cal;
    Crud crud;

    public FrmMatricula() {
        initComponents();
//      this.setLocation(310, 110);
        ocultaCampos();
        matriculaBtn.setEnabled(false);
        validarBtn.setEnabled(false);
        nombresBuscadosTxt.setEnabled(false);
        metodosGeneralesDao = new MetodosGeneralesDao();
        matriculaDao = new MatriculaDao();
        cargarEspecialidad();
        cargarSemestre();
        cargarParalelo();
        cargarDatos();
        especialidad = new Especialidad();
        semestre = new Semestre();
        paralelo = new Paralelo();
        cal = Calendar.getInstance();
    }

    private void cargarDatos() {
        String[] col = {"PK", "CEDULA", "NOMBRES COMPLETOS", "TIPO MATRICULA", "SEMESTRE", "ESPECIALIDAD", "PARALELO"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);

        this.matriculaTabla.setModel(modelo);
        matriculaTabla.getColumnModel().getColumn(0).setMaxWidth(40);
        matriculaTabla.getColumnModel().getColumn(0).setMinWidth(40);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(40);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(40);

        matriculaTabla.getColumnModel().getColumn(1).setMaxWidth(100);
        matriculaTabla.getColumnModel().getColumn(1).setMinWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(100);

        matriculaTabla.getColumnModel().getColumn(2).setMaxWidth(230);
        matriculaTabla.getColumnModel().getColumn(2).setMinWidth(230);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(230);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(230);

        matriculaTabla.getColumnModel().getColumn(3).setMaxWidth(100);
        matriculaTabla.getColumnModel().getColumn(3).setMinWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(100);

        matriculaTabla.getColumnModel().getColumn(4).setMaxWidth(100);
        matriculaTabla.getColumnModel().getColumn(4).setMinWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(100);

        matriculaTabla.getColumnModel().getColumn(6).setMaxWidth(80);
        matriculaTabla.getColumnModel().getColumn(6).setMinWidth(80);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(80);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(80);

        matriculaTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        new Thread(new Runnable() {

            @Override
            public void run() {
                matricula = new Matricula();
                int i = 0;
                if (listaMatricula == null) {
                    listaMatricula = new ArrayList<>();
                } else {
                    listaMatricula.clear();
                }
                try {
                    resultSet = matriculaDao.consultaOrdenada();
                    while (resultSet.next()) {
                        matricula.setIdMatricula(Integer.parseInt(resultSet.getString("id_matricula")));
                        matricula.setCedula(resultSet.getString("cedula"));
                        matricula.setApellidoPaterno(resultSet.getString("apellido_paterno"));
                        matricula.setApellidoMaterno(resultSet.getString("apellido_materno"));
                        matricula.setNombreCompleto(resultSet.getString("nombre_completo"));
                        matricula.setTipoMatricula(resultSet.getString("tipo_matricula"));
                        matricula.setSemestre(resultSet.getString("semestre"));
                        matricula.setEspecialidad(resultSet.getString("especialidad"));
                        matricula.setParalelo(resultSet.getString("paralelo"));
                        if (filtroTxt.equals("") || matricula.getCedula().contains(filtroTxt.getText())) {
                            listaMatricula.add(new Matricula(matricula.getIdMatricula(),
                                    matricula.getTipoMatricula(),
                                    matricula.getSemestre(),
                                    matricula.getParalelo(),
                                    matricula.getEspecialidad(),
                                    matricula.getCedula(),
                                    matricula.getNombreCompleto()));
                            modelo.insertRow(i, new Object[]{
                                matricula.getIdMatricula(),
                                matricula.getCedula(),
                                matricula.getNombreCompleto(),
                                matricula.getTipoMatricula(),
                                matricula.getSemestre(),
                                matricula.getEspecialidad(),
                                matricula.getParalelo()
                            });

                        }
                    }
                } catch (SQLException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error la cargar", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }).start();
    }

    private void ocultaCampos() {
        cedulaBuscadaTxt.setEnabled(false);
        nombresBuscadosTxt.setEnabled(false);
        buscarInscritoBtn.setEnabled(false);
        semestreCmb.setEnabled(false);
        especialidadCmb.setEnabled(false);
        paraleloCmd.setEnabled(false);
        validarBtn.setEnabled(false);

    }

    private void activaCampos() {
        cedulaBuscadaTxt.setEnabled(true);
        nombresBuscadosTxt.setEnabled(true);
        buscarInscritoBtn.setEnabled(true);
        semestreCmb.setEnabled(true);
        especialidadCmb.setEnabled(true);
        paraleloCmd.setEnabled(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        matriculaTabla = new javax.swing.JTable();
        nuevaMatriculaBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filtroTxt = new javax.swing.JTextField();
        buscarMatriculaBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cedulaBuscadaTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombresBuscadosTxt = new javax.swing.JTextField();
        buscarInscritoBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        semestreCmb = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        especialidadCmb = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        paraleloCmd = new javax.swing.JComboBox();
        matriculaBtn = new javax.swing.JButton();
        validarBtn = new javax.swing.JButton();

        setClosable(true);

        matriculaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(matriculaTabla);

        nuevaMatriculaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        nuevaMatriculaBtn.setText("Nueva Matricula");
        nuevaMatriculaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaMatriculaBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Busqueda por Cedula");

        buscarMatriculaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        buscarMatriculaBtn.setText("Buscar");
        buscarMatriculaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarMatriculaBtnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos para Realizar Matricula"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Alumno Inscrito"));

        jLabel3.setText("Cedula");

        jLabel4.setText("Nombres");

        buscarInscritoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        buscarInscritoBtn.setText("Buscar");
        buscarInscritoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarInscritoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cedulaBuscadaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarInscritoBtn))
                    .addComponent(jLabel4)
                    .addComponent(nombresBuscadosTxt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedulaBuscadaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarInscritoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombresBuscadosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Semestre");

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });

        jLabel6.setText("Especialidad");

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        especialidadCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                especialidadCmbItemStateChanged(evt);
            }
        });

        jLabel7.setText("Paralelo");

        paraleloCmd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        paraleloCmd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paraleloCmdItemStateChanged(evt);
            }
        });

        matriculaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Wallet.png"))); // NOI18N
        matriculaBtn.setText("Matricular");
        matriculaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculaBtnActionPerformed(evt);
            }
        });

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(matriculaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(validarBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(semestreCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(especialidadCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paraleloCmd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(paraleloCmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matriculaBtn)
                    .addComponent(validarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(buscarMatriculaBtn)
                                .addGap(462, 462, 462)
                                .addComponent(nuevaMatriculaBtn)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarMatriculaBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nuevaMatriculaBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaMatriculaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaMatriculaBtnActionPerformed
        activaCampos();
    }//GEN-LAST:event_nuevaMatriculaBtnActionPerformed

    private void buscarInscritoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarInscritoBtnActionPerformed
        if (!cedulaBuscadaTxt.getText().trim().isEmpty()) {
            alumnoDao = new AlumnoDao();
            alumno = alumnoDao.buscarAlumno(cedulaBuscadaTxt.getText().trim());
            if (alumno.getIdAlumno() != null) {
                nombresBuscadosTxt.setText(alumno.getApellidoPaterno().concat(" ")
                        .concat(alumno.getApellidoMaterno()).concat(" ")
                        .concat(alumno.getNombreCompleto()));
                validarBtn.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro inscripcion con esta cedula");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error campo busqueda vacio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscarInscritoBtnActionPerformed

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        MateriaDao materiaDao = new MateriaDao();
        if (validaForm()) {
            listaMaterias = materiaDao.listaMaterias(this.especialidad.getIdEspecialidad(), this.semestre.getIdSemestre());
            if (!listaMaterias.isEmpty()) {
                periodo = metodosGeneralesDao.codigoPeriodoActivo();
                matriculaBtn.setEnabled(true);
                ocultaCampos();
            } else {
                JOptionPane.showMessageDialog(null, "No existe materias para realizar la matricula");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_validarBtnActionPerformed

    private boolean validaForm() {
        boolean resultado = false;
        JComboBox[] combo = {especialidadCmb, semestreCmb, paraleloCmd};
        for (JComboBox combo1 : combo) {
            if (combo1.getSelectedIndex() == 0) {
                resultado = false;
                break;
            } else {
                resultado = true;
            }
        }
        return resultado;
    }
    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        try {
            Semestre seme = (Semestre) semestreCmb.getSelectedItem();
            this.semestre.setIdSemestre(seme.getIdSemestre());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_especialidadCmbItemStateChanged
        try {
            Especialidad espe = (Especialidad) especialidadCmb.getSelectedItem();
            this.especialidad.setIdEspecialidad(espe.getIdEspecialidad());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_especialidadCmbItemStateChanged

    private void paraleloCmdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paraleloCmdItemStateChanged

        try {
            Paralelo para = (Paralelo) paraleloCmd.getSelectedItem();
            this.paralelo.setIdParelelo(para.getIdParelelo());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_paraleloCmdItemStateChanged

    private void matriculaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculaBtnActionPerformed

        try {
            crud = new Crud();
            cargarCamposMatricula();
            crud.insertar("matricula", campos);
            cargaListaMaterias();
            for (Map mapMateria : mapMaterias) {
                crud.insertarMaterias("nota", periodo.getCodigoPeriodo(), mapMateria);
            }
            cargaResumen();
            crud.insertarM("resumen", campos2);
            limpiaCampos();
            cargarDatos();
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_matriculaBtnActionPerformed

    private void buscarMatriculaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarMatriculaBtnActionPerformed
        cargarDatos();
    }//GEN-LAST:event_buscarMatriculaBtnActionPerformed
    private Map cargarCamposMatricula() {
        campos = new HashMap();
        campos.put("id_alumno", alumno.getIdAlumno());
        campos.put("cedula", alumno.getCedula());
        campos.put("nombre_completo", nombresBuscadosTxt.getText());
        campos.put("tipo_matricula",Estado.ORDINARIA.name());
        campos.put("fecha_creacion", cal.getTime());
        campos.put("id_semestre", semestre.getIdSemestre());
        campos.put("id_especialidad", especialidad.getIdEspecialidad());
        campos.put("id_paralelo", paralelo.getIdParelelo());
        return campos;
    }

    private List<Map> cargaListaMaterias() {
        mapMaterias = new ArrayList<>();
        matricula.setIdMatricula(matriculaDao.cargaIdMatricuala());
        for (Materia listaMateria : listaMaterias) {
            campos1 = new HashMap();
            campos1.put("cedula", alumno.getCedula());
            campos1.put("fecha_ingreso", cal.getTime());
            campos1.put("fecha_modificacion", cal.getTime());
            campos1.put("id_alumno", alumno.getIdAlumno());
            campos1.put("id_matricula", matricula.getIdMatricula());
            campos1.put("id_materia", listaMateria.getIdMateria());
            campos1.put("id_config_materia", listaMateria.getIdConfiguracion());
            campos1.put("id_malla", listaMateria.getIdMalla());
            campos1.put("id_desc_materia", listaMateria.getIdDescripcion());
            mapMaterias.add(campos1);
        }
        return mapMaterias;
    }

    private Map cargaResumen() {
        campos2 = new HashMap();
        campos2.put("id_alumno", alumno.getIdAlumno());
        campos2.put("cedula", alumno.getCedula());
        campos2.put("nombre_completo", nombresBuscadosTxt.getText());
        campos2.put("fecha_creacion", cal.getTime());
        campos2.put("fecha_modificacion", cal.getTime());
        campos2.put("id_periodo", periodo.getIdPeriodo());
        campos2.put("id_semestre", semestre.getIdSemestre());
        campos2.put("id_especialidad", especialidad.getIdEspecialidad());
        campos2.put("id_malla", listaMaterias.get(0).getIdMalla());
        return campos2;
    }

    private void limpiaCampos() {
        cedulaBuscadaTxt.setText(null);
        nombresBuscadosTxt.setText(null);
        matriculaBtn.setEnabled(false);
        especialidadCmb.setSelectedIndex(0);
        semestreCmb.setSelectedIndex(0);
        paraleloCmd.setSelectedIndex(0);
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

    private void cargarParalelo() {
        try {
            resultSet = metodosGeneralesDao.cargacomboParalelo();
            while (resultSet.next()) {
                Paralelo para = new Paralelo();
                para.setIdParelelo(Integer.parseInt(resultSet.getString("id1_paralelo")));
                para.setParalelo(resultSet.getString("paralelo"));
                paraleloCmd.addItem(para);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarInscritoBtn;
    private javax.swing.JButton buscarMatriculaBtn;
    private javax.swing.JTextField cedulaBuscadaTxt;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton matriculaBtn;
    private javax.swing.JTable matriculaTabla;
    private javax.swing.JTextField nombresBuscadosTxt;
    private javax.swing.JButton nuevaMatriculaBtn;
    private javax.swing.JComboBox paraleloCmd;
    private javax.swing.JComboBox semestreCmb;
    private javax.swing.JButton validarBtn;
    // End of variables declaration//GEN-END:variables
}
