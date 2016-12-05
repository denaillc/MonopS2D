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
public class Ihm {    
     
    
    public static int nbJoueur(){
        int c = 0;
        System.out.print("Choisir le nombre de joueurs : ");
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
        return(c);
    }
    
    public static String nomJoueur(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le nom du joueur");
            String nom = sc.nextLine();
            return(nom);
    }
}
