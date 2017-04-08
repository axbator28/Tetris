


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Axel
 */
public class Grille extends Observable implements Runnable {
    private int largeur;
    private int hauteur;
    private int[][] tableau;
    private Case[][] tabCases; //ajouter les modifications sur cette marice
    private Piece piececourante;
    
    
    
    
    
    
    public Grille(int h, int l){
        largeur = l;
        hauteur = h;
        tableau = new int[h][l];
    }
    /**
     * Ajoute la piece en piece courante, et 
     * @param p 
     */
    public void ajoutPieceCourante(Piece p){
        piececourante = p;
        posepiece(piececourante);
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
        System.out.println("test empreinte");
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
    public void posepiece(Piece p){
        if (placelibre(p)){
            empreintePiece(p,1);
        }
    }
    /**
     * Retire la piece p de la grille (remplace la valeur par 0
     * @param p 
     */
    public void retirePiece(Piece p){
        empreintePiece(p,0);
    }
    
    /*
    *Retire la dernière ligne du tableau, et fait descendre
    *toutes les cases de 1.
    */
    public void retireLigne(int ligne){
        if (ligne<=hauteur){
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
    
    public void afficheTableau(){
        for (int i = 0; i<hauteur;i++){
            for (int j = 0; j<largeur;j++){
                System.out.print(tableau[i][j]);
            }
            System.out.println("");
        }
    }
    /** Test de ligne pleine
     *  Si la ligne est complète, il faudra l'effacer
     * @param i ligne à tester
     * @return true si la ligne est pleine, sinon false
     */
    public boolean testLigne(int i){
        int j=0;
        while(j<largeur && tableau[i][j]==1){
            j++;
        }
        return j==largeur-1;
        }
    
    /** Actualise le tetris tel que les lignes pleines soient effacées
     * 
     */
    public void actuTetris(){
        for(int i=0;i<hauteur;i++){
            if(testLigne(i)){
                retireLigne(i);
            }
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
    
    public int getHauteur(){
        return hauteur;
    }
    
    public int getLargeur(){
        return largeur;
    }
    
    public void setPiece( Piece p){
        piececourante = p;
    }
    
    public Piece getPiece(){
        return piececourante;
    }
    
    public Case[][] getCases(){
        return tabCases;
    }
    
    public int[][] getTableau(){
        return tableau;
    }
    
    public void setTableau(int[][] tab){
        tableau = tab;
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
     * Vérifie si la case d'en dessous de la piece est vide ou que l'on est pas en fin de tableau
     * @param p piece chekee
     */
        
    public boolean verifsous(Piece p){
        if (p.getPosition().getY()>=hauteur-1){
            return false;
        }
        for (int i = 0; i<3;i++){
            if (p.getPosition().getY() + p.getLien()[i].getY()>=hauteur-1){
                return false;
            }
        }
        if (tableau[p.getPosition().getY()+1][p.getPosition().getX()]!=0){
            return false;
        }
        for (int i = 0; i<3;i++){
            if (tableau[p.getPosition().getY() + p.getLien()[i].getY()+1][p.getPosition().getX()+ p.getLien()[i].getX()]!=0){
                return false;
            }
        }
        return true;
            
    }
    
    /**
     * Fait descendre la piece courante
     */
    public void chutePieceCourante(){
//        System.out.println("Avant clone");
//        Piece copie =piececourante.clone();
        retirePiece(piececourante);
        System.out.println("Avant chute");
        piececourante.deplace('d');
        System.out.println("après deplace");
//        retirePiece(piececourante);
        if (verifsous(piececourante)){
            if(placelibre(piececourante)){
            posepiece(piececourante);
            }
        }
        else{
            posepiece(piececourante);
            Random rand = new Random();
            Coordonnee base = new Coordonnee(4,4);
            piececourante = new Piece(base, rand);
        }
    }

    @Override
    public void run() {
        while (true) {
        chutePieceCourante();
        setChanged();
        notifyObservers();
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}



