/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Modele.*;
import java.util.*;

/**
 *
 * @author albertar
 */
public class Ihm extends Observable {

    private int nbJoueurs = 0;
    private String nomJoueur;

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////AFFICHAGE////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    public void nbJoueur() {
        System.out.print("Inscrire le nombre de joueurs : ");
        while (this.getNbJoueurs() > 6 || this.getNbJoueurs() < 2) {
            try {
                Scanner sc = new Scanner(System.in);
                this.setNbJoueurs(sc.nextInt());
            } catch (InputMismatchException e) {
                System.err.println("*Entrer le nombre de joueurs au format numérique (entre 2 et 6) : ");
            }
        }
        System.out.println("getNbJoueurs: " + this.getNbJoueurs());

        setChanged();
        notifyObservers(Validation.ValiderNombreJoueur);
        clearChanged();
    }

    public void nomJoueur() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le nom du joueur : ");
        setNomJoueur(sc.nextLine());
        System.out.println("\n");

        setChanged();
        notifyObservers(Validation.ValiderNomJoueur);
        clearChanged();
    }

    public void Jouer(Joueur jCourant) {
        Scanner sc = new Scanner(System.in);
        System.out.println("A votre tour Joueur " + jCourant.getNom() + "! (Appuyer sur une touche)");
        sc.nextLine();

        setChanged();
        notifyObservers(Validation.Lancer_Dés);
        clearChanged();
    }

    public void Double() {
        System.out.println("\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//");
        System.out.println("\\//\\//\\//\\//\\//\\//DOUBLE!!!!!!!!!!!!!!!!/\\//\\//\\");
        System.out.println("\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//");
    }

    public void ProposerAchat(Propriete p, Joueur j) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Joueur" + j.getNom() + "peut acheter la propriete" + p.getNom());
        System.out.println("Voulez vous acheter la propriete ? (Oui/Non)");
        String rep = sc.nextLine();
        if (null != rep.toLowerCase()) {
            switch (rep.toLowerCase()) {
                case "oui":
                    setChanged();
                    notifyObservers(Validation.AchatPropriete);
                    clearChanged();
                    break;
                case "non":
                    setChanged();
                    notifyObservers(Validation.NonAchatPropriete);
                    clearChanged();
                    break;
                default:
                    setChanged();
                    notifyObservers(Validation.ErreurProp);
                    clearChanged();
                    break;
            }
        }

    }

    public void payer(Joueur j, Propriete p, int loyer) {
        System.out.println("Skouatage de " + j.getNom() + "dans la propriete de" + p.getProprietaire().getNom());
        System.out.println(j.getNom() + "paye" + loyer + "à" + p.getProprietaire().getNom());
    }

    public void sweetHome(Joueur j) {
        System.out.println("Bienvenu chez toi " + j.getNom());
    }

    public void afficherJoueur(Joueur j) {
        System.out.println("------------------Tour--------------------");
        System.out.println("------------------------------------------");

        System.out.println("Joueur: " + j.getNom());
        System.out.println("Argent: " + j.getCash());
        System.out.println("Position Courante: " + j.getPositionCourante().getNom());
        if (j.getProprietes().isEmpty()) {
            System.out.println("Le joueur " + j.getNom() + "ne possede aucune propriete");
        } else {
            System.out.println("Liste de Propriete que le Joueur " + j.getNom() + " possede");
            for (int i = 0; i < j.getProprietes().size(); i++) {
                System.out.println(j.getProprietes().get(i).getNom());
            }
        }
    }

    //////////////////////////////////////////////////////////
    //////////////////////////////////ADDOBSERVER/////////////
    //////////////////////////////////////////////////////////
    public void abonner(Observer ecoutant) {
        this.addObserver(ecoutant);
    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////GUETTEUR&SETTEUR/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

}
