package cinema.comparator;

import java.util.Comparator;

// de Java 5 à Java 8
public class TitleComparator implements Comparator<String> {
    @Override
    public int compare(String title1, String title2) {
        return title1.compareToIgnoreCase(title2); // TODO : retire pronom, gerer accents
    }
}
