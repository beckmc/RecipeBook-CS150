import java.io.*;
import java.util.*;

public class InputOutput {
    
    private File dataFile = new File("recipe_60000a.txt");
    
    private RecipeBook rb = new RecipeBook(dataFile); 
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    
    private Boolean running = true;
    
    public static void main(String[] args) {
        InputOutput io = new InputOutput();
    }
    
    /**
     * Default constructor class. Runs the input/output command until quit is selected.
     */
    public InputOutput() {
        while (running) {
            try {
                home();
            } catch (Exception e) {
                System.out.println("Error. Field must be populated and can only contain integers.");
            }
        }
    }
    
    private void home() throws IOException {
        System.out.println("What would you like to do? (add/delete/find/plan/quit)");
        String input = br.readLine();
        
        if (input.contains("add")) {
            addRecipe();
        }else if (input.contains("delete")) {
            deleteRecipe();
        }else if (input.contains("find")) {
            find();
        }else if (input.contains("plan")) {
            plan();
        } else if (input.contains("quit")) {
            quit();
        } else {
            System.out.print("Invalid command. ");
        }
    }
    
    private void addRecipe() throws IOException {
        System.out.println("Enter Name");
        String name = br.readLine();
        System.out.println("Enter Cuisine");
        String cuisine = br.readLine();
        System.out.println("Enter Type");
        String type = br.readLine();
        System.out.println("Enter Main Ingredient(s), Separated By A Space");
        String mainIngredients = br.readLine();
        System.out.println("Enter Addon Ingredient(s), Separated By A Space");
        String addonIngredients = br.readLine();
        System.out.println("Enter Side Ingredient(s), Separated By A Space");
        String sideIngredients = br.readLine();
        System.out.println("Enter Prep Time, Integers Only");
        int prepTime = Integer.parseInt(br.readLine());
        System.out.println("Enter Cook Time, Integers Only");
        int cookTime = Integer.parseInt(br.readLine());
        
        Recipe addRecipe = new Recipe(cuisine, name, type, mainIngredients, addonIngredients, sideIngredients, prepTime, cookTime);
        
        rb.addToTree(addRecipe);
        
        System.out.println("Recipe created.");
    }
    
    private void deleteRecipe() throws IOException {
        System.out.println("Enter Name");
        String name = br.readLine();
        
        Recipe removalRecipe = rb.findRecipe(name);
        
        if (removalRecipe == null) {
            System.out.println("Error. Recipe does not exist.");
        } else {
            System.out.println("Are you sure you want to delete " + name + "? (yes/no)");
            
            String input = br.readLine();
            
            if (input.contains("yes")) {
                rb.deleteRecipe(name);
                System.out.println("Recipe deleted");
            } else {
                System.out.println("Recipe NOT deleted");
            }
        }
    }
    
    private void find() throws IOException {
        System.out.println("Enter Total Prep/Cook Time, Integers Only");
        int totalTime = Integer.parseInt(br.readLine());
        
        System.out.println("Enter Required Item(s), Separated By A Space (Or Leave Blank For None)");
        String includedItems = br.readLine();
        
        System.out.println("Enter Excluded Item(s), Separated By A Space (Or Leave Blank For None)");
        String excludedItems = br.readLine();
        
        System.out.println("Enter Cuisine Type (Or Leave Blank For None)");
        String cuisine = br.readLine();
        
        RecipeSearch rs = new RecipeSearch(rb);
        
        ArrayList<Recipe> goodRecipes = new ArrayList<>();
        Recipe bestRecipe;
        
        goodRecipes = rs.search(totalTime, includedItems, excludedItems, cuisine);
        
        if (goodRecipes.size() == 0) {
            System.out.println("No recipes found.");
        } else {
            bestRecipe = goodRecipes.get(goodRecipes.size() - 1);
            System.out.println("The recipe found was:");
            printRecipe(bestRecipe);
        }
    }
    
    private void plan() throws IOException {
        System.out.println("Enter Total Prep/Cook Time, Integers Only");
        int totalTime = Integer.parseInt(br.readLine());
        
        System.out.println("Enter Required Item(s), Separated By A Space (Or Leave Blank For None)");
        String includedItems = br.readLine();
        
        System.out.println("Enter Excluded Item(s), Separated By A Space (Or Leave Blank For None)");
        String excludedItems = br.readLine();
        
        System.out.println("Enter Cuisine Type (Or Leave Blank For None)");
        String cuisine = br.readLine();
        
        ArrayList<Recipe> planArray = new ArrayList<>();
        Recipe bestRecipeEntree;
        Recipe bestRecipeSalad;
        Recipe bestRecipeAppetizer1;
        Recipe bestRecipeAppetizer2;
        
        planArray = check(totalTime, includedItems, excludedItems, cuisine);
        
        if (planArray != null) {
            bestRecipeEntree = planArray.get(0);
            bestRecipeSalad = planArray.get(1);
            bestRecipeAppetizer1 = planArray.get(2);
            bestRecipeAppetizer2 = planArray.get(3);
            System.out.println("The entree found was:");
            printRecipe(bestRecipeEntree);
            System.out.println("The salad found was:");
            printRecipe(bestRecipeSalad);
            System.out.println("The first appetizer found was:");
            printRecipe(bestRecipeAppetizer1);
            System.out.println("The second appetizer found was:");
            printRecipe(bestRecipeAppetizer2);
        } else {
            System.out.println("Error. Unable to generate plan.");
        }
    }
    
    private ArrayList<Recipe> check(int totalTime, String includedItems, String excludedItems, String cuisine) {
        MealPlanSearch ps = new MealPlanSearch(rb);
        
        ArrayList<Recipe> planArray = new ArrayList<>();
        ArrayList<Recipe> planArray2 = new ArrayList<>();
        ArrayList<Recipe> planArray3 = new ArrayList<>();
        
        ArrayList<Recipe> goodRecipesEntree = new ArrayList<>();
        ArrayList<Recipe> goodRecipesSalad = new ArrayList<>();
        ArrayList<Recipe> goodRecipesAppetizer = new ArrayList<>();
        
        int suggestedPlan = 0;
        int suggestedPlanTime = 0;
        
        goodRecipesEntree = ps.search(totalTime, includedItems, excludedItems, cuisine, "Entree");
        goodRecipesSalad = ps.search(totalTime, "", excludedItems, cuisine, "Salad");
        goodRecipesAppetizer = ps.search(totalTime, "", excludedItems, cuisine, "Appetizer");
            
            
        MakeRecipePlan planner = new MakeRecipePlan();
        planArray = planner.planRecipes(goodRecipesEntree, goodRecipesSalad, goodRecipesAppetizer, totalTime);
        
        suggestedPlan = 1;
        
        if (planArray != null) {
            suggestedPlanTime = getPlanTime(planArray);
        }
        
        
        if (suggestedPlanTime != totalTime) {
            goodRecipesEntree = ps.search(totalTime, "", excludedItems, cuisine, "Entree");
            goodRecipesSalad = ps.search(totalTime, includedItems, excludedItems, cuisine, "Salad");
            goodRecipesAppetizer = ps.search(totalTime, "", excludedItems, cuisine, "Appetizer");
            
            planArray2 = planner.planRecipes(goodRecipesEntree, goodRecipesSalad, goodRecipesAppetizer, totalTime);
            
            if (planArray2 != null && suggestedPlanTime < getPlanTime(planArray2)) {
                planArray = planArray2;
                suggestedPlan = 2;
                suggestedPlanTime = getPlanTime(planArray2);
            }
        }
        
        if (suggestedPlanTime != totalTime) {
            goodRecipesEntree = ps.search(totalTime, "", excludedItems, cuisine, "Entree");
            goodRecipesSalad = ps.search(totalTime, "", excludedItems, cuisine, "Salad");
            goodRecipesAppetizer = ps.search(totalTime, includedItems, excludedItems, cuisine, "Appetizer");
            
            planArray3 = planner.planRecipes(goodRecipesEntree, goodRecipesSalad, goodRecipesAppetizer, totalTime);
            
            if (planArray3 != null && suggestedPlanTime < getPlanTime(planArray3)) {
                planArray = planArray3;
                suggestedPlan = 3;
                suggestedPlanTime = getPlanTime(planArray3);
            }
        }
        
        return planArray;
    }
    
    private int getPlanTime(ArrayList<Recipe> planArray) {
        Recipe entree = planArray.get(0);
        Recipe salad = planArray.get(0);
        Recipe appetizer1 = planArray.get(0);
        Recipe appetizer2 = planArray.get(0);
        
        int entreeTime = entree.getPrepTime();
        int saladTime = salad.getPrepTime();
        int appetizer1Time = appetizer1.getPrepTime();
        int appetizer2Time = appetizer2.getPrepTime();
        int cookTime = Math.max(entree.getCookTime(), Math.max(salad.getCookTime(), Math.max(appetizer1.getCookTime(), appetizer2.getCookTime())));
        
        return entreeTime + saladTime + appetizer1Time + appetizer2Time + cookTime;
    }
    
    private void printRecipe(Recipe recipe) {
        System.out.println("name: " + recipe.getName());
        System.out.println("type: " + recipe.getType());
        System.out.println("cuisine: " + recipe.getCuisine());
        System.out.println("main: " + recipe.getMainIngredients());
        System.out.println("addons: " + recipe.getAddonIngredients());
        System.out.println("sides: " + recipe.getSideIngredients());
        System.out.println("prep: " + recipe.getPrepTime());
        System.out.println("cook: " + recipe.getCookTime());
    }
    
    private void quit() {
        rb.quit();
        
        running = false;
    }
    
}