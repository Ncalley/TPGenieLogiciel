/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffee;

/**
 *
 * @author Nicolas
 */
public class Coffee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Machine m = new Machine();


    }

    public void acheterBoisson() {
        System.out.println("Choisisez votre Boisson");
        System.out.println(m.getBoissons());
        String choiceUserBoisson = sc.nextLine();

        for (String boisson : m.getBoissons() ) {
        	if (choiceUserBoisson == boisson) {
        		m.acheter(choiceUserBoisson);
        	} else {
        		System.out.print("Votre choix n'existe pas, Veuillez reessayer");
        		acheterBoisson();
        	}
        	
        }

    }
    
}
