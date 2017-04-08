/**
 * Created by Tal Taub on 19/02/2017.
 */
public class LogicalBoard implements Consts {

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

        return i;
    }


    public PLAYERS checkWin(int col, int row, PLAYERS player) {

        boolean flag = false;

        flag = flag || checkWinCol(col, row, player);
        flag = flag || checkWinRow(col, row, player);
        flag = flag || checkWinMainDiagonal(col, row, player);
        flag = flag || checkWinSecondaryDiagonal(col, row, player);

        if (flag)
            return player;
        else
            return PLAYERS.NONE;
    }

    public boolean checkWinCol(int col, int row, PLAYERS player) {
        int count = 0;
        if (row <= 2) {
            int i = 5;
            for (; i >= row && board[i][col] == player && count < 4; i--, count++) ;
            if (count >= 4)
                return true;
        }
        return false;
    }

    public boolean checkWinRow(int col, int row, PLAYERS player) {
        int count = 0;
        int checkRight = col + (4 - 1) >= 6 ? 6 : col + (4 - 1);
        int checkLeft = col - (4 - 1) <= 0 ? 0 : col - (4 - 1);

        for (int i = checkLeft; i < checkRight && board[row][i] == player && count < 4; i++, count++) ;
        if (count >= 4)
            return true;

        return false;
    }

    public boolean checkWinMainDiagonal(int col, int row, PLAYERS player) {

        int count = 0;

        int sCol = col, sRow = row;

        for (int i = 0; i < 4 && sCol <= 6 && sRow <= 5 && board[sRow][sCol] == player; i++, sRow++, sCol++, count++) ;
        sCol = col - 1;
        sRow = row - 1;
        for (int i = 0; i < 4 && sCol >= 0 && sRow >= 0 && board[sRow][sCol] == player; i++, sRow--, sCol--, count++) ;

        if (count >= 4)
            return true;

        return false;

    }

    public boolean checkWinSecondaryDiagonal(int col, int row, PLAYERS player) {

        int count = 0;

        int sCol = col, sRow = row;

        for (int i = 0; i < 4 && sCol >= 0 && sRow <= 5 && board[sRow][sCol] == player; i++, sRow++, sCol--, count++) ;
        sCol = col + 1;
        sRow = row - 1;
        for (int i = 0; i < 4 && sCol <= 6 && sRow >= 0 && board[sRow][sCol] == player; i++, sRow--, sCol++, count++) ;

        if (count >= 4)
            return true;

        return false;

    }


}
