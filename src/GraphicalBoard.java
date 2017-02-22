import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class GraphicalBoard extends JPanel {

    private GraphicalChecker[][] checkers;

    public GraphicalBoard() {
        checkers = new GraphicalChecker[6][7];
        initBoard();
        setSize(1038, 900);
        setLayout(new GridLayout(6,7));
        setVisible(true);
        //Delta for x is 145
        //TODO: find delta for y

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii;
        ii = new ImageIcon("Images/board.png");
        g.drawImage(ii.getImage(), 0, 0, null);
    }

    private void initBoard()
    {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <7 ; j++) {
                checkers[i][j] = new GraphicalChecker(Players.PLAYERS.NONE);
            }
        }
    }

    public void insertChecker(int col, Players.PLAYERS player) {
        int i = 5;
        for (; i >= 0 && checkers[i][col].getPlayer() != Players.PLAYERS.NONE; i--) ;
        checkers[i][col] = new GraphicalChecker(player);
        checkers[i][col].setLocation(0,0);
        add(checkers[i][col],i,col);
    }
}