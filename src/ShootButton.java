import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShootButton {
    public JPanel frame;
    private JButton shootButton;

    public ShootButton(){
        frame.setSize(1920, 1080);
        frame.setVisible(true);

        shootButton.addActionListener(e -> {
            try {
                Main.shootBall();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
    }
}
