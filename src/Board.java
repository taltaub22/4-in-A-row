import javax.swing.*;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class Board implements Consts {

    private GraphicalBoard gb;
    private LogicalBoard lb;

    public Board() {
        lb = new LogicalBoard();
        gb = new GraphicalBoard();
    }

    public GraphicalBoard getGb() {
        return gb;
    }

    public void setGb(GraphicalBoard gb) {
        this.gb = gb;
    }

    public LogicalBoard getLb() {
        return lb;
    }

    public void setLb(LogicalBoard lb) {
        this.lb = lb;
    }

    public int insertChecker(int col, Consts.PLAYERS player) {
        int row = -1;
        try {
            row = lb.insertChecker(col, player);
            gb.insertChecker(col, row, player);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            JOptionPane.showMessageDialog(null, "Column is full, choose another one.");
        }
        return row;
    }

    public boolean checkWin(int col, int row, Consts.PLAYERS player) {
        return lb.checkWin(col, row, player);
    }

    public void undoMove(int col) {
        int row = getLb().getHeight()[col];
        lb.undoMove(col, row);
        gb.undoMove(col, row);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < maxCol; i++) {
            if (lb.getHeight()[i] >= 0)
                return false;
        }
        return true;
    }

    public void save(int turn) {
        lb.save(turn);
    }

    public int load() {
        int turn = lb.load();
        gb.initBoard(lb.getBoard());
        return turn;
    }
}
