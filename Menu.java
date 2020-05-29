/*
POCKET_COOKER!
---------------------------------------------------------------------------------------------------------------------------------------------------------------
Проект PocketCooker:
Реализовала приложение для хранения рецептов с работой через командную строку.
Каждый рецепт имеет уникальное имя и ID, будем считать это уникальными идентификаторами рецепта.

Приложение умеет:
1) Перед запуском программы создавать массив рецептов и загружать туда данные из файла RecipeBook.csv:
2) Создавать рецепт по запросу с командной строки, добавлять в массив рецептов и записывать в файл RecipeBook.csv, давать ему автоматически ID
3) Удалять рецепт по запросу из командной строки и из файла RecipeBook.csv
4) Получать рецепт по его имени
5) Удалять все рецепты из файла RecipeBook.csv
6) Редактировать рецепт, его ингредиенты или приготовление с записью новых данных в файл RecipeBook.csv
7) Осуществлять поиск по ингредиентам, выдавать наиболее релевантные, топ 3, неизвестные ингредиенты добавлять в отдельный файл Not_knowing_words.txt
8) Выводить все рецепты на экран из массива всех рецептов RecipeArray
9) Создать рекурсивно вызывающиеся меню с предложением доступных методов
10) Добавлять рецепты в избранный файл FavoriteRecipes.csv
11) Удалять рецепты из избранного файла FavoriteRecipes.csv
12) Выводить все рецепты на экран из файла избранных рецептов FavoriteRecipes.csv
13) Удалять все рецепты из избранных рецептов FavoriteRecipes.csv

-----------------------------------------------------------------------------------------------------------------------------------------------------------------

Cайты с полезной информацией:
1. Создание класса, его переменные, конструкторы, инициализатор: https://metanit.com/java/tutorial/3.1.php
2. Создание массива объектов ArrayList: http://developer.alexanderklimov.ru/android/java/arraylist.php
3. Ввод с консоли: https://metanit.com/java/tutorial/2.9.php https://www.w3schools.com/java/java_user_input.asp
4. HashMap: http://developer.alexanderklimov.ru/android/java/hashmap.php
5. .split(): http://proglang.su/java/strings-split
6. Добававление в конец файла: https://ru.stackoverflow.com/questions/463771/%D0%9A%D0%B0%D0%BA-%D0%B4%D0%BE%D0%B1%D0%B0%D0%B2%D0%B8%D1%82%D1%8C-%D1%82%D0%B5%D0%BA%D1%81%D1%82-%D0%B2-%D0%BA%D0%BE%D0%BD%D0%B5%D1%86-%D1%84%D0%B0%D0%B9%D0%BB%D0%B0-%D0%B2-java
7. Удаление из csv файла: https://sourceforge.net/p/opencsv/source/ci/master/tree/examples/AddressExample.java
8. Удаление элемента из списка ArrayList: https://javarush.ru/groups/posts/1935-udalenie-ehlementa-iz-spiska-arraylist

----------------------------------------------------------------------------------------------------------------------------------------------------------------

Задачи для вузовского проекта:

Реализовать приложение для хранения рецептов с работой через командную строку.
Каждый рецепт имеет уникальный ID, будем считать это уникальным идентификатором рецепта.

Приложение должно уметь:
+ 1) Перед запуском программы создавать массив рецептов и загружать туда данные из файла RecipeBook.csv:
    + 1. Разработать класс Reciept с примитивными методами получения значений его приватных переменных и вывода всех на экран
         Приватные переменные, которые должны быть и в файле csv: int ID, String Name, String Ingredients (через ; записаны), String HowToCook
    + 2. Создать конструктор, который создает экземпляр класса и заполняет его данные из файла csv
    + 3. Модифицировать метод создания экземпляра в метод, создающий массив объектов класса reciept и заполняющий их из csv файла

+ 2) Создавать рецепт по запросу с командной строки, добавлять в массив рецептов и записывать в файл RecipeBook.csv, давать ему автоматически ID
    + 1. Создать новый конструктор в классе Recipe, запрашивающий данные для добавления у пользователя и автоматически присваювающий ID
    + 2. Создать метод в классе WorkWithRecipeBook для создания рецепта и добавления его в файл и в массив

+ 3) Удалять рецепт по запросу из командной строки и из файла RecipeBook.csv
    Создать метод в классе WorkWithRecipeBook для удаления любого рецепта из массива и файла:
    + 1. Спросить у пользователя имя рецепта, который он хочет удалить, и вычислить его ID по поиску с массиве рецептов
    + 2. Удалить этот элемент массива и перезаписать ID у оставшихся рецептов 
    + 3. Удалить строку из файла

+ 4) Получать рецепт по его имени
    + 1. Создать метод GetRecipeByName в классе Search, который возвращает рецепт
    + 2. Если нашел - вернуть найденный. Если нет - написать, что не нашел и вернуть пустой рецепт

+ 5) Удалять все рецепты из файла RecipeBook.csv
    + 1. Создать метод DeleteAllReccipes в классе WorkWithRecipeBook
    + 2. Возвращает новый пустой массив рецептов и перезаписывает файл, оставляя только шапку

+ 6) Редактировать рецепт, его ингредиенты или приготовление с записью новых данных в файл RecipeBook.csv
    + 1. Создать метод ChangeRecipe в классе WorkWithRecipeBook
    + 2. Находим рецепт, который хотим изменить с помощью метода GetRecipeByName
    + 3. Меняем в нем одно из 3х: имя, ингредиенты или способ приготовления и заносим в массив и в файл

+ 7) Осуществлять поиск по ингредиентам, выдавать наиболее релевантные, топ 3, неизвестные ингредиенты добавлять в отдельный файл Not_knowing_words.txt
   + 1. Создать класс Search, осуществляющий все нужные операции
   + 2. Создать метод MenuSearch, в котором вызывается метод из класса Search и производятся все необходимые действия для поиска

+ 8) Выводить все рецепты на экран из массива всех рецептов RecipeArray
    + 1. Создать метод PrintAllRecipes в классе WorkWithRecipeBook
    + 2. С помощью цикла for вывести все рецепты на экран

+ 9) Создать рекурсивно вызывающиеся меню с предложением доступных методов
    + 1. Создать метод CallMenu в классе Menu
    + 2. Вызывать его рекурсивно

+ 10) Добавлять рецепты в избранный файл FavoriteRecipes.csv
    + 1. Создать метод AddFavorites в классе WorkWithFavoriteRecipes
    + 2. Пользователь пишет имена рецептов, которые хочет добавить в избранное

+ 11) Удалять рецепты из избранного файла FavoriteRecipes.csv
    + 1. Пользователь пишет количество рецептов которые хочет удалить из избранного и их имена
    + 2. Перезаписывает файл FavoriteRecipes.csv, без удаленных рецептов

+ 12) Выводить все рецепты на экран из файла избранных рецептов FavoriteRecipes.csv

+ 13) Удалять все рецепты из избранных рецептов FavoriteRecipes.csv

Дополнительно:
1. Меню и выводимые данные реализовать в виде отдельного возникающего окна, третья лекция на ТУИСе

-----------------------------------------------------------------------------------------------------------------------------------------------------------------

*/

import java.util.*;

public class Menu {
    public static void main (String[] args) throws Exception {
    //Создаем массив рецептов из файла RecipeBook.csv
        ArrayList <Recipe> RecipeArray = WorkWithRecipeBook.CreateRecipeArray();
    //Находим длину этого массива
        int RALength = RecipeArray.size();
    //Вызываем меню, приветствуя пользователя
        System.out.println("\n" + "Good day! Here is a PocketCooker! It can do a lot of different things, such as:");
        CallMenu(RecipeArray, RALength);
        System.out.println("Ok, it was nice to work with you." + "\n" + "Have a nice day! Good bye!");
    }

    static void CallMenu (ArrayList <Recipe> RecipeArray, int RALength) throws Exception {
        int Choice = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("\n" + "Add 1 recipe (1), Delite any recipe (2), Search recipe by name (3), Search top 3 recipes by ingredients you have (4),");
        System.out.println("Change Name, Ingredients or HowToCook of any recipe (5), Show all recipes (6), Delete all recipes (7),");
        System.out.println("Add some recipes to Favorites (8), Delete some recipes from Favorites (9), Show all Favorite recipes (10), Delite all from Favorites (11), Exit (12)" + "\n");
        System.out.println("Write the number of function you want to do:");
        Choice = in.nextInt();
        while (Choice < 1 || Choice > 12) {
            System.out.println("Wrong number. Choose again. Only from 1 to 12:");
            Choice = in.nextInt();
        }
        
        switch (Choice) {
            case 1:
            //Производим добавление рецепта в книгу рецептов и в массив используя класс WorkWithRecipeBook
            RecipeArray = WorkWithRecipeBook.AddRecipe(RecipeArray, RALength);
            RALength = RecipeArray.size();
            CallMenu(RecipeArray, RALength);
            break;

            case 2:
            //Производим удаление рецепта из книги рецептов и масссива используя класс WorkWithRecipeBook
            RecipeArray = WorkWithRecipeBook.RemoveRecipe(RecipeArray, RALength);
            RALength = RecipeArray.size();
            //ДОБАВЬ ВЫЗОВ МЕТОДА УДАЛЕНИЯ ВСЕГО ИЗ FAVORITES

            CallMenu(RecipeArray, RALength);
            break;

            case 3:
            //Производим поиск по книге рецептов по имени рецепта, используя класс Search
            Recipe FoundRecipe = Search.GetRecipeByName(RecipeArray, RALength);
            if (FoundRecipe.GetID() != 0) FoundRecipe.PrintTheRecipe();
            CallMenu(RecipeArray, RALength);
            break;

            case 4:
            //Производим поиск по книге рецептов по доступным ингредиентам для нахождения топ 3 наиболее релевантных рецептов, используя класс Search
            Search.SearchRelevantRecipes(RecipeArray, RALength);
            CallMenu(RecipeArray, RALength);
            break;

            case 5:
            //Производим изменение рецепта, выбранного пользователем по имени, используя класс WorkWithRecipeBook
            RecipeArray = WorkWithRecipeBook.ChangeRecipe(RecipeArray, RALength);
            CallMenu(RecipeArray, RALength);
            break;

            case 6:
            //Производим вывод всех рецептов, используя класс WorkWithRecipeBook
            WorkWithRecipeBook.PrintAllRecipes(RecipeArray, RALength);
            CallMenu(RecipeArray, RALength);
            break;

            case 7:
            //Производим удаление всех рецептов из книги рецептов и масссива используя класс WorkWithRecipeBook
            RecipeArray = WorkWithRecipeBook.DeleteAllRecipes(RecipeArray, RALength);
            RALength = RecipeArray.size();
            CallMenu(RecipeArray, RALength);
            break;

            case 8:
            //Производим добавление рецептов в файл FavoriteRecipes.txt используя класс WorkWithFavoriteRecipes
            WorkWithFavoriteRecipes.AddFavorites(RecipeArray, RALength);
            CallMenu(RecipeArray, RALength);
            break;

            case 9:
            //Производим удаление рецептов из файла FavoriteRecipes.txt используя класс WorkWithFavoriteRecipes
            WorkWithFavoriteRecipes.RemoveSomeFavorite();
            CallMenu(RecipeArray, RALength);
            break;

            case 10:
            //Производим вывод всех рецептов из файла FavoriteRecipes.txt используя класс WorkWithFavoriteRecipes
            WorkWithFavoriteRecipes.ShowAllFavorites();;
            CallMenu(RecipeArray, RALength);
            break;

            case 11:
            //Производим удаление всех рецептов из  файла FavoriteRecipes.txt используя класс WorkWithFavoriteRecipes
            WorkWithFavoriteRecipes.RemoveAllFavorites();
            CallMenu(RecipeArray, RALength);
            break;

            case 12:
            break;
        }
    }
}