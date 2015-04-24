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
    JPanel optionsPane;
    JColorChooser colorChooser, colorChooser2;
    JButton confirm;

    public OptionsButton()
    {
        optionsPane = new JPanel();

        colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(this);

        colorChooser2 = new JColorChooser();
        colorChooser2.getSelectionModel().addChangeListener(this);

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);

        optionsPane.add(colorChooser);
        optionsPane.add(colorChooser2);
        optionsPane.add(confirm);
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
    }
}
