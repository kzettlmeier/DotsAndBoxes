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

    public Box(boolean left, boolean top, boolean right, boolean bottom, int score, BoxOwner owner) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.score = score;
        this.owner = owner;
    }

    public Box clone() {
        return new Box(this.left, this.top, this.right, this.bottom, this.score, this.owner);
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

    public boolean hasOwner() {
        if (this.owner.equals(BoxOwner.NONE)) {
            return true;
        }

        return false;
    }

    public boolean hasMovesLeft() {
        if (this.left && this.top && this.right && this.bottom) {
            return false;
        }
        return true;
    }

    public boolean isSideTaken(String side) {
        if (side.equals("left")) {
            return this.left;
        } else if (side.equals("top")) {
            return this.top;
        } else if (side.equals("right")) {
            return this.right;
        } else if (side.equals("bottom")) {
            return this.bottom;
        }
        return false;
    }

    public boolean setSide(String side, BoxOwner owner) {
        if (side.equals("left")) {
            this.setLeft();
        } else if (side.equals("top")) {
            this.setTop();
        } else if (side.equals("right")) {
            this.setRight();
        } else if (side.equals("bottom")) {
            this.setBottom();
        }

        // Check if they are now an owner
        if (!this.hasMovesLeft()) {
            this.setOwner(owner);
            return true;
        }

        return false;
    }
}
