package players;

import models.Board;
import models.Box;
import models.BoxOwner;

import java.util.Scanner;

public class Human {
    // When leaving this function the human will have made a move
    public void makeMove(Board board, Scanner input) {
        boolean stillNeedsToMove = true;
        while (stillNeedsToMove) {
            System.out.println("Please select a coordinate for a box to make a choice in: ");
            String coordinates = input.next().trim();
            if (coordinates.length() != 2) {
                System.out.println("Those were invalid coordinates!");
                continue;
            }
            int x = Integer.parseInt(coordinates.substring(0, 1));
            int y = Integer.parseInt(coordinates.substring(1, 2));
            // Check if that box has any moves left
            Box box = board.getBoxAtCoordinate(x, y);
            if (box == null) {
                System.out.println("Invalid coordinates, please try again!");
                continue;
            }
            if (!box.hasOwner()) {
                System.out.println("That box already has been completed!");
                continue;
            }
            System.out.println("Please select a line to take (LEFT, TOP, RIGHT, BOTTOM): ");
            String side = input.next().toLowerCase();
            if (box.isSideTaken(side)) {
                System.out.println("That side is already taken, please try making the move again!");
                continue;
            }
            // Take the move and check if now owner
            boolean nowOwner = box.setSide(side, BoxOwner.HUMAN);
            if (nowOwner) {
                System.out.println("You now own this box!");
            }
            // Check and see if you need to update the siblings side
            Box siblingToUpdate = board.getSiblingBoxBasedOnCoordinateAndMove(x, y, side);
            if (siblingToUpdate != null) {
                boolean owner;
                // Set opposite side in sibling
                if (side.equals("left")) {
                    owner = siblingToUpdate.setSide("right", BoxOwner.HUMAN);
                } else if (side.equals("top")) {
                    owner = siblingToUpdate.setSide("bottom", BoxOwner.HUMAN);
                } else if (side.equals("right")) {
                    owner = siblingToUpdate.setSide("left", BoxOwner.HUMAN);
                } else {
                    owner = siblingToUpdate.setSide("top", BoxOwner.HUMAN);
                }

                if (owner) {
                    System.out.println("You now own the sibling box as well");
                }
            }
            // End the turn for the user
            stillNeedsToMove = false;
        }
        // Print Board
        board.printBoard();
    }
}
