import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


class DuoTTT extends JFrame implements TicTacToe {


    private final int FIELD = 5;//sc.nextInt();

    private JButton[][] buttons = new JButton[FIELD][FIELD];
    private GameListener listener = new GameListener();
    private JTextField statusBar;
    private Menu menu;
    private GamePanel gamePanel;
    private ChatPanel chat;
    private Integer turn, count;


    DuoTTT() {
        //JOptionPane.showMessageDialog(null, "Enter number of fields: ");

        setTitle("Tic Tac Toe vs Friend");
        setLayout(new BorderLayout());
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menu = new Menu();
        add(menu, BorderLayout.NORTH);

        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);


//        chat = new ChatPanel();
//        add(chat, BorderLayout.WEST);


        statusBar = new JTextField("Player 1 turn");
        statusBar.setEditable(false);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);

    }


    class Menu extends JMenuBar {
        Menu() {
            JMenuBar menuBar = new JMenuBar();
            setJMenuBar(menuBar);

            JMenu fileMenu = new JMenu("File");
            menuBar.add(fileMenu);

            JMenuItem newGame = new JMenuItem("New game",
                    new ImageIcon("res/GamepadIcon.png"));

            newGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restart();
                }
            });
            fileMenu.add(newGame);
            fileMenu.addSeparator();


            JMenuItem saveItem = new JMenuItem("Save",
                    new ImageIcon("res/SaveIcon.png"));
            fileMenu.add(saveItem);

            JMenuItem closeItem = new JMenuItem("Close",
                    new ImageIcon("res/CloseIcon.png"));

            closeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            fileMenu.add(closeItem);


            JMenu optMenu = new JMenu("Options");
            menuBar.add(optMenu);

            JMenuItem settingsItem = new JMenuItem("Settings",
                    new ImageIcon("res/GearsIcon.png"));
            optMenu.add(settingsItem);


            JMenu helpMenu = new JMenu("Help");
            menuBar.add(helpMenu);

            JMenuItem rulesItem = new JMenuItem("Rules",
                    new ImageIcon("res/RulesIcon.png"));
            rulesItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    printRules();
                }
            });
            helpMenu.add(rulesItem);

            JMenuItem historyItem = new JMenuItem("History",
                    new ImageIcon("res/BookIcon.png"));
            historyItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    printHistory();
                }
            });
            helpMenu.add(historyItem);

            JMenuItem aboutItem = new JMenuItem("About",
                    new ImageIcon("res/HelpIcon.png"));
            aboutItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    printInstructions();
                }
            });
            helpMenu.add(aboutItem);
        }
    }


    class GamePanel extends JPanel {
        GamePanel() {
            setLayout(new GridLayout(FIELD, FIELD));
            setSize(600, 600);
            turn = 1;
            count = 0;

            //add buttons on gamePanel
            for (int i = 0; i < FIELD; i++) {
                for (int j = 0; j < FIELD; j++) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].putClientProperty("INDEX", new Integer[]{i, j});
                    buttons[i][j].putClientProperty("OWNER", null);
                    buttons[i][j].addActionListener(listener);
                    add(buttons[i][j]);
                }
            }

        }
    }


    class ChatPanel extends JPanel {
        ChatPanel() {
            setLayout(new BorderLayout());
            setSize(100, 600);
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

                ImageIcon ico = new ImageIcon("res/" + turn.toString() + ".png");
                butt.setIcon(ico);
                butt.setEnabled(false);
                butt.setDisabledIcon(ico);

                boolean result = checkVictoryCondition(index);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Player " + turn.toString() + " Wins");
                    restart();
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
    public void printButtonMap(Integer[][] bMap) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(bMap[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public boolean checkVictoryCondition(Integer[] index) {
        Integer[][] buttonMap = new Integer[][]{
                {getOwner(buttons[0][0]), getOwner(buttons[0][1]), getOwner(buttons[0][2])},
                {getOwner(buttons[1][0]), getOwner(buttons[1][1]), getOwner(buttons[1][2])},
                {getOwner(buttons[2][0]), getOwner(buttons[2][1]), getOwner(buttons[2][2])}
        };
        printButtonMap(buttonMap);

        Integer a = index[0];
        Integer b = index[1];

        int i;

        //check row
        for (i = 0; i < FIELD; i++) {
            if (getOwner(buttons[a][i]) != getOwner(buttons[a][b])) {
                break;
            }
        }

        if (FIELD <= 3) {
            if (i == 3) {
                return true;
            }
        }

        if (FIELD >= 5) {
            if (i == 5) {
                return true;
            }
        }

        //check column
        for (i = 0; i < FIELD; i++) {
            if (getOwner(buttons[i][b]) != getOwner(buttons[a][b])) {
                break;
            }
        }
        if (FIELD <= 3) {
            if (i == 3) {
                return true;
            }
        }

        if (FIELD >= 5) {
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

    private boolean checkForDraw() {
        return false;
    }

    public void restart() {
        for (int i = 0; i < FIELD; i++) {
            for (int j = 0; j < FIELD; j++) {
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

    private void printRules() {
        JOptionPane.showMessageDialog(null, "Some shit about rules");
    }

    private void printHistory() {
        JOptionPane.showMessageDialog(null, "Some shit about History of the game");
    }

    private void printInstructions() {
        JOptionPane.showMessageDialog(null, "Some shit about program");
    }

}

