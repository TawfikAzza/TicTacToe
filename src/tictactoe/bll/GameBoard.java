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

    public int player=1;
    int currentPlayer=1;
    int[][] handPlayed = { {-1,-1,-1} , {-1,-1,-1} , {-1,-1,-1}};
    int colPlayed;
    int rowPlayed;
    int winner=-1;

    GameBoard gameBoard;
    Button[] btnArray = new Button[9];
    public GameBoard(TicTacViewController t) {
       this.ticTacToeController =t;
        ticTacToeController=ticTacToeController.getTicTacToeController();
    }
    public GameBoard() {}
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {
        if(player == 1) {
            currentPlayer=1;
            return 0;
          } else {
            currentPlayer=0;
            return 1;
        }

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


        colPlayed=col;
        rowPlayed=row;
        if(handPlayed[row][col]==-1){
            handPlayed[row][col]=player;
            player=getNextPlayer();
            isGameOver();//done in order to update the array of position and for the check to be made on all the necessary fields
            //I must admit, I don't know why it doesn't work without it (I mean the test on the TicTacToe,
            // and this is kind of a dirty way to make it work, but that is the only solution I tested that actually
            //work and provide the right results on the test made by Peter...
            return true;
        } else {

            return false;
        }
    }

    public boolean isGameOver()
    {

        boolean isGameOver=false;
        int testDiagInv=0;
        for(int i = handPlayed.length-1; i > -1; i--) {
            if(handPlayed[i][2-i]==currentPlayer) {
                testDiagInv++;
            } else {
                testDiagInv=0;
            }
            if(testDiagInv==3){

                winner=currentPlayer==1?0:1;
                return true;
            }

        }

        int testVertical=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testVertical=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[k][i] == currentPlayer){

                    testVertical++;

                } else {

                    testVertical=0;
                }
                if(testVertical==3) {

                    winner=currentPlayer==1?0:1;;
                    return true;
                }
            }
        }
        int testHorizontal=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testHorizontal=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[i][k] ==currentPlayer){
                    testHorizontal++;
                } else {
                    testHorizontal=0;
                }
                if(testHorizontal==3) {

                    winner=currentPlayer==1?0:1;;
                    return true;
                }
            }
        }
        int testDiag=0;
        for(int i = 0; i < handPlayed.length; i++) {
            if(handPlayed[i][i]==currentPlayer) {
                testDiag++;
            } else {
                testDiag=0;
            }
            if(testDiag==3){

                winner=currentPlayer==1?0:1;;
                return true;
            }
        }
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                if(handPlayed[i][k]!=-1) {
                    isGameOver=true;
                } else if(winner==-1){
                    winner=-1;
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

        if(winner!=-1) {
            if(winner==1) {

                return 1;
            } else {

                return 0;
            }

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
                handPlayed[i][k]=-1;
            }
        }
        winner=-1;
        player=1;
     }

}
