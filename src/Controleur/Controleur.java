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
public class Controleur {

    private Ihm vue;
    private Carreau carreaux;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private HashMap<String,String> plateau = new HashMap<>();
    private HashSet<Groupe> groupes = new HashSet<>();

    public Controleur() {
        this.CreerPlateau("src//Data//data.txt");
        creerJoueurs();
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
                Groupe e = new Groupe(CouleurPropriete.valueOf(data.get(i)[2]));
                groupes.add(e);
                
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    Propriete_A_Construire p = new Propriete_A_Construire(i, data.get(i)[2], Integer.valueOf(data.get(i)[5]), Integer.valueOf(data.get(i)[4]), data.get(i)[2]);
                    
                    //AJOUTER p AU PLATEAU;
                    
                } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    //Gare g = new Gare();
                    //AJOUTER g AU PLATEAU;
                    
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    //Compagnie c = new Compagnie();
                    //AJOUTER c AU PLATEAU;
                    
                } else if (caseType.compareTo("AU") == 0) {
                    System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    //Carreau c = new Carreau();
                    //AJOUTER c AU PLATEAU;
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
                this.getPlateau().put(data.get(i)[1], data.get(i)[2]); //Ajoute les cases a la collections plateau
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
       int nb = vue.nbJoueur();
        for(int i = 0; i<nb; i++){
            addJoueurs(new Joueur(vue.nomJoueur()));
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
		int loy = p.calculLoyer(resultde);
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

    public HashMap<String, String> getPlateau() {
        return plateau;
    }

    public void setPlateau(HashMap<String, String> plateau) {
        this.plateau = plateau;
    }
}
