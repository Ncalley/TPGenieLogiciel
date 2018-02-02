package coffee;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

class Machine {

    Scanner sc = new Scanner(System.in);
	private ArrayList<Boisson> boissons = new ArrayList();
	private ArrayList<Ingredient> stocks = new ArrayList();
        private int maxBoisson = 5;



    public Machine(final ArrayList<Ingredient> stocks, final int maxBoisson) {
        this.stocks = stocks;
        this.maxBoisson = maxBoisson;
    }

    public Machine(final int maxBoisson) {
        this.maxBoisson = maxBoisson;
    }
    
    public Machine() {
    }

    /**
     * Fonction d'achat de boisson
     * Permet à l'utilisateur de choisir sa boisson et de commander
     */
    public void acheterBoisson() {
        System.out.println(this.getBoissons());
        String choix = (JOptionPane.showInputDialog(null,"Quelle boisson voulez-vous ? \n "
                + this.getBoissons()
                + "Quitter"));
        Boisson b = new Boisson(choix);
        boolean found = false;
        for (Boisson boisson : boissons ) {
            if (b.equals(boisson)) {
                try{
                    //System.out.println(boisson.toString());
                    this.acheter(boisson);
                    found = true;
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,e.toString()+"\n"
                            + "Votre boisson n'est pas disponible, veuillez réessayer"  );
//                    acheterBoisson();
                }
            }
        }
        if(found == false && !choix.equals("Quitter")){
            JOptionPane.showMessageDialog(null,"Votre choix n'existe pas, veuillez reessayer\n");
        }
        if (!choix.equals("Quitter")){
//            acheterBoisson();
               found=true;
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
        JOptionPane.showMessageDialog(null,"Voici votre boisson : "+ b.getNom() + " vous devez payer : "+ b.getPrix() + " €");
    }
    
    public void ajouterBoisson(Boisson b){
        if(boissons.size() <= maxBoisson){
            boissons.add(b);
        } else{
            JOptionPane.showMessageDialog(null,"Nombre maximum de boissons disponibles dans la machine atteint.");
        }
    }
    
    public Boisson creerBoisson() {
//    	Scanner sc = new Scanner(System.in);
        boolean fini = false;
        String name ="";
        while(fini == false){
            name = (JOptionPane.showInputDialog(null,"Veuillez choisir un nom de boisson"));
            
            if(boissons.contains(new Boisson(name))|| name.equals("Quitter")){
                JOptionPane.showMessageDialog(null,"Cette boisson existe déjà ou son nom est invalide");
            }else{
                fini = true;
            }
        }
   	int price=0;
        try{
            price = Integer.parseInt(JOptionPane.showInputDialog(null,"Quel sera le prix de cette boisson?"));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Prix invalide");
        }
   	 
   	 
   	ArrayList<Ingredient> ib = new ArrayList();
   	
        
   	for (Ingredient i : stocks) {
                int q=0;
                try{
                    q = Integer.parseInt(JOptionPane.showInputDialog(null,"Combien d'unité de "+ i.getNom() +" voulez-vous ajouter ?"));
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Quantité invalide");
                }
                if(q>0){
                    ib.add(new Ingredient(i.getNom(), q));
                }
   	}
   	 
   	 Boisson b = new Boisson(ib, name, price);
   	 
   	 JOptionPane.showMessageDialog(null,"La boisson "+b.getNom()+ " a été ajoutée");
   	
    	return b;
    }
    
    
    public void resupply(){
        boolean fini = false; //lol
        String name="";
        int q =0;
        while(!fini){
            name = (JOptionPane.showInputDialog(null,"Voici les ingrédients contenus dans la machine :\n"
                                + this.getStocks()
                                + "Quel ingrédient voulez-vous retirer ?"));
            
            try{
                q = Integer.parseInt(JOptionPane.showInputDialog(null,"Combien d'unité voulez-vous ajouter à chaque ingrédient du stock?"));
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Quantité invalide");
            }
            if(!stocks.contains(new Ingredient(name,q))){
                JOptionPane.showMessageDialog(null,"Nom invalide");
            }else{
                fini = true;
            }
            if(q<0){
                JOptionPane.showMessageDialog(null,"Quantité invalide");
                fini = false;
            }
            else{
                fini = true;
            }
        }
        stocks.get(stocks.indexOf(new Ingredient(name,q))).resupply(q);
        JOptionPane.showMessageDialog(null,q+ " unité(s) de l'ingredient "+name+ " a(ont) été ajoutée(s)");
    } 
    
    public void resupplyAll(int quantite){
        for(Ingredient i : stocks){
            stocks.get(stocks.indexOf(i)).resupply(quantite);
        }
        JOptionPane.showMessageDialog(null,quantite+ " unité(s) de chaque ingrédient ont été ajoutées");
    }
    
    //renvoie une chaine contenant les noms des boissons de la machine
    public String getBoissons() {
        StringBuffer res= new StringBuffer(100);
        for (Boisson b :boissons){
            res.append(b.toString()+"\n");
        }
        return res.toString();
    }

    public String getStocks() {
        StringBuffer res= new StringBuffer(100);
        for (Ingredient I :stocks){
            res.append(I.getNom()+ " Quantité: " + I.getQuantite() +"\n");
        }
        return res.toString();
    }

    public void setStocks(final ArrayList<Ingredient> stocks) {
        this.stocks = stocks;
    }
    
    
    public void addIngredient(Ingredient i){
       stocks.add(i);
    }
    
    public Ingredient createIngredient(){
        String name = (JOptionPane.showInputDialog(null,"Veuillez choisir un nom d'ingredient"));
   	int q=0;
        try{
            q = Integer.parseInt(JOptionPane.showInputDialog(null,"Combien d'unité voulez-vous ajouter à chaque ingrédient du stock?"));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Quantité invalide");
        }
   	Ingredient i = new Ingredient(name,q);
   	 
   	JOptionPane.showMessageDialog(null,q+ " unité(s) de l'ingredient "+i.getNom()+ " a(ont) été ajouté(s)");
   	
    	return i;
    }
    
    
    
    public boolean modifierBoisson(){
        boolean fini = false;
        String name ="";
        while(fini == false){
            name = (JOptionPane.showInputDialog(null,"Voici les boissons contenues dans la machine :\n"
                                + this.getBoissons()
                                + "Aucune : Quitter"
                                + "Quelle boisson voulez-vous modifier ?"));
            if(name=="Quitter"){return false;};
            if(!boissons.contains(new Boisson(name))){
                JOptionPane.showMessageDialog(null,"Cette boisson n'est pas dans la machine");
            }else{
                fini = true;
            }
        }
        
        
        int price = 0;
        try{
            price = Integer.parseInt(JOptionPane.showInputDialog(null,"Indiquez le nouveau prix de cette boisson"));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Prix invalide");
        }
   	 
   	 
   	ArrayList<Ingredient> ib = new ArrayList();
   	
        
   	for (Ingredient i : stocks) {
                int q=0;
                try{
                    q = Integer.parseInt(JOptionPane.showInputDialog(null,"Combien d'unité de "+ i.getNom() +" voulez-vous ajouter ?"));
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Quantité invalide");
                }
                if(q>0){
                    ib.add(new Ingredient(i.getNom(), q));
                }
   	}
   	 
        
   	Boisson b = new Boisson(ib, name, price);
        this.boissons.remove(b);
   	this.ajouterBoisson(b);
         
   	 JOptionPane.showMessageDialog(null,"La boisson "+b.getNom()+ " a été ajoutée");
   	return true;
    }
    
    //TODO :
    // Possibilité de modifier le niveau de sucre par boisson
    // Persistance des ingrédients dans un fichier ou une bdd
    // Contrôler les entrées/sorties
}