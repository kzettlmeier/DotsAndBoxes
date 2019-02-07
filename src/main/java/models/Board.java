package models;

public class Board {
    private Box[][] board;

    public Board(int size, boolean forClone) {
        this.board = new Box[size][size];
    }

    public Board(int size) {
        this.board = generateBoard(size);
    }

    public Box[][] getBoard() {
        return this.board;
    }

    public Board clone() {
        int size = this.board.length;
        Board newBoard = new Board(size, true);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newBoard.board[i][j] = board[i][j].clone();
            }
        }

        return newBoard;
    }

    public Box getBoxAtCoordinate(int x, int y) {
        int size = this.board.length;
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            return null;
        }
        return this.board[x][y];
    }

    public Box getSiblingBoxBasedOnCoordinateAndMove(int x, int y, String side) {
        int size = this.board.length;
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            return null;
        }
        if (side.equals("left")) {
            // Check if left most box
            if (x == 0) {
                return null;
            }
            return this.board[x][y - 1];
        } else if (side.equals("top")) {
            // Check if top most box
            if (y == 0) {
                return null;
            }
            return this.board[x - 1][y];
        } else if (side.equals("right")) {
            // Check if right most box
            if (x == size - 1) {
                return null;
            }
            return this.board[x][y + 1];
        } else if (side.equals("bottom")) {
            // Check if bottom most box
            if (y == size - 1) {
                return null;
            }
            return this.board[x + 1][y];
        }

        return null;
    }

    public void printBoard() {
        int size = this.board.length;
        // Print top coordinates
        String topLine = "  ";
        for (int i = 0; i < size - 1; i++) {
            topLine += " " + i + " ";
        }
        System.out.println(topLine);
        for (int i = 0; i < size; i++) {
            String boardLine = "  ";
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
                boardLine = "" + i + " ";
                for (int j = 0; j < size - 1; j++) {
                    if (j == 0) {
                        if (this.board[i][j].getLeft()) {
                            boardLine += "|";
                        } else {
                            boardLine += " ";
                        }
                    }
                    if (this.board[i][j].getOwner().equals(BoxOwner.NONE)) {
                        boardLine += this.board[i][j].getScore() + " ";
                    } else {
                        boardLine += this.board[i][j].getScore() + this.board[i][j].getOwner().toString().substring(0, 1);
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

    public BoxOwner calculateWinner() {
        int humanScore = 0;
        int aiScore = 0;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getOwner().equals(BoxOwner.HUMAN)) {
                    humanScore += this.board[i][j].getScore();
                } else if (this.board[i][j].getOwner().equals(BoxOwner.AI)) {
                    aiScore += this.board[i][j].getScore();
                }
            }
        }
        System.out.println("Human Score: " + humanScore);
        System.out.println("AI Score: " + aiScore);

        if (humanScore > aiScore) {
            return BoxOwner.HUMAN;
        }
        return BoxOwner.AI;
    }

    // This will check if there is a box without an owner, if TRUE then game not complete
    public boolean gameComplete() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getOwner().equals(BoxOwner.NONE)) {
                    return false;
                }
            }
        }

        return true;
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
