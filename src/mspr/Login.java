/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspr;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mspr.entities.Utilisateur;


/**
 *
 * @author Anthony
 */
public class Login extends javax.swing.JFrame {
    
        int erreur = 0;
        static PersistanceSQL pSQL = new PersistanceSQL ("localhost", 3306, "mspr_bdd");
        
    
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
    */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textFieldLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textFieldPassword = new javax.swing.JTextField();
        buttonConnexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Connexion");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Login");

        textFieldLogin.setToolTipText("");
        textFieldLogin.setName("login"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        textFieldPassword.setName("password"); // NOI18N

        buttonConnexion.setText("Connexion");
        buttonConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConnexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(textFieldLogin)
                            .addComponent(jLabel3)
                            .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(buttonConnexion)
                .addGap(35, 173, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(buttonConnexion)
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConnexionActionPerformed
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        try {
            if (!login.isEmpty() && !password.isEmpty()) {
                int loginExistant = pSQL.savoirLogin(login);
                int mdpExistant = pSQL.savoirMdp(login, password);
                if (loginExistant == 0) {
                    JOptionPane.showMessageDialog(null,  "Identifiant invalide", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (mdpExistant == 0) {
                        erreur++;
                        JOptionPane.showMessageDialog(null,  "Mot de passe invalide", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                        if (erreur == 3) {
                            pSQL.UserInactif(login);
                            JOptionPane.showMessageDialog(null,  "Compte bloqu?? ! Contacter un administrateur", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                            erreur = 0;
                        }
                    } else {
                        Utilisateur utilisateur = (Utilisateur) pSQL.Login(login, password);
                        System.out.println(utilisateur.getChangeMdp());
                        if (utilisateur.getChangeMdp() == 0) {
                            new Change_mdp(utilisateur).setVisible(true);
                            dispose();
                        }
                        JOptionPane.showMessageDialog(null,  "Connexion r??ussi", "Erreur de saisie", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,  "Veuillez bien saisir le login et le password !", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null,  "Erreur !", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
	}
    }//GEN-LAST:event_buttonConnexionActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConnexion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textFieldLogin;
    private javax.swing.JTextField textFieldPassword;
    // End of variables declaration//GEN-END:variables
}
