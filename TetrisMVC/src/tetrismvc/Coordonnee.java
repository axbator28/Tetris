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
public class Coordonnee {
    private int x;
    private int y;
    
    public Coordonnee(int x1, int y1){
        x = x1;
        y = y1;
    }
    public void setX(int x1){
        x = x1;
    }
    
    public void setY(int y1){
        y = y1;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void add(Coordonnee c){
        x+=c.getX();
        y+=c.getY();
    }
}
