import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Main {
    private static Graph graph;

    public static void main(String[] args) {
        graph = new Graph();
        read_graph_data();

        Deque<Node> stack = new LinkedList<>();
        for (Node node : graph.getNodes()) {
            if (node.isNotVisited()) {
                dfs(node, stack);
            }
        }

        graph.clearVisited();
        graph.transpose();

        List<List<Integer>> listOfSCC = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            if (currentNode.isNotVisited()) {
                List<Integer> component = new ArrayList<>();
                dfs_collect(currentNode, component);
                listOfSCC.add(component);
            }
        }

        printSCC(listOfSCC);
    }

    private static void dfs(Node node, Deque<Node> stack) {
        node.setVisited(true);
        for (Node neighbor : graph.getNeighbors(node)) {
            if (neighbor.isNotVisited()) {
                dfs(neighbor, stack);
            }
        }
        stack.push(node);
    }

    private static void dfs_collect(Node node, List<Integer> component) {
        node.setVisited(true);
        for (Node neighbor : graph.getNeighbors(node)) {
            if (neighbor.isNotVisited()) {
                dfs_collect(neighbor, component);
            }
        }
        component.add(node.getId());
    }

    private static void read_graph_data() {
        try {
            File file = new File("src/main/java/graph.txt");
            Scanner scanner = new Scanner(file);
            ArrayList<int[]> edges = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine().trim();
                if (!data.isEmpty()) {
                    String[] parts = data.split("\\s+");
                    if (parts.length == 2) {
                        try {
                            int[] edge = {Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
                            edges.add(edge);
                        } catch (NumberFormatException e) {
                            // Skip lines that do not contain valid integers
                        }
                    }
                }
            }
            edges.removeFirst();
            graph.setEdges(edges);

            Set<Integer> uniqueValues = new HashSet<>();
            ArrayList<Node> nodes = new ArrayList<>();
            for (int[] edge : edges) {
                if (uniqueValues.add(edge[0])) {
                    nodes.add(new Node(edge[0], false));
                }
                if (uniqueValues.add(edge[1])) {
                    nodes.add(new Node(edge[1], false));
                }
            }
            nodes.sort(Comparator.comparingInt(Node::getId));
            graph.setNodes(nodes);
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void printSCC(List<List<Integer>> listOfSCC) {
        System.out.println("Grafen har " + listOfSCC.size() + " sterkt sammenhengende komponenter.");
        System.out.println("Komponentent   Noder i komponenten");
        int i = 1;
        for (List<Integer> component : listOfSCC) {
            component.sort(Comparator.naturalOrder());
            System.out.println(i + "              " + component);
            i++;
        }
    }
}


class Graph {
    private ArrayList<int[]> edges;
    private ArrayList<Node> nodes;

    Graph() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public void transpose() {
        edges = edges.stream()
                .map(edge -> new int[]{edge[1], edge[0]})
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void clearVisited() {
        nodes.forEach(node -> node.setVisited(false));
    }

    private ArrayList<int[]> getConnections(Node node) {
        return edges.stream()
                .filter(edge -> edge[0] == node.getId())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int[] edge : getConnections(node)) {
            neighbors.add(getNode(edge[1]));
        }
        return neighbors;
    }

    public Node getNode(int id) {
        return nodes.stream()
                .filter(node -> node.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(ArrayList<int[]> edges) {
        this.edges = edges;
    }
}

class Node {
    private final int id;
    private boolean visited;

    public Node(int id, boolean visited) {
        this.id = id;
        this.visited = visited;
    }

    public int getId() {
        return id;
    }

    public boolean isNotVisited() {
        return !visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
