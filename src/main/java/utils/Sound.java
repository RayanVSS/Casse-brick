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
    private static double volumeS = GameConstants.SOUND / 100;
    private static double volumeM = GameConstants.MUSIC / 100;

    public Sound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        volumeS = GameConstants.SOUND / 100.0;
        FloatControl volumeControl = (FloatControl) this.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue((float) (Math.log(volumeS) / Math.log(10.0) * 20.0));
    }

    public void play() {
        this.getClip().setFramePosition(0); // Reset the sound to the beginning
        this.getClip().start(); // Play the sound
    }

    public void stop() {
        this.getClip().stop();
    }

    public Clip getClip() {
        return this.clip;
    }

    public static double getVolumeS() {
        return volumeS;
    }

    public static double getVolumeM() {
        return volumeM;
    }

    public static void setVolumeS(double volumeS) {
        Sound.volumeS = volumeS;
    }

    public static void setVolumeM(double volumeM) {
        Sound.volumeM = volumeM;
    }

    public static class ClickSound extends Sound {
        public ClickSound() {
            super("src/main/ressources/sound/clickv2.wav");
            this.update();
        }

        @Override
        public void update() {
            volumeS = GameConstants.SOUND / 100.0;
            FloatControl volumeControl = (FloatControl) this.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue((float) (Math.log(volumeS) / Math.log(10.0) * 20.0));
            this.play();
        }
    }

    public static class GameOverSound extends Sound {
        public GameOverSound() {
            super("src/main/ressources/sound/losing.wav");
            this.update();
        }
    }

    public static class GameWinSound extends Sound {
        public GameWinSound() {
            super("src/main/ressources/sound/gamewin.wav");
            this.update();
        }
    }

    public static class Music extends Sound {
        public Music() {
            super("src/main/ressources/sound/music.wav");
            this.getClip().loop(Clip.LOOP_CONTINUOUSLY);
            this.update();
            this.play();
        }

        @Override
        public void play() {
            this.getClip().setFramePosition(0); // Reset the sound to the beginning
            this.getClip().start(); // Play the sound
        }

        public boolean isMute() {
            return !this.getClip().isRunning();
        }

        @Override
        public void update() {
            volumeM = GameConstants.MUSIC / 100.0;
            FloatControl volumeControl = (FloatControl) this.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue((float) (Math.log(volumeM) / Math.log(10.0) * 20.0));
            if(volumeM == 0) {
                this.stop();
            }else if(this.isMute()) {
                this.play();
            }
        }
    }

    public static class BallSound extends Sound {
        public BallSound() {
            super("src/main/ressources/sound/ball.wav");
            this.update();
        }
    }

    public static class BonusSound extends Sound {
        public BonusSound() {
            super("src/main/ressources/sound/bonus.wav");
            this.update();
        }
    }

    public static class MalusSound extends Sound {
        public MalusSound() {
            super("src/main/ressources/sound/malus.wav");
            this.update();
        }
    }

    public static class LevelUpSound extends Sound {
        public LevelUpSound() {
            super("src/main/ressources/sound/levelUP.wav");
            this.update();
        }
    }




}
