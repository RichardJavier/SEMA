package vistas;

import conectar.Conexion;
import control.Crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.MallaDao;
import logica.MetodosGeneralesDao;
import modelo.Especialidad;
import modelo.Estado;
import modelo.Malla;
import modelo.Periodo;
import modelo.Semestre;

public class FormularioMalla extends javax.swing.JDialog {

    private static FrmMalla frmMalla;
    Conexion cc;
    Connection cn;
    Crud crud;
    private String activada;
    private Integer idMalla;
    MetodosGeneralesDao metodosGeneralesDao;
    ResultSet resultSet;
    private static final Integer valor = 100;
    Malla malla;
    Especialidad especialidad;
    Periodo periodo;
    Semestre semestre;
    Map campos;

    public FormularioMalla(FrmMalla parent, boolean modal) {
        FormularioMalla.frmMalla = parent;
        this.setModal(modal);
        initComponents();
        creditosNotaEmpresaTxt.setEnabled(false);
        malla = new Malla();
        metodosGeneralesDao = new MetodosGeneralesDao();
        this.gurdarBtn.setEnabled(false);
        especialidad = new Especialidad();
        periodo = new Periodo();
        semestre = new Semestre();
        cargarEspecialidad();
        cargarPeriodo();
        cargarSemestre();
    }

    public FormularioMalla(FrmMalla parent, boolean modal, Integer idMalla) {
        FormularioMalla.frmMalla = parent;
        this.setModal(modal);
        initComponents();
        creditosNotaEmpresaTxt.setEnabled(false);
        malla = new Malla();
        this.idMalla = idMalla;
        metodosGeneralesDao = new MetodosGeneralesDao();
        especialidad = new Especialidad();
        periodo = new Periodo();
        semestre = new Semestre();
        this.gurdarBtn.setEnabled(false);
        cargarEspecialidad();
        cargarPeriodo();
        cargarSemestre();

        MallaDao mallaDao = new MallaDao();
        if (idMalla > 0) {
            try {
                resultSet = mallaDao.consulta(idMalla);
                while (resultSet.next()) {
                    especialidad.setEspecialidad(resultSet.getString("especialidad"));
                    especialidad.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
                    especialidadCmb.setSelectedItem(especialidad);

                    periodo.setIdPeriodo(Integer.parseInt(resultSet.getString("id1_periodo")));
                    periodo.setNombrePeriodo(resultSet.getString("periodo"));
                    periodoCmb.setSelectedItem(periodo);

                    semestre.setIdSemestre(Integer.valueOf(resultSet.getString("id1_semestre")));
                    semestre.setSemestre(resultSet.getString("semestre"));
                    semestreCmb.setSelectedItem(semestre);

                    malla.setNombreMalla(resultSet.getString("nombre_malla"));
                    malla.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fecha_creacion")));
                    nombreMallaTxt.setText(malla.getNombreMalla());
                    porcentajeNotaEmpresaTxt.setText(resultSet.getString("porc_nota_empresa"));
                    porcentajeNotaTeorica.setText(resultSet.getString("porc_nota_teorica"));
                    porcentajePonderadoNota.setText(resultSet.getString("porc_ponderado_nota"));
                    porcentajeTutoriaIntegrada.setText(resultSet.getString("porc_integrada"));
                    totalCreditosCiclo.setText(resultSet.getString("cred_ciclo"));
                    totalCreditosTeoricaTxt.setText(resultSet.getString("cred_teorica"));
                    valorMinimoPromedioNotaTxt.setText(resultSet.getString("valor_min_promedio"));
                    creditosNotaEmpresaTxt.setText(resultSet.getString("cred_nota_empresa"));
                    valorIngresoNotaTxt.setText(resultSet.getString("valor_calf_nota"));
                    valorMinimoAsistenciaTxt.setText(resultSet.getString("valor_min_asistencia"));
                    valorMinimoPromedioNotaTxt.setText(resultSet.getString("valor_min_promedio"));
                    valorMinimoRecuperacionTxt.setText(resultSet.getString("valor_min_recuperacion"));
                    activada = resultSet.getString("estado");
                    if (!activada.equals("A")) {
                        desactivadaRdb.setSelected(true);
                    } else {
                        activadaRdb.setSelected(true);
                    }

                }
            } catch (SQLException | ParseException | NumberFormatException e) {
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estadoMallaGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreMallaTxt = new javax.swing.JTextField();
        porcentajePonderadoNota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        porcentajeTutoriaIntegrada = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        porcentajeNotaTeorica = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        porcentajeNotaEmpresaTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        totalCreditosTeoricaTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        totalCreditosCiclo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        activadaRdb = new javax.swing.JRadioButton();
        desactivadaRdb = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        semestreCmb = new javax.swing.JComboBox();
        especialidadCmb = new javax.swing.JComboBox();
        periodoCmb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        gurdarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        validarBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        creditosNotaEmpresaTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        valorMinimoPromedioNotaTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        valorMinimoAsistenciaTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        valorIngresoNotaTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        valorMinimoRecuperacionTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FORMULARIO DE MALLA");
        setUndecorated(true);

        jLabel1.setText("Nombre Malla");

        jLabel2.setText("Porcentaje Ponderado Nota");

        porcentajePonderadoNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajePonderadoNotaKeyTyped(evt);
            }
        });

        jLabel3.setText("Porcentaje Tutoria Integrada");

        porcentajeTutoriaIntegrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeTutoriaIntegradaKeyTyped(evt);
            }
        });

        jLabel4.setText("Porcentaje Nota Teorica");

        porcentajeNotaTeorica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeNotaTeoricaKeyTyped(evt);
            }
        });

        jLabel5.setText("Porcentaje Nota Empresa");

        porcentajeNotaEmpresaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                porcentajeNotaEmpresaTxtKeyTyped(evt);
            }
        });

        jLabel6.setText("Total Creditos F.Teorica");

        totalCreditosTeoricaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalCreditosTeoricaTxtKeyTyped(evt);
            }
        });

        jLabel7.setText("Total Creditos Ciclo");

        totalCreditosCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCreditosCicloActionPerformed(evt);
            }
        });
        totalCreditosCiclo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalCreditosCicloKeyTyped(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado Malla"));

        estadoMallaGroup.add(activadaRdb);
        activadaRdb.setText("Activada");
        activadaRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activadaRdbActionPerformed(evt);
            }
        });

        estadoMallaGroup.add(desactivadaRdb);
        desactivadaRdb.setText("Desactivada");
        desactivadaRdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desactivadaRdbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(activadaRdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desactivadaRdb)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activadaRdb)
                    .addComponent(desactivadaRdb))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jLabel8.setText("Semestre");

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });
        semestreCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semestreCmbActionPerformed(evt);
            }
        });

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        especialidadCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                especialidadCmbItemStateChanged(evt);
            }
        });

        periodoCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        periodoCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                periodoCmbItemStateChanged(evt);
            }
        });
        periodoCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodoCmbActionPerformed(evt);
            }
        });

        jLabel9.setText("Especialidad");

        jLabel10.setText("Periodo");

        gurdarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        gurdarBtn.setText("Guardar");
        gurdarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gurdarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        jLabel11.setText("Creditos Nota Empresa");

        creditosNotaEmpresaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditosNotaEmpresaTxtActionPerformed(evt);
            }
        });

        jLabel12.setText("Valor Minimo Promedio Materia");

        jLabel13.setText("Valor Minimo Asistencia");

        valorMinimoAsistenciaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorMinimoAsistenciaTxtActionPerformed(evt);
            }
        });

        jLabel14.setText("Valor de Ingreso Nota");

        jLabel15.setText("Valor Minimo Promedio Recuperacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                        .addComponent(validarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gurdarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarBtn)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(101, 101, 101)
                                        .addComponent(porcentajeNotaTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(75, 75, 75)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel13)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(77, 77, 77)
                                        .addComponent(porcentajeTutoriaIntegrada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel12)))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(valorMinimoPromedioNotaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(valorMinimoAsistenciaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(valorMinimoRecuperacionTxt)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(84, 84, 84)
                                        .addComponent(porcentajePonderadoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(75, 75, 75)
                                        .addComponent(jLabel14)
                                        .addGap(105, 105, 105))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(29, 29, 29)
                                        .addComponent(nombreMallaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel11)
                                        .addGap(98, 98, 98)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valorIngresoNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(creditosNotaEmpresaTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(totalCreditosCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(95, 95, 95)
                                            .addComponent(porcentajeNotaEmpresaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(totalCreditosTeoricaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(43, 43, 43)
                                        .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(periodoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreMallaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel11))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(porcentajePonderadoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(creditosNotaEmpresaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(valorIngresoNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(porcentajeTutoriaIntegrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(valorMinimoPromedioNotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(porcentajeNotaTeorica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valorMinimoRecuperacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valorMinimoAsistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(porcentajeNotaEmpresaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(periodoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(totalCreditosCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalCreditosTeoricaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(validarBtn)
                            .addComponent(gurdarBtn)
                            .addComponent(cancelarBtn))
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void periodoCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodoCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_periodoCmbActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        crud = new Crud();
        if (validaForm()) {
            if (validaValor()) {
                ocultaForm();
                validarBtn.setEnabled(false);
                this.gurdarBtn.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error en configuracion de la Malla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_validarBtnActionPerformed
    private void ocultaForm() {
        nombreMallaTxt.setEnabled(false);
        porcentajeNotaEmpresaTxt.setEnabled(false);
        porcentajeNotaTeorica.setEnabled(false);
        porcentajePonderadoNota.setEnabled(false);
        porcentajeTutoriaIntegrada.setEnabled(false);
        totalCreditosCiclo.setEnabled(false);
        totalCreditosTeoricaTxt.setEnabled(false);
        valorMinimoPromedioNotaTxt.setEnabled(false);
        semestreCmb.setEnabled(false);
        especialidadCmb.setEnabled(false);
        periodoCmb.setEnabled(false);
        activadaRdb.setEnabled(false);
        desactivadaRdb.setEnabled(false);
        valorIngresoNotaTxt.setEnabled(false);
        valorMinimoAsistenciaTxt.setEnabled(false);
        valorMinimoPromedioNotaTxt.setEnabled(false);
        valorMinimoRecuperacionTxt.setEnabled(false);
    }
    private void activadaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activadaRdbActionPerformed
        activada = Estado.AC.name();
    }//GEN-LAST:event_activadaRdbActionPerformed

    private void desactivadaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desactivadaRdbActionPerformed
        activada = Estado.DS.name();
    }//GEN-LAST:event_desactivadaRdbActionPerformed

    private void gurdarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gurdarBtnActionPerformed
        cargarDatos();
        if (idMalla == 0) {
            crud.insertarM("malla", campos);
            this.dispose();
        } else {
            crud.actualizarM("malla", "id_malla", idMalla, campos);
            this.dispose();
        }

    }//GEN-LAST:event_gurdarBtnActionPerformed

    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        Semestre semes = (Semestre) semestreCmb.getSelectedItem();
        semestre.setIdSemestre(semes.getIdSemestre());

    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_especialidadCmbItemStateChanged
        Especialidad espe = (Especialidad) especialidadCmb.getSelectedItem();
        especialidad.setIdEspecialidad(espe.getIdEspecialidad());
    }//GEN-LAST:event_especialidadCmbItemStateChanged

    private void periodoCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodoCmbItemStateChanged
        Periodo perio = (Periodo) periodoCmb.getSelectedItem();
        periodo.setIdPeriodo(perio.getIdPeriodo());
    }//GEN-LAST:event_periodoCmbItemStateChanged

    private void porcentajePonderadoNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajePonderadoNotaKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_porcentajePonderadoNotaKeyTyped

    private void totalCreditosCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCreditosCicloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCreditosCicloActionPerformed

    private void porcentajeTutoriaIntegradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeTutoriaIntegradaKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_porcentajeTutoriaIntegradaKeyTyped

    private void porcentajeNotaTeoricaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeNotaTeoricaKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_porcentajeNotaTeoricaKeyTyped

    private void porcentajeNotaEmpresaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_porcentajeNotaEmpresaTxtKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_porcentajeNotaEmpresaTxtKeyTyped

    private void totalCreditosTeoricaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCreditosTeoricaTxtKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_totalCreditosTeoricaTxtKeyTyped

    private void totalCreditosCicloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCreditosCicloKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_totalCreditosCicloKeyTyped

    private void valorMinimoAsistenciaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorMinimoAsistenciaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorMinimoAsistenciaTxtActionPerformed

    private void creditosNotaEmpresaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditosNotaEmpresaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditosNotaEmpresaTxtActionPerformed

    private void semestreCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semestreCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semestreCmbActionPerformed
    private boolean validaValor() {
        boolean resultado = true;
        malla.setPorcentajePonderacionNota(Integer.parseInt(porcentajePonderadoNota.getText()));
        malla.setPorcentajeTutoriaIntegrada(Integer.parseInt(porcentajeTutoriaIntegrada.getText()));
        malla.setPorcentajeNotaTeorica(Integer.parseInt(porcentajeNotaTeorica.getText()));
        malla.setPorcentajeNotaEmpresa(Integer.parseInt(porcentajeNotaEmpresaTxt.getText()));
        malla.setValorMinimoPromedio(Double.valueOf((valorMinimoPromedioNotaTxt.getText().replaceAll(",", "."))));
        malla.setCreditoCiclo(Integer.parseInt(totalCreditosCiclo.getText()));
        malla.setCreditoTeoria(Integer.parseInt(totalCreditosTeoricaTxt.getText()));
        malla.setValorMinimoAsistencia(Integer.parseInt(valorMinimoAsistenciaTxt.getText()));
        malla.setValorNota(Double.valueOf((valorIngresoNotaTxt.getText().replaceAll(",", "."))));
        malla.setValorRecuperacion(Double.valueOf((valorMinimoRecuperacionTxt.getText().replaceAll(",", "."))));
        int valorNotaEmpresa = malla.getCreditoCiclo() - malla.getCreditoTeoria();
        int sumaNotaPonderada = malla.getPorcentajePonderacionNota() + malla.getPorcentajeTutoriaIntegrada();
        int sumaNotaFinal = malla.getPorcentajeNotaEmpresa() + malla.getPorcentajeNotaTeorica();

        if (valorIngresoNotaTxt.getText().compareTo("20") != 0 && valorIngresoNotaTxt.getText().compareTo("10") != 0) {
            resultado = false;
        } else if (malla.getValorNota() != 20 && malla.getValorNota() != 10) {
            resultado = false;
        } else if (malla.getValorMinimoAsistencia() >= 100 || malla.getValorMinimoAsistencia() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 20 && malla.getValorMinimoPromedio() > 20 || malla.getValorNota() == 20 && malla.getValorMinimoPromedio() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 10 && malla.getValorRecuperacion() > 10 || malla.getValorNota() == 10 && malla.getValorMinimoPromedio() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 20 && malla.getValorRecuperacion() > 20 || malla.getValorNota() == 20 && malla.getValorRecuperacion() <= 0) {
            resultado = false;
        } else if (malla.getValorNota() == 10 && malla.getValorRecuperacion() > 10 || malla.getValorNota() == 10 && malla.getValorRecuperacion() <= 0) {
            resultado = false;
        } else if (valorNotaEmpresa <= 0) {
            resultado = false;
        } else if (sumaNotaPonderada > valor || sumaNotaPonderada < valor) {
            resultado = false;
        } else if (sumaNotaFinal > valor || sumaNotaFinal < valor) {
            resultado = false;
        } else {
            creditosNotaEmpresaTxt.setText(String.valueOf(valorNotaEmpresa));
            malla.setCreditoNotaEmpresa(valorNotaEmpresa);
        }

        return resultado;

    }

    private Map cargarDatos() {
        campos = new HashMap();
        Calendar cal = Calendar.getInstance();
        malla.setNombreMalla(nombreMallaTxt.getText().toUpperCase());
        malla.setEstado(activada);
        malla.setCreditoCiclo(Integer.valueOf(totalCreditosCiclo.getText()));
        malla.setCreditoTeoria(Integer.parseInt(totalCreditosTeoricaTxt.getText()));

        campos.put("nombre_malla", malla.getNombreMalla());
        campos.put("porc_nota_empresa", malla.getPorcentajeNotaEmpresa());
        campos.put("porc_nota_teorica", malla.getPorcentajeNotaTeorica());
        campos.put("porc_ponderado_nota", malla.getPorcentajePonderacionNota());
        campos.put("porc_integrada", malla.getPorcentajeTutoriaIntegrada());
        campos.put("cred_teorica", malla.getCreditoTeoria());
        campos.put("cred_ciclo", malla.getCreditoCiclo());
        campos.put("cred_nota_empresa", malla.getCreditoNotaEmpresa());
        campos.put("valor_min_promedio", malla.getValorMinimoPromedio());
        campos.put("cred_teorica_disp", malla.getCreditoCiclo());
        campos.put("valor_min_asistencia", malla.getValorMinimoAsistencia());
        campos.put("valor_min_recuperacion", malla.getValorRecuperacion());
        campos.put("valor_calf_nota", malla.getValorNota());
        campos.put("porcentaje", "100");
        campos.put("id1_especialidad", especialidad.getIdEspecialidad());
        campos.put("id1_periodo", periodo.getIdPeriodo());
        campos.put("id1_semestre", semestre.getIdSemestre());
        campos.put("estado", malla.getEstado());
        if (idMalla == 0) {
            campos.put("fecha_creacion", cal.getTime());
        } else {
            campos.put("fecha_creacion", malla.getFechaCreacion());
        }
        campos.put("fecha_modificacion", cal.getTime());
        return campos;
    }

    private void cargarEspecialidad() {
        try {

            resultSet = metodosGeneralesDao.cargaComboEspecialidad();
            while (resultSet.next()) {
                Especialidad espe = new Especialidad();
                espe.setEspecialidad(resultSet.getString("especialidad"));
                espe.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
                especialidadCmb.addItem(espe);

            }
        } catch (SQLException | NumberFormatException e) {
        }
    }

    private void cargarPeriodo() {
        try {
            resultSet = metodosGeneralesDao.cargaComboPeriodo();
            while (resultSet.next()) {
                Periodo perio = new Periodo();
                perio.setIdPeriodo(Integer.parseInt(resultSet.getString("id1_periodo")));
                perio.setNombrePeriodo(resultSet.getString("periodo"));
                periodoCmb.addItem(perio);
            }
        } catch (SQLException | NumberFormatException e) {
        }

    }

    private void cargarSemestre() {
        try {
            resultSet = metodosGeneralesDao.cargaComboSemestre();
            while (resultSet.next()) {
                Semestre semes = new Semestre();
                semes.setIdSemestre(resultSet.getInt("id1_semestre"));
                semes.setSemestre(resultSet.getString("semestre"));
                semestreCmb.addItem(semes);

            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private boolean validaForm() {
        boolean resultado = true;
        JTextField[] yu = {nombreMallaTxt,
            porcentajePonderadoNota,
            porcentajeNotaEmpresaTxt,
            porcentajeTutoriaIntegrada,
            porcentajeNotaTeorica,
            totalCreditosCiclo,
            totalCreditosTeoricaTxt,
            valorMinimoPromedioNotaTxt,
            valorMinimoAsistenciaTxt,
            valorIngresoNotaTxt,
            valorMinimoRecuperacionTxt};

        for (JTextField yu1 : yu) {
            if (yu1.getText().isEmpty() || yu1.getText().compareTo("0") == 0) {
                resultado = false;
                break;
            } else {
                resultado = true;
            }
        }
        if (estadoMallaGroup.isSelected(null)) {
            resultado = false;
        } else if (semestreCmb.getSelectedIndex() == 0 || especialidadCmb.getSelectedIndex() == 0 || periodoCmb.getSelectedIndex() == 0) {
            resultado = false;
        }

        return resultado;
    }

    private void validaNumero(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
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
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioMalla dialog = new FormularioMalla(frmMalla, true);
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
    private javax.swing.JRadioButton activadaRdb;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField creditosNotaEmpresaTxt;
    private javax.swing.JRadioButton desactivadaRdb;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.ButtonGroup estadoMallaGroup;
    private javax.swing.JButton gurdarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreMallaTxt;
    private javax.swing.JComboBox periodoCmb;
    private javax.swing.JTextField porcentajeNotaEmpresaTxt;
    private javax.swing.JTextField porcentajeNotaTeorica;
    private javax.swing.JTextField porcentajePonderadoNota;
    private javax.swing.JTextField porcentajeTutoriaIntegrada;
    private javax.swing.JComboBox semestreCmb;
    private javax.swing.JTextField totalCreditosCiclo;
    private javax.swing.JTextField totalCreditosTeoricaTxt;
    private javax.swing.JButton validarBtn;
    private javax.swing.JTextField valorIngresoNotaTxt;
    private javax.swing.JTextField valorMinimoAsistenciaTxt;
    private javax.swing.JTextField valorMinimoPromedioNotaTxt;
    private javax.swing.JTextField valorMinimoRecuperacionTxt;
    // End of variables declaration//GEN-END:variables
}
