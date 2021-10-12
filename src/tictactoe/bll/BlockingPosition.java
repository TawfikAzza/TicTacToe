package tictactoe.bll;

import javafx.scene.control.Button;

public class BlockingPosition {


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
    private int numHumanPlayerInCol;
    private int numAIPlayerInRow;
    private int numAIPlayerInCol;

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

}
