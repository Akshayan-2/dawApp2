/**
 * Akshayan Mathan
 * Ms. Xie 
 * 2026 06 08
 * ICS 3U7
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; 

public class stepSequencerPanel extends JPanel {
    private ArrayList<trackRow> tracks;
    private int step = 0;

    public stepSequencerPanel(ArrayList<trackRow> tracks) {
        this.tracks = tracks;
        setLayout(new GridLayout(tracks.size(), 1));
        for (trackRow t : tracks) {
            add(t);
        }
    }

    public void advance() {
        for (trackRow t : tracks) { 
            t.triggerStep(step);
            t.setHighlight(step);
        }
        step = (step + 1) % 16;
    }
}