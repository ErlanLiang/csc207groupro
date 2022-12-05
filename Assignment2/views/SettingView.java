package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import commands.*;

/**
 * Setting View
 *
 */
public class SettingView {

    TetrisView tetrisView;

    CommandManager commandManager;

    private Label settingErrorLabel = new Label("");

    private Button increaseColorContrastButton = new Button("Increase color contrast");
    private Button decreaseColorContrastButton = new Button("Decrease color contrast");

    /**
     * Constructor
     *
     * @param tetrisView master view
     */
    public SettingView(TetrisView tetrisView) {
        this.commandManager = new CommandManager();
        this.tetrisView = tetrisView;
        tetrisView.paused = true;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(tetrisView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        settingErrorLabel.setId("settingErrorLabel");
        settingErrorLabel.setStyle("-fx-text-fill: #e8e6e3;");
        settingErrorLabel.setFont(new Font(16));

        increaseColorContrastButton = new Button("Increase color contrast");
        increaseColorContrastButton.setId("increaseColorContrast"); // DO NOT MODIFY ID
        increaseColorContrastButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        increaseColorContrastButton.setPrefSize(200, 50);
        increaseColorContrastButton.setFont(new Font(15));
        increaseColorContrastButton.setOnAction(e -> increaseColorContrast());

        decreaseColorContrastButton = new Button("Decrease color contrast");
        decreaseColorContrastButton.setId("decreaseColorContrast"); // DO NOT MODIFY ID
        decreaseColorContrastButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        decreaseColorContrastButton.setPrefSize(200, 50);
        decreaseColorContrastButton.setFont(new Font(15));
        decreaseColorContrastButton.setOnAction(e -> decreaseColorContrast());

        VBox settingBox = new VBox(10, increaseColorContrastButton, decreaseColorContrastButton, settingErrorLabel);

        dialogVbox.getChildren().add(settingBox);
        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();
        dialog.setOnCloseRequest(event -> {
            tetrisView.paused = false;
        });

    }

    public void increaseColorContrast() {
        IncreaseColorContrast increaseColorContrast = new IncreaseColorContrast(this.tetrisView);
        settingErrorLabel.setText(this.commandManager.executeCommand(increaseColorContrast));
    }

    public void decreaseColorContrast() {
        DecreaseColorContrast decreaseColorContrast = new DecreaseColorContrast(this.tetrisView);
        settingErrorLabel.setText(this.commandManager.executeCommand(decreaseColorContrast));
    }
}
