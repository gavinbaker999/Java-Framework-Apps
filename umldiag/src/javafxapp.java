import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.*;

public class javafxapp extends Application {
	private String[] args;
	
    javafxapp(String[] args) {
    	this.args = args;
    }
    @Override
    public void start(Stage primaryStage) {
        StackPane layout = new StackPane();
//      Label label = new Label("Hello");
//      layout.getChildren().add(label);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
