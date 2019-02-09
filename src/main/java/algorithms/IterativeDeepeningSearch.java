package algorithms;

import models.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IterativeDeepeningSearch {
    public static void runDepthFirstSearchWithDepth(Node startingNode, int maxDepth) {
        LinkedList<Node> queue = new LinkedList<>();
        List<Node> visitedNodes = new ArrayList<>();

        queue.add(startingNode);

        while (!queue.isEmpty()) {
            // Dequeue
            Node node = queue.pollLast();

            // Mark node as visited
            visitedNodes.add(node);

            if (node.getCurrentDepth() < maxDepth) {
                List<Node> children = node.generateSuccessors();
                for (Node child : children) {
                    if (!queue.contains(child) && !visitedNodes.contains(child)) {
                        queue.add(child);
                    }
                }
            }
        }
    }
}
