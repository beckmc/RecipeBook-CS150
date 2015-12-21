import java.util.*;
import java.io.*;

public class MakeRecipePlan {
    
    private TimeComparatorReversed comp = new TimeComparatorReversed();
    private TreeSet<ArrayList> possibilities = new TreeSet<>(comp);
    
    private ArrayList<Recipe> plan = new ArrayList<>();
    
    /**
     * Given lists of all recipes that work with the parameters for the meal plan, this makes all possible combinations
     * of them (ignoring duplicates on time) and returns an array list with the final meal plan.
     * 
     * @param goodRecipesEntree A list of entrees that fit the parameters for the meal plan
     * @paramgoodRecipesSalad A list of salads that fit the parameters for the meal plan
     * @paramgoodRecipesAppetizer A list of appetizers that fit the parameters for the meal plan
     * @param totalTime The highest total time the recipes can take to make
     * 
     * @return An array list containing the recipes for the plan, or null if none could be found.
     */
    public ArrayList<Recipe> planRecipes(ArrayList<Recipe> goodRecipesEntree, ArrayList<Recipe> goodRecipesSalad, ArrayList<Recipe> goodRecipesAppetizer, int totalTime) {
        try{
            for (int i = 0; i < goodRecipesEntree.size(); i++) {
                for (int j = 0; j < goodRecipesSalad.size(); j++) {
                    for (int k = 0; k < goodRecipesAppetizer.size(); k++) {
                        for (int l = 0; l < goodRecipesAppetizer.size(); l++) {
                            int addedTime = add(goodRecipesEntree.get(i), goodRecipesSalad.get(j), goodRecipesAppetizer.get(k), goodRecipesAppetizer.get(l));
                            if (addedTime == totalTime) {
                                iterate(totalTime);
                                return plan;
                            }
                        }
                    }
                }
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        iterate(totalTime);
        
        if (plan.size() == 0) {
            return null;
        }
        
        return plan;
    }
    
    private int add(Recipe entree, Recipe salad, Recipe appetizer1, Recipe appetizer2) {
        ArrayList<Object> potentialPlan = new ArrayList<>();
        
        int prepEntree = entree.getPrepTime();
        int prepSalad = salad.getPrepTime();
        int prepAppetizer1 = appetizer1.getPrepTime();
        int prepAppetizer2 = appetizer2.getPrepTime();
        int longestCookTime = Math.max(entree.getCookTime(), Math.max(salad.getCookTime(), Math.max(appetizer1.getCookTime(), appetizer2.getCookTime())));
        
        int totalTime = prepEntree + prepSalad + prepAppetizer1 + prepAppetizer2 + longestCookTime;
        
        potentialPlan.add(totalTime);
        potentialPlan.add(entree);
        potentialPlan.add(salad);
        potentialPlan.add(appetizer1);
        potentialPlan.add(appetizer2);
            
        possibilities.add(potentialPlan);
        
        return totalTime;
    }
    
    private void iterate(int totalTime) {
        Iterator iterator = possibilities.iterator();
        
        boolean canAdd = false;
        
        while (!canAdd && iterator.hasNext()) {
            
            ArrayList next = (ArrayList)iterator.next();
            
            if ((int)next.get(0) <= totalTime) {
                canAdd = true;
            }
            
            if (canAdd) {
                plan.add((Recipe)next.get(1));
                plan.add((Recipe)next.get(2));
                plan.add((Recipe)next.get(3));
                plan.add((Recipe)next.get(4));
            }
        }
    }
    
}