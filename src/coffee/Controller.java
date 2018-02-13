/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe dee contrôle des entrées-sorties du programme.
 * Sert à faire différentes vérifications
 * @author Nicolas
 */
public class Controller {
    
    /**
     * Vérification de la string entrée a une valeur non nulle
     * @param s
     * @return 
     */
    public boolean control(String s){
        if(s==null || s.equals("") || s.equals("B") || s.equals("Max")){
            return false;}
        
        return true;
    }
    
    /**
     * Méthode de contrôle d'ajout des ingrédients sur une boisson
     * on retourne la liste des ingrédients (non vide)
     * @param b
     * @param stocks
     * @return 
     */
    public ArrayList<Ingredient> control(ArrayList<Boisson> b, ArrayList<Ingredient> stocks){
        ArrayList<Ingredient> ib = new ArrayList();
   	boolean done = false;
        int q = 0;
        while(!done){
            for (Ingredient i : stocks) {
                    
                //pour chaque ingrédient, s'il est valide on l'ajoute
                q = this.control("Combien d'unité de "+ i.getNom() +" voulez-vous ajouter ?", "Quantité invalide", true);
                if(q!=0){ ib.add(new Ingredient(i.getNom(), q));}
                    
            }
            //vérifier que la liste des ingrédients n'est pas nulle
            if(!ib.isEmpty()){
                done=true;
            }else{
                JOptionPane.showMessageDialog(null,"Vous ne pouvez pas créer de boisson sans ingrédient.\nVeuillez réessayer.");
            }
        }
   	
        
        return ib;
    }
    
    /**
     * Controle si une liste de boisson est nulle
     * @param b
     * @return 
     */
    public boolean control(ArrayList<Boisson> b){
        if(b.isEmpty()){return false;}
        return true;
    }
    
    /**
     * Contrôle d'une entrée d'entier au clavier
     * on peut définir si le cas nul est valide (nombres négatifs invalides)
     * @param question
     * @param wrong
     * @param isNullValid
     * @return 
     */
    public int control(String question, String wrong, boolean isNullValid){
        int price = 0;
        boolean juste = false;
        while(!juste){
            juste = true;
            try{
                price = Integer.parseInt(JOptionPane.showInputDialog(null,question));
            }catch (Exception e){
                juste= false;
            }
            if(!isNullValid){
                if(price<=0){
                    juste=false;
                }
            }
            if(!juste){JOptionPane.showMessageDialog(null,wrong);}
        }
        return price;
    }
    
    /**
     * Appelle la fonction ci dessus avec par défaut le cas nul invalide
     * @param question
     * @param wrong
     * @return 
     */
    public int control(String question, String wrong){
        return this.control(question, wrong, false);
    }
    
    /**
     * Contrôle des ingrédients
     *  - contrôle du nom
     *  - contrôle du fait qu'il soit ou non contenu dans la liste d'ingrédients
     * @param question
     * @param wrong
     * @param array
     * @param isContainedIn
     * @return 
     */
    public String controlIng(String question, String wrong, ArrayList array, boolean isContainedIn){
        String name = "";
        boolean fini = false;
        while(!fini){
            name = this.controlStr(question,wrong);
            
            if(this.controlNamedIng(name, wrong, array, isContainedIn)){
                fini = true;
            }
        }
        return name;
    }
    
    /**
     * Surcharge de la méthode ci dessus ave l'option par défaut contenu dans la liste
     * @param question
     * @param wrong
     * @param array
     * @return 
     */
    public String controlIng(String question, String wrong, ArrayList array){
        return this.controlIng(question, wrong, array, true);
    }
    
    /**
     * Contrôle du fait que les ingrédients soient contenus ou non dans la liste
     * @param name
     * @param wrong
     * @param array
     * @param isContainedIn
     * @return 
     */
    public boolean controlNamedIng(String name, String wrong,ArrayList array, boolean isContainedIn){
        if(!array.contains(new Ingredient(name,0))){
            if(isContainedIn){
                JOptionPane.showMessageDialog(null,wrong);
                return false;
            } 
        }else{
            if(!isContainedIn){
                JOptionPane.showMessageDialog(null,wrong);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Contrôle des boissons
     *  - contrôle du nom
     *  - contrôle du fait qu'il soit ou non contenu dans la liste d'ingrédients
     * @param question
     * @param wrong
     * @param array
     * @param isContainedIn
     * @return 
     */
    public String controlBoiss(String question, String wrong, ArrayList array, boolean isContainedIn){
        String name = "";
        boolean fini = false;
        while(!fini){
            name = this.controlStr(question,wrong);
            
            if(this.controlNamedBoiss(name, wrong, array, isContainedIn)){
                fini = true;
            }
            
        }
        return name;
    }
    
    /**
     * Surcharge de la méthode précédente avec l'option par défaut true
     * @param question
     * @param wrong
     * @param array
     * @return 
     */
    public String controlBoiss(String question, String wrong, ArrayList array){
        return this.controlBoiss(question, wrong, array, true);
    }
    
    /**
     * Contrôle du fait que les boissons soient contenus ou non dans la liste
     * @param name
     * @param wrong
     * @param array
     * @param isContainedIn
     * @return 
     */
    public boolean controlNamedBoiss(String name, String wrong,ArrayList array, boolean isContainedIn){
        if(!array.contains(new Boisson(name,0))){
            if(isContainedIn){
                JOptionPane.showMessageDialog(null,wrong);
                return false;
            } 
        }else{
            if(!isContainedIn){
                JOptionPane.showMessageDialog(null,wrong);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Contrôle de l'entrée d'une chaine de caractère
     * @param question
     * @param wrong
     * @return 
     */
    public String controlStr(String question, String wrong){
        String name = "";
        boolean fini = false;
        while(!fini){
            name = JOptionPane.showInputDialog(null,question);
            
            if(!this.control(name)){
                JOptionPane.showMessageDialog(null,wrong);
            }else{
                fini = true;
            }
            
        }
        return name;
    }
}
