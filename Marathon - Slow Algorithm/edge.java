
public class edge{
    String from;
    String destination;
    int length;

    public edge(String from, String destination, int length) {
        this.from = from;
        this.destination = destination;
        this.length = length;
    }

    @Override
    public String toString() {
        return "edge [from=" + from + " to " + destination + ", length=" + length + "]";
        //return "to " + destination + ", length=" + length + " ";
    }


    // @Override
    // public int compare(edge o1, edge o2) {
    //     if(o1.length < o2.length){
    //         return -1;
    //     }
    //     if(o1.length > o2.length){
    //         return 1;
    //     }
    //     return 0;
    // }

    
}
