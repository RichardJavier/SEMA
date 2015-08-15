package conectar;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;;



public final class Conexion1 {

    private static Conexion1 instance;
    private String driver;
    private String jdbc;
    private String host;
    private String database;
    private String username;
    private String password;
    private String opciones;
    private String s_conexion;
    private String mostrarOpciones;

    private Connection con;
    private Statement smt;

    public Conexion1() {
    }

    public static Conexion1 getInstance() {

        if (instance == null) {
            instance = new Conexion1();
        }
        return instance;
    }

    public void leerpropiedades() {
        try {
            FileInputStream propFile = new FileInputStream("configuracionWEB.txt");
            Properties prop = new Properties(System.getProperties());
            prop.load(propFile);
            driver = prop.getProperty("driver");
            jdbc = prop.getProperty("jdbc");
            host = prop.getProperty("host");
            database = prop.getProperty("database");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            opciones = prop.getProperty("options");
        } catch (java.io.FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo de configuracion" + e);
            System.exit(0);
        } catch (java.io.IOException w) {
            JOptionPane.showMessageDialog(null, "Ocurrio algun error de I/O");
            System.exit(0);
        }
    }

    public Connection Conectar() {

        try {
            desconectar();
            leerpropiedades();
            Class.forName(driver);
            s_conexion = jdbc + host + "/" + database;
            con = DriverManager.getConnection(s_conexion, username, password);
            con.setAutoCommit(false);
            smt = con.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
             JOptionPane.showMessageDialog(null, "No existe conexion,por favor comuniquese con el administrador del sistema");

        }
        return con;

    }

//    public Connection Conectar() {
//        if (onectar() == null) {
//           
//            JFrame fr = Instance.getInstance();
//            fr.setVisible(true);
//            fr.pack();
//        }
//        return onectar();
//    }

    public void desconectar() throws SQLException {
        if (smt != null) {
            smt.close();
            smt = null;
        }
        if (con != null) {
            con.commit();
            con.close();
            con = null;
        }
    }
}
