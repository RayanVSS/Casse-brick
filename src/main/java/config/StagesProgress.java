package config;

public class StagesProgress {

    private StageLevel[] stages;

    public StagesProgress(int nbStages) {
        this.stages = new StageLevel[nbStages];
    }

    public void createStages() {
        for (int i = 0; i < stages.length; i++) {
            stages[i] = new StageLevel(i, i + 1); // ajustement de la difficulté à faire
        }
    }

    public StageLevel[] getStages() {
        return stages;
    }

}
