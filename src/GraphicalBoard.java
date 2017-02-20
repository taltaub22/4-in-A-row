import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class GraphicalBoard extends JPanel {

    public GraphicalBoard() {
        setSize(1038, 900);
        setLayout(new GridLayout(6, 7));
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon ii;
        ii = new ImageIcon("Images/board.png");
        g.drawImage(ii.getImage(), 0, 0,null);
    }

}