import java.util.*;

public class TimeComparatorReversed implements Comparator<ArrayList> {
    /**
     * A comparator that compares the times of two recipes. Sorts them from highest to lowest.
     * 
     * @param o1 The first recipe
     * @param o2 The second recipe
     * 
     * @return The difference of the two times.
     */
    public int compare(ArrayList o1, ArrayList o2) {
        if ((int)o2.get(0) - (int)o1.get(0) != 0) {
            return (int)o2.get(0) - (int)o1.get(0);
        } else {
                return -1;
        }
    }
}