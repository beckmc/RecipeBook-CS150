import java.util.*;

public class RecipeSearch {
    
    private Boolean hasSeafood = null;
    private Boolean hasMeat = null;
    private Boolean hasDairy = null;
    private Boolean isVegan = null;
    private Boolean isVegetarian = null;
    private Boolean hasShellfish = null;
    
    private Boolean contradiction = false;
    
    private int totalTime;
    
    private String includedItems;
    private String excludedItems;
    private String cuisine;
    
    RecipeBook rb;
    
    /**
     * Default constructor. Sets the recipe book referenced to be the same as the one created when the program launched.
     */
    public RecipeSearch(RecipeBook rb) {
        this.rb = rb;
    }
    
    private Boolean checkIncluded(String item, Boolean itemBoolean) {
        if (includedItems.contains(item)) {
            itemBoolean = true;
            includedItems = includedItems.replace(item, "");
        }
            
        return itemBoolean;
    }
    
    private Boolean checkExcluded(String item, Boolean itemBoolean) {
        if (excludedItems.contains(item)) {
            if (itemBoolean != null) {
                contradiction = true;
            }
            
            itemBoolean = false;
            excludedItems = excludedItems.replace(item, "");
        }
            
        return itemBoolean;
    }
    
    private ArrayList<Recipe> iterateTree() {
        TreeSet <Recipe> recipeBookTime = rb.getRecipeBookTime();
        ArrayList <Recipe> potentialRecipeList = new ArrayList<>();
        
        Iterator iterator = recipeBookTime.iterator();
        
        while (iterator.hasNext()) {
            Boolean canAdd = true;
            
            Recipe next = (Recipe)iterator.next();
            
            if (hasSeafood != null && next.hasSeafood() != hasSeafood) {
                canAdd = false;
            }
            
            if (hasMeat != null && next.hasMeat() != hasMeat) {
                canAdd = false;
            }
            
            if (hasDairy != null && next.hasDairy() != hasDairy) {
                canAdd = false;
            }
            
            if (isVegan != null && next.isVegan() != isVegan) {
                canAdd = false;
            }
            
            if (isVegetarian != null && next.isVegetarian() != isVegetarian) {
                canAdd = false;
            }
            
            if (hasShellfish != null && next.hasShellfish() != hasShellfish) {
                canAdd = false;
            }
            
            String[] includedItemsTemp = includedItems.split(" ");
            for (int i = 0; i < includedItemsTemp.length; i++) {
                if (!next.getIngredients().contains(includedItemsTemp[i])){
                    canAdd = false;
                }
            }
            
            
            String[] excludedItemsTemp = excludedItems.split(" ");
            for (int i = 0; i < excludedItemsTemp.length; i++) {
                if (next.getIngredients().contains(excludedItemsTemp[i]) && excludedItemsTemp[i].length() > 0){
                    canAdd = false;
                }
            }
            
            if (cuisine.length() > 0) {
                if (!next.getCuisine().equals(cuisine) && !next.getRegion().equals(cuisine)) {
                    canAdd = false;
                }
            }
            
            if (next.getTotalTime() > totalTime) {
                canAdd = false;
            }
            
            if (canAdd && !contradiction) {
                potentialRecipeList.add(next);
            }
        }
        
        return potentialRecipeList;
    }
    
    /**
     * Given a set of parameters, finds all recipes from the data set that fall within the parameters.
     * 
     * @param totalTime The highest time the recipe can take.
     * @param includedItems A string containing all items to be included.
     * @param excludedItems A string containing all items to be excluded.
     * @param cuisine The type of cuisine for the dish.
     * 
     * @return An array list containing all recipes that fit the parameters
     */
    public ArrayList<Recipe> search(int totalTime, String includedItems, String excludedItems, String cuisine) {
        this.totalTime = totalTime;
        this.includedItems = includedItems;
        this.excludedItems = excludedItems;
        this.cuisine = cuisine;

        hasSeafood = checkIncluded("seafood", hasSeafood);
        hasMeat = checkIncluded("meat", hasMeat);
        hasDairy = checkIncluded("dairy", hasDairy);
        isVegan = checkIncluded("vegan", isVegan);
        isVegetarian = checkIncluded("vegetarian", isVegetarian);
        hasShellfish = checkIncluded("shellfish", hasShellfish);
        
        hasSeafood = checkExcluded("seafood", hasSeafood);
        hasMeat = checkExcluded("meat", hasMeat);
        hasDairy = checkExcluded("dairy", hasDairy);
        isVegan = checkExcluded("vegan", isVegan);
        isVegetarian = checkExcluded("vegetarian", isVegetarian);
        hasShellfish = checkExcluded("shellfish", hasShellfish);
        
        return iterateTree();
    }
    
}