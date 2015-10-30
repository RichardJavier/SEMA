/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosProfesorControl;
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
import tablas.DatosProfesor;



public final class FrmProfesor extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Double id1profe;
    private  DefaultTableModel modelo;
    public static FormularioDocente formularioDocente;
    private List<DatosProfesor> listaProfesor;
    

    public FrmProfesor() {
        super();
        initComponents();
        mostrarDatos();
    }

    public void mostrarJdialog() throws SQLException, ParseException, NullPointerException {

        formularioDocente = new FormularioDocente(FrmProfesor.this, true, id1profe);
        formularioDocente.setLocationRelativeTo(FrmProfesor.this);
        formularioDocente.setVisible(true);
        mostrarDatos();
    }

   public synchronized void mostrarDatos() {
        String[] col = {"CEDULA", "APELLIDO", "NOMBRE", "FECHA NACIMIENTO", "FECHA DE INGRESO", "TITULO", "ASIGNACION", "FUNCION", "pk"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        tablaProfesores.setModel(modelo);
        //ocultamos la clave primaria
        tablaProfesores.getColumnModel().getColumn(8).setMaxWidth(0);
        tablaProfesores.getColumnModel().getColumn(8).setMinWidth(0);
        tablaProfesores.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        tablaProfesores.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        // que se pueda organizar por columnas
      
        // definir la dimension de columna fechanacimiento
        tablaProfesores.getColumnModel().getColumn(3).setMaxWidth(130);
        tablaProfesores.getColumnModel().getColumn(3).setMinWidth(130);
        tablaProfesores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(130);
        tablaProfesores.getTableHeader().getColumnModel().getColumn(3).setMinWidth(130);

        //definir la dimension de columna fecha ingreso
        tablaProfesores.getColumnModel().getColumn(4).setMaxWidth(130);
        tablaProfesores.getColumnModel().getColumn(4).setMinWidth(130);
        tablaProfesores.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(130);
        tablaProfesores.getTableHeader().getColumnModel().getColumn(4).setMinWidth(130);
        
        
        tablaProfesores.setRowSorter(new TableRowSorter<TableModel>(modelo));

        mensaje.setText("ESPERE........");
        //inicio del hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                String cedProfe ;
                String nomProfe ;
                String apellProfe;
                Date fechanacimiento = null ;
                Date fechaIngreso = null;
                String titulo;
                String asignacion ;
                String funcion ;
               
                int i = 0;
                if (listaProfesor == null) {
                    listaProfesor = new ArrayList<>();
                } else {
                    listaProfesor.clear();
                }
                try {
                    DatosProfesorControl dp = new DatosProfesorControl();
                    ResultSet rs = dp.consultaOrdenada(0.0);
                    while (rs.next()) {
                         Thread.sleep(10);
                        cedProfe = rs.getString("ced_profe");
                        apellProfe = rs.getString("apell_profe");
                        nomProfe = rs.getString("nom_profe");
                        fechanacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fnac_profe"));
                        fechaIngreso = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fec_ing"));
                        titulo = rs.getString("titulo");
                        asignacion = rs.getString("tipo");
                        funcion = rs.getString("funcion");
                        
                        if (filtrotxt.equals("")||cedProfe.contains(filtrotxt.getText())) {
                            listaProfesor.add(new DatosProfesor(cedProfe,
                                    apellProfe,
                                    nomProfe,
                                    fechanacimiento,
                                    fechaIngreso,
                                    titulo,
                                    asignacion,
                                    funcion,
                                    Double.parseDouble(rs.getString("id1_profe").toString())));
                            modelo.insertRow(i++, new Object[]{
                            cedProfe,
                            apellProfe,
                            nomProfe,
                            new SimpleDateFormat("dd/MM/yyyy").format(fechanacimiento),
                            new SimpleDateFormat("dd/MM/yyyy").format(fechaIngreso),
                            titulo,
                            asignacion,
                            funcion,
                            rs.getObject("id1_profe").toString()
                        });
                           
                        }
                    }
                } catch (SQLException | ParseException | NullPointerException | InterruptedException ex) {
                    Logger.getLogger(FrmProfesor.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                    }
                }
                mensaje.setText("TERMINADO......");
            }
        }).start();
                  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProfesores = new javax.swing.JTable();
        mensaje = new javax.swing.JLabel();
        filtrotxt = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Formulario de Docentes ");
        setToolTipText("");
        setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/business_user_add.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProfesores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProfesoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProfesores);

        mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mensaje.setText("Buscar por CI");

        buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filtrotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mensaje)
                    .addComponent(filtrotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar))
                .addGap(67, 67, 67)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            id1profe = 0.0;
            mostrarJdialog();
        } catch (SQLException | ParseException | NullPointerException ex) {
            Logger.getLogger(FrmProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaProfesoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesoresMouseClicked
        double i = tablaProfesores.getSelectedRow();
        int y = (int)Math.round(i);
        id1profe = Double.parseDouble((String) tablaProfesores.getValueAt(y,8));
        try {
            mostrarJdialog();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablaProfesoresMouseClicked

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
       
            mostrarDatos();
        
    }//GEN-LAST:event_buscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JTextField filtrotxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTable tablaProfesores;
    // End of variables declaration//GEN-END:variables
}
