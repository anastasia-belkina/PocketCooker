/*
Класс RECIPE, на основе которого создаются объекты-рецепты и заносятся в массив рецептов.
Имеет:
Конструктор, достающий данные из файла и заносящий в поля объекта
Конструктор, сам запрашивающий данные для создания нового рецепта
Методы для получения всех приватных переменных
Метод вывода рецепта на экран
Метод расчета score для рецепта в зависимости от ингредиентотв из поиска
Метод получения количества ингредиентов в рецепте
Метод изменения ID
*/


import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.ArrayList;
import java.util.*;

public class Recipe {

    private int ID;
    private String Name;
    private String Ingredients;
    private String HowToCook;

    Recipe () {
    
    }

    Recipe (CSVRecord row, int IDa, boolean temp) {
        ID = IDa;
        Name = row.get(0);
        Ingredients = row.get(1);
        HowToCook = row.get(2);
    }

    Recipe (int RALength) {
        ID = RALength+1;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a Name of the recipe. If it contains more words than 1, write them all together:");
        Name = in.nextLine();
        System.out.println("Enter Ingredients of the recipe, spliting them with ';' :");
        Ingredients = in.nextLine();
        System.out.println("Enter how to cook the recipe. Use \"~\" instead of \",\":");
        HowToCook = in.nextLine();
    }

    Recipe (CSVRecord row, int IDa) {
        ID = IDa;
        Name = row.get("Name");
        Ingredients = row.get("Ingredients");
        HowToCook = row.get("HowToCook");
    }

    int GetID () {
        return ID;
    }

    String GetName () {
        return Name;
    }

    String GetIngredients () {
        return Ingredients;
    }

    String GetHowToCook () {
        return HowToCook;
    }

    void PrintTheRecipe () {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("ID of recipe:");
        System.out.println(GetID());
        System.out.println("Name of recipe:");
        System.out.println(GetName());
        System.out.println("Ingredients:");
        System.out.println(GetIngredients());
        System.out.println("How to cook:");
        for (String retval : GetHowToCook().split("~")) {
            System.out.println(retval);
        }
        System.out.println("---------------------------------------------------------------------");
    }

    Double GetScore (ArrayList <String> SearchWordsArray, int SWALength) {
        double Score = 0;
        int AmountIngredients = GetAmountIngredients();
        for (int i = 0; i < SWALength; i++) {
            String temp = SearchWordsArray.get(i);
            if (Ingredients.contains(temp)) {
                Score+=100;
            }
        }
        Score = Score/AmountIngredients;
        //Такие странные махинации, чтобы под TreeMap подстроить, который сортирует по возрастанию score, а особо вникать в сортировку мне было лень
        Score = Score - 2*Score;
        return Score;
    }

    int GetAmountIngredients () {
        int amount = 0;
        ArrayList <String> IngredientsArray = new ArrayList<>();
        for (String word : Ingredients.split(";")) {
            IngredientsArray.add(word);
        }
        amount = IngredientsArray.size();
        return amount;
    }

    void ChangeID (int temp) {
        ID = temp;
    }

    void ChangeName (String temp) {
        Name = temp;
    }

    void ChangeIngredients (String temp) {
        Ingredients = temp;
    }

    void ChangeHowToCook (String temp) {
        HowToCook = temp;
    }


}