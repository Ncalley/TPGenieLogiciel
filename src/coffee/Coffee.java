/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author Nicolas
 */
public class Coffee {
	
    //Scanner sc = new Scanner(System.in);


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Machine m = new Machine(5);
        ArrayList<Ingredient> i = new ArrayList();
        i.add(new Ingredient("Lait",1));
        i.add(new Ingredient("Eau",1));
        i.add(new Ingredient("Sucre",1));
        i.add(new Ingredient("chocolat",1));
        i.add(new Ingredient("Cafe",1));
        
        m.setStocks(i);
        //m.resupplyAll(25);

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
        boolean fini = false;
            
        JOptionPane.showMessageDialog(null,"Machine à café prête !");
        JOptionPane.showMessageDialog(null,"Bienvenue dans notre super machine à café!");
        while (fini == false){
        String choix = (JOptionPane.showInputDialog(null,"Que voulez vous faire ? \n "
                    + "- (1) Acheter une boisson\n"
                    + "- (2) Ajouter une boisson\n"
                    + "- (3) Modifier une boisson\n "
                    + "- (4) Supprimer une boisson\n"
                    + "- (5) Ajouter un Ingrédient\n"
                    + "- (6) Vérifier le stock\n "
                    + "- (7) Augmenter la quantité d'un ingrédient\n"
                    + "- (8) Augmenter la quantité de tout le stock\n"
                    + "- (9) Quitter\n "));
        
        try{   
            switch(choix) {
                case "1" :
                    if(m.getBoissons()==null || m.getBoissons().equals("")){
                        JOptionPane.showMessageDialog(null,"Pas de boissons enregistrées pour l'instant, veuillez enregistrer une boisson"  );
                    }
                    else{
                        m.acheterBoisson();
                    }
                    break;
                case "2" :
                    if(m.getStocks()==null){
                        JOptionPane.showMessageDialog(null,"Pas d'ingrédients enregistrées pour l'instant, veuillez enregistrer un ingrédient"  );
                    }else{
                        m.ajouterBoisson(m.creerBoisson());
                    }
                    break;
                case "3" : 
                    m.modifierBoisson();
                    break;
                case "4" : 
                    JOptionPane.showMessageDialog(null,"Fonctionnalité en développement, revenez un peu plus tard ;)"  );
                    //m.supprimerBoisson();
                    break;
                case "5" :
                    m.addIngredient(m.createIngredient());
                    break;
                case "6" : 
                    JOptionPane.showMessageDialog(null,"Voici les ingrédients contenus dans la machine :\n"
                            + m.getStocks());
                    break;
                case "7" : 
                    m.resupply();
                    //resetStock(m);
                    break;
                case "8" :
                    int q=0;
                    try{
                        q = Integer.parseInt(JOptionPane.showInputDialog(null,"Combien d'unité voulez-vous ajouter à chaque ingrédient du stock?"));
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Quantité invalide");
                    }
                    if(q<0){
                        JOptionPane.showMessageDialog(null,"Quantité invalide");
                    }else{
                        m.resupplyAll(q);
                    }
                case "9" :
                    JOptionPane.showMessageDialog(null,"Merci d'avoir utilisé notre machine à café, à la prochaine fois!"  );
                    fini = true;
                    break;
            }
        }catch(Exception e){
            System.out.println("Application fermée");
            fini=true;
        }   
    }
 }
 
 	

    
}
