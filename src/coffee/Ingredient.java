package coffee;

import java.util.Objects;

/**
 * Classe représentant un ingrédient
 * @author Nicolas
 */
class Ingredient  {

	private String nom;
	private int quantite;
        private final boolean isLiquide;

    //constructeurs
    public Ingredient(final String nom, final int quantite, final boolean isLiquide) {
        this.nom = nom;
        this.quantite = quantite;
        this.isLiquide = isLiquide;
    }    
        
    public Ingredient(final String nom, final int quantite) {
        this.nom = nom;
        this.quantite = quantite;
        this.isLiquide = false;
    }

    //méthodes
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    /**
     * Deux ingrédients du même nom sont les mêmes
     * @param obj
     * @return 
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
        return nom + " : " + quantite + " Unites ";
    }
    
    public String getData(){
        StringBuffer str = new StringBuffer(13+25*10+35);
        str.append(nom);
        str.append(",");
        str.append(quantite);
        return  str.toString();
    }
    /**
     * Enlève i quantité d'ingrédient à cet ingrédient
     * @param i 
     */
    public void decrease(int i) throws Exception {
        if(i >= this.quantite){
            throw new Exception("Quantité insuffisante: " + this.nom);
        }
        this.quantite -= i ;
    }
    
    /**
     * Ajoute i quantité d'ingrédient à cet ingrédient
     * @param i 
     */
    public void resupply(int i){
        this.quantite += i ;
    }
    
    //getters / setters
    
    /**
     * Retourne le nom de cet ingrédient
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la quantité de cet ingrédient
     * @return 
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Retourne vrai si l'ingrédient est liquide et faux sinon
     * @return 
     */
    public boolean isLiquide(){
        return isLiquide;
    }
}