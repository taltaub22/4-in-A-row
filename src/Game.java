import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class Game extends JFrame implements Players {


    private Board board;
    private static PLAYERS CurrentPlayer = PLAYERS.PLAYER1;

    public Game() {
        board = new Board();

        setSize(1040, 900 + 30 + 40);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));
        System.out.println(this.getWidth() + "," + this.getHeight());

        JButton buttons[] = new JButton[7];
        JPanel buttonPanel = new JPanel();

        for (int i = 0; i < buttons.length; i++) {
            int x = i;
            buttons[i] = new JButton("Column " + (i + 1));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        board.getLb().insertChecker(x, Players.PLAYERS.PLAYER1);
                    } catch (NotInBoardException e1) {
                        e1.printStackTrace();
                    } catch (ColumnFullException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        add(buttonPanel, BorderLayout.NORTH);
        add(board.getGb(), BorderLayout.CENTER);

        setVisible(true);


    }


}
