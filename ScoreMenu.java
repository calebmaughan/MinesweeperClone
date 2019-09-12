package CS2410.Assn8;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cmaug_000 on 11/29/2016.
 */
public class ScoreMenu extends Pane {
    Text timerText;
    Text minesText;
    Text seconds;
    Button resetBtn;
    int minesLeft;
    int time;
    String timeUpdate;
    Timer timer;

    public ScoreMenu(){
        setPrefWidth(500);
        setPrefHeight(40);
        minesLeft = 0;
        timerText = new Text();
        minesText = new Text();
        seconds = new Text();
        resetBtn = new Button("New Game");

        seconds.setText("Time: ");
        timerText.setText("0");
        minesText.setText("Mines Left: " + minesLeft);

        resetBtn.setPrefWidth(80);
        resetBtn.relocate(235, 15);

        timerText.relocate(400, 20);
        minesText.relocate(125, 20);
        seconds.relocate(350, 20);

        getChildren().addAll(timerText, minesText, resetBtn, seconds);
    }

    public void setStartMine(int startAmt){
        minesLeft = startAmt;
        minesText.setText("Mines Left: " + minesLeft);
    }
    public void lowerMines(){
        minesLeft--;
        minesText.setText("Mines Left: " + minesLeft);

    }
    public void addMines(){
        minesLeft++;
        minesText.setText("Mines Left: " + minesLeft);
    }
    public void setReset(EventHandler<ActionEvent> event){
        resetBtn.setOnAction(event);
    }

    public void updateTimer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
                timeUpdate = String.valueOf(time);
                timerText.setText(timeUpdate);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
    }
    public void stopTimer(){
        timer.cancel();
        time = 0;

    }

}
