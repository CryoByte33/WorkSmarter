import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ProgramHub extends JFrame implements ActionListener
{
    JPanel mainPanel;
    JButton calcButton, salesforceButton, etimeButton, wikiCentralButton, opsmartButton, jiraButton, btbbButton, bbhelpButton, testLabButton, bandwidthButton;
    GridLayout standard = new GridLayout(0,2);
    String salesforcePath = "https://blackboard.my.salesforce.com/console";
    String etimePath = "http://rocjfsweb06/eet/applications/wtk/html/ess/logon.jsp";
    String wikiCentralPath = "http://wikicentral.bbbb.net/login.action?logout=true";
    String opsmartPath = "https://opsmart.blackboard.com/tracksmart/login.php";
    String jiraPath = "https://carbon.pd.local:8443/secure/Dashboard.jspa";
    String btbbPath = "https://blackboard.secure.force.com/login";
    String bbhelpPath = "http://help.blackboard.com/";
    String testLabPath = "https://silicon.pd.local:8443/display/CSI/Test+Lab";
    String bandwidthPath = "http://10.8.224.35/";

    public ProgramHub()
    {
        mainPanel = new JPanel(standard);

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

        mainPanel.add(calcButton);
        mainPanel.add(salesforceButton);
        mainPanel.add(etimeButton);
        mainPanel.add(wikiCentralButton);
        mainPanel.add(opsmartButton);
        mainPanel.add(jiraButton);
        mainPanel.add(btbbButton);
        mainPanel.add(bbhelpButton);
        mainPanel.add(testLabButton);
        mainPanel.add(bandwidthButton);
        this.add(mainPanel);
    }

    public static void main (String[] args)
    {
        ProgramHub box = new ProgramHub();
        box.setTitle("WorkSmarter");
        box.setSize(500, 250);
        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        box.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
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
    }
}
