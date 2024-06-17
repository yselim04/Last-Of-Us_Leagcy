package views;


import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Font ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.characters.Hero;

public class StartGameScene extends Scene{

	public StartGameScene(Stage window,BorderPane root1) {
		super(root1,1280,720);

		//scene1
		HBox title = new HBox(10); 
		HBox strtbtn = new HBox(10);
		
		Button btn1=new Button("Start Game");
		btn1.setTextFill(Color.WHITE);
		btn1.setMinSize(250, 75);
		btn1.setStyle("-fx-font-size: 3em; -fx-background-color:#5A5A5A;");

		
		Image image =new Image("Load.jpg");
		ImageView background =new ImageView(image);
		
		root1.getChildren().addAll(background);	
		strtbtn.getChildren().addAll(btn1);
		
		title.setAlignment(Pos.CENTER);
		strtbtn.setAlignment(Pos.BOTTOM_CENTER);
		btn1.setOnAction(e -> window.setScene(new HerosScene(window,new BorderPane())));
		root1.setCenter(strtbtn);
		root1.setTop(title);
		
	}

}
