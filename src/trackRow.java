/**
 * Akshayan Mathan
 * Ms. Xie
 * 2026-06-12
 * ICS 3U7
 * Template for a single track row
 */
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class trackRow extends JPanel implements ActionListener {
    String path;
    Color baseColor;
    JToggleButton[] btns = new JToggleButton[16];
    JToggleButton muteBtn, soloBtn;
    JSlider volSlider, panSlider;
    static boolean anySolo = false;

    /**
     * Constructs a track row
     * @param name given track name
     * @param color base track color
     * @param path audio file path
     */
    public trackRow(String name, Color color, String path) {
        this.baseColor = color;
        this.path = path;
        // set border layout
        setLayout(new BorderLayout());
        
        // setup left panel
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.setBackground(color);
        panel.add(new JLabel(name, SwingConstants.CENTER));
        
        volSlider = new JSlider(0, 100, 80);
        volSlider.setBackground(color);
        panel.add(volSlider);
        
        panSlider = new JSlider(-50, 50, 0);
        panSlider.setBackground(color);
        panel.add(panSlider);

        // setup mute solo
        JPanel muteSoloPanel = new JPanel(new GridLayout(1, 2));
        muteBtn = new JToggleButton("Mute");
        soloBtn = new JToggleButton("Solo");
        muteBtn.addActionListener(this);
        soloBtn.addActionListener(this);
        muteSoloPanel.add(muteBtn);
        muteSoloPanel.add(soloBtn);
        panel.add(muteSoloPanel);

        // clear row button
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(event -> clearTrack());
        panel.add(clearBtn);
        // add west panel
        add(panel, BorderLayout.WEST);

        // grid step buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 16));
        for (int index = 0; index < 16; index++) {
            btns[index] = new JToggleButton();
            btns[index].setBackground(color); 
            btns[index].setOpaque(true); // force background paint
            btns[index].addActionListener(this);
            buttonPanel.add(btns[index]);
        }
        // add center panel
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Handles sequence button clicks
     * @param event action click event
     */
    public void actionPerformed(ActionEvent event) {
        // check solo state
        anySolo = soloBtn.isSelected();
        for (int index = 0; index < 16; index++) {
            if (event.getSource() == btns[index]) {
                // toggle violet color
                btns[index].setBackground(btns[index].isSelected() ? new Color(138, 43, 226) : baseColor);
            }
        }
    }

    /**
     * Clears pattern from track
     */
    public void clearTrack() {
        for (int index = 0; index < 16; index++) {
            // uncheck the step
            btns[index].setSelected(false);
            // reset base color
            btns[index].setBackground(baseColor);
        }
    }

    /**
     * Plays local audio file.
     * @param soundPath target file location
     */
    /**
     * plays sound fast
     * @param soundPath audio file path
     */
    public void playSound(String soundPath) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(soundPath)));
                clip.start();
            } catch (Exception e) {}
        }).start();
    }

    /**
     * Triggers active steps during playback
     * @param step current playing step
     */
    public void triggerStep(int step) {
        if ((soloBtn.isSelected() || !anySolo) && !muteBtn.isSelected() && btns[step].isSelected()) {
            // play step sound
            playSound(path);
        }
    }

    /**
     * Highlights the current column
     * @param column current playing step
     */
    public void setHighlight(int column) {
        for (int index = 0; index < 16; index++) {
            // border red highlight
            btns[index].setBorder(BorderFactory.createLineBorder(index == column ? Color.RED : Color.GRAY));
        }
    }
}