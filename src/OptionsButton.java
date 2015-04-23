import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class OptionsButton extends JFrame implements ChangeListener
{
    JPanel optionsPane;
    JColorChooser colorChooser, colorChooser2;
    Color chosenColor, chosenColor2;

    public OptionsButton()
    {
        optionsPane = new JPanel();

        colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(this);

        colorChooser2 = new JColorChooser();
        colorChooser2.getSelectionModel().addChangeListener(this);

        optionsPane.add(colorChooser);
        optionsPane.add(colorChooser2);
        this.add(optionsPane);
    }

    public static void main(String[] args)
    {
        OptionsButton box = new OptionsButton();
        box.setTitle("Options");
        box.setSize(800, 400);
        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        box.setVisible(true);
    }

    public Color getColor()
    {
        return (chosenColor);
    }

    public Color getColor2()
    {
        return (chosenColor2);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        chosenColor = colorChooser.getColor();
        chosenColor2 = colorChooser2.getColor();
    }
}
