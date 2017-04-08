/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc.Controlleur;


import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import tetrismvc.TetrisMVC;
import tetrismvc.Grille;
/**
 *
 * @author Axel
 */
public class Controller implements KeyListener {

    private TetrisMVC tetris;
    private boolean up, left, right;
    
    public Controller(TetrisMVC jeu){
        tetris = jeu;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Grille grille = tetris.GetGrille();
        int keyCode = e.getKeyCode();
        switch( keyCode ) { 
            case KeyEvent.VK_UP:
                grille.getPiece().rotation();
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                grille.getPiece().deplacegauche();
                left = true;
                break;
            case KeyEvent.VK_RIGHT :
                grille.getPiece().deplacedroite();
                right = true;
                break;
         }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) { 
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT :
                right = false;
                break;
         }
    }
}
