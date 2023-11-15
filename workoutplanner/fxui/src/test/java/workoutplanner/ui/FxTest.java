package workoutplanner.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.EmptyNodeQueryException;
import org.testfx.service.query.PointQuery;
import org.testfx.util.WaitForAsyncUtils;
import workoutplanner.fxutil.UiUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

public abstract class FxTest extends ApplicationTest {
    /**
     * Local int variable, used to define the minimum width of the stage.
     */
    private static final int MINWIDTH = 600;
    /**
     * Local int variable, used to define the minimum height of the stage.
     */
    private static final int MINHEIGHT = 428;

    private Stage testStage;
    protected ListView<String> listView;

    @Override
    public void start(final Stage primaryStage) throws IOException {
        // Set the app title
        testStage = primaryStage;
        testStage.setTitle("Workout Planner");

        // Set the application icon
        InputStream inputStream = this.getClass().getResourceAsStream("/icon.png");
        assert inputStream != null;
        Image icon = new Image(inputStream);
        testStage.getIcons().add(icon);

        // Set the minimum width and height for the stage
        testStage.setMinWidth(MINWIDTH); // Set the minimum width
        testStage.setMinHeight(MINHEIGHT); // Set the minimum height

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass()
                .getResource("Main.fxml"));
        testStage.setScene(new Scene(fxmlLoader.load()));
        testStage.show();
    }

    public void clickAlertQuery(String query) {
        WaitForAsyncUtils.waitForFxEvents();
        clickOn(query);
        WaitForAsyncUtils.waitForFxEvents();
        clickOn(LabeledMatchers.hasText("OK"));
        WaitForAsyncUtils.waitForFxEvents();
    }

    public void clickAlertLabel(String label) {
        WaitForAsyncUtils.waitForFxEvents();
        clickOn(LabeledMatchers.hasText(label));
        WaitForAsyncUtils.waitForFxEvents();
        clickOn(LabeledMatchers.hasText("OK"));
        WaitForAsyncUtils.waitForFxEvents();
    }

    public boolean isWindowShowing(Parent window) {
        return window.isVisible();
    }

    public void clickAndCheckAlert(final String title, final String message,
                                   final Alert.AlertType alertType) {
        Alert alertButton = UiUtils.getAlert();
        verifyThat("#alertButton", (button) -> !button.isDisabled());
        assertEquals(title,alertButton.getTitle());
        assertEquals(message,alertButton.getContentText());
        assertEquals(alertType,alertButton.getAlertType());
        clickOn("#alertButton");
    }

    public void clickAndCheckConfirmation(final String title, final String message){
        Alert.AlertType alertType = Alert.AlertType.CONFIRMATION;
        Alert confirmationAlert = UiUtils.getAlert();
        assertEquals(title,confirmationAlert.getTitle());
        assertEquals(message,confirmationAlert.getContentText());
        assertEquals(alertType,confirmationAlert.getAlertType());
        Optional<ButtonType> okButton = confirmationAlert.getButtonTypes().stream()
                .filter(buttonType -> buttonType.getText().equals("OK"))
                .findFirst();

        if (okButton.isPresent()) {
            ButtonType buttonType = okButton.get();
            Button okNode = (Button) confirmationAlert.getDialogPane().lookupButton(buttonType);
            if (okNode != null) {
                clickOn(okNode);
            } else {
                fail("OK button not found in the Alert");
            }
        } else {
            fail("OK button not found in the Alert");
        }
    }

    public <T> T getNode(Class<T> expectedType, String id) {
        Node node = lookup("#" + id).query();
        if (expectedType.isAssignableFrom(node.getClass())) {
            return expectedType.cast(node);
        } else {
            fail("Couldn't find node with id: " + id + " of type " + expectedType.getSimpleName());
            return null;
        }
    }

}
