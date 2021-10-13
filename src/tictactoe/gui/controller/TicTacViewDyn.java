package tictactoe.gui.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tictactoe.bll.GameBoardDyna;
import tictactoe.bll.GameBoardDynaAI;
import tictactoe.bll.IGameModel;
import tictactoe.bll.IGameModelDyna;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TicTacViewDyn implements Initializable {

    public Label lblPlayer;
    private IGameModelDyna game;
    public VBox vBoxCenter;
    public BorderPane borderPane;
    int length=6;
    int OffsetButton= 1;
    private static final String TXT_PLAYER = "Player: ";
    private static int gameStarted=0;
    private Boolean[][] handPlayed;
    private Boolean[][] handPlayedSim;
    private boolean notHandledYet;
    List<HBox> hBoxList = new ArrayList<>();
    List<Button> buttons = new ArrayList<>();
    String gameType;
    private String currentPlayer;
    private String playerDisplayed;
    private int Offset=0;
    Boolean player=true;


    public BorderPane getBorderPane() {
        return borderPane;
    }
    public int getLength() {
        return length;
    }
    public List<Button> getGameButton() {
        return this.buttons;
    }
    public void handleButtonAction(Button btn) {

        String[] colRow = getColRow(btn.getId());
        try
        {

            int r = Integer.parseInt(colRow[0]);
            int c = Integer.parseInt(colRow[1]);


            if (!game.isGameOver()) {
                player = game.getPlayer();
               // System.out.println("In TicTac Toe Player : "+player);

            }
            //numberOfTurns++;


            if (game.play(c, r) && Offset==0) {
                if(player){//I do this in order to call the execution of aiPlay() and reinitialize the value of the current player to the correct one.
                    //In order to continue the treatment of the information.
                    //there should be a simpler method to do it, however, this one has the advantage of having control of the happening as
                    //the HandleButtonControl method give a clear way via the waiting of action event to control the flow of exchange between the player and the AI.

                    game.play(c,r);
                }
                if (game.isGameOver()) {
                   // System.out.println("You are here");
                    if(Offset==0) {
                        String xOrO;

                        if(gameType.equals("2 Players")) {
                            currentPlayer = player? "1":"2";
                            xOrO = player ? "X" : "O";
                            //xOrO = !player ? "O": "X";
                        } else {
                            currentPlayer = player? "1":"A.I";
                            xOrO = player  ? "X" : "A.I";
                        }
                        btn.setTextFill(Color.valueOf("blue"));
                        btn.setText(xOrO);

                        Offset++;
                    }
                    Offset++;
                    int winner = game.getWinner();
                    displayWinner(winner);

                }
                else if(Offset==0)
                {

                    String xOrO;

                    if(gameType.equals("2 Players")) {
                        game.readGame(c,r);
                        currentPlayer = player? "1":"2";
                        playerDisplayed= currentPlayer.equals("1")?"A.I":"1";
                        xOrO = player ? "X" : "O";
                    } else {
                        currentPlayer = player ? "1":"A.I";
                        playerDisplayed= currentPlayer.equals("1")?"2":"1";
                        xOrO = player ? "X" : "A.I";
                    }
                    btn.setTextFill(Color.valueOf("blue"));
                    btn.setText(xOrO);
                    if(gameType.equals("2 Players")) {
                        if(playerDisplayed.equals("1")){
                            playerDisplayed="2";
                        } else {
                            playerDisplayed="1";
                        }
                    } else {
                        if(playerDisplayed.equals("1")){
                            playerDisplayed="A.I";
                        } else {
                            playerDisplayed="1";
                        }
                    }

                    setPlayer();
                }

            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


    }
     private void setPlayer()
    {
        //lblPlayer.setText(TXT_PLAYER + ""+(gameType.equals("2 Players")?(currentPlayer=="1"?"2":"1"):currentPlayer=="1"?"1":"A.I"));
        lblPlayer.setText(TXT_PLAYER + ""+(gameType.equals("2 Players")?(playerDisplayed=="1"?"2":"1"):playerDisplayed=="1"?"1":"A.I"));
        //System.out.println(TXT_PLAYER + ""+(gameType.equals("2 Players")?(playerDisplayed=="1"?"2":"1"):playerDisplayed=="1"?"1":"A.I"));
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
    private String[] getColRow(String textBtn){
        String[] arrayBtn = textBtn.split("_");
        return new String[] {arrayBtn[1],arrayBtn[3]};
    }
    private void createBoard(int length) {
        vBoxCenter.setAlignment(Pos.CENTER);
        vBoxCenter.prefHeight(800);
        vBoxCenter.setPrefHeight(800);
        vBoxCenter.setPrefWidth(800);
        vBoxCenter.setSpacing(5);

        //vBoxCenter.set
        for (int i = 0; i < length; i++) {
            HBox hb=new HBox();
            hb.setId("L_"+i+"_");
            hb.setSpacing(5);
            hb.setPrefWidth(vBoxCenter.getPrefWidth());
            //hb.set
            hb.setAlignment(Pos.CENTER);
            for (int k = 0; k < length; k++) {
                Button btn = new Button();

                btn.setPrefWidth(hb.getPrefWidth()/length);
                btn.setPrefHeight(hb.getPrefWidth()/length);
                btn.setGraphicTextGap(0);
                btn.setLineSpacing(0);
                //btn.setMinWidth(30);
                //btn.setMinHeight(30);
                btn.setUserData("");
                System.out.println("Button size = "+btn.getPrefWidth());
                System.out.println((btn.getPrefWidth()/length)-10);
                Font f = Font.font("Verdana", FontWeight.BOLD, (btn.getPrefWidth()/(length+OffsetButton)));
                btn.setFont(f);
                btn.setOnAction(e->handleButtonAction(btn));
                btn.setId(hb.getId()+"C_"+k);
                btn.setUserData(btn.getId());
                buttons.add(btn);
                hb.getChildren().add(btn);
            }
            hBoxList.add(hb);
            //vBoxCenter
            vBoxCenter.getChildren().add(hb);
        }
        for (HBox hb: hBoxList) {
            //System.out.println(hb.getChildren());
        }
        gameStarted=1;


    }

    public void changeButtonText(Button btn) {
        btn.getId();
        btn.setText("O");

    }
    public boolean setButtonText(String btnId, String text) {
        for (Button b: buttons) {
            if(b.getId().equals(btnId)){
                b.setTextFill(Color.valueOf("red"));
                b.setText(text);
                return true;
            }
        }
        return false;
    }
    public Button getButtonText(String btnId) {
        for (Button b: buttons) {
            if(b.getId().equals(btnId)){

                return b;
            }
        }
        return null;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //if(gameStarted<=2) {
            createBoard(length);
        //}
        //setButtonText();
        //handPlayed = new Boolean[length][length];
        //gameType="2 Players";
        gameType="A.I";
        if(gameType.equals("2 Players")) {
            game= new GameBoardDyna(this);
        } else {
            game= new GameBoardDynaAI(this);

        }

        setPlayer();
      /*  long begin = System.nanoTime();
        //Starting the watch
        Optional<Button> testBtn = buttons.stream().
                filter(p -> p.getId().equals("L_7_C_8")).
                findFirst();

        Button testFinal = testBtn.get();
        testFinal.setText("H");
        //End time
        long end = System.nanoTime();

        long time = end-begin;
        System.out.println();
        System.out.println("Elapsed Time: "+time +" nano seconds");

        long beginFor = System.nanoTime();
        //Starting the watch
        for (Button b: buttons) {
            if(b.getId().equals("L_7_C_8")){
                b.setText("J");
            }
        }
        //End time
        long endFor = System.nanoTime();

        long timeFor = endFor-beginFor;
        System.out.println();
        System.out.println("Elapsed TimeFor: "+timeFor +" nano seconds");*/


        //buttons.stream().filter(button -> {button.getId("L_7_C_8")}).findFirst();
        //System.out.println(buttons.get(0).getId());

    }
    public void handleNewGame(ActionEvent actionEvent) {

        game.newGame();
        Offset=0;
        setPlayer();
        clearBoard();



    }
    public void clearBoard() {
        for (Node node : vBoxCenter.getChildren())
        {
            HBox hb = (HBox) node;
            for (Node n : hb.getChildren()) {
                Button button = (Button) n;
                ((Button) n).setText("");
            }
        }
    }
}
//TODO: Clean up the code of all the rubish amassed at the end of it.

//    public Event dispatchEvent(Event event, EventDispatchChain tail) {
//        // capturing phase, can handle / modify / substitute / divert the event
//
//        if (notHandledYet) {
//            // forward the event to the rest of the chain
//            event = tail.dispatchEvent(event);
//
//            if (event != null) {
//                // bubbling phase, can handle / modify / substitute / divert
//                // the event
//            }
//        }
//
//        return notHandledYet ? event : null;
//
//    }



