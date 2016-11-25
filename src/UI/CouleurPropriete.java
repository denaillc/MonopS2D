/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author albertar
 */
public enum CouleurPropriete {
    BleuFonce ("Bleu Foncé"),
    Orange ("Orange"),
    Gris ("Gris"),
    Rouge("Rouge"),
    Jaune("Jaune"),
    Vert("Vert"),
    Violet("Violet"),
    Mauve("Mauve"),
    BleuCiel("Bleu Ciel");
    
    private String name ;

    CouleurPropriete (String name) {
        this.name = name ;
    }

    @Override
    public String toString() {
        return this.name ;
    } 
}
