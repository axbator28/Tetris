/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Axel
 */
public class VueGrille {
    Grille grille;
    Coordonnee coordcliquee;
    
    public void draw(Stage primaryStage) {
        
        grille = new Grille(23,8);
        Random rand = new Random();
        grille.ajoutPieceCourante(new Piece(new Coordonnee(4,4), rand));
        
        BorderPane border = new BorderPane();
                
        GridPane gPane = new GridPane();
        border.setCenter(gPane);
        int column;
        int row;
        
        int largeur= 30; //largeur des rectangles
        int hauteur = 30; //epaisseur des rectangles
        
        for (row=0; row < 20; row++){
            for (column = 0; column < 8; column++){
                Rectangle rect = new Rectangle();
                rect.setWidth(largeur);
                rect.setHeight(hauteur);
                if (grille.getTableau()[row+3][column]==1){
                    rect.setFill(Color.WHITE);
                }
                else{
                    rect.setFill(Color.WHITE);
                }
                GridPane.setRowIndex(rect, row);
                GridPane.setColumnIndex(rect, column);
                gPane.getChildren().addAll(rect);
            }
        }
    }
}
