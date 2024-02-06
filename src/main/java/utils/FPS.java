package utils;

public class FPS {
    private long lastTime = System.nanoTime();
    private int frameCount = 0;
    private double fps = 0.0;
    private double maxFps = 0.0;

    public FPS() {
    }

    public double calculateFPS() {
        long now = System.nanoTime();
        long deltaTime = now - lastTime;
        fps = frameCount / (deltaTime / 1e9);
        frameCount = 0;
        lastTime = now;
        frameCount++;
        setMaxFps(fps);
        return fps;
    }

    public void setMaxFps(double fps) {
        if (fps > this.maxFps) {
            this.maxFps = fps;
        }
    }

    public double getMaxFps() {
        return maxFps;
    }





}
