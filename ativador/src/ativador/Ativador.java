package ativador;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Ativador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ativadorSistema mm = new ativadorSistema();
        // frmMenu mm = new frmMenu();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        mm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mm.setVisible(true);
    }
}
