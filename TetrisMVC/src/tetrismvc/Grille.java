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
    private int[][] tableau;
    
    public Grille(){
        tableau = new int[20][8];
    }
    
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
    
    public void empreintePiece(Piece p , int t){//laisse dans le tableau l'empreinte de la piece avec la valeur t
        tableau[p.getCentre().getY()][p.getCentre().getX()] = t;
        int a, b;
        for (Coordonnee case1 : p.getCases()) {
            a = case1.getX() + p.getCentre().getX();
            b = case1.getY() + p.getCentre().getY();
            tableau[b][a]=t;
        }
        
    }
    
    public void posepiece(Piece p){
        if (placelibre(p)){
            empreintePiece(p,1);
        }
    }
    
    public void retirePiece(Piece p){
        empreintePiece(p,0);
    }
    
    /*
    *Retire la dernière ligne du tableau, et fait descendre
    *toutes les cases de 1.
    */
    public void retireLigne(){
        for (int i =tableau.length-1; i >0; i-- ){
            for (int j=0; j<tableau[0].length - 1; j++){
                tableau[i][j] = tableau[i-1][j];
            }
        }
        for (int i =0;i<=tableau[0].length - 1;i++){
            tableau[0][i] = 0;
        }
    }
    
    
    public void afficheTableau(){
        for (int i = 0; i<20;i++){
            for (int j = 0; j<8;j++){
                System.out.print(tableau[i][j]);
            }
            System.out.println("");
        }
    }
    
    
}
