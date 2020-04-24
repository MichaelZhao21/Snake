import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    Game g;

    @Override
    public void start(Stage stage) throws Exception {
        g = new Game(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
