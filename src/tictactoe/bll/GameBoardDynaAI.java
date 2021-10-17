package tictactoe.bll;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;
import tictactoe.gui.controller.TicTacViewDyn;

import java.util.*;

public class GameBoardDynaAI implements IGameModelDyna {

    TicTacViewDyn ticTacViewDyn;
    BorderPane borderPane;
    private Boolean player = true;
    private Boolean player2 = false;
    private Boolean currentPlayer = true;
    private Boolean[][] handPlayed;
    List<Button> buttons = new ArrayList<>();
    private int length;
    private int winner = -1;
    int colPlayed;
    int rowPlayed;
    String gameType = "AI";
    int winningLength = 4;
    public GameBoardDynaAI(TicTacViewDyn t) {

        this.ticTacViewDyn = t;
        this.length = ticTacViewDyn.getLength();
        handPlayed = new Boolean[ticTacViewDyn.getLength()][ticTacViewDyn.getLength()];
        borderPane = ticTacViewDyn.getBorderPane();
        this.buttons = ticTacViewDyn.getGameButton();

    }

    private void test(int height, int width) {
        int[][] myTable = new int[height][width];
    }

    @Override
    public Boolean getNextPlayer() {

        if (player) {
            currentPlayer = player;
            player = !player;
            return player;
        }
        if (!player) {

            currentPlayer = player;
            player = !player;
            return player;
        }

        return false;
    }

    @Override
    public boolean play(int col, int row) {
        colPlayed = col;
        rowPlayed = row;



        //System.out.println("currentPlayer = "+currentPlayer);
        if (currentPlayer) {
            //System.out.println("TEST COUNT DIAG Left "+countPlayerDiagLeft(handPlayed,row,col,player, winningLength));
            //System.out.println("TEST COUNT DIAG Right "+countPlayerDiagRight(handPlayed,row,col,player, winningLength));
            //System.out.println("TEST COUNT INV DIAG Right "+countPlayerInvDiagRight(handPlayed,row,col,player, winningLength));
            //System.out.println("TEST COUNT INV DIAG Left "+countPlayerInvDiagLeft(handPlayed,row,col,player, winningLength));
            //System.out.println("TEST COUNT Col UP "+countPlayerColUp(handPlayed,row,col,player, winningLength));
            //System.out.println("TEST COUNT Col Down "+countPlayerColDown(handPlayed,row,col,player, winningLength));
            //System.out.println("TEST COUNT row left "+countPlayerRowLeft(handPlayed,row,col,player, winningLength));
            System.out.println("TEST COUNT row left "+countPlayerRowRight(handPlayed,row,col,player, winningLength));
            if (handPlayed[row][col] == null) {
                handPlayed[row][col] = player;
                currentPlayer = false;
                isGameOver();
                return true;
            } else {
                return false;

            }
        } else if (gameType.equals("AI")) {



            aiPlay();
            /*String array ="";
            for (int i = 0; i < handPlayed.length; i++) {
                for (int k = 0; k < handPlayed.length; k++) {
                    array+="["+handPlayed[i][k]+"]";
                }
                array+="\n";
            }
            System.out.println(array);*/
            currentPlayer = true;
        } else {
            currentPlayer = true;
            return true;
        }
        return false;
    }

    public int countPlayerRowRight(Boolean[][] gameBoard, int row, int col, Boolean player, int winningLength) {
        int count = 0;
        int steps=0;
        for (int k = Math.min(col + 1, gameBoard.length); k < gameBoard.length && steps<winningLength; k++) {
            steps++;
            System.out.println("Parsing : ["+(row)+"]["+(k)+"]");
            if (gameBoard[row][k] == player) {
                count++;
            }
        }
        return count;
    }
    public int countPlayerRowLeft(Boolean[][] gameBoard, int row, int col, Boolean player, int winningLength) {
        int count = 0;
        for (int k = Math.max(col - 1, 0)>=winningLength?winningLength-1:Math.max(col - 1, 0); k>-1; k--) {
            System.out.println("Parsing : ["+(row)+"]["+(k)+"]");
            if (gameBoard[row][k] == player) {
                count++;
            }
        }
        return count;
    }
    public int countPlayerColUp(Boolean[][] gameBoard, int row, int col, Boolean player,int winningLength) {
        int count = 0;
        //System.out.println("Math Row = "+Math.max(row - 1, 0)+" Win Length = "+winningLength);
        for (int k = (Math.max(row - 1, 0)>=winningLength?winningLength-1:Math.max(row - 1, 0)); k > -1; k--) {
            System.out.println("Parsing : ["+(k)+"]["+(col)+"]");
            if (gameBoard[k][col] == player) {
                count++;
            }
        }

        return count;
    }

    public int countPlayerColDown(Boolean[][] gameBoard, int row, int col, Boolean player, int winningLength) {
        int count = 0;
        int steps=0;
        for (int k = Math.min(row + 1, gameBoard.length); k < gameBoard.length && steps<winningLength; k++) {
            steps++;
            System.out.println("Parsing : ["+(k)+"]["+(col)+"]");
            if (gameBoard[k][col] == player) {
                count++;
            }
        }

        return count;
    }
    public int countPlayerDiagRight(Boolean[][] gameBoard, int row,int col, Boolean player, int winningLength){
        int count=0;
        for (int i = 1 ; (i < (row>col?gameBoard.length-row: gameBoard.length-col)) && i<winningLength ; i++) {
            System.out.println("Parsing : ["+(row+i)+"]["+(col+i)+"]");
                if (gameBoard[row+i][col+i] == player) {
                    count++;
                }
        }
        return count;
    }
    public int countPlayerDiagLeft(Boolean[][] gameBoard, int row,int col, Boolean player, int winningLength){
        int count=0;
        int rowStart = (gameBoard.length -((gameBoard.length)-row));
        int colStart = (gameBoard.length -((gameBoard.length)-col));
        for (int k = 1; (k <(rowStart>colStart?colStart:rowStart)+1) && k<winningLength  ; k++) {
            System.out.println("Parsing : ["+(row-k)+"]["+(col-k)+"]");
            if (gameBoard[row-k][col-k] == player) {
                count++;
            }
        }
        return count;
    }
    public int countPlayerInvDiagRight(Boolean[][] gameBoard, int row,int col, Boolean player, int winningLength){
        int count=0;
        int length = gameBoard.length;
        for (int k = 1; ((k < (row>col?gameBoard.length-col:gameBoard.length-row)) && ((length-(length-row))-k)>-1 && ((length-(length-col)+k) < 6)) && k<winningLength ; k++) {
            System.out.println("Parsing : ["+((length-(length-row))-k)+"]["+(length-(length-col)+k)+"]");
            if (gameBoard[(length-(length-row))-k][length-(length-col)+k] == player) {
                count++;
            }
        }
        return count;
    }
    public int countPlayerInvDiagLeft(Boolean[][] gameBoard, int row, int col, Boolean player, int winningLength){
        int count=0;

        for (int k = 1; ((length-(length-col)-k >-1)&& ((length-(length-row))+k) <6) && k<winningLength ; k++) {
            System.out.println("Parsing : ["+(row+k)+"]["+(col-k)+"]");

            if (gameBoard[row+k][col-k] == player) {
                count++;
            }
        }
        return count;
    }
    public void aiPlay() {
        //  System.out.println("IN AI PLAY");
        List<BlockingPosition> blockingPosition = new ArrayList<>();
        List<BlockingPosition> blockingDiagPosition = new ArrayList<>();
        List<BlockingPosition> blockingInvDiagPosition = new ArrayList<>();

        HashSet<Button> hashButton = new HashSet<>(buttons);
        List<String> possiblePosition = new ArrayList<>();
        List<EmptyPosition> emptyPosition = new ArrayList<>();
        List<Integer> possibleLine = new ArrayList<>();
        List<Integer> possibleColumn = new ArrayList<>();
        boolean flagLineUsed = false;
        boolean flagColUsed = false;
        //TODO: Hard and not at all optimised coding ahead !!!!!
        // The way I go about things may really be better done, I'll try to explain how I go about it
        // In order to no be lost in the future and for the potentials people who will have the bad idea of looking into it...
        // Here goes:
        // In order to log every possible move the AI can make, I am going to first try making a list of every possible blocking points the AI should
        // remember in order to not lose too easily...
        // for that I created a BlockingPosition class which will be in charge of storing every button I can find which are in the way of the opponent
        // way of victory.
        // I'll store every buttons contained in every  Line, Row and Diagonal which are occupied only by the opponent, I'll also store the empty position possible in order to get the
        // AI a measure of liberty via randomness in order for it to try maybe new moves through a simulation array that I intend to create.
        // I need for that a whole lot of differents int variables in order to stock the number of free spaces the gameBoard has, as well as the number of button/position occupied
        // and other int type arrays to store the number of occurence of a player in colonnes, I unfortunatly don't know how to do it
        // differently.
        // Let's try it. (I'll try to comment the code after it works a little...

        int humanPlayerInRow = 0;
        int AIPlayerInRow = 0;
        int numPlayerInRow = 0;
        int numFreePlacesRow = 0;
        int humanPlayerInDiag = 0;
        int AIPlayerInDiag = 0;
        int numPlayerInDiag = 0;
        int numAIInDiag = 0;
        int numPlayerInInvDiag = 0;
        int numAIInInvDiag = 0;
        int numFreePlacesInDiag = 0;
        int humanPlayerInInvDiag = 0;
        int AIPlayerInInvDiag = 0;

        int numFreePlacesInInvDiag = 0;
        int[] arrayHumanPlayerInRow = new int[handPlayed.length];
        int[] uninterruptedPlayerInRow = new int[handPlayed.length];
        int[] uninterruptedPlayerInCol = new int[handPlayed.length];
        int[] uninterruptedAIInRow = new int[handPlayed.length];
        int[] uninterruptedAIInCol = new int[handPlayed.length];
        int[][] uninterruptedPlayerinDiag = new int[handPlayed.length][handPlayed.length];
        int[][] uninterruptedPlayerinInvDiag = new int[handPlayed.length][handPlayed.length];
        int[][] uninterruptedAIinDiag = new int[handPlayed.length][handPlayed.length];
        int[][] uninterruptedAIinInvDiag = new int[handPlayed.length][handPlayed.length];
        int[] arrayAIPlayerInRow = new int[handPlayed.length];
        int[] numPlayerPerRow = new int[handPlayed.length];
        int[] arrayNumFreePlacesInRow = new int[handPlayed.length];

        int[] arrayNumFreePlacesInCOl = new int[handPlayed.length];
        int[] humanPlayerInCol = new int[handPlayed.length];
        int[] AIPlayerInCol = new int[handPlayed.length];
        int[] arrayFreePlacesCol = new int[handPlayed.length];
        int longestAIRow = 0;
        int longestAICol = 0;
        int longestAIDiag = 0;
        int longestAIInvDiag = 0;
        int longestHumanRow = 0;
        int longestHumanCol = 0;
        int longestHumanDiag = 0;
        int longestHumanInvDiag = 0;

        for (int i = 0; i < handPlayed.length; i++) {
            for (int k = 0; k < handPlayed.length; k++) {
                if (handPlayed[i][k] == null && i == k) {
                    numFreePlacesInDiag += 1;
                }
                if (handPlayed[i][i] == player && i == k) {

                    humanPlayerInDiag++;
                    numPlayerInDiag++;
                }
                if (handPlayed[i][i] == player2 && i == k) {
                    AIPlayerInDiag += 1;
                    numAIInDiag++;
                }

                if (handPlayed[(length - 1) - i][i] == null && (length - 1) - i == k) {
                    numFreePlacesInInvDiag += 1;
                }
                if (handPlayed[(length - 1) - i][i] == player && (length - 1) - i == k) {
                    humanPlayerInInvDiag++;
                    numPlayerInInvDiag++;
                }
                if (handPlayed[(length - 1) - i][i] == player2 && (length - 1) - i == k) {
                    AIPlayerInInvDiag += 1;
                    numAIInInvDiag++;
                }

                if (handPlayed[i][k] == null) {
                    if ((length - 1) - i == k) {
                        if (humanPlayerInInvDiag > longestHumanInvDiag) {
                            //System.out.println("adding 1 to uninterrupted Inv Diag " + humanPlayerInInvDiag);
                            uninterruptedPlayerinInvDiag[(length - 1) - i][k] = humanPlayerInInvDiag;
                            longestHumanInvDiag = humanPlayerInInvDiag;
                        }

                        if (AIPlayerInInvDiag > longestAIInvDiag) {
                            uninterruptedAIinInvDiag[(length - 1) - i][k] = AIPlayerInInvDiag;
                            longestAIInvDiag = AIPlayerInInvDiag;
                        }
                        AIPlayerInInvDiag = 0;
                        humanPlayerInInvDiag = 0;
                    }
                    if (i == k) {
                        if (humanPlayerInDiag > longestHumanDiag) {

                            uninterruptedPlayerinDiag[i][i] = humanPlayerInDiag;
                            longestHumanDiag = humanPlayerInDiag;
                        }

                        if (AIPlayerInDiag > longestAIDiag) {
                            uninterruptedAIinDiag[i][i] = AIPlayerInDiag;
                            longestAIDiag = AIPlayerInDiag;
                        }
                        AIPlayerInDiag = 0;
                        humanPlayerInDiag = 0;
                    }
                    arrayFreePlacesCol[k] += 1;
                    numFreePlacesRow++;
                    if (uninterruptedAIInRow[i] > longestAIRow) {
                        longestAIRow = uninterruptedAIInRow[i];
                    }
                    if (uninterruptedAIInCol[k] > longestAICol) {
                        longestAICol = uninterruptedAIInCol[k];
                    }
                    if (uninterruptedPlayerInCol[k] > longestHumanCol) {
                        longestHumanCol = uninterruptedPlayerInCol[k];
                    }
                    if (uninterruptedPlayerInRow[i] > longestHumanRow) {
                        longestHumanRow = uninterruptedPlayerInRow[i];
                    }
                    uninterruptedPlayerInRow[i] = 0;
                    uninterruptedAIInRow[i] = 0;
                    flagLineUsed = true;

                    possiblePosition.add("L_" + i + "_C_" + k);
                    emptyPosition.add(new EmptyPosition(i, k, "L_" + i + "_C_" + k));
                }
                if (handPlayed[i][k] == player) {
                    uninterruptedPlayerInRow[i]++;
                    humanPlayerInCol[k]++;
                    uninterruptedPlayerInCol[k]++;
                    humanPlayerInRow++;
                    numPlayerInRow++;
                }
                if (handPlayed[i][k] == player2) {
                    uninterruptedAIInRow[i]++;
                    uninterruptedAIInCol[k]++;
                    AIPlayerInCol[k]++;
                    AIPlayerInRow++;
                    if (numPlayerInRow == 1) {
                        numPlayerInRow++;//this one is to count if the AI already played in the same row as the human player.
                    }
                }
                arrayNumFreePlacesInRow[i] = numFreePlacesRow;
                arrayHumanPlayerInRow[i] = humanPlayerInRow;
                arrayAIPlayerInRow[i] = AIPlayerInRow;
                numPlayerPerRow[i] = numPlayerInRow;


            }

            if (flagLineUsed) {
                possibleLine.add(i);
            }

            AIPlayerInRow = 0;
            humanPlayerInRow = 0;
            numPlayerInRow = 0;
            numFreePlacesRow = 0;

        }
        //TODO: The empty Position filling routine seems to fail when the number of position is low, I have to look that in detail.
        // System.out.println("Gathering of datas done.");


        //TODO: The gathering of datas is done, I'll now try to make it useable...
        //TODO: What we are looking for are blocking points,
        // Meaning that we must seek the positions of weaknesses and cover them
        // I start by making a check of every position which contains a row or col
        // which have a human player in it and no AI present. I store how many free spaces there are on it, and will decide how to treat
        // the situation based on the number of free spaces left on the row/col.
        // I had to the total of blocking points possible two more lists which will contain the Diagonal as well as the Inverse Diagonal Positions.
        // This way, the AI will have less weak points and will be able to defend itself better.
        // these lists will be used in emergency by the creation of an BlockingPosition object which will be set at null and used if the åparsing through one of the two list
        // reveals that the Human player succeded in being a threat to the AI in one of the diagonals...Well that's how it should be in theory...

        //Todo: I already have collected the necessary datas in the loops before, the next step is to dissect and use the datas collected.
        // Thanksfully, as the referential is a cartesian one in a two dimensional array, it is possible to target datas accurately.
        // I started by parsing the handPlayed array and from it I gathered the necessary datas to fill the number of free places, number of players in each diagonals
        // as well as if the AI or the player are the only one present on it.
        // from there, I check if the Human is the sole occupant of either Diagonals and if so, I add an entry in the list of blockingDiagonal or
        // Blocking inverse diagonal Blocking position list.
        // there is of course a way to do it more easily, but I just can't find it....
        // I had to create 3 different constructors in the BlickingPosition class alone in order to accomodate the different set of datas I ended up with needing
        // of treatment, I couldn't store everything in the same list as the datas showed would have been contradictory at some points.
        // Especially in the trimming of the list number of entries, as several instances of the same button would show for differents reasons, if for example the row and col
        // are matching and need to be filled the priority would be two, or three, however the same button may be used as a defensive point in the diagonal or inverse diagonal
        // defensive strategy, and the risk of it being getting rid of is real.
        // if I don't do the trimming, the number of instances of the same button will be in the dozen, and it will severly impede the priority process set up for the
        // row and col defense...
        // So I had to create two other lists one for the DIagonal and one for the Inverse diagonal...
        // now that it is implemented, the next stage is the reduction of the winning rule to a line of 4 instead of the length of the array which can
        // increase to up to 10 or more if I can find how to keep it clean (I should work on the graphic interface a little more... too much to do....)

        for (int i = 0; i < handPlayed.length; i++) {
            if (handPlayed[i][i] == null) {
                if (numFreePlacesInDiag != 0) {
                    if (numAIInDiag == 0 && numPlayerInDiag != 0) {
                        blockingDiagPosition.add(new BlockingPosition(getButtonText("L_" + i + "_C_" + i), i, i, arrayNumFreePlacesInRow[i], arrayFreePlacesCol[i]
                                , arrayHumanPlayerInRow[i], uninterruptedPlayerInRow[i], humanPlayerInCol[i], uninterruptedPlayerInCol[i]
                                , arrayAIPlayerInRow[i], uninterruptedAIInRow[i], AIPlayerInCol[i], uninterruptedAIInCol[i], numFreePlacesInDiag
                                , numPlayerInDiag, uninterruptedPlayerinDiag[i][i], numAIInDiag, uninterruptedAIinDiag[i][i]));
                    }//blockingPosition.add(new BlockingPosition(getButtonText("L_" + i + "_C_" + h),i,h,arrayNumFreePlacesInRow[i],arrayFreePlacesCol[h]
                    //    ,arrayHumanPlayerInRow[i],humanPlayerInCol[h],arrayAIPlayerInRow[i],AIPlayerInCol[h]));
                }
                if (numFreePlacesInInvDiag != 0) {
                    if (numAIInInvDiag == 0 && numPlayerInInvDiag != 0) {
                        blockingInvDiagPosition.add(new BlockingPosition(getButtonText("L_" + ((length - 1) - i) + "_C_" + i), ((length - 1) - i), i
                                , arrayNumFreePlacesInRow[i], arrayFreePlacesCol[i]
                                , arrayHumanPlayerInRow[i], uninterruptedPlayerInRow[i], humanPlayerInCol[i], uninterruptedPlayerInCol[i]
                                , arrayAIPlayerInRow[i], uninterruptedAIInRow[i], AIPlayerInCol[i], uninterruptedAIInCol[i], numFreePlacesInDiag
                                , numPlayerInDiag
                                , numPlayerInInvDiag, uninterruptedPlayerinInvDiag[(length - 1) - i][i], numAIInInvDiag, uninterruptedAIinInvDiag[(length - 1) - i][i]));
                    }
                }
            }
        }

        //TODO: Here I do the same for the Col and Rows, with the conditions of the rows or columns being empty of
        // AI input while an input of the player is present.

        for (int i = 0; i < handPlayed.length; i++) {

            for (int k = 0; k < handPlayed.length; k++) {

                if (arrayNumFreePlacesInRow[i] != 0) {
                    if (arrayHumanPlayerInRow[i] != 0 && humanPlayerInCol[k] != 0) {
                        for (int h = 0; h < k; h++) {
                            if (handPlayed[i][h] == null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + i + "_C_" + h), i, h, arrayNumFreePlacesInRow[i], arrayFreePlacesCol[h]
                                        , arrayHumanPlayerInRow[i], humanPlayerInCol[h], arrayAIPlayerInRow[i], AIPlayerInCol[h]));
                        }
                        for (int j = k + 1; j < handPlayed.length; j++) {
                            if (handPlayed[i][j] == null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + i + "_C_" + j), i, j, arrayNumFreePlacesInRow[i], arrayFreePlacesCol[j]
                                        , arrayHumanPlayerInRow[i], humanPlayerInCol[j], arrayAIPlayerInRow[i], AIPlayerInCol[j]));
                        }
                        for (int h = 0; h < i; h++) {
                            if (handPlayed[h][k] == null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + h + "_C_" + k), h, k, arrayNumFreePlacesInRow[h], arrayFreePlacesCol[k]
                                        , arrayHumanPlayerInRow[h], humanPlayerInCol[k], arrayAIPlayerInRow[h], AIPlayerInCol[k]));
                        }
                        for (int j = i + 1; j < handPlayed.length; j++) {
                            if (handPlayed[j][k] == null)
                                blockingPosition.add(new BlockingPosition(getButtonText("L_" + j + "_C_" + k), j, k, arrayNumFreePlacesInRow[j], arrayFreePlacesCol[k]
                                        , arrayHumanPlayerInRow[j], humanPlayerInCol[k], arrayAIPlayerInRow[j], AIPlayerInCol[k]));
                        }
                    }

                }
            }
        }

        //System.out.println("First sorting of dats done ");
        // Note: That must be what brainfuck means, I aged 10 years sorting my heads out about what to do or try....
        // TODO: I should have in my list only the positions as well as the associated positions which contains only humans player and no AI in it, I also stored the number
        //  of free places contained in its row and columns...should be enough to defend the poor AI...

        //TODO: I have to implement The Array List assciated with the attack and the one for the strategies if I can find a way to implement it...

        BlockingPosition emergencyPlay = null;
        for (int i = 0; i < blockingPosition.size(); i++) {
            for (int k = 0; k < blockingPosition.size(); k++) {

                if (blockingPosition.get(i).getButton().getId().equals(blockingPosition.get(k).getButton().getId())) {
                    if ((blockingPosition.get(i).getNumAIPlayerInCol() == 0 && blockingPosition.get(i).getFreePlacesOnCol() == 1) || (blockingPosition.get(i).getNumAIPlayerInRow() == 0 && blockingPosition.get(i).getFreePlacesOnRow() == 1)) {
                        emergencyPlay = blockingPosition.get(i);
                    }
                    if (blockingPosition.get(i).getPriority() == 0) {
                        blockingPosition.get(i).setPriority(blockingPosition.get(i).getPriority() + 1);

                    } else {
                        blockingPosition.get(i).setPriority(blockingPosition.get(i).getPriority() + 1);
                        blockingPosition.remove(blockingPosition.get(k));
                    }
                }
            }
        }
        for (int i = 0; i < blockingDiagPosition.size(); i++) {
            if (blockingDiagPosition.get(i).getNumHumanPlayerInDiag() == 2) {
                emergencyPlay = blockingDiagPosition.get(i);
            }
        }
        if (emergencyPlay == null) {
            for (int i = 0; i < blockingInvDiagPosition.size(); i++) {
                if (blockingInvDiagPosition.get(i).getNumHumanPlayerInInvDiag() == 2) {
                    emergencyPlay = blockingInvDiagPosition.get(i);
                }
            }
        }
        //TODO: I should have an array of priority based on the number of times a button appear on the list of
        // Blocking positions....
        // now to make the AI move according to the order of priority...


        blockingPosition.sort(Comparator.comparing(BlockingPosition::getPriority));

        blockingPosition.sort(Comparator.comparing(BlockingPosition::getNumAIPlayerInRow));
        blockingPosition.sort(Comparator.comparing(BlockingPosition::getNumHumanPlayerInRow));
        blockingPosition.sort(Comparator.comparing(BlockingPosition::getNumAIPlayerInCol));
        blockingPosition.sort(Comparator.comparing(BlockingPosition::getNumHumanPlayerInCol));
        blockingPosition.sort(Comparator.comparing(BlockingPosition::getFreePlacesOnRow));
/*
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
        }*/

        int highestPriority = 0;

        //TODO: Implement this for later use in the process, as soon as the nextPlayer problem is solved....

        Collections.shuffle(possiblePosition);
        Collections.shuffle(possibleLine);
        Collections.shuffle(possibleColumn);
        Collections.shuffle(blockingPosition);
        //TODO up to here, I need this for the next part of the AI algorythme...
        // Don't know if this will be used, for now it is just a try...

        Collections.shuffle(emptyPosition);
        possibleLine = new ArrayList(new HashSet(possibleLine));
        possibleColumn = new ArrayList(new HashSet(possibleColumn));

        currentPlayer = true;

        String randomButton;

        boolean flagButton = false;
        possiblePosition.clear();
        possibleLine.clear();
        possibleColumn.clear();
        emptyPosition.clear();


        //TODO: solve this enigma : the array handPlayed doesn't update just after the value has been inputed, but the turn after....
        // why?
        // Todo: Why commenting everything you ask?
        // because I'm going to try the design pattern approach...

        //The patterns are designed by using the relatives coordinates of the point currently parsed.
        //in order to store the coordinate, I use two arrays: one for the row (or X coordinate)
        //one for the col (or Y coordinate).
        //I also store the values of the width and height of the pattern in order to calculate the necessary Offset
        // for the i and k index used in the for loops this way, it will never (hopefully) go out of bound
        //This way,  the form which contains the pattern can be a square or a rectangle, the only thing which will change is the boundaries of the
        //Row and Col index used to parse though the handPlayed.
        //I then store the values of the two coordinates arrays in two List contained in the DesignPattern class through its constructor.
        //The constructor will fill two distinct lists, one for the X coordinate (or Offset as the coordianates can only
        // really be considered coordinate if the origin is the currently parsed position in the array) and one for the Y.
        // And as the coordinates always comes in pair,the two lists will allways have the same length,
        // thus, the same corresponding values of X and Y at the same index.
        //All I will have to do to compare the coordinates is parsing through the list and see if the entered relative coordinates
        //matches with the values of the current player, if it does, I know that the configuration of the positions of the currentPlayer
        //are the one entered in the designPattern.
        //Well................
        //that's how it should be in theory....

        //Here goes:
        //This is the list which will contain the Patterns described in the class DesignPattern before (this setup allows me to store as many
        // patterns as I want or needs) :
        List<DesignPattern> designPatterns = new ArrayList<>();
        //NOTE:
        //TODO: The problem I see is the fact that I will have to parse through all the patterns each time, there must be a more efficient way to do so, but my
        // intelligence is limited, I will have to ask Jeppe about it....

        //TODO: NOTE THAT X IS ACTUALLY THE COLUMN OFFSET AND Y THE ROW OFFSET IN THIS PATTERN! ! ! !
        // I now understand why Peter inversed the row and col in his play(c,r) method signature, he was actually helping us....bless him, he is a good guy.
        // I had to go through this hell in order to understand the why, I still have a long way to go.

        //Here, I describe the relative coordinates for the diagonal, I know that it starts from the point currently parsed and increment
        //by 1 with each X steps, we have a (X,Y) (X+1,Y+1) (X+2,Y+2) (X+3,Y+3) pattern of points, and it translates as the following
        //for the design pattern of the diagonal:

        int[] rowDiagPos = {0, 1, 2, 3};
        int[] colDiagPos = {0, 1, 2, 3};
        designPatterns.add(new DesignPattern("Diagonal", rowDiagPos, colDiagPos, 4, 4, length));

        //For the inverse diagonal, we have a
        // (X+3,Y) (X+2,Y+1) (X+1,Y+2) (X,Y+3) pattern to match the relatives coordinate for the inverse diagonal
        //It translate as the following in the design pattern of the inverse diagonal:

        int[] rowInvDiagPos = {3, 2, 1, 0};
        int[] colInvDiagPos = {0, 1, 2, 3};
        designPatterns.add(new DesignPattern("InvDiagonal", rowInvDiagPos, colInvDiagPos, 4, 4, length));

        //For the line, it was actually really simple, we have a pattern like the following.
        //(X,Y) (X+1,Y) (X+2,Y) (X+3,Y)
        int[] rowLinePos = {0, 0, 0, 0};
        int[] colLinePos = {0, 1, 2, 3};
        designPatterns.add(new DesignPattern("Line", rowLinePos, colLinePos, 4, 1, length));

        //For the column, we have a pattern like the following:
        //(X,Y) (X,Y+1) (X,Y+2) (X,Y+3)
        int[] rowVerticalPos = {0, 1, 2, 3};
        int[] colVerticalPos = {0, 0, 0, 0};
        designPatterns.add(new DesignPattern("Column", rowVerticalPos, colVerticalPos, 1, 4, length));

        // System.out.println("before patterns");
        for (DesignPattern dp : designPatterns) {
            // System.out.println(dp.getName());
            // System.out.println("GETHEIGHT for "+dp.getName()+" = "+(handPlayed.length-dp.getHeight()));

            for (int i = 0; i < handPlayed.length - dp.getHeight() + 1; i++) {

                //here I parse through the array representing the BoardGame with an offset on the rows minus the height of the
                //pattern currently sought, and I add a +1 to the boundary as the difference between the two is an absolute one.
                // So I have to add 1 to the index in order
                // to parse the entirety of the array as the stop point is the STRICKLY inferior value of k to the absolute
                //difference of the handPlayed length and the design pattern's Height.
                //TODO: confusing, come back here later in order to explain it better...

                //I have already set the different values of the indexes as well as their respective height and width in the DesignPattern class
                // I only have to add these value to the coordinate of the index currently parsed by the for loops.
                //the advantage I see in list is the fact that it can (in theory) store any type of pattern if I provide the right
                //offsets of coordinates X and Y in the arrays taken as argument by the constructor, this way I am not limited by the number of
                //relatives coordinates the pattern has and can have as many point designing the patterns as I want.
                // I only need to loop with a for type loop through the list of coordinates and verify if these coordinates
                //added on the current index of the array representing the GameBoard parsed are a match with the currentPlayer value.
                // Like this : gameBoardArray[i+X][k+Y]==currentPlayer, and this must be true for ALL the points listed in the
                // Pattern to be considered as a match of a pattern and thus the pattern being found.

                //the real issue was certainly the indexes boundaries setting up for it parse through the gameBoardArray (here it is name handPlayed)
                // in order to "brush" through the entirety of the array ...
                //Lots and Lots of trials an errors there...thanks God I thought of the width and Height of the pattern
                // in the design of the DesignPattern class.....still, I am sick of the "out of bounds" messages...

                //TODO: Here the relative height is already the size for the scope of i to parse without any risks of out of bounds (in theory).
                // also, aside from that, it seems to work....
                // It did work out Yay! , but what do I do with it though?


                for (int k = 0; k < handPlayed.length - dp.getWidth() + 1; k++) {

                    //LOTS OF RUBBISH COMMENTS AHEAD, but I can't bear to part with it, it was a determining factor in my succeeding
                    //finding the right borders of the parsing......I'll delete it later....
                    int matchPatternPlayer = 0;
                    int matchPatternAI = 0;
                    int matchPattern = 0;
                    int checkRow = 0;
                    int checkCol = 0;
                    int emergencyRow = 0;
                    int emergencyCol = 0;
                    boolean emergencyOn = false;
                    for (int j = 0; j < dp.rowPositions.size(); j++) {
                        //System.out.println("Pattern = "+dp.getName()+" Checking from row : "+i);
                        //in order for the pattern to be found successfully,
                        //the matching must be done in the entirety of the points described in its list of relatives coordinates.
                        // So the size of the index of the list must be equal to the index matchPattern which increase by one at
                        // each match of the coordinates of the pattern with the
                        //current point parsed by the routine as origin.
                        //TODO: I'll try exotic ones tomorrow, now I am too tired.

                        if (handPlayed[i + dp.rowPositions.get(j)][k + dp.colPositions.get(j)] == player) {
                            matchPattern++;
                        }
                    }
                    if (matchPattern == dp.rowPositions.size()) {//I can do that because the row and col list have strictly the same size.
                        System.out.println("PATTERN FOUND! ! ! the pattern found is " + dp.getName());
                    }
                    matchPattern = 0;

                }

            }

        }

        if (emergencyPlay == null) {
            if (blockingPosition.size() != 0) {
                //handPlayed[blockingPosition.get(blockingPosition.size()-1).getRow()][blockingPosition.get(blockingPosition.size()-1).getCol()] = player2;
                handPlayed[blockingPosition.get(0).getRow()][blockingPosition.get(0).getCol()] = player2;
                System.out.println("AI Played position : " + blockingPosition.get(0).getButton().getId());
                ticTacViewDyn.setButtonText(blockingPosition.get(0).getButton().getId(), "A.I");
            } else if (emptyPosition.size() != 0) {
                Random rand = new Random();

                handPlayed[blockingPosition.get(rand.nextInt(emptyPosition.size())).getRow()][emptyPosition.get(rand.nextInt(emptyPosition.size())).getCol()] = player2;
                ticTacViewDyn.setButtonText(emptyPosition.get(rand.nextInt(emptyPosition.size())).getButtonId(), "A.I");

                //     handPlayed[blockingPosition.get(emptyPosition.size() - 1==0?emptyPosition.size() - 1:0).getRow()][emptyPosition.get(emptyPosition.size() - 1==0?emptyPosition.size() - 1:0).getCol()] = player2;
                //     ticTacViewDyn.setButtonText(emptyPosition.get(emptyPosition.size() - 1==0?emptyPosition.size() - 1:0).getButtonId(), "A.I");
            }
        } else {
            handPlayed[emergencyPlay.getRow()][emergencyPlay.getCol()] = player2;
            System.out.println("Emergency play AI PLAYED POSITION : " + emergencyPlay.getButton().getId());
            ticTacViewDyn.setButtonText(emergencyPlay.getButton().getId(), "A.I");
        }
    }

    @Override
    public void readGame(int col, int row) {
        handPlayed[row][col] = player;
    }//necessary method to force the update of the AI play, if not done, the AI turn and corresponding values of its play
    //on the game board will not appear until the next turn...

    @Override
    public Boolean getPlayer() {
        return currentPlayer;
    }

    @Override
    public Button getButtonText(String btnId) {
        for (Button btn : buttons) {
            if (btn.getId().equals(btnId)) {
                return btn;
            }
        }
        return null;
    }

    private String createButtonId(int row, int col) {
        return "L_" + row + "C_" + col;
    }

    @Override
    public boolean isGameOver() {

        boolean isGameOver = false;
        int testDiagInvPlayer1 = 0;
        int testDiagInvPlayer2 = 0;
        for (int i = handPlayed.length - 1; i > -1; i--) {
            if (handPlayed[i][(handPlayed.length - 1) - i] == player) {
                testDiagInvPlayer1++;
            } else {
                testDiagInvPlayer1 = 0;
            }
            if (testDiagInvPlayer1 == handPlayed.length) {
                System.out.println("Win Inv Diagonal");
                winner = 1;
                return true;
            }
            if (handPlayed[i][(handPlayed.length - 1) - i] == player2) {
                testDiagInvPlayer2++;
            } else {
                testDiagInvPlayer2 = 0;
            }
            if (testDiagInvPlayer2 == handPlayed.length) {
                System.out.println("Win Inv Diagonal");
                winner = 2;
                return true;
            }
        }
        int testVerticalPlayer1 = 0;
        int testVerticalPlayer2 = 0;
        for (int i = 0; i < handPlayed.length; i++) {
            testVerticalPlayer1 = 0;
            for (int k = 0; k < handPlayed.length; k++) {

                if (handPlayed[k][i] == player) {
                    testVerticalPlayer1++;
                } else {
                    testVerticalPlayer1 = 0;
                }
                if (testVerticalPlayer1 == handPlayed.length) {
                    System.out.println("Win Vertical");
                    winner = 1;
                    return true;
                }
                if (handPlayed[k][i] == player2) {
                    testVerticalPlayer2++;
                } else {
                    testVerticalPlayer2 = 0;
                }
                if (testVerticalPlayer2 == handPlayed.length) {
                    System.out.println("Win Vertical");
                    winner = 2;
                    return true;
                }
            }
            testVerticalPlayer2 = 0;
        }
        int testHorizontalPlayer1 = 0;
        int testHorizontalPlayer2 = 0;
        for (int i = 0; i < handPlayed.length; i++) {
            testHorizontalPlayer1 = 0;
            for (int k = 0; k < handPlayed.length; k++) {

                if (handPlayed[i][k] == player) {
                    testHorizontalPlayer1++;
                } else {
                    testHorizontalPlayer1 = 0;
                }
                if (testHorizontalPlayer1 == handPlayed.length) {
                    winner = 1;
                    System.out.println("Win Horizontal");
                    return true;
                }
                if (handPlayed[i][k] == player2) {
                    testHorizontalPlayer2++;
                } else {
                    testHorizontalPlayer2 = 0;
                }
                if (testHorizontalPlayer2 == handPlayed.length) {
                    winner = 2;
                    System.out.println("Win Horizontal");
                    return true;
                }
            }
            testHorizontalPlayer2 = 0;
        }
        int testDiagPlayer1 = 0;
        int testDiagPlayer2 = 0;
        for (int i = 0; i < handPlayed.length; i++) {
            if (handPlayed[i][i] == player) {
                testDiagPlayer1++;
            } else {
                testDiagPlayer1 = 0;
            }
            if (testDiagPlayer1 == handPlayed.length) {
                winner = 1;
                System.out.println("Win Diagonal");
                return true;
            }
            if (handPlayed[i][i] == player2) {
                testDiagPlayer2++;
            } else {
                testDiagPlayer2 = 0;
            }
            if (testDiagPlayer2 == handPlayed.length) {
                System.out.println("Win Diagonal");
                winner = 2;
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
        System.out.println("Winner = " + winner);
        if (winner != 0) {
            return winner;
        } else {
            return -1;
        }
    }

    @Override
    public void newGame() {
        handPlayed = new Boolean[ticTacViewDyn.getLength()][ticTacViewDyn.getLength()];
        currentPlayer = player;
        // buttons.clear();
        winner = -1;
    }
}
