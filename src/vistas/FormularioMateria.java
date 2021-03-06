package vistas;

import control.Crud;
import control.EnviaEmail;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logica.ConfiguracionMateriaDao;
import logica.DescripcionMateriaDao;
import logica.MateriaDao;
import logica.MetodosGeneralesDao;
import modelo.Campo;
import modelo.ConfiguracionMateria;
import modelo.DescripcionMateria;
import modelo.Eje;
import modelo.Especialidad;
import modelo.Estado;
import modelo.Configuracion;
import modelo.Malla;
import modelo.Materia;
import modelo.Profesor;
import modelo.Semestre;

/**
 *
 * @author andre_000
 */
public class FormularioMateria extends javax.swing.JDialog {

    public static FrmMateria frmMateria;
    private Integer idMateria;
    ResultSet resultSet;
    Crud crud;
    Map campos, campos1, campos2;
    Configuracion configuracion;
    Especialidad especialidad;
    Semestre semestre;
    Eje eje;
    Materia materia;
    Profesor profesor;
    Malla malla;
    MetodosGeneralesDao metodosGeneralesDao;
    private static final int valor = 100;
    private String respuesta;
    private int numeroCampos, numeroCreditos;
    private ConfiguracionMateria configuracionMateria;
    private DescripcionMateria descripcionMateria;
    private List<Campo> lista, lista1;
    private Campo campo, campo1;
    private String estado;

    public FormularioMateria(FrmMateria parent, boolean modal) {
        FormularioMateria.frmMateria = parent;
        this.setModal(modal);
        initComponents();
        metodosGeneralesDao = new MetodosGeneralesDao();
        ocultaCampos();
        cargarEspecialidad();
        cargarSemestre();
        cargarEje();
        cargarConfiguracion();
        cargaMalla();
        this.guardarBtn.setEnabled(false);
        semestre = new Semestre();
        especialidad = new Especialidad();
        eje = new Eje();
        configuracion = new Configuracion();
        malla = new Malla();
        materia = new Materia();
        profesor = new Profesor();
        descripcionMateria = new DescripcionMateria();
        configuracionMateria = new ConfiguracionMateria();
    }

    public FormularioMateria(FrmMateria parent, boolean modal, Integer idMateria) {
        FormularioMateria.frmMateria = parent;
        this.setModal(modal);
        initComponents();
        this.idMateria = idMateria;
        metodosGeneralesDao = new MetodosGeneralesDao();
        ocultaCampos();
        cargarEspecialidad();
        cargarSemestre();
        cargarEje();
        cargarConfiguracion();
        cargaProfesor();
        cargaMalla();
        this.guardarBtn.setEnabled(false);
        this.validarBtn.setEnabled(false);
        this.calcularBtn.setEnabled(false);
        semestre = new Semestre();
        especialidad = new Especialidad();
        eje = new Eje();
        configuracion = new Configuracion();
        malla = new Malla();
        materia = new Materia();
        profesor = new Profesor();
        descripcionMateria = new DescripcionMateria();
        configuracionMateria = new ConfiguracionMateria();
        if (idMateria > 0) {
            try {
                MateriaDao materiaDao = new MateriaDao();
                resultSet = materiaDao.consulta(idMateria);
                while (resultSet.next()) {
                    nombreMateriaTxt.setText(resultSet.getString("materia"));
                    creditosTxt.setText(resultSet.getString("creditos"));
                    estado = resultSet.getString("activa_mat");
                    if (estado.equals(Estado.AC.name())) {
                        activadaRdb.setSelected(true);

                    } else {
                        desactivadaRdb.setSelected(true);
                    }
                    materia.setEstado(estado);
                    materia.setIdMateria(Integer.parseInt(resultSet.getString("id1_nombre_materia")));
                    materia.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("f_crea")));
                    materia.setCreditos(Integer.parseInt(resultSet.getString("creditos")));

                    configuracion.setIdConfiguracion(Integer.valueOf(resultSet.getString("id_configuracion")));
                    configuracion.setDescripcion(resultSet.getString("descripcion"));
                    configuracionCmb.setSelectedItem(configuracion);

                    semestre.setIdSemestre(Integer.parseInt(resultSet.getString("id1_semestre")));
                    semestre.setSemestre(resultSet.getString("semestre"));
                    semestreCmb.setSelectedItem(semestre);

                    especialidad.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
                    especialidad.setEspecialidad(resultSet.getString("especialidad"));
                    especialidadCmb.setSelectedItem(especialidad);

                    eje.setIdEje(Integer.parseInt(resultSet.getString("id1_eje")));
                    eje.setNombreEje(resultSet.getString("nom_ejes"));
                    ejeCmb.setSelectedItem(eje);

                    profesor.setIdProfesor(Integer.parseInt(resultSet.getString("id1_profe")));
                    profesor.setNombreProfesor(resultSet.getString("nom_profe"));
                    profesor.setApellidoProfesor(resultSet.getString("apell_profe"));
                    profesoCmb.setSelectedItem(profesor);

                    malla.setIdMalla(resultSet.getInt("id_malla"));
                    malla.setNombreMalla(resultSet.getString("nombre_malla"));
                    mallaCmb.setSelectedItem(malla);

                    horasTxt.setText(resultSet.getString("numhora"));
                    numeroCamposTxt.setText(resultSet.getString("num_aporte"));
                    descripcionMateria.setIdDescripcionMateria(Integer.parseInt(resultSet.getString("id_desc_materia")));
                    configuracionMateria.setIdConfiguracionMateria(Integer.parseInt(resultSet.getString("id_config_materia")));
                    int camp = Integer.parseInt(numeroCamposTxt.getText());
                    cargaCampo(camp);
                    seteaConfigMateria(resultSet, camp);
                    seteaDescripcion(resultSet, camp);

                }

            } catch (SQLException | NumberFormatException e) {
            } catch (ParseException ex) {
                Logger.getLogger(FormularioMateria.class.getName()).log(Level.SEVERE, null, ex);
                EnviaEmail.enviaMail("javier.tec1989@gmail.com", ex.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estadoMallaGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        nombreMateriaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        creditosTxt = new javax.swing.JTextField();
        horasTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        activadaRdb = new javax.swing.JRadioButton();
        desactivadaRdb = new javax.swing.JRadioButton();
        semestreCmb = new javax.swing.JComboBox();
        especialidadCmb = new javax.swing.JComboBox();
        ejeCmb = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        numeroCamposTxt = new javax.swing.JTextField();
        cargarBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        valorlbl1 = new javax.swing.JLabel();
        valorlbl3 = new javax.swing.JLabel();
        valorlbl5 = new javax.swing.JLabel();
        valorlbl7 = new javax.swing.JLabel();
        valorlbl9 = new javax.swing.JLabel();
        aporteTxt1 = new javax.swing.JTextField();
        aporteTxt3 = new javax.swing.JTextField();
        aporteTxt5 = new javax.swing.JTextField();
        aporteTxt7 = new javax.swing.JTextField();
        aporteTxt9 = new javax.swing.JTextField();
        valorlbl2 = new javax.swing.JLabel();
        aporteTxt2 = new javax.swing.JTextField();
        aporteTxt4 = new javax.swing.JTextField();
        valorlbl4 = new javax.swing.JLabel();
        valorlbl6 = new javax.swing.JLabel();
        aporteTxt6 = new javax.swing.JTextField();
        aporteTxt8 = new javax.swing.JTextField();
        valorlbl8 = new javax.swing.JLabel();
        valorlbl10 = new javax.swing.JLabel();
        aporteTxt10 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        desLbl1 = new javax.swing.JLabel();
        aporteDescTxt1 = new javax.swing.JTextField();
        desLbl2 = new javax.swing.JLabel();
        aporteDscTxt2 = new javax.swing.JTextField();
        desLbl3 = new javax.swing.JLabel();
        aporteDescTxt3 = new javax.swing.JTextField();
        desLbl4 = new javax.swing.JLabel();
        aporteDesTxt4 = new javax.swing.JTextField();
        desLbl7 = new javax.swing.JLabel();
        aporteDscTxt5 = new javax.swing.JTextField();
        desLbl6 = new javax.swing.JLabel();
        aporteDscTxt6 = new javax.swing.JTextField();
        desLbl9 = new javax.swing.JLabel();
        aporteDscTxt7 = new javax.swing.JTextField();
        desLbl8 = new javax.swing.JLabel();
        aporteDscTxt8 = new javax.swing.JTextField();
        desLbl5 = new javax.swing.JLabel();
        aporteDscTxt9 = new javax.swing.JTextField();
        desLbl10 = new javax.swing.JLabel();
        aporteDscTxt10 = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        validarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        configuracionCmb = new javax.swing.JComboBox();
        calcularBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        profesoCmb = new javax.swing.JComboBox();
        mallaCmb = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FORMULARIO DE REGISTRO DE MATERIA");
        setIconImage(new ImageIcon(getClass().getResource("/recursos/iconPr.png")).getImage());

        jLabel1.setText("Nombre Materia");

        nombreMateriaTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreMateriaTxtKeyTyped(evt);
            }
        });

        jLabel2.setText("Creditos");

        creditosTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                creditosTxtKeyTyped(evt);
            }
        });

        horasTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                horasTxtKeyTyped(evt);
            }
        });

        jLabel3.setText("Numero de Horas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado de Materia"));

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
                .addContainerGap()
                .addComponent(activadaRdb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(desactivadaRdb)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activadaRdb)
                    .addComponent(desactivadaRdb))
                .addContainerGap())
        );

        semestreCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        semestreCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semestreCmbItemStateChanged(evt);
            }
        });

        especialidadCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        especialidadCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                especialidadCmbItemStateChanged(evt);
            }
        });

        ejeCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        ejeCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ejeCmbItemStateChanged(evt);
            }
        });

        jLabel7.setText("Eje");

        jLabel6.setText("Especialidad");

        jLabel5.setText("Semestre");

        jLabel4.setText("Configuracion");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuracion Materia"));

        jLabel8.setText("Numero de Aportes");

        numeroCamposTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroCamposTxtKeyTyped(evt);
            }
        });

        cargarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Lightning.png"))); // NOI18N
        cargarBtn.setText("Cargar");
        cargarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarBtnActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor por Aporte"));

        valorlbl1.setText("Aporte 1");

        valorlbl3.setText("Aporte 3");

        valorlbl5.setText("Aporte 5");

        valorlbl7.setText("Aporte 7");

        valorlbl9.setText("Aporte 9");

        aporteTxt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt1KeyTyped(evt);
            }
        });

        aporteTxt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt3KeyTyped(evt);
            }
        });

        aporteTxt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt5KeyTyped(evt);
            }
        });

        aporteTxt7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt7KeyTyped(evt);
            }
        });

        aporteTxt9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt9KeyTyped(evt);
            }
        });

        valorlbl2.setText("Aporte 2");

        aporteTxt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt2KeyTyped(evt);
            }
        });

        aporteTxt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt4KeyTyped(evt);
            }
        });

        valorlbl4.setText("Aporte 4");

        valorlbl6.setText("Aporte 6");

        aporteTxt6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt6KeyTyped(evt);
            }
        });

        aporteTxt8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aporteTxt8ActionPerformed(evt);
            }
        });
        aporteTxt8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt8KeyTyped(evt);
            }
        });

        valorlbl8.setText("Aporte 8");

        valorlbl10.setText("Aporte 10");

        aporteTxt10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteTxt10KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(valorlbl9)
                        .addGap(18, 18, 18)
                        .addComponent(aporteTxt9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(valorlbl10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(aporteTxt10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(valorlbl1)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(valorlbl2)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(valorlbl3)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(valorlbl4)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(valorlbl5)
                                .addGap(19, 19, 19)
                                .addComponent(aporteTxt5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valorlbl6)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(valorlbl7)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valorlbl8)
                                .addGap(18, 18, 18)
                                .addComponent(aporteTxt8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valorlbl1)
                    .addComponent(aporteTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorlbl2)
                    .addComponent(aporteTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aporteTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(valorlbl3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(valorlbl4))
                    .addComponent(aporteTxt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aporteTxt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(valorlbl5)
                        .addComponent(valorlbl6))
                    .addComponent(aporteTxt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aporteTxt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(valorlbl8)
                        .addComponent(valorlbl7))
                    .addComponent(aporteTxt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aporteTxt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorlbl10)
                    .addComponent(aporteTxt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorlbl9))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripcion por Aporte"));

        desLbl1.setText("Aporte 1");

        aporteDescTxt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDescTxt1KeyTyped(evt);
            }
        });

        desLbl2.setText("Aporte 2");

        aporteDscTxt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt2KeyTyped(evt);
            }
        });

        desLbl3.setText("Aporte 3");

        aporteDescTxt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDescTxt3KeyTyped(evt);
            }
        });

        desLbl4.setText("Aporte 4");

        aporteDesTxt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aporteDesTxt4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDesTxt4KeyTyped(evt);
            }
        });

        desLbl7.setText("Aporte 7");

        aporteDscTxt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt5KeyTyped(evt);
            }
        });

        desLbl6.setText("Aporte 6");

        aporteDscTxt6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt6KeyTyped(evt);
            }
        });

        desLbl9.setText("Aporte 9");

        aporteDscTxt7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt7KeyTyped(evt);
            }
        });

        desLbl8.setText("Aporte 8");

        aporteDscTxt8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aporteDscTxt8ActionPerformed(evt);
            }
        });
        aporteDscTxt8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt8KeyTyped(evt);
            }
        });

        desLbl5.setText("Aporte 5");

        aporteDscTxt9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt9KeyTyped(evt);
            }
        });

        desLbl10.setText("Aporte 10");

        aporteDscTxt10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                aporteDscTxt10KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(desLbl5)
                        .addGap(18, 18, 18)
                        .addComponent(aporteDscTxt5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(desLbl6)
                        .addGap(32, 32, 32)
                        .addComponent(aporteDscTxt6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(desLbl3)
                                .addGap(18, 18, 18)
                                .addComponent(aporteDescTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(desLbl4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(desLbl1)
                                .addGap(18, 18, 18)
                                .addComponent(aporteDescTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(desLbl2)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aporteDscTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aporteDesTxt4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(desLbl7)
                                .addGap(18, 18, 18)
                                .addComponent(aporteDscTxt7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(desLbl8))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(desLbl9)
                                .addGap(18, 18, 18)
                                .addComponent(aporteDscTxt9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(desLbl10)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aporteDscTxt10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aporteDscTxt8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aporteDescTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desLbl1)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(desLbl2)
                                .addComponent(aporteDscTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aporteDescTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(desLbl4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desLbl3)
                            .addComponent(aporteDesTxt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aporteDscTxt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(desLbl6)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(desLbl5)
                        .addComponent(aporteDscTxt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aporteDscTxt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desLbl7)
                    .addComponent(desLbl8)
                    .addComponent(aporteDscTxt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desLbl9)
                    .addComponent(aporteDscTxt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desLbl10)
                    .addComponent(aporteDscTxt10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(numeroCamposTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cargarBtn))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8))
                            .addComponent(numeroCamposTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cargarBtn)))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/CD.png"))); // NOI18N
        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        validarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Apply.png"))); // NOI18N
        validarBtn.setText("Validar");
        validarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Delete.png"))); // NOI18N
        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        configuracionCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        configuracionCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                configuracionCmbItemStateChanged(evt);
            }
        });

        calcularBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Calculator.png"))); // NOI18N
        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("Profesor");

        profesoCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        profesoCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                profesoCmbItemStateChanged(evt);
            }
        });

        mallaCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        mallaCmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mallaCmbItemStateChanged(evt);
            }
        });

        jLabel9.setText("Malla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(nombreMateriaTxt))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(creditosTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(horasTxt)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(semestreCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ejeCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(especialidadCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(configuracionCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profesoCmb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mallaCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(validarBtn)
                        .addGap(30, 30, 30)
                        .addComponent(calcularBtn)
                        .addGap(31, 31, 31)
                        .addComponent(guardarBtn)
                        .addGap(18, 18, 18)
                        .addComponent(cancelarBtn)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombreMateriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(creditosTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(horasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mallaCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(configuracionCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(semestreCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(especialidadCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(5, 5, 5))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(validarBtn)
                                .addComponent(guardarBtn)
                                .addComponent(cancelarBtn)
                                .addComponent(calcularBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(ejeCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profesoCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aporteDscTxt8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aporteDscTxt8ActionPerformed

    }//GEN-LAST:event_aporteDscTxt8ActionPerformed

    private void validarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validarBtnActionPerformed
        if (validaForm()) {
            ocultaForm();
            guardarBtn.setEnabled(true);
            validarBtn.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Error verifique que el formulario este correcto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_validarBtnActionPerformed


    private void aporteTxt8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aporteTxt8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aporteTxt8ActionPerformed

    private void cargarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarBtnActionPerformed
        if (!numeroCamposTxt.getText().isEmpty()) {
            if (Integer.parseInt(numeroCamposTxt.getText()) == 0) {
                JOptionPane.showMessageDialog(null, "Error numero no admitido", "Error", JOptionPane.ERROR_MESSAGE);
                numeroCamposTxt.setText(null);
            } else {
                if (Integer.parseInt(numeroCamposTxt.getText()) > 10) {
                    JOptionPane.showMessageDialog(null, "Error numero no admitido", "Error", JOptionPane.ERROR_MESSAGE);
                    numeroCamposTxt.setText(null);
                } else {
                    cargaCampo(Integer.parseInt(numeroCamposTxt.getText()));
                    calcularBtn.setEnabled(true);
                    cargarBtn.setEnabled(false);
                    numeroCamposTxt.setEnabled(false);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error campos valor vacio", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_cargarBtnActionPerformed

    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        numeroCampos = Integer.parseInt(numeroCamposTxt.getText());
        if (validaSuma(numeroCampos) && validaDescripcion(numeroCampos)) {
            validarBtn.setEnabled(true);
            ocultaCampos();
            numeroCamposTxt.setEnabled(false);
            cargarBtn.setEnabled(false);
            calcularBtn.setEnabled(false);
        } else {
            if (respuesta.equals("descripcion")) {
                guardarBtn.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error campos de descripcion de nota vacios", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (respuesta.equals("vacio")) {
                guardarBtn.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error campos de valores vacios", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (respuesta.equals("suma")) {
                guardarBtn.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error valor incorrecta para configuracion", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_calcularBtnActionPerformed

    private void numeroCamposTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroCamposTxtKeyTyped
        validaNumero(evt);
        if (numeroCamposTxt.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_numeroCamposTxtKeyTyped

    private void aporteTxt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt1KeyTyped
        validaNumero(evt);
        if (aporteTxt1.getText().length() >= 3) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt1KeyTyped

    private void aporteTxt2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt2KeyTyped
        validaNumero(evt);
        if (aporteTxt2.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt2KeyTyped

    private void aporteTxt3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt3KeyTyped
        validaNumero(evt);
        if (aporteTxt3.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt3KeyTyped

    private void aporteTxt4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt4KeyTyped
        validaNumero(evt);
        if (aporteTxt4.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt4KeyTyped

    private void aporteTxt5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt5KeyTyped
        validaNumero(evt);
        if (aporteTxt5.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt5KeyTyped

    private void aporteTxt6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt6KeyTyped
        validaNumero(evt);
        if (aporteTxt6.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt6KeyTyped

    private void aporteTxt7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt7KeyTyped
        validaNumero(evt);
        if (aporteTxt7.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt7KeyTyped

    private void aporteTxt8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt8KeyTyped
        validaNumero(evt);
        if (aporteTxt8.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt8KeyTyped

    private void aporteTxt9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt9KeyTyped
        validaNumero(evt);
        if (aporteTxt9.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt9KeyTyped

    private void aporteTxt10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteTxt10KeyTyped
        validaNumero(evt);
        if (aporteTxt10.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_aporteTxt10KeyTyped

    private void aporteDescTxt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDescTxt1KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDescTxt1KeyTyped

    private void aporteDscTxt2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt2KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt2KeyTyped

    private void aporteDescTxt3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDescTxt3KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDescTxt3KeyTyped

    private void aporteDesTxt4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDesTxt4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_aporteDesTxt4KeyReleased

    private void aporteDesTxt4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDesTxt4KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDesTxt4KeyTyped

    private void aporteDscTxt5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt5KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt5KeyTyped

    private void aporteDscTxt6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt6KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt6KeyTyped

    private void aporteDscTxt7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt7KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt7KeyTyped

    private void aporteDscTxt8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt8KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt8KeyTyped

    private void aporteDscTxt9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt9KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt9KeyTyped

    private void aporteDscTxt10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aporteDscTxt10KeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_aporteDscTxt10KeyTyped

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        try {
            crud = new Crud();
            //inicia carga de datos para tabla configmateria
            cargarDatosMateriaLista(numeroCampos);
            campos = new HashMap();
            for (@SuppressWarnings("LocalVariableHidesMemberVariable") Campo lista1 : lista) {
                campos.put(lista1.getLlave(), lista1.getObjeto());
            }
            configuracionMateria.setNumeroAportes(numeroCamposTxt.getText());
            campos.put("num_aporte", configuracionMateria.getNumeroAportes());
            if (idMateria == 0) {
                crud.insertar("config_materia", campos, Ingreso.getUsuario().getNombre());
            } else {
                crud.actualizar("config_materia", "id_config_materia", configuracionMateria.getIdConfiguracionMateria(), campos, Ingreso.getUsuario().getNombre());
            }

            //inicia carga de datos para tabla descripcion materia
            if (idMateria == 0) {
                ConfiguracionMateriaDao cmd = new ConfiguracionMateriaDao();
                configuracionMateria.setIdConfiguracionMateria(cmd.cargaSecuencialMateria());
            } else {
                configuracionMateria.setIdConfiguracionMateria(configuracionMateria.getIdConfiguracionMateria());
            }

            cargarDescripcionLista(numeroCampos);
            campos1 = new HashMap();
            for (Campo lista2 : lista1) {
                campos1.put(lista2.getLlave(), lista2.getObjeto1());
            }

            if (idMateria == 0) {
                descripcionMateria.setIdConfiguracionMateria(configuracionMateria.getIdConfiguracionMateria());
            } else {
                descripcionMateria.setIdConfiguracionMateria(descripcionMateria.getIdDescripcionMateria());
            }
            campos1.put("id_config_materia", descripcionMateria.getIdConfiguracionMateria());
            if (idMateria == 0) {
                crud.insertar("desc_materia", campos1, Ingreso.getUsuario().getNombre());
            } else {
                crud.actualizar("desc_materia", "id_desc_materia", descripcionMateria.getIdConfiguracionMateria(), campos1, Ingreso.getUsuario().getNombre());
            }

            //inicia carga de datos para tabla materia
            cargaDatos();
            if (idMateria == 0) {
                crud.insertarM("nombre_materia", campos2, Ingreso.getUsuario().getNombre());
                this.dispose();
            } else {
                crud.actualizarM("nombre_materia", "id1_nombre_materia", materia.getIdMateria(), campos2, Ingreso.getUsuario().getNombre());
                this.dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar la informacion", "Error", JOptionPane.ERROR_MESSAGE);
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }


    }//GEN-LAST:event_guardarBtnActionPerformed
    private Map cargaDatos() {
        try {
            campos2 = new HashMap();
            Calendar cal = Calendar.getInstance();
            materia.setNombreMateria(nombreMateriaTxt.getText().toUpperCase());
            materia.setCreditos(Integer.parseInt(creditosTxt.getText()));
            materia.setNumeroHoras(Integer.parseInt(horasTxt.getText()));
            materia.setIdMalla(malla.getIdMalla());
            materia.setIdConfiguracion(configuracion.getIdConfiguracion());
            materia.setIdSemestre(semestre.getIdSemestre());
            materia.setIdEspecialidad(especialidad.getIdEspecialidad());
            materia.setIdProfesor(profesor.getIdProfesor());
            materia.setIdEje(eje.getIdEje());
            materia.setIdDescripcion(descripcionMateria.getIdDescripcionMateria());
            materia.setIdConfiguracionMateria(configuracionMateria.getIdConfiguracionMateria());
            if (idMateria == 0) {
                materia.setFechaCreacion(cal.getTime());
                DescripcionMateriaDao dao = new DescripcionMateriaDao();
                descripcionMateria.setIdDescripcionMateria(dao.cargaSecuencialMateria());
            } else {
                materia.setFechaCreacion(materia.getFechaCreacion());
            }
            materia.setFechaModificacion(cal.getTime());
            campos2.put("id_materia", " ");
            campos2.put("id_semestre", " ");
            campos2.put("id_especial", " ");
            campos2.put("materia", materia.getNombreMateria());
            campos2.put("creditos", materia.getCreditos());
            campos2.put("numhora", materia.getNumeroHoras());
            campos2.put("mat_sec", " ");
            campos2.put("activa_mat", materia.getEstado());
            campos2.put("AUX", 0);
            campos2.put("ID_NUM", 0);
            campos2.put("id1_semestre", semestre.getIdSemestre());
            campos2.put("id1_especialidad", especialidad.getIdEspecialidad());
            campos2.put("id1_eje", eje.getIdEje());
            campos2.put("id_ciclo", 0);
            campos2.put("f_crea", materia.getFechaCreacion());
            campos2.put("f_modifica", materia.getFechaModificacion());
            campos2.put("id1_profe", profesor.getIdProfesor());
            campos2.put("alias", " ");
            campos2.put("materia_antes", materia.getNombreMateria());
            campos2.put("id_malla", malla.getIdMalla());
            campos2.put("id_configuracion", configuracion.getIdConfiguracion());
            campos2.put("id_config_materia", configuracionMateria.getIdConfiguracionMateria());
            campos2.put("id_desc_materia", descripcionMateria.getIdDescripcionMateria());
            return campos2;
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
            return null;

        }

    }
    private void configuracionCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_configuracionCmbItemStateChanged
//        if (idMateria == 0) {
//            try {
//                ConfiguracionDao configDao = new ConfiguracionDao();
//                Configuracion config = (Configuracion) configuracionCmb.getSelectedItem();
//                this.configuracion.setIdConfiguracion(config.getIdConfiguracion());
//                this.configuracion.setDescripcion(config.getDescripcion());
//                resultSet = configDao.consulta(config.getIdConfiguracion());
//                while (resultSet.next()) {
//                    especialidad.setEspecialidad(resultSet.getString("especialidad"));
//                    especialidad.setIdEspecialidad(Integer.parseInt(resultSet.getString("id1_especialidad")));
//                    especialidadCmb.setSelectedItem(especialidad);
//                    semestre.setIdSemestre(Integer.valueOf(resultSet.getString("id1_semestre")));
//                    semestre.setSemestre(resultSet.getString("semestre"));
//                    semestreCmb.setSelectedItem(semestre);
//                }
//            } catch (SQLException | NumberFormatException e) {
//            }
//
//        } else {
//            Configuracion config = (Configuracion) configuracionCmb.getSelectedItem();
//            config.setIdConfiguracion(config.getIdConfiguracion());
//
//        }
        try {
            Configuracion config = (Configuracion) configuracionCmb.getSelectedItem();
            configuracion.setIdConfiguracion(config.getIdConfiguracion());
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.comn", e.toString());
        }

    }//GEN-LAST:event_configuracionCmbItemStateChanged
    private ConfiguracionMateria cargaConfigMateria(int numCampos) {
        switch (numCampos) {
            case 1:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                break;
            case 2:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                break;
            case 3:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                break;
            case 4:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                break;
            case 5:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                configuracionMateria.setAporte5(Double.valueOf(aporteTxt5.getText()));
                break;
            case 6:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                configuracionMateria.setAporte5(Double.valueOf(aporteTxt5.getText()));
                configuracionMateria.setAporte6(Double.valueOf(aporteTxt6.getText()));
                break;
            case 7:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                configuracionMateria.setAporte5(Double.valueOf(aporteTxt5.getText()));
                configuracionMateria.setAporte6(Double.valueOf(aporteTxt6.getText()));
                configuracionMateria.setAporte7(Double.valueOf(aporteTxt7.getText()));
                break;
            case 8:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                configuracionMateria.setAporte5(Double.valueOf(aporteTxt5.getText()));
                configuracionMateria.setAporte6(Double.valueOf(aporteTxt6.getText()));
                configuracionMateria.setAporte7(Double.valueOf(aporteTxt7.getText()));
                configuracionMateria.setAporte8(Double.valueOf(aporteTxt8.getText()));
                break;
            case 9:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                configuracionMateria.setAporte5(Double.valueOf(aporteTxt5.getText()));
                configuracionMateria.setAporte6(Double.valueOf(aporteTxt6.getText()));
                configuracionMateria.setAporte7(Double.valueOf(aporteTxt7.getText()));
                configuracionMateria.setAporte8(Double.valueOf(aporteTxt8.getText()));
                configuracionMateria.setAporte9(Double.valueOf(aporteTxt9.getText()));
                break;
            case 10:
                configuracionMateria.setAporte1(Double.valueOf(aporteTxt1.getText()));
                configuracionMateria.setAporte2(Double.valueOf(aporteTxt2.getText()));
                configuracionMateria.setAporte3(Double.valueOf(aporteTxt3.getText()));
                configuracionMateria.setAporte4(Double.valueOf(aporteTxt4.getText()));
                configuracionMateria.setAporte5(Double.valueOf(aporteTxt5.getText()));
                configuracionMateria.setAporte6(Double.valueOf(aporteTxt6.getText()));
                configuracionMateria.setAporte7(Double.valueOf(aporteTxt7.getText()));
                configuracionMateria.setAporte8(Double.valueOf(aporteTxt8.getText()));
                configuracionMateria.setAporte9(Double.valueOf(aporteTxt9.getText()));
                configuracionMateria.setAporte10(Double.valueOf(aporteTxt10.getText()));
                break;
        }

        return configuracionMateria;
    }

    private void seteaConfigMateria(ResultSet resultSet, int numCampos) {
        try {
            switch (numCampos) {
                case 1:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    break;
                case 2:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    break;
                case 3:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    break;
                case 4:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    break;
                case 5:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    aporteTxt5.setText(resultSet.getString("aporte5"));
                    break;
                case 6:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    aporteTxt5.setText(resultSet.getString("aporte5"));
                    aporteTxt6.setText(resultSet.getString("aporte6"));
                    break;
                case 7:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    aporteTxt5.setText(resultSet.getString("aporte5"));
                    aporteTxt6.setText(resultSet.getString("aporte6"));
                    aporteTxt7.setText(resultSet.getString("aporte7"));
                    break;
                case 8:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    aporteTxt5.setText(resultSet.getString("aporte5"));
                    aporteTxt6.setText(resultSet.getString("aporte6"));
                    aporteTxt7.setText(resultSet.getString("aporte7"));
                    aporteTxt8.setText(resultSet.getString("aporte8"));
                    break;
                case 9:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    aporteTxt5.setText(resultSet.getString("aporte5"));
                    aporteTxt6.setText(resultSet.getString("aporte6"));
                    aporteTxt7.setText(resultSet.getString("aporte7"));
                    aporteTxt8.setText(resultSet.getString("aporte8"));
                    aporteTxt9.setText(resultSet.getString("aporte9"));
                    break;
                case 10:
                    aporteTxt1.setText(resultSet.getString("aporte1"));
                    aporteTxt2.setText(resultSet.getString("aporte2"));
                    aporteTxt3.setText(resultSet.getString("aporte3"));
                    aporteTxt4.setText(resultSet.getString("aporte4"));
                    aporteTxt5.setText(resultSet.getString("aporte5"));
                    aporteTxt6.setText(resultSet.getString("aporte6"));
                    aporteTxt7.setText(resultSet.getString("aporte7"));
                    aporteTxt8.setText(resultSet.getString("aporte8"));
                    aporteTxt9.setText(resultSet.getString("aporte9"));
                    aporteTxt10.setText(resultSet.getString("aporte10"));
                    break;
            }

        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }
    }

    private DescripcionMateria cargaDescripcion(int numCampos) {

        switch (numCampos) {
            case 1:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                break;
            case 2:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                break;
            case 3:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                break;
            case 4:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                break;
            case 5:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                descripcionMateria.setDescripcion5(aporteDscTxt5.getText().toUpperCase());
                break;
            case 6:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                descripcionMateria.setDescripcion5(aporteDscTxt5.getText().toUpperCase());
                descripcionMateria.setDescripcion6(aporteDscTxt6.getText().toUpperCase());
                break;
            case 7:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                descripcionMateria.setDescripcion5(aporteDscTxt5.getText().toUpperCase());
                descripcionMateria.setDescripcion6(aporteDscTxt6.getText().toUpperCase());
                descripcionMateria.setDescripcion7(aporteDscTxt7.getText().toUpperCase());
                break;
            case 8:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                descripcionMateria.setDescripcion5(aporteDscTxt5.getText().toUpperCase());
                descripcionMateria.setDescripcion6(aporteDscTxt6.getText().toUpperCase());
                descripcionMateria.setDescripcion7(aporteDscTxt7.getText().toUpperCase());
                descripcionMateria.setDescripcion8(aporteDscTxt8.getText().toUpperCase());
                break;
            case 9:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                descripcionMateria.setDescripcion5(aporteDscTxt5.getText().toUpperCase());
                descripcionMateria.setDescripcion6(aporteDscTxt6.getText().toUpperCase());
                descripcionMateria.setDescripcion7(aporteDscTxt7.getText().toUpperCase());
                descripcionMateria.setDescripcion8(aporteDscTxt8.getText().toUpperCase());
                descripcionMateria.setDescripcion9(aporteDscTxt9.getText().toUpperCase());
                break;
            case 10:
                descripcionMateria.setDescripcion1(aporteDescTxt1.getText().toUpperCase());
                descripcionMateria.setDescripcion2(aporteDscTxt2.getText().toUpperCase());
                descripcionMateria.setDescripcion3(aporteDescTxt3.getText().toUpperCase());
                descripcionMateria.setDescripcion4(aporteDesTxt4.getText().toUpperCase());
                descripcionMateria.setDescripcion5(aporteDscTxt5.getText().toUpperCase());
                descripcionMateria.setDescripcion6(aporteDscTxt6.getText().toUpperCase());
                descripcionMateria.setDescripcion7(aporteDscTxt7.getText().toUpperCase());
                descripcionMateria.setDescripcion8(aporteDscTxt8.getText().toUpperCase());
                descripcionMateria.setDescripcion9(aporteDscTxt9.getText().toUpperCase());
                descripcionMateria.setDescripcion10(aporteDscTxt10.getText().toUpperCase());
                break;
        }
        return descripcionMateria;
    }

    private DescripcionMateria seteaDescripcion(ResultSet resultSet, int numCampos) {
        try {
            switch (numCampos) {
                case 1:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    break;
                case 2:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    break;
                case 3:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    break;
                case 4:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    break;
                case 5:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    aporteDscTxt5.setText(resultSet.getString("dm.aporte5"));
                    break;
                case 6:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    aporteDscTxt5.setText(resultSet.getString("dm.aporte5"));
                    aporteDscTxt6.setText(resultSet.getString("dm.aporte6"));
                    break;
                case 7:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    aporteDscTxt5.setText(resultSet.getString("dm.aporte5"));
                    aporteDscTxt6.setText(resultSet.getString("dm.aporte6"));
                    aporteDscTxt7.setText(resultSet.getString("dm.aporte7"));
                    break;
                case 8:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    aporteDscTxt5.setText(resultSet.getString("dm.aporte5"));
                    aporteDscTxt6.setText(resultSet.getString("dm.aporte6"));
                    aporteDscTxt7.setText(resultSet.getString("dm.aporte7"));
                    aporteDscTxt8.setText(resultSet.getString("dm.aporte8"));
                    break;
                case 9:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    aporteDscTxt5.setText(resultSet.getString("dm.aporte5"));
                    aporteDscTxt6.setText(resultSet.getString("dm.aporte6"));
                    aporteDscTxt7.setText(resultSet.getString("dm.aporte7"));
                    aporteDscTxt8.setText(resultSet.getString("dm.aporte8"));
                    aporteDscTxt9.setText(resultSet.getString("dm.aporte9"));
                    break;
                case 10:
                    aporteDescTxt1.setText(resultSet.getString("dm.aporte1"));
                    aporteDscTxt2.setText(resultSet.getString("dm.aporte2"));
                    aporteDescTxt3.setText(resultSet.getString("dm.aporte3"));
                    aporteDesTxt4.setText(resultSet.getString("dm.aporte4"));
                    aporteDscTxt5.setText(resultSet.getString("dm.aporte5"));
                    aporteDscTxt6.setText(resultSet.getString("dm.aporte6"));
                    aporteDscTxt7.setText(resultSet.getString("dm.aporte7"));
                    aporteDscTxt8.setText(resultSet.getString("dm.aporte8"));
                    aporteDscTxt9.setText(resultSet.getString("dm.aporte9"));
                    aporteDscTxt10.setText(resultSet.getString("dm.aporte10"));
                    break;
            }
        } catch (Exception e) {
        }

        return descripcionMateria;
    }

    private List<Campo> cargarDatosMateriaLista(int numCampos) {
        cargaConfigMateria(numCampos);
        lista = new ArrayList<>();
        Double[] dato = {configuracionMateria.getAporte1(), configuracionMateria.getAporte2(), configuracionMateria.getAporte3(), configuracionMateria.getAporte4(), configuracionMateria.getAporte5(),
            configuracionMateria.getAporte6(), configuracionMateria.getAporte7(), configuracionMateria.getAporte8(),
            configuracionMateria.getAporte9(), configuracionMateria.getAporte10()};
        String[] llave = {"aporte1", "aporte2", "aporte3", "aporte4", "aporte5", "aporte6",
            "aporte7", "aporte8", "aporte9", "aporte10"};
        int i = 0;
        for (Double dato1 : dato) {
            if (i < numCampos) {
                campo = new Campo();
                campo.setLlave(llave[i]);
                campo.setObjeto(dato1);
                lista.add(campo);
            }
            if (i >= numCampos) {
                break;
            }
            i++;
        }
        return lista;
    }

    private List<Campo> cargarDescripcionLista(int numCampos) {
        cargaDescripcion(numCampos);
        lista1 = new ArrayList<>();
        String[] descripcion = {descripcionMateria.getDescripcion1(), descripcionMateria.getDescripcion2(),
            descripcionMateria.getDescripcion3(), descripcionMateria.getDescripcion4(), descripcionMateria.getDescripcion5(),
            descripcionMateria.getDescripcion6(), descripcionMateria.getDescripcion7(), descripcionMateria.getDescripcion8(),
            descripcionMateria.getDescripcion9(), descripcionMateria.getDescripcion10()};
        String[] llave = {"aporte1", "aporte2", "aporte3", "aporte4", "aporte5", "aporte6",
            "aporte7", "aporte8", "aporte9", "aporte10"};
        int i = 0;
        for (String llave1 : llave) {
            if (i < numCampos) {
                campo1 = new Campo();
                campo1.setLlave(llave1);
                campo1.setObjeto1(descripcion[i]);
                lista1.add(campo1);
            }
            if (i >= numCampos) {
                break;
            }
            i++;
        }
        return lista1;
    }
    private void semestreCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semestreCmbItemStateChanged
        try {
            Semestre semes = (Semestre) semestreCmb.getSelectedItem();
            semestre.setIdSemestre(semes.getIdSemestre());
            semestre.setSemestre(semes.getSemestre());
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

    }//GEN-LAST:event_semestreCmbItemStateChanged

    private void especialidadCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_especialidadCmbItemStateChanged
        try {
            Especialidad espe = (Especialidad) especialidadCmb.getSelectedItem();
            especialidad.setIdEspecialidad(espe.getIdEspecialidad());
            especialidad.setEspecialidad(espe.getEspecialidad());
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

    }//GEN-LAST:event_especialidadCmbItemStateChanged

    private void activadaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activadaRdbActionPerformed
        materia.setEstado(Estado.AC.name());
    }//GEN-LAST:event_activadaRdbActionPerformed

    private void desactivadaRdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desactivadaRdbActionPerformed
        materia.setEstado(Estado.DS.name());
    }//GEN-LAST:event_desactivadaRdbActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void profesoCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_profesoCmbItemStateChanged
        try {
            Profesor profe = (Profesor) profesoCmb.getSelectedItem();
            profesor.setIdProfesor(profe.getIdProfesor());

        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

    }//GEN-LAST:event_profesoCmbItemStateChanged

    private void ejeCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ejeCmbItemStateChanged
        Eje j = (Eje) ejeCmb.getSelectedItem();
        eje.setIdEje(j.getIdEje());
    }//GEN-LAST:event_ejeCmbItemStateChanged

    private void creditosTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditosTxtKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_creditosTxtKeyTyped

    private void horasTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_horasTxtKeyTyped
        validaNumero(evt);
    }//GEN-LAST:event_horasTxtKeyTyped

    private void nombreMateriaTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreMateriaTxtKeyTyped
        validaLetras(evt);
    }//GEN-LAST:event_nombreMateriaTxtKeyTyped

    private void mallaCmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mallaCmbItemStateChanged
        Malla m = (Malla) mallaCmb.getSelectedItem();
        malla.setIdMalla(m.getIdMalla());
    }//GEN-LAST:event_mallaCmbItemStateChanged
    private void validaNumero(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }

    private void validaLetras(java.awt.event.KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car == KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }

    private void ocultaCampos() {
        JTextField[] camp = {aporteTxt1, aporteTxt2, aporteTxt3, aporteTxt4, aporteTxt5, aporteTxt6,
            aporteTxt7, aporteTxt8, aporteTxt9, aporteTxt10, aporteDescTxt1, aporteDscTxt2,
            aporteDescTxt3, aporteDesTxt4, aporteDscTxt5, aporteDscTxt6, aporteDscTxt7,
            aporteDscTxt8, aporteDscTxt9, aporteDscTxt10};

        for (JTextField campo3 : camp) {
            campo3.setEnabled(false);
        }

    }

    private void cargaCampo(int valorCampos) {
        JTextField[] camp = {aporteTxt1, aporteTxt2, aporteTxt3, aporteTxt4, aporteTxt5, aporteTxt6,
            aporteTxt7, aporteTxt8, aporteTxt9, aporteTxt10};
        int i = 0;
        for (JTextField campo4 : camp) {
            if (i < valorCampos) {
                campo4.setEnabled(true);
                i++;
            }
        }
        JTextField[] desc = {aporteDescTxt1, aporteDscTxt2, aporteDescTxt3,
            aporteDesTxt4, aporteDscTxt5, aporteDscTxt6, aporteDscTxt7,
            aporteDscTxt8, aporteDscTxt9, aporteDscTxt10};
        int y = 0;
        for (JTextField desc1 : desc) {
            if (y < valorCampos) {
                desc1.setEnabled(true);
                y++;
            }
        }

    }

    private boolean validaSuma(int valorNum) {
        boolean resultado = true;
        try {
            JTextField[] camp = {aporteTxt1, aporteTxt2, aporteTxt3, aporteTxt4, aporteTxt5, aporteTxt6,
                aporteTxt7, aporteTxt8, aporteTxt9, aporteTxt10};
            int val1 = 0, val2 = 0, val3 = 0, val4 = 0, val5 = 0, val6 = 0, val7 = 0, val8 = 0, val9 = 0, val10 = 0;
            int[] constante = {val1, val2, val3, val4, val5, val6, val7, val8, val9, val10};
            int i = 0;
            int y = 0;
            int suma = 0;
            for (JTextField campo5 : camp) {
                if (i < valorNum) {
                    if (!campo5.getText().trim().isEmpty()) {
                        constante[y] = Integer.parseInt(campo5.getText());
                        i++;
                        y++;
                    } else {
                        respuesta = "vacio";
                        resultado = false;
                        break;
                    }
                }
            }
            int x = 0;
            if (resultado) {
                for (int d : constante) {
                    if (x < valorNum) {
                        suma = suma + constante[x];
                        x++;
                    }
                }
                if (suma < valor || suma > valor) {
                    resultado = false;
                    respuesta = "suma";
                }
                if (suma == valor) {
                    resultado = true;
                }
            }
        } catch (Exception e) {
            EnviaEmail.enviaMail("javier.tec1989@gmail.com", e.toString());
        }

        return resultado;

    }

    private boolean validaDescripcion(int valorCampos) {
        boolean resultado = true;
        JTextField[] camp = {aporteDescTxt1, aporteDscTxt2, aporteDescTxt3, aporteDesTxt4,
            aporteDscTxt5, aporteDscTxt6, aporteDscTxt7, aporteDscTxt8, aporteDscTxt9, aporteDscTxt10};
        int i = 0;
        for (JTextField campo6 : camp) {
            if (i < valorCampos) {
                if (campo6.getText().length() == 0) {
                    resultado = false;
                    respuesta = "descripcion";
                }
            }
            i++;
        }
        return resultado;
    }

    private void ocultaForm() {
        nombreMateriaTxt.setEnabled(false);
        creditosTxt.setEnabled(false);
        horasTxt.setEnabled(false);
        activadaRdb.setEnabled(false);
        desactivadaRdb.setEnabled(false);
        configuracionCmb.setEnabled(false);
        mallaCmb.setEnabled(false);
        configuracionCmb.setEnabled(false);
        semestreCmb.setEnabled(false);
        especialidadCmb.setEnabled(false);
        ejeCmb.setEnabled(false);
        profesoCmb.setEnabled(false);
    }

    private boolean validaForm() {
        boolean resultado = true;
        if (!validaSuma(numeroCampos)) {
            resultado = false;
        } else if (!validaDescripcion(numeroCampos)) {
            resultado = false;
        } else if (nombreMateriaTxt.getText().trim().length() == 0) {
            resultado = false;
        } else if (creditosTxt.getText().trim().length() == 0) {
            resultado = false;
        } else if (horasTxt.getText().trim().length() == 0) {
            resultado = false;
        } else if (estadoMallaGroup.isSelected(null)) {
            resultado = false;
        } else if (configuracionCmb.getSelectedIndex() == 0) {
            resultado = false;
        } else if (ejeCmb.getSelectedIndex() == 0) {
            resultado = false;
        } else if (profesoCmb.getSelectedIndex() == 0) {
            resultado = false;
        } else if (mallaCmb.getSelectedIndex() == 0) {
            resultado = false;
        }

        return resultado;
    }

    private void cargarConfiguracion() {
        try {
            resultSet = metodosGeneralesDao.cargaConfiguracion();
            while (resultSet.next()) {
                Configuracion co = new Configuracion();
                co.setIdConfiguracion(Integer.parseInt(resultSet.getString("id_configuracion")));
                co.setDescripcion(resultSet.getString("descripcion"));
                configuracionCmb.addItem(co);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
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

    private void cargarEje() {
        try {
            resultSet = metodosGeneralesDao.cargaComboEje();
            while (resultSet.next()) {
                Eje e = new Eje();
                e.setIdEje(Integer.parseInt(resultSet.getString("id1_ejes")));
                e.setNombreEje(resultSet.getString("nom_ejes"));
                ejeCmb.addItem(e);
            }

        } catch (SQLException | NumberFormatException e) {
        }
    }

    private void cargaProfesor() {
        try {
            resultSet = metodosGeneralesDao.cargaComboProfesor();
            while (resultSet.next()) {
                Profesor p = new Profesor();
                p.setIdProfesor(Integer.parseInt(resultSet.getString("id1_profe")));
                p.setNombreProfesor(resultSet.getString("nom_profe"));
                p.setApellidoProfesor(resultSet.getString("apell_profe"));
                profesoCmb.addItem(p);
            }
        } catch (SQLException | NumberFormatException e) {
        }

    }

    private void cargaMalla() {
        try {
            resultSet = metodosGeneralesDao.cargaComboMalla();
            while (resultSet.next()) {
                Malla m = new Malla();
                m.setIdMalla(resultSet.getInt("id_malla"));
                m.setNombreMalla(resultSet.getString("nombre_malla"));
                mallaCmb.addItem(m);
            }
        } catch (Exception e) {
            System.out.println(e);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioMateria dialog = new FormularioMateria(frmMateria, true);
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
    private javax.swing.JTextField aporteDesTxt4;
    private javax.swing.JTextField aporteDescTxt1;
    private javax.swing.JTextField aporteDescTxt3;
    private javax.swing.JTextField aporteDscTxt10;
    private javax.swing.JTextField aporteDscTxt2;
    private javax.swing.JTextField aporteDscTxt5;
    private javax.swing.JTextField aporteDscTxt6;
    private javax.swing.JTextField aporteDscTxt7;
    private javax.swing.JTextField aporteDscTxt8;
    private javax.swing.JTextField aporteDscTxt9;
    private javax.swing.JTextField aporteTxt1;
    private javax.swing.JTextField aporteTxt10;
    private javax.swing.JTextField aporteTxt2;
    private javax.swing.JTextField aporteTxt3;
    private javax.swing.JTextField aporteTxt4;
    private javax.swing.JTextField aporteTxt5;
    private javax.swing.JTextField aporteTxt6;
    private javax.swing.JTextField aporteTxt7;
    private javax.swing.JTextField aporteTxt8;
    private javax.swing.JTextField aporteTxt9;
    private javax.swing.JButton calcularBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton cargarBtn;
    private javax.swing.JComboBox configuracionCmb;
    private javax.swing.JTextField creditosTxt;
    private javax.swing.JLabel desLbl1;
    private javax.swing.JLabel desLbl10;
    private javax.swing.JLabel desLbl2;
    private javax.swing.JLabel desLbl3;
    private javax.swing.JLabel desLbl4;
    private javax.swing.JLabel desLbl5;
    private javax.swing.JLabel desLbl6;
    private javax.swing.JLabel desLbl7;
    private javax.swing.JLabel desLbl8;
    private javax.swing.JLabel desLbl9;
    private javax.swing.JRadioButton desactivadaRdb;
    private javax.swing.JComboBox ejeCmb;
    private javax.swing.JComboBox especialidadCmb;
    private javax.swing.ButtonGroup estadoMallaGroup;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JTextField horasTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox mallaCmb;
    private javax.swing.JTextField nombreMateriaTxt;
    private javax.swing.JTextField numeroCamposTxt;
    private javax.swing.JComboBox profesoCmb;
    private javax.swing.JComboBox semestreCmb;
    private javax.swing.JButton validarBtn;
    private javax.swing.JLabel valorlbl1;
    private javax.swing.JLabel valorlbl10;
    private javax.swing.JLabel valorlbl2;
    private javax.swing.JLabel valorlbl3;
    private javax.swing.JLabel valorlbl4;
    private javax.swing.JLabel valorlbl5;
    private javax.swing.JLabel valorlbl6;
    private javax.swing.JLabel valorlbl7;
    private javax.swing.JLabel valorlbl8;
    private javax.swing.JLabel valorlbl9;
    // End of variables declaration//GEN-END:variables
}
