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
    private Piece piececourante;
    
    public Grille(int h, int l){
        largeur = l;
        hauteur = h;
        tableau = new int[h][l];
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
        Coordonnee c = p.getCentre();
        boolean test=testCollision(c);
        Coordonnee[] tab = p.getCases();
        for(int i=0;i<tab.length;i++){
            tab[i].add(c);
            test=test && testCollision(tab[i]);
        }
        return test;
    }
}
