import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Display {
    Stage stage;
    Scene scene;
    StackPane root;
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
        root = new StackPane();
        scene = new Scene(root); //TODO bottom align stackpane
        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.setHeight(size + 100);
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

    public void drawFruit(Point2D fruit) {
        gc.setFill(Color.RED);
        gc.fillRect(fruit.getX(), fruit.getY(), gridUnit, gridUnit);
    }

    public void drawDeath() {
        Text oops = new Text();
        oops.setText("Oops! You died!\nPress ENTER to restart\nor any other key to quit :))");
        oops.setFont(Font.font("Times New Roman", 45));
        oops.setFill(Color.GOLDENROD);
        oops.setTextAlignment(TextAlignment.CENTER);
        StackPane sp = new StackPane(oops);
        scene = new Scene(sp);
        stage.setScene(scene);
        stage.show();
        // TODO: 4/26/20 FIX 
    }

    public void clear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
