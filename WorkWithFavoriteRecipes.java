/*
Класс вносит изменения в файл FavoriteRecipes.csv
Доступные методы:
Метод добавления в избранные рецепты в файл FavoriteRecipes.csv
Метод удаления из избранных рецептов файла FavoriteRecipes.csv
Метод удаления всех рецептов из файла FavoriteRecipes.csv
Метод вывода всех избранных рецептов из файла FavoriteRecipes.csv
*/

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class WorkWithFavoriteRecipes {

    //Метод вывода всех избранных рецептов из файла FavoriteRecipes.csv
    static void ShowAllFavorites () {
        FileResource fr = new FileResource("FavoriteRecipes.csv");
        CSVParser parser = fr.getCSVParser(false);
        int index = 0;
        for (CSVRecord row : parser) {
            Recipe rec = new Recipe(row, index+1, true);
            rec.PrintTheRecipe();
            index++;
        }
    }

    static void RemoveAllFavorites () throws Exception {
        String fname = "FavoriteRecipes.csv";
        FileWriter writer = new FileWriter(fname);
        writer.append("");
        writer.close();
        System.out.println("You removed all recipes");
    }

    //Метод добавления избранных рецептов в файл FavoriteRecipes.csv
    static void AddFavorites (ArrayList <Recipe> RecipeArray, int RALength) throws Exception {
        Scanner in = new Scanner(System.in);
        String fname = "FavoriteRecipes.csv";
        System.out.println("Enter the names of recipes you want to add to Favorites, separate by \" \" :");
        String Input = in.nextLine();
        String [] arr = Input.split(" ");
        for (String RecName : arr) {
            for (Recipe rec : RecipeArray) {
                if (rec.GetName().equals(RecName)) {
                    String row = rec.GetName() + ",\"" + rec.GetIngredients() + "\",\"" + rec.GetHowToCook() + "\"" + "\n";
                    Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
                }
            }
        }
    }

    //Метод удаления нескольких рецептов из избранных рецептов файла FavoriteRecipes.csv
    static void RemoveSomeFavorite () throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("How many recipes do you want to delite from Favorites?");
        int howmany = input.nextInt();
        Scanner input2 = new Scanner(System.in);
        for (int i = 0; i < howmany; i++) {
            //Scanner input2 = new Scanner(System.in);
            System.out.println("Enter the name of recipe you want to remove from Favorites:");
            String Input = input2.nextLine();
            System.out.println("Starting recipe removing");
            WorkWithFavoriteRecipes.RemoveFavorite(Input);
            System.out.println("The recipe removed from Favorites.");
        }
    }

    //Метод удаления рецепта из избранных рецептов файла FavoriteRecipes.csv
    static void RemoveFavorite (String RecName) throws Exception {
        String fname = "FavoriteRecipes.csv";
        List<String> allElements = new ArrayList<String>();
        int index = 0;
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord row : parser) {
            String rowSt = row.get(0) + ",\"" + row.get(1) + "\",\"" + row.get(2) + "\"" + "\n";
            allElements.add(rowSt);
            //System.out.println(rowSt);
            if (row.get(0).equals(RecName)) {
                index = (allElements.size() - 1);
                // System.out.println("AllElements Size: " + allElements.size());
                // System.out.println("Index: " + index);
            }
        }
        allElements.remove(index);
        // System.out.println("AllElements Size: " + allElements.size());

        //Пытаемся удалить файл и создать заново
        File file = new File (fname);
        file.deleteOnExit();
        if(file.delete()){
            System.out.println("file.txt файл был удален с корневой папки проекта");
        }else System.out.println("Файл file.txt не был найден в корневой папке проекта");
        FileWriter out = new FileWriter(fname);
        CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT);

        // FileWriter writer = new FileWriter(fname);
        // writer.write("");
        for (String row : allElements) {
            Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
            //printer.print(row);
        }
        // writer.close();
        allElements.clear();

    }

}