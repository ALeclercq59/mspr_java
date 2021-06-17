/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import mspr.entities.Utilisateur;

/**
 *
 * @author Anthony
 */
public class PersistanceSQL {
    Connection connexion = null;
    Statement req = null;
    ResultSet resultat = null;
    ResultSet resultat1 = null;
    ResultSet resultat2 = null;

    public PersistanceSQL(String ipBase, int port, String nomBaseDonnees){
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://"+ipBase+":"+port+"/"+nomBaseDonnees,"root",""); // ipBase = localhost/mspr_bdd / port:3306
        }catch (Exception ex){
            System.out.println("Veuillez vous connectez à la base de données !");
        }
    }
    
    public int savoirLogin(String login) {
        try  {
            req = connexion.createStatement();
            resultat = req.executeQuery("SELECT COUNT(*) FROM utilisateur WHERE Identifiant = '" + login + "'");
            resultat.next();
            int compteur = resultat.getInt(1);
            return compteur;
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public int savoirMdp(String login, String password) {
        try  {
            req = connexion.createStatement();
            resultat = req.executeQuery("SELECT COUNT(*) FROM utilisateur WHERE Identifiant = '" + login + "' AND Mot_de_passe = '" + password + "'");
            resultat.next();
            int compteur = resultat.getInt(1);
            return compteur;
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    public Object Login(String login, String password) {
        Object obj = null;
        try {
            req = connexion.createStatement();
            resultat = req.executeQuery("SELECT * FROM utilisateur WHERE Identifiant = '" + login + "' AND Mot_de_passe = '" + password + "'");
                while(resultat.next()) {
                    int idUser = resultat.getInt("idUser");
                    String nom = resultat.getString("Nom");
                    String prenom = resultat.getString("Prenom");
                    String mail = resultat.getString("Mail");
                    String identifiant = resultat.getString("Identifiant");
                    String mdp = resultat.getString("Mot_de_passe");
                    Date dateUpdateMdp = resultat.getDate("DateUpdateMdp");
                    int changeMdp = resultat.getInt("change_mdp");
                    int actif = resultat.getInt("Actif");
                    int idRole = resultat.getInt("IdRole");
                    int idSt = resultat.getInt("id_ST");
                
                    Utilisateur utilisateur = new Utilisateur (idUser, nom, prenom, mail, identifiant, mdp, dateUpdateMdp, changeMdp, actif, idRole, idSt);
                
                    obj = utilisateur;
                }
        } catch (SQLException ex) {
            return null;
        }
        return obj;
    }
    
    public void UserInactif(String login) {
        try {
            PreparedStatement req = null;
            req = connexion.prepareStatement("UPDATE utilisateur SET Actif = 0 WHERE Identifiant = ?");
            req.setString(1, login);
            req.executeUpdate(); 
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    public void changeMdp(String password, int idUser) {
        try {
            PreparedStatement req = null;
            req = connexion.prepareStatement("UPDATE utilisateur SET Mot_de_passe = ? WHERE idUser = ?");
            req.setString(1, password);
            req.setInt(2, idUser);
            req.executeUpdate(); 
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
