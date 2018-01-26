package coffee;

import java.util.ArrayList;

class Boisson {

    private String nom;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private int prix;

    public Boisson(ArrayList<Ingredient> ingredients, String nom, int prix) {

	this.ingredients = ingredients;
	this.nom = nom;
	this.prix = prix;
    }

    public Boisson(String nom, int prix) {
        this.nom = nom;
        this.prix = prix;
    }

	
}