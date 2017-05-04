import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tal Taub on 19/02/2017.
 */

public class Game extends JFrame implements Consts {

    private Board board;
    private PLAYERS currentPlayer = PLAYERS.PLAYER1;
    private int turn = 0;

    public Game(GAMETYPES type) {
        board = new Board();
        setVisible(true);
        setSize(1036, 912 + 90);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)));

        JPanel buttonPanel = new JPanel();
        JPanel textPanel = new JPanel();

        JLabel curPlayer = new JLabel();
        textPanel.add(curPlayer);

        JButton buttons[] = new JButton[7];

        for (int i = 0; i < buttons.length; i++) {
            int x = i;
            buttons[i] = new JButton("Column " + (i + 1));
            buttons[i].setPreferredSize(new Dimension(137, 50));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    turn++;

                    if (type == GAMETYPES.PVP) {
                        currentPlayer = (currentPlayer == PLAYERS.PLAYER1) ? PLAYERS.PLAYER2 : PLAYERS.PLAYER1;
                        curPlayer.setText("Current Player is:" + currentPlayer);
                        System.out.println(currentPlayer);
                    }

                    int row = -1;
                    row = board.insertChecker(x, currentPlayer);
                    if (row != -1) {
                        if (board.checkWin(x, row, currentPlayer) != PLAYERS.NONE) {
                            int choice = JOptionPane.showConfirmDialog(Game.super.rootPane, currentPlayer + "is the Winner. \n Do you want to start a new game?", "We Have A Winner!", JOptionPane.YES_NO_OPTION);
                            if (choice == 1) {
                                setVisible(false);
                                dispose();
                            }
                            if (choice == 0) {
                                new Game(type);
                                setVisible(false);
                                dispose();
                            }
                        }

                        if (type == GAMETYPES.PVPC) {
                        }
                    }
                }
            });
        }

        add(textPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.NORTH);
        add(board.getGb(), BorderLayout.CENTER);

    }

    private int computerAI() {
        AI ai = new AI();
        int row = -1;
        ai.calculateScores(board);
        int col = ai.getCol();

        boolean flag = true;
        do {
            try {
                row = board.getLb().insertChecker(col, PLAYERS.PLAYER2);
                board.getGb().insertChecker(col, PLAYERS.PLAYER2);
            } catch (NotInBoardException e) {
                e.printStackTrace();
                continue;
            } catch (ColumnFullException e) {
                System.out.println("Choosing another col");
                continue;
            }
            flag = false;
        } while (flag);

        return row;
    }

}
