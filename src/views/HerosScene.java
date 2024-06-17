package views;

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

public class HerosScene extends Scene{

	public HerosScene(Stage window,BorderPane b) {
		super(b,1280,720);
		
		
		Image image =new Image("Hero.jpg");
		ImageView background2 =new ImageView(image);
		b.getChildren().addAll(background2);
		HBox Heroes = new HBox(10); 
		HBox title =new HBox(10);

		try {
			Game.loadHeroes("Heroes.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Hero h:Game.availableHeroes) {
			Button btn2 = new Button("Type: "+h.getClass().getSimpleName()+"\n"+"Name: "+h.getName()+"\n"+"MaxHP: "+h.getMaxHp()+"\n"+"AttackDamage: "+h.getAttackDmg()+"\n"+"MaxActions: "+h.getMaxActions());
			btn2.setTextFill(Color.WHITE);
			btn2.setFont(Font.font("Arial",14));
			btn2.setMinHeight(200);
			btn2.setStyle("-fx-background-color:#4a4a4a;");
			Heroes.getChildren().add(btn2);			
			btn2.setOnAction(e -> {
				Game.startGame(h);
				MessageBox.display(window, "You have chosen ''"+h.getName()+"'' to be your hero!!","Start","Canel",h);
			});
		}
		Heroes.setAlignment(Pos.CENTER);
		title.setAlignment(Pos.TOP_LEFT);

		b.setTop(title);
		b.setCenter(Heroes);
	}

}
