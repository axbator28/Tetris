/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

/**
 *
 * @author Axel
 */
public class Grille {
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
        Coordonnee centre = p.getCentre();
        for (int i = 0; i < p.getCases().length;i++){
            if (tableau[ centre.getY() + (p.getCases()[i]).getY()][centre.getX() + (p.getCases()[i]).getX()]!=0){
                rep = false;
                a = centre.getY() + (p.getCases()[i]).getY();
                b = centre.getX() + (p.getCases()[i]).getX();
                System.out.println("Colision en "+a+";"+b);
            }
        }
        if (tableau[(p.getCentre().getY())][p.getCentre().getX()] !=0){
            rep = false;
            a = p.getCentre().getY();
            b = p.getCentre().getX();
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
        tableau[p.getCentre().getY()][p.getCentre().getX()] = t;
        int a, b;
        for (Coordonnee case1 : p.getCases()) {
            a = case1.getX() + p.getCentre().getX();
            b = case1.getY() + p.getCentre().getY();
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
    
    
}
