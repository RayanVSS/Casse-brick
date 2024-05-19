// package gui.Menu.MenuViews;

// import gui.Menu.Menu;
// import gui.Menu.MenuControllers.InfinityModeController;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import utils.GameConstants;

// public class InfinityModeView implements Menu {
//     private Stage primaryStage;
//     private VBox root;
//     private Scene scene;

//     // private CheckBox RandomMode;
//     // private CheckBox ColorMode;
//     // private CheckBox TransparentMode;
//     // private CheckBox UnbreakableMode;

//     private Button playButton;
//     private Button backButton;

//     public InfinityModeView(Stage primaryStage) {
//         this.primaryStage = primaryStage;
//         root = new VBox(45);
//         scene = new Scene(root, GameConstants.DEFAULT_WINDOW_WIDTH, GameConstants.DEFAULT_WINDOW_HEIGHT);
//         backButton = createButton("Retour", 0, 0);

//         // RandomMode = createCheckBox("Random Mode");
//         // ColorMode = createCheckBox("Color Mode");
//         // TransparentMode = createCheckBox("Transparent Mode");
//         // UnbreakableMode = createCheckBox("Unbreakable Mode");

//         playButton = createButton("Jouer", 0, 0);
//         // root.getChildren().addAll(RandomMode, ColorMode, TransparentMode,
//         // UnbreakableMode, playButton, backButton);
//         root.getChildren().addAll(playButton, backButton);
//         new InfinityModeController(this);
//     }

//     public Stage getPrimaryStage() {
//         return primaryStage;
//     }

//     public Scene getScene() {
//         return scene;
//     }

//     public Button getBackButton() {
//         return backButton;
//     }

//     public Button getPlayButton() {
//         return playButton;
//     }

//     public VBox getRoot() {
//         return root;
//     }

//     // public CheckBox getRandomMode() {
//     // return RandomMode;
//     // }

//     // public CheckBox getColorMode() {
//     // return ColorMode;
//     // }

//     // public CheckBox getTransparentMode() {
//     // return TransparentMode;
//     // }

//     // public CheckBox getUnbreakableMode() {
//     // return UnbreakableMode;
//     // }
// }
