/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.*;
/**
 *
 * @author Axel
 */
public class Grille extends Observable {

    private int largeur;
    private int hauteur;
    private int[][] tableau;
    private Carte tabCases; //ajouter les modifications sur cette marice
    private Piece piececourante;
    
    public Grille(int h, int l){
        largeur = l;
        hauteur = h;
        tableau = new int[h][l];
        tabCases = new Carte(h,l);
    }
    /**
     * Ajoute la piece en piece courante, et 
     * @param p 
     */
    public void ajoutPieceCourante(Piece p){
        piececourante = p;
    }

    public Piece getPiececourante() {
        return piececourante;
    }
    /**
     * La piece n'est pas encore posée. On vérifie si on peu avec ses coordonnées actuelles
     * @param p
     * @return boolean
     */
    public boolean placelibre(Piece p){
        boolean rep = true;
        int a = 0;
        int b =0;
        Coordonnee centre = p.getPosition();
        for (int i = 0; i < p.getLien().length;i++){
            if (tableau[ centre.getY() + (p.getLien()[i]).getY()][centre.getX() + (p.getLien()[i]).getX()]!=0){
                rep = false;
                a = centre.getY() + (p.getLien()[i]).getY();
                b = centre.getX() + (p.getLien()[i]).getX();
                System.out.println("Colision en "+a+";"+b);
            }
        }
        if (tableau[(p.getPosition().getY())][p.getPosition().getX()] !=0){
            rep = false;
            a = p.getPosition().getY();
            b = p.getPosition().getX();
            System.out.println("Colision en "+a+";"+b);
        }
        return rep;
    }
    /**
     * laisse dans le tableau l'empreinte de la piece avec la valeur t
     * @param p Piece
     * @param t Int
     */
    public void empreintePiece(Piece p , int t){
        tableau[p.getPosition().getY()][p.getPosition().getX()] = t;
        int a, b;
        for (Coordonnee case1 : p.getLien()) {
            a = case1.getX() + p.getPosition().getX();
            b = case1.getY() + p.getPosition().getY();
            tableau[b][a]=t;
        }
        
    }
    /**
     * Pose la piece p dans la grille, avec des 1
     * @param p 
     */
    public boolean posepiece(Piece p){
        boolean test = false;
        if (placelibre(p)){
            empreintePiece(p,1);
            tabCases.setPiece(p);
            test = true;
        }
        return test;
    }
    /**
     * Retire la piece p de la grille (remplace la valeur par 0
     * @param p 
     */
    public void retirePiece(Piece p){
        empreintePiece(p,0);
        tabCases.removePiece(p);
    }
    
    /*
    *Retire la dernière ligne du tableau, et fait descendre
    *toutes les cases de 1.
    */
    public void retireLigne(int ligne){
        if (ligne<=hauteur){
            tabCases.retireLigne(ligne, largeur);
            for (int i =ligne; i >0; i-- ){
                for (int j=0; j<largeur; j++){
                    tableau[i][j] = tableau[i-1][j];
                }
            }
            for (int i =0;i< largeur;i++){
                tableau[0][i] = 0;
            }
        }
    }
    
    public boolean verifLigne(int l){
        boolean test = true;
        for(int i =0; i<largeur; i++){
            test=test && tableau[l][i]==1;
        }
        return test;
    }
    
    public void verifTab(){
        for(int i=0; i<hauteur; i++){
            if(verifLigne(i)){
                retireLigne(i);
            }
        }
    }
    
    public void afficheTableau(){
        for (int i = 0; i<hauteur;i++){
            for (int j = 0; j<largeur;j++){
                System.out.print(tableau[i][j]);
            }
            System.out.println("");
        }
    }
    
    /** Test d'un déplacement pour le rushour
     * On ne peut déplacer une pièce que dans sa longueur et si elle ne se supperpose pas après à une autre
     * @param p La pièce à déplacer
     * @param c Les coordonnées correspondant au déplacement à effectuer (vecteur)
     * @return 
     */
    public boolean testDRushHour(Piece p, Coordonnee c){
        int x=c.getX();
        int y=c.getY();
        boolean test;
        if(x!=0 && y!=0){
            test=false;
        }
        else{
            Piece pt=p.clone();
            pt.deplace(c);
            test=testCollision(pt);
        }
        return test;
    }
    
    public boolean testCollision(Coordonnee c){
        int x=c.getX();
        int y=c.getY();
        return tableau[x][y]==0;
    }
    
    public boolean testCollision(Piece p){
        Coordonnee c = p.getPosition();
        boolean test=testCollision(c);
        Coordonnee[] tab = p.getLien();
        for(int i=0;i<tab.length;i++){
            tab[i].add(c);
            test=test && testCollision(tab[i]);
        }
        return test;
    }

    public void verifFin() {
        boolean test = false;
        for(int i=0; i<largeur;i++){
           if (tableau[0][i]==1)
           {test = true;}
        }
        if(test){
            System.out.println("Partie finie.");
        }
    }
        /**
     * Fait descendre la piece courante
     */
        public void chutePieceCourante(){
        Piece copie =piececourante.clone();
        copie.deplace('d');
        retirePiece(piececourante);
        if (placelibre(copie)){
            posepiece(copie);
            piececourante = copie;
        }
        else{
            posepiece(piececourante);
            Random rand = new Random();
            Coordonnee base = new Coordonnee(4,4);
            piececourante = new Piece(base, rand);
        }
    }
}