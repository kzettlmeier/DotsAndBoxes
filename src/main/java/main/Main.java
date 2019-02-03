package main;

import models.Board;
import models.Box;

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
    }
}
