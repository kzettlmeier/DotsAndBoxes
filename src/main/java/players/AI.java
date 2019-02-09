package players;

import algorithms.MinMaxSearch;
import algorithms.IterativeDeepeningSearch;
import models.*;

public class AI {
    // This will have the AI make a move on the board
    public void makeMove(Board board, int numOfPlys) {
        Node root = this.buildGameTree(board, numOfPlys);

        //Run Depth first search to see which move to make
        MinMaxSearch.searchGameTreeForMove(root);

        // Get score of root and find child that has that score, that is the move to take
        int score = root.getScore().getScore();
        for (Node child : root.getChildren()) {
            if (child.getScore().getScore() == score) {
                // This is the move to make
                // Take the move and check if now owner
                Move move = child.getMove();
                int xCoordinateOfBox = move.getX();
                int yCoordinateOfBox = move.getY();
                String side = move.getSide();
                boolean nowOwner = board.getBoard()[xCoordinateOfBox][yCoordinateOfBox].setSide(side, BoxOwner.AI);
                if (nowOwner) {
                    System.out.println("AI now owns this box!");
                }
                // Check and see if you need to update the siblings side
                Box siblingToUpdate = board.getSiblingBoxBasedOnCoordinateAndMove(xCoordinateOfBox, yCoordinateOfBox, side);
                if (siblingToUpdate != null) {
                    boolean owner;
                    // Set opposite side in sibling
                    if (side.equals("left")) {
                        owner = siblingToUpdate.setSide("right", BoxOwner.AI);
                    } else if (side.equals("top")) {
                        owner = siblingToUpdate.setSide("bottom", BoxOwner.AI);
                    } else if (side.equals("right")) {
                        owner = siblingToUpdate.setSide("left", BoxOwner.AI);
                    } else {
                        owner = siblingToUpdate.setSide("top", BoxOwner.AI);
                    }

                    if (owner) {
                        System.out.println("AI now owns the sibling box as well");
                    }
                }

                break;
            }
        }

        // Print Board
        board.printBoard();
    }

    // This initiates the building of the game tree for the AI
    private Node buildGameTree(Board board, int numOfPlys) {
        // Create root node
        Node root = new Node(board, MinMaxEnum.MAX, null, null);

        IterativeDeepeningSearch.runDepthFirstSearchWithDepth(root, numOfPlys + 1);

        return root;
    }
}
