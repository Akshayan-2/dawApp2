/**
 * Akshayan Mathan
 * Ms. Xie 
 * 2026 06 08
 * ICS 3U7
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class mainFrame extends JFrame {
    private stepSequencerPanel seq;
    private transportPanel trans;
    private ArrayList<trackRow> tracksList;

    public mainFrame() {
        setTitle("DAW - Step Sequencer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 30));

        tracksList = new ArrayList<>();
        tracksList.add(new trackRow("Kick",  new Color(200, 80, 80),  "sounds/kick.wav"));
        tracksList.add(new trackRow("Snare", new Color(80, 200, 80),  "sounds/snare.wav"));
        tracksList.add(new trackRow("HiHat", new Color(80, 80, 200),  "sounds/hihat.wav"));
        tracksList.add(new trackRow("Clap",  new Color(200, 160, 80), "sounds/clap.wav"));

        seq = new stepSequencerPanel(tracksList);
        trans = new transportPanel(seq);

        setLayout(new BorderLayout());
        add(trans, BorderLayout.NORTH);
        add(seq, BorderLayout.CENTER);
        
        setVisible(true);
    }
}