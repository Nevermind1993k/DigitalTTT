import javax.swing.*;
import java.awt.*;

public class FieldSizeBoard extends JFrame {

    private int width = 400;
    private int height = 200;
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel();
    private JTextField fieldSize = new JTextField();

    public FieldSizeBoard() {
        frame.setSize(width, height);
        frame.setTitle("Enter size");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setVisible(true);

        label.setText("Enter number of fields :");
        frame.add(label);

        frame.add(fieldSize);


    }

}
