import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {

    private final Map<String, Set<String>> adjacencyList = new LinkedHashMap<>();

    public boolean addLocation(String location) {
        String name = cleanName(location);
        if (name == null || adjacencyList.containsKey(name)) {
            return false;
        }

        adjacencyList.put(name, new LinkedHashSet<>());
        return true;
    }

    public boolean removeLocation(String location) {
        String name = cleanName(location);
        if (name == null || !adjacencyList.containsKey(name)) {
            return false;
        }

        adjacencyList.remove(name);
        for (Set<String> neighbours : adjacencyList.values()) {
            neighbours.remove(name);
        }
        return true;
    }

    public boolean addConnection(String firstLocation, String secondLocation) {
        String first = cleanName(firstLocation);
        String second = cleanName(secondLocation);
        if (first == null || second == null || first.equals(second)
                || !adjacencyList.containsKey(first) || !adjacencyList.containsKey(second)) {
            return false;
        }

        if (!adjacencyList.get(first).add(second)) {
            return false;
        }
        adjacencyList.get(second).add(first);
        return true;
    }

    public boolean removeConnection(String firstLocation, String secondLocation) {
        String first = cleanName(firstLocation);
        String second = cleanName(secondLocation);
        if (first == null || second == null || !adjacencyList.containsKey(first)
                || !adjacencyList.containsKey(second)
                || !adjacencyList.get(first).contains(second)) {
            return false;
        }

        adjacencyList.get(first).remove(second);
        adjacencyList.get(second).remove(first);
        return true;
    }

    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations have been added.");
            return;
        }

        for (Map.Entry<String, Set<String>> entry : adjacencyList.entrySet()) {
            String neighbours = entry.getValue().isEmpty()
                    ? "No connections"
                    : String.join(", ", entry.getValue());
            System.out.println(entry.getKey() + " -> " + neighbours);
        }
    }

    public List<String> breadthFirstTraversal(String startingLocation) {
        String start = cleanName(startingLocation);
        if (start == null || !adjacencyList.containsKey(start)) {
            return Collections.emptyList();
        }

        List<String> visitOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> pending = new ArrayDeque<>();
        visited.add(start);
        pending.add(start);

        while (!pending.isEmpty()) {
            String current = pending.remove();
            visitOrder.add(current);

            for (String neighbour : adjacencyList.get(current)) {
                if (visited.add(neighbour)) {
                    pending.add(neighbour);
                }
            }
        }

        return visitOrder;
    }

    private String cleanName(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }
}