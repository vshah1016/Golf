import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GolfCourse{
    public JPanel frame;
    private JButton mo_lord;

    public GolfCourse(){
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        mo_lord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println();
            }
        });
    }

    public JButton getMo_lord() {
        return mo_lord;
    }
}
