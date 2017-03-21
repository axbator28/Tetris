/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package src.blokus;

import com.sun.javafx.collections.MappingChange.Map;
import java.util.HashMap;
*/
/**
 *
 * @author Héléna
 */
/*public class Carte {
    HashMap<Integer, Case> map;
    
    
    
    public Case getCase(Integer k1, Integer k2){
        Case c = null;
        if(map.contains(k1)){
            Map<Integer, Case> m=map.get(k1);
            if(m.contains(k2)){
                c=m.get(k2);
            }
            else{
                System.out.println("Erreur : la colonne sur la ligne considérée n'existe pas");
            }
        }
        else{
            System.out.println("Erreur : la ligne n'existe pas");
        }
        return c;
    }
    
    public void setCase(Integer k1, Integer k2, Case c){
        if(map.containsKey(k1)){
            if(map.contains(k2)){
                Map<Integer, Case> m=map.get(k1);
                m.remove(k2);
                m.put(k2, c);
            }
            else{
                System.out.println("Erreur : la colonne n'existe pas sur cette ligne");
            }
        }
        else{System.out.println("Erreur : la ligne n'existe pas");}
    }
    
}
*/