import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Rafael Borges
 */

public class App implements ActionListener {

    private AudioStream as;
    private JButton play = new JButton("Play");
    private JButton stop = new JButton("Stop");
    private JButton pause = new JButton("Pause");

    public static void main(String[] args) {
        new App().createGui();

    }

    public void createGui(){

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        JFrame frame = new JFrame("Music Player Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,5,3,3));
        frame.setPreferredSize(new Dimension(300, 100));
        frame.setLocation(screenWidth / 3, screenHeight / 3);
        frame.add(play);
        frame.add(stop);
        frame.add(pause);
        frame.pack();
        frame.setVisible(true);


        play.addActionListener(this);
        play.setBackground(Color.BLUE);
        play.setForeground(Color.WHITE);

        stop.addActionListener(this);
        stop.setBackground(Color.RED);
        stop.setForeground(Color.WHITE);

        pause.addActionListener(this);
        pause.setBackground(Color.CYAN);

        try {
            as = new AudioStream(this.getClass().getResourceAsStream("/bttn.wav"));
        } catch (IOException e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == play){
            AudioPlayer.player.start(as);
        }else
            if (actionEvent.getSource() == stop){
                AudioPlayer.player.stop(as);
                try {
                    as = new AudioStream(this.getClass().getResourceAsStream("/bttn.wav"));
                } catch (IOException e) {
                    System.out.println("Erro: "+e.getMessage());
                }
            }
         else
             AudioPlayer.player.stop(as);

    }
}
