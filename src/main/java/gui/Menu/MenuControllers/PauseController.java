package gui.Menu.MenuControllers;

import java.util.Timer;
import java.util.TimerTask;

import gui.App;
import gui.GameView;
import gui.Menu.MenuViews.PauseView;
import javafx.application.Platform;
import javafx.stage.Stage;
import save.Sauvegarde;
import utils.GameConstants;
import utils.Sound.ClickSound;

public class PauseController {
    private Timer timer;
    private PauseView view;
    private ClickSound click = App.clickSoundPlayer;
    private int sound = GameConstants.SOUND;

    public PauseController(Stage p, PauseView view) {
        this.timer = new Timer();
        this.view = view;
        this.view.getBtnReplay().setOnAction(e -> {
            click.play();
            replay();
        });
        this.view.getBtnQuit().setOnAction(e -> {
            click.play();
            quit();
        });
        this.view.getBtnMenu().setOnAction(e -> {
            click.play();
            menu();
        });
        this.view.getBtnResume().setOnAction(e -> {
            click.play();
            resume();
        });

        this.view.getBtnMuteMusic().setOnAction(e -> {
            click.play();
            muteMusic();
        });

        this.view.getBtnMuteSound().setOnAction(e -> {
            click.play();
            muteSound();
        });

    }

    private void replay() {
        view.getStageLevel().resetGame();
        view.getChildren().clear();
        this.timer.cancel();
        new GameView(view.getPrimaryStage(), view.getStageLevel());
    }

    private void quit() {
        Sauvegarde sauvegarde = new Sauvegarde();
        sauvegarde.autoSave();
        this.timer.cancel();
        view.getPrimaryStage().close();
        Platform.exit();
        System.exit(0);
    }

    private void menu() {
        view.getChildren().clear();
        this.timer.cancel();
        App.menuManager.changeScene(view.getPrimaryStage(), "StartMenuView");
    }

    private void resume() {
        view.getRoot().getChildren().remove(view);
        TimerTask task = new TimerTask() {
            public void run() {
                view.getAnimationTimer().start();
            }
        };
        timer.schedule(task, 1000);
    }

    public void muteMusic() {
        if (GameConstants.MUSIC == 0) {
            this.view.getBtnMuteMusic().setGraphic(view.getMuteImageView());
            return;
        }
        if (App.music.isMute()) {
            this.view.getBtnMuteMusic().setGraphic(view.getUnmuteImageView());
            App.music.play();
        } else if (!App.music.isMute()) {
            this.view.getBtnMuteMusic().setGraphic(view.getMuteImageView());
            App.music.stop();
        }
    }

    private void muteSound() {
        if (GameConstants.SOUND == 0) {
            this.view.getBtnMuteSound().setGraphic(view.getUnmuteImageView2());
            if (sound == 0) {
                sound = 50;
            }
            GameConstants.SOUND = sound;
            App.clickSoundPlayer.update();
            App.ballSound.update();
        } else {
            this.view.getBtnMuteSound().setGraphic(view.getMuteImageView2());
            GameConstants.SOUND = 0;
            App.clickSoundPlayer.update();
            App.ballSound.update();
        }
    }

    public PauseView getView() {
        return view;
    }

}