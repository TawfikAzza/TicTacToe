package tictactoe.bll;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DesignPattern {




    private int priority;
    private int width;
    private int height;
    private String name;



    private int rowWin;
    private int colWin;

    List<Integer> rowPositions = new ArrayList<>();
    List<Integer> colPositions = new ArrayList<>();

    List<Integer> rowClockWisePositions = new ArrayList();
    List<Integer> colClockWisePositions = new ArrayList<>();

    List<Integer> rowOppositePositions = new ArrayList<>();
    List<Integer> colOppositePositions = new ArrayList<>();

    List<Integer> rowAntiCLockWisePositions = new ArrayList<>();
    List<Integer> colAntiCLockWisePositions = new ArrayList<>();

    public DesignPattern(String name, int[] rowPos, int[] colPos,int width, int height,int lengthArray, int rowWin, int colWin, int priority){
        this.name=name;
        this.width=width;
        this.rowWin=rowWin;
        this.colWin=colWin;
        this.priority=priority;
        this.height=height;
        for (int i = 0; i < rowPos.length; i++) {
            rowPositions.add(rowPos[i]);
        }
        for (int i = 0; i < colPos.length; i++) {
            colPositions.add(colPos[i]);
        }

        for (int i = 0; i < rowPos.length; i++) {
            //rowPositions.add(rowPos[i]);
        }
        for (int i = 0; i < colPos.length; i++) {
            int resultRow=0;
            int resultCol=0;
            resultRow = ((width-1)*colPos[i]+(width-1))%(width);
            resultCol =  resultRow;
            rowClockWisePositions.add(colPos[i]);
        }
    }

    public DesignPattern(String name, int[] rowPos, int[] colPos,int width, int height,int lengthArray) {
        this.name=name;
        this.width=width;
        this.height=height;
        for (int i = 0; i < rowPos.length; i++) {
            rowPositions.add(rowPos[i]);
        }
        for (int i = 0; i < colPos.length; i++) {
            colPositions.add(colPos[i]);
        }

        for (int i = 0; i < rowPos.length; i++) {
            //rowPositions.add(rowPos[i]);
        }
        for (int i = 0; i < colPos.length; i++) {
            int resultRow=0;
            int resultCol=0;
            resultRow = ((width-1)*colPos[i]+(width-1))%(width);
            resultCol =  resultRow;
            rowClockWisePositions.add(colPos[i]);
        }
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRowWin() {
        return rowWin;
    }

    public int getColWin() {
        return colWin;
    }
    public int getPriority() {
        return priority;
    }
}
