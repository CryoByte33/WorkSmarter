import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsButton extends JDialog implements ActionListener, ChangeListener
{
    static Color chosenColor1;
    static Color chosenColor2;
    JPanel optionsPane = new JPanel(new BorderLayout());
    ;
    JPanel colorPane = new JPanel(new GridLayout(0, 1));
    JPanel layoutPane = new JPanel(new GridLayout(0, 3));
    JColorChooser colorChooser, colorChooser2;
    JRadioButton standardLayout, tallLayout, wideLayout;
    GradientButton confirm;

    public OptionsButton()
    {
        colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(this);

        colorChooser2 = new JColorChooser();
        colorChooser2.getSelectionModel().addChangeListener(this);

        standardLayout = new JRadioButton("Standard", true);
        ProgramHub.windowSize = 0;
        standardLayout.addActionListener(this);

        tallLayout = new JRadioButton("Tall", false);
        tallLayout.addActionListener(this);

        wideLayout = new JRadioButton("Wide", false);
        wideLayout.addActionListener(this);

        confirm = new GradientButton("Confirm");
        confirm.addActionListener(this);

        colorPane.add(new JLabel("Color 1:"));
        colorPane.add(colorChooser);
        colorPane.add(new JLabel("Color 2:"));
        colorPane.add(colorChooser2);
        layoutPane.add(new JLabel("Layouts:"));
        layoutPane.add(standardLayout);
        layoutPane.add(tallLayout);
        layoutPane.add(wideLayout);
        optionsPane.add(layoutPane, BorderLayout.EAST);
        optionsPane.add(colorPane, BorderLayout.CENTER);
        optionsPane.add(confirm, BorderLayout.SOUTH);

        this.add(optionsPane);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        chosenColor1 = colorChooser.getColor();
        chosenColor2 = colorChooser2.getColor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            ProgramHub.color1 = chosenColor1;
            ProgramHub.color2 = chosenColor2;
            this.dispose();
        }

        if (e.getSource() == standardLayout)
        {
            ProgramHub.windowSize = 0;
            standardLayout.setSelected(true);
            tallLayout.setSelected(false);
            wideLayout.setSelected(false);
        }

        if (e.getSource() == tallLayout)
        {
            ProgramHub.windowSize = 1;
            standardLayout.setSelected(false);
            tallLayout.setSelected(true);
            wideLayout.setSelected(false);
        }

        if (e.getSource() == wideLayout)
        {
            ProgramHub.windowSize = 2;
            standardLayout.setSelected(false);
            tallLayout.setSelected(false);
            wideLayout.setSelected(true);
        }
    }
}
