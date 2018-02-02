package coffee;

import java.util.ArrayList;
import java.util.Scanner;

class Machine {

    Scanner sc = new Scanner(System.in);
	private ArrayList<Boisson> boissons = new ArrayList();
	private ArrayList<Ingredient> stocks = new ArrayList();
        private int maxBoisson = 5;



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
        boolean found = false;
        for (Boisson boisson : boissons ) {
                System.out.println(boisson);
            if (b.equals(boisson)) {
                try{
                    System.out.println(boisson.toString());
                    this.acheter(boisson);
                    found = true;
                }catch(Exception e){
                    System.out.println(e.toString());
                    System.out.println("Votre boisson n'est pas disponible, veuillez reessayer\n");
//                    acheterBoisson();
                }
            }
        }
        if(found == false && !choiceUserBoisson.equals("Q")){
            System.out.print("Votre choix n'existe pas, veuillez reessayer\n");
        }
        if (!choiceUserBoisson.equals("Q")){
//            acheterBoisson();
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
    
    public Boisson creerBoisson() {
//    	Scanner sc = new Scanner(System.in);

   	 System.out.println("Veuillez choisir un nom de boisson");
   	 String name = sc.nextLine();
   	 
   	 System.out.println("Veuillez choisir le prix de boisson");
   	 int price = sc.nextInt();
   	 
   	ArrayList<Ingredient> ib = new ArrayList();
   	
   	for (Ingredient i : stocks) {
   		System.out.println("Combien d'unité de " +i.getNom()+ " voulez-vous mettre dans votre "+name+ " ?");
   		int q = sc.nextInt();
   		ib.add(new Ingredient(i.getNom(), q));
   	}
   	 
   	 Boisson b = new Boisson(ib, name, price);
   	 
   	 System.out.println("La boisson "+b.getNom()+ " a été ajoutée");
   	
    	return b;
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
    
    //TODO :
    // Possibilité de modifier le niveau de sucre par boisson
    // Persistance des ingrédients dans un fichier ou une bdd
    
}