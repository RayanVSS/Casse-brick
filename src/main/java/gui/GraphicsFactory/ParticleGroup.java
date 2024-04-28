package gui.GraphicsFactory;

import java.util.ArrayList;
import java.util.List;

import config.Game;
import javafx.scene.layout.Pane;
import physics.entity.Ball;
import utils.GameConstants;

public class ParticleGroup{
    private List<List<Particle>> particles=new ArrayList<>();
    private int trailLength=GameConstants.DEFAULT_trailLength;
    private Game game;

    public ParticleGroup(Pane root,Game game) {
        this.game = game;
        for (int i = 0; i < trailLength; i++) {
            List<Particle> trail = new ArrayList<>();
            for (int j = 0; j < GameConstants.DEFAULT_PARTICLE; j++) { // nombre de particules
                Particle particle = new Particle(GameConstants.DEFAULT_WINDOW_WIDTH / 2, GameConstants.DEFAULT_WINDOW_HEIGHT/ 2);
                trail.add(particle);
                root.getChildren().add(particle);
            }
            particles.add(trail);
        }
    }

    public void update() {
        for (int i = 0; i < trailLength; i++) {
            List<Particle> trail = particles.get(i);

            for (int j = trail.size() - 1; j > 0; j--) {
                Particle currentParticle = trail.get(j);
                Particle previousParticle = trail.get(j - 1);

                currentParticle.setCenterX(previousParticle.getCenterX());
                currentParticle.setCenterY(previousParticle.getCenterY());
                currentParticle.applyRandomFluctuation(); // fluctuation des particules
            }
            Particle firstParticle = trail.get(0);
            for(Ball b:game.getBalls()){
                firstParticle.setCenterX(b.getC().getX());
                firstParticle.setCenterY(b.getC().getY());
                firstParticle.applyRandomFluctuation(); // Appliquer la fluctuation
                // Si il y a des balles (au moins une balle)
                return;
            }
        }
    }
}
