package coffee;

import java.util.ArrayList;
import java.util.Scanner;

class Machine {

    Scanner sc = new Scanner(System.in);
	private ArrayList<Boisson> boissons = new ArrayList();
	private ArrayList<Ingredient> stocks = new ArrayList();
        private int maxBoisson = 3;



    public Machine(final ArrayList<Ingredient> stocks, final int maxBoisson) {
        this.stocks = stocks;
        this.maxBoisson = maxBoisson;
    }

    public Machine() {
    }

    /**
     * Fonction d'achat de boisson
     * Permet à l'utilisateu de choisir sa boisson et de commander
     */
    public void acheterBoisson() {
        System.out.println("Choisisez votre Boisson : (Q = quitter)");
        System.out.println(this.getBoissons());
        String choiceUserBoisson = sc.nextLine();
        Boisson b = new Boisson(choiceUserBoisson);
        System.out.println(b.toString());
        boolean found = false;
        for (Boisson boisson : boissons ) {
                
            if (b.equals(boisson)) {
                try{
                    this.acheter(boisson);
                    found = true;
                }catch(Exception e){
                    System.out.println(e.toString());
                    System.out.println("Votre boisson n'est pas disponible, veuillez reessayer\n");
                    acheterBoisson();
                }
            }
        }
        if(found == false && !choiceUserBoisson.equals("Q")){
            System.out.print("Votre choix n'existe pas, veuillez reessayer\n");
        }
        if (!choiceUserBoisson.equals("Q")){
            acheterBoisson();
        }
    }
    
    /**
     * Vérifie qu'on peut retirer le montant de chaqu ingrédient de la boisson du stock (si les ingrédients sont disponibles),
     * si ce n'est pas possible on lance une exception.
     * Complexité relativement haute, à optimiser si on utilise beaucoup d'ingrédients.
     * @param b
     * @throws Exception 
     */
    private void acheter(Boisson b) throws Exception{
        //vérification
        for (Ingredient I : stocks){
            for (Ingredient Ib : b.getIngredients()){
                if(I.equals(Ib) && I.getQuantite()<Ib.getQuantite()){
                    throw new Exception("Quantité de "+ I.getNom() + " insuffisante.");
                }
            }
        }
        //retrait des stocks
        for (Ingredient I : stocks){
            for (Ingredient Ib : b.getIngredients()){
                if(I.equals(Ib)){
                    I.decrease(Ib.getQuantite());
                }
            }
        }
        System.out.println("Voici votre boisson : "+ b.getNom() + " vous devez payer : "+ b.getPrix() + " €");
    }
    
    public void ajouterBoisson(Boisson b){
        if(boissons.size() <= maxBoisson){
            boissons.add(b);
        } else{
            System.out.println("Nombre maximum de boissons disponibles dans la machine atteint.");
        }
    }
    
    public void resupply(Ingredient ingredient, int quantite){
        for(Ingredient i : stocks){
            if(i.equals(ingredient)){
                i.resupply(quantite);
            }
        }
    } 
    
    public void resupplyAll(int quantite){
        for(Ingredient i : stocks){
            i.resupply(quantite);
        }
    }
    
    //renvoie une chaine contenant les noms des boissons de la machine
    public String getBoissons() {
        StringBuffer res= new StringBuffer(100);
        for (Boisson b :boissons){
            res.append(b.getNom()+"\n");
        }
        return res.toString();
    }

    public ArrayList<Ingredient> getStocks() {
        return stocks;
    }

    public void setStocks(final ArrayList<Ingredient> stocks) {
        this.stocks = stocks;
    }
    
}