import javafx.scene.Scene;
import javafx.stage.Stage;

public class Display {
    Stage stage;
    Scene scene;

    Display(Stage stage) {
        this.stage = stage;
        init();
    }

    private void init() {
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(500);
        stage.show();
    }

}
