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
    private Carreau carreaux;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private HashMap<Integer,Carreau> plateau = new HashMap<>();
    private HashMap<CouleurPropriete,Groupe> groupes = new HashMap<>();
    private int nbJoueur;
    private String nomJoueur;

    public Controleur() {
        vue.addObserver(this);
        DeroulementMonopoly();
        
        
    }
    
    public void DeroulementMonopoly() {
        CreerPlateau("src//Data//data.txt");
        System.out.println("Joueur");
        creerJoueurs();
        
    
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Validation){
            if(arg == Validation.ValiderNombreJoueur)
                setNbJoueur(vue.getNbJoueurs());
            System.out.println(getNbJoueur());
        }else if (arg instanceof String){
            setNomJoueur((String) arg);
            System.out.println("Bug2");
        }else{
            System.out.println("Fin");
        }
    }
    
     public void addView(Ihm personne) {
        personne.abonner(this);
    }

    ////////////////////////////////////////////////////////////////
    //////CREATION//////////////////////////////////////////////////
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
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Propriete_A_Construire p = new Propriete_A_Construire(i, data.get(i)[2], Integer.valueOf(data.get(i)[5]), Integer.valueOf(data.get(i)[4]), (data.get(i)[3]));
                    putPlateau(i,new Carreau(i,data.get(i)[2]));// Ajout des Carreaux dans le plateau
                    Groupe g = new Groupe(CouleurPropriete.valueOf(data.get(i)[3]));
                            
                    if (!(groupes.keySet().contains(g.getCouleur()))) {
                        groupes.put(g.getCouleur(), g);
                        g.addPropriete(p);
                    }
                    else {
                        for (Groupe gr : groupes.values()) {
                            if (gr.getCouleur() == g.getCouleur()) {
                                gr.addPropriete(p);
                            }
                        }
                    }
                    
                    
                    //AJOUTER p AU PLATEAU;
                    
                } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Gare g = new Gare(i, data.get(i)[2], Integer.valueOf(data.get(i)[3]));
                   putPlateau(i,new Carreau(i,data.get(i)[2]));// Ajout des Carreaux dans le plateau
                    
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Compagnie c = new Compagnie(i, data.get(i)[2], Integer.valueOf(data.get(i)[3]));
                    putPlateau(i,new Carreau(i,data.get(i)[2]));// Ajout des Carreaux dans le plateau
                    
                } else if (caseType.compareTo("AU") == 0) {
                    System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Carreau c = new Carreau(i, String.valueOf(data.get(i)[2]));
                    putPlateau(i,new Carreau(i,data.get(i)[2])); // Ajout des Carreaux dans le plateau
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
    public void creerJoueurs(){ 
       vue.nbJoueur();
        for(int i = 0; i< getNbJoueur(); i++){
            vue.nomJoueur();
            addJoueurs(new Joueur(this.getNomJoueur(),getCarreauPlateau(0)));                                           
        }
        for(Joueur j : joueurs){
            System.out.println(j.getNom());
        }
    }
    
    
    ////////////////////////////////////////////////////////////////////////
    //////////////////////////ACTION////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    
    
    
    private Carreau avancer(Joueur j, int nb) {
        // TODO - implement Controleur.avancer
        throw new UnsupportedOperationException();
    }

    
    public void miseAJour(int type) {
        // TODO - implement Controleur.miseAJour
        throw new UnsupportedOperationException();
    }

    public Joueur getJoueurCourant() {
        // TODO - implement Controleur.getJoueurCourant
        throw new UnsupportedOperationException();
    }

    public int calculPosition(int numC, int valDes) {
        // TODO - implement Controleur.calculPosition
        throw new UnsupportedOperationException();
    }

    public void actionPropriete(Joueur j, int resultde, Propriete p){
     
	if(p.getProprietaire() != null){ //bien possédé
    if(p.getProprietaire() != j){ //j n'est pas le propriétaire
		int loy = p.calculLoyer(resultde, groupes);
                j.payerLoyer(loy); //j paye le loyer
		p.getProprietaire().recevoirLoyer(loy);
               }
	}
	else{
	    if(assezArgent(j,p)){//Proposition d'achat si assez d'argent
             }
	    else{
	 }
	}
    }
    
    
    
    public boolean assezArgent(Joueur j, Propriete p){
        return((j.getCash() - p.getPrixAchat()) <= 0);
    }
    
    public void putPlateau(int i, Carreau c){
        plateau.put(i, c);
    }
    
    
    
    
    //////////////////////////////////////////////////////////////////////
    ///////////////////////GETTEUR&SETTEUR////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    
    
    public Carreau getCarreaux() {
        return carreaux;
    }

    public void setCarreaux(Carreau carreaux) {
        this.carreaux = carreaux;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void addJoueurs(Joueur e) {
        this.joueurs.add(e);
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
    
    public Carreau getCarreauPlateau(int c){
        return getPlateau().get(c);
    }

    
}
