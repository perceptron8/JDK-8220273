package sandbox;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ObservableList<String> items = FXCollections.observableArrayList();
		
		ListView<String> list = new ListView<>(items);
		
		// it's easier to see this bug when custom cell factory is used
		list.setCellFactory(TextFieldListCell.forListView());
		list.setEditable(true);
		
		Button minus = new Button("-");
		Button plus = new Button("+");
		minus.disableProperty().bind(Bindings.isEmpty(items));
		minus.setOnAction(e -> items.remove(items.size() - 1));
		plus.setOnAction(e -> items.add(null));
		
		Label label = new Label();
		label.textProperty().bind(Bindings.size(items).asString());
		
		VBox root = new VBox(new HBox(minus, plus), list, label);
		
		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
