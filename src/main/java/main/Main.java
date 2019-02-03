package main;

import models.Board;
import models.BoxOwner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Ask how many plys the AI will search
        System.out.println("How many plys will the AI search? ");
        int numOfPlys = input.nextInt();

        // Ask for the size of the board
        System.out.println("What size board would you like to play? ");
        int boardSize = input.nextInt();
        input.close();

        // Generate a board
        Board board = new Board(boardSize);
        board.printBoard();

        // Start Game (Human Goes first then switches back and forth)
        boolean humanTurn = true;
        // Keep playing while there is a box that doesn't have an owner
        while (!board.gameComplete()) {
            // If Human ask for input

            // If AI figure out best move

            // Swap players
            humanTurn = !humanTurn;
        }

        // Game complete calculate score
        BoxOwner winner = board.calculateWinner();
        System.out.println("The Winner is " + winner.toString());
    }
}
