package CS2410.Assn8;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by cmaug_000 on 11/29/2016.
 */
public class Main extends Application{

    Scene scene1;
    Control game1;


    @Override
    public void start(Stage primaryStage) throws Exception {

        game1 = new Control();

        scene1 = new Scene(game1);


        primaryStage.setTitle("Minesweeperish");
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();



    }
}
