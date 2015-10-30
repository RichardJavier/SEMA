/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosMallaControl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tablas.Malla;

/**
 *
 * @author Toshiba
 */
public final class FrmMalla extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private DefaultTableModel modelo;
    private List<Malla> listaMallas;
    private DatosMallaControl dmc;
    private ResultSet rs;
    public static FormularioMalla formularioMalla;
    private int idmalla;

    public FrmMalla() {
        initComponents();
        mostrarDatos();
    }

    public void mostrarDatos() {
        String[] campos = {"ID", "NOMBRE MALLA", "SEMESTRE", "ESPECIALIDAD", 
                           "PERIODO", "VALOR NOTA", "VALOR TUTORIA", 
                           "NUMERO HORAS", "PORCENTAJE", "FECHA CREACION", "ESTADO"};
        String[][] data = {{" ", " ", " "}};
        modelo = new DefaultTableModel(data, campos);
        modelo.setRowCount(0);
        mallaTabla.setModel(modelo);
        mallaTabla.setRowSorter(new TableRowSorter<TableModel>(modelo));

        new Thread(new Runnable() {

            @Override
            public void run() {
                String nombreMalla;
                String semestre;
                String especialidad;
                String periodo;
                String ciclo;
                String porNOta;
                String porTuto;
                String numHoras;
                String porcentaje;
                Date fechaCreacion = null;
                String estado;
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                int i = 0;
                if (listaMallas == null) {
                    listaMallas = new ArrayList<>();
                } else {
                    listaMallas.clear();
                }
                try {
                    dmc = new DatosMallaControl();
                    rs = dmc.consultaOrdenada();
                    while (rs.next()) {
                        Thread.sleep(5);
                        nombreMalla = rs.getString("nombre_malla");
                        semestre = rs.getString("semestre");
                        especialidad = rs.getString("especialidad");
                        periodo = rs.getString("periodo");
                        porNOta = rs.getString("por_nota").concat("%");
                        porTuto = rs.getString("por_tuto").concat("%");
                        numHoras = rs.getString("num_hora");
                        porcentaje = rs.getString("porcentaje").concat("%");
                        fechaCreacion = formato.parse(rs.getString("fecha_creacion"));
                        estado = rs.getString("estado");
                        if (estado.compareTo("A") == 0) {
                            estado = "ACTIVADO";
                        } else {
                        }
                        modelo.insertRow(i, new Object[]{
                            rs.getString("id_malla"),
                            nombreMalla,
                            semestre,
                            especialidad,
                            periodo,
                            porNOta,
                            porTuto,
                            numHoras,
                            porcentaje,
                            formato.format(fechaCreacion),
                            estado
                        });
                    }

                } catch (SQLException | InterruptedException | ParseException e) {
                    Logger.getLogger(FrmMalla.class.getName()).log(Level.SEVERE, null, e);
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
    private void mostrarFormularioMalla() throws SQLException{
    formularioMalla=new FormularioMalla(FrmMalla.this,true,idmalla);
    formularioMalla.setLocationRelativeTo(FrmMalla.this);
    formularioMalla.setVisible(true);
    mostrarDatos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mallaTabla = new javax.swing.JTable();
        ingresarBtn = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();

        setClosable(true);

        mallaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        mallaTabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(mallaTabla);

        ingresarBtn.setText("Ingresar");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });

        buscarBtn.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(ingresarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(ingresarBtn)
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
       idmalla=0;
        try {
            mostrarFormularioMalla();
        } catch (SQLException ex) {
            Logger.getLogger(FrmMalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ingresarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable mallaTabla;
    // End of variables declaration//GEN-END:variables
}
