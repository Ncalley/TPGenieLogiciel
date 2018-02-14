/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;


import javax.swing.JOptionPane;


/**
 * Programme principal de la machine à café
 * @author Nicolas
 */
public class Coffee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Machine m = new Machine(5);
        startMenu(m);
        
    }
 
    /**
     * Méthode de démarrage de la machine, consiste en un grand menu reprédentant toutes les options de la machine. 
     * @param m la machine
     */
 public static void startMenu(Machine m) {
        boolean fini = false;

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
                    m.supprimerBoisson();
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
                    m.resupplyAll(m.getController().control("Combien d'unité voulez-vous ajouter à chaque ingrédient du stock?", "Quantité invalide"));
                    break;
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
