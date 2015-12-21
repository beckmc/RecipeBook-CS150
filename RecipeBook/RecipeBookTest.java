import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class RecipeBookTest extends TestCase {
    //String cuisine, String name, String type, String mainIngredients, String addonIngredients, String sideIngredients, int prepTime, int cookTime) {
    public void testAddFind() {
        RecipeBook rb = new RecipeBook(null);
        
        Recipe addedRecipe = new Recipe("Chinese", "AddedRecipe", "Entree", "milk cheese", "beef", "eggs", 12, 3);
        
        rb.addToTree(addedRecipe);
        
        assertNotNull(rb.findRecipe("AddedRecipe"));
        
    }
    public void testDelete() {
        RecipeBook rb = new RecipeBook(null);
        
        Recipe addedRecipe = new Recipe("Chinese", "AddedRecipe", "Entree", "milk cheese", "beef", "eggs", 12, 3);
        
        rb.addToTree(addedRecipe);
        
        rb.deleteRecipe("AddedRecipe");
        
        assertNull(rb.findRecipe("AddedRecipe"));
    }
    
}
