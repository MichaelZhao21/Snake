import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Game {
    final int DELAY = 5; // 60/DELAY fps
    final int size = 1000; // Length & Width of board (pixels)
    final int gridUnit = 10; // Size of one "grid" unit
    Snake snake;
    int delayCount = 0;
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
                if (delayCount == DELAY) {
                    delayCount = 0;
                    snake.move(gridUnit);
                    display.clear();
                    display.drawSnake(snake);
                }
                else
                    delayCount++;
            }
        };
        animator.start();
        addKeyHandler();
    }

    private void addKeyHandler() {
        display.scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.LEFT && snake.dir != Direction.RIGHT)
                snake.dir = Direction.LEFT;
            else if (keyEvent.getCode() == KeyCode.RIGHT && snake.dir != Direction.LEFT)
                snake.dir = Direction.RIGHT;
            else if (keyEvent.getCode() == KeyCode.UP && snake.dir != Direction.DOWN)
                snake.dir = Direction.UP;
            else if (keyEvent.getCode() == KeyCode.DOWN && snake.dir != Direction.UP)
                snake.dir = Direction.DOWN;
        });
    }

    public void collision() {

    }

    public void gameOver() {
        display.clear();

    }

}
