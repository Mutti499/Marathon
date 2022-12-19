import java.util.Comparator;

public class myComparator2 implements Comparator<edge> {

    @Override
    public int compare(edge o1, edge o2) {
        if(o1.length < o2.length){
            return -1;
        }
        if(o1.length > o2.length){
            return 1;
        }
        return 0;

    }
    
}
