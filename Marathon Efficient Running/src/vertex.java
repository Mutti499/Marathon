
public class vertex{
    String name;
    // int distanceToSource;
    // boolean visited =  false;
    boolean flag = false;
    int distanceStart = Integer.MAX_VALUE;

    public vertex(String name) {
        this.name = name;
    }

    // @Override
    // public String toString() {
    //     return "vertex [name=" + name + ", distanceStart=" + distanceStart + "]";
    // }

    @Override
    public String toString() {
        return name + " : " + distanceStart;
       // return "vertex name=" + name ;
    }

}

