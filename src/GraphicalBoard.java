import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class GraphicalBoard extends JPanel implements Consts {

    private GraphicalChecker[][] board;

    public GraphicalBoard() {
        board = new GraphicalChecker[6][7];
        initBoard();
        setSize(1036, 880);
        setLayout(new GridLayout(6, 7, 0, 0));
        setVisible(true);
    }

    public GraphicalChecker[][] getBoard() {
        return board;
    }

    public void setBoard(GraphicalChecker[][] board) {
        this.board = board;
    }

    private void initBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new GraphicalChecker(Consts.PLAYERS.NONE);
                add(board[i][j]);
            }
        }
    }

    public void initBoard(PLAYERS[][] board) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j].setPlayer(board[i][j]);
            }
        }
    }

    public void insertChecker(int col, int row, PLAYERS player) {
        int i = 0;
        for (; i >= 0 && i < row; i++) {

            board[i][col].setPlayer(player);

            try {
                Thread.sleep(150 - (i * 25));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            board[i][col].setPlayer(Consts.PLAYERS.NONE);

        }

        board[i][col].setPlayer(player);

    }

    public void undoMove(int col, int row) {
        board[row][col].setPlayer(PLAYERS.NONE);
        paintImmediately(0, 0, 1000, 800);
    }
}