import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Game {
    int size = 1000;
    int gridUnit = 10; // Size of one "grid" unit
    Snake snake;
    int delay = 0;
    AnimationTimer animator;
    Display display;

    Game(Stage stage) {
        display = new Display(stage, size, gridUnit);
        initGame();
        display.drawSnake(snake);
    }

    public void initGame() {
        snake = new Snake(size);
        animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (delay == 10) {
                    delay = 0;
                    snake.move(gridUnit);
                    display.clear();
                    display.drawSnake(snake);
                }
                else
                    delay++;
            }
        };
        animator.start();
    }

}
