import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class ProgramHub extends JFrame implements ActionListener
{
    JPanel linkPanel;
    JPanel header;
    JButton calcButton, salesforceButton, etimeButton, wikiCentralButton, opsmartButton, jiraButton, btbbButton, bbhelpButton, testLabButton, bandwidthButton, headerButton;
    JButton windowsCalcButton, puttyButton, winscpButton,remoteDesktopButton, outlookButton,notepadButton;
    GridLayout standard = new GridLayout(0,3,10,10);
    BorderLayout pageLayout = new BorderLayout();
    GridLayout headerLayout = new GridLayout(0,1);
    String headerPath = "https://github.com/CryoByte33/WorkSmarter";
    String salesforcePath = "https://blackboard.my.salesforce.com/console";
    String etimePath = "http://rocjfsweb06/eet/applications/wtk/html/ess/logon.jsp";
    String wikiCentralPath = "http://wikicentral.bbbb.net/login.action?logout=true";
    String opsmartPath = "https://opsmart.blackboard.com/tracksmart/login.php";
    String jiraPath = "https://carbon.pd.local:8443/secure/Dashboard.jspa";
    String btbbPath = "https://blackboard.secure.force.com/login";
    String bbhelpPath = "http://help.blackboard.com/";
    String testLabPath = "https://silicon.pd.local:8443/display/CSI/Test+Lab";
    String bandwidthPath = "http://10.8.224.35/";

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

    public ProgramHub()
    {
        linkPanel = new JPanel(standard);
        header = new JPanel(headerLayout);

        BufferedImage buttonIcon = null;
        try
        {
            buttonIcon = ImageIO.read(getClass().getResource("/images/worksmarter.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        headerButton = new JButton(new ImageIcon(buttonIcon));
        headerButton.addActionListener(this);
        header.add(headerButton);

        calcButton = new JButton("Work Calculator");
        calcButton.addActionListener(this);

        salesforceButton = new JButton("SalesForce");
        salesforceButton.addActionListener(this);

        etimeButton = new JButton("eTime");
        etimeButton.addActionListener(this);

        wikiCentralButton = new JButton("WikiCentral");
        wikiCentralButton.addActionListener(this);

        opsmartButton = new JButton("OpSmart");
        opsmartButton.addActionListener(this);

        jiraButton = new JButton("JIRA");
        jiraButton.addActionListener(this);

        btbbButton = new JButton("BTBB");
        btbbButton.addActionListener(this);

        bbhelpButton = new JButton("Bb Help");
        bbhelpButton.addActionListener(this);

        testLabButton = new JButton("Test Lab");
        testLabButton.addActionListener(this);

        bandwidthButton = new JButton("Bandwidth");
        bandwidthButton.addActionListener(this);

        windowsCalcButton = new JButton("Windows Calculator");
        windowsCalcButton.addActionListener(this);

        puttyButton = new JButton("PuTTY");
        puttyButton.addActionListener(this);

        winscpButton = new JButton("WinSCP");
        winscpButton.addActionListener(this);

        remoteDesktopButton = new JButton("Remote Desktop");
        remoteDesktopButton.addActionListener(this);

        outlookButton = new JButton("Outlook");
        outlookButton.addActionListener(this);

        notepadButton = new JButton("Notepad++");
        notepadButton.addActionListener(this);

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

        this.setLayout(pageLayout);
        this.add(header, BorderLayout.PAGE_START);
        this.add(linkPanel,BorderLayout.CENTER);
        this.add(calcButton, BorderLayout.PAGE_END);
    }

    public static void main (String[] args)
    {
        ProgramHub box = new ProgramHub();
        box.setTitle("WorkSmarter");
        box.setSize(500, 400);
        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        box.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
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
            WorkCalcGUI workCalc = new WorkCalcGUI();
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
    }

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
}
