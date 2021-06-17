/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mspr.entities;

import java.util.Date;

/**
 *
 * @author Anthony
 */
public class Utilisateur {
    private int idUser;
    private String nom;
    private String prenom;
    private String mail;
    private String identifiant;
    private String mdp;
    private Date dateUpdateMdp;
    private int changeMdp;
    private int actif;
    private int idRole;
    private int idSt;
    
    public Utilisateur (int idUser, String nom, String prenom, String mail, String identifiant, String mdp, Date dateUpdateMdp, int changeMdp, int actif, int idRole, int idSt) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.dateUpdateMdp = dateUpdateMdp;
        this.changeMdp = changeMdp;
        this.actif = actif;
        this.idRole = idRole;
        this.idSt = idSt;
    }
   

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDateUpdateMdp() {
        return dateUpdateMdp;
    }

    public void setDateUpdateMdp(Date dateUpdateMdp) {
        this.dateUpdateMdp = dateUpdateMdp;
    }

    public int getChangeMdp() {
        return changeMdp;
    }

    public void setChangeMdp(int changeMdp) {
        this.changeMdp = changeMdp;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdSt() {
        return idSt;
    }

    public void setIdSt(int idSt) {
        this.idSt = idSt;
    }
    
    
}
