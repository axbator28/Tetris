
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import tetrismvc.Controlleur.*;
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
import javafx.scene.*;
import javafx.scene.input.*;


/**
 *
 * @author Axel
 */
public class TetrisMVC extends Application  {
    
    private Grille grille;
    private Coordonnee coordcliquee;
    private Rectangle[][] grillerect;
    private Controller control;
    
    
    
    public Grille getGrille(){
        return grille;
    }
    
    public Rectangle[][] GetGrilleRect(){
        return grillerect;
    }
    @Override
    public void start(Stage primaryStage) {

        grille = new Grille(23,8);
        Random rand = new Random();
        Piece piecededepart = new Piece(new Coordonnee(2,2), rand);
        Forme Leu = piecededepart.getForme();
        piecededepart.donneForme(Leu);
        grille.ajoutPieceCourante(piecededepart);
        grille.afficheTableau();
        BorderPane border = new BorderPane();
        
        grillerect = new Rectangle[20][8];
                
        GridPane gPane = new GridPane();
        border.setCenter(gPane);
        int column;
        int row;
        
        int largeur= 30; //largeur des rectangles
        int hauteur = 30; //epaisseur des rectangles
        Rectangle r2 = null;
        for (row=0; row < 20; row++){
            for (column = 0; column < 8; column++){
                Rectangle rect = new Rectangle();
                r2 = rect;
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
                rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        coordcliquee = new Coordonnee((int)rect.getX(), (int)rect.getY());
                    }
                  });
                gPane.getChildren().addAll(rect);
                
                grillerect[row][column] = rect;
            }
            
            
        }
        
        grille.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.print("hello");   
                //r3.setFill(Color.CYAN);
                for (int i=0; i < 20; i++){
                    for (int j = 0; j < 8; j++){
                        if (grille.getTableau()[i+3][j]==1){
                            grillerect[i][j].setFill(Color.GREEN);
                        }
                        if (grille.getTableau()[i+3][j]==2){
                            grillerect[i][j].setFill(Color.RED);
                        }
                        if (grille.getTableau()[i+3][j]==3){
                            grillerect[i][j].setFill(Color.CYAN);
                        }
                        if (grille.getTableau()[i+3][j]==4){
                            grillerect[i][j].setFill(Color.PURPLE);
                        }
                        if (grille.getTableau()[i+3][j]==5){
                           grillerect[i][j].setFill(Color.YELLOW);
                        }
                        if (grille.getTableau()[i+3][j]==6){
                            grillerect[i][j].setFill(Color.BLUE);
                        }
                        if (grille.getTableau()[i+3][j]==7){
                            grillerect[i][j].setFill(Color.ORANGE);
                        }
                        if (grille.getTableau()[i+3][j]==0){
                            grillerect[i][j].setFill(Color.WHITE);
                        }
                
                
                }
            }
            }
        });
        
        new Thread(grille).start();

        
//        grille.addObserver(new Observer(){
//                        @Override
//            public void update(Observable o, Object arg) {
//                for (int i =22; i>2;i--){
//                    for (int j =0; j<8;j++){
//                        if (grille.getTableau()[i][j]==1){
//                            
//                    }
//                }
//            }
//        });
//        }
                
                
        gPane.setGridLinesVisible(true);
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.WHITE);
        MovePiece(scene);
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();
        
}

    
    private void MovePiece(Scene scene) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override public void handle(KeyEvent event) {
          boolean bas = true;
        switch (event.getCode()) {
          case UP:    
                    if (grille.placerotation()){
                        grille.retirePiece(grille.getPiece());
                        grille.getPiece().rotation();
                        grille.posepiece(grille.getPiece());
                    }
                    break;
          
          
          case RIGHT: 
              if (grille.placedroite()){
              grille.retirePiece(grille.getPiece());
              grille.getPiece().deplacedroite();
              grille.posepiece(grille.getPiece());
              }
              break;
          
          case DOWN: break;
          
          
          case LEFT:  
              if (grille.placegauche()){
              grille.retirePiece(grille.getPiece());
              grille.getPiece().deplacegauche();
              grille.posepiece(grille.getPiece());
              }
              break;
        }
      }
    });
}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Grille g = new Grille(20,8);
//        Coordonnee bas = new Coordonnee(1,16);
//        Random ran = new Random();
//        Piece p2 = new Piece(bas, ran);
//        Forme Leu = p2.getForme();
//        p2.donneForme(Leu);
//        g.posepiece(p2);
//        System.out.println("");
//        g.afficheTableau();
//        System.out.println("");
//        g.chutePieceCourante();
        launch(args);
    }
    
}

