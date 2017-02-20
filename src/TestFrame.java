import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

/**
 * Created by Tal Taub on 12/02/2017.
 */
public class TestFrame extends JFrame {

    public TestFrame() throws HeadlessException {


        setSize(1040, 900 + 30);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));
        System.out.println(this.getWidth() + "," + this.getHeight());
        GraphicalBoard gb = new GraphicalBoard();
        add(gb, BorderLayout.CENTER);

        setVisible(true);

    }

}
