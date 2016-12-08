/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.*;

/**
 *
 * @author albertar
 */
public class Ihm extends Observable{    
     private static int nbJoueurs;
     private static String nomJoueur;
    
   public void nbJoueur() {
        int c = 0;
        System.out.print("Inscrire le nombre de joueur : ");
        while (c > 6 || c < 2) {
            try {
                Scanner sc = new Scanner(System.in);
                c = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Entrer le nombre de joueurs sous format numérique (différent de 0) : ");
            }
            if (c > 6 || c < 2) {
                System.out.print("Entrer un nombre de joueurs entre 2 et 6 :");
            }
        }
        
        setChanged();
        notifyObservers(c);
        clearChanged();
    }
    
    public void nomJoueur(){
        Scanner sc = new Scanner(System.in);
            System.out.print("Entrez le nom du joueur : ");
            String nom = sc.nextLine();
            System.out.println("\n");
            
            setChanged();
            notifyObservers(nom);
            clearChanged();
    }
    
    public static void CreationJoueurs(){
        
            
    }

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
