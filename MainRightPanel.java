import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainRightPanel extends JPanel
{
    private JPanel panel1;
    private JPanel panel2;

    public MainRightPanel()
    {
        setLayout(new GridLayout(3,1,5,5));
        panel1=new JPanel(new GridLayout(2,2,10,10));
        panel2=new JPanel(new GridLayout(2,2,10,10));
        panel1.add(new JTextField());
        panel1.add(new JButton("Add User"));
        panel1.add(new JTextField());
        panel1.add(new JButton("Add Group"));
        //panel2
        panel2.add(new JButton("Show User Total"));
        panel2.add(new JButton("Show Group Total"));
        panel2.add(new JButton("Show Message Total"));
        panel2.add(new JButton("Show Positive Percentage"));
        add(panel1);
        JButton openUser=new JButton("Open User View");
        openUser.addActionListener(new UserViewListener());
        add(openUser);
        add(panel2);
    }
    private class UserViewListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            new UserViewFrame();
        }
    }
}