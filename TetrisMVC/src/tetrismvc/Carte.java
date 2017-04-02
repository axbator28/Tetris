
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

import java.util.HashMap;
import java.util.LinkedList;

public class Carte {
    HashMap<Coordonnee, Case> map;

    Carte(int h, int l) {
        map=new HashMap<Coordonnee, Case>();
        for(int i=0;i<h;i++){
            for(int j=0;j<l; j++){
                map.put(new Coordonnee(i,j), new Case(new Coordonnee(i,j)));
            }
        }
    }


    public Case getCase(Coordonnee k){
        Case c = null;
        if(map.containsKey(k)){
            c=map.get(k);
        }
        else{
            System.out.println("Erreur : la case n'existe pas");
        }
        return c;
    }
    
    public void setCase(Coordonnee k, Case c){
        if(map.containsKey(k)){
            map.remove(k);
            map.put(k, c);
        }
        else{
            System.out.println("Erreur : la case n'existe pas");
        }
    }
    
    public void setPiece(Piece p){
        Coordonnee c = p.getPosition();
        Coordonnee[] pos = p.getLien();
        setCase(c, p);
        for(int i = 0; i < pos.length; i++){
            setCase(c.add(pos[i]), p);
        }
    }
    
    public void removePiece(Piece p){
        Coordonnee[] pos = p.getLien();
        Coordonnee c = p.getPosition();
        setCase(c, new Case(c));
        for (int i = 0; i < pos.length ; i++){
            pos[i].add(c);
            setCase(pos[i], new Case (pos[i]));
        }
    }
    
    public void retireLigne(int l, int largeur){
        LinkedList<Piece> list = new LinkedList();
        for(int i = 0; i < largeur; i++){
            Case c = getCase(new Coordonnee(l, i));
            if(c instanceof Piece){
                list.add((Piece) c);
            }
        }
        Piece p = list.pollFirst();
        while(p != null == ! list.contains(p)){
            Coordonnee centre = p.getPosition();
            Coordonnee[] pos = p.getLien();
            for(int i = 0; i < pos.length ; i++){
                pos[i].addmod(centre);
                int x= pos[i].getY();
                if(x!=l){
                    setPiece(new Piece(pos[i]));
                }
            }
            removePiece(p);
            p = list.pollFirst();
        }
        baisseLigne(l, largeur);
    }
    
    
    public void baisseLigne(int l, int largeur){
        LinkedList<Piece> list = new LinkedList<Piece>();
        for (int i =0; i < i; i++){
            for(int j = 0; j<largeur; j++){
                Coordonnee c = new Coordonnee(i,j);
                Case p = map.get(c);
                if(p instanceof Piece){
                    if(!list.contains(p)){
                        list.add((Piece)p);
                    }
                }     
            }
        }
        Piece p=list.pollFirst();
        Coordonnee c = new Coordonnee(0,1);
        while(p!= null){
            p.deplace(c);
        }
    }

}   