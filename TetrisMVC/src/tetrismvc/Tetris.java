///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tetrismvc;
//
//import java.util.LinkedList;
//
///**
// *
// * @author Héléna
// */
//public class Tetris{
//    private Grille plateau;
//    private LinkedList<Piece> pieces;
//
//   
//    
//    public Tetris() {
//        plateau=new Grille(10,20);
//        pieces=new LinkedList<Piece>;
//        // Ajout de trois pièces dans pieces A FAIRE
//    }
//    
//    public void tour(){
//        Piece p = plateau.getPiececourante();
//        p.deplace(new Coordonnee(0,1));
//        if(!plateau.posepiece(p)){
//            System.out.println("Fin de la partie.");
//        }
//        else{
//            // VERIF LIGNE PLEINE
//            plateau.ajoutPieceCourante(pieces.pollFirst());
//            // Ajout d'une pièce random à pieces A FAIRE
//                    
//        }
//        
//    }
//    
//    public void nouvellePiece(){
//        plateau.ajoutPieceCourante(pieces.pollFirst());
//        pieces.add(new Piece());
//    }
//    
//}
