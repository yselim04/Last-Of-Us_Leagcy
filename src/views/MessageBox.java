package views;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.characters.Hero;
import javafx.application.Application;

public class MessageBox {
	public static void display ( String message,String buttonName) {
		Stage window = new Stage() ;
		Image icon = new Image("icon.png");		
		window.getIcons().add(icon);
		window.initModality (Modality.APPLICATION_MODAL) ;
		window.setTitle("Last of Us - Legacy");
		window.setMinWidth (250);
		Label label = new Label ();
		label. setText (message);
		Button closeButton = new Button (" "+buttonName);
		closeButton.setOnAction (e -> window.close ());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene (layout);
		window.setResizable(false);
		window. setScene (scene);
		window. showAndWait ();
	}
	
	public static void display (Stage s, String message,String button1Name,String button2Name,Hero h) {
		Stage window = new Stage() ;
		Image icon = new Image("icon.png");	
		window.setTitle("Last of Us - Legacy");
		window.getIcons().add(icon);
		window.initModality (Modality.APPLICATION_MODAL) ;
		window.setMinWidth (250);
		Label label = new Label ();
		label. setText (message);
		Button stButton = new Button (" "+button1Name);
		Button cancelButton = new Button (" "+button2Name);
		cancelButton.setOnAction (e -> window.close ());
		stButton.setOnAction (e -> {
			window.close();
			s.setScene(new GameMapScene(s,new BorderPane(),h));
			
		});
		HBox layout = new HBox(10);
		VBox layout1 = new VBox(10);
		layout.getChildren().addAll(stButton,cancelButton);
		layout.setAlignment(Pos.CENTER);
		layout1.getChildren().addAll(layout);
		layout1.setAlignment(Pos.CENTER);
		Scene scene = new Scene (layout1);
		window.setResizable(false);
		window. setScene (scene);
		window. showAndWait ();
	}
	
	public static void display (Stage stage, String message,String buttonName) {
		Stage window = new Stage() ;
		window.initModality (Modality.APPLICATION_MODAL) ;
		Image icon = new Image("icon.png");	
		window.setTitle("Last of Us - Legacy");
		window.getIcons().add(icon);
		window.setMinWidth (250);
		Label label = new Label ();
		label. setText (message);
		Button closeButton = new Button (" "+buttonName);
		closeButton.setOnAction (e -> {
			window.close();
			stage.close ();
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene (layout);
		window.setResizable(false);
		window. setScene (scene);
		window. showAndWait ();
	}
	
}