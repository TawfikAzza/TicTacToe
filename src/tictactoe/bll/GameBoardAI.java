package tictactoe.bll;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tictactoe.gui.controller.TicTacViewController;

import java.util.ArrayList;
import java.util.List;

public class GameBoardAI implements IGameModel {



    TicTacViewController ticTacToeController;

    public int player=2;
    int player1=1;
    int playerAI=2;
    int[][] handPlayed = { {0,0,0} , {0,0,0} , {0,0,0}};

    List<EmptyPosition> emptyPositionList = new ArrayList<>();
    int colPlayed;
    int rowPlayed;
    int winner=0;
    int diagLength=0;


    public GameBoardAI(TicTacViewController t) {
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                handPlayed[i][k]=0;
            }
        }
        this.ticTacToeController =t;
    }
    public GameBoardAI() {}
    public void checkLines() {
        for (int i = 0; i < handPlayed.length; i++) {

        }
    }
    @Override
    public int getNextPlayer() {

        Button[] btnArrayTmp = new Button[9];
        Button[][] btnArray = new Button[9][9];
        GridPane gridPane = ticTacToeController.getGridPane();
        int index = 0;
        for (Node n : gridPane.getChildren()) {
            btnArrayTmp[index] = (Button) n;
            index++;
        }
        int indexTab=0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k ++) {
                btnArray[i][k]=btnArrayTmp[indexTab];
                indexTab++;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k ++) {
               // System.out.println("["+i+"] "+"["+k+"] ="+btnArray[i][k].getId());

            }
        }

    /*    int longestWinningLinePlayer1=0;
        int longestWinningLinePlayer2=0;
        String txtLine1;
        String txtLine2;
        String txtLine3;

        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                if(handPlayed[i][k]==player1){
                    longestWinningLinePlayer1++;
                } else if(longestWinningLinePlayer1==1 && k==1 && handPlayed[i][k+1]==player1){
                    System.out.println("["+i+"] "+"["+(k)+"] is a winning horizontal move for Player 1");
                }
                if(longestWinningLinePlayer1==2 && k==1 && handPlayed[i][k+1]==0) {
                    System.out.println("["+i+"] "+"["+(k+1)+"] is a winning horizontal move for Player 1");
                }
                if(handPlayed[i][k]==playerAI){
                    longestWinningLinePlayer2++;
                } else if(longestWinningLinePlayer2==1 && k==1 && handPlayed[i][k+1]==playerAI){
                    System.out.println("["+i+"] "+"["+(k)+"] is a winning horizontal move for AI");
                }
                if(longestWinningLinePlayer2==2 && k==1 && handPlayed[i][k+1]==0) {
                    System.out.println("["+i+"] "+"["+(k+1)+"] is a winning horizontal move for A.I");
                }
                if(handPlayed[i][k]==0) {
                    emptyPositionList.add(new EmptyPosition(i,k));
                }
            }
            longestWinningLinePlayer1=0;
            longestWinningLinePlayer2=0;
        }

        int longestWinningInvLinePlayer1=0;
        int longestWinningInvLinePlayer2=0;

        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = handPlayed.length-1; k > -1; k--) {
                if(handPlayed[i][k]==player1){
                    longestWinningInvLinePlayer1++;
                }
                if(longestWinningInvLinePlayer1==2 && k==1 && handPlayed[i][k-1]==0) {
                    System.out.println("["+i+"] "+"["+(k-1)+"] is a winning inverse horizontal move for Player 1");
                }
                if(handPlayed[i][k]==playerAI){
                    longestWinningInvLinePlayer2++;
                }
                if(longestWinningInvLinePlayer2==2 && k==1 && handPlayed[i][k-1]==0) {
                    System.out.println("["+i+"] "+"["+(k-1)+"] is a winning inverse horizontal move for A.I !");
                }
                if(handPlayed[i][k]==0) {
                    emptyPositionList.add(new EmptyPosition(i,k));
                }
            }
            longestWinningInvLinePlayer1=0;
            longestWinningInvLinePlayer2=0;
        }
        int longestWinningColumnPlayer1=0;
        int longestWinningColumnPlayer2=0;

        for (int i = 0; i < handPlayed.length; i++) {

            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[k][i] ==player1) {
                    longestWinningColumnPlayer1++;
                }
                if (longestWinningColumnPlayer1 == 2 && k == 1 && handPlayed[k+1][i]==0) {
                    System.out.println("[" + (k+1) + "] " + "[" + i + "] is a winning vertical move for Player 1!");
                }
                if(handPlayed[k][i] ==playerAI) {
                    longestWinningColumnPlayer2++;
                }
                if (longestWinningColumnPlayer2 == 2 && k == 1 && handPlayed[k+1][i]==0) {
                    System.out.println("[" + (k+1) + "] " + "[" + i + "] is a winning vertical move for AI!");
                }
                if(handPlayed[k][i]==0) {
                    emptyPositionList.add(new EmptyPosition(i,k));
                }
            }
            longestWinningColumnPlayer1=0;
            longestWinningColumnPlayer2=0;

        }
        int longestWinningInvColumnPlayer1=0;
        int longestWinningInvColumnPlayer2=0;

        for (int i = 0; i < handPlayed.length; i++) {

            for (int k = handPlayed.length-1; k > -1; k--) {

                if(handPlayed[k][i] ==player1) {
                    longestWinningInvColumnPlayer1++;
                }
                if (longestWinningInvColumnPlayer1 == 2 && k == 1 && handPlayed[k-1][i]==0) {
                    System.out.println("[" + (k-1) + "] " + "[" + i + "] is a winning inverse vertical move for Player 1!");
                }
                if(handPlayed[k][i] ==playerAI) {
                    longestWinningInvColumnPlayer2++;
                }
                if (longestWinningInvColumnPlayer2 == 2 && k == 1 && handPlayed[k-1][i]==0) {
                    System.out.println("[" + (k-1) + "] " + "[" + i + "] is a winning inverse vertical move for AI!");
                }
            }
            longestWinningInvColumnPlayer1=0;
            longestWinningInvColumnPlayer2=0;
        }
        int longestWinningDiagPlayer1=0;
        int longestWinningDiagPlayer2=0;
        for(int i = 0; i < handPlayed.length; i++) {
            if(handPlayed[i][i]==player1) {
                longestWinningDiagPlayer1++;
            }
            if(longestWinningDiagPlayer1==2 && i==1 && handPlayed[i+1][i+1]==0){
                System.out.println("[" + (i+1) + "] " + "[" + (i+1) + "] is a winning diagonal move for Player 1!");
            }
            if(handPlayed[i][i]==playerAI) {
                longestWinningDiagPlayer2++;
            }
            if(longestWinningDiagPlayer2==2 && i==1 && handPlayed[i+1][i+1]==0){
                System.out.println("[" + (i+1) + "] " + "[" + (i+1) + "] is a winning diagonal move for AI!");
            }
        }
        int longestWinningReverseDiagPlayer1=0;
        int longestWinningReverseDiagPlayer2=0;
        int length=(handPlayed.length-1);
        for(int i = handPlayed.length-1; i >-1; i--) {
            if(handPlayed[i][i]==player1) {
                longestWinningReverseDiagPlayer1++;
            }
            if(longestWinningReverseDiagPlayer1==2 && i==1 && handPlayed[i-1][i-1]==0){
                System.out.println("[" + (i-1) + "] " + "[" + (i-1) + "] is a winning reverse diagonal move for Player 1!");
            }
            if(handPlayed[i][i]==playerAI) {
                longestWinningReverseDiagPlayer2++;
            }
            if(longestWinningReverseDiagPlayer2==2 && i==1 && handPlayed[i-1][i-1]==0){
                System.out.println("[" + (i-1) + "] " + "[" + (i-1) + "] is a winning reverse diagonal move for AI!");
            }
        }
        int longestWinningInvDiagPlayer1=0;
        int longestWinningInvDiagPlayer2=0;
        for(int i = handPlayed.length-1; i > -1; i--) {
            if(handPlayed[i][length-i]==player1) {
                longestWinningInvDiagPlayer1++;
            }
            if(longestWinningInvDiagPlayer1==2 && i==1 && handPlayed[(length-i)-1][length]==0){
                System.out.println("[" + (i-1) + "] " + "[" + ((length)-(i-1)) + "] is a winning inverse diagonal move for Player 1!");
            }
            if(handPlayed[i][(handPlayed.length-1)-i]==playerAI) {
                longestWinningInvDiagPlayer2++;
            }
            if(longestWinningInvDiagPlayer2==2 && i==1 && handPlayed[(length-i)-1][length]==0){
                System.out.println("[" + (i-1)+ "] " + "[" + ((length)-(i-1)) + "] is a winning inverse diagonal move for AI!");
            }

        }*/
//        int longestWinningReverseInvDiagPlayer1=0;
//        int longestWinningReverseInvDiagPlayer2=0;
//        for(int i = length; i > -1; i--) {
//            if(handPlayed[(length)-i][i]==player1) {
//                longestWinningReverseInvDiagPlayer1++;
//            } else if(handPlayed[(length-(length))][(length-(i-1))]==player1 && i==1 && handPlayed[(length-(i-1))][(length-(i-1))]==player1) {
//                System.out.println("[" + ((length-i)) + "] " + "[" + (((length-i))) + "] is a winning reverse inverse diagonal move for Player 1!");
//            }
//            if(longestWinningReverseInvDiagPlayer1==2 && i==1 && handPlayed[length][((length-i)-1)]==0){
//                System.out.println("[" + ((length-i)+1) + "] " + "[" + (((length-i)-1)) + "] is a winning reverse inverse diagonal move for Player 1!");
//            }
//            if(handPlayed[length-i][i]==playerAI) {
//                longestWinningReverseInvDiagPlayer2++;
//            }
//            if(longestWinningReverseInvDiagPlayer2==2 && i==1 && handPlayed[length][((length-i)-1)]==0){
//                System.out.println("[" + (((length)-i)+1)+ "] " + "[" + ((((length)-i)-1))+ "] is a winning reverse inverse diagonal move for AI!");
//            }
//
//        }
      /*  int longestWinningReverseInvDiagPlayer1=0;
        int longestWinningReverseInvDiagPlayer2=0;
        for(int i = 0; i < length; i++) {
            if(handPlayed[(length-(length-i))][length-i]==player1) {
                longestWinningReverseInvDiagPlayer1++;
            }
            if(longestWinningReverseInvDiagPlayer1==(length) && i==(length-1) && handPlayed[(length-(length-i))+1][(length-i)+1]==0){
                System.out.println("[" + ((length-(length-i))+1)+ "] " + "[" + ((length-i)+1) + "] is a winning reverse inverse diagonal move for Player 1!");
            }
            if(handPlayed[(length-(length-i))][length-i]==playerAI) {
                longestWinningReverseInvDiagPlayer2++;
            }
            if(longestWinningReverseInvDiagPlayer2==2 && i==1 && handPlayed[(length-(length-i))+1][(length-i)+1]==0){
                System.out.println("[" + ((length-(length-i))+1)+ "] " + "[" + ((length-i)+1) + "] is a winning reverse inverse diagonal move for Player AI!");
            }

        }*/
        //TODO: Make a Boolean Object type array in order to store three state (Null, True and false) in order to check if one of the player (associated with true or false)
        // have played the position or if no one played this hand yet (associated with Null as a Boolean array is declared with its entire fields as null)
        // => Jeppe's clever idea !!!

        if(player==1) {
            player = 2;
        } else if(player==2){
            player=1;
        }
       // System.out.println("Player = "+player);
       // System.out.println("In getNext Player-> next player is "+player);
        return player;

    }
    /*private void recurDiagSearch(int rowPlayed, int colPlayed){
        if((rowPlayed>0 && rowPlayed< handPlayed.length) && (colPlayed>0 && colPlayed<handPlayed.length)) {
            if(handPlayed[rowPlayed][colPlayed]==0){
                diagLength++;
                if(diagLength==2){
                    System.out.println("[" + (colPlayed)+ "] " + "[" + (rowPlayed) + "] is a winning diagonal move for Player"+player);
                }
                recurDiagSearch(rowPlayed+1,colPlayed+1);
            } else {
                diagLength=0;
            }
        } else {
            System.out.println("DiagLine = "+diagLength);
        }
    }*/
    /*public void recursRevDiagSearch(int rowPlayed, int colPlayed){
        if((rowPlayed>0 && rowPlayed< handPlayed.length) && (colPlayed>0 && colPlayed<handPlayed.length)) {
            if(handPlayed[rowPlayed][colPlayed]!=0){
                diagLength++;
                if(diagLength==2){
                    System.out.println("[" + (colPlayed)+ "] " + "[" + (rowPlayed) + "] is a winning inverse diagonal move for Player"+player);
                }
                recurDiagSearch(rowPlayed-1,colPlayed+1);
            } else {
                diagLength=0;
            }
        } else {
            System.out.println("DiagLine = "+diagLength);
        }
    }*/
    @Override
    public boolean play(int col, int row) {

        colPlayed=col;
        rowPlayed=row;
       // System.out.println("("+row+")"+" "+"("+col+")");
        diagLength=0;
       // recurDiagSearch(row,col);
       // recursRevDiagSearch(row,col);
        if(handPlayed[row][col]==0){
            handPlayed[row][col]=player;
            return true;
        } else {
            getNextPlayer();
            return false;
        }

    }

    @Override
    public boolean isGameOver() {
        boolean isGameOver=false;
        int testDiagInv=0;
        for(int i = handPlayed.length-1; i > -1; i--) {
            if(handPlayed[i][2-i]==player) {
                testDiagInv++;
            } else {
                testDiagInv=0;
            }
            if(testDiagInv==3){
                winner=player;
                return true;
            }

        }
        int testVertical=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testVertical=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[k][i] ==player){
                    testVertical++;
                } else {
                    testVertical=0;
                }
                if(testVertical==3) {
                    winner=player;
                    return true;
                }
            }
        }
        int testHorizontal=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testHorizontal=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[i][k] ==player){
                    testHorizontal++;
                } else {
                    testHorizontal=0;
                }
                if(testHorizontal==3) {
                    winner=player;
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
            if(testDiag==3){
                winner=player;
                return true;
            }
        }



        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                if(handPlayed[i][k]!=0) {
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

        if(winner!=0) {
            return winner;
        } else {
            return -1;
        }
    }

    @Override
    public void newGame() {
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                handPlayed[i][k]=0;
            }
        }
        winner=0;
        player=2;

    }
}
