package vistas;

import conectar.Conexion;
import controles.DatosMateriaControl;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tablas.Malla;
import tablas.Materia;

public class FormularioMateria extends javax.swing.JDialog {

    private Double idMateria;
    private DatosMateriaControl materiaControl;
    private Date fechaCreacion;
    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    public static FrmMateria frmMateria;
    private String activaMateria, val;
    private int numCampos, horasMaterias, numPonderacionMalla;
    double numHoras;
    List<Materia> listaHoras;
    private Malla malla;
    private static final int total = 100;
    private int suma;
    private boolean resultado;
    Map campos1;
    Map campos;

    public FormularioMateria(FrmMateria parent, boolean modal) throws SQLException {
        this.frmMateria = parent;
        this.setModal(modal);
        initComponents();
        cargarProfesor();
        cargarEje();
        cargarEspecialidad();
        cargarSemestre();
        cargarMalla();
        ocultaCampos();
        btnGuardar.setEnabled(false);
        calcularBtn.setEnabled(false);
        this.setLocationRelativeTo(null);
        materiaControl = new DatosMateriaControl();
        numHorasTxt.setEnabled(false);
        ponderacionTxt.setEnabled(false);
        cmbSemestre.setEnabled(false);
        cmbEspecialidad.setEnabled(false);
        horasDisponiblesTxt.setEnabled(false);

    }

    public FormularioMateria(FrmMateria parent, boolean modal, Double pkMateria) throws SQLException, ParseException {
        this.frmMateria = parent;
        this.setModal(modal);
        initComponents();
        creditosEspinner.setFocusCycleRoot(false);
        cargarProfesor();
        cargarEje();
        cargarEspecialidad();
        cargarSemestre();
        cargarMalla();
        ocultaCampos();
        this.setLocationRelativeTo(null);
        btnGuardar.setEnabled(false);
        calcularBtn.setEnabled(false);
        materiaControl = new DatosMateriaControl();
        this.idMateria = pkMateria;
        numHorasTxt.setEnabled(false);
        ponderacionTxt.setEnabled(false);
        cmbSemestre.setEnabled(false);
        cmbEspecialidad.setEnabled(false);
        horasDisponiblesTxt.setEnabled(false);
        ResultSet rs;
        int idconfigMaterias = 0;

        if (idMateria > 0) {
            rs = materiaControl.consulta(pkMateria);
            while (rs.next()) {
                String mate = " ";
                fechaCreacion = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("f_crea").toString());
                materiatxt.setText(rs.getString("m.materia").trim());
                int vil = Integer.parseInt(rs.getString("num_hora").trim());
                creditosEspinner.setValue(vil);
                mate = rs.getString("activa_mat");
                if (mate.equals("SI")) {
                    rdbSi.setSelected(true);
                } else if (mate.equals("NO")) {
                    rdbNo.setSelected(true);
                }
                cmbSemestre.setSelectedItem(rs.getString("semestre"));
                cmbEspecialidad.setSelectedItem(rs.getString("especialidad"));
                cmbEje.setSelectedItem(rs.getString("nom_ejes"));
                fechaChooser.setDate(fechaCreacion);
                mallaCmb.setSelectedIndex(Integer.parseInt(rs.getString("id_malla")));
                cmbProfesor.setSelectedItem(rs.getString("profesor"));
                ponderacionTxt.setText(rs.getString("ponderacion"));
                idconfigMaterias = Integer.parseInt(rs.getString("id_config_materia"));

            }

            rs = materiaControl.cargarConfigMateria(idconfigMaterias);
            while (rs.next()) {
                numCampos = Integer.parseInt(rs.getString("num_aporte"));
                switch (numCampos) {
                    case 1:
                        nota1Txt.setText(rs.getString("aporte1"));
                        break;
                    case 2:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        break;
                    case 3:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        break;
                    case 4:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        break;
                    case 5:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        nota5Txt.setText(rs.getString("aporte5"));
                        break;
                    case 6:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        nota5Txt.setText(rs.getString("aporte5"));
                        nota6Txt.setText(rs.getString("aporte6"));
                        break;
                    case 7:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        nota5Txt.setText(rs.getString("aporte5"));
                        nota6Txt.setText(rs.getString("aporte6"));
                        nota7Txt.setText(rs.getString("aporte7"));
                        break;
                    case 8:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        nota5Txt.setText(rs.getString("aporte5"));
                        nota6Txt.setText(rs.getString("aporte6"));
                        nota7Txt.setText(rs.getString("aporte7"));
                        nota8Txt.setText(rs.getString("aporte8"));
                        break;
                    case 9:
                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        nota5Txt.setText(rs.getString("aporte5"));
                        nota6Txt.setText(rs.getString("aporte6"));
                        nota7Txt.setText(rs.getString("aporte7"));
                        nota8Txt.setText(rs.getString("aporte8"));
                        nota9Txt.setText(rs.getString("aporte9"));
                        break;
                    case 10:

                        nota1Txt.setText(rs.getString("aporte1"));
                        nota2Txt.setText(rs.getString("aporte2"));
                        nota3Txt.setText(rs.getString("aporte3"));
                        nota4Txt.setText(rs.getString("aporte4"));
                        nota5Txt.setText(rs.getString("aporte5"));
                        nota6Txt.setText(rs.getString("aporte6"));
                        nota7Txt.setText(rs.getString("aporte7"));
                        nota8Txt.setText(rs.getString("aporte8"));
                        nota9Txt.setText(rs.getString("aporte9"));
                        nota10Txt.setText(rs.getString("aporte10"));
                        break;

                }
            }
            numCamposTxt.setText(String.valueOf(numCampos));
            accionCampos(numCampos);

            numHoras = materiaControl.cargarNumHorasMalla(malla.getIdmalla());
            numHorasTxt.setText(String.valueOf(numHoras));
//            cmbCiclo.setEnabled(false);
//            cmbEje.setEnabled(false);
//            cmbEspecialidad.setEnabled(false);
//            cmbProfesor.setEnabled(false);
//            cmbSemestre.setEnabled(false);
        }
    }

    private void cargarSemestre() throws SQLException {
        String sql = "select * from semestre";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            cmbSemestre.addItem(rs.getString("semestre"));
        }
    }

   

    private void cargarEje() throws SQLException {
        String sql = "select * from ejes";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            cmbEje.addItem(rs.getString("nom_ejes"));
        }
    }

    private void cargarEspecialidad() throws SQLException {
        String sql = "select * from especialidad";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            cmbEspecialidad.addItem(rs.getString("especialidad"));
        }
    }

    private void cargarProfesor() throws SQLException {
        String sql = "select CONCAT(nom_profe,\" \",apell_profe )AS nombre from datos_profesor";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            cmbProfesor.addItem(rs.getString("nombre"));
        }

    }

    private void cargarMalla() throws SQLException {
        String sql = "SELECT * FROM malla";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            malla = new Malla();
            malla.setIdmalla(Integer.parseInt(rs.getString("id_malla")));
            malla.setNombreMalla(rs.getString("nombre_malla"));
            mallaCmb.addItem(malla);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materia = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rdbSi = new javax.swing.JRadioButton();
        rdbNo = new javax.swing.JRadioButton();
        materiatxt = new javax.swing.JTextField();
        Semestre = new javax.swing.JLabel();
        cmbSemestre = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbEje = new javax.swing.JComboBox();
        fechaChooser = new com.toedter.calendar.JDateChooser();
        fechalbl = new javax.swing.JLabel();
        cmbProfesor = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmbEspecialidad = new javax.swing.JComboBox();
        creditosEspinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        numCamposTxt = new javax.swing.JTextField();
        cargarCamposBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nota1Txt = new javax.swing.JTextField();
        nota2Txt = new javax.swing.JTextField();
        nota3Txt = new javax.swing.JTextField();
        nota4Txt = new javax.swing.JTextField();
        nota5Txt = new javax.swing.JTextField();
        nota6Txt = new javax.swing.JTextField();
        nota7Txt = new javax.swing.JTextField();
        nota8Txt = new javax.swing.JTextField();
        nota9Txt = new javax.swing.JTextField();
        nota10Txt = new javax.swing.JTextField();
        calcularBtn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        mallaCmb = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        numHorasTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ponderacionTxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        horasDisponiblesTxt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        totalPonMallaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FORMULARIO MATERIA");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nombre Materia ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Numero de Horas Materia");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10)), "Activar Materia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        materia.add(rdbSi);
        rdbSi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbSi.setText("SI");
        rdbSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbSiActionPerformed(evt);
            }
        });

        materia.add(rdbNo);
        rdbNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdbNo.setText("NO");
        rdbNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(rdbSi)
                .addGap(91, 91, 91)
                .addComponent(rdbNo)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbSi)
                    .addComponent(rdbNo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Semestre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Semestre.setText("Semestre");

        cmbSemestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cmbSemestre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSemestreItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Eje");

        cmbEje.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cmbEje.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEjeItemStateChanged(evt);
            }
        });

        fechalbl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fechalbl.setText("Fecha Creacion");

        cmbProfesor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cmbProfesor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProfesorItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Profesor");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Especialidad");

        cmbEspecialidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));

        creditosEspinner.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        creditosEspinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        creditosEspinner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                creditosEspinnerKeyTyped(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Valores para Notas "));

        cargarCamposBtn.setText("Cargar Campos");
        cargarCamposBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarCamposBtnActionPerformed(evt);
            }
        });

        jLabel7.setText("Nota1");

        jLabel8.setText("Nota2");

        jLabel9.setText("Nota3");

        jLabel10.setText("Nota5");

        jLabel11.setText("Nota4");

        jLabel12.setText("Nota9");

        jLabel13.setText("Nota10");

        jLabel14.setText("Nota8");

        jLabel15.setText("Nota7");

        jLabel16.setText("Nota6");

        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numCamposTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargarCamposBtn)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(18, 18, 18)
                            .addComponent(nota5Txt))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(nota4Txt))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(nota3Txt))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(nota2Txt))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(jLabel12)
                                .addComponent(jLabel16))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nota6Txt, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                        .addComponent(nota10Txt)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(nota9Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(1, 1, 1))))
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(calcularBtn)
                        .addGap(78, 78, 78)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel14)
                                    .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nota9Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33)
                .addComponent(numCamposTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargarCamposBtn)
                    .addComponent(calcularBtn))
                .addContainerGap())
        );

        jLabel19.setText("Malla");

        mallaCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona" }));
        mallaCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mallaCmbItemStateChanged(evt);
            }
        });

        jLabel17.setText("Numero de Horas Malla");

        jLabel18.setText("Ponderaciòn Materia");

        jLabel21.setText("Horas Disponibles de Especialidad");

        jLabel22.setText("Total Ponderacion Malla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(creditosEspinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(mallaCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(fechalbl)
                                                    .addGap(38, 38, 38))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel3))
                                                    .addGap(25, 25, 25)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmbEje, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fechaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(Semestre, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(btnGuardar))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(80, 80, 80)
                                .addComponent(materiatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21)
                            .addComponent(jLabel17))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numHorasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(98, 98, 98)
                                .addComponent(ponderacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(horasDisponiblesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalPonMallaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(materiatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(creditosEspinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mallaCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Semestre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechalbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(horasDisponiblesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(numHorasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(ponderacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(totalPonMallaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        val = String.valueOf(creditosEspinner.getValue());

        try {
            if (materiatxt.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Campo Nombre Materia esta Vacio ", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (val.compareTo("0") == 0) {
                JOptionPane.showMessageDialog(this, "Campo Numero Creditos esta Vacio ", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (rdbSi.isSelected() == false && rdbNo.isSelected() == false) {
                JOptionPane.showMessageDialog(this, "Deve selecionar una accion para Materia ", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cmbSemestre.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "El campo Semestre esta vacio", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cmbEspecialidad.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "El campo Especialidad esta vacio", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cmbEje.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "El campo Eje esta vacio", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (fechaChooser.getDate() == null) {
                JOptionPane.showMessageDialog(this, "La fecha de Creacion esta vacio", "error", JOptionPane.ERROR_MESSAGE);
                fechaChooser.requestFocus();
                return;
            }
            if (cmbProfesor.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "El campo Profesor esta vacio", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (numCamposTxt.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Error campo configutracion de aportes vacios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                numCampos = Integer.valueOf(numCamposTxt.getText());
            }

            if (idMateria == 0.0) {
                if (validaCampos(Integer.parseInt(numCamposTxt.getText()))) {
                    if (validaSuma(Integer.parseInt(numCamposTxt.getText()))) {
                        verificaHoras();
                        cargarCogigMaterias(numCampos);
                        materiaControl.insertar("config_materia", campos1);
                        cargaMateria();
                        materiaControl.insertar("nombre_materia", campos);
                        JOptionPane.showMessageDialog(null, "Registros ingresados Correctamente");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de configuracion de Valor de Materias", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error de configuracion de Valor de Materias", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                if (validaCampos(Integer.parseInt(numCamposTxt.getText()))) {
                    if (validaSuma(Integer.parseInt(numCamposTxt.getText()))) {
                        verificaHoras();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error de configuracion de Valor de Materias", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error de configuracion de Valor de Materias", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (HeadlessException | NumberFormatException e) {
            Logger.getLogger(FormularioPeriodo.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    private Map cargarCogigMaterias(int numCampos) {
        campos1 = new HashMap();
        campos1.put("numa_porte", numCamposTxt.getText());

        switch (numCampos) {
            case 1:
                campos1.put("aporte1", nota1Txt.getText());
                break;
            case 2:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                break;
            case 3:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                break;
            case 4:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                break;
            case 5:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                campos1.put("aporte5", nota5Txt.getText());
                break;
            case 6:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                campos1.put("aporte5", nota5Txt.getText());
                campos1.put("aporte6", nota6Txt.getText());
                break;
            case 7:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                campos1.put("aporte5", nota5Txt.getText());
                campos1.put("aporte6", nota6Txt.getText());
                campos1.put("aporte7", nota7Txt.getText());
                break;
            case 8:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                campos1.put("aporte5", nota5Txt.getText());
                campos1.put("aporte6", nota6Txt.getText());
                campos1.put("aporte7", nota7Txt.getText());
                campos1.put("aporte8", nota8Txt.getText());
                break;
            case 9:
                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                campos1.put("aporte5", nota5Txt.getText());
                campos1.put("aporte6", nota6Txt.getText());
                campos1.put("aporte7", nota7Txt.getText());
                campos1.put("aporte8", nota8Txt.getText());
                campos1.put("aporte9", nota9Txt.getText());
                break;
            case 10:

                campos1.put("aporte1", nota1Txt.getText());
                campos1.put("aporte2", nota2Txt.getText());
                campos1.put("aporte3", nota3Txt.getText());
                campos1.put("aporte4", nota4Txt.getText());
                campos1.put("aporte5", nota5Txt.getText());
                campos1.put("aporte6", nota6Txt.getText());
                campos1.put("aporte7", nota7Txt.getText());
                campos1.put("aporte8", nota8Txt.getText());
                campos1.put("aporte9", nota9Txt.getText());
                campos1.put("aporte10", nota10Txt.getText());
                break;
        }

        return campos1;
    }

    private Map cargaMateria() {
        campos = new HashMap();
        campos.put("id_materia", " ");
        campos.put("id_semestre", " ");
        campos.put("id_especial", " ");
        campos.put("id_ejes", " ");
        campos.put("materia", materiatxt.getText().toUpperCase());
        campos.put("num_hora", val);
        campos.put("mat_sec", "xxx");
        campos.put("activa_mat", activaMateria);
        campos.put("AUX", 0);
        campos.put("ID_NUM", 0);
        campos.put("id1_semestre", cmbSemestre.getSelectedIndex());
        campos.put("id1_especialidad", cmbEspecialidad.getSelectedIndex());
        campos.put("id1_eje", cmbEje.getSelectedIndex());
        campos.put("f_crea", fechaChooser.getCalendar().getTime());
        campos.put("f_modifica", fechaChooser.getCalendar().getTime());
        campos.put("id1_profe", cmbProfesor.getSelectedIndex());
        campos.put("alias", " ");
        campos.put("materia_antes", materiatxt.getText().toUpperCase());
        campos.put("id_malla", malla.getIdmalla());
        campos.put("id_config_materia", materiaControl.idconfigMateria());
        campos.put("ponderacion", Double.parseDouble(ponderacionTxt.getText()));
        return campos;
    }
    private void rdbSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbSiActionPerformed
        activaMateria = "SI";
        if (materiatxt.getText().contains("tutoria")) {
            creditosEspinner.setEnabled(false);
            cargarCamposBtn.setEnabled(false);
        }
    }//GEN-LAST:event_rdbSiActionPerformed

    private void rdbNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbNoActionPerformed
        activaMateria = "NO";
    }//GEN-LAST:event_rdbNoActionPerformed

    private void creditosEspinnerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditosEspinnerKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }        
    }//GEN-LAST:event_creditosEspinnerKeyTyped


    private void cargarCamposBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarCamposBtnActionPerformed
        if (numCamposTxt.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Campos Vacio debe ingresar un valor", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            numCampos = Integer.parseInt(numCamposTxt.getText());
            accionCampos(numCampos);
            calcularBtn.setEnabled(true);
        }     
    }//GEN-LAST:event_cargarCamposBtnActionPerformed

    @SuppressWarnings("UnusedAssignment")
    private void mallaCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mallaCmbItemStateChanged
        if (idMateria == 0) {
            try {
                verificaHoras();
                ResultSet re = materiaControl.cargarIdentificadores(mallaCmb.getSelectedIndex());
                while (re.next()) {
                    cmbSemestre.setSelectedIndex(Integer.parseInt(re.getString("id1_semestre")));
                    cmbEspecialidad.setSelectedIndex(Integer.valueOf(re.getString("id1_especialidad")));
                }

            } catch (SQLException ex) {
                Logger.getLogger(FormularioMateria.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_mallaCmbItemStateChanged

    private void cmbEjeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEjeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEjeItemStateChanged

    private void cmbProfesorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProfesorItemStateChanged
//        if (idMateria == 0) {
//            verificaHoras();
//        }
//        

    }//GEN-LAST:event_cmbProfesorItemStateChanged

    private void cmbSemestreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSemestreItemStateChanged

    }//GEN-LAST:event_cmbSemestreItemStateChanged

    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        numCampos = Integer.valueOf(numCamposTxt.getText());
        if (validaCampos(numCampos)) {
            if (!validaSuma(numCampos)) {
                JOptionPane.showMessageDialog(null, "Error rango de sumatoria no aceptado, debe der igual a 100%", "Error", JOptionPane.ERROR_MESSAGE);
                limpia();
            } else {
                btnGuardar.setEnabled(true);
                verificaHoras();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos Vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_calcularBtnActionPerformed
    private void limpia() {
        nota1Txt.setText(null);
        nota2Txt.setText(null);
        nota3Txt.setText(null);
        nota4Txt.setText(null);
        nota5Txt.setText(null);
        nota6Txt.setText(null);
        nota7Txt.setText(null);
        nota8Txt.setText(null);
        nota9Txt.setText(null);
        nota10Txt.setText(null);
    }

    private void verificaHoras() {

        horasMaterias = materiaControl.numeroHorasMaterias(malla.getIdmalla());
        numPonderacionMalla = materiaControl.numeroPonderacionMalla(malla.getIdmalla());
        int valHoraMateria = (int) creditosEspinner.getValue();
        numHoras = materiaControl.cargarNumHorasMalla(malla.getIdmalla());
        numHorasTxt.setText(String.valueOf(numHoras));
        if (horasMaterias == 0) {
            horasDisponiblesTxt.setText(String.valueOf(numHoras - valHoraMateria));
            double valor;
            valor = (valHoraMateria * 100) / numHoras;
            double numero = valor + numPonderacionMalla;
            ponderacionTxt.setText(String.valueOf(Math.round(valor * Math.pow(10, 1) / Math.pow(10, 1))));
            totalPonMallaTxt.setText(String.valueOf(Math.round(numero * Math.pow(10, 1) / Math.pow(10, 1))) + " " + "%");
        } else {
            int val = horasMaterias + valHoraMateria;
            int co = (int) (numHoras - val);
            if (co < 0) {
                JOptionPane.showMessageDialog(null, "No hay numero de horas  para la materia", "Error", JOptionPane.ERROR_MESSAGE);
                horasDisponiblesTxt.setText("0");
                btnGuardar.setEnabled(false);
                return;
            } else {
                horasDisponiblesTxt.setText(String.valueOf(co));
                double valor;
                valor = (valHoraMateria * 100) / numHoras;
                double numero = valor + numPonderacionMalla;
                ponderacionTxt.setText(String.valueOf(Math.round(valor * Math.pow(10, 1) / Math.pow(10, 1))));
                totalPonMallaTxt.setText(String.valueOf(Math.round(numero * Math.pow(10, 1) / Math.pow(10, 1))) + " " + "%");
            }

        }

    }

    private void accionCampos(int numCampos) {
        if (numCampos > 10) {
            JOptionPane.showMessageDialog(null, "Error rango no valido para cargar campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            switch (numCampos) {
                case 1:
                    nota1Txt.setEnabled(true);
                    break;
                case 2:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    break;
                case 3:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    break;

                case 4:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    break;
                case 5:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    nota5Txt.setEnabled(true);
                    break;
                case 6:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    nota5Txt.setEnabled(true);
                    nota6Txt.setEnabled(true);
                    break;
                case 7:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    nota5Txt.setEnabled(true);
                    nota6Txt.setEnabled(true);
                    nota7Txt.setEnabled(true);
                    break;

                case 8:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    nota5Txt.setEnabled(true);
                    nota6Txt.setEnabled(true);
                    nota7Txt.setEnabled(true);
                    nota8Txt.setEnabled(true);
                    break;
                case 9:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    nota5Txt.setEnabled(true);
                    nota6Txt.setEnabled(true);
                    nota7Txt.setEnabled(true);
                    nota8Txt.setEnabled(true);
                    nota9Txt.setEnabled(true);
                    break;
                case 10:
                    nota1Txt.setEnabled(true);
                    nota2Txt.setEnabled(true);
                    nota3Txt.setEnabled(true);
                    nota4Txt.setEnabled(true);
                    nota5Txt.setEnabled(true);
                    nota6Txt.setEnabled(true);
                    nota7Txt.setEnabled(true);
                    nota8Txt.setEnabled(true);
                    nota9Txt.setEnabled(true);
                    nota10Txt.setEnabled(true);
                    break;

            }
        }

    }

    private void ocultaCampos() {
        nota1Txt.setEnabled(false);
        nota2Txt.setEnabled(false);
        nota3Txt.setEnabled(false);
        nota4Txt.setEnabled(false);
        nota5Txt.setEnabled(false);
        nota6Txt.setEnabled(false);
        nota7Txt.setEnabled(false);
        nota8Txt.setEnabled(false);
        nota9Txt.setEnabled(false);
        nota10Txt.setEnabled(false);

    }

    private boolean validaCampos(int numCampos) {
        resultado = true;

        String val1, val2, val3, val4, val5, val6, val7, val8, val9, val10;
        val1 = nota1Txt.getText();
        val2 = nota2Txt.getText();
        val3 = nota3Txt.getText();
        val4 = nota4Txt.getText();
        val5 = nota5Txt.getText();
        val6 = nota6Txt.getText();
        val7 = nota7Txt.getText();
        val8 = nota8Txt.getText();
        val9 = nota9Txt.getText();
        val10 = nota10Txt.getText();
        if (numCampos > 10) {
            JOptionPane.showMessageDialog(null, "Error rango no valido para cargar campos", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            switch (numCampos) {
                case 1:

                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 2:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 3:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    break;

                case 4:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 5:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    if (val5.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 6:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    if (val5.length() == 0) {
                        resultado = false;
                    }
                    if (val6.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 7:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    if (val5.length() == 0) {
                        resultado = false;
                    }
                    if (val6.length() == 0) {
                        resultado = false;
                    }
                    if (val7.length() == 0) {
                        resultado = false;
                    }
                    break;

                case 8:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    if (val5.length() == 0) {
                        resultado = false;
                    }
                    if (val6.length() == 0) {
                        resultado = false;
                    }
                    if (val7.length() == 0) {
                        resultado = false;
                    }
                    if (val8.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 9:
                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    if (val5.length() == 0) {
                        resultado = false;
                    }
                    if (val6.length() == 0) {
                        resultado = false;
                    }
                    if (val7.length() == 0) {
                        resultado = false;
                    }
                    if (val8.length() == 0) {
                        resultado = false;
                    }

                    if (val9.length() == 0) {
                        resultado = false;
                    }
                    break;
                case 10:

                    if (val1.length() == 0) {
                        resultado = false;
                    }
                    if (val2.length() == 0) {
                        resultado = false;
                    }
                    if (val3.length() == 0) {
                        resultado = false;
                    }
                    if (val4.length() == 0) {
                        resultado = false;
                    }
                    if (val5.length() == 0) {
                        resultado = false;
                    }
                    if (val6.length() == 0) {
                        resultado = false;
                    }
                    if (val7.length() == 0) {
                        resultado = false;
                    }
                    if (val8.length() == 0) {
                        resultado = false;
                    }

                    if (val9.length() == 0) {
                        resultado = false;
                    }
                    if (val10.length() == 0) {
                        resultado = false;
                    }
                    break;

            }
        }
        return resultado;
    }

    private boolean validaSuma(int numCampos) {
        int val1, val2, val3, val4, val5, val6, val7, val8, val9, val10;

        switch (numCampos) {
            case 1:
                val1 = Integer.parseInt(nota1Txt.getText());
                if (val1 > total || val1 < total) {
                    resultado = false;
                }
                break;
            case 2:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                suma = val1 + val2;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 3:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                suma = val1 + val2 + val3;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 4:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                suma = val1 + val2 + val3 + val4;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 5:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                val5 = Integer.parseInt(nota5Txt.getText());
                suma = val1 + val2 + val3 + val4 + val5;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 6:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                val5 = Integer.parseInt(nota5Txt.getText());
                val6 = Integer.parseInt(nota6Txt.getText());
                suma = val1 + val2 + val3 + val4 + val5 + val6;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 7:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                val5 = Integer.parseInt(nota5Txt.getText());
                val6 = Integer.parseInt(nota6Txt.getText());
                val7 = Integer.parseInt(nota7Txt.getText());
                suma = val1 + val2 + val3 + val4 + val5 + val6 + val7;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 8:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                val5 = Integer.parseInt(nota5Txt.getText());
                val6 = Integer.parseInt(nota6Txt.getText());
                val7 = Integer.parseInt(nota7Txt.getText());
                val8 = Integer.parseInt(nota8Txt.getText());
                suma = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 9:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                val5 = Integer.parseInt(nota5Txt.getText());
                val6 = Integer.parseInt(nota6Txt.getText());
                val7 = Integer.parseInt(nota7Txt.getText());
                val8 = Integer.parseInt(nota8Txt.getText());
                val9 = Integer.parseInt(nota9Txt.getText());
                suma = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;
            case 10:
                val1 = Integer.parseInt(nota1Txt.getText());
                val2 = Integer.parseInt(nota2Txt.getText());
                val3 = Integer.parseInt(nota3Txt.getText());
                val4 = Integer.parseInt(nota4Txt.getText());
                val5 = Integer.parseInt(nota5Txt.getText());
                val6 = Integer.parseInt(nota6Txt.getText());
                val7 = Integer.parseInt(nota7Txt.getText());
                val8 = Integer.parseInt(nota8Txt.getText());
                val9 = Integer.parseInt(nota9Txt.getText());
                val10 = Integer.parseInt(nota10Txt.getText());
                suma = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9 + val10;
                if (suma > total || suma < total) {
                    resultado = false;
                }
                break;

        }

        return resultado;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioMateria dialog = null;
                try {
                    dialog = new FormularioMateria(frmMateria, true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormularioMateria.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Semestre;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton calcularBtn;
    private javax.swing.JButton cargarCamposBtn;
    private javax.swing.JComboBox cmbEje;
    private javax.swing.JComboBox cmbEspecialidad;
    private javax.swing.JComboBox cmbProfesor;
    private javax.swing.JComboBox cmbSemestre;
    private javax.swing.JSpinner creditosEspinner;
    private com.toedter.calendar.JDateChooser fechaChooser;
    private javax.swing.JLabel fechalbl;
    private javax.swing.JTextField horasDisponiblesTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox mallaCmb;
    private javax.swing.ButtonGroup materia;
    private javax.swing.JTextField materiatxt;
    private javax.swing.JTextField nota10Txt;
    private javax.swing.JTextField nota1Txt;
    private javax.swing.JTextField nota2Txt;
    private javax.swing.JTextField nota3Txt;
    private javax.swing.JTextField nota4Txt;
    private javax.swing.JTextField nota5Txt;
    private javax.swing.JTextField nota6Txt;
    private javax.swing.JTextField nota7Txt;
    private javax.swing.JTextField nota8Txt;
    private javax.swing.JTextField nota9Txt;
    private javax.swing.JTextField numCamposTxt;
    private javax.swing.JTextField numHorasTxt;
    private javax.swing.JTextField ponderacionTxt;
    private javax.swing.JRadioButton rdbNo;
    private javax.swing.JRadioButton rdbSi;
    private javax.swing.JTextField totalPonMallaTxt;
    // End of variables declaration//GEN-END:variables
}
