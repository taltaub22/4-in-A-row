import javafx.scene.control.TableCell;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class LogicalBoard implements Players {

    private PLAYERS[][] board;


    public LogicalBoard() {
        initBoard();
    }

    public PLAYERS[][] getBoard() {
        return board;
    }

    public void setBoard(PLAYERS[][] board) {
        this.board = board;
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

        int i = 5;
        for (; i >= 0 && board[i][col] != PLAYERS.NONE; i--) ;
        board[i][col] = player;

        checkWin(col, i, player);

        return i;
    }


    public PLAYERS checkWin(int col, int row, PLAYERS player) {

        checkWinCol(col, row, player);
        System.out.println(player + " Win row?" + checkWinRow(col, row, player));

        return player;
    }

    public boolean checkWinCol(int col, int row, PLAYERS player) {
        int count = 0;
        if (row <= 2) {
            int i = 5;
            for (; i >= row && board[i][col] == player; i--, count++) ;
            if (count >= 4)
                return true;
        }
        return false;
    }

    public boolean checkWinRow(int col, int row, PLAYERS player) {
        int count = 0;
        int checkLeft = col - 4 <= 0 ? 0 : col - 4;
        int checkRight = 6 - 4 + col >= 6 ? 6 : 6 - 4 + col;


        for (int i = checkLeft; i < checkRight && board[row][i] == player; i++, count++) ;
        if (count >= 4)
            return true;

        return false;

    }

}
