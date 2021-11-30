package gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SubmittedController {
    public void onBackClicked(MouseEvent mouseEvent) throws IOException {
        Window window = ((Node) mouseEvent.getTarget()).getScene().getWindow();

        /* get the dimension and position of previous window */
        double height = window.getHeight();
        double width = window.getWidth();
        double x = window.getX();
        double y = window.getY();
        boolean isFullscreen = ((Stage) window).isFullScreen();

        FXMLLoader fxmlLoader = new FXMLLoader(SubmittedController.class.getResource("/gui/main.fxml"));
        Parent submitted = (Parent) fxmlLoader.load();

        Scene scene = new Scene(submitted);
        Stage appStage = (Stage) window;
        appStage.setScene(scene);

        appStage.setHeight(height);
        appStage.setWidth(width);
        appStage.setX(x);
        appStage.setY(y);
        appStage.setFullScreen(isFullscreen);

        appStage.show();
    }
}
