package control;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Proceso {

    private static Proceso instance;

    public Proceso() {
    }

    public static Proceso getInstance() {
        if (instance == null) {
            instance = new Proceso();
        }
        return instance;
    }

    public void cargarJDialog() {
        JOptionPane opt = new JOptionPane(" ESPERE POR FAVOR ", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, new ImageIcon("src/recursos/spineer.gif"), new Object[]{}); // no buttons

        final JDialog dlg = opt.createDialog("Procesando");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1700);
                    dlg.dispose();

                } catch (Throwable th) {
                 System.out.println("error en el hilo de proceso"+th);
                }
            }
        }).start();
        dlg.setVisible(true);

    }
}
