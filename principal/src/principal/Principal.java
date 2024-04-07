/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author Leds
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       login mm = new login();
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
