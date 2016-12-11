/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import UI.CouleurPropriete;
import java.util.ArrayList;

/**
 *
 * @author albertar
 */
public class Joueur {
    
    private String nom;
    private int cash;
    private Carreau positionCourante;
    private ArrayList<Propriete_A_Construire> constructibles;
    private ArrayList<Compagnie> compagnies;
    private ArrayList<Gare> gares;
    private ArrayList<Propriete> propriete;

    public Joueur(String nom, Carreau c) {
        this.gares = new ArrayList();
        this.propriete = new ArrayList();
        this.constructibles = new ArrayList();
        this.compagnies = new ArrayList();
        this.nom = nom;
        this.cash = 1500;
        this.positionCourante = c;
    }

    
    //////////////////////////////////////////////////////////////////////
    ///////////////////////////////Methodes////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    
     public void addGare(Gare g){
        gares.add(g);
    }
    public void addPropriete(Propriete p){
        getProprietes().add(p);
    }
    
    public void addProprieteAConstruire(Propriete_A_Construire p){
        getConstructibles().add(p);
    }
    
    public void addCompagnie(Compagnie c){
        getCompagnies().add(c);
    }
     public int getNbGares(){
        return gares.size();
    }
    public void payerLoyer(int loyer){
        setCash(getCash()-loyer);
    }
    
    public void recevoirLoyer(int loyer){
        setCash(getCash()+loyer);
    }
    
    public boolean estMort(){
        return cash<0;
    }
    
    //////////////////////////////////////////////////////////////////////
    ///////////////////////////////////GETTEUR&SETTEUR////////////////////
    //////////////////////////////////////////////////////////////////////
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

    public ArrayList<Propriete_A_Construire> getConstructibles() {
        return constructibles;
    }

    public void setConstructibles(ArrayList<Propriete_A_Construire> constructibles) {
        this.constructibles = constructibles;
    }
    
    
    
    public Carreau getPositionCourante() {
        return positionCourante;
    }

    public void setPositionCourante(Carreau positionCourante) {
        this.positionCourante = positionCourante;
    }

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public void setGares(ArrayList<Gare> gares) {
        this.gares = gares;
    }

    public ArrayList<Propriete> getProprietes() {
        return propriete;
    }

    public void setPropriete(ArrayList<Propriete> propriete) {
        this.propriete = propriete;
    }

    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }

    public void setCompagnies(ArrayList<Compagnie> compagnies) {
        this.compagnies = compagnies;
    }
    
    public int getNbProprieteCouleur(CouleurPropriete couleur) {
        int n = 0;
        for (Propriete_A_Construire c : constructibles){
            if (c.getCouleur() == couleur) {
                n++;
            }
        }
        return n;
    }
}