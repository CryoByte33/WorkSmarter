import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class WorkCalcGUI extends JFrame implements ActionListener
{
    //Standard declarations.
    JPanel mainPanel;
    JLabel wageLabel, incrementLabel, totalMadeLabel, totalMade, hoursPassed, minutesPassed, secondsPassed, afterTaxLabel, afterTax;
    JTextField wageBox,incrementField;
    JRadioButton hourly, salaried;
    GradientButton go, stop, pause;

    boolean isHourly = true;
    float payGrade;
    float amountPerSecond = 0f;
    int secondsElapsed = 0;
    int increment = 1;
    int runningTotal;
    int minutes = 0;
    int hours = 0;
    DecimalFormat df = new DecimalFormat("$##0.00");
    DecimalFormat time = new DecimalFormat("00");

    //Volatile to prevent any cache issues bleeding.
    volatile boolean isInterrupted = false;

    //Thread to prevent lockup.
    Thread thread = new Thread()
    {
        public void run()
        {
            timeToGo();
        }
    };

    //Main constructor.
    public WorkCalcGUI(Color colorOne, Color colorTwo, int direction)
    {
        //Implement fields and listeners.
        mainPanel = new JPanel();
        wageLabel = new JLabel("Wage:");
        wageBox = new JTextField(10);
        totalMadeLabel = new JLabel("Total Made:");
        totalMade = new JLabel("$0.00");
        incrementLabel = new JLabel("How Often to Refresh (In Seconds):");
        incrementField = new JTextField(2);
        hoursPassed = new JLabel("Hours: 00");
        minutesPassed = new JLabel("Minutes: 00");
        secondsPassed = new JLabel("Seconds: 00");
        afterTaxLabel = new JLabel("After Tax: ");
        afterTax = new JLabel("$0.00");

        hourly = new JRadioButton("Hourly", true);
        hourly.addActionListener(this);

        salaried = new JRadioButton("Salaried", false);
        salaried.addActionListener(this);

        go = new GradientButton(colorOne, colorTwo, direction, "Start");
        go.addActionListener(this);

        stop = new GradientButton(colorOne, colorTwo, direction, "Stop");
        stop.addActionListener(this);

        pause = new GradientButton(colorOne, colorTwo, direction, "Pause/Resume");
        pause.addActionListener(this);

        //Formatting and whatnot.
        mainPanel.add(wageLabel);
        mainPanel.add(wageBox);
        mainPanel.add(incrementLabel);
        mainPanel.add(incrementField);
        mainPanel.add(hourly);
        mainPanel.add(salaried);

        mainPanel.add(new JLabel("                                                             ")); //Forced separator.
        mainPanel.add(hoursPassed);
        mainPanel.add(minutesPassed);
        mainPanel.add(secondsPassed);
        mainPanel.add(go);
        mainPanel.add(totalMadeLabel);
        mainPanel.add(totalMade);
        mainPanel.add(stop);
        mainPanel.add(new JLabel("                 ")); //Forced separator.
        mainPanel.add(afterTaxLabel);
        mainPanel.add(afterTax);
        mainPanel.add(new JLabel("                 ")); //Forced separator.
        mainPanel.add(pause);
        this.add(mainPanel);
    }

    //Main action class. Performs all calculations.
    public void timeToGo()
    {
        //Determines pay per second.
        if (isHourly) //Hourly Pay
        {
            payGrade = Float.parseFloat(wageBox.getText());
            amountPerSecond = (payGrade / 3600);
        }
        else if (!isHourly) //Salaried
        {
            payGrade = Float.parseFloat(wageBox.getText());
            amountPerSecond = (payGrade / 7488000); //Weeks*days*hours*minutes*seconds
        }

        //Sets update interval.
        increment = Integer.parseInt(incrementField.getText());

        //Max time running of one year.
        while (secondsElapsed < 7488000)
        {
            //Time handler. Simple CPU friendly implementation.
            runningTotal = (secondsElapsed - (60 * minutes) - (3600 * hours));

            while (runningTotal >= 60)
            {
                minutes = minutes + 1;
                runningTotal = runningTotal - 60;
            }

            while (minutes >= 60)
            {
                hours = hours + 1;
                minutes = minutes - 60;
            }

            //Sleeps for chosen update interval.
            try
            {
                thread.sleep(increment * 1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            float total = (amountPerSecond * secondsElapsed);

            //Puts data in correct field and formatting style.
            hoursPassed.setText("Hours: " + time.format(hours));
            minutesPassed.setText("Minutes: " + time.format(minutes));
            secondsPassed.setText("Seconds: " + time.format(runningTotal));
            totalMade.setText(df.format(total));
            afterTax.setText(df.format(total * .8));

            secondsElapsed = secondsElapsed + increment;

            //Ends loop if pause is selected.
            if (isInterrupted)
            {
                synchronized (thread)
                {
                    try
                    {
                        thread.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == hourly) //Radio Buttons just update correct variable and appearance.
        {
            isHourly = true;
            hourly.setSelected(true);
            salaried.setSelected(false);
        }

        if (event.getSource() == salaried)
        {
            isHourly = false;
            salaried.setSelected(true);
            hourly.setSelected(false);
        }

        if (event.getSource() == go) //Start button. Simple
        {
            if (thread.isAlive() == false)
            {
                thread.start();
            }
        }

        if (event.getSource() == pause)
        {
            if (isInterrupted)
            {
                synchronized (thread)
                {
                    isInterrupted = false;
                    thread.notify();
                }
            }
            else
            {
                isInterrupted = true;
            }
        }

        if (event.getSource() == stop)
        {
            thread.stop();

            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            thread = new Thread()
            {
                public void run()
                {
                    timeToGo();
                }
            };

            wageLabel.setText("Wage:");
            wageBox.setText("");
            totalMadeLabel.setText("Total Made:");
            totalMade.setText("$0.00");
            incrementLabel.setText("How Often to Refresh (In Seconds):");
            incrementField.setText("");
            hoursPassed.setText("Hours: 00");
            minutesPassed.setText("Minutes: 00");
            secondsPassed.setText("Seconds: 00");
            hourly.setSelected(true);
            salaried.setSelected(false);
            isHourly = true;
            amountPerSecond = 0f;
            secondsElapsed = 0;
            increment = 1;
            minutes = 0;
            hours = 0;
            runningTotal = 0;
        }
    }
}
