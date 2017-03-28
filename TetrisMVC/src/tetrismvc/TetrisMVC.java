/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.Observable;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import java.util.Random;

/**
 *
 * @author Axel
 */
public class TetrisMVC extends Application {
    
    Grille grille;
    Coordonnee coordcliquee;
    
    
    @Override
    public void start(Stage primaryStage) {
        
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
                rect.setFill(Color.WHITE);
                GridPane.setRowIndex(rect, row);
                GridPane.setColumnIndex(rect, column);
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        coordcliquee = new Coordonnee((int)rect.getX(), (int)rect.getY());
                    }
                  });
                gPane.getChildren().addAll(rect);
            }
        }
        
        grille.addObserver(new Observer(){
                        @Override
            public void update(Observable o, Object arg) {
                for (int i =22; i>2;i--){
                    for (int j =0; j<8;j++){
                        
                    }
                }
            }
        });
                
                
        gPane.setGridLinesVisible(true);
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();
}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grille g = new Grille(20,8);
        Coordonnee bas = new Coordonnee(1,16);
        Random ran = new Random();
        Piece p2 = new Piece(bas, ran);
        Forme Leu = p2.getForme();
        p2.donneForme(Leu);
        g.posepiece(p2);
        System.out.println("");
        g.afficheTableau();
        System.out.println(""); 
        //launch(args);
    }
    
}
