import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]) throws IOException {
        long time1 = System.currentTimeMillis();

        ArrayList<String> inputLines = new ArrayList<String>();

        FileReader in = new FileReader("C:\\Users\\Lenovo\\Desktop\\smallCases\\input\\inp1.txt");
        //FileReader in = new FileReader("C:\\Users\\Lenovo\\Desktop\\largeCases\\largeCases\\input\\stress2.txt");


        BufferedReader br = new BufferedReader(in);

        String givenInputLine;
        while ((givenInputLine = br.readLine()) != null) {
            inputLines.add(givenInputLine);
        }
        in.close();

        int lineNum = Integer.parseInt(inputLines.get(0));
        graph Graph = new graph(lineNum);

        String[] startAndFinish = inputLines.get(2).split(" ");

        String start = startAndFinish[0];
        String finish = startAndFinish[1];

        String[] flags = inputLines.get(3).split(" ");
        List<String> flags2 = new ArrayList<String>(Arrays.asList(flags));

        for (int i = 0; i < lineNum; i++) {
            String[] line = inputLines.get(4 + i).split(" ");
            LinkedList<edge> neighbours = new LinkedList<edge>();
            String name = line[0];
            vertex newW = new vertex(name);

            for (int x = 0; x < (line.length - 1) / 2; x++) {

                int dist = Integer.parseInt(line[2 + 2 * x]);
                edge way = new edge(name, line[(1 + 2 * x)], dist);
                neighbours.add(way);
            }

            Graph.keys.put(name, newW);
            Graph.adjacency.put(newW, neighbours);

        }
        for (int i = 0; i < lineNum; i++) {
            String[] line = inputLines.get(4 + i).split(" ");

            String toAdd = line[0];

            for (int x = 0; x < (line.length - 1) / 2; x++) {
                String slot = line[(1 + 2 * x)];
                int dist = Integer.parseInt(line[2 + 2 * x]);
                vertex slotW = Graph.keys.get(slot);

                edge way = new edge(slot, toAdd, dist);
                Graph.adjacency.get(slotW).add(way);

            }

        }

        System.out.println(Graph.adjacency);

        // System.out.println(Graph.keys);
        // System.out.println();
        // System.out.println(Graph.adjacency);
        int result1 = Graph.dijkstra(start,finish);
        System.out.println(result1);
        System.out.println("time 1 :" + (System.currentTimeMillis() - time1));
        System.out.println();
        
        graph Graph2 = new graph(flags.length);
        int result2 = Integer.MIN_VALUE;
        for (String flagName : flags) {
            HashMap<vertex, Integer> distancesToFlag = Graph.dijkstra2(flagName);
            LinkedList<edge> neighbours = new LinkedList<edge>();
            if(distancesToFlag == null){ result2 = -1;
            break;}
            //System.out.println(flagName + " in sonuclari :" + distancesToFlag);
            String name = flagName;
            vertex newW = new vertex(name);

            for (Map.Entry<vertex, Integer> entry : distancesToFlag.entrySet()) {
                int distance = entry.getValue();
                String to = entry.getKey().name;
                if (distance != 0 && flags2.contains(to)) {
                    edge way = new edge(name, to, distance);
                    neighbours.add(way);
                }

            }

            Graph2.keys.put(name, newW);
            Graph2.adjacency.put(newW, neighbours);

        }

        // System.out.println();

        // for (Map.Entry<vertex, LinkedList<edge>> entry : Graph2.adjacency.entrySet()) {
        //     System.out.print(entry.getKey() + " ");
        //     System.out.println(entry.getValue());
        // }
        System.out.println("time 2.Graph :" + (System.currentTimeMillis() - time1));
        if(result2 == -1){
            System.out.println(-1);
        }else{
            System.out.println(Graph2.MST(flags[0]));

        }
        //long time3 = System.currentTimeMillis();

        System.out.println("time 2.MST :" +  (System.currentTimeMillis() - time1));

    }
}
