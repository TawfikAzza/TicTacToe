package tictactoe.bll;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;
import tictactoe.gui.controller.TicTacViewDyn;

import java.util.*;

public class GameBoardDynaAI implements IGameModelDyna{

    TicTacViewDyn ticTacViewDyn;
    BorderPane borderPane;
    private Boolean player=true;
    private Boolean player2=false;
    private Boolean currentPlayer=player;
    private Boolean[][] handPlayed;
    List<Button> buttons = new ArrayList<>();
    private int length;
    private int winner=-1;
    int colPlayed;
    int rowPlayed;
    String gameType = "AI";

    public GameBoardDynaAI(TicTacViewDyn t) {

        this.ticTacViewDyn = t;
        this.length=ticTacViewDyn.getLength();
        handPlayed=new Boolean[ticTacViewDyn.getLength()][ticTacViewDyn.getLength()];
        borderPane=ticTacViewDyn.getBorderPane();
        this.buttons=ticTacViewDyn.getGameButton();

    }

    @Override
    public Boolean getNextPlayer() {
        System.out.println("GetNext Called "+currentPlayer);
        if(player) {

            currentPlayer=player2;
            return player2;
        } if(!player) {

            currentPlayer=player;
            return player;
        }

        return false;
    }

    @Override
    public boolean play(int col, int row) {
        colPlayed=col;
        rowPlayed=row;
        if(currentPlayer) {
            if(handPlayed[row][col]==null){
                handPlayed[row][col]=player;
                currentPlayer=false;
                return true;
            } else {
                return false;

            }
        } else if(gameType.equals("AI")){
            aiPlay();
            currentPlayer=true;
        } else {
            currentPlayer=true;
            return true;
        }
        return false;
    }

    public void aiPlay() {
        List<BlockingPosition> blockingPosition = new ArrayList<>();
        HashSet<Button> hashButton = new HashSet<>(buttons);
        List<String> possiblePosition= new ArrayList<>();
        List<EmptyPosition> emptyPosition=new ArrayList<>();
        List<Integer> possibleLine = new ArrayList<>();
        List<Integer> possibleColumn = new ArrayList<>();
        boolean flagLineUsed=false;
        boolean flagColUsed=false;
        //TODO: Hard and not at all optimised coding ahead !!!!!
        // The way I go about things may really be better done, I'll try to explain how I go about it
        // In order to no be lost in the future and for the potentials people who will have the bad idea of looking into it...
        // Here goes:
        // In order to log every possible move the AI can make, I am going to first try making a list of every possible blocking points the AI should
        // remember in order to not lose too easily...
        // for that I created a BlockingPosition class which will be in charge of storing every button I can find which are in the way of the opponent
        // way of victory.
        // I'll store every buttons comtained in every  Line, Row and Diagonal which are occupied only by the opponent, I'll also store the empty position possible in order to get the
        // AI a measure of liberty via randomness in order for it to try maybe new moves through a simulation array that I intend to create.
        // I need for that a whole lot of differents int variables in order to stock the number of free spaces the gameBoard has, as well as the number of button/position occupied
        // and other int type arrays to store the number of occurence of a player in colonnes, I unfortunatly don't know how to do it
        // differently.
        // Let's try it. (I'll try to comment the code after it works a little...

        int humanPlayerInRow=0;
        int AIPlayerInRow=0;
        int numPlayerInRow=0;
        int numFreePlacesRow=0;

        int[] arrayHumanPlayerInRow=new int[handPlayed.length];
        int[] arrayAIPlayerInRow = new int[handPlayed.length];
        int[] numPlayerPerRow = new int[handPlayed.length];
        int[] arrayNumFreePlacesInRow = new int[handPlayed.length];
        int[] arrayNumFreePlacesInCOl = new int[handPlayed.length];
        int[] humanPlayerInCol = new int[handPlayed.length];
        int[] AIPlayerInCol = new int[handPlayed.length];
        int[] arrayFreePlacesCol = new int[handPlayed.length];
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                if(handPlayed[i][k]==null){
                    arrayFreePlacesCol[k]+=1;
                    numFreePlacesRow++;
                    flagLineUsed=true;

                    possiblePosition.add("L_" + i + "_C_" + k);
                    emptyPosition.add(new EmptyPosition(i,k,"L_" + i + "_C_" + k));
                }
                if(handPlayed[i][k]==player){
                    humanPlayerInCol[k]++;
                    humanPlayerInRow++;
                    numPlayerInRow++;
                }
                if(handPlayed[i][k]==player2){
                    AIPlayerInCol[k]++;
                    AIPlayerInRow++;
                    if(numPlayerInRow==1){
                        numPlayerInRow++;
                    }
                }
                arrayNumFreePlacesInRow[i]=numFreePlacesRow;
                arrayHumanPlayerInRow[i]=humanPlayerInRow;
                arrayAIPlayerInRow[i]=AIPlayerInRow;
                numPlayerPerRow[i]=numPlayerInRow;


            }

            if(flagLineUsed){
                possibleLine.add(i);
            }

            AIPlayerInRow=0;
            humanPlayerInRow=0;
            numPlayerInRow=0;
            numFreePlacesRow=0;

        }
        //TODO: The empty Position filling routine seems to fail when the number of position is low, I have to look that in detail.



        //TODO: The gathering of datas is done, I'll now try to make it useable...
        //TODO: What we are looking or are blocking points,
        // Meaning that we must seek the positions of weaknesses and cover them
        // I start by making a check of every position which contains a row or col
        // which have a human player in it and no AI present. I store how many free spaces there are on it, and will decide how to treat
        // the situation based on the number of free spaces left on the row/col.

        for (int i = 0; i < handPlayed.length; i++) {
            System.out.println("Number of free places in row :"+arrayNumFreePlacesInRow[i]);
            for (int k = 0; k < handPlayed.length; k++) {
                if(arrayNumFreePlacesInRow[i]!=0) {
                    if(arrayHumanPlayerInRow[i]!=0 && humanPlayerInCol[k] !=0){
                        for (int h = 0; h < k; h++) {
                            if(handPlayed[i][h]==null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + i + "_C_" + h),i,h,arrayNumFreePlacesInRow[i],arrayFreePlacesCol[h]
                                    ,arrayHumanPlayerInRow[i],humanPlayerInCol[h],arrayAIPlayerInRow[i],AIPlayerInCol[h]));
                        }
                        for (int j = k+1; j < handPlayed.length ; j++) {
                            if(handPlayed[i][j]==null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + i + "_C_" + j),i,j,arrayNumFreePlacesInRow[i],arrayFreePlacesCol[j]
                                    ,arrayHumanPlayerInRow[i],humanPlayerInCol[j],arrayAIPlayerInRow[i],AIPlayerInCol[j]));
                        }
                        for (int h = 0; h < i; h++) {
                            if(handPlayed[h][k]==null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + h + "_C_" + k),h,k,arrayNumFreePlacesInRow[h],arrayFreePlacesCol[k]
                                    ,arrayHumanPlayerInRow[h],humanPlayerInCol[k],arrayAIPlayerInRow[h],AIPlayerInCol[k]));
                        }
                        for (int j = i+1; j < handPlayed.length; j++) {
                            if(handPlayed[j][k]==null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + j + "_C_" + k),j,k,arrayNumFreePlacesInRow[j],arrayFreePlacesCol[k]
                                    ,arrayHumanPlayerInRow[j],humanPlayerInCol[k],arrayAIPlayerInRow[j],AIPlayerInCol[k]));
                        }
                    }

                }
            }
        }
        // Note: That must be what brainfuck means, I aged 10 years sorting my heads out about what to do or try....
        // TODO: I should have in my list only the positions as well as the associated positions which contains only humans player and no AI in it, I also stored the number
        //  of free places contained in its row and columns...should be enough to defend the poor AI...

        //TODO: I have to implement The Array List assciated with the attack and the one for the strategies if I can find a way to implement it...
        System.out.println("Blocking position List size = "+blockingPosition.size());
       /* for (BlockingPosition buttonName : blockingPosition) {
            System.out.println(" Button name : "+buttonName.getButton()
                    +" Row :"+buttonName.getRow()
                    +" Col : "+buttonName.getCol()
                    +" free place On ROW : "+buttonName.getFreePlacesOnRow()
                    +" free place On  COL : "+buttonName.getFreePlacesOnCol()
                    +" Human player In ROW : "+buttonName.getNumHumanPlayerInRow()
                    +" Human player in COL : "+buttonName.getNumHumanPlayerInCol()
                    +" AI player in ROW :"+buttonName.getNumAIPlayerInRow()
                    +" AI player in COL :"+ buttonName.getNumAIPlayerInCol()
                    );
        }*/

        System.out.println("--------Array col ----------");
        for (int i = 0; i < arrayFreePlacesCol.length; i++) {
            System.out.println("Col "+i+" has "+arrayFreePlacesCol[i]+"Free spaces");
        }
        System.out.println("--------Array Col end -------");
      //  List<Pair<BlockingPosition, Integer>> priorityList = new Pair<BlockingPosition,Integer>();
       /* if(priorityList.size()==0) {
            priorityList.add(new Pair<>(blockingPosition.get(i),1));
        }*/

        for (int i = 0; i < blockingPosition.size(); i++) {
            for (int k = 0; k < blockingPosition.size(); k++) {
                if(blockingPosition.get(i).getButton().getId().equals(blockingPosition.get(k).getButton().getId())) {
                    if(blockingPosition.get(i).getPriority()==0) {
                       blockingPosition.get(i).setPriority(blockingPosition.get(i).getPriority()+1);
                    } else {
                        blockingPosition.get(i).setPriority(blockingPosition.get(i).getPriority()+1);
                        blockingPosition.remove(blockingPosition.get(k));
                    }
                }
            }
        }
        //TODO: I should have an array of priority based on the number of times a button appear on the list of
        // Blocking positions....
        // now to make the AI move according to the order of priority...
        if(blockingPosition.size()!=0) {
        System.out.println("Blocking position List size = "+blockingPosition.size());
        for (BlockingPosition buttonName : blockingPosition) {
            System.out.println(" Button name : "+buttonName.getButton()
                    +" Row :"+buttonName.getRow()
                    +" Col : "+buttonName.getCol()
                    +" Priority :"+buttonName.getPriority()
                    +" free place On ROW : "+buttonName.getFreePlacesOnRow()
                    +" free place On  COL : "+buttonName.getFreePlacesOnCol()
                    +" Human player In ROW : "+buttonName.getNumHumanPlayerInRow()
                    +" Human player in COL : "+buttonName.getNumHumanPlayerInCol()
                    +" AI player in ROW :"+buttonName.getNumAIPlayerInRow()
                    +" AI player in COL :"+ buttonName.getNumAIPlayerInCol()
            );
        }

            blockingPosition.sort(Comparator.comparing(BlockingPosition::getPriority));

            System.out.println("After Try Sorting :");
            for (BlockingPosition buttonName : blockingPosition) {
                System.out.println(" Button name : " + buttonName.getButton()
                        + " Row :" + buttonName.getRow()
                        + " Col : " + buttonName.getCol()
                        + " Priority :" + buttonName.getPriority()
                        + " free place On ROW : " + buttonName.getFreePlacesOnRow()
                        + " free place On  COL : " + buttonName.getFreePlacesOnCol()
                        + " Human player In ROW : " + buttonName.getNumHumanPlayerInRow()
                        + " Human player in COL : " + buttonName.getNumHumanPlayerInCol()
                        + " AI player in ROW :" + buttonName.getNumAIPlayerInRow()
                        + " AI player in COL :" + buttonName.getNumAIPlayerInCol()
                );
            }
        }
        int highestPriority =0;

        //TODO: Implement this for later use in the process, as soon as the nextPlayer problem is solved....
        System.out.println("Before shuffle");
        Collections.shuffle(possiblePosition);
        Collections.shuffle(possibleLine);
        Collections.shuffle(possibleColumn);

        //TODO up to here, I need this for the next part of the AI algorythme...
        // Don't know if this will be used, for now it is just a try...

        Collections.shuffle(emptyPosition);
        possibleLine= new ArrayList(new HashSet(possibleLine));
        possibleColumn=new ArrayList(new HashSet(possibleColumn));
        System.out.println("Blocking size = "+blockingPosition.size());
        System.out.println("Empty Position size = "+emptyPosition.size());
        if(blockingPosition.size()!=0) {
            handPlayed[blockingPosition.get(blockingPosition.size()-1).getRow()][blockingPosition.get(blockingPosition.size()-1).getCol()] = player2;
            ticTacViewDyn.setButtonText(blockingPosition.get(blockingPosition.size()-1).getButton().getId(), "A.I");
        } else if(emptyPosition.size()!=0){
            Random rand = new Random();

            handPlayed[blockingPosition.get(rand.nextInt(emptyPosition.size())).getRow()][emptyPosition.get(rand.nextInt(emptyPosition.size())).getCol()] = player2;
            ticTacViewDyn.setButtonText(emptyPosition.get(rand.nextInt(emptyPosition.size())).getButtonId(), "A.I");
       //     handPlayed[blockingPosition.get(emptyPosition.size() - 1==0?emptyPosition.size() - 1:0).getRow()][emptyPosition.get(emptyPosition.size() - 1==0?emptyPosition.size() - 1:0).getCol()] = player2;
       //     ticTacViewDyn.setButtonText(emptyPosition.get(emptyPosition.size() - 1==0?emptyPosition.size() - 1:0).getButtonId(), "A.I");
        }
        //ticTacViewDyn.setButtonText("L_" + blockingPosition.get(0).getRow() + "_C_"+blockingPosition.get(0).getCol(),"A.I");
        System.out.println("After setText");
        currentPlayer=true;
        System.out.println("Buttons size : "+buttons.size());
        //ticTacViewDyn.handleButtonAction(getButtonText(emptyPosition.get(0).getButtonId()));
        String randomButton;

        boolean flagButton =false;
        possiblePosition.clear();
        possibleLine.clear();
        possibleColumn.clear();
        emptyPosition.clear();


        //TODO: solve this enigma : the array handPlayed doesn't update just after the value has been inputed, but the turn after....
        // why?
        // maybe revert from Boolean to integer in the array ?

        String array ="";
        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                array+="["+handPlayed[i][k]+"]";
            }
            array+="\n";
        }
        System.out.println(array);


       // getNextPlayer();
    }

    @Override
    public void readGame(int col, int row) {
        handPlayed[row][col]=player;
    }

    @Override
    public Boolean getPlayer() {
        return currentPlayer;
    }

    @Override
    public Button getButtonText(String btnId) {
        for (Button btn: buttons) {
            if(btn.getId().equals(btnId)) {
                return btn;
            }
        }
        return null;
    }

    private String createButtonId(int row, int col){
        return "L_"+row+"C_"+col;
    }

    @Override
    public boolean isGameOver() {

        boolean isGameOver=false;
        int testDiagInvPlayer1=0;
        int testDiagInvPlayer2=0;
        for(int i = handPlayed.length-1; i > -1; i--) {
            if(handPlayed[i][(handPlayed.length-1)-i]==player) {
                testDiagInvPlayer1++;
            } else {
                testDiagInvPlayer1=0;
            }
            if(testDiagInvPlayer1==handPlayed.length){
                System.out.println("Win Inv Diagonal");
                winner=1;
                return true;
            }
            if(handPlayed[i][(handPlayed.length-1)-i]==player2) {
                testDiagInvPlayer2++;
            } else {
                testDiagInvPlayer2=0;
            }
            if(testDiagInvPlayer2==handPlayed.length){
                System.out.println("Win Inv Diagonal");
                winner=2;
                return true;
            }
        }
        int testVerticalPlayer1=0;
        int testVerticalPlayer2=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testVerticalPlayer1=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[k][i] == player){
                    testVerticalPlayer1++;
                } else {
                    testVerticalPlayer1=0;
                }
                if(testVerticalPlayer1==handPlayed.length) {
                    System.out.println("Win Vertical");
                    winner=1;
                    return true;
                }
                if(handPlayed[k][i] == player2){
                    testVerticalPlayer2++;
                } else {
                    testVerticalPlayer2=0;
                }
                if(testVerticalPlayer2==handPlayed.length) {
                    System.out.println("Win Vertical");
                    winner=2;
                    return true;
                }
            }
            testVerticalPlayer2=0;
        }
        int testHorizontalPlayer1=0;
        int testHorizontalPlayer2=0;
        for (int i = 0; i < handPlayed.length; i++) {
            testHorizontalPlayer1=0;
            for (int k = 0; k < handPlayed.length; k++) {

                if(handPlayed[i][k] == player){
                    testHorizontalPlayer1++;
                } else {
                    testHorizontalPlayer1=0;
                }
                if(testHorizontalPlayer1==handPlayed.length) {
                    winner=1;
                    System.out.println("Win Horizontal");
                    return true;
                }
                if(handPlayed[i][k] == player2){
                    testHorizontalPlayer2++;
                } else {
                    testHorizontalPlayer2=0;
                }
                if(testHorizontalPlayer2==handPlayed.length) {
                    winner=2;
                    System.out.println("Win Horizontal");
                    return true;
                }
            }
            testHorizontalPlayer2=0;
        }
        int testDiagPlayer1=0;
        int testDiagPlayer2=0;
        for(int i = 0; i < handPlayed.length; i++) {
            if(handPlayed[i][i]==player) {
                testDiagPlayer1++;
            } else {
                testDiagPlayer1=0;
            }
            if(testDiagPlayer1==handPlayed.length){
                winner=1;
                System.out.println("Win Diagonal");
                return true;
            }
            if(handPlayed[i][i]==player2) {
                testDiagPlayer2++;
            } else {
                testDiagPlayer2=0;
            }
            if(testDiagPlayer2==handPlayed.length){
                System.out.println("Win Diagonal");
                winner=2;
                return true;
            }
        }
        for (Boolean[] booleans : handPlayed) {
            for (int k = 0; k < handPlayed.length; k++) {
                if (booleans[k] != null) {
                    isGameOver = true;
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
        handPlayed=new Boolean[ticTacViewDyn.getLength()][ticTacViewDyn.getLength()];
        currentPlayer=player;
       // buttons.clear();
        winner=-1;
    }
}
