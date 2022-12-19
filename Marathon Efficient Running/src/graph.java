import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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

        HashSet<vertex> visited = new HashSet<vertex>();
        PriorityQueue<vertex> Qu = new PriorityQueue<vertex>(new myComparator());

        vertex start = keys.get(startS);
        vertex finish = keys.get(finishS);

        start.distanceStart = 0;
        Qu.add(start);

        while (visited.size() != vertices) {
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

        int last = finish.distanceStart;
        for (Map.Entry<String, vertex> set : keys.entrySet()) {
            vertex answer = set.getValue();
            answer.distanceStart = Integer.MAX_VALUE;
        }

        return last;

    }

    public int MST(List<String> flags) {
        int flagNum = flags.size();

        if (flags.size() == 0) {
            return -1;
        }

        ArrayList<String> completedFlags = new ArrayList<String>(); 
        vertex start = keys.get(flags.get(0));
        start.distanceStart = 0;
        completedFlags.add(start.name);

        
        HashSet<vertex> visited = new HashSet<vertex>();
        PriorityQueue<vertex> Qu = new PriorityQueue<vertex>(new myComparator());
        boolean controler = true;
        boolean controler2 = true;
        int totalCost = 0;

        while (controler2) {
            if(completedFlags.size() == flagNum){
                controler2 = false;
                break;
            }


            for (String flag : completedFlags) {
                Qu.add(keys.get(flag));
                keys.get(flag).distanceStart = 0;

            }


            while (controler) {
                if (Qu.isEmpty() || visited.size() == vertices) {

                    controler = false;
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
                            if (way + current.distanceStart <= next.distanceStart) {

                                if(Qu.contains(next)){
                                    Qu.remove(next);
                                    next.distanceStart = way + current.distanceStart;
                                    Qu.add(next);
                                }
                                else{
                                    next.distanceStart = way + current.distanceStart;
                                    Qu.add(next);
                                }


                            }

                        }

                    }
                    if (flags.contains(current.name)) {
                        if (!completedFlags.contains(current.name)) {
                            totalCost += current.distanceStart;
                            completedFlags.add(current.name);
                            controler = false;
                            break;
                        }
                    }

                }

            }

            controler = true;
            Qu.clear();
            visited.clear();
        }

        return totalCost;

    }

}
