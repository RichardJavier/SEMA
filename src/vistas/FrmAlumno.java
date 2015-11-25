package vistas;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import logica.AlumnoDao;
import modelo.Alumno;

public final class FrmAlumno extends javax.swing.JInternalFrame {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    DefaultTableModel modelo;
    Alumno alumno;
    List<Alumno> listaAlumno;
    AlumnoDao alumnoDao;
    ResultSet resultSet;
   public static  FormularioAlumno formularioAlumno;
   private Integer idAlumno;
    public FrmAlumno() {
        initComponents();
        this.setLocation(310, 110);
        cargaDatos();
    }
    
    public void cargaDatos() {
        String[] columnas = {"PK", "CEDULA", "NOMBRE COMPLETOS", "SEXO", "FECHA NACIMIENTO"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, columnas){
        @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
        };
        modelo.setRowCount(0);
        this.alumnoTabla.setModel(modelo);
        alumnoTabla.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
        alumnoTabla.getColumnModel().getColumn(0).setMaxWidth(45);
        alumnoTabla.getColumnModel().getColumn(0).setMinWidth(45);
        alumnoTabla.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(45);
        alumnoTabla.getTableHeader().getColumnModel().getColumn(0).setMinWidth(45);

        alumnoTabla.getColumnModel().getColumn(1).setMaxWidth(120);
        alumnoTabla.getColumnModel().getColumn(1).setMinWidth(120);
        alumnoTabla.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(120);
        alumnoTabla.getTableHeader().getColumnModel().getColumn(1).setMinWidth(120);
        
        alumnoTabla.getColumnModel().getColumn(2).setMaxWidth(150);
        alumnoTabla.getColumnModel().getColumn(2).setMinWidth(150);
        alumnoTabla.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(250);
        alumnoTabla.getTableHeader().getColumnModel().getColumn(2).setMinWidth(250);
          

        new Thread(new Runnable() {
            @Override
            @SuppressWarnings("IncompatibleEquals")
            public void run() {
                alumno = new Alumno();
                int i = 0;
                if (listaAlumno == null) {
                    listaAlumno = new ArrayList<>();
                } else {
                    listaAlumno.clear();
                }
                try {
                    alumnoDao = new AlumnoDao();
                    resultSet = alumnoDao.consultaOrdenada();
                    while (resultSet.next()) {
                        alumno.setIdAlumno(Integer.parseInt(resultSet.getString("id_alumno")));
                        alumno.setCedula(resultSet.getString("cedula"));
                        alumno.setApellidoPaterno(resultSet.getString("apellido_paterno"));
                        alumno.setApellidoMaterno(resultSet.getString("apellido_materno"));
                        alumno.setNombreCompleto(resultSet.getString("nombre_completo"));
                        alumno.setSexo(resultSet.getString("sexo"));
                        alumno.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fecha_nacimiento")));
                        if (filtroTxt.equals("") || alumno.getCedula().contains(filtroTxt.getText())) {
                            listaAlumno.add(new Alumno(alumno.getIdAlumno(),
                                    alumno.getCedula(),
                                    alumno.getApellidoPaterno(),
                                    alumno.getApellidoMaterno(),
                                    alumno.getNombreCompleto(),
                                    alumno.getSexo(),
                                    alumno.getFechaNacimiento()));
                            modelo.insertRow(i++, new Object[]{
                                alumno.getIdAlumno(),
                                alumno.getCedula(),
                                alumno.getApellidoPaterno().concat(" ").
                                concat(alumno.getApellidoMaterno()).concat(" ").
                                concat(alumno.getNombreCompleto()),
                                alumno.getSexo(),
                                new SimpleDateFormat("yyyy-MM-dd").format(alumno.getFechaNacimiento())

                            });
                        }

                    }
                } catch (SQLException | NumberFormatException | ParseException e) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        alumnoTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        filtroTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("LISTADO DE ALUMNOS INSCRITOS");

        alumnoTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        alumnoTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alumnoTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(alumnoTabla);

        jLabel1.setText("Búsqueda  por Cedula");

        buscarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/search.png"))); // NOI18N
        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(372, 372, 372)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buscarBtn))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(jButton1)
                            .addGap(477, 477, 477)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        cargaDatos();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            idAlumno=0;
            mostrarDialog();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void alumnoTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alumnoTablaMouseClicked
     Integer i=alumnoTabla.getSelectedRow();
     idAlumno=(Integer)alumnoTabla.getValueAt(i, 0);
        try {
            mostrarDialog();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_alumnoTablaMouseClicked
   public void mostrarDialog(){
    formularioAlumno=new FormularioAlumno(FrmAlumno.this,true,idAlumno);
    formularioAlumno.setLocationRelativeTo(FrmAlumno.this);
    formularioAlumno.setVisible(true);
    cargaDatos();
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable alumnoTabla;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
