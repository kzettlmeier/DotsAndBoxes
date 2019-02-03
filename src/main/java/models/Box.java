package models;

public class Box {
    private boolean left;
    private boolean top;
    private boolean right;
    private boolean bottom;
    private int score;
    private BoxOwner owner;

    public Box() {
        this.left = false;
        this.top = false;
        this.right = false;
        this.bottom = false;
        this.score = generateRandomInt();
        this.owner = BoxOwner.NONE;
    }

    public boolean getLeft() {
        return this.left;
    }

    public void setLeft() {
        this.left = true;
    }

    public boolean getRight() {
        return this.right;
    }

    public void setRight() {
        this.right = true;
    }

    public boolean getTop() {
        return this.top;
    }

    public void setTop() {
        this.top = true;
    }

    public boolean getBottom() {
        return this.bottom;
    }

    public void setBottom() {
        this.bottom = true;
    }

    public BoxOwner getOwner() {
        return this.owner;
    }

    public void setOwner(BoxOwner owner) {
        this.owner = owner;
    }

    public int getScore() {
        return this.score;
    }

    private int generateRandomInt() {
        return ((int)(Math.random() * 5)) + 1;
    }
}
