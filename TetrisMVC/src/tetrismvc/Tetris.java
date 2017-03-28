/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.LinkedList;

/**
 *
 * @author Héléna
 */
public class Tetris{
    private Grille plateau;
    private LinkedList<Piece> pieces;

   
    
    public Tetris() {
        plateau=new Grille(10,21);
        pieces=new LinkedList<Piece>();
        // Ajout de trois pièces dans pieces A FAIRE
    }
    
    public void tour(){
        plateau.chutePieceCourante();
        plateau.verifTab();
        // plateau.ajoutPieceCourante(pieces.pollFirst());
        plateau.verifFin();
                    
        }
        
    }
    
    
    
}
