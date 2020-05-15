import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Game {
    final int DELAY = 3; // 60/DELAY fps
    final int SIZE = 800; // Length & Width of board (pixels)
    final int GRID_UNIT = 20; // Size of one "grid" unit
    Snake snake;
    int delayCount = 0;
    AnimationTimer animator;
    Display display;
    int gameState = 0; // 0 (menu), 1 (playing), 2 (pause), 3 (death)
    Direction currDir;
    LinkedList<Direction> dirQueue;
    Point2D fruit;

    Game(Stage stage) {
        display = new Display(stage, SIZE, GRID_UNIT);
        initGame();
        display.drawSnake(snake);
    }

    public void initGame() {
        gameState = 1;
        snake = new Snake(SIZE);
        currDir = Direction.UP;
        genFruit();
        dirQueue = new LinkedList<>();
        animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (delayCount == DELAY) {
                    if (!dirQueue.isEmpty())
                        snake.dir = dirQueue.pop();
                    delayCount = 0;
                    snake.move(GRID_UNIT, testEat());
                    testCollision();
                    display.clear();
                    display.drawSnake(snake);
                    display.drawFruit(fruit);
                }
                else
                    delayCount++;
                // TODO: Add stopping conditions
                if (gameState != 1)
                    stop();
            }
        };
        animator.start();
        addKeyHandler();
    }

    private void addKeyHandler() {
        // TODO: 4/25/20 Add this to a queue and only activate before a movement tick
        display.scene.setOnKeyPressed(keyEvent -> {
            if (gameState == 1) {
                if (keyEvent.getCode() == KeyCode.LEFT && currDir != Direction.RIGHT) {
                    currDir = Direction.LEFT;
                    dirQueue.add(Direction.LEFT);
                }
                else if (keyEvent.getCode() == KeyCode.RIGHT && snake.dir != Direction.LEFT) {
                    currDir = Direction.RIGHT;
                    dirQueue.add(Direction.RIGHT);
                }
                else if (keyEvent.getCode() == KeyCode.UP && snake.dir != Direction.DOWN) {
                    currDir = Direction.UP;
                    dirQueue.add(Direction.UP);
                }
                else if (keyEvent.getCode() == KeyCode.DOWN && snake.dir != Direction.UP) {
                    currDir = Direction.DOWN;
                    dirQueue.add(Direction.DOWN);
                }
            }
        });
    }

    public void genFruit() {
        boolean unique = false;
        while (!unique) {
            unique = true;
            fruit = new Point2D(Math.floor(Math.random() * SIZE / GRID_UNIT) * GRID_UNIT, Math.floor(Math.random() * SIZE / GRID_UNIT) * GRID_UNIT);
            for (Point2D seg : snake.body) {
                if (seg.getX() == fruit.getX() && seg.getY() == fruit.getY()) {
                    unique = false;
                    break;
                }
            }
        }
        System.out.println(fruit.getX() + " " + fruit.getY());
    }

    public boolean testEat() {
        if (snake.head.getX() == fruit.getX() && snake.head.getY() == fruit.getY()) {
            genFruit();
            return true;
        }
        return false;
    }

    public void testCollision() {
        // TODO: 4/26/20 ahhhhhhhhhhhhd collision
    }

    public void gameOver() {
        display.clear();
        display.drawDeath();

    }

}
