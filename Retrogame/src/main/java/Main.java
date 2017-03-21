import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Adam on 2017-03-21.
 */
public class Main extends Application {

        private  final int WIDTH = 700 ;
        private  final int HEIGHT =800;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("RetroGame!");
            Player p1 = new Player(100, 100);
            Player p2 = new Player(500, 100);
            Group root = new Group();
            root.getChildren().add(p1);
            root.getChildren().add(p2);
            primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
            primaryStage.show();
        }
}
