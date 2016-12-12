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
public class Groupe {
    
    
    private CouleurPropriete couleur;
    private ArrayList<Propriete_A_Construire> proprietes;

    public Groupe(CouleurPropriete couleur, ArrayList<Propriete_A_Construire> proprietes) {
        this.couleur = couleur;
        this.proprietes = proprietes;
    }

    public Groupe(CouleurPropriete couleur) {
        this.couleur = couleur;
        this.proprietes = new ArrayList<>();
    }
    
    
    //////////////////////////////////////////////////////////////////////
    ///////////////////////////////////GETTEUR&SETTEUR////////////////////
    //////////////////////////////////////////////////////////////////////

    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }

    public ArrayList<Propriete_A_Construire> getProprietes() {
        return proprietes;
    }

    public void setProprietes(ArrayList<Propriete_A_Construire> proprietes) {
        this.proprietes = proprietes;
    }
    
    public void addPropriete(Propriete_A_Construire p) {
        proprietes.add(p);
    }
    
    
}
