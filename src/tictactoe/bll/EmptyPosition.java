package tictactoe.bll;

public class EmptyPosition {


    private int row;
    private int col;
    private String buttonId;

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public EmptyPosition(int row, int col, String buttonId){
        this.row=row;
        this.col=col;
        this.buttonId=buttonId;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
