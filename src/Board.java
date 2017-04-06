import javax.swing.*;

/**
 * Created by Tal Taub on 19/02/2017.
 */
public class Board {

    private GraphicalBoard gb;
    private LogicBoard lb;

    public Board() {
        lb = new LogicBoard();
        gb = new GraphicalBoard();
    }

    public GraphicalBoard getGb() {
        return gb;
    }

    public void setGb(GraphicalBoard gb) {
        this.gb = gb;
    }

    public LogicBoard getLb() {
        return lb;
    }

    public void setLb(LogicBoard lb) {
        this.lb = lb;
    }

    public boolean insertChecker(int col, Players.PLAYERS player) {
        try {
            lb.insertChecker(col, player);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            JOptionPane.showMessageDialog(null,"Column is full, choose another one.");
            return false;
        }
        gb.insertChecker(col, player);
        return true;
    }

}
