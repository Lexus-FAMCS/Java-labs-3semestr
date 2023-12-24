import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Loader {

    private Canvas canvas;

    Loader(Canvas canvas) { this.canvas = canvas; }

    public void load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image files (*.png, *.jpeg, *.jpg, *.gif)", "*.png", "*.jpeg", "*.jpg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                loadIntoFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadIntoFile(File file) throws IOException {
        Image image = new Image(file.toURI().toString());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
    }
}