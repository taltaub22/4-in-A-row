import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class GraphicalBoard extends JPanel {

    private GraphicalChecker[][] checkers;

    public GraphicalBoard() {
        setSize(1038, 900);
        setLayout(new GridLayout(6, 7));
        setVisible(true);
        checkers = new GraphicalChecker[6][7];
        //Delta for x is 145
        //TODO: find delta for y

        for (int i = 0; i < checkers.length ; i++) {
            for (int j = 0; j < checkers[i].length ; j++) {
                Players.PLAYERS[] pl = new Players.PLAYERS[2];
                pl[0] = Players.PLAYERS.PLAYER1;
                pl[1] = Players.PLAYERS.PLAYER2;
                checkers[i][j] = new GraphicalChecker(pl[(int)Math.random()*10]);
                int deltaX = 145;
                int locationX = 9;
                checkers[i][j].setLocation(locationX,15);
                locationX += deltaX;
            }
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii;
        ii = new ImageIcon("Images/board.png");
        g.drawImage(ii.getImage(), 0, 0, null);
    }

    public void insertChecker (int col, Players.PLAYERS player)
    {

    }

}