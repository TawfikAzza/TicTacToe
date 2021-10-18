package tictactoe.bll;

import javafx.scene.control.Button;

public class BlockingPosition {


    private DesignPattern dp;
    private Button button;
    private int row;
    private int col;
    private int freePlacesOnRow;
    private int freePlacesOnCol;
    private int numHumanPlayerInRow;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private int priority=0;
    private int uninterruptedPlayerInRow;
    private int numHumanPlayerInCol;



    private int uninterruptedPlayerInCol;
    private int numAIPlayerInRow;
    private int uninterruptedAIInRow;
    private int numAIPlayerInCol;
    private int uninterruptedAIInCol;
    private int numFreePlacesInDiag;
    private int numHumanPlayerInDiag;
    private int uninterruptedPlayerinDiag;
    private int numAIInDiag;
    private int uninterruptedAIinDiag;
    private int numFreePlacesInInvDiag;
    private int numHumanPlayerInInvDiag;
    private int uninterruptedPlayerinInvDiag;
    private int numAIInInvDiag;
    private int uninterruptedAIinInvDiag;

    public BlockingPosition(Button button, int row, int col, int freePlacesOnRow, int freePlacesOnCol, int numHumanPlayerInRow, int numHumanPlayerInCol, int numAIPlayerInRow, int numAIPlayerInCol) {
        this.button = button;
        this.row = row;
        this.col = col;
        this.freePlacesOnRow = freePlacesOnRow;
        this.freePlacesOnCol = freePlacesOnCol;
        this.numHumanPlayerInRow = numHumanPlayerInRow;
        this.numHumanPlayerInCol = numHumanPlayerInCol;
        this.numAIPlayerInRow = numAIPlayerInRow;
        this.numAIPlayerInCol = numAIPlayerInCol;
    }
    public BlockingPosition(Button button, int row, int col, int freePlacesOnRow, int freePlacesOnCol, int numHumanPlayerInRow,int uninterruptedPlayerInRow
            ,int numHumanPlayerInCol, int uninterruptedPlayerInCol,int numAIPlayerInRow, int uninterruptedAIInRow ,int numAIPlayerInCol,int uninterruptedAIInCol
            , int numFreePlacesInDiag, int numHumanPlayerInDiag , int uninterruptedPlayerinDiag, int numAIInDiag, int uninterruptedAIinDiag
           ) {
        this.button = button;
        this.row = row;
        this.col = col;
        this.freePlacesOnRow = freePlacesOnRow;
        this.freePlacesOnCol = freePlacesOnCol;
        this.numHumanPlayerInRow = numHumanPlayerInRow;
        this.uninterruptedPlayerInRow = uninterruptedPlayerInRow;
        this.numHumanPlayerInCol = numHumanPlayerInCol;
        this.uninterruptedPlayerInCol = uninterruptedPlayerInCol;
        this.numAIPlayerInRow = numAIPlayerInRow;
        this.uninterruptedAIInRow = uninterruptedAIInRow;
        this.numAIPlayerInCol = numAIPlayerInCol;
        this.uninterruptedAIInCol = uninterruptedAIInCol;
        this.numFreePlacesInDiag = numFreePlacesInDiag;
        this.numHumanPlayerInDiag = numHumanPlayerInDiag;
        this.uninterruptedPlayerinDiag = uninterruptedPlayerinDiag;
        this.numAIInDiag = numAIInDiag;
        this.uninterruptedAIinDiag = uninterruptedAIinDiag;
    }
    public BlockingPosition(Button button, int row, int col) {

        this.button = button;
        this.row = row;
        this.col = col;

    }
    public BlockingPosition(Button button, int row, int col, DesignPattern dp) {
        this.dp=dp;
        this.button = button;
        this.row = row;
        this.col = col;

    }
    public BlockingPosition(Button button, int row, int col, int freePlacesOnRow, int freePlacesOnCol, int numHumanPlayerInRow,int uninterruptedPlayerInRow
            ,int numHumanPlayerInCol, int uninterruptedPlayerInCol,int numAIPlayerInRow, int uninterruptedAIInRow ,int numAIPlayerInCol,int uninterruptedAIInCol
            , int numFreePlacesInDiag
            , int numFreePlacesInInvDiag, int numHumanPlayerInInvDiag, int uninterruptedPlayerinInvDiag, int numAIInInvDiag, int uninterruptedAIinInvDiag) {
        this.button = button;
        this.row = row;
        this.col = col;
        this.freePlacesOnRow = freePlacesOnRow;
        this.freePlacesOnCol = freePlacesOnCol;
        this.numHumanPlayerInRow = numHumanPlayerInRow;
        this.uninterruptedPlayerInRow = uninterruptedPlayerInRow;
        this.numHumanPlayerInCol = numHumanPlayerInCol;
        this.uninterruptedPlayerInCol = uninterruptedPlayerInCol;
        this.numAIPlayerInRow = numAIPlayerInRow;
        this.uninterruptedAIInRow = uninterruptedAIInRow;
        this.numAIPlayerInCol = numAIPlayerInCol;
        this.uninterruptedAIInCol = uninterruptedAIInCol;
        this.numFreePlacesInDiag = numFreePlacesInDiag;

        this.numFreePlacesInInvDiag = numFreePlacesInInvDiag;
        this.numHumanPlayerInInvDiag = numHumanPlayerInInvDiag;
        this.uninterruptedPlayerinInvDiag = uninterruptedPlayerinInvDiag;
        this.numAIInInvDiag = numAIInInvDiag;
        this.uninterruptedAIinInvDiag = uninterruptedAIinInvDiag;
    }

    public BlockingPosition(int freePlacesRow,int freePlacesCol, int row, int col, Button button){
        this.button=button;
        this.row=row;
        this.col=col;
        this.freePlacesOnRow=freePlacesRow;
        this.freePlacesOnCol=freePlacesCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    public Button getButton() {
        return button;
    }

    public int getFreePlacesOnRow() {
        return freePlacesOnRow;
    }

    public int getFreePlacesOnCol() {
        return freePlacesOnCol;
    }

    public int getNumHumanPlayerInRow() {
        return numHumanPlayerInRow;
    }

    public int getNumHumanPlayerInCol() {
        return numHumanPlayerInCol;
    }

    public int getNumAIPlayerInRow() {
        return numAIPlayerInRow;
    }

    public int getNumAIPlayerInCol() {
        return numAIPlayerInCol;
    }

    public int getUninterruptedPlayerInRow() {
        return uninterruptedPlayerInRow;
    }

    public int getUninterruptedPlayerInCol() {
        return uninterruptedPlayerInCol;
    }

    public int getUninterruptedAIInRow() {
        return uninterruptedAIInRow;
    }

    public int getUninterruptedAIInCol() {
        return uninterruptedAIInCol;
    }

    public int getNumFreePlacesInDiag() {
        return numFreePlacesInDiag;
    }

    public int getNumHumanPlayerInDiag() {
        return numHumanPlayerInDiag;
    }

    public int getUninterruptedPlayerinDiag() {
        return uninterruptedPlayerinDiag;
    }

    public int getNumAIInDiag() {
        return numAIInDiag;
    }

    public int getUninterruptedAIinDiag() {
        return uninterruptedAIinDiag;
    }

    public int getNumFreePlacesInInvDiag() {
        return numFreePlacesInInvDiag;
    }

    public int getNumHumanPlayerInInvDiag() {
        return numHumanPlayerInInvDiag;
    }

    public int getUninterruptedPlayerinInvDiag() {
        return uninterruptedPlayerinInvDiag;
    }

    public int getNumAIInInvDiag() {
        return numAIInInvDiag;
    }

    public int getUninterruptedAIinInvDiag() {
        return uninterruptedAIinInvDiag;
    }
}
