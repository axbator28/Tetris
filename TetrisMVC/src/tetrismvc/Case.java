/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;
import java.awt.*;  

/**
 *
 * @author Axel
 */
public class Case {
    private Coordonnee position;
    private Coordonnee[] lien;
    private Color couleur;

    Case(Coordonnee c) {
        position = c;
    }
    
    public Case(Coordonnee coord, String couleur, Coordonnee[] cases){
        position=coord;
        //this.couleur = couleur;
        lien=cases;
    }
    

    public void setPosition(Coordonnee position) {
        this.position = position;
    }

    /*
    Recupere les cases liees à la case cible
     */
    /*public Case[] getCasesLiees(Case[] tab, int compteur){
    tab[compteur] = this;
    compteur++;
    for (int i =0; i< lien.length-1; i++){
    if
    lien[i].getCasesLiees(tab, compteur);
    compteur++;
    }
    return tab;
    }*/
    public void setLien(Coordonnee[] lien) {
        this.lien = lien;
    }

    public Coordonnee getPosition() {
        return position;
    }

    public Coordonnee[] getLien() {
        return lien;
    }

    public Color getCouleur() {
        return couleur;
    }
}
