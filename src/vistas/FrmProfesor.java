/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.ProfesorDao;
import modelo.Profesor;

/**
 *
 * @author USER
 */
public class FrmProfesor extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    List<Profesor> listaProfesor;
    ResultSet resultSet;
    Profesor profesor;
    public static  FormularioProfesor formularioProfesor;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idProfesor;

    public FrmProfesor() {
        initComponents();
        profesor = new Profesor();
        this.setLocation(250, 110);
        cargarDatos();
    }

    private void cargarDatos() {
        String[] col = {"PK", "CEDULA", "NOMBRES", "TITULO", "TIPO", "FUNCION"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        profesorTabla.setModel(modelo);
        profesorTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        profesorTabla.getColumnModel().getColumn(0).setMaxWidth(45);
        profesorTabla.getColumnModel().getColumn(0).setMinWidth(45);
        profesorTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(45);
        profesorTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(45);

        profesorTabla.getColumnModel().getColumn(1).setMaxWidth(90);
        profesorTabla.getColumnModel().getColumn(1).setMinWidth(90);
        profesorTabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(90);
        profesorTabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(90);

        profesorTabla.getColumnModel().getColumn(4).setMaxWidth(100);
        profesorTabla.getColumnModel().getColumn(4).setMinWidth(100);
        profesorTabla.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(100);
        profesorTabla.getTableHeader().getColumnModel().getColumn(4).setMinWidth(100);

        profesorTabla.getColumnModel().getColumn(5).setMaxWidth(100);
        profesorTabla.getColumnModel().getColumn(5).setMinWidth(100);
        profesorTabla.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(100);
        profesorTabla.getTableHeader().getColumnModel().getColumn(5).setMinWidth(100);

        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                if (listaProfesor == null) {
                    listaProfesor = new ArrayList<>();
                } else {
                    listaProfesor.clear();
                }
                try {
                    ProfesorDao profesorDao = new ProfesorDao();
                    resultSet = profesorDao.cargaProfesor();
                    while (resultSet.next()) {
                        profesor.setIdProfesor(Integer.valueOf(resultSet.getString("id1_profe")));
                        profesor.setCedula(resultSet.getString("ced_profe"));
                        profesor.setNombreProfesor(resultSet.getString("nom_profe"));
                        profesor.setApellidoProfesor(resultSet.getString("apell_profe"));
                        profesor.setTitulo(resultSet.getString("titulo"));
                        profesor.setFuncion(resultSet.getString("funcion"));
                        profesor.setTipoProfesor(resultSet.getString("tipo"));
                        if (filtroTxt.equals("") || profesor.getNombreProfesor()
                                .concat(" ")
                                .concat(profesor.getApellidoProfesor()).contains(filtroTxt.getText().trim())) {
                            listaProfesor.add(new Profesor(profesor.getIdProfesor(),
                                    profesor.getCedula(),
                                    profesor.getNombreProfesor(),
                                    profesor.getApellidoProfesor(),
                                    profesor.getTitulo(),
                                    profesor.getTipoProfesor(), profesor.getFuncion()));
                            modelo.insertRow(i, new Object[]{
                                profesor.getIdProfesor(),
                                profesor.getCedula(),
                                profesor.getNombreProfesor()
                                .concat(" ")
                                .concat(profesor.getApellidoProfesor()),
                                profesor.getTitulo(),
                                profesor.getTipoProfesor(),
                                profesor.getFuncion()
                            });
                        }
                    }
                } catch (SQLException | NumberFormatException e) {
                    System.out.println("Ocurrio un error al cargar");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        profesorTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        filtroTxt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("LISTADO DE PROFESORES");

        profesorTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        profesorTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profesorTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(profesorTabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        filtroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroTxtKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Busqueda por Nombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(jLabel1)
                                .addGap(26, 26, 26)
                                .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton1)))
                        .addGap(0, 308, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarDatos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void filtroTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroTxtKeyReleased
        convertiraMayusculasEnJtextfield(filtroTxt);
    }//GEN-LAST:event_filtroTxtKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        idProfesor=0;
        mostrarFormularioProfesor();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void profesorTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profesorTablaMouseClicked
        Integer i =profesorTabla.getSelectedRow();
        idProfesor=(Integer)profesorTabla.getValueAt(i,0);
        mostrarFormularioProfesor();
    }//GEN-LAST:event_profesorTablaMouseClicked
    public void convertiraMayusculasEnJtextfield(javax.swing.JTextField jTextfieldS) {
        String cadena = (jTextfieldS.getText()).toUpperCase();
        jTextfieldS.setText(cadena);
    }
     public void mostrarFormularioProfesor(){
        formularioProfesor = new FormularioProfesor(FrmProfesor.this, true,idProfesor);
        formularioProfesor.setLocationRelativeTo(FrmProfesor.this);
        formularioProfesor.setVisible(true);
        cargarDatos();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable profesorTabla;
    // End of variables declaration//GEN-END:variables
}
