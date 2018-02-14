package coffee;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Classe d'une boisson
 * @author Nicolas
 */
class Boisson {

    private String nom;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private int prix;

    //constructeurs
    public Boisson(final ArrayList<Ingredient> ingredients, final String nom, final int prix) {

	this.ingredients = ingredients;
	this.nom = nom;
	this.prix = prix;
    }

    public Boisson(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }
    
    public Boisson(String nom) {
        this.nom = nom;
    }
    
    //méthodes

    /**
     * Retourn vrai si la boisson contient au moins un élément liquide, faux sinon
     * @return 
     */
    public boolean containsLiquide(){
        for(Ingredient elt: ingredients){
            if(elt.isLiquide()){
                return true;
            }
        }
        return false;
    }
    /**
     * Redéfinition du Hashcode correspondant au equals
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nom);
        return hash;
    }
    
    /**
     * Deux boissons sont les mêmes si elles ont le même nom
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boisson other = (Boisson) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    /**
     * Permet d'afficher la boisson, son contenu et son prix
     * @return 
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer(13+25*10+35);
        str.append(nom);
        str.append("{Ingredients=");
        for(Ingredient i : ingredients){
            str.append(i.toString());
        }
        str.append(new String(", prix=" + this.prix + "€ "+ '}'));
        return  str.toString();
    }

    /**
     * Renvoie le contenu de la boisson dans un format compréhensible par le FileManager
     * @return 
     */
    public String getData(){
        StringBuffer str = new StringBuffer(13+25*10+35);
        str.append("B;");
        str.append(nom);
        for(Ingredient i : ingredients){
            str.append(";");
            str.append(i.getData());
        }
        str.append(";");
        str.append(this.prix);
        return  str.toString();
    }
    
    /**
     * Retourne le nom de la boisson
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la liste contenant les ingrédients de la boisson 
     * @return 
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Retourne la prix de la boisson
     * @return 
     */
    public int getPrix() {
        return prix;
    }

    
}