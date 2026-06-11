/**
 * Akshayan Mathan
 * Ms. Xie 
 * 2026 06 08
 * ICS 3U7
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class transportPanel extends JPanel implements ActionListener {
    private stepSequencerPanel seq;
    private JButton playBtn, stopBtn;
    private JSpinner bpmSpin;
    private Timer timer;

    public transportPanel(stepSequencerPanel seq) {
        this.seq = seq;
        playBtn = new JButton("Play");
        stopBtn = new JButton("Stop");
        bpmSpin = new JSpinner(new SpinnerNumberModel(120, 60, 200, 1));

        playBtn.addActionListener(this);
        stopBtn.addActionListener(this);

        add(playBtn); add(stopBtn); add(new JLabel("BPM:")); add(bpmSpin);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            if (timer != null) timer.stop();
            int bpm = (Integer) bpmSpin.getValue();
            timer = new Timer(60000 / (bpm * 4), ae -> seq.advance());
            timer.start();
        } else if (e.getSource() == stopBtn) {
            if (timer != null) timer.stop();
        }
    }
}