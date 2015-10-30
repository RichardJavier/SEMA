/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import controles.DatosAlumnoControl;
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
import tablas.DatosAlumno;

/**
 *
 * @author Toshiba
 */
public class FrmAlumno extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idAlumno;
    private DefaultTableModel modelo;
    private List<DatosAlumno> listaAlumnos;
    public static FormularioAlumno formularioAlumno;
    private String codigo;
    ResultSet rs;
    public FrmAlumno() {
        super();
        initComponents();
        mostrarDatos();

    }

    public void mostrarDatos() {
        String[] col = {"PK", "CEDULA", "NOMBRE COMPLETOS", "FECHA NACIMIENTO", "SEXO", "DIRECCION", "FECHA DE INSCRIPCION"};
        String[][] data = {{" ", " ", " ",}};
        modelo = new DefaultTableModel(data, col);
        modelo.setRowCount(0);
        tablaAlumnos.setModel(modelo);
        //tablaAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
        //tablaAlumnos.getColumnModel().getColumn(0).setMinWidth(0);
        //tablaAlumnos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        //tablaAlumnos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tablaAlumnos.setRowSorter(new TableRowSorter<TableModel>(modelo));
        mensaje.setText("ESPERE.....");

        new Thread(new Runnable() {
            @Override
            public void run() {
                String cedula;
                String nombres;
                Date fechaNacimiento = null;
                String sexo;
                String direccion;
                Date fechaIgreso = null;
                int i = 0;
                if (listaAlumnos == null) {
                    listaAlumnos = new ArrayList<>();
                } else {
                    listaAlumnos.clear();
                }

                try {
                    DatosAlumnoControl d = new DatosAlumnoControl();
                     rs = d.llenarTabla(0);
                    while (rs.next()) {
                        Thread.sleep(10);
                        cedula = rs.getString("cedula");
                        nombres = rs.getString("nom_completos");
                        fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fecha_nacimiento"));
                        sexo = rs.getString("sexo");
                        direccion = rs.getString("direccion");
                        fechaIgreso = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fecha_inscripcion"));
                        modelo.insertRow(i++, new Object[]{
                            rs.getString("id_alumno"),
                            cedula,
                            nombres,
                            new SimpleDateFormat("dd/MM/yyyy").format(fechaNacimiento),
                            sexo,
                            direccion,
                            new SimpleDateFormat("dd/MM/yyyy").format(fechaIgreso)
                        });
                    }
                } catch (SQLException | InterruptedException | ParseException | NullPointerException ex) {
                    Logger.getLogger(FrmAlumno.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        cc.desconectar();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                mensaje.setText("Terminado....");
            }
        }).start();

    }
    /*
     public String codigo(){
     try {
     String sql ="SELECT * FROM periodo_semestre WHERE matricula ='1' LIMIT 0,1 ";
     Statement st = cn.createStatement();
     ResultSet rs = st.executeQuery(sql);
     while(rs.next()){
     codigo  = rs.getString("id_periodo");
     }
     if (codigo==null) {
     JOptionPane.showMessageDialog(null,"No existe ningun periodo activo para matricular","Error",JOptionPane.ERROR_MESSAGE);
     }
               
     return codigo;
     } catch (Exception e) {
     }
     System.out.println(codigo);
     return  codigo;
     } 
     */

    private void mostrarFormularioAlumno() throws SQLException {
        formularioAlumno = new FormularioAlumno(FrmAlumno.this, true, idAlumno);
        formularioAlumno.setLocationRelativeTo(FrmAlumno.this);
        formularioAlumno.setVisible(true);
        mostrarDatos();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        mensaje = new javax.swing.JLabel();
        btningresar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Formulario de Alumnos ");

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaAlumnos.setAutoscrolls(false);
        tablaAlumnos.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(tablaAlumnos);

        mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btningresar.setText("Insertar");
        btningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btningresar)))
                .addContainerGap(327, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btningresar)
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
        idAlumno = 0;
        try {
            mostrarFormularioAlumno();

        } catch (SQLException ex) {
            Logger.getLogger(FrmAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btningresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btningresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTable tablaAlumnos;
    // End of variables declaration//GEN-END:variables
}
