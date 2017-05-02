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


        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 1));
        buttons.setPreferredSize(new Dimension(150, 500));
        buttons.setLocation(1036 / 2, (912 + 90) / 2);

        JButton newGame1v1 = new JButton("Start New P vs P Game");
        newGame1v1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game(Consts.GAMETYPES.PVP);
                dispose();
            }
        });

        JButton newGame1vAI = new JButton("Start New P vs PC Game");
        newGame1vAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game(Consts.GAMETYPES.PVPC);
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

        buttons.add(newGame1v1);
        buttons.add(newGame1vAI);
        buttons.add(exit);

        add(buttons, BorderLayout.CENTER);


    }
}
