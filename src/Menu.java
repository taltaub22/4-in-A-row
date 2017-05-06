import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal Taub on 08/04/2017.
 */
public class Menu extends JFrame {

    public Menu() throws HeadlessException {


        setSize(1036, 720);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));
        setTitle("4-In-A-Row");

        JLabel title = new JLabel(new ImageIcon(this.getClass().getResource("/Images/Title.png")));
        add(title, BorderLayout.NORTH);

        //Buttons

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        buttons.setPreferredSize(new Dimension(200, 70 * 3));
        buttons.setLocation(1036 / 2, (912 + 90) / 2);

        Dimension butSize = new Dimension(300, 100);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);

        JButton newGame1v1 = new JButton("Start New P vs P Game");
        newGame1v1.setPreferredSize(butSize);
        newGame1v1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game(Consts.GAMETYPES.PVP);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttons.add(newGame1v1, gbc);

        JButton newGame1vAI = new JButton("Start New P vs PC Game");
        newGame1vAI.setPreferredSize(butSize);
        newGame1vAI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game(Consts.GAMETYPES.PVPC);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttons.add(newGame1vAI, gbc);


        JButton exit = new JButton("Exit");
        exit.setPreferredSize(butSize);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        buttons.add(exit, gbc);

        add(buttons, BorderLayout.CENTER);

        setVisible(true);
    }
}

