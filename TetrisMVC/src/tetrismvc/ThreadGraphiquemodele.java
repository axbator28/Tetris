/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Axel
 */
public class ThreadGraphiquemodele extends Thread {
    Grille grille;
    
    ThreadGraphiquemodele(){
        super();
    }
    
    @Override
    public void run(){
        BorderPane border = new BorderPane();
        GridPane gPane = new GridPane();
        border.setCenter(gPane);
        int column;
        int row;
        
        int largeur= 30; //largeur des rectangles
        int hauteur = 30; //epaisseur des rectangles
        grille.chutePieceCourante();
        for (row=0; row < 20; row++){
            for (column = 0; column < 8; column++){
                Rectangle rect = new Rectangle();
                rect.setWidth(largeur);
                rect.setHeight(hauteur);
                if (grille.getTableau()[row+3][column]==1){
                    rect.setFill(Color.GREEN);
                }
                else{
                    rect.setFill(Color.WHITE);
                }
                GridPane.setRowIndex(rect, row);
                GridPane.setColumnIndex(rect, column);
                gPane.getChildren().addAll(rect);
            }
        grille.chutePieceCourante();
    }
    }
}
