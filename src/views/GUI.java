package views;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.characters.Hero;
import javafx.scene.effect.ImageInput;  
import javafx.scene.image.*;  

public class GUI extends Application {

	Stage window = new Stage();
	Scene scene1 , scene2 , scene3;
	Hero h;

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage; 
		BorderPane root1 = new BorderPane();
		StartGameScene scene1 = new StartGameScene(primaryStage,root1);
		Image icon = new Image("icon.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene1);  
		primaryStage.setTitle("Last of Us - Legacy");  
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[]args){
		launch(args); 
	}
}