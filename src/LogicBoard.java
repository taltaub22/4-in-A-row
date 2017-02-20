import javafx.scene.control.TableCell;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class LogicBoard implements Players{

    private PLAYERS[][] board;


    public LogicBoard() {

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
    * @return The row the checker was inserted to.
    * */
    public int insertChecker(int col,PLAYERS player) throws NotInBoardException, ColumnFullException {
        if (col < 0 && col > 6)
            throw new NotInBoardException("");

        int i = 0;
        for (; i < 6 && board[i][col] != PLAYERS.NONE; i++) ;
        if (i >= 1) {
            board[i - 1][col] = player;
            return i-1;
        } else {
            throw new ColumnFullException();
        }
    }


    public PLAYERS checkWin(int col,int row,PLAYERS player) {

        for (int i = row; i < board.length ; i++) {
            for (int j = col; j < board[i].length; j++) {
                
            }
        }




        return player;
    }
}
