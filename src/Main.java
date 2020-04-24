import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Display d = new Display(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
