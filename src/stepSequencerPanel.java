/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Panel that manages and sequences all tracks in use
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class stepSequencerPanel extends JPanel {
    ArrayList<trackRow> tracks;
    int step = 0;

    /**
     * Constructs step sequencer panel
     * @param tracks track list array
     */
    public stepSequencerPanel(ArrayList<trackRow> tracks) {
        this.tracks = tracks;
        // grid layout tracks
        setLayout(new GridLayout(tracks.size(), 1));
        for (trackRow track : tracks) {
            add(track);
        }
    }

    /**
     * Advances to next step.
     */
    public void advance() {
        for (trackRow track : tracks) { 
            // trigger track step
            track.triggerStep(step);
            // highlight playing step
            track.setHighlight(step);
        }
        // loop step counter makes sure step doesnt go over 16
        step = (step + 1) % 16;
    }

    /**
     * Clears all track patterns
     */
    public void clearAll() {
        for (trackRow track : tracks) {
            // clears all tracks
            track.clearTrack();
        }
    }
}