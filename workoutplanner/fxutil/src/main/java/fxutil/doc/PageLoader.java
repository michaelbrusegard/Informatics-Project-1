package fxutil.doc;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PageLoader {
    public static Controller pageLoader(FXMLLoader loader, Node button) throws IOException {
        Parent root = loader.load();
        // Create a new scene and set it on the stage
        Scene scene = new Scene(root);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
        // Show the stage
        stage.show();
        return loader.getController();
    }
}
