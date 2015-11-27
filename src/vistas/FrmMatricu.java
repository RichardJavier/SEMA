/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import control.Crud;
import control.EnviaEmail;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import modelo.Resumen;
import modelo.Semestre;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Ricardo.Bravo
 */
public class FrmMatricu extends javax.swing.JInternalFrame {

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
    List<Materia> listaMaterias, listaMateriasArrastre;
    Especialidad especialidad;
    Semestre semestre;
    Paralelo paralelo;
    Map campos, campos1, campos2;
    List<Map> mapMaterias;
    Calendar cal;
    Crud crud;
    Resumen resumen;
    Integer valorMatricula;
    String file;

    public FrmMatricu() {
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
        especialidad = new Especialidad();
        semestre = new Semestre();
        paralelo = new Paralelo();
        cal = Calendar.getInstance();
        resumen = new Resumen();
        cargarDatos();
    }

    private void cargarDatos() {
        String[] col = {"PK", "CEDULA", "NOMBRES COMPLETOS", "TIPO MATRICULA", "SEMESTRE", "ESPECIALIDAD", "PARALELO"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
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

        matriculaTabla.getColumnModel().getColumn(3).setMaxWidth(150);
        matriculaTabla.getColumnModel().getColumn(3).setMinWidth(150);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(150);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(3).setMinWidth(150);

        matriculaTabla.getColumnModel().getColumn(4).setMaxWidth(100);
        matriculaTabla.getColumnModel().getColumn(4).setMinWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(100);

        matriculaTabla.getColumnModel().getColumn(6).setMaxWidth(80);
        matriculaTabla.getColumnModel().getColumn(6).setMinWidth(80);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(80);
        matriculaTabla.getTableHeader().getColumnModel().getColumn(6).setMinWidth(80);

        matriculaTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
     
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
                        matricula.setIdAlumno(Integer.parseInt(resultSet.getString("id_alumno")));
                        matricula.setCedula(resultSet.getString("cedula"));
                        matricula.setApellidoPaterno(resultSet.getString("apellido_paterno"));
                        matricula.setApellidoMaterno(resultSet.getString("apellido_materno"));
                        matricula.setNombreCompleto(resultSet.getString("nombre_completo"));
                        matricula.setTipoMatricula(resultSet.getString("tipo_matricula"));
                        matricula.setIdSemestre(Integer.parseInt(resultSet.getString("id_semestre")));
                        matricula.setSemestre(resultSet.getString("semestre"));
                        matricula.setIdEspecialidad(Integer.parseInt(resultSet.getString("id_especialidad")));
                        matricula.setEspecialidad(resultSet.getString("especialidad"));
                        matricula.setIdParalelo(Integer.parseInt(resultSet.getString("id_paralelo")));
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
                    EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
                } finally {
                    try {
                        //cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
          
    }

    private void ocultaCampos() {
        cedulaBuscadaTxt.setEnabled(false);
        nombresBuscadosTxt.setEnabled(false);
        buscarInscritoBtn.setEnabled(false);
        semestreCmb.setEnabled(false);
        especialidadCmb.setEnabled(false);
        paraleloCmd.setEnabled(false);
        validarBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        certificadoBtn.setEnabled(false);
        fichaBtn.setEnabled(false);
    }

    private void activaCampos() {
        cedulaBuscadaTxt.setEnabled(true);
        buscarInscritoBtn.setEnabled(true);
        semestreCmb.setEnabled(true);
        especialidadCmb.setEnabled(true);
        paraleloCmd.setEnabled(true);
        cancelarBtn.setEnabled(true);
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
        cancelarBtn = new javax.swing.JButton();
        certificadoBtn = new javax.swing.JButton();
        fichaBtn = new javax.swing.JButton();

        setClosable(true);

        matriculaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        matriculaTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                matriculaTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(matriculaTabla);

        nuevaMatriculaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        nuevaMatriculaBtn.setText("Nueva Matricula");
        nuevaMatriculaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaMatriculaBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Búsqueda por Cedula");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombresBuscadosTxt)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cedulaBuscadaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscarInscritoBtn))
                            .addComponent(jLabel4))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
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

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(semestreCmb, 0, 173, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(especialidadCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(paraleloCmd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(matriculaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(validarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(validarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        certificadoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Report.png"))); // NOI18N
        certificadoBtn.setText("Certificado");
        certificadoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificadoBtnActionPerformed(evt);
            }
        });

        fichaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Report.png"))); // NOI18N
        fichaBtn.setText("Ficha");
        fichaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fichaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(buscarMatriculaBtn)
                        .addGap(283, 283, 283)
                        .addComponent(certificadoBtn)
                        .addGap(18, 18, 18)
                        .addComponent(fichaBtn)
                        .addGap(18, 18, 18)
                        .addComponent(nuevaMatriculaBtn))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buscarMatriculaBtn)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nuevaMatriculaBtn)
                                    .addComponent(fichaBtn)
                                    .addComponent(certificadoBtn))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void matriculaTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_matriculaTablaMouseClicked
        try {
            Integer i = matriculaTabla.getSelectedRow();
            matricula.setIdMatricula((Integer) matriculaTabla.getValueAt(i, 0));
            matricula.setCedula(String.valueOf(matriculaTabla.getValueAt(i, 1)));
            matricula.setNombreCompleto(String.valueOf(matriculaTabla.getValueAt(i, 2)));
            semestre.setIdSemestre(matricula.getIdSemestre());
            semestre.setSemestre(matricula.getSemestre());
            especialidad.setIdEspecialidad(matricula.getIdEspecialidad());
            especialidad.setEspecialidad(matricula.getEspecialidad());
            paralelo.setIdParelelo(matricula.getIdParalelo());
            paralelo.setParalelo(matricula.getParalelo());
            cedulaBuscadaTxt.setText(matricula.getCedula());
            nombresBuscadosTxt.setText(matricula.getNombreCompleto());
            semestreCmb.setSelectedItem(semestre);
            especialidadCmb.setSelectedItem(especialidad);
            paraleloCmd.setSelectedItem(paralelo);
            alumno = new Alumno();
            alumno.setIdAlumno(matricula.getIdAlumno());
            alumno.setCedula(matricula.getCedula());
            activaCampos();
            buscarInscritoBtn.setEnabled(false);
            nuevaMatriculaBtn.setEnabled(false);
            validarBtn.setEnabled(true);
            cancelarBtn.setEnabled(true);
            certificadoBtn.setEnabled(true);
            fichaBtn.setEnabled(true);
            periodo = metodosGeneralesDao.codigoPeriodoActivo();
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

    }//GEN-LAST:event_matriculaTablaMouseClicked

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        limpiaCampos();
        ocultaCampos();
        cancelarBtn.setEnabled(false);
        nuevaMatriculaBtn.setEnabled(true);
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void certificadoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificadoBtnActionPerformed
        if (matriculaTabla.getSelectedRowCount() != 0) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de pdf", "pdf");
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Guardar Achivo");
                if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile().toString().concat(".pdf");
                    Conexion cc = Conexion.getInstance();
                    Connection cn = cc.Conectar();
//                JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes/asociacion2.jasper");
//                String path = "src/reportes/asociacion2.jasper";
                    JasperReport jr = null;
                    jr = (JasperReport) JRLoader.loadObjectFromFile("reportes/certificadoMatricula.jasper");

                    Map parametros = new HashMap();
                    parametros.put("cedula", matricula.getCedula());
                    parametros.put("idEspecialidad", especialidad.getIdEspecialidad());
                    parametros.put("idSemestre", semestre.getIdSemestre());
                    parametros.put("idMatricula", matricula.getIdMatricula());
                    JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cn);
                    JasperExportManager.exportReportToPdfFile(jp, file);
                    File archivo = new File(file);
                    Desktop.getDesktop().open(archivo);
                    //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
                    ocultaCampos();
                    limpiaCampos();
                }
            } catch (JRException | IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar pdf", "Error", JOptionPane.ERROR_MESSAGE);
                EnviaEmail.enviaMail("javier.tec1989@gmail.com", ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error sin escojer parametros", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_certificadoBtnActionPerformed

    private void fichaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fichaBtnActionPerformed
        if (matriculaTabla.getSelectedRowCount() != 0) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de pdf", "pdf");
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Guardar Achivo");
                if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile().toString().concat(".pdf");
                    Conexion cc = Conexion.getInstance();
                    Connection cn = cc.Conectar();
//                JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes/asociacion2.jasper");
//                String path = "src/reportes/asociacion2.jasper";
                    JasperReport jr = null;
                    jr = (JasperReport) JRLoader.loadObjectFromFile("reportes/fichaMatricula.jasper");

                    Map parametros = new HashMap();
                    parametros.put("cedula", matricula.getCedula());
                    parametros.put("idEspecialidad", especialidad.getIdEspecialidad());
                    parametros.put("idSemestre", semestre.getIdSemestre());
                    parametros.put("idMatricula", matricula.getIdMatricula());
                    JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cn);
                    JasperExportManager.exportReportToPdfFile(jp, file);
                    File archivo = new File(file);
                    Desktop.getDesktop().open(archivo);
                    //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file
                    ocultaCampos();
                    limpiaCampos();

                }

            } catch (JRException | IOException ex) {
                System.out.println(ex.toString());
                EnviaEmail.enviaMail("javier.tec1989@gmail.com", ex.toString());
                JOptionPane.showMessageDialog(null, "Error al cargar pdf", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error sin escojer parametros", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_fichaBtnActionPerformed

    private void nuevaMatriculaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        activaCampos();
        periodo = metodosGeneralesDao.codigoPeriodoActivo();
        matricula=new Matricula();
    }

    private void buscarInscritoBtnActionPerformed(java.awt.event.ActionEvent evt) {
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
    }

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        MateriaDao materiaDao = new MateriaDao();

        try {

            if (validaForm()) {
                if (periodo.getCodigoPeriodo().compareTo("PE22") != 0) {
                    cedulaBuscadaTxt.setEnabled(false);
                    if (matriculaDao.verificaMatricula(cedulaBuscadaTxt.getText(), periodo.getIdPeriodo())) {
                        if (semestre.getIdSemestre() == 1) {
                            listaMaterias = materiaDao.listaMaterias(this.especialidad.getIdEspecialidad(), this.semestre.getIdSemestre());
                            if (!listaMaterias.isEmpty()) {
                                matriculaBtn.setEnabled(true);
                                valorMatricula = 0;//valor para matricula normal
                                ocultaCampos();
                            } else {
                                JOptionPane.showMessageDialog(null, "No existe materias para realizar la matricula");
                            }
                        } else {
                            String h = metodosGeneralesDao.codigoPeriodoBusacado();
                            if (h.compareTo("PE22") == 0) {
                                JOptionPane.showMessageDialog(null, "Estimado usuario no existe la tabla de notas  PE22", "Informacion", JOptionPane.WARNING_MESSAGE);
                            } else {
                                listaMateriasArrastre = materiaDao.listaMateriasArrastre(h, this.especialidad.getIdEspecialidad(), this.semestre.getIdSemestre() - 1, cedulaBuscadaTxt.getText());
                                listaMaterias = materiaDao.listaMaterias(this.especialidad.getIdEspecialidad(), this.semestre.getIdSemestre());
                                if (!listaMateriasArrastre.isEmpty()) {
                                    if (JOptionPane.showConfirmDialog(this, "¿Estimado usuario el alumno tiene materias de arraste del anterior periodo "
                                            + "desea inscribirlo solo en las materias perdidas ", "Advertencia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                        valorMatricula = 1;//valor para solo materias arrastre
                                        matriculaBtn.setEnabled(true);
                                    } else {
                                        valorMatricula = 2;//valor para matricular todas las materias incluido los arrastres
                                        if (!listaMaterias.isEmpty()) {
                                            matriculaBtn.setEnabled(true);
                                            ocultaCampos();
                                            valorMatricula = 0;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No existe materias para realizar la matricula");
                                            limpiaCampos();
                                            ocultaCampos();
                                        }
                                    }
                                } else {
                                    if (!listaMaterias.isEmpty()) {
                                        matriculaBtn.setEnabled(true);
                                        valorMatricula = 0;//valor matricula normal...
                                        ocultaCampos();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No existe materias para realizar la matricula");
                                        limpiaCampos();
                                        ocultaCampos();
                                    }

                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "El alumno ya se encuentra matricula en el periodo actual", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        limpiaCampos();
                        ocultaCampos();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Estimado usuario no existe creado un periodo para matricular al alumno", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    limpiaCampos();
                    ocultaCampos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

    }

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

    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {
        try {
            Semestre seme = (Semestre) semestreCmb.getSelectedItem();
            this.semestre.setIdSemestre(seme.getIdSemestre());
            this.semestre.setSemestre(seme.getSemestre());
        } catch (Exception e) {
        }

    }

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {
        try {
            Especialidad espe = (Especialidad) especialidadCmb.getSelectedItem();
            this.especialidad.setIdEspecialidad(espe.getIdEspecialidad());
            this.especialidad.setEspecialidad(espe.getEspecialidad());
        } catch (Exception e) {
        }

    }

    private void paraleloCmdItemStateChanged(java.awt.event.ItemEvent evt) {

        try {
            Paralelo para = (Paralelo) paraleloCmd.getSelectedItem();
            this.paralelo.setIdParelelo(para.getIdParelelo());
            this.paralelo.setParalelo(para.getParalelo());
        } catch (Exception e) {
        }

    }

    private void matriculaBtnActionPerformed(java.awt.event.ActionEvent evt) {

        try {
            crud = new Crud();
            cargarCamposMatricula();
            crud.insertar("matricula", campos, Ingreso.getUsuario().getNombre());
            cargaListaMaterias();
            for (Map mapMateria : mapMaterias) {
                crud.insertarMaterias("nota", periodo.getCodigoPeriodo(), mapMateria, Ingreso.getUsuario().getNombre());
            }
            if (valorMatricula == 0) {
                cargaResumen();
                crud.insertarM("resumen", campos2, Ingreso.getUsuario().getNombre());
                limpiaCampos();
                cargarDatos();
            } else if (valorMatricula == 1) {
                JOptionPane.showMessageDialog(null, "Registos ingresados");
                limpiaCampos();
                cargarDatos();
            } else if (valorMatricula == 2) {
                cargaResumen();
                crud.insertarM("resumen", campos2, Ingreso.getUsuario().getNombre());
                limpiaCampos();
                cargarDatos();
            }

        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }
    }

    private void buscarMatriculaBtnActionPerformed(java.awt.event.ActionEvent evt) {   
        cargarDatos();
        if (matriculaTabla.getRowCount()==0) {
            JOptionPane.showMessageDialog(null,"No se encontro el alumno matriculado");
        }
    }

    private Map cargarCamposMatricula() {
        try {
            campos = new HashMap();
            campos.put("id_alumno", alumno.getIdAlumno());
            campos.put("cedula", alumno.getCedula());
            alumno.setNombreCompleto(nombresBuscadosTxt.getText());
            campos.put("nombre_completo", alumno.getNombreCompleto());
            if (valorMatricula == 0) {
                campos.put("tipo_matricula", Estado.ORDINARIA.name());
                campos.put("id_semestre", semestre.getIdSemestre());
            } else if (valorMatricula == 1) {
                campos.put("tipo_matricula", Estado.RELEGADA.name());
                campos.put("id_semestre", semestre.getIdSemestre() - 1);
            } else if (valorMatricula == 2) {
                campos.put("tipo_matricula", Estado.C_ARRASTRES.name());
                campos.put("id_semestre", semestre.getIdSemestre());
            }
            campos.put("fecha_creacion", cal.getTime());
            campos.put("id_periodo", periodo.getIdPeriodo());
            campos.put("id_especialidad", especialidad.getIdEspecialidad());
            campos.put("id_paralelo", paralelo.getIdParelelo());
            return campos;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }
        return null;
    }

    private List<Map> cargaListaMaterias() {
        try {
            mapMaterias = new ArrayList<>();
            matricula.setIdMatricula(matriculaDao.cargaIdMatricula());
            double numeroCreditos = 0;
            double numeroTeorica = 0;
            double ncem = 0;
            if (valorMatricula == 0) {
                for (Materia listaMateria : listaMaterias) {
                    numeroCreditos = numeroCreditos + listaMateria.getCreditos();
                    if (listaMateria.getNombreMateria().contains("EMPRESA")) {
                        numeroTeorica = numeroCreditos - listaMateria.getCreditos();
                        ncem = listaMateria.getCreditos();
                    }

                    campos1 = new HashMap();
                    campos1.put("cedula", alumno.getCedula());
                    campos1.put("fecha_ingreso", cal.getTime());
                    campos1.put("fecha_modificacion", cal.getTime());
                    campos1.put("tipo_nota", Estado.NORMAL.name());
                    campos1.put("id_alumno", alumno.getIdAlumno());
                    campos1.put("id_matricula", matricula.getIdMatricula());
                    campos1.put("id_materia", listaMateria.getIdMateria());
                    campos1.put("id_config_materia", listaMateria.getIdConfiguracionMateria());
                    campos1.put("id_configuracion", listaMateria.getIdConfiguracion());
                    campos1.put("id_desc_materia", listaMateria.getIdDescripcion());
                    campos1.put("id1_periodo", periodo.getIdPeriodo());
                    mapMaterias.add(campos1);
                }
            } else if (valorMatricula == 1) {
                for (Materia listaMateriaArras : listaMateriasArrastre) {
                    campos1 = new HashMap();
                    campos1.put("cedula", alumno.getCedula());
                    campos1.put("fecha_ingreso", cal.getTime());
                    campos1.put("fecha_modificacion", cal.getTime());
                    campos1.put("tipo_nota", Estado.ARRASTRE.name());
                    campos1.put("id_alumno", alumno.getIdAlumno());
                    campos1.put("id_matricula", matricula.getIdMatricula());
                    campos1.put("id_materia", listaMateriaArras.getIdMateria());
                    campos1.put("id_config_materia", listaMateriaArras.getIdConfiguracionMateria());
                    campos1.put("id_configuracion", listaMateriaArras.getIdConfiguracion());
                    campos1.put("id1_periodo", periodo.getIdPeriodo());
                    campos1.put("id_desc_materia", listaMateriaArras.getIdDescripcion());
                    mapMaterias.add(campos1);
                }
            } else if (valorMatricula == 2) {
                for (Materia listaMateria : listaMaterias) {
                    numeroCreditos = numeroCreditos + listaMateria.getCreditos();
                    if (listaMateria.getNombreMateria().contains("EMPRESA")) {
                        numeroTeorica = numeroCreditos - listaMateria.getCreditos();
                    }

                    campos1 = new HashMap();
                    campos1.put("cedula", alumno.getCedula());
                    campos1.put("fecha_ingreso", cal.getTime());
                    campos1.put("fecha_modificacion", cal.getTime());
                    campos1.put("tipo_nota", Estado.NORMAL.name());
                    campos1.put("id_alumno", alumno.getIdAlumno());
                    campos1.put("id_matricula", matricula.getIdMatricula());
                    campos1.put("id_materia", listaMateria.getIdMateria());
                    campos1.put("id_config_materia", listaMateria.getIdConfiguracionMateria());
                    campos1.put("id_configuracion", listaMateria.getIdConfiguracion());
                    campos1.put("id1_periodo", periodo.getIdPeriodo());
                    campos1.put("id_desc_materia", listaMateria.getIdDescripcion());
                    mapMaterias.add(campos1);
                }
                for (Materia listaMateriaArras : listaMateriasArrastre) {
                    campos1 = new HashMap();
                    campos1.put("cedula", alumno.getCedula());
                    campos1.put("fecha_ingreso", cal.getTime());
                    campos1.put("fecha_modificacion", cal.getTime());
                    campos1.put("tipo_nota", Estado.ARRASTRE.name());
                    campos1.put("id_alumno", alumno.getIdAlumno());
                    campos1.put("id_matricula", matricula.getIdMatricula());
                    campos1.put("id_materia", listaMateriaArras.getIdMateria());
                    campos1.put("id_config_materia", listaMateriaArras.getIdConfiguracionMateria());
                    campos1.put("id_configuracion", listaMateriaArras.getIdConfiguracion());
                    campos1.put("id1_periodo", periodo.getIdPeriodo());
                    campos1.put("id_desc_materia", listaMateriaArras.getIdDescripcion());
                    mapMaterias.add(campos1);
                }
            }

            double pnt = numeroTeorica / numeroCreditos;
            pnt = pnt * 100;
            double pnem = ncem / numeroCreditos;
            pnem = pnem * 100;
            resumen.setPorcentajeNotaEmpresa((int) pnem);
            resumen.setPorcentajeNotaTeorica((int) pnt);
            resumen.setNumeroCreditosCiclo((int) numeroCreditos);
            resumen.setNumeroCreditosTeorica((int) numeroTeorica);
            return mapMaterias;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }
        return mapMaterias;
    }

    private Map cargaResumen() {
        campos2 = new HashMap();
        campos2.put("id_alumno", alumno.getIdAlumno());
        campos2.put("cedula", alumno.getCedula());
        campos2.put("nombre_completo", nombresBuscadosTxt.getText());
        campos2.put("cred_ciclo", resumen.getNumeroCreditosCiclo());
        campos2.put("cred_teorica", resumen.getNumeroCreditosTeorica());
        campos2.put("porc_nota_teorica", resumen.getPorcentajeNotaTeorica());
        campos2.put("porc_nota_empresa", resumen.getPorcentajeNotaEmpresa());
        campos2.put("fecha_creacion", cal.getTime());
        campos2.put("fecha_modificacion", cal.getTime());
        campos2.put("id_semestre", semestre.getIdSemestre());
        campos2.put("id_especialidad", especialidad.getIdEspecialidad());
        campos2.put("id_periodo", periodo.getIdPeriodo());
        campos2.put("id_configuracion", listaMaterias.get(0).getIdConfiguracion());
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
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cedulaBuscadaTxt;
    private javax.swing.JButton certificadoBtn;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.JButton fichaBtn;
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
