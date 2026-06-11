/**
 * Akshayan Mathan
 * Ms. Xie 
 * 2026 06 08
 * ICS 3U7
 */
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class trackRow extends JPanel implements ActionListener {
    private String name;
    private Color color;
    private String path;
    private JToggleButton[] btns;
    private boolean[] states;
    private JToggleButton muteBtn, soloBtn;
    private boolean isMute = false, isSolo = false;
    
    private static boolean anySolo = false;

    public trackRow(String name, Color color, String path) {
        this.name = name;
        this.color = color;
        this.path = path;
        btns = new JToggleButton[16];
        states = new boolean[16];

        setLayout(new BorderLayout());
        
        JPanel p = new JPanel(new GridLayout(5, 1));
        muteBtn = new JToggleButton("Mute");
        soloBtn = new JToggleButton("Solo");
        muteBtn.addActionListener(this);
        soloBtn.addActionListener(this);

        p.add(new JLabel(name, SwingConstants.CENTER));
        p.add(new JSlider()); p.add(new JSlider());
        p.add(muteBtn); p.add(soloBtn);

        JPanel bPanel = new JPanel(new GridLayout(1, 16));
        for (int i = 0; i < 16; i++) {
            btns[i] = new JToggleButton();
            btns[i].addActionListener(this);
            bPanel.add(btns[i]);
        }
        add(p, BorderLayout.WEST); add(bPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == muteBtn) {
        	isMute = muteBtn.isSelected();
        }
        else if (e.getSource() == soloBtn) {
        	isSolo = soloBtn.isSelected(); anySolo = isSolo; }
        else {
            for (int i = 0; i < 16; i++) {
                if (e.getSource() == btns[i]) {
                    states[i] = btns[i].isSelected();
                    btns[i].setBackground(states[i] ? color : Color.WHITE);
                }
            }
        }
    }

    public void triggerStep(int step) {
        if ((isSolo||!anySolo) && !isMute && states[step]) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(path)));
                clip.start();
            } catch (Exception ex) { Toolkit.getDefaultToolkit().beep(); }
        }
    }

    public void setHighlight(int col) {
        for (int i = 0; i < 16; i++) 
            btns[i].setBorder(BorderFactory.createLineBorder(i == col ? Color.RED : Color.GRAY));
    }
}