/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.MetodosGeneralesDao;
import modelo.Especialidad;
import modelo.Periodo;
import modelo.Semestre;
import modeloReportes.NotaSemestre;
import logicaReportes.NotaSemestreDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteAlumno extends javax.swing.JInternalFrame {

    MetodosGeneralesDao metodosGeneralesDao;
    ResultSet resultSet;
    DefaultTableModel modelo;
    List<NotaSemestre> listaNotaSemestre;
    NotaSemestre notaSemestre;
    ResultSet resultSet1;
    Periodo periodo;
    Semestre semestre;
    Especialidad especialidad;
    int i = 0;
    private String file;

    public ReporteAlumno() {
        initComponents();
        metodosGeneralesDao = new MetodosGeneralesDao();
        periodo = new Periodo();
        semestre = new Semestre();
        especialidad = new Especialidad();
        cargaPeriodo();
        cargarEspecialidad();
        cargarSemestre();
        alumnoBuscadoTxt.setEnabled(false);
        reporteBtn.setEnabled(false);
        this.setLocation(310, 100);
    }

    private void cargarDatos(final String codigoPeriodo, final String cedula, final Integer idEspecialidad, final Integer idSemestre) {
        String[] colum = {"MATERIA", "PROMEDIO", "ESTADO", "CREDITOS", "TIPO NOTA"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, colum) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        this.notaSemestreTabla.setModel(modelo);
        notaSemestreTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        notaSemestre = new NotaSemestre();

        try {
            NotaSemestreDao dao = new NotaSemestreDao();
            resultSet1 = dao.cargarNotaSemestre(codigoPeriodo, cedula, idEspecialidad, idSemestre);
            while (resultSet1.next()) {
                notaSemestre.setNombreCompleto(resultSet1.getString("nombre_completo"));
                notaSemestre.setMateria(resultSet1.getString("materia"));
                notaSemestre.setPromedio(Double.valueOf(resultSet1.getString("promedio")));
                notaSemestre.setEstado(resultSet1.getString("estado_nota"));
                notaSemestre.setCreditos(Integer.valueOf(resultSet1.getString("creditos")));
                notaSemestre.setTipoNota(resultSet1.getString("tipo_nota"));
                alumnoBuscadoTxt.setText(notaSemestre.getNombreCompleto());
                modelo.insertRow(i, new Object[]{
                    notaSemestre.getMateria(),
                    notaSemestre.getPromedio(),
                    notaSemestre.getEstado(),
                    notaSemestre.getCreditos(),
                    notaSemestre.getTipoNota()
                });
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cedulaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        periodoCmb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        especialidadCmb = new javax.swing.JComboBox();
        semestreCmb = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        reporteBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        alumnoBuscadoTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        notaSemestreTabla = new javax.swing.JTable();

        setTitle("REPORTE DE NOTAS POR ALUMNO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Busqueda"));

        jLabel1.setText("Cedula");

        jLabel2.setText("Periodo");

        periodoCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        periodoCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                periodoCmbItemStateChanged(evt);
            }
        });

        jLabel3.setText("Especialidad");

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        especialidadCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                especialidadCmbItemStateChanged(evt);
            }
        });

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });

        jLabel4.setText("Semestre");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        reporteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Report.png"))); // NOI18N
        reporteBtn.setText("Reporte");
        reporteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteBtnActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Nombres :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)
                        .addComponent(reporteBtn)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(semestreCmb, 0, 205, Short.MAX_VALUE)
                                    .addComponent(especialidadCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(periodoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(alumnoBuscadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 241, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(periodoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(reporteBtn)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(alumnoBuscadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        notaSemestreTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(notaSemestreTabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validaDatos()) {
            cargarDatos(periodo.getCodigoPeriodo(), cedulaTxt.getText(), especialidad.getIdEspecialidad(), semestre.getIdSemestre());
            if (this.notaSemestreTabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No existe datos del alumno con los parametros ingresados");
                alumnoBuscadoTxt.setText(null);
            } else {
                reporteBtn.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos de busqueda Vacio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void periodoCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodoCmbItemStateChanged
        try {
            Periodo p = (Periodo) periodoCmb.getSelectedItem();
            periodo.setCodigoPeriodo(p.getCodigoPeriodo());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_periodoCmbItemStateChanged

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_especialidadCmbItemStateChanged
        try {
            Especialidad e = (Especialidad) especialidadCmb.getSelectedItem();
            especialidad.setIdEspecialidad(e.getIdEspecialidad());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_especialidadCmbItemStateChanged

    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        try {
            Semestre s = (Semestre) semestreCmb.getSelectedItem();
            semestre.setIdSemestre(s.getIdSemestre());
        } catch (Exception e) {
        }

    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void reporteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteBtnActionPerformed
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
                jr = (JasperReport) JRLoader.loadObjectFromFile("reportes/notaAlumno.jasper");
                String co = periodo.getCodigoPeriodo();
                int cop = Integer.valueOf(co.substring(2, 4));
                Map parametros = new HashMap();
                parametros.put("cedula", cedulaTxt.getText());
                parametros.put("idEspecialidad", String.valueOf(especialidad.getIdEspecialidad()));
                parametros.put("idSemestre", String.valueOf(semestre.getIdSemestre()));
                parametros.put("codigoPeriodo", cop);
                JasperPrint jp = JasperFillManager.fillReport(jr, parametros, cn);
                JasperExportManager.exportReportToPdfFile(jp, file);
                File archivo = new File(file);
                Desktop.getDesktop().open(archivo);
                //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
            }

        } catch (JRException | IOException ex) {
            System.out.println(ex);
             JOptionPane.showMessageDialog(null, "Error al cargar pdf", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_reporteBtnActionPerformed
    private boolean validaDatos() {
        boolean resul = true;
        if (cedulaTxt.getText().trim().isEmpty()) {
            resul = false;
        } else if (periodoCmb.getSelectedIndex() == 0) {
            resul = false;
        } else if (especialidadCmb.getSelectedIndex() == 0) {
            resul = false;
        } else if (semestreCmb.getSelectedIndex() == 0) {
            resul = false;
        }
        return resul;
    }

    private void cargaPeriodo() {
        try {
            resultSet = metodosGeneralesDao.cargaPeriodo();
            Periodo p;
            while (resultSet.next()) {
                p = new Periodo();
                p.setIdPeriodo(Integer.parseInt(resultSet.getString("id1_periodo")));
                p.setNombrePeriodo(resultSet.getString("periodo"));
                p.setCodigoPeriodo(resultSet.getString("id_periodo"));
                periodoCmb.addItem(p);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }

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
    private javax.swing.JTextField alumnoBuscadoTxt;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable notaSemestreTabla;
    private javax.swing.JComboBox periodoCmb;
    private javax.swing.JButton reporteBtn;
    private javax.swing.JComboBox semestreCmb;
    // End of variables declaration//GEN-END:variables
}
