import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class Game extends JFrame implements Players {

    private Board board;
    private static PLAYERS currentPlayer = PLAYERS.PLAYER1;

    public Game() {
        board = new Board();
        setVisible(true);
        setSize(1036, 912 + 90);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));
        System.out.println(this.getWidth() + "," + this.getHeight());

        JButton buttons[] = new JButton[7];
        JLabel curPlayer = new JLabel();
        JPanel buttonPanel = new JPanel();
        //  buttonPanel.add(curPlayer);


        for (int i = 0; i < buttons.length; i++) {
            int x = i;
            buttons[i] = new JButton("Column " + (i + 1));
            buttons[i].setPreferredSize(new Dimension(137, 50));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Boolean flag = false;
                    flag = board.insertChecker(x, currentPlayer);
                    if (flag) {
                        currentPlayer = (currentPlayer == PLAYERS.PLAYER1) ? PLAYERS.PLAYER2 : PLAYERS.PLAYER1;
                        curPlayer.setText("Current Player is:" + currentPlayer);
                    }
                    System.out.println(currentPlayer);
                }
            });
        }
        add(buttonPanel, BorderLayout.NORTH);
        add(board.getGb(), BorderLayout.CENTER);


    }


}
