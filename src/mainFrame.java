/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Main window where the application actually runs with the sequencer
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class mainFrame extends JFrame {
    /**
     * Constructs main window
     */
    public mainFrame() {
        setTitle("AK-STUDIOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);

        // track list array
        ArrayList<trackRow> tracksList = new ArrayList<>();
        tracksList.add(new trackRow("Kick",  new Color(245, 230, 255), "sounds/kick.wav"));
        tracksList.add(new trackRow("Snare", new Color(235, 215, 255), "sounds/snare.wav"));
        tracksList.add(new trackRow("HiHat", new Color(225, 200, 255), "sounds/hihat.wav"));
        tracksList.add(new trackRow("Clap",  new Color(215, 185, 255), "sounds/clap.wav"));
        tracksList.add(new pianoTrackRow());

        // create sequencer panel
        stepSequencerPanel sequencer = new stepSequencerPanel(tracksList);
        
        setLayout(new BorderLayout());
        // add transport panel
        add(new transportPanel(sequencer, this), BorderLayout.NORTH);
        // add sequencer panel
        add(sequencer, BorderLayout.CENTER);

        setVisible(true);
    }
}