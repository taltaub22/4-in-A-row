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
    private AI computer;

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

        if (type == GAMETYPES.PVPC) {
            computer = new AI(board);
        }


        for (int i = 0; i < buttons.length; i++) {
            int x = i;
            buttons[i] = new JButton("Column " + (i + 1));
            buttons[i].setPreferredSize(new Dimension(137, 50));
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (turn % 2 == 0) {
                        currentPlayer = PLAYERS.PLAYER1;
                    } else if (turn % 2 == 1) {
                        currentPlayer = PLAYERS.PLAYER2;
                    }

                    curPlayer.setText("Current Player is:" + currentPlayer);
                    System.out.println(currentPlayer);

                    int row = board.insertChecker(x, currentPlayer);
                    if (board.checkWin(x, row, currentPlayer)) {
                        int choice = JOptionPane.showConfirmDialog(Game.super.rootPane, currentPlayer + "is the Winner. \n Do you want to start a new game?", "We Have A Winner!", JOptionPane.YES_NO_OPTION);
                        if (choice == 1) {
                            setVisible(false);
                            dispose();
                        }
                        if (choice == 0) {
                            new Menu();
                            setVisible(false);
                            dispose();
                        }
                    }

                    if (type == GAMETYPES.PVPC) {
                        for (int j = 0; j < 7; j++) {
                            buttons[j].setEnabled(false);
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                computerAI();
                                turn++;
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }

                                for (int j = 0; j < 7; j++) {
                                    buttons[j].setEnabled(true);
                                }
                            }
                        }).start();
                    }
                    turn++;
                }
            });
        }

        add(textPanel, BorderLayout.NORTH);

        add(buttonPanel, BorderLayout.NORTH);

        add(board.getGb(), BorderLayout.CENTER);

    }

    private void computerAI() {

        int bestCol = 4;
        int mark;
        int maxMark = Integer.MIN_VALUE;

        for (int i = 0; i < maxCol; i++) {

            board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.PLAYER2;
            mark = computer.calculateScores();

            if (mark > maxMark) {
                maxMark = mark;
                bestCol = i;
            }

            // If this move can win, DO THE MOVE!
            if (board.checkWin(i, board.getLb().getHeight()[i], PLAYERS.PLAYER2)) {
                board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.NONE;
                board.insertChecker(i, PLAYERS.PLAYER2);

                int choice = JOptionPane.showConfirmDialog(Game.super.rootPane, "The Computer is the Winner. \n Do you want to start a new game?", "We Have A Winner!", JOptionPane.YES_NO_OPTION);
                if (choice == 1) {
                    setVisible(false);
                    dispose();
                    return;
                }
                if (choice == 0) {
                    new Menu();
                    setVisible(false);
                    dispose();
                    return;
                }
            } else {
                board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.NONE;
            }

            //If can block - BLOCK!
            board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.PLAYER1;
            if (board.checkWin(i, board.getLb().getHeight()[i], PLAYERS.PLAYER1)) {
                board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.NONE;
                board.insertChecker(i, PLAYERS.PLAYER2);
                return;
            }

        }

        board.insertChecker(bestCol, PLAYERS.PLAYER2);
    }

}
