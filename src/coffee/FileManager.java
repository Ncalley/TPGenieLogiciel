/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Gère la lecture et l'écriture des fichiers
 * @author Nicolas
 */
public class FileManager {
    private final String SAVEFILE = "Data/data.csv"; 
    private final Crypter CRYPTER = new Crypter("Machine à café de OOOUUUUFFF"); // pas génial qu'il soit ici mais bon... pas le temps

    //constructeurs
    /**
     * Le constructeur construit le fichier data qui contiendra les données
     */
    public FileManager() {
        String path="Data//";
        new File(path).mkdirs();
    }
    
    //méthodes
    /**
     * Fonction de chargement du fichier utilisable par la machine
     * renvoie un tableau de données contenant:
     *  - Une liste de boissons
     *  - Une liste d'ingrédients
     *  - Une liste qui contient uniquement l'entier correspondant au nombre maximal de boissons
     * @return ArrayList[]
     */
    public ArrayList[] loadLocal() {
        ArrayList data[] = {new ArrayList<Boisson>(),new ArrayList<Ingredient>(), new ArrayList()};
        try{
            return getData(SAVEFILE);
        }catch(FileNotFoundException e){
            try{
                storeLocal(new ArrayList(), new ArrayList(), 3);
                return getData(SAVEFILE);
            }catch(Exception e1){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
    /**
     * Fonction d'enregistrement des données dans un format (une LinkedList de String) qui permet l'écriture dans un fichier csv.
     * @param boissons
     * @param stocks
     * @throws java.lang.Exception
     */
    public void storeLocal(ArrayList<Boisson> boissons, ArrayList<Ingredient> stocks, int maxBoisson) throws Exception{
        LinkedList<String> text = new LinkedList<>();
        for(Boisson b : boissons){
            text.add(CRYPTER.encrypt(b.getData()));
        }
        for(Ingredient i : stocks){
            text.add(CRYPTER.encrypt(i.getData()));
        }
        text.add(CRYPTER.encrypt("Max,"+maxBoisson));
        rewrite(SAVEFILE,text);
    }
    
    /**
     * Permet d'écrire le contenu le la liste dans un fichier donné
     * @param nom_fichier
     * @param l 
     */
    private void rewrite(String nom_fichier, LinkedList<String> l){
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
     * Fonction de lecture du fichier qui renvoie les données extraites à la fontion loadLocal()
     * non accessible hors de cette classe.
     * @param fileName 
     */
    private ArrayList[] getData(String fileName) throws Exception{
        ArrayList data[] = {new ArrayList<Boisson>(),new ArrayList<Ingredient>(), new ArrayList()};
        
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
                splitLine = ligne.split(";");
                //cas où ce n'est pas une boisson
                if(!splitLine[0].equals("B")){
                    System.out.println(splitLine[0]);
                    //si c'est le nombre maxBoisson
                    splitElt = splitLine[0].split(",");
                    if(splitElt[0].equals("Max")){
                        
                        data[2].add(Integer.parseInt(splitElt[1]));
                    }else{
                        if(!data[1].contains(new Ingredient(splitElt[0],0))){
                            data[1].add(new Ingredient(splitElt[0],Integer.parseInt(splitElt[1])));
                        }
                    }
                }else{
                    //cas où c'est une boisson
                    ArrayList<Ingredient> ingredients = new ArrayList();
                    for(String elt : splitLine){
                        if(x>2){
                            if(x<splitLine.length-1){
                                splitElt = splitLine[x].split(",");
                                ingredients.add(new Ingredient(splitElt[0],Integer.parseInt(splitElt[1])));
                            }
                        }
                        
                        x++;
                        
                    }
                    if(!data[0].contains(new Boisson(splitLine[1]))){
                        data[0].add(new Boisson(ingredients,splitLine[1],Integer.parseInt(splitLine[splitLine.length-1])));
                    }
                }
                x=0;
            }
        }
        for(Boisson b : (ArrayList<Boisson>)data[0]){
            System.out.println(b.toString());
        }
        for(Ingredient b : (ArrayList<Ingredient>)data[1]){
            System.out.println(b.toString());
        }
        return data;
    }
    
   
    
    /**
     * Permet de supprimer le contenu du fichier (dangereux)
     */
    public void clean(){
        
        File index = new File("Data");
        String[]entries = index.list();
        for(String s: entries){
            File currentFile = new File(index.getPath(),s);
            currentFile.delete();
        }
        try {
            storeLocal(new ArrayList(),new ArrayList(),3);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
}
