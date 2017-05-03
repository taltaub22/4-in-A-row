/**
 * Created by Tal Taub on 02/05/2017.
 */
public class AI {

    private Board board;
    private int[] scores;

    public AI(Board board) {
        board = board;
        scores = new int[7];
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public void calculateScores(Board board) {


        //Col

        //Row


        //Main Diagonal


        //Secondary Diagonal
        count = 0;

        sCol = col;
        sRow = row;

        for (int i = 0; i < 4 && sCol >= 0 && sRow <= 5 && testBoard[sRow][sCol] == Consts.PLAYERS.PLAYER2; i++, sRow++, sCol--, count++)
            ;
        sCol = col + 1;
        sRow = row - 1;
        for (int i = 0; i < 4 && sCol <= 6 && sRow >= 0 && testBoard[sRow][sCol] == Consts.PLAYERS.PLAYER2; i++, sRow--, sCol++, count++)
            ;

        return (int) Math.pow(10, count);

    }

    public int calculateRow(int col, int row) {

        int markHumen = 0, markComputer = 0;
        int[] counters = new int[3];
        counters[0] = counters[1] = counters[2] = 0;

        //Right
        int i;
        for (i = 0; i + col < Consts.maxCol && i < Consts.seqLength; i++) {
            counters[board.getLb().getBoard()[row][col + i].ordinal()]++;
        }

        if (i == Consts.seqLength) {
            if (counters[Consts.PLAYERS.PLAYER1.ordinal()] > 0 && counters[Consts.PLAYERS.PLAYER2.ordinal()] == 0)
                markHumen += (int) Math.pow(10, counters[Consts.PLAYERS.PLAYER1.ordinal()]);

            if (counters[Consts.PLAYERS.PLAYER2.ordinal()] > 0 && counters[Consts.PLAYERS.PLAYER1.ordinal()] == 0)
                markComputer += (int) Math.pow(10, counters[Consts.PLAYERS.PLAYER2.ordinal()]);
        }
    }

    public int calculateCol(int col, int row) {
        int count = 0;
        for (int i = 5; i >= row && board.getLb().getBoard()[i][col] == Consts.PLAYERS.PLAYER2 && count < 4; i--, count++)
            ;
        return (int) Math.pow(10, count);
    }

    public int calculateMainDiagonal(int col, int row) {
        int count = 0;

        int sCol = col, sRow = row;

        for (int i = 0; i < 4 && sCol <= 6 && sRow <= 5 && board.getLb().getBoard()[sRow][sCol] == Consts.PLAYERS.PLAYER2; i++, sRow++, sCol++, count++)
            ;
        sCol = col - 1;
        sRow = row - 1;
        for (int i = 0; i < 4 && sCol >= 0 && sRow >= 0 && board.getLb().getBoard()[sRow][sCol] == Consts.PLAYERS.PLAYER2; i++, sRow--, sCol--, count++)
            ;

        return (int) Math.pow(10, count);
    }

    public int calculateSeconderyDiagonal(int col, int row) {
        int count = 0;

        int sCol = col;
        int sRow = row;

        for (int i = 0; i < 4 && sCol >= 0 && sRow <= 5 && board.getLb().getBoard()[sRow][sCol] == Consts.PLAYERS.PLAYER2; i++, sRow++, sCol--, count++)
            ;
        sCol = col + 1;
        sRow = row - 1;
        for (int i = 0; i < 4 && sCol <= 6 && sRow >= 0 && board.getLb().getBoard()[sRow][sCol] == Consts.PLAYERS.PLAYER2; i++, sRow--, sCol++, count++)
            ;

        return (int) Math.pow(10, count);
    }


    public int getCol() {

        int col = 3;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < scores.length; i++) {
            if (max < scores[i]) {
                max = scores[i];
                col = i;
            }
        }

        return col;
    }

}
