package coffee;

import java.util.ArrayList;
import java.util.Scanner;

class Machine {

	private Boisson[] boissons = new Boisson[3];
	private ArrayList<Ingredient> stocks;

    public Machine(ArrayList<Ingredient> stocks) {
        this.stocks = stocks;
    }

    public Machine() {
    }

    public String acheter(String nom){
        return null;
    }
    
    public void ajouterBoisson(Boisson b){
    }
    
    public Boisson[] getBoissons() {
        return boissons;
    }

    public void setBoissons(Boisson[] boissons) {
        this.boissons = boissons;
    }

    public ArrayList<Ingredient> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Ingredient> stocks) {
        this.stocks = stocks;
    }
}