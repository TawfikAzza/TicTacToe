package tictactoe.bll;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import tictactoe.gui.controller.TicTacViewController;
import tictactoe.gui.controller.TicTacViewDyn;

public class GameBoardDyna implements IGameModelDyna{
    TicTacViewDyn ticTacViewDyn;
    BorderPane borderPane;
    private Boolean player=true;
    private Boolean player2=false;
    private Boolean[][] handPlayed;
    private int length;
    private int winner=0;
    int colPlayed;
    int rowPlayed;
    public GameBoardDyna(TicTacViewDyn t) {
        this.ticTacViewDyn = t;
        this.length=ticTacViewDyn.getLength();
        handPlayed=new Boolean[ticTacViewDyn.getLength()][ticTacViewDyn.getLength()];
        borderPane=ticTacViewDyn.getBorderPane();

    }
    @Override
    public Boolean getNextPlayer() {
        player=!player;
        //String array ="";
        /*for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                array+="["+handPlayed[i][k]+"]";
            }
            array+="\n";
        }
        System.out.println(array);*/
        return player;
    }
    public void readGame(int col, int row) {
        handPlayed[row][col]=player;
    }
    @Override
    public boolean play(int col, int row) {
        colPlayed=col;
        rowPlayed=row;

        boolean flagPlayable =false;
        if(handPlayed[row][col]==null){
            handPlayed[row][col]=player;
            return true;
        } else {
            //getNextPlayer();
            return false;
        }
//        String array="";
//        for (int i = 0; i < handPlayed.length; i++) {
//            for (int k = 0; k < handPlayed.length; k++) {
//                array+="["+handPlayed[i][k]+"]";
//            }
//            array+="\n";
//        }
        //System.out.println(array);
      //  return flagPlayable;
    }

    @Override
    public boolean isGameOver() {

        boolean isGameOver=false;
        int testDiagInv=0;
        for(int i = handPlayed.length-1; i > -1; i--) {
            if(handPlayed[i][(handPlayed.length-1)-i]==player) {
                testDiagInv++;
            } else {
                testDiagInv=0;
            }
            if(testDiagInv==handPlayed.length){
                winner=player?1:2;
                return true;
            }

        }
        int testVertical=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testVertical=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[k][i] == player){
                    testVertical++;
                } else {
                    testVertical=0;
                }
                if(testVertical==handPlayed.length) {
                    winner=player?1:2;
                    return true;
                }
            }
        }
        int testHorizontal=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testHorizontal=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[i][k] == player){
                    testHorizontal++;
                } else {
                    testHorizontal=0;
                }
                if(testHorizontal==handPlayed.length) {
                    winner=player?1:2;
                    return true;
                }
            }
        }
        int testDiag=0;
        for(int i = 0; i < handPlayed.length; i++) {
            if(handPlayed[i][i]==player) {
                testDiag++;
            } else {
                testDiag=0;
            }
            if(testDiag==handPlayed.length){
                winner=player?1:2;
                return true;
            }
        }
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                if(handPlayed[i][k]!=null) {
                    isGameOver=true;
                } else {
                    return false;
                }
            }
        }



        return isGameOver;
    }

    @Override
    public int getWinner() {
        System.out.println("Winner = "+winner);
        if(winner!=0) {
            return winner;
        } else {
            return -1;
        }
    }

    @Override
    public void newGame() {
        //String array ="";
        /*for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                array+="["+handPlayed[i][k]+"]";
            }
            array+="\n";
        }*/
        //System.out.println(array);
        handPlayed=new Boolean[ticTacViewDyn.getLength()][ticTacViewDyn.getLength()];
        winner=0;
    }

    @Override
    public void aiPlay() {

    }

    @Override
    public Boolean getPlayer() {
        return player;
    }

    @Override
    public Button getButtonText(String btnId) {return null;};
}
