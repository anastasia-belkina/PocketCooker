/*
Класс SEARCH. 
Методы:
Метод SearchRelevantRecipes реализует поиск по составу ингредиентов в рецептах, взятых из файла RecipeBook.csv, находя наиболее релевантные, отображая топ 3 пользователю.
   Если какой-то ингредиент из строки поиска неизвестен, то происходит их запись в конец файла Not_knowing_words.txt
Меотд GetRecipeByName находит рецепт по имени и возвращает его

1. Поисковый запрос - строка ингредиентов, введенных пользователем, разделенных "_";
2. На выходе - вывод в терминал топ 3 самых релевантных рецепта, или меньше, в зависимости от введенных ингредиентов

Этапы разработки метода SearchRelevantRecipes:
+ 1. Создать метод Search, разделяющий строку, введенную пользователем, на массив слов по "+"
+ 2. Создать метод GetScore, рассчитывающий score по каждому рецепту сначала прибавление очков, по наличию ингредиентов в составе, а после деление этих очков
    на общее количество ингредиентов в составе, ищем соответствие введенного ингредиента в строке Ingredients через .contains()
+ 3. Создание hashmap, который будет хранить в себе объект-рецепт и его score, полученный с помощью метода GetScore
+ 4. Сортировка hashmap по убыванию score
+ 5. Вывод топ 3 рецептов-объектов из map пользователю (если score первого элемента map равен 0, то выводить на экран сообщение, что нет подходящих рецептов)
+ 6. Неизвестные слова из запроса пользователя заносить в специальный файл Not_knowing_words.txt для дальнейшего добавления рецептов, интересных пользователю
*/

import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Search {
    
    static Recipe GetRecipeByName (ArrayList <Recipe> RecipeArray, int RALength) {
        Recipe recipe = new Recipe();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a name of the recipe:");
        String InputName = in.nextLine();
        for (Recipe rec : RecipeArray) {
            if (rec.GetName().equals(InputName)) {
                recipe = rec;
                return recipe;
            }
        }
        System.out.println("The recipe is not found. Please check if you wrote the name of recipe correct.");
        return recipe;
    }
    
    public static void SearchRelevantRecipes (ArrayList <Recipe> RecipeArray, int RALength) throws Exception {
        Search rSearch = new Search();
    //Вызываем метод Search, который запрашивает строку ингредиентов, делит ее на массив слов и возвращает его
        ArrayList <String> SearchWordsArray = rSearch.Search();
    //Находим длину этого массива
        int SWALength = SearchWordsArray.size();
    //Проверяем слова из поиска, если их нет ни в одном рецепте - заносим в файл Not_knowing_words.txt
        rSearch.CheckUnknownWords(RecipeArray, RALength, SearchWordsArray, SWALength);
    //Создаем HashMap и вызываем метод CreateSortedHashmap
        Map <Double, Recipe> HashMap = CreateSortedHashmap(RecipeArray, RALength, SearchWordsArray, SWALength);
    //Вызываем метод Print3RelevantRecipes для отображения 3х самых релевантных рецептов
        rSearch.Print3RelevantRecipes(HashMap, SearchWordsArray, SWALength);
    }

    ArrayList CreateRecipeArray (CSVParser parser) {
        ArrayList<Recipe> recipesList = new ArrayList<>();
        int index = 0;
        for (CSVRecord row : parser) {
            Recipe rec = new Recipe(row, index+1);
            recipesList.add(rec);
            // recipesList.get(index).PrintTheRecipe();
            // index++;
        }     
        return recipesList;
    }

    ArrayList Search () {
        ArrayList <String> SearchWordsArray = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter ingredients that you have, separating words with _ :");
        String SearchInputLine = in.nextLine();
        // System.out.println("Input cheking: " + SearchInputLine);
        // System.out.println("");
        for (String word : SearchInputLine.split("_")) {
            SearchWordsArray.add(word);
        }
        return SearchWordsArray;
    }

    void CheckUnknownWords (ArrayList <Recipe> RecipeArray, int RALength, ArrayList <String> SearchWordsArray, int SWALength) throws Exception {
        int temp = 0;
        for (String word : SearchWordsArray) {
            for (Recipe recipe : RecipeArray) {
                if (recipe.GetIngredients().contains(word) == false) {
                    temp++;
                }
            }
            if (temp == RALength) {
                System.out.println("Sorry, I dont know the ingredient " + word + ". I will put it into list Not_knowing_Ingredients.txt and later will add recipes with them.");
                String fname = "Not_knowing_Ingredients.txt";
                word = "\n" + word;
                Files.write(Paths.get(fname), word.getBytes(), StandardOpenOption.APPEND);
            }
            temp = 0;
        } 
    }

    static Map CreateSortedHashmap (ArrayList <Recipe> RecipeArray, int RALength, ArrayList <String> SearchWordsArray, int SWALength) {
        //Создали hashmap, где мы храним рецепт и score
        Map <Double, Recipe> HashMap = new HashMap<Double, Recipe>();
        //Вызываем метод GetScore для расчета релевантности для каждого рецепта и заносим его к рецепту в hashmap
        Double tempina = -1.0;
        for (int i = 0; i < RALength; i++) {
            Recipe value = RecipeArray.get(i);
            double key = value.GetScore(SearchWordsArray, SWALength);
            if (tempina == -1.0) {
                tempina = key;
            }
            else {
                if (tempina !=0 && tempina == key) {
                    key++;
                }
            }
            HashMap.put(key, value);
        }
        //Выведем все ключи
        // for (Double key : HashMap.keySet()) {
        //     System.out.println("Key: " + key);
        // }
        //Создали TreeMap, который автоматически отсортировал по ключу
        TreeMap <Double, Recipe> sortedHashMap = new TreeMap<>(HashMap);
        HashMap = sortedHashMap;
        return HashMap;
    }

    void Print3RelevantRecipes (Map <Double, Recipe> HashMap, ArrayList <String> SearchWordsArray, int SWALength) throws Exception {
        //Достанем элемент из нашего HashMap
        Set<Map.Entry<Double, Recipe>> set = HashMap.entrySet();
        int count = 0;
        for (Map.Entry<Double, Recipe> hashmap : set) {
            if (hashmap.getKey() == 0) {
                continue;
            }
            if (count == 3) {
                break;
            }
            else {
                hashmap.getValue().PrintTheRecipe();
            }
        }
    }

}