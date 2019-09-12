package CS2410.Assn8;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * Created by cmaug_000 on 11/30/2016.
 */
public class Control extends BorderPane {
    GameBoard board1;
    ScoreMenu score1;
    boolean timerStart;
    Control(){

        score1 = new ScoreMenu();
        setTop(score1);
        score1.setStartMine(40);
        newBoard();


            score1.setReset(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    newBoard();
                    score1.setStartMine(40);
                    score1.stopTimer();
                    score1.timerText.setText("0");
                    timerStart = false;


                }
            });



    }
    public void newBoard(){
        board1 = new GameBoard();
        setCenter(board1);
        for(int i = 0; i < 400; i++) {
            int j = i;
            board1.grid1.arr1.get(j).setButtonClick(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(!timerStart){
                        score1.updateTimer();
                        timerStart = true;
                    }
                    if (event.getButton() == MouseButton.SECONDARY) {
                        if (board1.grid1.arr1.get(j).pressed) {

                        } else if (!board1.grid1.arr1.get(j).pressed) {
                            if (board1.grid1.arr1.get(j).flagVal == 0) {
                                board1.grid1.arr1.get(j).flagVal = 1;
                                board1.grid1.arr1.get(j).flag = "F";
                                score1.lowerMines();
                                Image flag1 = new Image("CS2410/Assn8/img/Red-Flag.png");
                                ImageView falg = new ImageView(flag1);
                                falg.setFitHeight(15);
                                falg.setFitWidth(7);
                                falg.relocate(0,0);
                                board1.grid1.arr1.get(j).setGraphic(falg);;
                                board1.grid1.arr1.get(j).setAlignment(Pos.TOP_LEFT);
                                board1.grid1.arr1.get(j).setPrefSize(25, 25);
                                board1.grid1.arr1.get(j).setId("flag");
                            } else if (board1.grid1.arr1.get(j).flagVal == 1) {
                                board1.grid1.arr1.get(j).flagVal = 2;
                                board1.grid1.arr1.get(j).flag = "?";
                                board1.grid1.arr1.get(j).setGraphic(null);
                                board1.grid1.arr1.get(j).setText(board1.grid1.arr1.get(j).flag);
                            } else if (board1.grid1.arr1.get(j).flagVal == 2) {
                                board1.grid1.arr1.get(j).flagVal = 0;
                                board1.grid1.arr1.get(j).flag = "";
                                board1.grid1.arr1.get(j).setText(board1.grid1.arr1.get(j).flag);
                                board1.grid1.arr1.get(j).setId("");
                                score1.addMines();
                            }
                        }

                    } else if (event.getButton() == MouseButton.PRIMARY) {
                        if(board1.grid1.leftClicker(board1.grid1.arr1.get(j).mineIndex) == 1){
                            score1.stopTimer();
                            timerStart = false;
                            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
                            winAlert.setTitle("You Win");
                            winAlert.setHeaderText(null);
                            winAlert.setContentText("Time: " + score1.timeUpdate);
                            winAlert.showAndWait();

                        }
                        else if(board1.grid1.leftClicker(board1.grid1.arr1.get(j).mineIndex) == 2){
                            score1.stopTimer();
                            timerStart = false;
                            Alert loseAlert = new Alert(Alert.AlertType.INFORMATION);
                            loseAlert.setTitle("You Lose");
                            loseAlert.setHeaderText(null);
                            loseAlert.setContentText("That was a mine!!");
                            loseAlert.showAndWait();
                            score1.setStartMine(0);
                            for(int i = 0; i < 400; i++){
                                if(board1.grid1.arr1.get(i).mine){
                                    board1.grid1.arr1.get(i).setText("!");
                                    board1.grid1.arr1.get(i).pressed = true;
                                    if(board1.grid1.arr1.get(i).flagVal == 1 || board1.grid1.arr1.get(i).flagVal == 2){
                                        board1.grid1.arr1.get(i).setCorrect();
                                    }
                                    else if(board1.grid1.arr1.get(i).flagVal == 0){
                                        board1.grid1.arr1.get(i).setEndMine();
                                    }
                                }
                                else {
                                    if (!board1.grid1.arr1.get(i).pressed) {
                                        board1.grid1.leftClicker(i);
                                        if(board1.grid1.arr1.get(i).flagVal == 1 || board1.grid1.arr1.get(i).flagVal == 2){
                                            board1.grid1.arr1.get(i).setIncorrect();
                                            board1.grid1.arr1.get(i).setText(board1.grid1.arr1.get(i).mineNext);
                                        }

                                    }
                                }
                            }
                        }

                    }
                }
            });
        }
    }

}
