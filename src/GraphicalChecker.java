import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 21/02/2017.
 */
public class GraphicalChecker extends JLabel {

    private Players.PLAYERS player;
    private Point location;

    public GraphicalChecker(Players.PLAYERS player) {
        this.player = player;
        location = new Point(0, 0);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon checker;
        checker = new ImageIcon("Images/" + player.toString() + ".png");
        g.drawImage(checker.getImage(), location.x, location.y, null);
    }

    public Players.PLAYERS getPlayer() {
        return player;
    }

    public void setPlayer(Players.PLAYERS player) {
        this.player = player;
        repaint();
    }

    @Override
    public void setLocation(int x, int y) {
        location.x = x;
        location.y = y;
        repaint();
    }
}

