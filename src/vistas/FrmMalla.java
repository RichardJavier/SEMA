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
import logica.MallaDao;
import modelo.Malla;

public class FrmMalla extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private DefaultTableModel modelo;
    private List<Malla> listaMalla;
    ResultSet resultSet;
    private Integer idMalla;
    private Malla malla;
    private MallaDao mallaDao;
    private static  FormularioMalla formularioMalla;
    public FrmMalla() {
        initComponents();
        this.setLocation(310,110);
        cargarDatos();
    }

    private void cargarDatos() {
        String[] culumnas = {"PK", "NOMBRE MALLA", "SEMESTRE"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, culumnas);
        modelo.setRowCount(0);
        this.mallaTabla.setModel(modelo);
        mallaTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        this.mensajeLbl.setText("PROCESANDO....");
        new Thread(new Runnable() {

            @Override
            public void run() {
                malla = new Malla();
                int i = 0;
                if (listaMalla == null) {
                    listaMalla = new ArrayList<>();
                } else {
                    listaMalla.clear();
                }
                try {
                    mallaDao = new MallaDao();
                    resultSet = mallaDao.consultaOrdenada();
                    while (resultSet.next()) {
                        malla.setIdMalla(Integer.parseInt(resultSet.getString("id_malla")));
                        malla.setNombreMalla(resultSet.getString("nombre_malla"));
                        malla.setSemestre(resultSet.getString("semestre"));
                        
                        modelo.insertRow(i++, new Object[]{
                            malla.getIdMalla(),
                            malla.getNombreMalla(),
                            malla.getSemestre()
                        });
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
       formularioMalla=new FormularioMalla(FrmMalla.this, true, idMalla);
       formularioMalla.setLocationRelativeTo(FrmMalla.this);
       formularioMalla.setVisible(true);
        cargarDatos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mallaTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        mensajeLbl = new javax.swing.JLabel();

        setClosable(true);

        mallaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        mallaTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mallaTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(mallaTabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mensajeLbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mensajeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(mensajeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            idMalla=0;
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmMalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mallaTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mallaTablaMouseClicked
        try {
            Integer i = mallaTabla.getSelectedRow();
            idMalla=(Integer)mallaTabla.getValueAt(i, 0);
            mostrarDialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmMalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mallaTablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mallaTabla;
    private javax.swing.JLabel mensajeLbl;
    // End of variables declaration//GEN-END:variables
}
