package players;

import algorithms.IterativeDeepeningSearch;
import models.Board;
import models.MinMaxEnum;
import models.Node;

public class AI {
    public void makeMove(Board board, int numOfPlys) {
        this.buildGameTree(board, numOfPlys);
    }

    private int score(int blackScore, int whiteScore) {
        return blackScore - whiteScore;
    }

    private Node buildGameTree(Board board, int numOfPlys) {
        // Create root node
        Node root = new Node(board, MinMaxEnum.MAX, null);

        IterativeDeepeningSearch.runDepthFirstSearchWithDepth(root, numOfPlys);

        return root;
    }
}
