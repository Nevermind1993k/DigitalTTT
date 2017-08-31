import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private int width = 400;
    private int height = 400;

    private JPanel window = new JPanel();
    private JFrame frame = new JFrame();

    private JButton solo, duo, online, options, about, quit;
    private Rectangle Rsolo, Rduo, Ronline, Roptions, Rabout, Rquit;

    private int buttonWidth = 80;
    private int buttonHeight = 40;

    private String title = "Digital Tic Tac Toe";

    public MainMenu() {
        frame.setTitle(title);
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.add(component);
        frame.getContentPane().add(window);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setIconImage(new ImageIcon("res/GameIcon.png").getImage());
        frame.setLayout(null);

        drawButtons();

        frame.repaint();
    }

    private void drawButtons() {
        solo = new JButton("Solo");
        Rsolo = new Rectangle((width / 2) - (buttonWidth / 2), 50, buttonWidth, buttonHeight);
        solo.setBounds(Rsolo);
        window.add(solo);

        duo = new JButton("Duo");
        Rduo = new Rectangle((width / 2) - (buttonWidth / 2), 100, buttonWidth, buttonHeight);
        duo.setBounds(Rduo);
        window.add(duo);

        online = new JButton("Online");
        Ronline = new Rectangle((width / 2) - (buttonWidth / 2), 150, buttonWidth, buttonHeight);
        online.setBounds(Ronline);
        window.add(online);

        options = new JButton("Options");
        Roptions = new Rectangle((width / 2) - (buttonWidth / 2), 200, buttonWidth, buttonHeight);
        options.setBounds(Roptions);
        window.add(options);

        about = new JButton("About");
        Rabout = new Rectangle((width / 2) - (buttonWidth / 2), 250, buttonWidth, buttonHeight);
        about.setBounds(Rabout);
        window.add(about);

        quit = new JButton("Quit");
        Rquit = new Rectangle((width / 2) - (buttonWidth / 2), 300, buttonWidth, buttonHeight);
        quit.setBounds(Rquit);
        window.add(quit);

        solo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });
        duo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                duoTTT frame = new duoTTT();
                //frame.dispose();
            }
        });
        online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("options");
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("about");
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
