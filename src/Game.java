import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * Created by Tal Taub on 19/02/2017.
 */

public class Game extends JFrame implements Consts {

    private Board board;
    private PLAYERS currentPlayer = PLAYERS.PLAYER1;
    private int turn = 0;
    private Stack<Integer> moves = new Stack<>();
    private AI computer;

    public Game(GAMETYPES type) {
        /*---------------------------------General Settings-------------------------*/
        board = new Board();
        setVisible(true);
        setSize(1036, 890 + 90);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setLocation(((int) (d.getWidth() / 2 - this.getWidth() / 2)), ((int) (d.getHeight() / 2 - this.getHeight() / 2)) - 25);
        setTitle("4 In-A-Row " + type);

        /*---------------------------------Menu Bar--------------------------------*/
        JMenuBar manuBar = new JMenuBar();
        setJMenuBar(manuBar);

        JMenu file = new JMenu("File");
        manuBar.add(file);

        //new Game
        JMenuItem newGame = new JMenuItem("New Game");
        file.add(newGame);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game(type);
                dispose();
            }
        });
        newGame.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        //Save
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.save(turn);
                new Menu();
                dispose();
            }
        });
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        //Load
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn = board.load();
            }
        });
        open.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));


        //Undo
        JMenuItem undo = new JMenuItem("Undo");
        file.add(undo);
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!moves.empty()) {
                    turn--;
                    board.undoMove(moves.pop());
                } else
                    JOptionPane.showMessageDialog(Game.super.rootPane, "No more moves to undo!");
            }
        });
        undo.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

        //Exit
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Menu();
                dispose();
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE));


        /*---------------------------------GAME------------------------------------*/
        JPanel buttonPanel = new JPanel();
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

                    System.out.println(currentPlayer);

                    if (turn >= maxCol * maxRow)
                        if (board.isBoardFull()) {
                            int option = JOptionPane.showConfirmDialog(Game.super.rootPane,
                                    "The board is full!", "Board is full", JOptionPane.ERROR_MESSAGE);
                            if (option == JOptionPane.OK_OPTION) {
                                new Menu();
                                dispose();
                            }
                        }

                    /*************Inserting Checker***************/
                    int row = board.insertChecker(x, currentPlayer);
                    moves.push(x);

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

                                /*----------AI: CHANGE HERE---------*/

//                                simpleComputerAI();
                                negaMaxComputerAI();

                                /*----------------------------------*/


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

        add(buttonPanel, BorderLayout.NORTH);

        add(board.getGb(), BorderLayout.CENTER);
    }

    private void simpleComputerAI() {

        int bestCol = 4;
        int mark;
        int maxMark = Integer.MIN_VALUE;

        for (int i = 0; i < maxCol; i++) {

            if (board.getLb().getHeight()[i] >= 0) {
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
                    moves.push(i);

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
                    moves.push(i);
                    return;
                } else {
                    board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.NONE;
                }
            }
        }

        board.insertChecker(bestCol, PLAYERS.PLAYER2);
        moves.push(bestCol);
    }

    private void negaMaxComputerAI() {
        int bestCol = 4;

        for (int i = 0; i < maxCol; i++) {

            if (board.getLb().getHeight()[i] >= 0) {
                board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.PLAYER2;

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
                } else {
                    board.getLb().getBoard()[board.getLb().getHeight()[i]][i] = PLAYERS.NONE;
                }
            }
        }

        computer.negaMax(computer.getDepth(), PLAYERS.PLAYER2);
        bestCol = computer.getBestCol();
        board.insertChecker(bestCol, PLAYERS.PLAYER2);
    }

}
