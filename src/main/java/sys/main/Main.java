package sys.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL url = Main.class.getResource("/sys/main/main-frame.fxml");
        System.out.println("Initializing Frame: " + url);

        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Energy-Efficient Knapsack Algorithm");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}