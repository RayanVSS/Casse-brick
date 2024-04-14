package utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private Clip clip;
    private static double volumeS=GameConstants.SOUND/100;
    private static double volumeM=GameConstants.MUSIC/100;

    public Sound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0); // Reset the sound to the beginning
        clip.start(); // Play the sound
    }

    public void stop() {
        clip.stop();
    }

    public void updateS(){
        volumeS = GameConstants.SOUND / 100.0;
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) (Math.log(volumeS) / Math.log(10.0) * 20.0));
        System.out.println("volumeS = " + volumeS);
        System.out.println("GameConstants.SOUND = " + GameConstants.SOUND);
    }

    public void updateM() {
        volumeM = GameConstants.MUSIC / 100.0;
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) (Math.log(volumeM) / Math.log(10.0) * 20.0));
        System.out.println("volumeM = " + volumeM);
        System.out.println("GameConstants.MUSIC = " + GameConstants.MUSIC);
    }

    public Clip getClip() {
        return clip;
    }

    public static class ClickSound extends Sound {
        public ClickSound() {
            super("src/main/ressources/sound/clickv2.wav");
        }

        @Override   
        public void updateS() {
            stop();
            volumeS = GameConstants.SOUND / 100.0;
            FloatControl volumeControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue((float) (Math.log(volumeS) / Math.log(10.0) * 20.0));
            System.out.println("volumeS = " + volumeS);
            System.out.println("GameConstants.SOUND = " + GameConstants.SOUND);
            play();
        }
    }

    public static class GameOverSound extends Sound {
        public GameOverSound() {
            super("src/main/ressources/sound/losing.wav");
            this.updateS();
        }
    }

    public static class GameWinSound extends Sound {
        public GameWinSound() {
            super("src/main/ressources/sound/gamewin.wav");
            this.updateS();
        }
    }

    public static class Music extends Sound {
        public Music() {
            super("src/main/ressources/sound/music.wav");
            this.getClip().loop(Clip.LOOP_CONTINUOUSLY);
            this.updateM();
        }
    }

    public static class BallSound extends Sound {
        public BallSound() {
            super("src/main/ressources/sound/ball.wav");
            this.updateS();
        }
    }
}
