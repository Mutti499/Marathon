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


    
}
