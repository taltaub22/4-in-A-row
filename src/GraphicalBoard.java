import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class GraphicalBoard extends JPanel {

    private GraphicalChecker[][] board;

    public GraphicalBoard() {
        board = new GraphicalChecker[6][7];
        initBoard();
        setSize(1036, 912);
        setLayout(new GridLayout(6, 7,0,0));

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii;
        ii = new ImageIcon("Images/board.png");
        g.drawImage(ii.getImage(), -13, -15, null);
    }

    private void initBoard() {
        int width = this.getWidth() / 7;
        int height = this.getHeight() / 6;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new GraphicalChecker(Consts.PLAYERS.NONE);
//                board[i][j].setLocation(width/2 - 137/2, height/2 - 137/2);
                add(board[i][j]);
            }
        }
    }

    public void insertChecker(int col, Consts.PLAYERS player) {
        int i = 5;
        for (; i >= 0 && board[i][col].getPlayer() != Consts.PLAYERS.NONE; i--) ;
        board[i][col].setPlayer(player);
    }
}