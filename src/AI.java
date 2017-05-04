/**
 * Created by Tal Taub on 02/05/2017.
 */
public class AI implements Consts {

    private Board board;

    public AI(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int calculateScores() {
        int grade = 0;

        for (int col = 0; col < maxCol; col++) {
            int row = board.getLb().getHeight()[col];
            grade += calculateRow(col, row);
            grade += calculateCol(col, row);
            grade += calculateMainDiagonal(col, row);
            grade += calculateSeconderyDiagonal(col, row);
        }

        return grade;

    }

    public int calculateRow(int col, int row) {

        int markHumen = 0, markComputer = 0;
        int[] counters = new int[3];
        counters[0] = counters[1] = counters[2] = 0;

        //Right
        int i;
        for (i = 0; col + i < maxCol && i < seqLength; i++) {
            counters[board.getLb().getBoard()[row][col + i].ordinal()]++;
        }

        if (i == seqLength) {
            if (counters[PLAYERS.PLAYER1.ordinal()] > 0 && counters[PLAYERS.PLAYER2.ordinal()] == 0)
                markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()]);

            if (counters[PLAYERS.PLAYER2.ordinal()] > 0 && counters[PLAYERS.PLAYER1.ordinal()] == 0)
                markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()]);

            //_HH___
            if (counters[PLAYERS.PLAYER1.ordinal()] == seqLength - 2)
                if (board.getLb().getBoard()[row][col] == PLAYERS.NONE
                        && col + i < maxCol
                        && board.getLb().getBoard()[row][col + i - 1] == PLAYERS.NONE
                        && board.getLb().getBoard()[row][col + i] == PLAYERS.NONE) {
                    markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()] + 1);
                }

            //_CC___
            if (counters[PLAYERS.PLAYER2.ordinal()] == seqLength - 2)
                if (board.getLb().getBoard()[row][col] == PLAYERS.NONE
                        && col + i < maxCol
                        && board.getLb().getBoard()[row][col + i - 1] == PLAYERS.NONE
                        && board.getLb().getBoard()[row][col + i] == PLAYERS.NONE) {
                    markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()] + 1);
                }


        }

        //Left
        for (i = counters[0] = counters[1] = counters[2] = 0; col - i >= 0 && i < seqLength; i++) {
            counters[board.getLb().getBoard()[row][col - i].ordinal()]++;
        }

        if (i == seqLength) {
            if (counters[PLAYERS.PLAYER1.ordinal()] > 0 && counters[PLAYERS.PLAYER2.ordinal()] == 0)
                markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()]);

            if (counters[PLAYERS.PLAYER2.ordinal()] > 0 && counters[PLAYERS.PLAYER1.ordinal()] == 0)
                markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()]);

            //__HH_
            if (counters[PLAYERS.PLAYER1.ordinal()] == seqLength - 2)
                if (board.getLb().getBoard()[row][col] == PLAYERS.NONE
                        && col - i >= 0
                        && board.getLb().getBoard()[row][col - i + 1] == PLAYERS.NONE
                        && board.getLb().getBoard()[row][col - i] == PLAYERS.NONE) {
                    markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()] + 1);
                }
            //__CC_
            if (counters[PLAYERS.PLAYER2.ordinal()] == seqLength - 2)
                if (board.getLb().getBoard()[row][col] == PLAYERS.NONE
                        && col - i >= 0
                        && board.getLb().getBoard()[row][col - i + 1] == PLAYERS.NONE
                        && board.getLb().getBoard()[row][col - i] == PLAYERS.NONE) {
                    markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()] + 1);
                }

        }


        return markComputer - markHumen;
    }

    public int calculateCol(int col, int row) {
        int markHumen = 0, markComputer = 0;
        int[] counters = new int[3];
        counters[0] = counters[1] = counters[2] = 0;

        int i;
        for (i = 0; row - i >= 0 && i < seqLength; i++) {
            counters[board.getLb().getBoard()[row - i][col].ordinal()]++;
        }

        if (i == seqLength) {
            if (counters[PLAYERS.PLAYER1.ordinal()] > 0 && counters[PLAYERS.PLAYER2.ordinal()] == 0)
                markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()]);

            if (counters[PLAYERS.PLAYER2.ordinal()] > 0 && counters[PLAYERS.PLAYER1.ordinal()] == 0)
                markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()]);
        }

        return (markComputer - markHumen);

    }

    public int calculateMainDiagonal(int col, int row) {

        int markHumen = 0, markComputer = 0;
        int[] counters = new int[3];
        counters[0] = counters[1] = counters[2] = 0;

        int i;
        for (i = 0; row - i >= 0 && col - i >= 0 && i < seqLength; i++) {
            counters[board.getLb().getBoard()[row - i][col - i].ordinal()]++;
        }

        if (i == seqLength) {
            if (counters[PLAYERS.PLAYER1.ordinal()] > 0 && counters[PLAYERS.PLAYER2.ordinal()] == 0)
                markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()]);

            if (counters[PLAYERS.PLAYER2.ordinal()] > 0 && counters[PLAYERS.PLAYER1.ordinal()] == 0)
                markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()]);
        }

        return (markComputer - markHumen);
    }

    public int calculateSeconderyDiagonal(int col, int row) {

        int markHumen = 0, markComputer = 0;
        int[] counters = new int[3];
        counters[0] = counters[1] = counters[2] = 0;

        int i;
        for (i = 0; row - i >= 0 && col + i < maxCol && i < seqLength; i++) {
            counters[board.getLb().getBoard()[row - i][col + i].ordinal()]++;
        }

        if (i == seqLength) {
            if (counters[PLAYERS.PLAYER1.ordinal()] > 0 && counters[PLAYERS.PLAYER2.ordinal()] == 0)
                markHumen += (int) Math.pow(10, counters[PLAYERS.PLAYER1.ordinal()]);

            if (counters[PLAYERS.PLAYER2.ordinal()] > 0 && counters[PLAYERS.PLAYER1.ordinal()] == 0)
                markComputer += (int) Math.pow(10, counters[PLAYERS.PLAYER2.ordinal()]);
        }

        return (markComputer - markHumen);


    }


}
