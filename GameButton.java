package CS2410.Assn8;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Created by cmaug_000 on 11/29/2016.
 */
public class GameButton extends Button {

    int flagVal = 0;
    boolean pressed;
    public String mineNext;
    public boolean mine;
    public String flag;
    public int mineIndex;



    GameButton(boolean mine1){
        mine = mine1;
        setPrefSize(25, 25);
        getStylesheets().addAll("CS2410/Assn8/buttonCSS.css");

    }

    public void setButtonClick(EventHandler<MouseEvent> event){setOnMouseClicked(event);}
    public void setCorrect(){
        setId("correct-flag");
    }
    public void setIncorrect(){
        setId("incorrect-flag");
    }
    public void setEndMine(){
        setId("unmarked-mine");
    }
    public void setPressed(){
        setId("pressed");
    }

}
