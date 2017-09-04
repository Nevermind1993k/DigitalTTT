import javax.swing.*;
import java.awt.*;

public class FieldSizeBoard extends JFrame {

    private int width = 400;
    private int height = 200;

    private JTextField TFfieldSize = new JTextField();
    private JTextField TFplayer1Name = new JTextField();
    private JTextField TFplayer2Name = new JTextField();

    private JLabel LnumberOfTiles = new JLabel();
    private JLabel Lplayer1Name = new JLabel();
    private JLabel Lplayer2Name = new JLabel();

    private JFrame frame = new JFrame();

    private JButton bPlay = new JButton();


    public FieldSizeBoard() {
        frame.setSize(width, height);
        frame.setTitle("Enter size");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
//        frame.setResizable(false);
        frame.setVisible(true);

        LnumberOfTiles.setText("Enter number of tiles :");
        frame.add(LnumberOfTiles);

        TFfieldSize.setText("0");
        frame.add(TFfieldSize);

        Lplayer1Name.setText("Player 1 Enter Name: ");
        frame.add(Lplayer1Name);
        TFplayer1Name.setText("Player 1");
        frame.add(TFplayer1Name);

        Lplayer2Name.setText("Player 2 Enter Name: ");
        frame.add(Lplayer2Name);
        TFplayer2Name.setText("Player 2");
        frame.add(TFplayer2Name);

        bPlay.setText("Play");
        frame.add(bPlay);

    }

    public JTextField getTFfieldSize() {
        return TFfieldSize;
    }

    public JTextField getTFplayer1Name() {
        return TFplayer1Name;
    }

    public JTextField getTFplayer2Name() {
        return TFplayer2Name;
    }
}
