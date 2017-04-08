import javax.swing.*;
import java.awt.*;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class TestFrame extends JFrame {

    public TestFrame() throws HeadlessException {

        setSize(1040, 900 + 30 + 40);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));
        System.out.println(this.getWidth() + "," + this.getHeight());

        GraphicalChecker gc = new GraphicalChecker(Consts.PLAYERS.NONE);
        add(gc);
        gc.setPlayer(Consts.PLAYERS.PLAYER2);
        setVisible(true);


        int x = 0;
        for (int i = 0; i <300 ; i++) {
            gc.setLocation(x,0);
            x ++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gc.setPlayer(Consts.PLAYERS.PLAYER1);



    }

}
