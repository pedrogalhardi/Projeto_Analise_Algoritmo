/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package caixeiroviajante;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author pedro
 */
public class backTracking {

    public static long cost = Long.MAX_VALUE;
    public static long costBest = Long.MAX_VALUE;
    private static Random random = new Random();

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("src/caixeiroviajante/test.txt");

        Graph graph = null;
        int nVertex = 0;

        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0) {
                nVertex = Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
            } else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String lineSubstring = line.substring(line.indexOf(" "), line.length());
                String splits[] = lineSubstring.split(";");
                for (String part : splits) {
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    graph.setEdge(oriVertex, targetVertex, weight);
                }
            }
        }

        int initialNode = 19;
        int vertexSize = graph.getVertexSize();
        int[] path = new int[vertexSize];
        boolean[] av = new boolean[vertexSize];

        for (int i = 0; i < vertexSize; i++) {
            av[i] = true;
            path[i] = -1;
        }

        path[0] = initialNode;
        av[initialNode] = false;
        backTracking(initialNode, path, av, 1, graph, 0);
    }

    public static int minRemainingCost(boolean av[], Graph graph) {
        int minRemainingCost = Integer.MAX_VALUE;

        for (int i = 0; i < av.length; i++) {
            if (av[i]) {
                int currentMin = Integer.MAX_VALUE;
                int vertexSize = graph.getVertexSize();
                int edgeWeight;

                for (int j = 0; j < vertexSize; j++) {
                    if (av[j]) {
                        edgeWeight = graph.getEdgeWeight(i, j);
                        currentMin = Math.min(currentMin, edgeWeight);
                    }
                }

                minRemainingCost = Math.min(minRemainingCost, currentMin);
            }
        }

        return minRemainingCost;
    }

    public static void backTracking(int node, int path[], boolean av[], int pos, Graph graph, int cost) {

        int vertexSize = graph.getVertexSize();
        if (pos == vertexSize) {
            int pathCost = cost + graph.getEdgeWeight(node, path[0]);

            if (pathCost < costBest && pathCost > 0) {
                costBest = pathCost;
                StringBuilder result = new StringBuilder();

                for (int i = 0; i < (pos - 1); i++) {
                    //result.append(path[i]).append("\t");
                }

                result.append(path[pos - 1]).append("\t-\t").append(pathCost).append("\n");
                System.out.print(result.toString());
            }

        } else {
            ArrayList<Integer> adj = graph.getAdjVertex(node);

            for (int i : adj) {
                if (av[i]) {
                    int edgeWeight = graph.getEdgeWeight(node, i);
                    cost += edgeWeight;
                    if (cost < costBest) {
                        av[i] = false;
                        path[pos] = i;
                        pos++;
                        backTracking(i, path, av, pos, graph, cost);
                        pos--;
                        av[i] = true;
                    }
                    cost -= edgeWeight;
                }
            }
        }
    }

}
