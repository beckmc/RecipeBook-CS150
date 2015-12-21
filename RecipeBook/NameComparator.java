import java.util.*;

public class NameComparator implements Comparator<String> {
    /**
     * A comparator that compares the names of two recipes.
     * 
     * @param o1 The name of the first recipe
     * @param o2 The name of the second recipe
     * 
     * @return 1 if o1 comes earlier in the alphabet, -1 if it comes after, or 0 if they are the same.
     */
    public int compare(String o1, String o2) {
        if (!o1.equals(o2)) {
            return o1.compareTo(o2);
        } else {
            return 0;
        }
    }
}