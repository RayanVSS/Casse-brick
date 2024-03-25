package gui.GraphicsFactory;

import physics.entity.Entity;

public interface EntityGraphics {

    void update();

    Entity getEntity();

    public boolean isWaitingAdded();

    public void setWaitingAdded(boolean waitingAdded);

    public boolean isWaitingRemoved();

    public void setWaitingRemoved(boolean waitingRemoved);
}
