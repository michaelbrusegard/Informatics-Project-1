package workoutplanner.ui;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;

public class OverviewControllerTest extends ApplicationTest {

  @Override
  public void start(final Stage primaryStage) {
    primaryStage.show();
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
  public void testSaveOver20charValidation() {
    lookup("#inpName").queryAs(TextField.class).setText("Over 20 characters");
    clickOn("#saveButton");
    FxAssert.verifyThat("#alertButton", (button) -> !button.isDisabled());
    clickOn("#alertButton");
  }

}
