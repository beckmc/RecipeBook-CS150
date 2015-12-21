import java.util.*;

public class Recipe {
    
    private String cuisine;
    private String region; //Asian, Middle Eastern, South Asian, or N/A
    private String name;
    private String type;
    private String ingredients; 
    private String mainIngredients;
    private String addonIngredients;
    private String sideIngredients;
    
    private int prepTime;
    private int cookTime;
    
    private Boolean hasSeafood = null;
    private Boolean hasMeat = null;
    private Boolean hasDairy = null;
    private Boolean isVegan = null;
    private Boolean isVegetarian = null;
    private Boolean hasShellfish = null;
    
    private Dictionary dictionary = new Dictionary();
    
    /**
     * Default constructor. Builds the recipe with the information given.
     * 
     * @param cuisine The cuisine for the dish.
     * @param name The name of the dish.
     * @param type The type of the dish.
     * @param mainIngredients The main ingredients for the dish.
     * @param addonIngredients The addon ingredients for the dish.
     * @param sideIngredients The side ingredients for the dish.
     * @param prepTime The prep time for the dish.
     * @param cookTime The cook time for the dish.
     */
    public Recipe(String cuisine, String name, String type, String mainIngredients, String addonIngredients, String sideIngredients, int prepTime, int cookTime) {
        this.cuisine = cuisine;
        this.name = name;
        this.type = type;
        this.mainIngredients = mainIngredients;
        this.addonIngredients = addonIngredients;
        this.sideIngredients = sideIngredients;
        this.ingredients = mainIngredients + " " + addonIngredients + " " + sideIngredients;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        
        setRegion();
        checkSeafood();
        checkShellfish();
        checkMeat();
        checkDairy();
        checkVegetarian();
        checkVegan();
    }
    
    private void setRegion() {
        if (cuisine.equals("Korean") || cuisine.equals("Chinese")) {
            region = "Asian";
        } else if (cuisine.equals("Indian") || cuisine.equals("Pakistan")) {
            region = "SouthEastern";
        } else if (cuisine.equals("Greek") || cuisine.equals("Turkish")) {
            region = "MiddleEastern";
        } else {
            region = "None";
        }
    }
    
    private void checkSeafood() {
        LinkedList<String> seafood = dictionary.getEntry("seafood");
        while (seafood.size() > 0) {
            if (ingredients.contains(seafood.poll())) {
                hasSeafood = true;
            }
        }
        
        if (hasSeafood == null) {
            hasSeafood = false;
        }
    }
    
    private void checkShellfish() {
        LinkedList<String> shellfish = dictionary.getEntry("shellfish");
        while (shellfish.size() > 0) {
            if (ingredients.contains(shellfish.poll())) {
                hasShellfish = true;
            }
        }
        
        if (hasShellfish == null) {
            hasShellfish = false;
        }
    }
    
    private void checkMeat() {
        LinkedList<String> meats = dictionary.getEntry("meat");
        while (meats.size() > 0) {
            if (ingredients.contains(meats.poll())) {
                hasMeat = true;
            }
        }
        
        if (hasMeat == null) {
            hasMeat = false;
        }
    }
    
    private void checkDairy() {
        if (ingredients.contains("cheese") || ingredients.contains("milk")) {
            hasDairy = true;
        } else {
            hasDairy = false;
        }
        
        LinkedList<String> dairy = dictionary.getEntry("dairy");
        while (dairy.size() > 0) {
            if (ingredients.contains(dairy.poll())) {
                hasDairy = true;
            }
        }
        
        if (hasDairy == null) {
            hasDairy = false;
        }
    }
    
    private void checkVegetarian() {
        if (!hasSeafood || !hasMeat) {
            isVegetarian = true;
        } else {
            isVegetarian = false;
        }
    }
    
    private void checkVegan() {
        if (isVegetarian && !hasDairy && !ingredients.contains("eggs")) {
            isVegan = true;
        } else {
            isVegan = false;
        }
    }
    
    public String getRegion() {
        return region;
    }
    
    public Boolean contains(String ingredient) {
        if (ingredients.contains(ingredient)) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getTotalTime() {
        return prepTime + cookTime;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCuisine() {
        return cuisine;
    }
    
    public String getType() {
        return type;
    }
    
    public String getMainIngredients() {
        return mainIngredients;
    }
    
    public String getAddonIngredients() {
        return addonIngredients;
    }
    
    public String getSideIngredients() {
        return sideIngredients;
    }
    
    public String getIngredients() {
        return ingredients;
    }
    
    public int getPrepTime() {
        return prepTime;
    }
    
    public int getCookTime() {
        return cookTime;
    }
    
    public Boolean hasSeafood() {
        return hasSeafood;
    }
    
    public Boolean hasMeat() {
        return hasMeat;
    }
    
    public Boolean hasDairy() {
        return hasDairy;
    }
    
    public Boolean isVegan() {
        return isVegan;
    }
    
    public Boolean isVegetarian() {
        return isVegetarian;
    }
    
    public Boolean hasShellfish() {
        return hasShellfish;
    }
    
}