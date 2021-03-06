import javax.swing.*;
import java.io.*;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class LogicalBoard implements Consts {

    private PLAYERS[][] board;
    private int[] height;

    public LogicalBoard() {
        height = new int[]{5, 5, 5, 5, 5, 5, 5};
        initBoard();
    }

    public PLAYERS[][] getBoard() {
        return board;
    }

    public void setBoard(PLAYERS[][] board) {
        this.board = board;
    }

    public int[] getHeight() {
        return height;
    }

    public void setHeight(int[] height) {
        this.height = height;
    }

    private void initBoard() {
        board = new PLAYERS[6][7];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = PLAYERS.NONE;
            }
        }
    }

    /*
    * The insertChecker method returns the row which the
    * checker got inserted into in the column provided in the paramter.
    * @param col The col to insert a checker in.
    * @param player The player who inserted the checker.
    * @return The row that checker was inserted to.
    * */
    public int insertChecker(int col, PLAYERS player) throws NotInBoardException, ColumnFullException {

        if (col < 0 && col > 6)
            throw new NotInBoardException();

        if (board[0][col] != PLAYERS.NONE) {
            throw new ColumnFullException();
        }

        board[height[col]][col] = player;
        height[col]--;
        return height[col] + 1;
    }

    public void undoMove(int col, int row) {
        board[row][col] = PLAYERS.NONE;
        height[col]++;
    }

    public boolean checkWin(int col, int row, PLAYERS player) {

        boolean flag = false;

        flag = flag || checkWinCol(col, row, player);
        flag = flag || checkWinRow(col, row, player);
        flag = flag || checkWinMainDiagonal(col, row, player);
        flag = flag || checkWinSecondaryDiagonal(col, row, player);

        return flag;
    }

    public boolean checkWinCol(int col, int row, PLAYERS player) {
        int i, counter = 1;
        for (i = 1; i < seqLength && row + i < maxRow && board[row + i][col] == player; i++)
            counter++;

        return counter == seqLength;

    }

    public boolean checkWinRow(int col, int row, PLAYERS player) {
        int i, counter = 1;

        for (i = 1; i < seqLength && col + i < maxCol && board[row][col + i] == player; i++)
            counter++;

        for (i = 1; i < seqLength && col - i >= 0 && board[row][col - i] == player; i++)
            counter++;

        return counter >= seqLength;

    }

    public boolean checkWinMainDiagonal(int col, int row, PLAYERS player) {
        int i, counter = 1;

        for (i = 1; i < seqLength && row + i < maxRow && col + i < maxCol &&
                board[row + i][col + i] == player; i++)

            counter++;

        for (i = 1; i < seqLength && row - i >= 0 && col - i >= 0 &&
                board[row - i][col - i] == player; i++)

            counter++;

        return counter >= seqLength;

    }

    public boolean checkWinSecondaryDiagonal(int col, int row, PLAYERS player) {
        int i, counter = 1;

        for (i = 1; i < seqLength && row - i >= 0 && col + i < maxCol &&
                board[row - i][col + i] == player; i++)

            counter++;

        for (i = 1; i < seqLength && row + i < maxRow && col - i >= 0 &&
                board[row + i][col - i] == player; i++)

            counter++;

        return counter >= seqLength;

    }

    public void save(int turn) {
        try {  // Catch errors in I/O if necessary.
            JFileChooser c = new JFileChooser();
            int r = c.showDialog(null, "Create file to save game");
            if (r == JFileChooser.APPROVE_OPTION) {
                File f = c.getSelectedFile();
                FileOutputStream saveFile = new FileOutputStream(f);
                ObjectOutputStream save = new ObjectOutputStream(saveFile);

                save.writeObject(board);
                save.writeObject(height);
                save.writeObject(turn);

                save.close(); // This also closes saveFile.
            }
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    public int load() {
        int turn = 0;
        try {
            JFileChooser c = new JFileChooser();
            int r = c.showDialog(null, "Open file to load game");
            if (r == JFileChooser.APPROVE_OPTION) {
                File f = c.getSelectedFile();
                FileInputStream loadFile = new FileInputStream(f);

                ObjectInputStream load = new ObjectInputStream(loadFile);

                setBoard((PLAYERS[][]) load.readObject());
                setHeight((int[]) load.readObject());
                turn = (int) load.readObject();
                // Close the file.
                load.close(); // This also closes saveFile.

            }
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }

        return turn;
    }
}
