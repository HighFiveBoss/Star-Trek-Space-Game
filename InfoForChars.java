public class InfoForChars {
    private int way, length;
    private boolean used;
    private int x, y;
    private static int computer_score = 0;
    private boolean movable = true;

    public boolean isMovable() {
        return movable;
    }
    public void setMovable(boolean move) {
        this.movable = move;
    }
    public static int getComputer_score() {
        return computer_score;
    }
    public static void setComputer_score(int computer_score) {
        InfoForChars.computer_score = computer_score;
    }
    InfoForChars(int way, int length) {
        this.length = length;
        this.way = way;
        used = false;
    }
    InfoForChars(int way, int length, int x, int y) {
        this.length = length;
        this.way = way;
        used = false;
        this.x = x;
        this.y = y;
    }
    public int getWay() {
        return way;
    }
    public void setWay(int way) {
        this.way = way;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}