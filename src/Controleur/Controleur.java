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
        CreerPlateau("src//Data//data.txt");
        vue.CtrlMenu();

    }

    public void DeroulementMonopoly() {

        //Lancement du tour de jeu
        while (getJoueurs().size() > 1) {                                                               // Tant qu'il y a au moins 2 Joueur dans la partie
            for (Joueur j : getJoueurs()) {
                setjCourant(j);                                                                         // Permet de déterminer le joueur Courant
                vue.afficherJoueur(getjCourant());                                                      //Affiche les information du joueur Courant
                vue.Jouer(j);                                                                           //Lance les dés
                avancer(j, getValDésTot());                                                             // Le joueur courant avance
                while (getValDés1() == getValDés2()) {
                    vue.Double();//Affichage double
                    vue.Jouer(j);//Rejoue
                    avancer(j, getValDésTot());
                    
                }
                if (j.getPositionCourante().estProp()) { //Si c'est une propriete 
                    actionPropriete(j, getValDésTot(), (Propriete) j.getPositionCourante()); //Agit sur le loyer
                }
                if (getjCourant().estMort()) {
                    addJoueurDeathNote(j);      //Ajoute le joueur a la liste des joueurs mort
                    /*Probleme car si le joueur est supprimer
                                                Erreur etant donner que l'on supprime 
                                                un joueur de la collection que l'on parcours*/

                }

            }

            for (Joueur j : getDeathNote()) {
                removeJoueurVivant(j);
            }

            deathNote.clear();
        }

        vue.FinPartie(getJoueurs().get(0).getNom());

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Validation) {
            if (arg == Validation.ValiderNombreJoueur) {
                setNbJoueur(vue.getNbJoueurs());
            }
            if (arg == Validation.ValiderNomJoueur) {
                setNomJoueur(vue.getNomJoueur());
            }
            if (arg == Validation.Lancer_Dés) {
                setValDés1(Utilitaire.De3());
                setValDés2(Utilitaire.De3());
                setValDésTot(getValDés1() + getValDés2());
            }
            if (arg == Validation.AchatPropriete) {
                ((Propriete) getjCourant().getPositionCourante()).acheterPropriete(jCourant);

            }
            if (arg == Validation.ErreurProp) {
                vue.ProposerAchat((Propriete) jCourant.getPositionCourante(), getjCourant());
            }
            if (arg == Validation.ListeJoueurs) {

                if (getJoueurs().size() == 0) {
                    creerJoueurs();
                } else {
                    vue.erreurListeJoueurs();
                }

            }
            if (arg == Validation.LancerPartie) {
                if (getJoueurs().size() != 0) {
                    DeroulementMonopoly();
                } else {
                    vue.erreurLancement();
                }
            }


        } else {
            System.out.println("Erreur Validation Enumerer");
            System.exit(0);
        }
    }

    public void addView(Ihm personne) {
        personne.abonner(this);
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

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {

                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    Propriete_A_Construire p = new Propriete_A_Construire(i, data.get(i)[2], Integer.valueOf(data.get(i)[5]), Integer.valueOf(data.get(i)[4]), (data.get(i)[3]));
                    putPlateau(i, p);// Ajout des Carreaux dans le plateau
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

                    //AJOUTER p AU PLATEAU;
                } else if (caseType.compareTo("G") == 0) {
                    Gare g = new Gare(i, data.get(i)[2], Integer.valueOf(data.get(i)[3]));
                    putPlateau(i, g);// Ajout des Carreaux dans le plateau

                } else if (caseType.compareTo("C") == 0) {
                    Compagnie c = new Compagnie(i, data.get(i)[2], Integer.valueOf(data.get(i)[3]));
                    putPlateau(i, c);// Ajout des Carreaux dans le plateau

                } else if (caseType.compareTo("AU") == 0) {
                    Carreau ca = new Carreau(i, String.valueOf(data.get(i)[2]));
                    putPlateau(i, ca); // Ajout des Carreaux dans le plateau
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
        Carreau cCourant = jCourant.getPositionCourante();
        int numC = cCourant.getNumero();
        int newNumC = calculPosition(numC, getValDésTot());
        Carreau newC = getCarreauCourant(newNumC % 40);//Permet de faire des tours de plateau
        jCourant.setPositionCourante(newC);
        vue.etatAvancement(jCourant);
    }

    public int calculPosition(int numC, int valDes) {
        return numC + valDes;
    }

    public void actionPropriete(Joueur j, int resultde, Propriete p) {
        if (p.getProprietaire() == null) {
            int cash = j.getCash();
            int loyer = p.getPrixAchat();
            if (cash > loyer) {
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
