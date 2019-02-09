package models;

public class Move {
    private int xBoxCoordinate;
    private int yBoxCoordinate;
    private String sideToTake;

    public Move(int x, int y, String side) {
        this.xBoxCoordinate = x;
        this.yBoxCoordinate = y;
        this.sideToTake = side;
    }

    public int getX() {
        return this.xBoxCoordinate;
    }

    public int getY() {
        return this.yBoxCoordinate;
    }

    public String getSide() {
        return this.sideToTake;
    }
}
