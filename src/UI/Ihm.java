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
    private int valDés1;
    private int valDés2;

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////AFFICHAGE////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    
    public void CtrlMenu() {
        
        Scanner sc = new Scanner(System.in);
                        String choix ="0";
			do {
			System.out.println("\n*****************************************************************");
			System.out.println("                 *   Monopoly    *");
			System.out.println("*****************************************************************");
			System.out.println("      * 1- Ajouter les joueurs                         *");
			System.out.println("      * 2- Lancer la partie                            *");
			System.out.println("      * 3- Quitter                                     *");
                        System.out.println("      * 9- Version Démo                                *");
			System.out.println("*****************************************************************");
			System.out.print("      Votre Choix : ");
			
                        choix = sc.nextLine();
                        switch (choix) {
				case "1": {
                                            setChanged();
                                            notifyObservers(Validation.ListeJoueurs);
                                            clearChanged();
                                        break; }
				case "2": {
                                            setChanged();
                                            notifyObservers(Validation.LancerPartie);
                                            clearChanged();
                                        break;}
				case "3": {
                                            System.exit(0);
                                        break;}
                                case "9" :{
                                    setChanged();
                                    notifyObservers(Validation.Demo);
                                    clearChanged();
                                    break;}
                                case "0":
                                        return;
				default:
                                       System.out.println("Choix non valide");
                                       break;
				} // switch
                        } while (choix != "0");
    }
    
    public void nbJoueur() {
        System.out.print("Inscrire le nombre de joueurs : ");
        while (this.getNbJoueurs() > 6 || this.getNbJoueurs() < 2) {
            try {
                Scanner sc = new Scanner(System.in);
                this.setNbJoueurs(sc.nextInt());
            } catch (InputMismatchException e) {
                System.err.println("Entrer le nombre de joueurs au format numérique (entre 2 et 6) : ");
            }
        }

        setChanged();
        notifyObservers(Validation.ValiderNombreJoueur);
        clearChanged();
    }

    public void nomJoueur() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le nom du joueur : ");
        setNomJoueur(sc.nextLine());

        setChanged();
        notifyObservers(Validation.ValiderNomJoueur);
        clearChanged();
    }

    public void Jouer(Joueur jCourant) {
        Scanner sc = new Scanner(System.in);
        System.out.println("A votre tour Joueur " + jCourant.getNom() + " ! (Appuyer sur Entree)");
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
        System.out.println("Joueur " + j.getNom() + " peut acheter la propriete " + p.getNom());
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
        System.out.println("Skouatage de " + j.getNom() + " dans la propriete de " + p.getProprietaire().getNom());
        System.out.println(j.getNom() + " paye " + loyer + " à " + p.getProprietaire().getNom());
    }

    public void sweetHome(Joueur j) {
        System.out.println("Bienvenue chez toi " + j.getNom());
    }

    public void afficherJoueur(Joueur j, int k) {
        System.out.println("\n------------------Tour n°" + k + "----------------");
        System.out.println("------------------------------------------");

        System.out.println("Joueur: " + j.getNom());
        System.out.println("Argent: " + j.getCash());
        System.out.println("Position Courante : " + j.getPositionCourante().getNom());
        
        if (j.getProprietes().isEmpty()) {
            System.out.println("Le joueur " + j.getNom() + " ne possede aucune propriete");
        } else {
            System.out.println("Liste de Propriete que le Joueur " + j.getNom() + " possede");
            for (int i = 0; i < j.getProprietes().size(); i++) {
                System.out.println(j.getProprietes().get(i).getNom());
            }
        }
    }
    

    public void erreurLancement() {
        System.out.println("Erreur : Aucun joueur créé");
        CtrlMenu();
    }
    
    
    public void erreurListeJoueurs() {
        System.out.println("Erreur : Joueurs déjà créés");
        CtrlMenu();
    }
    
    public void FinPartie(String nom) {
        System.out.println("\n\n\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//");
        System.out.println("Joueur " + nom + " a gagné !");
        System.out.println("\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//");
        System.exit(0);
    }


    public void mort(Joueur jCourant) {
        System.out.println("\n\n\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//");
        System.out.println("Joueur " + jCourant.getNom() + " a perdu !");
        System.out.println("\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//");
    }
    
    public void etatAvancement(Joueur jCourant) {
        System.out.println("Le joueur avance sur : " + jCourant.getPositionCourante().getNom());
    }



    public void Fauche(Joueur j) {
        System.out.println(j.getNom() + " n'a pas assez d'argent ! Achat impossible");
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////DEMO/////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    
    public void erreurDemo() {
        System.out.println("Erreur : Il faut 3 joueur ");
        CtrlMenu();
         }
    
    public void jouerDemo(Joueur jCourant){
        Scanner sc = new Scanner(System.in);
        System.out.println("A votre tour Joueur " + jCourant.getNom() + " ! (Appuyer sur Entree)");
        sc.nextLine();

        setChanged();
        notifyObservers(Validation.Lancer_Dés_Demo);
        clearChanged();
    }
    
    public void valeursDés(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez la valeur du dés 1 !");
        try {
                setValDés1( sc.nextInt());
            } catch (InputMismatchException e) {
                System.err.println("Entrer un nombre !!!!");
            }
        
        System.out.println("Entrez la valeur du dés 2 !");
        try {
                setValDés2( sc.nextInt());
            } catch (InputMismatchException e) {
                System.err.println("Entrer un nombre !!!!");
            }
        
        setChanged();
        notifyObservers(Validation.Valeur_Dés_Demo);
        clearChanged();
    }
    //////////////////////////////////////////////////////////
    //////////////////////////////////ADDOBSERVER/////////////
    //////////////////////////////////////////////////////////
    public void abonner(Observer ecoutant) {
        this.addObserver(ecoutant);
    }

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////GETTER&SETTER////////////////////////////////////
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
    
    //Demo/////

    public int getValDés1() {
        return valDés1;
    }

    public void setValDés1(int valDés1) {
        this.valDés1 = valDés1;
    }

    public int getValDés2() {
        return valDés2;
    }

    public void setValDés2(int valDés2) {
        this.valDés2 = valDés2;
    }
    


    

    



    



}
