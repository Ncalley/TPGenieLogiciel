/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Nicolas
 */
public class Coffee {
	
    Scanner sc = new Scanner(System.in);


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

        ArrayList<Ingredient> i2 = new ArrayList();
        i2.add(new Ingredient("Eau",2));
        i2.add(new Ingredient("Sucre",1));
        i2.add(new Ingredient("Cafe",1));
        m.ajouterBoisson(new Boisson(i2,"cafe",2));
        
        //System.out.println(m.getBoissons());
        
        startMenu(m);
//        m.acheterBoisson();
        
    }
    
 public static void startMenu(Machine m) {
	 
	    Scanner sc = new Scanner(System.in);

    	
    	System.out.println("**********************************************");        
        System.out.println("* Bienvenue sur la machine a café            *");
        System.out.println("* Que voulez-vous faire ?                    *");
        System.out.println("* 1 : Acheter une boisson                    *");        
        System.out.println("* 2 : Ajouter une boisson                    *");
        System.out.println("* 3 : Modifier une boisson                   *");
        System.out.println("* 4 : Supprimer une boisson                  *"); 
        System.out.println("* 5 : Ajouter un ingrédient                  *");       
        System.out.println("* 6 : Vérifier le stock                      *");
        System.out.println("* 7 : Réinitialiser le stock d'un ingrédient *");
        System.out.println("* 8 : Réinitialiser tout le stock            *");
        System.out.println("**********************************************");   
        
        int choiceUser = sc.nextInt();
        
        switch(choiceUser) {
        case 1 : 
        	acheterBoisson(m);
        	break;
        case 2 :
        	ajouterBoisson(m);
        	break;
//        case 3 : 
//        	m.modifierBoisson();
//        	break;
//        case 4 : 
//        	m.supprimerBoisson();
//        	break;
//        case 5 :
//        	m.ajouterIngredient();
//        	break;
        case 6 : 
        	verifierStock(m);
        	break;
        case 7 : 
        	resetStock(m);
        	break;
        }
    }
 
 
 private static void resetStock(Machine m) {
	 System.out.println(" ");

	
}

public static void acheterBoisson(Machine m) {
	 m.acheterBoisson();
	 pause(3000);
	 startMenu(m);
 }
 
 public static void ajouterBoisson(Machine m) {
	 Boisson b = m.creerBoisson();
	 m.ajouterBoisson(b);
	 pause(2000);
	 startMenu(m);
 }
 
public static void verifierStock(Machine m) {
	 System.out.println("Stock des ingrédients");
     for(Ingredient I : m.getStocks()){
         System.out.println(I.toString());
     }
     
     pause(3000);
     startMenu(m);

 }


 public static void pause(int time) {
	 
	 try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
 
 	

    
}
