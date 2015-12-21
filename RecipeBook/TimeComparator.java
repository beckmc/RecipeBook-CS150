import java.util.*;

public class TimeComparator implements Comparator<Recipe> {
    /**
     * A comparator that compares the times of two recipes. Sorts them from lowest to highest.
     * 
     * @param o1 The first recipe
     * @param o2 The second recipe
     * 
     * @return The difference of the two times.
     */
    public int compare(Recipe o1, Recipe o2) {
            if (o1.getTotalTime() - o2.getTotalTime() != 0) {
                return o1.getTotalTime() - o2.getTotalTime();
            } else {
                return -1;
            }
    }
}