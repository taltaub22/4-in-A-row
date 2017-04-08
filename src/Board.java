import javax.swing.*;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class Board {

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
            gb.insertChecker(col, player);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            JOptionPane.showMessageDialog(null,"Column is full, choose another one.");
        }
        return row;
    }

    public Consts.PLAYERS checkWin(int col, int row, Consts.PLAYERS player) {
        return lb.checkWin(col, row, player);
    }

}
