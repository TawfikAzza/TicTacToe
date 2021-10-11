package tictactoe.bll;

public class GameBoardAIRecursive implements IGameModel{
    @Override
    public int getNextPlayer() {
        return 0;
    }

    @Override
    public boolean play(int col, int row) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getWinner() {
        return 0;
    }

    @Override
    public void newGame() {

    }
}
