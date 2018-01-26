package coffee;

class Ingredient  {

	private String nom;
	private int quantite;

    
    public Ingredient(String nom, int quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }
    
    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

	
}