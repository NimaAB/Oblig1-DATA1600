package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
        primaryStage.setTitle("Registreringsskjema");
        primaryStage.setScene(new Scene(root, primaryStage.getMinWidth(), primaryStage.getMinHeight()));
        primaryStage.show();
    }
    /*public void start1(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        primaryStage.setTitle("Skjema");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}
