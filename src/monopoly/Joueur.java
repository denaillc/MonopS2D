/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

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

    public Joueur(String nom) {
        this.gares = new ArrayList();
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

    private void setCash(int cash) {
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
    
    
}
