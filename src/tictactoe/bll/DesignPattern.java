package tictactoe.bll;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DesignPattern {




    private int width;
    private int height;



    private String name;
    List<Integer> rowPositions = new ArrayList<>();
    List<Integer> colPositions = new ArrayList<>();
    public DesignPattern(String name, int[] rowPos, int[] colPos,int width, int height){
        this.name=name;
        this.width=width;
        this.height=height;
        for (int i = 0; i < rowPos.length; i++) {
            rowPositions.add(rowPos[i]);

        }
        for (int i = 0; i < colPos.length; i++) {
            colPositions.add(colPos[i]);
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


}
