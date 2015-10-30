package vistas;

import conectar.Conexion;
import controles.DatosNotaControl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import metodos.CargarDatos;
import metodos.CargarDatosNotas;

/**
 *
 * @author Toshiba
 */
public class FormularioNotas extends javax.swing.JDialog {

    Conexion cc = Conexion.getInstance();
    Connection cn = cc.Conectar();
    private Integer idnota;
    private DatosNotaControl notaControl;
    public static FrmNotas frmNotas;
    private String idPeriodo, numaportes, cedula;
    CargarDatos cd;
    CargarDatosNotas cdn;
    Map campos;
    BigDecimal promedio;
    private double numhoras, nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10, proPonderado,
            porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6,
            porcentaje7, porcentaje8, porcentaje9, porcentaje10, ponderacion;
    private int idconfimaterias, idmateria, idsemestre, idconfigmaterias, idespecialidad, idmalla, idalumno,porAsistencia;
    private static final BigDecimal valor = new BigDecimal(14);
    BigDecimal notaAsistencia = new BigDecimal("00.00");

    public FormularioNotas(FrmNotas parent, boolean modal) {
        FormularioNotas.frmNotas = parent;
        this.setModal(modal);
        initComponents();
//        idPeriodo = cd.codigo();
//        removerCampos();
//        guardarBtn.setEnabled(false);

    }

    public FormularioNotas(FrmNotas parent, boolean modal, Integer pknota) throws SQLException {
        FormularioNotas.frmNotas = parent;
        this.setModal(modal);
        initComponents();
//        removerCampos();
//        cd = new CargarDatos();
//        cdn = new CargarDatosNotas();
//        idPeriodo = cd.codigo();
//        this.setLocationRelativeTo(null);
//        this.idnota = pknota;
//        confMaterias();
//        configMaterias();
//        notaControl = new DatosNotaControl();
//        materiaTxt.setText(cdn.cargarNombreMateria(idmateria));
//        semestreTxt.setText(cdn.cargarNombreSemestre(idsemestre));
//        guardarBtn.setEnabled(false);
//        validaCampos();
    }

//    private void confMaterias() {
//        try {
//            ResultSet rs = cdn.cargarNotas(idPeriodo, idnota);
//            while (rs.next()) {
//                numhoras = Double.valueOf(rs.getString("numhoras"));
//                nota1 = Double.valueOf(rs.getString("nota1"));
//                nota2 = Double.valueOf(rs.getString("nota2"));
//                nota3 = Double.valueOf(rs.getString("nota3"));
//                nota4 = Double.valueOf(rs.getString("nota4"));
//                nota5 = Double.valueOf(rs.getString("nota5"));
//                nota6 = Double.valueOf(rs.getString("nota6"));
//                nota7 = Double.valueOf(rs.getString("nota7"));
//                nota8 = Double.valueOf(rs.getString("nota8"));
//                nota9 = Double.valueOf(rs.getString("nota9"));
//                nota10 = Double.valueOf(rs.getString("nota10"));
//                cedula = rs.getString("cedula");
//
//                // proPonderado = Double.valueOf(rs.getString("proPonderado"));
//                cedulaTxt.setText(cedula);
//                idconfimaterias = Integer.parseInt(rs.getString("idconfigmaterias"));
//                idmateria = Integer.parseInt(rs.getString("idmateria"));
//                idsemestre = Integer.parseInt(rs.getString("idsemestre"));
//                idespecialidad = Integer.parseInt(rs.getString("idespecialidad"));
//                ponderacion = Double.valueOf(rs.getString("ponderacion"));
//                idmalla = Integer.parseInt(rs.getString("idmalla"));
//                idalumno = Integer.parseInt(rs.getString("idalumno"));
//                estadoTxt.setText(rs.getString("estado"));
//                proPonderadoTxt.setText(rs.getString("proPonderado"));
//                porAsistenciaTxt.setText(rs.getString("porAsistencia"));
//                numHorasTxt.setText(String.valueOf(numhoras));
//                nota1Txt.setText(String.valueOf(nota1));
//                nota2Txt.setText(String.valueOf(nota2));
//                nota3Txt.setText(String.valueOf(nota3));
//                nota4Txt.setText(String.valueOf(nota4));
//                nota5Txt.setText(String.valueOf(nota5));
//                nota6Txt.setText(String.valueOf(nota6));
//                nota7Txt.setText(String.valueOf(nota7));
//                nota8Txt.setText(String.valueOf(nota8));
//                nota9Txt.setText(String.valueOf(nota9));
//                nota10Txt.setText(String.valueOf(nota10));
//                promedioTxt.setText(String.valueOf(proPonderado));
//            }
//        } catch (SQLException | NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Ocurrion un error al cargar los datos principales ", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void configMaterias() {
//        try {
//            ResultSet rs = cdn.configuracionMaterias(idconfimaterias);
//            while (rs.next()) {
//                porcentaje1 = Double.valueOf(rs.getString("aporte1"));
//                porcentaje2 = Double.valueOf(rs.getString("aporte2"));
//                porcentaje3 = Double.valueOf(rs.getString("aporte3"));
//                porcentaje4 = Double.valueOf(rs.getString("aporte4"));
//                porcentaje5 = Double.valueOf(rs.getString("aporte5"));
//                porcentaje6 = Double.valueOf(rs.getString("aporte6"));
//                porcentaje7 = Double.valueOf(rs.getString("aporte7"));
//                porcentaje8 = Double.valueOf(rs.getString("aporte8"));
//                porcentaje9 = Double.valueOf(rs.getString("aporte9"));
//                porcentaje10 = Double.valueOf(rs.getString("aporte10"));
//
//                numaportes = rs.getString("numaportes");
//                numApoLbl.setText("Numero de Aportes: " + "  " + numaportes);
//
//                por1lbl.setText(String.valueOf(porcentaje1) + "%");
//                por2lbl.setText(String.valueOf(porcentaje2) + "%");
//                por3lbl.setText(String.valueOf(porcentaje3) + "%");
//                por4lbl.setText(String.valueOf(porcentaje4) + "%");
//                por5lbl.setText(String.valueOf(porcentaje5) + "%");
//                por6lbl.setText(String.valueOf(porcentaje6) + "%");
//                por7lbl.setText(String.valueOf(porcentaje7) + "%");
//                por8lbl.setText(String.valueOf(porcentaje8) + "%");
//                por9lbl.setText(String.valueOf(porcentaje9) + "%");
//                por10lbl.setText(String.valueOf(porcentaje10) + "%");
//
//            }
//        } catch (SQLException | NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Ocurrion un error al cargar los valores de la notas ", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//    }
//
//    private void validaCampos() {
//        switch (Integer.parseInt(numaportes)) {
//            case 1:
//                nota1Txt.setEnabled(true);
//                porAsistenciaTxt.setEnabled(true);
//                break;
//            case 2:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                } else {
//                    nota2Txt.setEnabled(false);
//                }
//                break;
//            case 3:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                }
//                break;
//            case 4:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                }
//
//                break;
//            case 5:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                    nota5Txt.setEnabled(false);
//                }
//
//                break;
//            case 6:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                    nota5Txt.setEnabled(false);
//                    nota6Txt.setEnabled(false);
//                }
//                break;
//            case 7:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                    nota5Txt.setEnabled(false);
//                    nota6Txt.setEnabled(false);
//                    nota7Txt.setEnabled(false);
//                }
//                break;
//            case 8:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0 && nota7 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    nota8Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                    nota5Txt.setEnabled(false);
//                    nota6Txt.setEnabled(false);
//                    nota7Txt.setEnabled(false);
//                    nota8Txt.setEnabled(false);
//                }
//                break;
//            case 9:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0 && nota7 > 0 && nota8 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    nota8Txt.setEnabled(true);
//                    nota9Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0 && nota7 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    nota8Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                }
//                if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                    nota5Txt.setEnabled(false);
//                    nota6Txt.setEnabled(false);
//                    nota7Txt.setEnabled(false);
//                    nota8Txt.setEnabled(false);
//                    nota9Txt.setEnabled(false);
//                }
//
//                break;
//            case 10:
//                nota1Txt.setEnabled(true);
//                if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0 && nota7 > 0 && nota8 > 0 && nota9 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    nota8Txt.setEnabled(true);
//                    nota9Txt.setEnabled(true);
//                    nota10Txt.setEnabled(true);
//                    porAsistenciaTxt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0 && nota7 > 0 && nota8 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    nota8Txt.setEnabled(true);
//                    nota9Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0 && nota7 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    nota8Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0 && nota6 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    nota7Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0 && nota5 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    nota6Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0 && nota4 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    nota5Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0 && nota3 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    nota4Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0 && nota2 > 0) {
//                    nota2Txt.setEnabled(true);
//                    nota3Txt.setEnabled(true);
//                    break;
//                } else if (nota1 > 0) {
//                    nota2Txt.setEnabled(true);
//                    break;
//                } else {
//                    nota2Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota3Txt.setEnabled(false);
//                    nota4Txt.setEnabled(false);
//                    nota5Txt.setEnabled(false);
//                    nota6Txt.setEnabled(false);
//                    nota7Txt.setEnabled(false);
//                    nota8Txt.setEnabled(false);
//                    nota9Txt.setEnabled(false);
//                    nota10Txt.setEnabled(false);
//                }
//                break;
//        }
//
//    }

//    private void removerCampos() {
//        cedulaTxt.setEnabled(false);
//        materiaTxt.setEnabled(false);
//        semestreTxt.setEnabled(false);
//        estadoTxt.setEnabled(false);
//        promedioTxt.setEnabled(false);
//        numHorasTxt.setEnabled(false);
//        nota1Txt.setEnabled(false);
//        nota2Txt.setEnabled(false);
//        nota3Txt.setEnabled(false);
//        nota4Txt.setEnabled(false);
//        nota5Txt.setEnabled(false);
//        nota6Txt.setEnabled(false);
//        nota7Txt.setEnabled(false);
//        nota8Txt.setEnabled(false);
//        nota9Txt.setEnabled(false);
//        nota10Txt.setEnabled(false);
//        proPonderadoTxt.setEnabled(false);
//        porAsistenciaTxt.setEnabled(false);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cedulaTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        materiaTxt = new javax.swing.JTextField();
        semestreTxt = new javax.swing.JTextField();
        numApoLbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nota1Txt = new javax.swing.JTextField();
        nota2Txt = new javax.swing.JTextField();
        nota3Txt = new javax.swing.JTextField();
        nota4Txt = new javax.swing.JTextField();
        nota5Txt = new javax.swing.JTextField();
        por1lbl = new javax.swing.JLabel();
        por2lbl = new javax.swing.JLabel();
        por3lbl = new javax.swing.JLabel();
        por4lbl = new javax.swing.JLabel();
        por5lbl = new javax.swing.JLabel();
        nota6Txt = new javax.swing.JTextField();
        nota7Txt = new javax.swing.JTextField();
        nota8Txt = new javax.swing.JTextField();
        nota9Txt = new javax.swing.JTextField();
        nota10Txt = new javax.swing.JTextField();
        por6lbl = new javax.swing.JLabel();
        por7lbl = new javax.swing.JLabel();
        por8lbl = new javax.swing.JLabel();
        por9lbl = new javax.swing.JLabel();
        por10lbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        estadoTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        promedioTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        guardarBtn = new javax.swing.JButton();
        numHorasTxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        porAsistenciaTxt = new javax.swing.JTextField();
        calcularBtn = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        proPonderadoTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("cedula");

        jLabel3.setText("Materia");

        jLabel4.setText("Semestre");

        semestreTxt.setText(" ");

        numApoLbl.setText("Numero de Aportes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas de Materia"));

        por1lbl.setText("%");

        por2lbl.setText("%");

        por3lbl.setText("%");

        por4lbl.setText("%");

        por5lbl.setText("%");

        nota8Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nota8TxtActionPerformed(evt);
            }
        });

        por6lbl.setText("%");

        por7lbl.setText("%");

        por8lbl.setText("%");

        por9lbl.setText("%");

        por10lbl.setText("%");

        jLabel5.setText("Nota 1");

        jLabel6.setText("Nota 6");

        jLabel7.setText("Nota 2");

        jLabel8.setText("Nota 7");

        jLabel9.setText("Nota 3");

        jLabel10.setText("Nota 8");

        jLabel11.setText("Nota 4");

        jLabel12.setText("Nota 5");

        jLabel13.setText("Nota 9");

        jLabel14.setText("Nota 10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(por2lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(por1lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(por3lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(por4lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(por5lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota7Txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(nota8Txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nota9Txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(por6lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por7lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por8lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por9lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por10lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nota1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por1lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nota6Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por6lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nota2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por2lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nota7Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por7lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nota8Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(por8lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(por3lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nota9Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(por9lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(nota4Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(por4lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nota10Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por10lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(nota5Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(por5lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(27, 27, 27))
        );

        jLabel15.setText("Estado ");

        jLabel2.setText("Promedio ");

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        jLabel16.setText("Numero de Horas");

        jLabel17.setText("Porcentaje de Asistencia");

        calcularBtn.setText("Calcular");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        jLabel18.setText("Promedio Ponderado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(numApoLbl)
                        .addGap(211, 211, 211)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(estadoTxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(materiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(semestreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(numHorasTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(promedioTxt)
                                    .addComponent(proPonderadoTxt)
                                    .addComponent(porAsistenciaTxt)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(guardarBtn)
                                .addGap(28, 28, 28)
                                .addComponent(calcularBtn)))))
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedulaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(materiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semestreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(numApoLbl)
                                .addGap(18, 18, 18)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(promedioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numHorasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(porAsistenciaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(proPonderadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardarBtn)
                            .addComponent(calcularBtn))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void nota8TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nota8TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nota8TxtActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
//        Calendar cal = Calendar.getInstance();
//        if (promedioTxt.getText().length() == 0) {
//
//            JOptionPane.showMessageDialog(null, "Promedio vacio para ingreso", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            try {
//                cargarDatos();
//                campos.put("estado", estadoTxt.getText());
//                campos.put("promedio", promedioTxt.getText());
//                campos.put("proPonderado", proPonderadoTxt.getText());
//                campos.put("fechaModificacion", cal.getTime());
//                campos.put("porAsistencia", porAsistencia);
//                campos.put("asistencia", notaAsistencia);
//                cdn.actualizar("nota", idPeriodo, "idnota", idnota, campos);
//
//                double resultado = cdn.proPonderado(cedula, idPeriodo, idespecialidad, idsemestre);
//                Map camposResumen = new HashMap();
//                int porNotaMalla = cdn.porNotaFinal(idmalla);
//                double con = 100;
//                double total = porNotaMalla / con;
//                
//                BigDecimal porNotaResumen = BigDecimal.valueOf(resultado).multiply(BigDecimal.valueOf(total));
//                BigDecimal proPonderadoNota = BigDecimal.valueOf(resultado);
//                proPonderadoNota = proPonderadoNota.setScale(1, RoundingMode.HALF_UP);
//                porNotaResumen = porNotaResumen.setScale(1, RoundingMode.HALF_UP);
//                double notaFin = cdn.notaFinal(cedula, idespecialidad, idsemestre);
//                
//                BigDecimal nota = new BigDecimal(notaFin).add(porNotaResumen);
//                nota= nota.setScale(1, RoundingMode.HALF_UP);
//                String estado = "";
//                camposResumen.put("proPonderadoNota", proPonderadoNota);
//                camposResumen.put("porcentajeNota", porNotaResumen);
//                camposResumen.put("notaFinal", nota);
//                
//                if (nota.compareTo(valor) < 0) {
//                    estado = "REPROBADO";
//                } else {
//                    estado = "APROBADO";
//                }
//                
//                camposResumen.put("fechaModificacion", cal.getTime());
//                camposResumen.put("estado", estado);
//                camposResumen.put("asistencia", cdn.asistencia(cedula, idPeriodo, idespecialidad, idsemestre));
//                cdn.actualizarResumen("resumen", "idresumen", cedula, idalumno, idespecialidad, idsemestre, camposResumen);
//                JOptionPane.showMessageDialog(null, "Registros actualizados Correctamente");
//                this.dispose();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de guardar las notas en BD", "Error", JOptionPane.ERROR_MESSAGE);
//
//            }
//
//        }


    }//GEN-LAST:event_guardarBtnActionPerformed

    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
//        try {
//            double[] porcentajes = {porcentaje1, porcentaje2, porcentaje3, porcentaje4, porcentaje5, porcentaje6,
//                porcentaje7, porcentaje8, porcentaje9, porcentaje10};
//            nota1 = Double.valueOf(nota1Txt.getText().replaceAll(",", "."));
//            nota2 = Double.valueOf(nota2Txt.getText().replaceAll(",", "."));
//            nota3 = Double.valueOf(nota3Txt.getText().replaceAll(",", "."));
//            nota4 = Double.valueOf(nota4Txt.getText().replaceAll(",", "."));
//            nota5 = Double.valueOf(nota5Txt.getText().replaceAll(",", "."));
//            nota6 = Double.valueOf(nota6Txt.getText().replaceAll(",", "."));
//            nota7 = Double.valueOf(nota7Txt.getText().replaceAll(",", "."));
//            nota8 = Double.valueOf(nota8Txt.getText().replaceAll(",", "."));
//            nota9 = Double.valueOf(nota9Txt.getText().replaceAll(",", "."));
//            nota10 = Double.valueOf(nota10Txt.getText().replaceAll(",", "."));
//
//            double[] notas = {nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8, nota9, nota10};
//            double resultadoPromedio = 0;
//            int porcentaje = 100;
//            double resul1 = 0;
//            double notaFinal = 0;
//            for (int i = 0; i < porcentajes.length; i++) {
//                resultadoPromedio = porcentajes[i] / porcentaje;
//                resul1 = notas[i] * resultadoPromedio;
//                notaFinal = notaFinal + resul1;
//            }
//            promedio = new BigDecimal(notaFinal);
//
//            promedio = promedio.setScale(1, RoundingMode.HALF_UP);
//            if (promedio.compareTo(valor) < 0) {
//                estadoTxt.setText("REPROBADO");
//            } else {
//                estadoTxt.setText("APROBADO");
//            }
//
//            double resul = ponderacion / 100;
//            BigDecimal proPonderado = new BigDecimal("00.00");
//            proPonderado = promedio.multiply(BigDecimal.valueOf(resul));
//            proPonderado = proPonderado.setScale(1, RoundingMode.HALF_UP);
//             porAsistencia = Integer.parseInt(porAsistenciaTxt.getText());
//            
//            if (porAsistencia > 0) {
//                    porAsistenciaTxt.setEnabled(false);
//                if (porAsistencia > 100) {
//                    JOptionPane.showMessageDialog(null, "Error porcentaje de asistencia no valido", "Error", JOptionPane.ERROR_MESSAGE);
//                    porAsistenciaTxt.setText(null);
//                    porAsistenciaTxt.setEnabled(true);
//                    return;
//                } else if (porAsistencia == 100) {
//                    notaAsistencia= new BigDecimal("1.0");
//                    proPonderado = proPonderado.add(notaAsistencia);
//                }
//            }else {
//            notaAsistencia = new BigDecimal("0.0");
//            }
//            proPonderadoTxt.setText(String.valueOf(proPonderado));
//            promedioTxt.setText(String.valueOf(promedio));
//            guardarBtn.setEnabled(true);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de calcular los valores", "Error", JOptionPane.ERROR_MESSAGE);
//        }

    }//GEN-LAST:event_calcularBtnActionPerformed

//    private Map cargarDatos() {
//        campos = new HashMap();
//        switch (numaportes) {
//            case "1":
//                campos.put("nota1", nota1Txt.getText());
//                break;
//            case "2":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                break;
//            case "3":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                break;
//            case "4":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                break;
//            case "5":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                campos.put("nota5", nota5Txt.getText());
//                break;
//            case "6":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                campos.put("nota5", nota5Txt.getText());
//                campos.put("nota6", nota6Txt.getText());
//                break;
//            case "7":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                campos.put("nota5", nota5Txt.getText());
//                campos.put("nota6", nota6Txt.getText());
//                campos.put("nota7", nota7Txt.getText());
//                break;
//            case "8":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                campos.put("nota5", nota5Txt.getText());
//                campos.put("nota6", nota6Txt.getText());
//                campos.put("nota7", nota7Txt.getText());
//                campos.put("nota8", nota8Txt.getText());
//                break;
//            case "9":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                campos.put("nota5", nota5Txt.getText());
//                campos.put("nota6", nota6Txt.getText());
//                campos.put("nota7", nota7Txt.getText());
//                campos.put("nota8", nota8Txt.getText());
//                campos.put("nota9", nota9Txt.getText());
//                break;
//            case "10":
//                campos.put("nota1", nota1Txt.getText());
//                campos.put("nota2", nota2Txt.getText());
//                campos.put("nota3", nota3Txt.getText());
//                campos.put("nota4", nota4Txt.getText());
//                campos.put("nota5", nota5Txt.getText());
//                campos.put("nota6", nota6Txt.getText());
//                campos.put("nota7", nota7Txt.getText());
//                campos.put("nota8", nota8Txt.getText());
//                campos.put("nota9", nota9Txt.getText());
//                campos.put("nota10", nota10Txt.getText());
//                break;
//        }
//        return campos;
//    }

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
            java.util.logging.Logger.getLogger(FormularioNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioNotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioNotas dialog = new FormularioNotas(frmNotas, true);
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
    private javax.swing.JButton calcularBtn;
    private javax.swing.JTextField cedulaTxt;
    private javax.swing.JTextField estadoTxt;
    private javax.swing.JButton guardarBtn;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField materiaTxt;
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
    private javax.swing.JLabel numApoLbl;
    private javax.swing.JTextField numHorasTxt;
    private javax.swing.JLabel por10lbl;
    private javax.swing.JLabel por1lbl;
    private javax.swing.JLabel por2lbl;
    private javax.swing.JLabel por3lbl;
    private javax.swing.JLabel por4lbl;
    private javax.swing.JLabel por5lbl;
    private javax.swing.JLabel por6lbl;
    private javax.swing.JLabel por7lbl;
    private javax.swing.JLabel por8lbl;
    private javax.swing.JLabel por9lbl;
    private javax.swing.JTextField porAsistenciaTxt;
    private javax.swing.JTextField proPonderadoTxt;
    private javax.swing.JTextField promedioTxt;
    private javax.swing.JTextField semestreTxt;
    // End of variables declaration//GEN-END:variables
}
