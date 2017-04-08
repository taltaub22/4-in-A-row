import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal Taub on 08/04/2017.
 */
public class Menu extends JFrame {

    public Menu() throws HeadlessException {

        setVisible(true);
        setSize(1036, 912 + 90);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));
        setTitle("4-In-A-Row");

        JPanel imageTitle = new JPanel();
        JLabel image = new JLabel(new ImageIcon("/Images/Title.png"));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 1));
        buttons.setPreferredSize(new Dimension(500, 500));
        buttons.setLocation(1036 / 2, (912 + 90) / 2);

        JButton newGame = new JButton("Start New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game();
                dispose();
            }
        });

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        buttons.add(newGame);
        buttons.add(exit);

        imageTitle.add(image);
        add(imageTitle, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);


    }
}
