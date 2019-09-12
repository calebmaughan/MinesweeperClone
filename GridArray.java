package CS2410.Assn8;

import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cmaug_000 on 11/29/2016.
 */
public class GridArray {
    public int minesLeft;
    public int safeLeft;

    public ArrayList<GameButton> arr1;
    public GridArray(){
        arr1 = new ArrayList<>();

        for(int i = 0; i < 40; i++){
            arr1.add(i, new GameButton(true));
        }
        for(int i = 40; i < 400; i++){
            arr1.add(i, new GameButton(false));
        }
        Collections.shuffle(arr1);

        minesLeft = 40;
        safeLeft = 0;

        for(int i = 0; i < 20; i++){
            rowMaker(arr1, i);
        }



    }
    public void rowMaker(ArrayList<GameButton> arr, int row){
        int rowMod = row*20;
        int col = 1;
        for(int i = rowMod; i < rowMod + 20; i++){
            arr.get(i).relocate(25*col, 25*(row+1));
            col++;
        }
    }
   public void setNumber(ArrayList<GameButton> arr, int index){
        int totalMine = 0;
        if(arr.get(index).mine){

        }
        else {
            if (index < 20) {
                if (index == 0) {
                    if (arr.get(index + 1).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 20).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 21).mine) {
                        totalMine++;
                    }
                } else if (index == 19) {
                    if (arr.get(index - 1).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 20).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 19).mine) {
                        totalMine++;
                    }
                } else {
                    if (arr.get(index + 1).mine) {
                        totalMine++;
                    }
                    if (arr.get(index - 1).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 19).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 20).mine) {
                        totalMine++;
                    }
                    if (arr.get(index + 21).mine) {
                        totalMine++;
                    }
                }
            } else if (index > 379) {
                if(index == 380){
                    if(arr.get(index+1).mine){
                        totalMine++;
                    }
                    if(arr.get(index-19).mine){
                        totalMine++;
                    }
                    if(arr.get(index-20).mine){
                        totalMine++;
                    }
                }
                else if(index == 399){
                    if(arr.get(index - 1).mine){
                        totalMine++;
                    }
                    if(arr.get(index-20).mine){
                        totalMine++;
                    }
                    if(arr.get(index-21).mine){
                        totalMine++;
                    }
                }
                else{
                    if(arr.get(index+1).mine){
                        totalMine++;
                    }
                    if(arr.get(index-1).mine){
                        totalMine++;
                    }
                    if(arr.get(index-19).mine){
                        totalMine++;
                    }
                    if(arr.get(index - 20).mine){
                        totalMine++;
                    }
                    if(arr.get(index - 21).mine){
                        totalMine++;
                    }
                }

            }
            else if(index%20 == 0){
                if(arr.get(index+1).mine){
                    totalMine++;
                }
                if(arr.get(index-20).mine){
                    totalMine++;
                }
                if(arr.get(index-19).mine){
                    totalMine++;
                }
                if(arr.get(index+20).mine){
                    totalMine++;
                }
                if(arr.get(index+21).mine){
                    totalMine++;
                }
            }
            else if((index+1)%20 == 0){
                if(arr.get(index-1).mine){
                    totalMine++;
                }
                if(arr.get(index-20).mine){
                    totalMine++;
                }
                if(arr.get(index-21).mine){
                    totalMine++;
                }
                if(arr.get(index+20).mine){
                    totalMine++;
                }
                if(arr.get(index+19).mine){
                    totalMine++;
                }
            }
            else {
                totalMine = search9(arr, index);
            }
            arr.get(index).mineNext = String.valueOf(totalMine);
            //arr.get(index).setText(arr.get(index).mineNext);
        }
   }
   public int search9(ArrayList<GameButton> arr1, int index1){
       int total = 0;
       if(arr1.get(index1-1).mine){
           total++;
       }
       if(arr1.get(index1+1).mine){
           total++;
       }
       if(arr1.get(index1+20).mine){
           total++;
       }
       if(arr1.get(index1-20).mine){
           total++;
       }
       if(arr1.get(index1+19).mine){
           total++;
       }
       if(arr1.get(index1+21).mine){
           total++;
       }
       if(arr1.get(index1-19).mine){
           total++;
       }
       if(arr1.get(index1-21).mine){
           total++;
       }
       return total;
   }
   public int leftClicker(int index) {
       if (arr1.get(index).mine) {
           if (arr1.get(index).pressed) {
                return 0;
           } else {
               if (arr1.get(index).flagVal == 0) {
                   arr1.get(index).setText("!");
                   arr1.get(index).setEndMine();
                   return 2;
               }
           }
       }
       else {
           if (!arr1.get(index).pressed) {
               if (arr1.get(index).flagVal == 0) {
                   arr1.get(index).pressed = true;
                   arr1.get(index).setPressed();
                   safeLeft++;
                   arr1.get(index).setText(arr1.get(index).mineNext);
                   arr1.get(index).setBackground(Background.EMPTY);
                   if(safeLeft < 360) {
                       if (arr1.get(index).mineNext.equals("0")) {
                           if (index == 0) {
                               leftClicker(1);
                               leftClicker(20);
                               leftClicker(21);
                           } else if (index < 19) {
                               leftClicker(index + 1);
                               leftClicker(index - 1);
                               leftClicker(index + 19);
                               leftClicker(index + 20);
                               leftClicker(index + 21);
                           } else if (index == 19) {
                               leftClicker(18);
                               leftClicker(38);
                               leftClicker(39);
                           } else if (index == 399) {
                               leftClicker(398);
                               leftClicker(379);
                               leftClicker(378);
                           } else if (index == 380) {
                               leftClicker(381);
                               leftClicker(360);
                               leftClicker(361);
                           } else if (index < 399 && index > 380) {
                               leftClicker(index + 1);
                               leftClicker(index - 1);
                               leftClicker(index - 20);
                               leftClicker(index - 21);
                               leftClicker(index - 19);
                           } else if (index % 20 == 0) {
                               leftClicker(index + 1);
                               leftClicker(index + 20);
                               leftClicker(index + 21);
                               leftClicker(index - 20);
                               leftClicker(index - 19);
                           } else if ((index + 1) % 20 == 0) {
                               leftClicker(index - 1);
                               leftClicker(index + 20);
                               leftClicker(index + 19);
                               leftClicker(index - 20);
                               leftClicker(index - 21);
                           } else {
                               leftClicker(index - 1);
                               leftClicker(index + 1);
                               leftClicker(index + 19);
                               leftClicker(index + 20);
                               leftClicker(index + 21);
                               leftClicker(index - 19);
                               leftClicker(index - 20);
                               leftClicker(index - 21);
                           }
                       }
                   }
                   else if(safeLeft == 360){
                       return 1;
                   }
                   return 0 ;
               }
           }
       }
       return 0;
   }
}
