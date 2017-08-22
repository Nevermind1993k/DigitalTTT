import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


class TicTacToeFrame extends JFrame {

    Scanner sc = new Scanner(System.in);
    int field = 5;//sc.nextInt();


    JButton[][] buttons = new JButton[field][field];
    GameListener listener = new GameListener();
    JTextField statusBar;
    GamePanel panel;
    ChatPanel chat;
    private Integer turn, count;

    //ImageIcon xIcon = new ImageIcon(this.getClass().getResource("Icons/1.png"));
    // ImageIcon oIcon = new ImageIcon(this.getClass().getResource("Icons/2.png"));


    TicTacToeFrame() {
        //JOptionPane.showMessageDialog(null, "Enter number of fields: ");

        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new GamePanel();
        add(panel, BorderLayout.CENTER);
//        chat = new ChatPanel();
//        add(chat, BorderLayout.EAST);


        statusBar = new JTextField("Player 1 turn");
        statusBar.setEditable(false);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);

    }

    class ChatPanel extends JPanel {
        ChatPanel() {
            setLayout(new BorderLayout());
            setSize(100, 600);
        }
    }


    class GamePanel extends JPanel {
        GamePanel() {
            setLayout(new GridLayout(field, field));
            setSize(600, 600);
            turn = 1;
            count = 0;

            //add buttons on panel
            for (int i = 0; i < field; i++) {
                for (int j = 0; j < field; j++) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].putClientProperty("INDEX", new Integer[]{i, j});
                    buttons[i][j].putClientProperty("OWNER", null);
                    buttons[i][j].addActionListener(listener);
                    add(buttons[i][j]);
                }
            }


        }
    }

    class GameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                count++;
                JButton butt = (JButton) e.getSource();
                Integer[] index = (Integer[]) butt.getClientProperty("INDEX");
                //System.out.println(turn);
                //System.out.println("[" + index[0] + "]" + "[" + index[1] + "]");
                butt.putClientProperty("OWNER", turn);

                ImageIcon ico = new ImageIcon(this.getClass().getResource("Icons/" + turn.toString() + ".png"));
                butt.setIcon(ico);
                butt.setEnabled(false);
                boolean result = checkVictoryCondition(index);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Player " + turn.toString() + " Wins");
                    initComponents();
                } else {
                    if (turn == 1) {
                        turn = 2;
                        statusBar.setText("Player 1 turn");
                    } else {
                        turn = 1;
                        statusBar.setText("Player 2 turn");
                    }
                }
            } catch (NullPointerException exp) {
                exp.printStackTrace();
            }


        }
    }

    private Integer getOwner(JButton b) {
        return (Integer) b.getClientProperty("OWNER");
    }

    //PrintButtonMap for Diagnostics
    private void printbuttonMap(Integer[][] bMap) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bMap[i][j] + " ");
            }
            System.out.println("");
        }
    }


    private boolean checkVictoryCondition(Integer[] index) {
        Integer[][] buttonMap = new Integer[][]{
                {getOwner(buttons[0][0]), getOwner(buttons[0][1]), getOwner(buttons[0][2])},
                {getOwner(buttons[1][0]), getOwner(buttons[1][1]), getOwner(buttons[1][2])},
                {getOwner(buttons[2][0]), getOwner(buttons[2][1]), getOwner(buttons[2][2])}
        };
        printbuttonMap(buttonMap);

        Integer a = index[0];
        Integer b = index[1];

        int i;

        //check row
        for (i = 0; i < field; i++) {
            if (getOwner(buttons[a][i]) != getOwner(buttons[a][b])) {
                break;
            }
        }

        if (field <= 3) {
            if (i == 3) {
                return true;
            }
        }

        if (field >= 5) {
            if (i == 5) {
                return true;
            }
        }

        //check column
        for (i = 0; i < field; i++) {
            if (getOwner(buttons[i][b]) != getOwner(buttons[a][b])) {
                break;
            }
        }
        if (field <= 3) {
            if (i == 3) {
                return true;
            }
        }

        if (field >= 5) {
            if (i == 5) {
                return true;
            }
        }

        //check diagonal
        if ((a == 0 && b == 0) || (a == 1 && b == 1) || (a == 2 && b == 2) || (a == 0 && b == 2) || (a == 2 && b == 0)) {
            // left diagonal
            if ((getOwner(buttons[0][0]) == getOwner(buttons[a][b]))
                    && (getOwner(buttons[1][1]) == getOwner(buttons[a][b]))
                    && (getOwner(buttons[2][2]) == getOwner(buttons[a][b]))) {
                return true;
            }

            //right diagonal
            if ((getOwner(buttons[0][2]) == getOwner(buttons[a][b]))
                    && (getOwner(buttons[1][1]) == getOwner(buttons[a][b]))
                    && (getOwner(buttons[2][0]) == getOwner(buttons[a][b]))) {
                return true;
            }
        }


        return false;
    }

    private void initComponents() {
        for (int i = 0; i < field; i++) {
            for (int j = 0; j < field; j++) {
                buttons[i][j].putClientProperty("INDEX", new Integer[]{i, j});
                buttons[i][j].putClientProperty("OWNER", null);
                buttons[i][j].setIcon(null);
                buttons[i][j].setEnabled(true);
                turn = 1;
                count = 0;
                statusBar.setText("Player1's Turn");
            }
        }
    }


}

