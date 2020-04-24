import javafx.geometry.Point2D;

public class Snake {
    Point2D head;

    Snake(int boardSize) {
        int half = (int) (boardSize / 2);
        head = new Point2D(half, half);
    }


}
