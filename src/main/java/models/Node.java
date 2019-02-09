package models;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Board board;
    private Score score;
    private MinMaxEnum minOrMax;
    private List<Node> children;
    private Node parent;
    private Move move;

    public Node(Board board, MinMaxEnum minOrMax, Node parent, Move move) {
        this.board = board;
        this.score = null;
        this.minOrMax = minOrMax;
        this.children = new ArrayList<>();
        this.parent = parent;
        this.move = move;
    }


    public Node getParent() {
        return this.parent;
    }

    public Move getMove() {
        return this.move;
    }

    public Board getBoard() {
        return this.board;
    }

    public MinMaxEnum getMinOrMax() {
        return this.minOrMax;
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

    public void setScore(int score) {
        this.score = new Score(score);
    }

    public Score getScore() {
        return this.score;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public List<Node> generateSuccessors() {
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
                    newBox.setSide("left", BoxOwner.AI);
                    newBoard.getBoard()[i][j] = newBox;
                    // Check and see if you need to update the siblings side
                    Box siblingToUpdate = newBoard.getSiblingBoxBasedOnCoordinateAndMove(i, j, "left");
                    if (siblingToUpdate != null) {
                        siblingToUpdate.setSide("right", BoxOwner.AI);
                    }
                    Node newNode = new Node(newBoard, newMinxOrMax, this, new Move(i, j, "left"));
                    list.add(newNode);
                }
                if (!box.getTop()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setSide("top", BoxOwner.AI);
                    newBoard.getBoard()[i][j] = newBox;
                    Box siblingToUpdate = newBoard.getSiblingBoxBasedOnCoordinateAndMove(i, j, "top");
                    if (siblingToUpdate != null) {
                        siblingToUpdate.setSide("bottom", BoxOwner.AI);
                    }
                    Node newNode = new Node(newBoard, newMinxOrMax, this, new Move(i, j, "top"));
                    list.add(newNode);
                }
                if (!box.getRight()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setSide("right", BoxOwner.AI);
                    newBoard.getBoard()[i][j] = newBox;
                    Box siblingToUpdate = newBoard.getSiblingBoxBasedOnCoordinateAndMove(i, j, "right");
                    if (siblingToUpdate != null) {
                        siblingToUpdate.setSide("left", BoxOwner.AI);
                    }
                    Node newNode = new Node(newBoard, newMinxOrMax, this, new Move(i, j, "right"));
                    list.add(newNode);
                }
                if (!box.getBottom()) {
                    Board newBoard = board.clone();
                    Box newBox = box.clone();
                    newBox.setSide("bottom", BoxOwner.AI);
                    newBoard.getBoard()[i][j] = newBox;
                    Box siblingToUpdate = newBoard.getSiblingBoxBasedOnCoordinateAndMove(i, j, "bottom");
                    if (siblingToUpdate != null) {
                        siblingToUpdate.setSide("top", BoxOwner.AI);
                    }
                    Node newNode = new Node(newBoard, newMinxOrMax, this, new Move(i, j, "bottom"));
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
