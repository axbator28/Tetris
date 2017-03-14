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
public class Piece {
    private Coordonnee centre;
    private String couleur;
    private Coordonnee[] cases; //contient les coordonnées des cases de la pièce relativement 
    //au centre de gravité, avec le centre de gravité en 0,0 donc
    
    public Piece(Coordonnee coord){
        Coordonnee a = new Coordonnee(-1,0);
        Coordonnee b = new Coordonnee(0,1);
        Coordonnee c = new Coordonnee(1,1);
        centre = coord;
        cases = new Coordonnee[3];
        cases[0] = a;
        cases[1] = b;
        cases[2] = c;
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
    
    public Coordonnee[] getCases(){
        return cases;
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
}
