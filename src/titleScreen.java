/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Title screen, where the application starts
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class titleScreen extends JFrame implements ActionListener {
    private JButton btnStart, btnHelp, btnSettings, btnExit;
    private static int masterVolume = 80;
    
    /**
     * Constructs title screen window
     */
    public titleScreen() {
        setTitle("DAW - AK Studios Sequencer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        // background image pixel art
        JPanel bgPanel = new JPanel(new BorderLayout()) {
            private Image background = new ImageIcon("images/ak_studios.png").getImage();
            
            /**
             * Paints the background image
             * @param graphics target graphics context
             */
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setOpaque(true);
        setContentPane(bgPanel);

        // title buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        btnStart    = new JButton("Start");
        btnHelp     = new JButton("Help");
        btnSettings = new JButton("Settings");
        btnExit     = new JButton("Exit");

        // button styling
        styleButton(btnStart);
        styleButton(btnHelp);
        styleButton(btnSettings);
        styleButton(btnExit);

        // action listeners
        btnStart.addActionListener(this);
        btnHelp.addActionListener(this);
        btnSettings.addActionListener(this);
        btnExit.addActionListener(this);

        // add to panel
        buttonPanel.add(btnStart);
        buttonPanel.add(btnHelp);
        buttonPanel.add(btnSettings);
        buttonPanel.add(btnExit);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.add(buttonPanel);
        bgPanel.add(wrapper, BorderLayout.CENTER);
    }

    /**
     * Styles the buttons
     * @param button target styling button
     */
    private void styleButton(JButton button) {
        // set base style
        button.setBackground(new Color(165, 130, 220, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    /**
     * @param event action click event
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnStart) {
            // start main daw screen
            this.dispose();
            new mainFrame().setVisible(true);
        } else if (event.getSource() == btnHelp) {
            // display the instructions dialog
            showHelpDialog();
        } else if (event.getSource() == btnSettings) {
            // display settings option for volume
            showSettingsDialog();
        } else if (event.getSource() == btnExit) {
            // close program
            System.exit(0);
        }
    }

    /**
     * help menu
     */
    private void showHelpDialog() {
        // popup help text
        JOptionPane.showMessageDialog(this, "Instructions for use...", "Help", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Shows the settings menu
     */
    private void showSettingsDialog() {
        // slider for volume
        JSlider volumeSlider = new JSlider(0, 100, masterVolume);
        if (JOptionPane.showConfirmDialog(this, volumeSlider, "Master Volume", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            // saving master volume
            masterVolume = volumeSlider.getValue();
        }
    }

    /**
     * Gets total master volume
     * @return current integer volume
     */
    public static int getMasterVolume() { 
        return masterVolume; 
    }
}