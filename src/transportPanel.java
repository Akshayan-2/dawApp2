/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Top control panel for buttons, back, play, stop, clear all and bpm
 */
import javax.swing.*;
import java.awt.*;

public class transportPanel extends JPanel {
    Timer timer;

    /**
     * Constructs transport control panel
     * @param sequencer main sequence manager
     * @param frame main window
     */
    public transportPanel(stepSequencerPanel sequencer, mainFrame frame) {
        setBackground(new Color(245, 230, 255)); 
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // back button
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(event -> {
            // close frame
            frame.dispose();
            // open main title
            new titleScreen().setVisible(true);
        });

        JButton playBtn = new JButton("Play");
        JButton stopBtn = new JButton("Stop");
        JSpinner bpmSpin = new JSpinner(new SpinnerNumberModel(120, 60, 200, 1));
        JButton clearBtn = new JButton("Clear All");

        playBtn.addActionListener(event -> {
            if (timer != null) {
                // stop timer
                timer.stop();
            }
            int bpm = (int) bpmSpin.getValue();
            // loop step advancement
            timer = new Timer(60000 / (bpm * 4), actionEvent -> sequencer.advance());
            // start timer
            timer.start();
        });

        stopBtn.addActionListener(event -> { 
            if (timer != null) {
                // stop playback loop
                timer.stop(); 
            }
        });
        clearBtn.addActionListener(event -> sequencer.clearAll());

        // add top buttons
        add(backBtn);
        add(playBtn);
        add(stopBtn);
        add(new JLabel("BPM:"));
        add(bpmSpin);
        add(clearBtn);
    }
}