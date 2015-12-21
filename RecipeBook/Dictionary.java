import java.util.*;

public class Dictionary {
    
    private HashMap<String, LinkedList<String>> dictionary = new HashMap<>();
  
    private LinkedList<String> meat = new LinkedList<>();
    private LinkedList<String> dairy = new LinkedList<>();
    private LinkedList<String> seafood = new LinkedList<>();
    private LinkedList<String> shellfish = new LinkedList<>();
    
    /**
     * Default constructor class. Builds up the dictionary, adding the different ingredient
     * types to the corrosponding arrays and adding them to the dictionary.
     */
    public Dictionary() {
        meat.add("beef");
        meat.add("chicken");
        meat.add("lamb");
        meat.add("goat");
        
        seafood.add("salmon");
        seafood.add("shrimp");
        seafood.add("scallops");
        
        shellfish.add("shrimp");
        shellfish.add("scallops");
        
        dairy.add("cheese");
        dairy.add("milk");
        
        dictionary.put("meat", meat);
        dictionary.put("dairy", dairy);
        dictionary.put("seafood", seafood);
        dictionary.put("shellfish", shellfish);
    }
    
    /**
     * Retrieves a linked list containing all of the items that fall into a specific category.
     * 
     * @param key The key of the category of items to be retrieved.
     * 
     * @return Returns the linked list containing the items in that category in the dictionary.
     */
    public LinkedList<String> getEntry(String key) {
        return dictionary.get(key);
    }
    
}