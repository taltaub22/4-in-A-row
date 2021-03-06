/**
 * Created by Tal Taub on 02/05/2017.
 */
public class AI implements Consts {

    private Board board;
    public int depth = 3;
    public int bestCol;

    public AI(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getBestCol() {
        return bestCol;
    }

    public void setBestCol(int bestCol) {
        this.bestCol = bestCol;
    }

    public int calculateScores() {
        int grade = 0;

        for (int col = 0; col < maxCol; col++) {
            int row = board.getLb().getHeight()[col];
            //If row is full, skip over it
            if (board.getLb().getHeight()[col] >= 0) {
                grade += calculateRow(col, row);
                grade += calculateCol(col, row);
                grade += calculateMainDiagonal(col, row);
                grade += calculateSeconderyDiagonal(col, row);
            }
        }

        return grade;

    }

    public int calculateRow(int col, int row) {

        int markHumen = 0, markComputer = 0;
        int[] counters = new int[3];
        counters[PLAYERS.NONE.ordinal()] = counters[PLAYERS.PLAYER1.ordinal()] = counters[PLAYERS.PLAYER2.ordinal()] = 0;

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
        counters[PLAYERS.NONE.ordinal()] = counters[PLAYERS.PLAYER1.ordinal()] = counters[PLAYERS.PLAYER2.ordinal()] = 0;

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
        counters[PLAYERS.NONE.ordinal()] = counters[PLAYERS.PLAYER1.ordinal()] = counters[PLAYERS.PLAYER2.ordinal()] = 0;
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
        counters[PLAYERS.NONE.ordinal()] = counters[PLAYERS.PLAYER1.ordinal()] = counters[PLAYERS.PLAYER2.ordinal()] = 0;

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

    public int negaMax(int depth, PLAYERS player) {
        int best = Integer.MIN_VALUE;
        int val, col;

        if (depth == 0)
            return -this.calculateScores();

        for (col = 0; col < maxCol; col++) {
            if (board.getLb().getHeight()[col] >= 0) {
                board.getLb().getBoard()[board.getLb().getHeight()[col]][col] = player;// Make move
                board.getLb().getHeight()[col]--;
                val = -negaMax(depth - 1, (player == PLAYERS.PLAYER1 ? PLAYERS.PLAYER2 : PLAYERS.PLAYER1));

                board.getLb().getHeight()[col]++;
                board.getLb().getBoard()[board.getLb().getHeight()[col]][col] = PLAYERS.NONE;// undo move
                if (val > best) {
                    best = val;
                    // Only keep best move in the first
                    // game tree level
                    if (depth == this.depth) {
                        bestCol = col;
                    }
                }
            }
        }
        return (best);
    }

}
