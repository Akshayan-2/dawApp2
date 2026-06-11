/**
 * Akshayan Mathan
 * Ms. Xie 
 * 2026 06 08
 * ICS 3U7
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class titleScreen extends JFrame implements ActionListener {
    private JButton btnStart, btnHelp, btnSettings, btnExit;
    private static int masterVolume = 80;

    public titleScreen() {
        setTitle("DAW - Ableton Style Sequencer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 0, 128));

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My DAW Sequencer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBackground(new Color(0, 0, 128));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        btnStart = new JButton("Start");
        btnHelp = new JButton("Help");
        btnSettings = new JButton("Settings");
        btnExit = new JButton("Exit");

        styleButton(btnStart);
        styleButton(btnHelp);
        styleButton(btnSettings);
        styleButton(btnExit);
        
        btnStart.addActionListener(this);
        btnHelp.addActionListener(this);
        btnSettings.addActionListener(this);
        btnExit.addActionListener(this);

        buttonPanel.add(btnStart);
        buttonPanel.add(btnHelp);
        buttonPanel.add(btnSettings);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(70, 130, 200));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setFocusPainted(false);
    }

    //button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
            this.dispose();
            mainFrame mainApp = new mainFrame();
            mainApp.setVisible(true);
        } 
        else if (e.getSource() == btnHelp) {
            showHelpDialog();
        } 
        else if (e.getSource() == btnSettings) {
            showSettingsDialog();
        } 
        else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    private void showHelpDialog() {
        String helpText = "<html><body><h2>How to use:</h2><ul>" +
                "<li>Click step buttons to create a rhythm pattern.</li>" +
                "<li>Press Play to hear your sequence.</li>" +
                "<li>Adjust BPM to change tempo.</li>" +
                "<li>Use Mute/Solo per track.</li>" +
                "</ul></body></html>";
        JOptionPane.showMessageDialog(this, helpText, "Help - Instructions", JOptionPane.PLAIN_MESSAGE);
    }

    private void showSettingsDialog() {
        JSlider volumeSlider = new JSlider(0, 100, masterVolume);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        
        int result = JOptionPane.showConfirmDialog(this, volumeSlider, "Master Volume", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            masterVolume = volumeSlider.getValue();
        }
    }

    public static int getMasterVolume() { 
        return masterVolume; 
    }
}