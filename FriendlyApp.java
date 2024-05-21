import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FriendlyApp {
    public static void main(String[] args) {
        new FriendlyFrame();
    }
}

class FriendlyFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public FriendlyFrame() {
        setTitle("Friendly Social Cues");
        setSize(528, 372);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set up card layout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add custom panels to the card panel
        LoadingPanel loadingPanel = new LoadingPanel();
        FriendlyGuidePanel1 guidePanel1 = new FriendlyGuidePanel1(this);
        FriendlyGuidePanel2 guidePanel2 = new FriendlyGuidePanel2(this);

        cardPanel.add(loadingPanel, "loading");
        cardPanel.add(guidePanel1, "guide1");
        cardPanel.add(guidePanel2, "guide2");

        add(cardPanel);
        setVisible(true);

        // Timer to switch from loading panel to guide panel 1
        cardLayout.show(cardPanel, "loading");
        try{Thread.sleep(2500);}catch(Exception e){}
        cardLayout.show(cardPanel, "guide1");

    }

    // Method to switch to the next panel
    public void showNextPanel(String nextPanelName) {
        cardLayout.show(cardPanel, nextPanelName);
    }
}

class LoadingPanel extends JPanel {

    private Image friendlyImage;
    private int loadingProgress = 0;
    private Timer timer;

    public LoadingPanel() {
        try {
            // Load the image
            friendlyImage = new ImageIcon(getClass().getResource("friendly.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start the animation timer
        timer = new Timer(10, e -> updateProgressBar());
        timer.start();
    }

    private void updateProgressBar() {
        loadingProgress += 1;
        if (loadingProgress >= 115) {
            timer.stop();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        FontMetrics fmboldFont, fmmediumFont;
        fmboldFont = g.getFontMetrics(Variables.boldFont);
        fmmediumFont = g.getFontMetrics(Variables.mediumFont);

        // Background color
        g.setColor(Variables.lightBlue); // light blue color
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the white rounded rectangle
        int rectWidth = 340;
        int rectHeight = 210;
        int rectX = (getWidth() - rectWidth) / 2;
        int rectY = (getHeight() - rectHeight) / 2;
        g.setColor(Color.WHITE);
        g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, 50, 50);

        // Center the text horizontally
        String title = "Friendly";
        String subtitle = "Recognizing Social Cues";

        int titleX = rectX + (rectWidth - fmboldFont.stringWidth(title)) / 2;
        int subtitleX = rectX + (rectWidth - fmmediumFont.stringWidth(subtitle)) / 2;

        // Draw the text
        g.setColor(Variables.darkBlue);
        g.setFont(Variables.boldFont);
        g.drawString(title, titleX, rectY + 90);

        g.setFont(Variables.mediumFont);
        g.drawString(subtitle, subtitleX, rectY + 130);

        // Draw the progress bar fill
        int barWidth = 230;
        int barHeight = 15;
        int barX = rectX + (rectWidth - barWidth) / 2;
        int barY = rectY + 155;
        g.setColor(new Color(7, 55, 108));
        g.fillRoundRect(barX, barY, loadingProgress * 2, barHeight, 20, 20);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(barX, barY, barWidth, barHeight, 20, 20);

        // Draw the friendly image
        if (friendlyImage != null) {
            int imgWidth = 115;
            int imgHeight = 100;
            int imgX = rectX + rectWidth - imgWidth + 60;
            int imgY = rectY - imgHeight / 2 + 10;
            g.drawImage(friendlyImage, imgX, imgY, imgWidth, imgHeight, this);
        }
    }
}

class FriendlyGuidePanel1 extends JPanel implements ActionListener {

    private Image friendlyImage;
    private JButton nextButton;
    private FriendlyFrame parentFrame;

    public FriendlyGuidePanel1(FriendlyFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);

        try {
            // Load the image
            friendlyImage = new ImageIcon(getClass().getResource("friendly.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the "Next" button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);
        nextButton.setBounds(400, 270, 80, 30); // Position it at the bottom right corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch to the next panel
        parentFrame.showNextPanel("guide2");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background color
        g.setColor(new Color(0, 193, 255)); // Light blue color
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the white rounded rectangle
        int rectWidth = 460;
        int rectHeight = 290;
        int rectX = (getWidth() - rectWidth) / 2;
        int rectY = (getHeight() - rectHeight) / 2;
        g.setColor(Color.WHITE);
        g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, 30, 30);

        // Text settings
        g.setColor(new Color(7, 55, 108)); // Dark blue color
        g.setFont(Variables.mediumFont);
        int lineX = rectX + 20;
        int lineY = rectY + 40;

        g.drawString("Hi, I'm Friendly!", lineX, lineY);
        g.drawString("I'll guide you through this game.", lineX, lineY + 30);
        g.drawString("I love helping people understand", lineX, lineY + 90);
        g.drawString("social cues. It brings me joy!", lineX, lineY + 120);
        g.drawString("To navigate through this app, use your", lineX, lineY + 180);
        g.drawString("mouse to click on different buttons.", lineX, lineY + 210);

        // Draw the friendly image
        if (friendlyImage != null) {
            int imgWidth = 94;
            int imgHeight = 80;
            int imgX = rectX + rectWidth - imgWidth - 20;
            int imgY = rectY + 20;
            g.drawImage(friendlyImage, imgX, imgY, imgWidth, imgHeight, this);
        }
    }
}

class FriendlyGuidePanel2 extends JPanel implements ActionListener {

    private Image friendlyImage;
    private JButton nextButton;
    private FriendlyFrame parentFrame;

    public FriendlyGuidePanel2(FriendlyFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);

        try {
            // Load the image
            friendlyImage = new ImageIcon(getClass().getResource("friendly.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the "Next" button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);
        nextButton.setBounds(400, 270, 80, 30); // Position it at the bottom right corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch to the next panel or perform other actions
        //parentFrame.showNextPanel("nextPanel");
        System.out.println("Next panel action");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background color
        g.setColor(new Color(0, 193, 255)); // Light blue color
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw the white rounded rectangle
        int rectWidth = 460;
        int rectHeight = 290;
        int rectX = (getWidth() - rectWidth) / 2;
        int rectY = (getHeight() - rectHeight) / 2;
        g.setColor(Color.WHITE);
        g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, 30, 30);

        // Text settings
        g.setColor(new Color(7, 55, 108)); // Dark blue color
        g.setFont(Variables.mediumFont);
        int lineX = rectX + 20;
        int lineY = rectY + 40;

        g.drawString("In this app, there are three", lineX, lineY);
        g.drawString("levels: a learning stage, a", lineX, lineY + 30);
        g.drawString("knowledge testing stage, and", lineX, lineY + 60);
        g.drawString("a game stage.", lineX, lineY + 90);
        g.drawString("Go through each stage one at a time", lineX, lineY + 150);
        g.drawString("to learn how to recognize social cues!", lineX, lineY + 180);
        g.drawString("Have fun!", lineX, lineY + 210);

        // Draw the friendly image
        if (friendlyImage != null) {
            int imgWidth = 94;
            int imgHeight = 80;
            int imgX = rectX + rectWidth - imgWidth - 20;
            int imgY = rectY + 20;
            g.drawImage(friendlyImage, imgX, imgY, imgWidth, imgHeight, this);
        }
    }
}
