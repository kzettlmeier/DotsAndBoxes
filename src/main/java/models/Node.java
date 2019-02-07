package models;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Board board;
    private int score;
    private MinMaxEnum minOrMax;
    private List<Node> children;
    private Node parent;

    public Node(Board board, MinMaxEnum minOrMax, Node parent) {
        this.board = board;
        this.score = 0;
        this.minOrMax = minOrMax;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    public int getCurrentDepth() {
        int depth = 1;
        Node parent = this.parent;
        while (parent != null) {
            depth++;
            parent = parent.parent;
        }

        return depth;
    }

    public List<Node> getSuccessors() {
        List<Node> list = new ArrayList<>();
        MinMaxEnum newMinxOrMax = null;
        if (this.minOrMax.equals(MinMaxEnum.MIN)) {
            newMinxOrMax = MinMaxEnum.MAX;
        } else {
            newMinxOrMax = MinMaxEnum.MIN;
        }

        Box[][] currentBoard = board.getBoard();
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                Box box = currentBoard[i][j];
                // Check if each side has been taken, if not make that move in a new board object to be added to a new Node
                if (!box.getLeft()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setLeft();
                    newBoard.getBoard()[i][j] = newBox;
                    Node newNode = new Node(newBoard, newMinxOrMax, this);
                    list.add(newNode);
                }
                if (!box.getTop()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setTop();
                    newBoard.getBoard()[i][j] = newBox;
                    Node newNode = new Node(newBoard, newMinxOrMax, this);
                    list.add(newNode);
                }
                if (!box.getRight()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setRight();
                    newBoard.getBoard()[i][j] = newBox;
                    Node newNode = new Node(newBoard, newMinxOrMax, this);
                    list.add(newNode);
                }
                if (!box.getBottom()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setBottom();
                    newBoard.getBoard()[i][j] = newBox;
                    Node newNode = new Node(newBoard, newMinxOrMax, this);
                    list.add(newNode);
                }
            }
        }

        // Loop through these and make children as well
        list.forEach(node -> {
            this.children.add(node);
        });
        return list;
    }
}
