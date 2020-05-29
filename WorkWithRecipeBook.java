/*
Класс вносит изменения в файл RecipeBook.csv
Доступные методы:
Метод создания массива рецептов из файла RecipeBook.csv
Метод добавления нового рецепта в конец файла
Метод удаления одного рецепта по его имени
Метод удаления всех рецептов
Метод изменения рецепта, найденного по имени, и записывающий изменения и в файл, и в массив
Метод вывода всех рецептов на экран
*/

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class WorkWithRecipeBook {

    //Метод создания массива рецептов из файла RecipeBook.csv
    static ArrayList CreateRecipeArray () {
        FileResource fr = new FileResource("RecipeBook.csv");
        CSVParser parser = fr.getCSVParser();
        ArrayList<Recipe> recipesList = new ArrayList<>();
        int index = 0;
        for (CSVRecord row : parser) {
            Recipe rec = new Recipe(row, index+1);
            recipesList.add(rec);
            // recipesList.get(index).PrintTheRecipe();
            index++;
        }     
        return recipesList;
    }

    //Метод вывода всех рецептов на экран
    static void PrintAllRecipes (ArrayList <Recipe> RecipeArray, int RALength) {
        for (Recipe rec : RecipeArray) {
            rec.PrintTheRecipe();
        }
    }

    //Метод добавления рецепта в файл и в массив
    static ArrayList AddRecipe (ArrayList <Recipe> RecipeArray, int RALength) throws Exception {
        ArrayList <Recipe> NewRecipeArray = RecipeArray;
        // System.out.println("The length of RecipeArray before adding new recipe: " + NewRecipeArray.size());
        // System.out.println("The ID of last recipe in RecipeArray before adding new recipe: " + NewRecipeArray.get(NewRecipeArray.size()-1).GetID());
        //Создали новый рецепт
        Recipe rec = new Recipe(RALength);
        System.out.println("The ID of New Recipe is: " + rec.GetID());
        //Добавили его в массив действующий
        NewRecipeArray.add(rec);
        // System.out.println("The length of RecipeArray after adding new recipe: " + NewRecipeArray.size());
        //Добавили его в файл RecipeBook.csv, изначально создав строку из данных
        String fname = "RecipeBook.csv";
        String row = "\n" + rec.GetName() + ",\"" + rec.GetIngredients() + "\",\"" + rec.GetHowToCook() + "\"";
        Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
        return NewRecipeArray;
    }

    //Метод удаления всех рецептов из массива и файла 
    static ArrayList DeleteAllRecipes(ArrayList <Recipe> RecipeArray, int RALength) throws Exception{
        ArrayList <Recipe> NewRecipeArray = RecipeArray;
    //Удалили все из массива
        NewRecipeArray.clear();
    //Удаляем все из файла и записываем туда только шапку
        String fname = "RecipeBook.csv";
        FileWriter writer = new FileWriter(fname);
        writer.append("");
        String row = "Name,Ingredients,HowToCook";
        Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
        writer.close();
        System.out.println("You removed all recipes");
        return NewRecipeArray;
    }

    //Метод изменения рецепта, найденного по имени, и записывающий изменения и в файл, и в массив
    static ArrayList ChangeRecipe(ArrayList <Recipe> RecipeArray, int RALength) throws Exception {
        ArrayList <Recipe> NewRecipeArray = RecipeArray;
        Recipe RecipeToChange = new Recipe();
        RecipeToChange = Search.GetRecipeByName(RecipeArray, RALength);
        if (RecipeToChange.GetID() != 0) {
            System.out.println("That's how the recipe is looking now:" + "\n");
            RecipeToChange.PrintTheRecipe();

            //То, что нужно для каждого кейса
            int ArrayIDofChangeRecipe = RecipeToChange.GetID() - 1;
            System.out.println("ID of the recipe u want to change is: " + (ArrayIDofChangeRecipe+1));
            List<String> allElements = new ArrayList<String>();
            String fname = "RecipeBook.csv";
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser();
            int index = 0;
            String Head = "Name,Ingredients,HowToCook";
            allElements.add(Head);
            for (CSVRecord row : parser) {
                String rowSt = "\n" + row.get("Name") + ",\"" + row.get("Ingredients") + "\",\"" + row.get("HowToCook") + "\"";
                allElements.add(rowSt);
                //System.out.println(allElements.get(index));
                index++;
            }
            String StringToChange = allElements.get(ArrayIDofChangeRecipe+1);
            String[] arr = StringToChange.split(",");
            FileWriter writer = new FileWriter(fname);
            //----------------------------------------

            Scanner in = new Scanner(System.in);
            Scanner inInt = new Scanner(System.in);
            System.out.println("\n" + "What do you want to change? Enter the number: 1 - Name, 2 - Ingredients, 3 - HowToCook: ");
            //int WhatToDo = Integer.parseInt(in.nextLine());
            int WhatToDo = inInt.nextInt();
            while (WhatToDo < 1 || WhatToDo > 12) {
                System.out.println("Wrong number. Choose again. Only from 1 to 12:");
                WhatToDo = inInt.nextInt();
            }
            switch (WhatToDo) {
            case 1: 
                System.out.println("Enter a new name of the recipe: ");
                String NewName = in.nextLine();
            //Изменим имя в массиве рецептов
                NewRecipeArray.get(ArrayIDofChangeRecipe).ChangeName(NewName);
                System.out.println("The recipe after changing in array:");
                NewRecipeArray.get(ArrayIDofChangeRecipe).PrintTheRecipe();
                System.out.println("\n");
            //Изменим имя в файле
                arr[0] = "\n" + NewName;
                StringToChange = arr[0] + "," + arr[1] + "," + arr[2];
                allElements.set(ArrayIDofChangeRecipe+1, StringToChange);
                writer.append("");
                for (String row : allElements) {
                    Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
                }
                writer.close();
            break;

            case 2:
                System.out.println("Enter new Ingredients for the recipe. Separate them by \";\" : ");
                String NewIngredients = in.nextLine();
            // Изменим ингредиенты в массиве рецептов
                NewRecipeArray.get(ArrayIDofChangeRecipe).ChangeIngredients(NewIngredients);
                System.out.println("The recipe after changing in array:");
                NewRecipeArray.get(ArrayIDofChangeRecipe).PrintTheRecipe();
                System.out.println("\n");
            //Изменим ингредиенты в файле
                arr[1] = "\"" + NewIngredients + "\"";
                StringToChange = arr[0] + "," + arr[1] + "," + arr[2];
                allElements.set(ArrayIDofChangeRecipe+1, StringToChange);
                writer.append("");
                for (String row : allElements) {
                    Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
                }
                writer.close();
            break;

            case 3:
                System.out.println("Enter how to cook the recipe. Use \"~\" instead of \",\": ");
                String NewHowToCook = in.nextLine();
            // Изменим ингредиенты в массиве рецептов
                NewRecipeArray.get(ArrayIDofChangeRecipe).ChangeHowToCook(NewHowToCook);
                System.out.println("The recipe after changing in array:");
                NewRecipeArray.get(ArrayIDofChangeRecipe).PrintTheRecipe();
                System.out.println("\n");
            //Изменим ингредиенты в файле
                arr[2] = "\"" + NewHowToCook + "\"";
                StringToChange = arr[0] + "," + arr[1] + "," + arr[2];
                allElements.set(ArrayIDofChangeRecipe+1, StringToChange);
                writer.append("");
                for (String row : allElements) {
                    Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
                }
                writer.close();
            break;
            }
        }
        return NewRecipeArray;
    }

    //Метод удаления одного рецепта по имени из файла и из массива
    //Возвращает новый массив рецептов без удаленного и перезаписывает файл
    static ArrayList RemoveRecipe(ArrayList <Recipe> RecipeArray, int RALength) throws Exception {  
        ArrayList <Recipe> NewRecipeArray = RecipeArray;
        Scanner in = new Scanner(System.in);
        int RemoveID = -1;
        int temp = 1;
    //Спросили имя рецепта, который хочет удалить
        System.out.println("Enter a Name of the recipe you want to remove. If it contains more words than 1, write them all together:");
        String RemoveName = in.nextLine();
    //Ищем рецепт по имени в массиве рецептов
        for (Recipe rec : RecipeArray) {
            //Нашли - просим ID, удаляем из файла и массива, прерываем цикл
            if (rec.GetName().equals(RemoveName)) {
                RemoveID = rec.GetID();
                System.out.println("RemoveID: " + RemoveID);
                //Удаляем из массива
                NewRecipeArray.remove(RemoveID-1);
                System.out.println("Removing was ok");
                int NRALength = NewRecipeArray.size();
                System.out.println("Array Length after removing recipe from it: " + NRALength);
                //Перезаписали ID у рецептов в массиве
                for (Recipe recip : RecipeArray) {
                    recip.ChangeID(temp);
                    temp++;
                }
                //Удаляем из файла путем перезаписывания его без удаленной строки
                List<String> allElements = new ArrayList<String>();
                FileResource fr = new FileResource("RecipeBook.csv");
                CSVParser parser = fr.getCSVParser();
                int index = 0;
                String Head = "Name,Ingredients,HowToCook";
                allElements.add(Head);
                for (CSVRecord row : parser) {
                    String rowSt = "\n" + row.get("Name") + ",\"" + row.get("Ingredients") + "\",\"" + row.get("HowToCook") + "\"";
                    allElements.add(rowSt);
                    //System.out.println(allElements.get(index));
                    index++;
                } 
                allElements.remove(RemoveID);
                String fname = "RecipeBook.csv";
                FileWriter writer = new FileWriter(fname);
                writer.append("");
                for (String row : allElements) {
                    Files.write(Paths.get(fname), row.getBytes(), StandardOpenOption.APPEND);
                }
                writer.close();
                break;
            }
        }
        return NewRecipeArray;
    }
}