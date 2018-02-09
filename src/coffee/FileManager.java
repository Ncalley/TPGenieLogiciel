/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Gère la lecture et l'écriture des fichiers
 * @author Nicolas
 */
public class FileManager {
    private final String SAVEFILE = "Data/Accounts/comptes.csv"; 
    private final Crypter CRYPTER = new Crypter("Machine à café de OOOUUUUFFF");

    public FileManager() {}
    
    
    public void loadLocal(){
        getData(SAVEFILE);
    }
    
    /**
     * Permet de recuperer toutes les donnees des comptes enregistres pour les sauvegarder en csv
     * @param boissons
     * @param stocks
     * @throws java.lang.Exception
     */
    public void storeLocal(ArrayList<Boisson> boissons, ArrayList<Ingredient> stocks) throws Exception{
        LinkedList<String> text = new LinkedList<>();
        StringBuilder data = new StringBuilder();
        for(Boisson b : boissons){
            data.append(b.toString());
        }
        for(Ingredient i : stocks){
            data.append(i.toString());
        }
        text.add(CRYPTER.encrypt(data.toString()));
        rewrite(SAVEFILE,text);
    }
    
    /**
     * Permet d'écrire le contenu le la liste dans un fichier donné
     * @param nom_fichier
     * @param l 
     */
    public void rewrite(String nom_fichier, LinkedList<String> l){
        //ouvrir le fichier en écriture
	File f = new File(nom_fichier);
        try (FileWriter fw = new FileWriter(f)) {
            for(String elt : l){ //pour chaque élément de la liste l
                //on l'écrit dans le fichier
                fw.write(String.valueOf(elt));
                fw.write("\r\n"); // puis on passe une ligne
            }
        }
	catch(IOException e){
            System.out.println(e.toString()); // en cas d'erreur, on affiche l'erreur
	}
    }
    

    /**
     * Remplit le HashMap identifiers avec le contenu du fichier donné
     * @param fileName 
     */
    private ArrayList[] getData(String fileName){
        ArrayList data[] = {new ArrayList<Boisson>(),new ArrayList<Ingredient>()};
        try{
            // On lit le fichier
            InputStream ips=new FileInputStream(fileName); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            try (BufferedReader br = new BufferedReader(ipsr)) {
                String ligne;
                String[] splitLine;
                String[] splitElt;
                int x = 0;
                while ((ligne=br.readLine())!=null){ // pour chaque ligne
                    //On suppose que les mots sont séparés par des retours à la ligne
                    ligne = CRYPTER.decrypt(ligne);
                    //System.out.println(ligne);
                    splitLine = ligne.split(";");
                    if(!splitLine[0].equals("B")){
                        splitElt = splitLine[0].split(",");
                        data[1].add(new Ingredient(splitElt[0],Integer.parseInt(splitElt[1])));
                    }else{
                        ArrayList<Ingredient> ingredients = new ArrayList();
                        for(String elt : splitLine){
                            if(x>2){
                                if(x<splitLine.length-1){
                                    splitElt = splitLine[x].split(",");
                                    ingredients.add(new Ingredient(splitElt[0],Integer.parseInt(splitElt[1])));
                                }
                            }
                            data[0].add(new Boisson(ingredients,splitLine[0],Integer.parseInt(splitLine[splitLine.length-1])));
                            x++;
                        }
                    }
                    x=0;
                }
            }
	}		
	catch (Exception e){
            System.out.println(e.toString());
        }
        return data;
    }
    
   
    
    /**
     * Permet de supprimer tous les comptes en mémoire (dangereux)
     */
    public void clean(){
        identifiers.clear();
        comptes.clear();
        File index = new File("Data/Rapports");
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
        try {
            storeLocal();
        } catch (Exception ex) {
            Logger.getLogger(ComptesEnregistres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
