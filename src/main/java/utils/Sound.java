package utils;

import javafx.scene.media.MediaPlayer;

import java.io.File;

import javafx.scene.media.Media;

public class Sound {
    private Media media;
    private MediaPlayer mediaPlayer;

    public Sound(String path) {
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(GameConstants.SOUND);
    }

    public void play() {
        mediaPlayer.stop(); // Stop the sound if it's currently playing
        mediaPlayer.setVolume(GameConstants.SOUND);
        mediaPlayer.seek(javafx.util.Duration.ZERO); // Reset the sound to the beginning
        mediaPlayer.play(); // Play the sound
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static class ClickSound extends Sound {
        public ClickSound() {
            super("src/main/ressources/sound/click.mp3");
        }

    }
}
