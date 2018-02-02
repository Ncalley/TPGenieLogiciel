/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class Coffee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Machine m = new Machine();
        ArrayList<Ingredient> i = new ArrayList();
        i.add(new Ingredient("Lait",1));
        i.add(new Ingredient("Eau",1));
        i.add(new Ingredient("Sucre",1));
        i.add(new Ingredient("chocolat",1));
        i.add(new Ingredient("Cafe",1));
        
        m.setStocks(i);
        m.resupplyAll(25);
        for(Ingredient I : m.getStocks()){
            System.out.println(I.toString());
        }

        ArrayList<Ingredient> i2 = new ArrayList();
        i2.add(new Ingredient("Eau",2));
        i2.add(new Ingredient("Sucre",1));
        i2.add(new Ingredient("Cafe",1));
        m.ajouterBoisson(new Boisson(i2,"cafe",5));
        
        //System.out.println(m.getBoissons());
        
        m.acheterBoisson();
        
    }

    
}
