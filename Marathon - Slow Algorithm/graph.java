import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;


public class graph {

    int vertices;

    HashMap<String, vertex> keys = new HashMap<String, vertex>();
    HashMap<vertex, LinkedList<edge>> adjacency = new HashMap<vertex, LinkedList<edge>>();

    graph(int size) {
        this.vertices = size;

    }

    public int dijkstra(String startS, String finishS) {

        HashSet<vertex> visited = new HashSet<vertex>(); // vertex ziyaret edildiyse buraya koy
        PriorityQueue<vertex> Qu = new PriorityQueue<vertex>(new myComparator());

        vertex start = keys.get(startS);
        vertex finish = keys.get(finishS);


        start.distanceStart = 0;
        Qu.add(start);

        while (visited.size() != vertices) {
            // System.out.println(Qu);
            if (Qu.isEmpty()) {
                return -1;
            }
            if (visited.contains(Qu.peek())) {
                Qu.poll();
                continue;
            } else {
                vertex current = Qu.poll();
                visited.add(current);

                for (edge edge : adjacency.get(current)) {
                    int way = edge.length;
                    String name = edge.destination;
                    vertex next = keys.get(name);

                    if (visited.contains(next)) {
                        continue;
                    } else {
                        if (way + current.distanceStart < next.distanceStart) {
                            next.distanceStart = way + current.distanceStart;
                            Qu.add(next);

                        }
                    }

                }

            }

        }

        int last =   finish.distanceStart;  
        for (Map.Entry<String, vertex> set : keys.entrySet()) {
            vertex answer = set.getValue();
            //System.out.println(answer.name   +" "+answer.distanceStart);
            answer.distanceStart = Integer.MAX_VALUE;
        }

        return last;

    }

    public HashMap<vertex, Integer> dijkstra2(String startS) {

        HashMap<vertex, Integer> distances = new HashMap<vertex, Integer>();
        HashSet<vertex> visited = new HashSet<vertex>(); // vertex ziyaret edildiyse buraya koy
        PriorityQueue<vertex> Qu = new PriorityQueue<vertex>(new myComparator());

        vertex start = keys.get(startS);

        for (Map.Entry<String, vertex> entry : keys.entrySet()) {
            vertex value = entry.getValue();
            distances.put(value, Integer.MAX_VALUE);
        }

        start.distanceStart = 0;
        Qu.add(start);

        while (visited.size() != vertices) {
            // System.out.println(Qu);
            if (Qu.isEmpty()) {
                return null;
            }
            if (visited.contains(Qu.peek())) {
                Qu.poll();
                continue;
            } else {
                vertex current = Qu.poll();
                visited.add(current);

                for (edge edge : adjacency.get(current)) {
                    int way = edge.length;
                    String name = edge.destination;
                    vertex next = keys.get(name);

                    if (visited.contains(next)) {
                        continue;
                    } else {
                        if (way + current.distanceStart < next.distanceStart) {

                            next.distanceStart = way + current.distanceStart;
                            if(Qu.contains(next)){
                                Qu.remove(next);
                            }

                            Qu.add(next);

                        }
                    }

                }

            }

        }
        // System.out.println();
        // System.out.println();

        // System.out.println();

        // for (Map.Entry<String, vertex> set : keys.entrySet()){
        // System.out.println(set.getValue());
        // }

        for (Map.Entry<String, vertex> set : keys.entrySet()) {
            vertex answer = set.getValue();
            distances.put(answer, answer.distanceStart);
            answer.distanceStart = Integer.MAX_VALUE;
        }

        return distances;

    }

    public int MST(String startS) {

        HashSet<vertex> visited = new HashSet<vertex>(); // vertex ziyaret edildiyse buraya koy
        ArrayList<edge> mstArray = new ArrayList<edge>(); //en kisa edgeleri burda topla
        int totalCost = 0; //edgelerijn toplam uzunlugu
        PriorityQueue<edge> Qu = new PriorityQueue<edge>(new myComparator2()); // edgelerin boyutlarini kisitlayan bir comparator

        vertex start = keys.get(startS);//kodun baslayacagi RASTGELE bir vertex (node)
        Qu.add(new edge(startS, startS, 0)); // Quya calismasi icin random bir edge atadim. Baslangic kednisine 0 uzaklikta oldugu icin onu koydum
        for (edge neigh : adjacency.get(start)) {
            Qu.add(neigh);//Komsularini queue ya attim
        }
        visited.add(start);// visited yaptim ki geri donmesin kod


        while (mstArray.size() != vertices - 1) {// 
            if (Qu.isEmpty()) {
                return -1;
            }
            edge current = Qu.poll();
            String dest = current.destination;
            vertex destination = keys.get(dest);

            
            if (visited.contains(destination))
                continue;
            else {
                visited.add(destination);
                mstArray.add(current);
                totalCost += current.length;

                for (edge neigh : adjacency.get(keys.get(dest))) {
                    vertex destin = keys.get(neigh.destination);
                    if (!visited.contains(destin)) {
                        Qu.add(neigh);
                    }
                }

            }

        }

        return totalCost;

    }

}