import javafx.geometry.Point2D;
import java.util.LinkedList;

enum Direction {LEFT, RIGHT, UP, DOWN}

public class Snake {
    Point2D head;
    LinkedList<Point2D> body;
    Direction dir;

    Snake(int boardSize) {
        int half = boardSize / 2;
        head = new Point2D(half, half);
        body = new LinkedList<>();
        dir = Direction.UP;
    }

    public void move(int gridUnit) {
        body.addFirst(head);
        if (!body.isEmpty())
            body.removeLast();
        head = head.add(dirToAdd(gridUnit));
    }

    private Point2D dirToAdd(int gridUnit) {
        switch (dir) {
            case UP:
                return new Point2D(0, -gridUnit);
            case DOWN:
                return new Point2D(0, gridUnit);
            case LEFT:
                return new Point2D(-gridUnit, 0);
            case RIGHT:
                return new Point2D(gridUnit, 0);
            default:
                System.err.println("Invalid Direction :((");
                return new Point2D(0, 0);
        }
    }

}
