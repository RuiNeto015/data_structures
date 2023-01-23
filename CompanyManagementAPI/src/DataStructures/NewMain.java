package DataStructures;

import AdaptedDataStructures.AdaptedNetwork;

/**
 *
 * @author Simão
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdaptedNetwork<Integer> network = new AdaptedNetwork<>();
        network.addVertex(10);
        network.addVertex(20);
        network.addVertex(30);
        network.addVertex(40);
        network.addVertex(50);
        network.addVertex(60);
        network.addVertex(70);
        network.addEdge(10, 20, 10);
        network.addEdge(10, 40, 20);
        network.addEdge(10, 30, 5);
        network.addEdge(20, 30, 15);
        network.addEdge(20, 70, 50);
        network.addEdge(30, 50, 5);
        network.addEdge(50, 70, 5);
        network.addEdge(70, 60, 15);
        network.addEdge(60, 30, 30);
        network.addEdge(60, 40, 25);
        network.addEdge(40, 30, 5);
        
        double shortestPath = network.shortestPathWeight(10, 50);
        System.out.println(shortestPath);
        System.out.println("breakpoint");
    }

}
