import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class project4 {
    public static void main(String args[]) throws IOException {

        FileReader in = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(in);

        FileOutputStream foutput = new FileOutputStream(args[1]);
        System.setOut(new PrintStream(foutput));



        ArrayList<String> inputLines = new ArrayList<String>();

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


        int result1 = Graph.dijkstra(start,finish);
        System.out.println(result1);

        int result2 = Graph.MST(flags2);       
        System.out.println(result2);
    }
}


