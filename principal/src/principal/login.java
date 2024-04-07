/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {

    public login() {
        initComponents();
         setResizable(false);
        setExtendedState(login.MAXIMIZED_HORIZ);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btAcesso = new javax.swing.JButton();
        txtSenhaLogin = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btAcesso.setText("Login");
        btAcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAcessoActionPerformed(evt);
            }
        });
        btAcesso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btAcessoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btAcesso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(txtSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btAcesso))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAcessoActionPerformed
        Connection con = conexao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT * FROM credencial WHERE  senha = ?");

            pstm.setString(1, txtSenhaLogin.getText().trim());

            rs = pstm.executeQuery();

            if (rs.next()) {
                // Verifica se a senha expirou
                if (senhaExpirou(rs.getDate("ultima_Alteracao_Senha"))) {
                    JOptionPane.showMessageDialog(null, "Sua senha expirou. Por favor, entre em contato com suporte.");
                    
                } else {
                    // fecha o form login
                    this.dispose();
                    // abrir o form de menu
                    menu mm = new menu();
                    mm.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Insira uma senha válida");

            }
        } catch (SQLException ErroSql) {
            JOptionPane.showMessageDialog(null, ErroSql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            conexao.closeConnection(con, pstm, rs);
        }
    }//GEN-LAST:event_btAcessoActionPerformed
// Método para verificar se a senha expirou

    private boolean senhaExpirou(Date ultimaAlteracaoSenha) {
        // Obtenha a data atual
        java.util.Date dataAtual = new java.util.Date();

        // Verifique se o mês atual 
        int mesAtual = Calendar.getInstance().get(Calendar.MONTH);

        //if (mesAtual != 3) { // Se o mês atual não for abril
        //  return true; // A senha não expira
        //}
        // Obtenha o mês da última alteração da senha
        Calendar cal = Calendar.getInstance();
        cal.setTime(ultimaAlteracaoSenha);
        int mesUltimaAlteracao = cal.get(Calendar.MONTH);

        // Verifique se o mês da última alteração é diferente do mês atual
        if (mesUltimaAlteracao != mesAtual) {
            return true; // A senha expirou
        }
        // Se os meses forem iguais, a senha ainda é válida
        return false;
    }
    private void btAcessoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btAcessoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btAcesso.doClick();
        }
    }//GEN-LAST:event_btAcessoKeyPressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAcesso;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtSenhaLogin;
    // End of variables declaration//GEN-END:variables
}
