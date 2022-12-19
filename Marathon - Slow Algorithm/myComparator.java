import java.util.Comparator;


public class myComparator implements Comparator<vertex> {

    
    public int compare(vertex s1, vertex s2) {
        if (s1.distanceStart < s2.distanceStart)
            return -1;
        else if (s1.distanceStart > s2.distanceStart)
            return 1;
        return 0;
    }

}