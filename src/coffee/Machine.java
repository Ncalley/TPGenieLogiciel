package coffee;

import java.util.ArrayList;
import javax.swing.JOptionPane;

class Machine {

	private ArrayList<Boisson> boissons = new ArrayList();
	private ArrayList<Ingredient> stocks = new ArrayList();
        private int maxBoisson = 5;
        private final Controller c = new Controller();
        private final FileManager f = new FileManager();



    public Machine(final ArrayList<Boisson> boissons, final ArrayList<Ingredient> stocks, final int maxBoisson) {
        this.boissons = boissons;
        this.stocks = stocks;
        this.maxBoisson = maxBoisson;
    }    
        
    public Machine(final ArrayList<Ingredient> stocks, final int maxBoisson) {
        ArrayList data[] = f.loadLocal();
        this.boissons = data[0];
        this.stocks = stocks;
        this.maxBoisson = maxBoisson;
    }

    public Machine(final int maxBoisson) {
        ArrayList data[] = f.loadLocal();
        this.boissons = data[0];
        this.stocks = data[1];
        this.maxBoisson = maxBoisson;
    }
    
    public Machine() {
        ArrayList data[] = f.loadLocal();
        this.boissons = data[0];
        this.stocks = data[1];
        this.maxBoisson = (int)data[2].get(0);
    }

    /**
     * Fonction d'achat de boisson
     * Permet à l'utilisateur de choisir sa boisson et de commander
     */
    public boolean acheterBoisson() {
        
        boolean found = false;
        while(!found){
            String choix = c.controlStr("Quelle boisson voulez-vous ? \n "
                    + this.getBoissons()
                    + "Quitter", "Nom invalide");
            Boisson b = new Boisson(choix);
            if("Quitter".equals(choix)){return true;}


            for (Boisson boisson : boissons ) {
                if (b.equals(boisson)) {
                    try{

                        this.acheter(boisson);
                        found = true;
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage()+"\n"
                                + "Votre boisson n'est pas disponible, veuillez réessayer"  );
                        found = true;
                    }
                }
            }
            if(found == false){
                JOptionPane.showMessageDialog(null,"Votre choix n'existe pas, veuillez reessayer\n");
            }
        }
        return true;
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
        store();
        JOptionPane.showMessageDialog(null,"Voici votre boisson : "+ b.getNom() + " vous devez payer : "+ b.getPrix() + " €");
    }
    
    /**
     * Permet d'ajouter la boisson b à la machine
     * Si le nombre de boissons maximum de la machine est déjà atteint le processu échoue et signale l'utilisateur
     * @param b 
     */
    public void ajouterBoisson(Boisson b){
        if(boissons.size() <= maxBoisson){
            boissons.add(b);
            store();
        } else{
            JOptionPane.showMessageDialog(null,"Nombre maximum de boissons disponibles dans la machine atteint.\nAjout échoué.");
        }
    }
    
    /**
     * Permet de créer une boisson
     * On contrôle les entrées-sorties
     * @return 
     */
    public Boisson creerBoisson() {
        
        String name = c.controlBoiss("Veuillez choisir un nom de boisson", "Le nom entré est invalide ou la boisson existe déjà",boissons, false);
        
   	int price = c.control("Quel sera le prix de cette boisson?", "Prix invalide, veuillez réessayer"); 
   	
   	Boisson b = new Boisson(c.control(boissons, stocks), name, price);
   	 
   	JOptionPane.showMessageDialog(null,"La boisson "+b.getNom()+ " a été créée");
   	
    	return b;
    }
    
    /**
     * Permet d'ajouter un nombre défini d'ingrédient donné à la machine
     */
    public void resupply(){
        String name = c.controlIng("Voici les ingrédients contenus dans la machine :\n"
                                + this.getStocks()
                                + "Quel ingrédient voulez-vous ajouter ?","Nom invalide",stocks);
        int q = c.control("Combien d'unité de "+name+" voulez-vous ajouter ?", "Quantité invalide", true);
        stocks.get(stocks.indexOf(new Ingredient(name,q))).resupply(q);
        store();
        JOptionPane.showMessageDialog(null,q+ " unité(s) de l'ingredient "+name+ " a(ont) été ajoutée(s)");
    } 
    
    /**
     * Permet d'ajouter une quantite définie à tous les ingrédients
     * @param quantite 
     */
    public void resupplyAll(int quantite){
        for(Ingredient i : stocks){
            stocks.get(stocks.indexOf(i)).resupply(quantite);
        }
        store();
        JOptionPane.showMessageDialog(null,quantite+ " unité(s) de chaque ingrédient ont été ajoutées");
    }
    
    /**
     * Permet d'ajouter un nouvel ingrédient dans les stocks
     * @param i 
     */
    public void addIngredient(Ingredient i){
       stocks.add(i);
       store();
    }
    
    /**
     * Permet à l'utilisateur de créer un ingrédient
     * les entrées-sorties sont contrôlées
     * @return 
     */
    public Ingredient createIngredient(){
        boolean valid = false;
        String name = "";
        while(!valid){
            name = c.controlStr("Veuillez choisir un nom d'ingredient", "Nom d'ingrédient invalide");
            if(c.controlNamedIng(name, "Ingrédient déjà dans le stock", stocks, false)){valid=true;}
        }
        int q = c.control("Combien d'unité de cet ingrédient voulez vous mettre dans le stock?", "Quantité invalide", true);
   	Ingredient i = new Ingredient(name,q);
   	if(q==0){
            JOptionPane.showMessageDialog(null,"Ingredient "+i.getNom()+ " créé");
        }else{
            JOptionPane.showMessageDialog(null,q+ " unité(s) de l'ingredient "+i.getNom()+ " a(ont) été ajouté(s)");
        }
    	return i;
    }
    
    
    /**
     * Permet à l'utilisateur de modifier une boisson
     * les entrées-sorties sont contrôlées
     * @return 
     */
    public boolean modifierBoisson(){
        String name = selectBoisson();
        if("Quitter".equals(name)){return false;}
        
        int price = c.control("Indiquez le nouveau prix de cette boisson", "Prix invalide");
        
   	ArrayList<Ingredient> ib = new ArrayList();
   	
        Boisson b = new Boisson(c.control(boissons, stocks), name, price);
        this.boissons.remove(b);
   	this.ajouterBoisson(b);
        store(); 
   	JOptionPane.showMessageDialog(null,"La boisson "+b.getNom()+ " a été modifiée");
   	return true;
        
    }
    
    public boolean supprimerBoisson(){
        String name = selectBoisson();
        if("Quitter".equals(name)){return false;}
        
        this.boissons.remove(new Boisson(name));
        store();
        JOptionPane.showMessageDialog(null,"La boisson "+name+ " a été supprimée");
        return true;
    }
    
    private String selectBoisson(){
        boolean fini = false;
        String name ="";
        while(fini == false){
            name = c.controlStr("Voici les boissons contenues dans la machine :\n"
                                + this.getBoissons()
                                + "Aucune : Quitter\n"
                                + "Quelle boisson voulez-vous modifier ?", "Nom de boisson invalide");
            
            if(!"Quitter".equals(name)){
                if(c.controlNamedBoiss(name, "Cette boisson n'est pas dans la machine", boissons, true)){
                    fini = true;
                }
            }else{fini=true;}
        }
        return name;
    }
    
    private void store(){
        try{
            f.storeLocal(boissons, stocks, maxBoisson);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    //Getters et Setters
    
    /**
     * renvoie une chaine contenant les noms des boissons de la machine
     * @return 
     */
    public String getBoissons() {
        StringBuffer res= new StringBuffer(100);
        for (Boisson b :boissons){
            res.append(b.toString()+"\n");
        }
        return res.toString();
    }

    /**
     * renvoie une chaine contenant les noms des ingrédients de la machine
     * @return 
     */
    public String getStocks() {
        StringBuffer res= new StringBuffer(100);
        for (Ingredient I :stocks){
            res.append(I.getNom()+ " Quantité: " + I.getQuantite() +"\n");
        }
        return res.toString();
    }

    /**
     * Permet de réinitialiser les stocks à partir d'une liste d'ingrédiants donnés
     * @param stocks 
     */
    public void setStocks(final ArrayList<Ingredient> stocks) {
        this.stocks = stocks;
    }
    
    /**
     * Retourne le contrôleur de la classe
     * @return 
     */
    public Controller getController() {
        return c;
    }
    
    
    //TODO :
    // Possibilité de modifier le niveau de sucre par boisson
}