package workoutplanner.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OverviewController {
    @FXML
    private VBox containerBox;
    @FXML
    public void cancel(){
        System.out.println("hei");
    }
    @FXML
    public void initialize(){
        System.out.println("test");
        Text txt = new Text("hei");
        containerBox.getChildren().add(0,txt);
    }
}
