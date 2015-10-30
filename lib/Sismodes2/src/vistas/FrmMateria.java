
package vistas;

import conectar.Conexion;
import controles.DatosMateriaControl;
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
import tablas.DatosMateria;


public final class FrmMateria extends javax.swing.JInternalFrame {
  Conexion cc = Conexion.getInstance();
  Connection cn = cc.Conectar();
  private Double idmateria;
  private DefaultTableModel modelo;
  public static  FormularioMateria formularioMateria;
  private List<DatosMateria>listaMateria;
 
    public FrmMateria() {
        super();
        initComponents();
        tablaMateria.setEnabled(false);
        mostraDatos();
    }

   public synchronized void mostraDatos(){
    String []col = {"Pk","NOMBRE MATERIA","CREDITOS","ACTIVACION","SEMESTRE","ESPECIALIDAD","EJE","CICLO","FECHA CREACION","PROFESOR"};
    String [][]data={{"","",""}};
    modelo=new DefaultTableModel(data,col);
    modelo.setRowCount(0);
    this.tablaMateria.setModel(this.modelo);
    //definir dimension de columna
    
     tablaMateria.getColumnModel().getColumn(0).setMaxWidth(0);
     tablaMateria.getColumnModel().getColumn(0).setMinWidth(0);
     tablaMateria.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
     tablaMateria.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
  
     //materia dimension de columna en modelo de tabla 
     tablaMateria.getColumnModel().getColumn(2).setMaxWidth(90);
     tablaMateria.getColumnModel().getColumn(2).setMinWidth(90);
     tablaMateria.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(90);
     tablaMateria.getTableHeader().getColumnModel().getColumn(2).setMinWidth(90);
     
     //definir la columna de creditos en el modelo 
     tablaMateria.getColumnModel().getColumn(3).setMaxWidth(90);
     tablaMateria.getColumnModel().getColumn(3).setMinWidth(90);
     tablaMateria.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(90);
     tablaMateria.getTableHeader().getColumnModel().getColumn(3).setMinWidth(90);
     
//columna de activacion  en el modelo
//     tablaMateria.getColumnModel().getColumn(4).setMaxWidth(30);
//     tablaMateria.getColumnModel().getColumn(4).setMinWidth(30);
//     tablaMateria.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(30);
//     tablaMateria.getTableHeader().getColumnModel().getColumn(4).setMinWidth(30);
     tablaMateria.setRowSorter(new TableRowSorter<TableModel>(this.modelo));
     this.mensaje.setText("ESPERE......");
    insertaBtn.setEnabled(false);
    tablaMateria.setEnabled(false);
    //inicio del hilo 
    new Thread(new Runnable() {
        @Override
        public void run() {
            String materia;
            String activacion ;
            String semestre ;
            String especialidad ;
            String eje ;
            String ciclo ;
            Date fechaCreacion = null;
            String profesor ;
            int i = 0;
            if (listaMateria == null) {
                listaMateria = new ArrayList<>();
            } else {
                listaMateria.clear();
            }
            try {
                DatosMateriaControl materiaControl = new DatosMateriaControl();
                ResultSet rs = materiaControl.consultaOrdenada(idmateria);
                while (rs.next()) {
                Thread.sleep(3);
                materia=rs.getString("m.materia");
                activacion=rs.getString("activa_mat");
                semestre=rs.getString("semestre");
                especialidad=rs.getString("especialidad");
                eje= rs.getString("nom_ejes");
                ciclo= rs.getString("c.ciclo");
                fechaCreacion= new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("f_crea").toString());
                profesor= rs.getString("profesor");
                
                modelo.insertRow(i++, new Object[]{
                  rs.getObject("id1_nombre_materia"),
                  materia,
                  rs.getObject("numhora"),
                  activacion,
                  semestre,
                  especialidad,
                  eje,
                  ciclo,
                  new SimpleDateFormat("dd/MM/yyyy").format(fechaCreacion),
                  profesor
                });
                }
            } catch (SQLException | ParseException | NullPointerException | InterruptedException ex) {
                    Logger.getLogger(FrmProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    cc.desconectar();
                } catch (Exception e) {
                }
            }
            mensaje.setText("TERMINADO......");
            insertaBtn.setEnabled(true);
            tablaMateria.setEnabled(true);
        }
    }).start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMateria = new javax.swing.JTable();
        insertaBtn = new javax.swing.JButton();
        mensaje = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Formulario Materia ");

        tablaMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMateriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMateria);

        insertaBtn.setText("Insertar");
        insertaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertaBtnActionPerformed(evt);
            }
        });

        mensaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(insertaBtn)
                .addGap(146, 146, 146)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(710, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(insertaBtn)
                    .addComponent(mensaje))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertaBtnActionPerformed
      try {
          idmateria=0.0;
          mostrarFormularioMateria();
      } catch (SQLException | ParseException ex) {
          Logger.getLogger(FrmMateria.class.getName()).log(Level.SEVERE, null, ex);
      }
       
    }//GEN-LAST:event_insertaBtnActionPerformed

    private void tablaMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMateriaMouseClicked
        double i = tablaMateria.getSelectedRow();
        int y =(int)Math.round(i);
        idmateria=(Double)(tablaMateria.getValueAt(y, 0));
        try {
            mostrarFormularioMateria();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(FrmMateria.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_tablaMateriaMouseClicked
 public void mostrarFormularioMateria() throws SQLException, ParseException{
  formularioMateria = new FormularioMateria(FrmMateria.this, true,idmateria);
  formularioMateria.setLocationRelativeTo(FrmMateria.this);
  formularioMateria.setVisible(true);
  mostraDatos();
 }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertaBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mensaje;
    private javax.swing.JTable tablaMateria;
    // End of variables declaration//GEN-END:variables
}
