/**
 * Created by Tal Taub on 02/05/2017.
 */
public class AI {

    private int[] scores;

    public AI() {
        scores = new int[7];
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public void calculateScores(Board board) {

        Consts.PLAYERS[][] testBoard = board.getLb().getBoard();

        //Setting the score for each column
        for (int col = 0; col < 7; col++) {

            int row = 5;
            for (; row >= 0 && testBoard[row][col] != Consts.PLAYERS.NONE; row--) ;

            //Col
            int count = 0;
            for (int i = 5; i >= row && testBoard[i][col] == Consts.PLAYERS.PLAYER2 && count < 4; i--, count++) ;
            scores[col] += Math.pow(10, count);

            //Row
            int checkRight = (col + (4 - 1)) >= 6 ? 6 : col + (4 - 1);
            int checkLeft = (col - (4 - 1)) <= 0 ? 0 : col - (4 - 1);

            for (int i = col; i >= checkLeft && testBoard[row][i] == Consts.PLAYERS.PLAYER2 && count <= 4; i--, count++)
                ;
            for (int i = col + 1; i <= checkRight && testBoard[row][i] == Consts.PLAYERS.PLAYER2 && count <= 4; i++, count++)
                ;
            scores[col] += Math.pow(10, count);

            //Main Diagonal

            //Secondary Diagonal

        }


    }
}
