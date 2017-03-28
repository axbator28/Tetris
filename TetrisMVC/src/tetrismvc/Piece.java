/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.Random;
import java.awt.*; 
/**
 *
 * @author Axel
 */
public class Piece {
    
    private Forme forme;
    private Coordonnee centre;
    private Color couleur;
    private Coordonnee[] cases; //contient les coordonnées des cases de la pièce relativement 
    //au centre de gravité, avec le centre de gravité en 0,0 donc
    
    public Piece(Coordonnee coord){

        cases = new Coordonnee[3];

        centre = coord;

    }
    
    public Piece(Coordonnee coord, Random random){
        centre = coord;
        cases = new Coordonnee[3];
        couleur = Color.BLUE;
        forme = Forme.values()[random.nextInt(7)];
    }
    
    
    
    public void donneForme(Forme F){
        int [][][] listeFormes = {
            {{-1,1}, {1,0}, {0,1}},
            {{1,1}, {0,1}, {-1,0}},
            {{0,-1}, {0,1}, {0,2}},
            {{-1,0}, {1,0}, {0,-1}},
            {{0,1}, {1,0}, {1,1}},
            {{0,1}, {0,2}, {1,0}},
            {{0,1}, {0,2}, {-1,0}}};
        
        for (int i = 0; i < 3 ; i++) {
            cases[i] = new Coordonnee(listeFormes[F.ordinal()][i][0],
                    -listeFormes[F.ordinal()][i][1]);
        }
        forme = F;
    }
    public Piece(Coordonnee coord, Color couleur, Coordonnee[] cases){
        centre=coord;
        this.couleur=couleur;
        this.cases=cases;
    }
    
    @Override
    public Piece clone(){
        return new Piece(centre, couleur, cases);
    }

    public void rotation(){
        int a, b;
        for (Coordonnee case1 : cases) {
            a = case1.getX();
            b = -case1.getY();
            case1.setX(b);
            case1.setY(a);    
        }
    }
    
    public Coordonnee getCentre(){
        return centre;
    }
    
    public void setCentre(Coordonnee newcen){
        centre = newcen;
    }
    
    public Coordonnee[] getCases(){
        return cases;
    }
    
    public void setCases(Coordonnee[] coo){
        cases = coo;
    }
    
    public Color geCouleur(){
        return couleur;
    }
    
    public void setCouleur(Color newcoul){
        couleur = newcoul;
    }
                
    public void deplace(Coordonnee c){
        centre.add(c);
    }
    

    
   /*
    Fonction deplace permet le déplacement suivant la valeur de la direction.
    ATTENTION : pour l'instant, on ne vérifie pas les collisions/sorties de tableau
    */
    public void deplace(char direction)
    {
        if (direction == 'r'){//right
            centre.setX(centre.getX()+1); 
        }
        if (direction == 'g'){//left
            centre.setX(centre.getX()-1);
        }
        if(direction =='u'){//up
            centre.setY(centre.getY()-1);
        }
        if (direction =='d'){//down
            centre.setY(centre.getY()+1);
        }
    }
    
    public void deplacebas(){
        deplace('d');
    }
    
    public void deplacedroite(){
        deplace('r');
    }
    public void deplacegauche(){
        deplace('g');
    }
    
    public Forme getForme(){
        return forme;
    }
    public void setForme(Forme form){
        forme = form;
    }
    
    public Piece copy(){
        Piece copie = new Piece(getCentre());
        copie.setForme(forme);
        copie.setCentre(centre);
        copie.setCases(cases);
        copie.setCouleur(couleur);
        return copie;
    }
}
