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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.MateriaDao;
import modelo.Materia;

/**
 *
 * @author andre_000
 */
public class FrmMateria extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    private static FormularioMateria formularioMateria;
    private Integer idMateria;
    Materia materia;
    private List<Materia> listaMateria;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    ResultSet resultSet;
    MateriaDao materiaDao;

    public FrmMateria() {
        initComponents();
        this.setLocation(310, 110);
        cargarDatos();
    }

    private void cargarDatos() {
        String[] col = {"PK", "NOMBRE MATERIA", "ESPECIALIDAD", "SEMESTRE"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col){
        @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
        };
        modelo.setRowCount(0);
        this.materiaTabla.setModel(modelo);
        materiaTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        materiaTabla.getColumnModel().getColumn(0).setMaxWidth(45);
        materiaTabla.getColumnModel().getColumn(0).setMinWidth(45);
        materiaTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(45);
        materiaTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(45);
        new Thread(new Runnable() {

            @Override
            public void run() {
                materia = new Materia();
                int i = 0;
                if (listaMateria == null) {
                    listaMateria = new ArrayList<>();
                } else {
                    listaMateria.clear();
                }
                try {
                    materiaDao = new MateriaDao();
                    resultSet = materiaDao.consultaOrdenada();
                    while (resultSet.next()) {
                        materia.setIdMateria(Integer.parseInt(resultSet.getString("id1_nombre_materia")));
                        materia.setNombreMateria(resultSet.getString("materia"));
                        materia.setSemestre(resultSet.getString("semestre"));
                        materia.setEspecialidad(resultSet.getString("especialidad"));
                        if (filtroTxt.equals("") || materia.getNombreMateria().contains(filtroTxt.getText().toUpperCase())) {
                            listaMateria.add(new Materia(materia.getIdMateria(),
                                    materia.getNombreMateria(),
                                    materia.getSemestre(),
                                    materia.getEspecialidad()));
                            modelo.insertRow(i, new Object[]{
                                materia.getIdMateria(),
                                materia.getNombreMateria(),
                                materia.getSemestre(),
                                materia.getEspecialidad()
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

    public void mostrarDialog() throws SQLException, ParseException {
        formularioMateria = new FormularioMateria(FrmMateria.this, true, idMateria);
        formularioMateria.setLocationRelativeTo(FrmMateria.this);
        formularioMateria.setVisible(true);
        cargarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        materiaTabla = new javax.swing.JTable();
        insertarBtn = new javax.swing.JButton();
        filtroTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("LISTADO DE MATERIAS");

        materiaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        materiaTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                materiaTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(materiaTabla);

        insertarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        insertarBtn.setText("Insertar");
        insertarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarBtnActionPerformed(evt);
            }
        });

        filtroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroTxtKeyReleased(evt);
            }
        });

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(insertarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(buscarBtn)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(insertarBtn)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBtnActionPerformed
        try {
            idMateria = 0;
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_insertarBtnActionPerformed

    private void materiaTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_materiaTablaMouseClicked
        Integer i = materiaTabla.getSelectedRow();
        idMateria = (Integer) materiaTabla.getValueAt(i, 0);
        try {
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_materiaTablaMouseClicked

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        cargarDatos();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void filtroTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroTxtKeyReleased
        convertiraMayusculasEnJtextfield(filtroTxt);
    }//GEN-LAST:event_filtroTxtKeyReleased
    public void convertiraMayusculasEnJtextfield(javax.swing.JTextField jTextfieldS) {
        String cadena = (jTextfieldS.getText()).toUpperCase();
        jTextfieldS.setText(cadena);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton insertarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable materiaTabla;
    // End of variables declaration//GEN-END:variables
}
