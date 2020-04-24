import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Display {
    Stage stage;
    Scene scene;
    Group root;
    Canvas canvas;
    GraphicsContext gc;
    int size;
    int gridUnit;

    Display(Stage stage, int size, int gridUnit) {
        this.stage = stage;
        this.size = size;
        this.gridUnit = gridUnit;
        initStage();
    }

    private void initStage() {
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.setHeight(size);
        stage.setWidth(size);
        stage.show();

        canvas = new Canvas(size, size);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
    }

    public void drawSnake(Snake snake) {
        int x = (int) snake.head.getX();
        int y = (int) snake.head.getY();
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(x, y, gridUnit, gridUnit);

        gc.setFill(Color.GREEN);
        for (Point2D p : snake.body) {
            gc.fillRect(p.getX(), p.getY(), gridUnit, gridUnit);
        }
    }

    public void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
