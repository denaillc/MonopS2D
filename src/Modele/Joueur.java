/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 *
 * @author albertar
 */
public class Joueur {
    
    private String nom;
    private int cash;
    private Carreau positionCourante;
    private ArrayList<Gare> gares;
    private ArrayList<Propriete> propriete;

    public Joueur(String nom) {
        this.gares = new ArrayList();
        this.propriete = new ArrayList();
        this.nom = nom;
        this.cash = 1500;
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
    
    public void payerLoyer(int loyer){
        setCash(getCash()-loyer);
    }
    
    public void recevoirLoyer(int loyer){
        setCash(getCash()+loyer);
    }

    public Carreau getPositionCourante() {
        return positionCourante;
    }

    public void setPositionCourante(Carreau positionCourante) {
        this.positionCourante = positionCourante;
    }
    
    public void addGare(Gare g){
        gares.add(g);
    }
    
    public int getNbGares(){
        return gares.size();
    }

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public void setGares(ArrayList<Gare> gares) {
        this.gares = gares;
    }

    public ArrayList<Propriete> getPropriete() {
        return propriete;
    }

    public void setPropriete(ArrayList<Propriete> propriete) {
        this.propriete = propriete;
    }
    
    public void addPropriete(Propriete p){
        getPropriete().add(p);
    }
}