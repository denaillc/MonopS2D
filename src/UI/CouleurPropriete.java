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
    bleuFonce ("Bleu Foncé"),
    orange ("Orange"),
    gris ("Gris"),
    rouge("Rouge"),
    jaune("Jaune"),
    vert("Vert"),
    violet("Violet"),
    mauve("Mauve"),
    bleuCiel("Bleu Ciel");
    
    private String name ;

    CouleurPropriete (String name) {
        this.name = name ;
    }

    @Override
    public String toString() {
        return this.name ;
    } 
}
