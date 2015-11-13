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
import logica.ConfiguracionDao;
import modelo.Configuracion;

public class FrmConfiguracion extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private DefaultTableModel modelo;
    private List<Configuracion> listaConfiguracion;
    ResultSet resultSet;
    private Integer idConfiguracion;
    private Configuracion configuracion;
    private ConfiguracionDao configuracionDao;
    private static FormularioConfiguracion formularioConfiguracion;

    public FrmConfiguracion() {
        initComponents();
        this.setLocation(310, 110);
        cargarDatos();
    }

    private void cargarDatos() {
        String[] culumnas = {"PK", "DESCRIPCION","ESTADO"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, culumnas){
        @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
        };
        modelo.setRowCount(0);
        this.configuracionTabla.setModel(modelo);
        configuracionTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        this.mensajeLbl.setText("PROCESANDO....");
        new Thread(new Runnable() {

            @Override
            public void run() {
                configuracion = new Configuracion();
                int i = 0;
                if (listaConfiguracion == null) {
                    listaConfiguracion = new ArrayList<>();
                } else {
                    listaConfiguracion.clear();
                }
                try {
                    configuracionDao = new ConfiguracionDao();
                    resultSet = configuracionDao.consultaOrdenada();
                    while (resultSet.next()) {
                        configuracion.setIdConfiguracion(Integer.parseInt(resultSet.getString("id_configuracion")));
                        configuracion.setDescripcion(resultSet.getString("descripcion"));
                        configuracion.setEstado(resultSet.getString("estado"));
                        if (filtroTxt.equals("") || configuracion.getDescripcion().contains(filtroTxt.getText())) {
                            listaConfiguracion.add(new Configuracion(configuracion.getIdConfiguracion(), configuracion.getDescripcion(),configuracion.getEstado()));
                            modelo.insertRow(i++, new Object[]{
                                configuracion.getIdConfiguracion(),
                                configuracion.getDescripcion(),
                                configuracion.getEstado()
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
                mensajeLbl.setText("TERMINADO.....");
            }
        }).start();
    }

    public void mostrarDialog() throws SQLException, ParseException {
        formularioConfiguracion = new FormularioConfiguracion(FrmConfiguracion.this, true, idConfiguracion);
        formularioConfiguracion.setLocationRelativeTo(FrmConfiguracion.this);
        formularioConfiguracion.setVisible(true);
        cargarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        configuracionTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        mensajeLbl = new javax.swing.JLabel();
        filtroTxt = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("LISTADO DE CONFIGURACION");

        configuracionTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        configuracionTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                configuracionTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(configuracionTabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mensajeLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        filtroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroTxtKeyReleased(evt);
            }
        });

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Busqueda Por Nombre Malla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mensajeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Buscar)))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensajeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            idConfiguracion = 0;
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void configuracionTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionTablaMouseClicked
        try {
            Integer i = configuracionTabla.getSelectedRow();
            idConfiguracion = (Integer) configuracionTabla.getValueAt(i, 0);
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_configuracionTablaMouseClicked

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        cargarDatos();
    }//GEN-LAST:event_BuscarActionPerformed

    private void filtroTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroTxtKeyReleased
        convertiraMayusculasEnJtextfield(filtroTxt);
    }//GEN-LAST:event_filtroTxtKeyReleased
    public void convertiraMayusculasEnJtextfield(javax.swing.JTextField jTextfieldS) {
        String cadena = (jTextfieldS.getText()).toUpperCase();
        jTextfieldS.setText(cadena);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JTable configuracionTabla;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mensajeLbl;
    // End of variables declaration//GEN-END:variables
}
