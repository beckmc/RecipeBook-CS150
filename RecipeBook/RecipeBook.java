import java.io.*;
import java.util.*;

public class RecipeBook {
    
    private TimeComparator compTime = new TimeComparator();
    private NameComparator compName = new NameComparator();
    private TreeSet<Recipe> recipeBookTime = new TreeSet<>(compTime);
    private TreeMap<String, Recipe> recipeBookName = new TreeMap<>(compName);
    
    /**
     * Default constructor. Reads a file and populates a tree based on the data within the file.
     * 
     * @param dataFile The data file to be read.
     */
    public RecipeBook(File dataFile) {
        readData(dataFile);
    }
    
    private void readData(File dataFile) {
        try {
            Scanner sc = new Scanner(dataFile);
            
            while (sc.hasNextLine()) {
                String name = null;
                String type = null;
                String cuisine = null;
                String mainIngredients = null;
                String addonIngredients = null;
                String sideIngredients = null;
                int prepTime = 0;
                int cookTime = 0;
                
                name = sc.nextLine();
                String nameTemp[] = name.split (":");
                name = nameTemp[1].trim();
                
                type = sc.nextLine();
                String typeTemp[] = type.split (":");
                type = typeTemp[1].trim();
                
                cuisine = sc.nextLine();
                String cuisineTemp[] = cuisine.split (":");
                cuisine = cuisineTemp[1].trim();
                
                mainIngredients = sc.nextLine();
                String mainIngredientsTemp[] = mainIngredients.split (":");
                mainIngredients = mainIngredientsTemp[1].trim();
                
                addonIngredients = sc.nextLine();
                String addonIngredientsTemp[] = addonIngredients.split (":");
                addonIngredients = addonIngredientsTemp[1].trim();
                
                sideIngredients = sc.nextLine();
                String sideIngredientsTemp[] = sideIngredients.split (":");
                sideIngredients = sideIngredientsTemp[1].trim();
                
                String prepTimeString = sc.nextLine();
                String prepTimeStringTemp[] = prepTimeString.split (":");
                prepTimeString = prepTimeStringTemp[1].trim();
                prepTime = Integer.parseInt(prepTimeString);
                
                String cookTimeString = sc.nextLine();
                String cookTimeStringTemp[] = cookTimeString.split(":");
                cookTimeString = cookTimeStringTemp[1].trim();
                cookTime = Integer.parseInt(cookTimeString);
                
                Recipe addRecipe = new Recipe(cuisine, name, type, mainIngredients, addonIngredients, sideIngredients, prepTime, cookTime);
                
                addToTree(addRecipe);
                
                sc.nextLine();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    /**
     * Adds a recipe to the two trees of recipes.
     * 
     * @param addedRecipe The recipe to add to the trees.
     */
    public void addToTree(Recipe addedRecipe) {
        recipeBookTime.add(addedRecipe);
        recipeBookName.put(addedRecipe.getName(), addedRecipe);
    }
    
    /**
     * Checks to see if a recipe exists within the data sets.
     * 
     * @param name The name of the recipe to be found.
     * 
     * @return The recipe with the corresponding name, or null if it doesn't exist.
     */
    public Recipe findRecipe(String name) {
        Recipe removalRecipe = recipeBookName.get(name);
        
        return removalRecipe;
    }
    
    /**
     * Deletes a recipe when given a name.
     * 
     * @param name The recipe to be deleted.
     */
    public void deleteRecipe(String name) {
        Recipe removalRecipe = recipeBookName.get(name);
        
        recipeBookName.remove(name);
        recipeBookTime.remove(removalRecipe);
    }
    
    /**
     * Saves the data to a new file "newRecipes.txt" in the same format that it was obtained.
     */
    public void quit() {
        Iterator iterator = recipeBookTime.iterator();
        
        File outputFile = new File("newRecipes.txt");
        
        try {
            PrintWriter out = new PrintWriter(new FileWriter(outputFile));
            
            while (iterator.hasNext()) {
                Recipe recipe = (Recipe)iterator.next();
                
                out.println("name: " + recipe.getName());
                out.println("type: " + recipe.getType());
                out.println("cuisine: " + recipe.getCuisine());
                out.println("main: " + recipe.getMainIngredients());
                out.println("addons: " + recipe.getAddonIngredients());
                out.println("sides: " + recipe.getSideIngredients());
                out.println("prep: " + recipe.getPrepTime());
                out.println("cook: " + recipe.getCookTime());
                out.println();
            }
            out.close();
            
            outputFile.createNewFile();
            System.out.println("Recipe File Saved!");
        } catch (Exception e) {
            
        }
    }
    
    /**
     * Getter, returns the tree set sorted by time
     * 
     * @return The tree set sorted by time.
     */
    public TreeSet<Recipe> getRecipeBookTime() {
        return recipeBookTime;
    }
    
}