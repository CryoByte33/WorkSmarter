import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WorkCalc
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner( System.in );
        float payGrade;
        float amountPerSecond = 0f;
        int secondsElapsed = 0;
        int increment;
        int runningTotal;
        int minutes = 0;
        int hours = 0;
        DecimalFormat df = new DecimalFormat("$##0.00");

        System.out.println("This program takes your wage and refreshes at");
        System.out.println("a custom rate, letting you know how much you've");
        System.out.println("made since the start of the program.");
        System.out.println("Created By: Kyle Mace");

        System.out.print("Are you hourly? (Y or N) => ");
        char input = scanner.next().charAt(0);
        input = Character.toUpperCase(input);

        if (input == 'Y')
        {
            System.out.print("How much do you make per hour? (Dollar Amount) => ");
            payGrade = scanner.nextFloat();
            amountPerSecond = (payGrade / 3600);
        }
        else if (input == 'N')
        {
            System.out.print("How much do you make per year? (Dollar Amount) => ");
            payGrade = scanner.nextFloat();
            amountPerSecond = (payGrade / 7488000); //Weeks*days*hours*minutes*seconds
        }
        else
        {
            System.out.print("Invalid input, terminating program.");
        }

        System.out.print("How often should it refresh? (In seconds) => ");
        increment = scanner.nextInt();

        while (secondsElapsed < 7488000)
        {
            runningTotal = (secondsElapsed - (60 * minutes) - (3600 * hours));

            while(runningTotal >= 60)
            {
                minutes = minutes + 1;
                runningTotal = runningTotal - 60;
            }

            while (minutes >= 60)
            {
                hours = hours + 1;
                minutes = minutes - 60;
            }

            float total = (amountPerSecond * secondsElapsed);
            System.out.printf("Time Elapsed: %2d hours, %2d minutes, %2d seconds ---------- You have made ",hours,minutes,runningTotal);
            System.out.println(df.format(total));

            try
            {
                TimeUnit.SECONDS.sleep(increment);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            secondsElapsed = secondsElapsed + increment;
        }
    }
}
