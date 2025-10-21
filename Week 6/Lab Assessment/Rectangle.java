// Q1-W6-A: Triangle 2.0

public class Rectangle {
    private int length1, length2;

    public Rectangle() {
        length1 = length2 = 1;    
    }

    public Rectangle(int side) {
        length1 = length2 = side;
    }

    public Rectangle(int side1, int side2) {
        length1 = side1;
        length2 = side2;
    }

    public int getShortSide() {
        return Math.min(length1, length2);
    }

    public int getLongSide() {
        return Math.max(length1, length2);
    }

    public boolean isSquare() {
        return (length1 == length2);
    }

    public int area() {
        return length1 * length2;
    }

    public double diagonal() {
        return Math.sqrt(Math.pow(length1, 2.0) + Math.pow(length2, 2.0));
    }

    public boolean bigger(Rectangle other) {
        return this.area() > other.area();
    }
}