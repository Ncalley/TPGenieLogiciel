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

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer(13+25*10+35);
        str.append(nom);
        str.append("{Ingredients=");
        for(Ingredient i : ingredients){
            str.append(i.toString());
        }
        str.append(new String(", prix=" + prix + '}'));
        return  str.toString();
    }

    
    /**
     * Retourne le nom de la fonction
     * @return 
     */
    public String getNom() {
        return nom;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getPrix() {
        return prix;
    }

    
}