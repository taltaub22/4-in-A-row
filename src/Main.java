/**
 * Created by Tal Taub on 12/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        LogicBoard lb = new LogicBoard();
        try {
            lb.insertChecker(3);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }


        try {
            lb.insertChecker(3);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }

        try {
            lb.insertChecker(2);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }

        int[][] a = lb.getBoard();
    }


}
