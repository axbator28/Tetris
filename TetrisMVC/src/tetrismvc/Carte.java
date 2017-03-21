/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrismvc;

public class Carte {
    HashMap<Coordonnee, Case> map;


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
}
