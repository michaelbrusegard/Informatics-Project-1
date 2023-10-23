package workoutplanner.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;

public class OverviewControllerTest extends ApplicationTest {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        // FXMLLoader loader = new
        // FXMLLoader(OverviewController.class.getResource("Overview.fxml"));
        // Parent root = loader.load();
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testCancel() {
        // Click on the "newWorkout" button
        clickOn("#cancelButton");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testSavedValidation() {
        clickOn("#saveButton");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

    @Test
    public void testSaveover20charValidation() {
        lookup("#inpName").queryAs(TextField.class).setText("Over 20 characterssss");
        clickOn("#saveButton");
        FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
        clickOn("#alertButton");
    }

}
