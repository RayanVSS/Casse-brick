package gui.GraphicsFactory;

import physics.entity.Entity;

/**
 * Interface pour les objets qui s'affichent et ont pour particularité de mettre à jour leur statut visuel
 */
public interface EntityGraphics {

    void update();

    Entity getEntity();

    public boolean isWaitingAdded();

    public void setWaitingAdded(boolean waitingAdded);

    public boolean isWaitingRemoved();

    public void setWaitingRemoved(boolean waitingRemoved);
}
