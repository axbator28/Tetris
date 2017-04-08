
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Axel
 */
public class Piece extends Case{
    
    private Forme forme;
//contient les coordonnées des cases de la pièce relativement 
  //au centre de gravité, avec le centre de gravité en 0,0 donc
   
    static int [][][] listeFormes = {
            {{-1,1}, {1,0}, {0,1}},
            {{-1,0}, {1,0}, {1,1}},
            {{0,-1}, {0,1}, {0,2}},
            {{-1,0}, {1,0}, {0,1}},
            {{0,1}, {1,0}, {1,1}},
            {{1,0}, {2,0}, {0,-1}},
            {{1,0}, {2,0}, {0,1}}};
    
    
    
    public Piece(Coordonnee coord){
        super(coord);
        super.setLien(new Coordonnee[3]);
    }
    
    public void donneForme(Forme F){
        Coordonnee[] t = getLien();
        for (int i = 0; i < 3 ; i++) {
            
            t[i]= new Coordonnee(listeFormes[F.ordinal()][i][0],
                    listeFormes[F.ordinal()][i][1]);
        }
        forme = F;
        setLien(t);
    }
    
    public Piece(Coordonnee coord, Color couleur, Coordonnee[] cases){
        super(coord, couleur, cases);
    }
    
    
        public Piece(Coordonnee coord, Random random){
            super(coord, Color.BLUE);
            forme = Forme.values()[random.nextInt(7)];
            Coordonnee[] cases = new Coordonnee[3];
            for(int i=0; i<3;i++){
                cases[i]=new Coordonnee(listeFormes[forme.ordinal()][i][0], -listeFormes[forme.ordinal()][i][1]);
            }
            setLien(cases);
        
    }
    
    @Override
    public Piece clone(){
        return new Piece(getPosition(), getCouleur(), getLien());
    }

    public void rotation(){
        int a, b;
        for (Coordonnee case1 : getLien()) {
            a = case1.getX();
            b = -case1.getY();
            case1.setX(b);
            case1.setY(a);    
        }
    }
    
    
    public void deplace(Coordonnee c){
        setPosition(getPosition().add(c));
    }
    
   /*
    Fonction deplace permet le déplacement suivant la valeur de la direction.
    ATTENTION : pour l'instant, on ne vérifie pas les collisions/sorties de tableau
    */
    public void deplace(char direction){
        switch(direction){
        case 'r': // Right (droite)
            deplace(new Coordonnee(1,0)); 
            break;
        case 'l': // Left : gauche
            deplace(new Coordonnee(-1,0));
            break;
        case 'u': // Up : Haut
            deplace(new Coordonnee(0,-1));
            break;
        case 'd':  // Down : Bas
            deplace(new Coordonnee(0,1));
            break;
        default:
            break;
        }
    }
    
        public void deplacebas(){
        deplace('d');
    }
   
    public void deplacedroite(){
        deplace('r');
    }
    public void deplacegauche(){
        deplace('l');
    }
    
    public Forme getForme(){
        return forme;
    }
    public void setForme(Forme form){
        forme = form;
    }
}
