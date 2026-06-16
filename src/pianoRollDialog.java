/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Dialog window for editing piano roll
 */
import javax.swing.*;
import java.awt.*;

public class pianoRollDialog extends JDialog {
    /**
     * Constructs piano roll dialog
     * @param noteNames string note array
     * @param grid 
     * @param background base background color
     */
    public pianoRollDialog(String[] noteNames, boolean[][] grid, Color background) {
        setTitle("Piano Roll");
        setModal(true);
        setSize(900, 500);
        setLocationRelativeTo(null);
        // keep dialog on top to prevent freezing
        setAlwaysOnTop(true);
        
        // create main panel
        JPanel panel = new JPanel(new GridLayout(noteNames.length, 17));
        for (int note = 0; note < noteNames.length; note++) {
            panel.add(new JLabel(noteNames[note], SwingConstants.CENTER));
            for (int step = 0; step < 16; step++) {
                // create toggle button
                JToggleButton button = new JToggleButton();
                button.setOpaque(true);
                // force border to override native os styling
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                button.setSelected(grid[note][step]);
                // set color
                button.setBackground(grid[note][step] ? new Color(138, 43, 226) : background);
                final int FINALNOTE = note;
                final int FINALSTEP = step;
                button.addActionListener(event -> {
                    grid[FINALNOTE][FINALSTEP] = button.isSelected();
                    // update color
                    button.setBackground(button.isSelected() ? new Color(138, 43, 226) : background);
                });
                panel.add(button);
            }
        }    
        // add scroll pane
        add(new JScrollPane(panel), BorderLayout.CENTER);
        // close button
        JButton close = new JButton("Close");
        close.addActionListener(event -> dispose());
        // add close button
        add(close, BorderLayout.SOUTH);
    }
}