import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class ProgramHub extends JFrame implements ActionListener
{
    //Formatting variables
    static Color color1 = new Color(255, 255, 255);
    static Color color2 = new Color(117, 243, 250);
    static int direction = GradientButton.TOP_TO_BOTTOM;
    static int windowSize = 0;
    //Panels to be used.
    JPanel linkPanel;
    JPanel header;
    //Buttons for days!
    GradientButton calcButton, salesforceButton, etimeButton, wikiCentralButton, opsmartButton, jiraButton, btbbButton, bbhelpButton, testLabButton, bandwidthButton, headerButton;
    GradientButton windowsCalcButton, puttyButton, winscpButton, remoteDesktopButton, outlookButton, notepadButton, optionsButton;
    //Links for buttons.
    String headerPath = "http://github.com/CryoByte33/WorkSmarter";
    String salesforcePath = "https://blackboard.my.salesforce.com/console";
    String etimePath = "http://rocjfsweb06/eet/applications/wtk/html/ess/logon.jsp";
    String wikiCentralPath = "http://wikicentral.bbbb.net/login.action?logout=true";
    String opsmartPath = "https://opsmart.blackboard.com/tracksmart/login.php";
    String jiraPath = "https://carbon.pd.local:8443/secure/Dashboard.jspa";
    String btbbPath = "https://blackboard.secure.force.com/login";
    String bbhelpPath = "http://help.blackboard.com/";
    String testLabPath = "https://silicon.pd.local:8443/display/CSI/Test+Lab";
    String bandwidthPath = "http://10.8.224.35/";
    GridLayout standard = new GridLayout(0, 3, 10, 10);
    BorderLayout pageLayout = new BorderLayout();
    GridLayout headerLayout = new GridLayout(0, 1);
    String directory = System.getProperty("user.home") + "/AppData/Roaming/WorkSmarter/";
    String pathToFile = "WorkSmarter.txt";
    public Thread optionsThread = new Thread()
    {
        public void run()
        {
            launchOptions();
        }
    };
    //Threads for each program that's bootable.
    Thread calcThread = new Thread()
    {
        public void run()
        {
            launchWinCalc();
        }
    };
    Thread puttyThread = new Thread()
    {
        public void run()
        {
            launchPutty();
        }
    };
    Thread winscpThread = new Thread()
    {
        public void run()
        {
            launchWinscp();
        }
    };
    Thread remoteThread = new Thread()
    {
        public void run()
        {
            launchRemoteDesktop();
        }
    };
    Thread outlookThread = new Thread()
    {
        public void run()
        {
            launchOutlook();
        }
    };
    Thread notepadThread = new Thread()
    {
        public void run()
        {
            launchNotepad();
        }
    };

    //Main constructor
    public ProgramHub()
    {
        try
        {
            loadOptions();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (windowSize == 0)
        {
            standard = new GridLayout(0, 3, 10, 10);
        }
        else if (windowSize == 1)
        {
            standard = new GridLayout(0, 2, 0, 5);
        }
        else if (windowSize == 2)
        {
            standard = new GridLayout(0, 8, 10, 10);
        }

        initializeGUI();
    }

    //Initialize the program
    public static void main(String[] args)
    {
        ProgramHub box = new ProgramHub();
        box.setTitle("WorkSmarter");

        if (windowSize == 0)
        {
            box.setSize(450, 400);
        }
        else if (windowSize == 1)
        {
            box.setSize(325, 800);
        }
        else if (windowSize == 2)
        {
            box.setSize(1400, 250);
        }

        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        box.setVisible(true);
    }

    public void initializeGUI()
    {
        //Give panels layouts
        linkPanel = new JPanel(standard);
        header = new JPanel(headerLayout);

        //Fancy banner with error checking
        BufferedImage buttonIcon = null;
        try
        {
            buttonIcon = ImageIO.read(getClass().getResource("/images/worksmarter.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        headerButton = new GradientButton(color1, color2, direction, new ImageIcon(buttonIcon));
        headerButton.addActionListener(this);
        header.add(headerButton);

        //Various buttons being implemented
        calcButton = new GradientButton(color1, color2, direction, "Work Calculator");
        calcButton.addActionListener(this);

        salesforceButton = new GradientButton(color1, color2, direction, "SalesForce");
        salesforceButton.addActionListener(this);

        etimeButton = new GradientButton(color1, color2, direction, "eTime");
        etimeButton.addActionListener(this);

        wikiCentralButton = new GradientButton(color1, color2, direction, "WikiCentral");
        wikiCentralButton.addActionListener(this);

        opsmartButton = new GradientButton(color1, color2, direction, "OpSmart");
        opsmartButton.addActionListener(this);

        jiraButton = new GradientButton(color1, color2, direction, "JIRA");
        jiraButton.addActionListener(this);

        btbbButton = new GradientButton(color1, color2, direction, "BTBB");
        btbbButton.addActionListener(this);

        bbhelpButton = new GradientButton(color1, color2, direction, "Bb Help");
        bbhelpButton.addActionListener(this);

        testLabButton = new GradientButton(color1, color2, direction, "Test Lab");
        testLabButton.addActionListener(this);

        bandwidthButton = new GradientButton(color1, color2, direction, "Bandwidth");
        bandwidthButton.addActionListener(this);

        windowsCalcButton = new GradientButton(color1, color2, direction, "Windows Calculator");
        windowsCalcButton.addActionListener(this);

        puttyButton = new GradientButton(color1, color2, direction, "PuTTY");
        puttyButton.addActionListener(this);

        winscpButton = new GradientButton(color1, color2, direction, "WinSCP");
        winscpButton.addActionListener(this);

        remoteDesktopButton = new GradientButton(color1, color2, direction, "Remote Desktop");
        remoteDesktopButton.addActionListener(this);

        outlookButton = new GradientButton(color1, color2, direction, "Outlook");
        outlookButton.addActionListener(this);

        notepadButton = new GradientButton(color1, color2, direction, "Notepad++");
        notepadButton.addActionListener(this);

        optionsButton = new GradientButton(color1, color2, direction, "Options");
        optionsButton.addActionListener(this);

        //Add all the things to the panel
        linkPanel.add(salesforceButton);
        linkPanel.add(etimeButton);
        linkPanel.add(wikiCentralButton);
        linkPanel.add(opsmartButton);
        linkPanel.add(jiraButton);
        linkPanel.add(btbbButton);
        linkPanel.add(bbhelpButton);
        linkPanel.add(testLabButton);
        linkPanel.add(bandwidthButton);
        linkPanel.add(windowsCalcButton);
        linkPanel.add(puttyButton);
        linkPanel.add(winscpButton);
        linkPanel.add(remoteDesktopButton);
        linkPanel.add(outlookButton);
        linkPanel.add(notepadButton);
        linkPanel.add(optionsButton);

        //Formatting
        this.setLayout(pageLayout);
        this.add(header, BorderLayout.PAGE_START);
        this.add(linkPanel, BorderLayout.CENTER);
        this.add(calcButton, BorderLayout.PAGE_END);
    }

    public void updateGUI()
    {
        linkPanel.removeAll();
        this.remove(linkPanel);

        headerButton.setGradient(color1, color2, direction);
        calcButton.setGradient(color1, color2, direction);
        salesforceButton.setGradient(color1, color2, direction);
        etimeButton.setGradient(color1, color2, direction);
        wikiCentralButton.setGradient(color1, color2, direction);
        opsmartButton.setGradient(color1, color2, direction);
        jiraButton.setGradient(color1, color2, direction);
        btbbButton.setGradient(color1, color2, direction);
        bbhelpButton.setGradient(color1, color2, direction);
        testLabButton.setGradient(color1, color2, direction);
        bandwidthButton.setGradient(color1, color2, direction);
        windowsCalcButton.setGradient(color1, color2, direction);
        puttyButton.setGradient(color1, color2, direction);
        winscpButton.setGradient(color1, color2, direction);
        remoteDesktopButton.setGradient(color1, color2, direction);
        outlookButton.setGradient(color1, color2, direction);
        notepadButton.setGradient(color1, color2, direction);
        optionsButton.setGradient(color1, color2, direction);

        if (windowSize == 0)
        {
            this.setSize(450, 400);
            standard = new GridLayout(0, 3, 10, 10);
        }
        else if (windowSize == 1)
        {
            this.setSize(325, 800);
            standard = new GridLayout(0, 2, 0, 5);
        }
        else if (windowSize == 2)
        {
            this.setSize(1400, 250);
            standard = new GridLayout(0, 8, 10, 10);
        }

        linkPanel = new JPanel(standard);

        linkPanel.add(salesforceButton);
        linkPanel.add(etimeButton);
        linkPanel.add(wikiCentralButton);
        linkPanel.add(opsmartButton);
        linkPanel.add(jiraButton);
        linkPanel.add(btbbButton);
        linkPanel.add(bbhelpButton);
        linkPanel.add(testLabButton);
        linkPanel.add(bandwidthButton);
        linkPanel.add(windowsCalcButton);
        linkPanel.add(puttyButton);
        linkPanel.add(winscpButton);
        linkPanel.add(remoteDesktopButton);
        linkPanel.add(outlookButton);
        linkPanel.add(notepadButton);
        linkPanel.add(optionsButton);

        this.add(linkPanel, BorderLayout.CENTER);
    }

    //Implementation of abstract methods/
    @Override
    public void actionPerformed(ActionEvent event)
    {
        //Make link buttons do things
        if (event.getSource() == headerButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(headerPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (event.getSource() == calcButton)
        {
            WorkCalcGUI workCalc = new WorkCalcGUI(color1, color2, direction);
            workCalc.setTitle("Work Calculator");
            workCalc.setSize(260, 250);
            workCalc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            workCalc.setVisible(true);
        }

        if (event.getSource() == salesforceButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(salesforcePath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == etimeButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(etimePath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == wikiCentralButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(wikiCentralPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == opsmartButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(opsmartPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == jiraButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(jiraPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == btbbButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(btbbPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == bbhelpButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(bbhelpPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == testLabButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(testLabPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (event.getSource() == bandwidthButton)
        {
            try
            {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(bandwidthPath));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //Start threads if not active.
        if (event.getSource() == windowsCalcButton)
        {
            if (calcThread.isAlive() == false)
            {
                calcThread.start();
            }
        }

        if (event.getSource() == puttyButton)
        {
            if (puttyThread.isAlive() == false)
            {
                puttyThread.start();
            }
        }

        if (event.getSource() == winscpButton)
        {
            if (winscpThread.isAlive() == false)
            {
                winscpThread.start();
            }
        }

        if (event.getSource() == remoteDesktopButton)
        {
            if (remoteThread.isAlive() == false)
            {
                remoteThread.start();
            }
        }

        if (event.getSource() == outlookButton)
        {
            if (outlookThread.isAlive() == false)
            {
                outlookThread.start();
            }
        }

        if (event.getSource() == notepadButton)
        {
            if (notepadThread.isAlive() == false)
            {
                notepadThread.start();
            }
        }

        if (event.getSource() == optionsButton)
        {
            if (optionsThread.isAlive() == false)
            {
                optionsThread.start();
            }
        }
    }

    //Everything following is launch and thread logic. To be improved.
    public void launchWinCalc()
    {
        Process process = null;
        try
        {
            process = new ProcessBuilder(
                    "C:\\Windows\\System32\\calc.exe").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        calcThread.interrupt();

        try
        {
            calcThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        calcThread = new Thread()
        {
            public void run()
            {
                launchWinCalc();
            }
        };
    }

    public void launchPutty()
    {
        Process process = null;
        try
        {
            process = new ProcessBuilder(
                    "C:\\Program Files (x86)\\PuTTY\\putty.exe").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        puttyThread.interrupt();

        try
        {
            puttyThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        puttyThread = new Thread()
        {
            public void run()
            {
                launchPutty();
            }
        };
    }

    public void launchWinscp()
    {
        Process process = null;
        try
        {
            process = new ProcessBuilder(
                    "C:\\Program Files (x86)\\WinSCP\\WinSCP.exe").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        winscpThread.interrupt();

        try
        {
            winscpThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        winscpThread = new Thread()
        {
            public void run()
            {
                launchWinscp();
            }
        };
    }

    public void launchRemoteDesktop()
    {
        Process process = null;
        try
        {
            process = new ProcessBuilder(
                    "C:\\Windows\\System32\\mstsc.exe").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        remoteThread.interrupt();

        try
        {
            remoteThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        remoteThread = new Thread()
        {
            public void run()
            {
                launchRemoteDesktop();
            }
        };
    }

    public void launchOutlook()
    {
        Process process = null;
        try
        {
            process = new ProcessBuilder(
                    "C:\\Program Files (x86)\\Microsoft Office\\Office12\\OUTLOOK.EXE").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        outlookThread.interrupt();

        try
        {
            outlookThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        outlookThread = new Thread()
        {
            public void run()
            {
                launchOutlook();
            }
        };
    }

    public void launchNotepad()
    {
        Process process = null;
        try
        {
            process = new ProcessBuilder(
                    "C:\\Program Files (x86)\\Notepad++\\notepad++.exe").start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try
        {
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        notepadThread.interrupt();

        try
        {
            notepadThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        notepadThread = new Thread()
        {
            public void run()
            {
                launchNotepad();
            }
        };
    }

    public void launchOptions()
    {
        OptionsButton options = new OptionsButton();
        options.setTitle("Options");
        options.setSize(700, 700);
        options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        options.setVisible(true);

        options.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                updateGUI();

                try
                {
                    saveOptions();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        optionsThread.interrupt();

        try
        {
            optionsThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        optionsThread = new Thread()
        {
            public void run()
            {
                launchOptions();
            }
        };
    }

    public void saveOptions() throws FileNotFoundException
    {
        File fileDirectory = new File(directory);

        if (fileDirectory.exists() == false)
        {
            fileDirectory.mkdir();
        }

        String content = color1.getRed() + " " + color1.getGreen() + " " + color1.getBlue() + " " + color2.getRed() + " " + color2.getGreen() + " " + color2.getBlue() + " " + windowSize;
        PrintWriter out = new PrintWriter(directory + pathToFile);
        out.println(content);
        out.close();
    }

    public void loadOptions() throws IOException
    {
        Scanner scanner = new Scanner(new File(directory + pathToFile));
        int[] values = new int[7];
        int i = 0;

        while (scanner.hasNextInt())
        {
            values[i++] = scanner.nextInt();
        }

        color1 = new Color(values[0], values[1], values[2]);
        color2 = new Color(values[3], values[4], values[5]);
        windowSize = values[6];
    }
}