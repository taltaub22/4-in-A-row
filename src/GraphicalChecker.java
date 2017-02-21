import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 21/02/2017.
 */
public class GraphicalChecker extends JPanel{


    private Players.PLAYERS player;

    public GraphicalChecker(Players.PLAYERS player) {
        this.player = player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon cheker;
        cheker = new ImageIcon("Images/"+player.toString()+".png");
        g.drawImage(cheker.getImage(), 0, 0,null);
    }

    public Players.PLAYERS getPlayer() {
        return player;
    }

    public void setPlayer(Players.PLAYERS player) {
        this.player = player;
    }
}
