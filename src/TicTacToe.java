import javax.swing.*;

public interface TicTacToe {


    void restart();

    void printButtonMap(Integer[][] bMap);

    boolean checkVictoryCondition(Integer[] index);
}
