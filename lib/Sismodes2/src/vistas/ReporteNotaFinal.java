package vistas;

import conectar.Conexion;
import controles.DatosResumenControl;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import metodos.Proceso;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import tablas.ReporteNota;

public final class ReporteNotaFinal extends javax.swing.JInternalFrame {

    private List<ReporteNota> listaResumen;
    private DefaultTableModel modelo;
    private final DatosResumenControl resumenControl;
    private String mcedula, nombres;
    private int idespecialidad;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private String file;

    public ReporteNotaFinal() {
        initComponents();
        resumenControl = new DatosResumenControl();
        nombresTxt.setEnabled(false);
        reporteBtn.setEnabled(false);
        cargarBtn.setEnabled(false);
        cargarEspecialidad();

    }

    private void cargarDatos(final String rcedula, final int especialidad) {
        String[] campos = {"Promedio", "% NOTA", "NOTA TUTORIA", "% TUTORIA", "NOTA FINAL", "ESTADO", "ASISTENCIA"};
        String[][] data = {{" ", " ", " "}};
        modelo = new DefaultTableModel(data, campos);
        modelo.setRowCount(0);
        this.reporteNotaTabla.setModel(modelo);
        this.reporteNotaTabla.setRowSorter(new TableRowSorter<TableModel>(modelo));
        new Thread(new Runnable() {
            @Override
            public void run() {
                double nota;
                double porNota;
                double notaTuto;
                double porTutoria;
                double notaFinal;
                String estado;
                String asistencia;

                int i = 0;
                if (listaResumen == null) {
                    listaResumen = new ArrayList<>();
                } else {
                    listaResumen.clear();
                }
                try {
                    ResultSet rs = resumenControl.listaReporte(rcedula, especialidad);
                    while (rs.next()) {
                        nota = Double.valueOf(rs.getString("pro_ponderado_nota"));
                        porNota = Double.valueOf(rs.getString("porcentaje_nota"));
                        notaTuto = Double.valueOf(rs.getString("nota_tutoria"));
                        porTutoria = Double.valueOf(rs.getString("porcentaje_tutoria"));
                        notaFinal = Double.valueOf(rs.getString("nota_final"));
                        estado = rs.getString("estado");
                        asistencia = rs.getString("asistencia");
                        modelo.insertRow(i++, new Object[]{
                            nota,
                            porNota,
                            notaTuto,
                            porTutoria,
                            notaFinal,
                            estado,
                            asistencia
                        });
                    }
                } catch (SQLException | NumberFormatException e) {
                    Logger.getLogger(ReporteNotaFinal.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, "Error al cargar la Lista", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al cargar la Lista", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cedulaTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reporteNotaTabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        especialidadCmb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        nombresTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        reporteBtn = new javax.swing.JButton();
        cargarBtn = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setText("Cedula");

        reporteNotaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(reporteNotaTabla);

        jLabel2.setText("Especialidad");

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));

        jLabel3.setText("Nombres");

        buscarBtn.setText("Buscar Estudiante");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        reporteBtn.setText("Generar Reporte");
        reporteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteBtnActionPerformed(evt);
            }
        });

        cargarBtn.setText("Cargar Datos");
        cargarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nombresTxt))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(94, 94, 94)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(buscarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reporteBtn)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(cargarBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarBtn)
                    .addComponent(reporteBtn)
                    .addComponent(cargarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        if (cedulaTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo cedula vacio", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            mcedula = cedulaTxt.getText();
            nombresTxt.setText(resumenControl.buscarEstudiante(mcedula));
            if (nombresTxt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"No se encontro al alumno");
                return;
            }
            cargarBtn.setEnabled(true);
        }
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void reporteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteBtnActionPerformed
        try {
            List lista = new ArrayList<>();
            int filas = reporteNotaTabla.getRowCount();
            if (filas < 0) {
                JOptionPane.showMessageDialog(null, "Error tabla vacia para generar reporte", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                for (int i = 0; i < filas; i++) {
                    ReporteNota reporteNota = new ReporteNota(reporteNotaTabla.getValueAt(i, 0).toString(),
                            reporteNotaTabla.getValueAt(i, 1).toString(),
                            reporteNotaTabla.getValueAt(i, 2).toString(),
                            reporteNotaTabla.getValueAt(i, 3).toString(),
                            reporteNotaTabla.getValueAt(i, 4).toString(),
                            reporteNotaTabla.getValueAt(i, 5).toString(),
                            reporteNotaTabla.getValueAt(i, 6).toString());
                    lista.add(reporteNota);
                }
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Pdf", "pdf");
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Guadar Archivo");
                if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile().toString().concat(".pdf");
                    JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes/reporteNota.jasper");
                    HashMap parametros = new HashMap();
                    parametros.put("cedula", cedulaTxt.getText());
                    parametros.put("especialidad", especialidadCmb.getSelectedItem().toString());
                    parametros.put("nombres", nombresTxt.getText());

                    new Proceso().cargarJDialog();
                    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, dataSource);
                    JasperExportManager.exportReportToPdfFile(jasperPrint, file);
                    new Proceso().cargarJDialog();
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
                }
            }

        } catch (HeadlessException | JRException | IOException e) {
            JOptionPane.showMessageDialog(null, "Ocurrior un error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("" + e);
        }
    }//GEN-LAST:event_reporteBtnActionPerformed

    private void cargarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarBtnActionPerformed
        if (especialidadCmb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe escojer una especialidad", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            idespecialidad = especialidadCmb.getSelectedIndex();
            cargarDatos(mcedula, idespecialidad);
            reporteBtn.setEnabled(true);
        }
    }//GEN-LAST:event_cargarBtnActionPerformed
    private void cargarEspecialidad() {
        String sql = "select * from especialidad";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                especialidadCmb.addItem(rs.getString("especialidad"));
            }
        } catch (Exception e) {
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cargarBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JButton reporteBtn;
    private javax.swing.JTable reporteNotaTabla;
    // End of variables declaration//GEN-END:variables
}
