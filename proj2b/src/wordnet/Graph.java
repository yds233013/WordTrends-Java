package wordnet;

import java.util.*;

public class Graph<T> {
    private Map<T, List<T>> adjacent;

    public Graph() {

        adjacent = new HashMap<>();
    }
    public void addVertex(T node) {

        adjacent.putIfAbsent(node, new ArrayList<>());
    }
    public void addcorner(T source, T target) {

        adjacent.get(source).add(target);
    }
    public List<T> getdescend(T node) {
        if (!adjacent.containsKey(node)) {
            return new ArrayList<>();
        }
        List<T> descend = new ArrayList<>();
        Queue<T> que = new LinkedList<>(adjacent.get(node));

        while (!que.isEmpty()) {
            T curr = que.poll();
            descend.add(curr);
            List<T> child = adjacent.get(curr);
            if (child != null) {
                que.addAll(child);
            }
        }
        return descend;
    }
}
