package TabPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by s000191354 on 4/11/17.
 */
public class CurrentCommittee {

    private String[] Committees = {"Blue", "Grean", "Yellow"};
    private JComboBox CommitteeBox = new JComboBox();
    private JTextField BoxTextField = new JTextField(15);
    private JButton DropDownButton = new JButton("Add items");

    private String[] description = { "Ebullient", "Obtuse", "Recalcitrant",
            "Brilliant", "Somnescent", "Timorous", "Florid", "Putrescent" };

    private JComboBox c = new JComboBox();

    private JButton b = new JButton("Add items");

    private int count = 0;
    private JPanel DropDownPanel;
    private JPanel CurrentCommittee;






    public static void main(String[] args) {
        run();

    }


    public JPanel getPanel(){
        CurrentCommittee = new JPanel();
        //JLabel filler = new JLabel("TEST");
        //filler.setHorizontalAlignment(JLabel.CENTER);
        CurrentCommittee.setLayout(new GridLayout(1,1));
        //CurrentCommittee.add(filler);
        createDropDown();

        return CurrentCommittee;


    }

    public void createDropDown(){
        DropDownPanel = new JPanel();

        for (int i = 0; i < 4; i++)
            c.addItem(description[count++]);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (count < description.length)
                    c.addItem(description[count++]);
            }
        });
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        DropDownPanel.add(c);
        DropDownPanel.add(b);

        CurrentCommittee.add(DropDownPanel);
    }

    public static void run() {
        TabPanels.CurrentCommittee c = new CurrentCommittee();
        c.getPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(c.DropDownPanel);
        frame.setVisible(true);
    }
}

/**
 * Load Drop down box.
 */
