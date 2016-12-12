/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import UI.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author albertar
 */
public class Controleur implements Observer {

    private Ihm vue = new Ihm();
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private ArrayList<Joueur> deathNote = new ArrayList(); //Liste de joueur eliminer
    private HashMap<Integer, Carreau> plateau = new HashMap<>();
    private HashMap<CouleurPropriete, Groupe> groupes = new HashMap<>();
    private int nbJoueur;
    private String nomJoueur;
    private int valDés1;
    private int valDés2;
    private int valDésTot;
    private Joueur jCourant;

    public Controleur() {
        vue.addObserver(this); 
        CreerPlateau("src//Data//data.txt");                                                                                     //Creer et instancie le plateau
        vue.CtrlMenu();                                                                                                          //Affiche le menu

    }

    public void DeroulementMonopoly() {
        
        int i = 0;
        //Lancement du tour de jeu
        while (getJoueurs().size() > 1) {                                                                                       // Tant qu'il y a au moins 2 Joueur dans la partie
            i++;
            for (Joueur j : getJoueurs()) {                                                                                     // Pour chaque joueur encore en jeu
                setjCourant(j);                                                                                                 // Permet de déterminer le joueur Courant
                vue.afficherJoueur(getjCourant(), i);                                                                           // Affiche les information du joueur Courant
                vue.Jouer(getjCourant());                                                                                       // Lance les dés
                avancer(j, getValDésTot());                                                                                     // Le joueur courant avance
                if (j.getPositionCourante().estProp()) {                                                                        // Si c'est une propriete 
                    actionPropriete(getjCourant(), getValDésTot(), (Propriete) j.getPositionCourante());                        // Agit sur le loyer
                }
                while (getValDés1() == getValDés2()) {
                    vue.Double();                                                                                               // Affichage double
                    vue.Jouer(getjCourant());                                                                                   // Rejoue
                    avancer(getjCourant(), getValDésTot());
                    
                    if (j.getPositionCourante().estProp()) {                                                                     //Si c'est une propriete 
                    actionPropriete(getjCourant(), getValDésTot(), (Propriete) getjCourant().getPositionCourante());             //Agit sur le loyer
                }
                    
                }
                
                if (getjCourant().estMort()) {                                                                                  // Si le joueur est en faillite
                    addJoueurDeathNote(getjCourant());                                                                          //Ajoute le joueur a la liste des joueurs morts
                    ventePropriete(getjCourant());                                                                              //Reinitialise tous les proprietaires des proprietes possédées par le jCourant
                    vue.mort(getjCourant());                                                                                    //affiche le joueur mort
                }

            }

            for (Joueur j : getDeathNote()) {                                                                                   
                removeJoueurVivant(getjCourant());                                                                              //Supprime tous les joueurs de la collection Joueurs contenu dans la collection DeathNote
            }

            deathNote.clear();                                                                                                  // Une fois la liste de joueurs exclus appliquée, on vide la liste pour les tours suivants
        }

        vue.FinPartie(getJoueurs().get(0).getNom());                                                                            // Fin de partie, le joueur à l'indice 0 est vainqueur

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Validation) {
            if (arg == Validation.ValiderNombreJoueur) {                                                                        // Cas : Entrée du nombre de joueurs
                setNbJoueur(vue.getNbJoueurs());                                                                                // On initialise le nombre de joueurs avec celui fourni par la vue
            }
            if (arg == Validation.ValiderNomJoueur) {                                                                           // Cas : Entrée du nom d'un joueur
                setNomJoueur(vue.getNomJoueur());                                                                               // On entre le nom d'un joueur à partir du celui fourni par la vue
            }
            if (arg == Validation.Lancer_Dés) {                                                                                 // Cas : Lancer de dés
                setValDés1(Utilitaire.De3());                                                                                   // On roll le premier dé
                setValDés2(Utilitaire.De3());                                                                                   // puis le second dé
                setValDésTot(getValDés1() + getValDés2());                                                                      // On additionne le total à partir de ces deux lancers
            }
            if (arg == Validation.AchatPropriete) {                                                                             // Cas : Achat d'une propriété
                ((Propriete) getjCourant().getPositionCourante()).acheterPropriete(jCourant);                                   // La propriété où le joueur jCourant est achetée et lui appartient désormais

            }
            if (arg == Validation.ErreurProp) {                                                                                 // Cas : Erreur propriété, quand le joueur entre autre chose que "oui" ou "non"
                vue.ProposerAchat((Propriete) jCourant.getPositionCourante(), getjCourant());                                   // On rappelle la fonction ProposerAchat tant qu'il ne répondra pas "oui" ou "non"
            }
            if (arg == Validation.ListeJoueurs) {                                                                               // Cas : Création de la liste de joueurs

                if (getJoueurs().size() == 0) {                                                                                 // Si la liste n'est pas encore faite,
                    creerJoueurs();                                                                                             // On la crée
                } else {                                                                                                        // Sinon on renvoie l'erreur (liste déjà créée)
                    vue.erreurListeJoueurs();
                }

            }
            if (arg == Validation.LancerPartie) {                                                                               // Cas : Lancement de la partie
                if (getJoueurs().size() != 0) {                                                                                 // Si la liste de joueurs est bien créée,
                    DeroulementMonopoly();                                                                                      // On peut lancer la partie
                } else {                                                                                                        // Sinon on renvoie l'erreur (lancement)
                    vue.erreurLancement();
                }
            }


        } else {
            System.out.println("Erreur Validation Enumerer");                                                                   //Gestion d'erreur si modification du code et probleme d'enumeration
            System.exit(0);                                                                                                     // Fin du programme
        }
    }

    public void addView(Ihm personne) {
        personne.abonner(this);                                                                                                 //ajoute la vue
    }

    ////////////////////////////////////////////////////////////////
    //////////////////////CREATION//////////////////////////////////
    ////////////////////////////////////////////////////////////////
                                                                                                                                //Creation plateau
    public void CreerPlateau(String dataFilename) {
        buildGamePlateau(dataFilename);
    }

    private void buildGamePlateau(String dataFilename) {

        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            for (int i = 0; i < data.size(); ++i) {

                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    Propriete_A_Construire p = new Propriete_A_Construire(i, data.get(i)[2], Integer.valueOf(data.get(i)[5]), Integer.valueOf(data.get(i)[4]), (data.get(i)[3]));
                    putPlateau(i, p);                                                                                           // Ajout des Carreaux dans le plateau
                    Groupe g = new Groupe(CouleurPropriete.valueOf(data.get(i)[3]));

                    if (!(groupes.keySet().contains(g.getCouleur()))) {
                        groupes.put(g.getCouleur(), g);
                        g.addPropriete(p);
                    } else {
                        for (Groupe gr : groupes.values()) {
                            if (gr.getCouleur() == g.getCouleur()) {
                                gr.addPropriete(p);
                            }
                        }
                    }

                    
                } else if (caseType.compareTo("G") == 0) {
                    Gare g = new Gare(i, data.get(i)[2], Integer.valueOf(data.get(i)[3]));
                    putPlateau(i, g);                                                                                           // Ajout des Carreaux dans le plateau

                } else if (caseType.compareTo("C") == 0) {
                    Compagnie c = new Compagnie(i, data.get(i)[2], Integer.valueOf(data.get(i)[3]));
                    putPlateau(i, c);                                                                                           // Ajout des Carreaux dans le plateau

                } else if (caseType.compareTo("AU") == 0) {
                    Carreau ca = new Carreau(i, String.valueOf(data.get(i)[2]));
                    putPlateau(i, ca);                                                                                          // Ajout des Carreaux dans le plateau
                } else {
                    System.err.println("[buildGamePlateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

                                                                                                                                //Creation de joueurs
    public void creerJoueurs() {
        vue.nbJoueur();
        for (int i = 0; i < getNbJoueur(); i++) {
            vue.nomJoueur();
            addJoueurs(new Joueur(this.getNomJoueur(), getCarreauCourant(0)));
        }
    }

    ////////////////////////////////////////////////////////////////////////
    //////////////////////////ACTION////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    public void avancer(Joueur jCourant, int valdes) {
        Carreau cCourant = jCourant.getPositionCourante();                                                                      //Recupere le Carreau du jCourant
        int numC = cCourant.getNumero();                                                                                            
        int newNumC = calculPosition(numC, getValDésTot());                                                                     //Utilisa la methode calculPosition
        Carreau newC = getCarreauCourant(newNumC % 40);                                                                         //Le modulo permet de faire des tours de plateau
        jCourant.setPositionCourante(newC);
        vue.etatAvancement(jCourant);                                                                                           //Affiche le carreau sur lequel est le jCourant
    }

    public int calculPosition(int numC, int valDes) {
        return numC + valDes;                                                                                                   //Calcule la position en fonction de la position du joueur et de la valeur des dés 
    }

    public void actionPropriete(Joueur j, int resultde, Propriete p) {
        if (p.getProprietaire() == null) {                                                                                      //Si la propriete n'a pas de proprietaire
            int cash = j.getCash();
            int loyer = p.getPrixAchat();
            if (cash > loyer) {                                                                                                 //Si le joueur a assez d'argent
                vue.ProposerAchat(p, j);
            }
            else {
                vue.Fauche(j);
            }
        } else if (j != p.getProprietaire()) {
            int loyer = p.calculLoyer(valDés1, groupes);
            j.payerLoyer(loyer);
            p.getProprietaire().recevoirLoyer(loyer);
            vue.payer(j, p, loyer);
        } else {
            vue.sweetHome(j);
        }
    }

    public boolean assezArgent(Joueur j, Propriete p) {
        return ((j.getCash() - p.getPrixAchat()) <= 0);
    }

    public void putPlateau(int i, Carreau c) {
        plateau.put(i, c);
    }

    public void addJoueurs(Joueur e) {
        getJoueurs().add(e);
    }

    public void removeJoueurVivant(Joueur j) {
        getJoueurs().remove(j);
    }

    public void addJoueurDeathNote(Joueur j) {
        getDeathNote().add(j);
    }
    
     public void ventePropriete(Joueur j) {
       for(Propriete p : j.getProprietes())
           p.setProprietaire(null);
    }

    //////////////////////////////////////////////////////////////////////
    ///////////////////////GETTEUR&SETTEUR////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public HashMap<Integer, Carreau> getPlateau() {
        return plateau;
    }

    public void setPlateau(HashMap<Integer, Carreau> plateau) {
        this.plateau = plateau;
    }

    public Ihm getVue() {
        return vue;
    }

    public void setVue(Ihm vue) {
        this.vue = vue;
    }

    public HashMap<CouleurPropriete, Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(HashMap<CouleurPropriete, Groupe> groupes) {
        this.groupes = groupes;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public Carreau getCarreauCourant(int c) {
        return getPlateau().get(c);
    }

    public int getValDés2() {
        return valDés2;
    }

    public void setValDés2(int valDés2) {
        this.valDés2 = valDés2;
    }

    public int getValDés1() {
        return valDés1;
    }

    public void setValDés1(int valDés1) {
        this.valDés1 = valDés1;
    }

    public int getValDésTot() {
        return valDésTot;
    }

    public void setValDésTot(int valDésTot) {
        this.valDésTot = valDésTot;
    }

    public Joueur getjCourant() {
        return jCourant;
    }

    public void setjCourant(Joueur jCourant) {
        this.jCourant = jCourant;
    }

    public ArrayList<Joueur> getDeathNote() {
        return deathNote;
    }

    public void setDeathNote(ArrayList<Joueur> deathNote) {
        this.deathNote = deathNote;
    }

   

}
