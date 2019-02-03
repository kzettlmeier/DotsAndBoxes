package models;

public class Board {
    private Box[][] board;

    public Board(int size) {
        this.board = generateBoard(size);
    }

    public Box[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        int size = this.board.length;
        for (int i = 0; i < size; i++) {
            String boardLine = "";
            // Print Top
            for (int j = 0; j < size - 1; j++) {
                boardLine += ".";
                if (this.board[i][j].getTop()) {
                    boardLine += "__";
                } else {
                    boardLine += "  ";
                }
            }
            boardLine += ".";
            System.out.println(boardLine);
            // Print Sides
            if (i != size - 1) {
                boardLine = "";
                for (int j = 0; j < size - 1; j++) {
                    if (j == 0) {
                        if (this.board[i][j].getLeft()) {
                            boardLine += "|";
                        } else {
                            boardLine += " ";
                        }
                    }
                    if (this.board[i][j].getOwner().equals(BoxOwner.NONE)) {
                        boardLine += "  ";
                    } else {
                        boardLine += this.board[i][j].getOwner().toString().substring(0, 2);
                    }
                    if (this.board[i][j].getRight()) {
                        boardLine += "|";
                    } else {
                        boardLine += " ";
                    }
                }
                System.out.println(boardLine);
            }
            // Print Bottom
            boardLine = "";
            for (int j = 0; j < size; j++) {
                boardLine += ".";
                if (this.board[i][j].getBottom()) {
                    boardLine += "__";
                } else {
                    boardLine += "  ";
                }
            }
        }
    }

    // This will generate a new board
    private Box[][] generateBoard(int size) {
        Box[][] board = new Box[size+1][size+1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Box();
            }
        }

        return board;
    }
}
