/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import conectar.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.UsuarioDao;
import modelo.Usuario;

/**
 *
 * @author USER
 */
public class FrmUsuario extends javax.swing.JInternalFrame {

    Usuario usuario;
    ResultSet resultSet;
    List<Usuario> listaUsuarios;
    DefaultTableModel modelo;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idUsuario;
    public static FormularioUsuario formularioUsuario;

    public FrmUsuario() {
        initComponents();
        usuario = new Usuario();
        cargaDatos();
        setLocation(310, 100);
    }

    private void cargaDatos() {
        String[] col = {"PK", "NOMBRES", "PERFIL", "ESTADO"};
        String[][] data = {{"", "", ""}};
        modelo = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.setRowCount(0);
        usuarioTabla.setModel(modelo);
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                if (listaUsuarios == null) {
                    listaUsuarios = new ArrayList<>();
                } else {
                    listaUsuarios.clear();
                }
                try {
                    UsuarioDao usuarioDao = new UsuarioDao();
                    resultSet = usuarioDao.listarUsuarios();
                    while (resultSet.next()) {
                        usuario.setIdUsuario(resultSet.getInt("idusuario"));
                        usuario.setNombre(resultSet.getString("nombre"));
                        usuario.setPerfil(resultSet.getString("perfil"));
                        usuario.setEstado(resultSet.getString("estado"));
                        modelo.insertRow(i, new Object[]{
                            usuario.getIdUsuario(),
                            usuario.getNombre(),
                            usuario.getPerfil(),
                            usuario.getEstado()
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Error" + e.getMessage());
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
        usuarioTabla = new javax.swing.JTable();
        insertarBtn = new javax.swing.JButton();

        setClosable(true);
        setTitle("Listado de Usuarios");

        usuarioTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        usuarioTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usuarioTabla);

        insertarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Add.png"))); // NOI18N
        insertarBtn.setText("Insertar");
        insertarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertarBtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(insertarBtn)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBtnActionPerformed
        idUsuario=0;
        llamaFormUsuario();
    }//GEN-LAST:event_insertarBtnActionPerformed

    private void usuarioTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioTablaMouseClicked
        Integer i = usuarioTabla.getSelectedRow();
        idUsuario=(Integer) usuarioTabla.getValueAt(i,0);
        llamaFormUsuario();
    }//GEN-LAST:event_usuarioTablaMouseClicked
    private void llamaFormUsuario() {
        formularioUsuario=new FormularioUsuario(FrmUsuario.this, true, idUsuario);
        formularioUsuario.setLocationRelativeTo(FrmUsuario.this);
        formularioUsuario.setVisible(true);
        cargaDatos();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable usuarioTabla;
    // End of variables declaration//GEN-END:variables
}
