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
    
    enum Forme {
    Z, 
    S, 
    ligne, 
    t, 
    carre, L, Linverse };
    
    private Forme forme;
    private Coordonnee centre;
    private String couleur;
    private Coordonnee[] cases; //contient les coordonnées des cases de la pièce relativement 
    //au centre de gravité, avec le centre de gravité en 0,0 donc
    
    public Piece(Coordonnee coord){

        cases = new Coordonnee[3];

        centre = coord;

    }
    
    public void donneForme(Forme F){
        int [][][] listeFormes = {
            {{-1,1}, {1,0}, {0,1}},
            {{-1,0}, {1,0}, {1,1}},
            {{0,-1}, {0,1}, {0,2}},
            {{-1,0}, {1,0}, {1,0}},
            {{0,1}, {1,0}, {1,1}},
            {{1,0}, {2,0}, {1,0}},
            {{-1,0}, {1,0}, {2,0}}};
        
        for (int i = 0; i < 3 ; i++) {
            cases[i] = new Coordonnee(listeFormes[F.ordinal()][i][0],
                    listeFormes[F.ordinal()][i][1]);
        }
        forme = F;
    }
    public Piece(Coordonnee coord, String couleur, Coordonnee[] cases){
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
    
    public Coordonnee[] getCases(){
        return cases;
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
}
