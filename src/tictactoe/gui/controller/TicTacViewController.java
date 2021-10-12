/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import tictactoe.bll.GameBoard;
import tictactoe.bll.GameBoardAI;
import tictactoe.bll.IGameModel;

/**
 *
 * @author Stegger
 */
public class TicTacViewController implements Initializable
{

    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    public GridPane gridPane;

    //ImageView imgX = new ImageView(getClass().getResource("src/tictactoe/gui/images/imgX.png").toString());
    //ImageView imgY = new ImageView(getClass().getResource("src/tictactoe/gui/images/imgY.png").toString());
    //imgView.setFitWidth(75);
    //imgView.setFitHeight(100);
    private static final String TXT_PLAYER = "Player: ";
    private IGameModel game;
    private GameBoard gameBoard;
    //private int currentPlayer=1;
    private String currentPlayer= "1";
    private String playerDisplayed="1";
    private int Offset=0;
    public ObservableList<Node> testBut;

    public ActionEvent actionTest;
    private int numberOfTurns=0;
    String gameType;




    public TicTacViewController getTicTacToeController(){
        return this;
    }
    public GridPane getGridPane() {
        return gridPane;
    }
    @FXML
    private void handleButtonAction(ActionEvent event)
    {

        actionTest=event;
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            int player;
            if (!game.isGameOver()) {
                 player = game.getNextPlayer();

            } else {
                 player = currentPlayer.equals("1")?1:2;
            }
            numberOfTurns++;


            if (game.play(c, r))
            {

                if (game.isGameOver())
                {

                        String xOrO;
                        Button btn = (Button) event.getSource();
                        if(gameType.equals("2 Players")) {
                            currentPlayer = player==1? "1":"2";
                            xOrO = player == 0 ? "X" : "O";
                        } else {
                            currentPlayer = player==1? "1":"A.I";
                            xOrO = player == 0 ? "X" : "A.I";
                        }

                        btn.setText(xOrO);


                    int winner = game.getWinner();
                    displayWinner(winner);

                }
                else
                {

                    String xOrO;
                    Button btn = (Button) event.getSource();
                    if(gameType.equals("2 Players")) {
                        currentPlayer = player==0 ? "1":"2";
                        playerDisplayed= currentPlayer.equals("1")?"A.I":"1";
                        xOrO = player == 0 ? "X" : "O";
                    } else {
                        currentPlayer = player==1? "1":"A.I";
                        playerDisplayed= currentPlayer.equals("1")?"2":"1";
                        xOrO = player == 0 ? "X" : "A.I";
                    }

                    btn.setText(xOrO);
                    setPlayer();
                }

            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public ActionEvent getActionE() {
        return actionTest;
    }
    public void handleButtonActionAI(){
       //Button test = new Button();
       //test.setId(testBut.get(0).getId());
        //test.setId("1");
        //System.out.println(testBut);

        //System.out.println(this);
        //Node node = gridPane.getChildren();
        //Button btnTest = (Button) node;
        //System.out.println(btnTest);
    }

    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gameType="2 Players";
        //gameType="A.I";
        if(gameType.equals("2 Players")) {
            game = new GameBoard(this);
        } else {
            game = new GameBoardAI(this);
        }

        playerDisplayed = currentPlayer.equals("1")?"2":"1";
        game.isGameOver();

        setPlayer();
    }

    private void setPlayer()
    {
        //game.getNextPlayer();
        //lblPlayer.setText(TXT_PLAYER + ""+(gameType.equals("2 Players")?(currentPlayer=="1"?"2":"1"):currentPlayer=="1"?"1":"A.I"));
        lblPlayer.setText(TXT_PLAYER + ""+(gameType.equals("2 Players")?(playerDisplayed=="1"?"2":"1"):playerDisplayed=="1"?"1":"A.I"));

    }

    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";

                break;
            default:
                message = (gameType.equals("2 Players")?(winner==1?"Player 2":"Player 1"):winner==2?"A.I":"Player 1") + " wins!!!";

                break;
        }
        lblPlayer.setText(message);
    }

    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
            Offset=0;
            currentPlayer="1";
        }
    }

}
