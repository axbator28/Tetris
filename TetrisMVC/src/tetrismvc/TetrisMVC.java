/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

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

/**
 *
 * @author Axel
 */
public class TetrisMVC extends Application {
    
    
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
                
        GridPane gPane = new GridPane();
        
        int column = 0;
        int row = 0;
        

        for (String s : new String[]{" ", " ", " ", " ", " ", " ", " ", "0", "0", "0",
            " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",
        " ", " ", " ", " ", " ", " ", " ", " ", " ", " ",}) {
            final Text t = new Text(s);
            t.setWrappingWidth(30);
            t.setFont(Font.font ("Verdana", 20));
            t.setTextAlignment(TextAlignment.CENTER);
            
            gPane.add(t, column++, row);
            
            if (column > 10) {
                column = 0;
                row++;
            }  
            
            t.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
            @Override
            public void handle(MouseEvent event) {
                t.setFont(Font.font("Arial",10));
            }
                
            });
    }
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.LIGHTBLUE);
        
        primaryStage.setTitle("Calc FX");
        primaryStage.setScene(scene);
        primaryStage.show();
}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grille g = new Grille(20,8);
        Coordonnee bas = new Coordonnee(1,16);
        Piece p2 = new Piece(bas);
        Forme L = New 
        p2.donneForme(Forme.L);
        g.posepiece(p2);
        for (int i =0; i<20;i++){
            p2.rotation();
            g.posepiece(p2);
        }

        
        Coordonnee haut = new Coordonnee(1,0);
        Piece p3 = new Piece(haut);
        g.posepiece(p3);
        System.out.println("");
        g.afficheTableau();
        System.out.println("");
        //launch(args);
    }
    
}
