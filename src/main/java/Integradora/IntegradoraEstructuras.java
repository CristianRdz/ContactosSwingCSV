package Integradora;

import com.formdev.flatlaf.FlatLightLaf;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class IntegradoraEstructuras {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Menu ini = new Menu();
                        ini.setExtendedState(ini.MAXIMIZED_BOTH);
                        ini.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(IntegradoraEstructuras.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (CsvValidationException ex) {
                        Logger.getLogger(IntegradoraEstructuras.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IntegradoraEstructuras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
