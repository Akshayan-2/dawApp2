/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Track row specifically for instruments with varying notes
 */
import javax.swing.*;
import java.awt.*;

public class pianoTrackRow extends trackRow {
    String[] noteNames = {"C5","B4","A#4","A4","G#4","G4","F#4","F4","E4","D#4","D4","C#4","C4"};
    boolean[][] grid = new boolean[noteNames.length][16];
    JComboBox<String> instBox;

    /**
     * Constructs track row
     */
    public pianoTrackRow() {
        super("Piano", new Color(205, 170, 255), "sounds/piano");
        removeAll(); // clear old layout
        setLayout(new BorderLayout());

        // create left panel
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.setBackground(baseColor);
        instBox = new JComboBox<>(new String[]{"Piano","Guitar","Saxophone","Synth"});
        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(event -> openPianoRoll());
        
        // add controls
        panel.add(instBox); 
        panel.add(editBtn); 
        panel.add(volSlider); 
        panel.add(panSlider);
        
        // mute and solo panels
        JPanel muteSoloPanel = new JPanel(new GridLayout(1, 2));
        muteSoloPanel.add(muteBtn); 
        muteSoloPanel.add(soloBtn); 
        panel.add(muteSoloPanel);
        
        // add to west
        add(panel, BorderLayout.WEST);

        // right buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 16));
        for (int index = 0; index < 16; index++) {
            btns[index].setEnabled(false); 
            // add line border
            btns[index].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            buttonPanel.add(btns[index]);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Opens the piano editor
     */
    private void openPianoRoll() {
        // create roll 
        pianoRollDialog dialog = new pianoRollDialog(noteNames, grid, baseColor);
        dialog.setVisible(true);
        for (int step = 0; step < 16; step++) {
            boolean isOn = false;
            for (int note = 0; note < noteNames.length; note++) {
                if (grid[note][step]) {
                    isOn = true;
                }
            }
            btns[step].setSelected(isOn);        
            btns[step].setBackground(isOn ? new Color(138, 43, 226) : baseColor); // update color
        }
    }

    /**
     * Triggers sound steps
     * @param step current playing step
     */
    public void triggerStep(int step) {
        if (muteBtn.isSelected() || (anySolo && !soloBtn.isSelected())) return;
        // get instrument
        String instrument = instBox.getSelectedItem().toString().toLowerCase();
        for (int note = 0; note < noteNames.length; note++) {
            if (grid[note][step]) {
                // play exact file
                playSound("sounds/" + instrument + "_" + noteNames[note] + ".wav");
            }
        }
    }

    /**
     * Clears the piano roll
     */
    @Override
    public void clearTrack() {
        super.clearTrack(); // clear main buttons
        // clear 2d grid
        for (int note = 0; note < noteNames.length; note++) {
            for (int step = 0; step < 16; step++) {
                grid[note][step] = false;
            }
        }
    }
}