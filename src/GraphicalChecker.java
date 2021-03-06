import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 21/02/2017.
 */
public class GraphicalChecker extends JLabel implements Consts {

    private PLAYERS player;
    private Point location;

    public GraphicalChecker(PLAYERS player) {
        this.player = player;
        location = new Point(0, 0);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon checker;
        checker = new ImageIcon(this.getClass().getResource("/Images/" + player.toString() + ".png"));
        g.drawImage(checker.getImage(), location.x, location.y, null);
    }

    public Consts.PLAYERS getPlayer() {
        return player;
    }

    public void setPlayer(Consts.PLAYERS player) {
        this.player = player;
        paintImmediately(0, 0, 150, 150);
    }

    @Override
    public void setLocation(int x, int y) {
        location.x = x;
        location.y = y;
        repaint();
    }
}

