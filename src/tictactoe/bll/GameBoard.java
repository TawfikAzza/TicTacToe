/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.bll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tictactoe.gui.controller.TicTacViewController;

import java.lang.reflect.Field;

/**
 *
 * @author Stegger
 */
public class GameBoard implements IGameModel
{
    @FXML
    private GridPane gridPane;
    TicTacViewController ticTacToeController;

    public int player=2;
    int[][] handPlayed = { {0,0,0} , {0,0,0} , {0,0,0}};
    int colPlayed;
    int rowPlayed;
    int winner=0;
    GameBoard gameBoard;
    Button[] btnArray = new Button[9];
    public GameBoard(TicTacViewController t) {
       this.ticTacToeController =t;
    }
    public GameBoard() {}
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        if(player==1) {
            player = 2;
          } else if(player==2){
            player=1;
        }
        return player;
     }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {

        ticTacToeController=ticTacToeController.getTicTacToeController();
        colPlayed=col;
        rowPlayed=row;
        if(handPlayed[row][col]==0){
            handPlayed[row][col]=player;
            return true;
        } else {
            getNextPlayer();
            return false;
        }
    }

    public boolean isGameOver()
    {

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

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        if(winner!=0) {
            return winner;
        } else {
            return -1;
        }
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                handPlayed[i][k]=0;
            }
        }
        winner=0;
        player=2;
        //TODO Implement this method
    }

}
