package CS2410.Assn8;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by cmaug_000 on 11/29/2016.
 */
public class GameBoard extends Pane {
    GridArray grid1;

    public GameBoard(){
        grid1 = new GridArray();
        setPrefSize(550, 550);
        for(int i = 0; i < 400; i++){
            getChildren().addAll(grid1.arr1.get(i));
        }
        for(int i = 0; i < 400; i++){
            int index = i;
            grid1.setNumber(grid1.arr1, i);
            grid1.arr1.get(i).mineIndex = i;

        }
    }
}
