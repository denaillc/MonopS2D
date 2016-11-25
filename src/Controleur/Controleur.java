/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.io.*;
import java.util.*;
import Data.*;
import UI.*;

/**
 *
 * @author albertar
 */
public class Controleur {
    
	private Carreau carreaux;
	private Collection<Joueur> joueurs;

	/**
	 * 
	 * @param j
	 * @param nb
	 */
	private Carreau avancer(Joueur j, int nb) {
		// TODO - implement Controleur.avancer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param type
	 */
	public void miseAJour(int type) {
		// TODO - implement Controleur.miseAJour
		throw new UnsupportedOperationException();
	}

	public Joueur getJoueurCourant() {
		// TODO - implement Controleur.getJoueurCourant
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numC
	 * @param valDes
	 */
	public int calculPosition(int numC, int valDes) {
		// TODO - implement Controleur.calculPosition
		throw new UnsupportedOperationException();
	}


    public void CreerPlateau(String dataFilename){
		buildGamePlateau(dataFilename);
	}
	
	private void buildGamePlateau(String dataFilename)
	{
		try{
			ArrayList<String[]> data = readDataFile(dataFilename, ",");
			
			//TODO: create cases instead of displaying
			for(int i=0; i<data.size(); ++i){
				String caseType = data.get(i)[0];
				if(caseType.compareTo("P") == 0){
					System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("G") == 0){
					System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("C") == 0){
					System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("AU") == 0){
					System.out.println("Case Autre :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else
					System.err.println("[buildGamePleateau()] : Invalid Data type");
			}
			
		} 
		catch(FileNotFoundException e){
			System.err.println("[buildGamePlateau()] : File is not found!");
		}
		catch(IOException e){
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
	}
	
	private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		BufferedReader reader  = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = reader.readLine()) != null){
			data.add(line.split(token));
		}
		reader.close();
		
		return data;
	}
}



