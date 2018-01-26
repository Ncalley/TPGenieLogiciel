package coffee;

import java.util.Objects;

/**
 * Classe représentant un ingrédient
 * @author Nicolas
 */
class Ingredient  {

	private String nom;
	private int quantite;

    
    public Ingredient(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.nom);
        return hash;
    }

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
        final Ingredient other = (Ingredient) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
    /**
     * Renvoie les informations des ingrédients
     * @return 
     */
    @Override
    public String toString() {
        return nom + " : " + quantite + " Unites";
    }
    
    
    
    
    //getters / setters
    
    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    
}