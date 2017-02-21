/**
 * Created by Tal Taub on 12/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        LogicBoard lb = new LogicBoard();
        try {
            lb.insertChecker(3,Players.PLAYERS.PLAYER1);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }


        try {
            lb.insertChecker(3, Players.PLAYERS.PLAYER1);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }

        try {
            lb.insertChecker(2,Players.PLAYERS.PLAYER2);
        } catch (NotInBoardException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }

        Players.PLAYERS[][] a = lb.getBoard();
    }


}
