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
        setSize(1036, 800);
        setLayout(new GridLayout(6, 7, 0, 0));
        setVisible(true);
    }

    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii;
        ii = new ImageIcon("Images/board.png");
        g.drawImage(ii.getImage(), -13, -15, null);
    }*/

    private void initBoard() {
        int width = this.getWidth() / 7;
        int height = this.getHeight() / 6;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new GraphicalChecker(Consts.PLAYERS.NONE);
                add(board[i][j]);
            }
        }
    }

    public void insertChecker(int col, int row, PLAYERS player) {
        int i = 0;
        int gravity = 5;
        for (; i >= 0 && i < row; i++) {


            board[i][col].setPlayer(player);

            try {
                Thread.sleep(10 * (10 - i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            board[i][col].setPlayer(Consts.PLAYERS.NONE);

        }

        board[i][col].setPlayer(player);

    }
}