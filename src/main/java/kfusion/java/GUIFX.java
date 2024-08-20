package kfusion.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kfusion.java.common.KfusionConfig;

public class GUIFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        System.out.println("GUIFX start");
        // Initialize Kfusion configuration
        final KfusionConfig config = new KfusionConfig();

        // Create the JavaFX Canvas, similar to Kfusion
        Canvas canvas = new Canvas(1320, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set up the layout
        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        // Create the Scene and set it on the Stage
        Scene scene = new Scene(root, 1320, 500);
        primaryStage.setTitle("KFusion JavaFX GUI");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add initial rendering logic (placeholder)
        render(gc, config);
    }

    private void render(GraphicsContext gc, KfusionConfig config) {
        // Placeholder for actual rendering logic
        gc.fillText("Rendering KFusion Pipeline...", 10, 50);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
