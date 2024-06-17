package views;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Zombie;
import model.characters.Medic;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class GameMapScene extends Scene{
	private Hero h;

	public GameMapScene(Stage window,BorderPane b3,Hero selected) {
		super(b3, 1280, 720);
		/////
		BorderPane group = new BorderPane();
		////
		h = selected;
		Image image =new Image("Gamem.jpg");
		ImageView background =new ImageView(image);
		
		b3.getChildren().addAll(background);	
		
		VBox hs =new VBox(10);
		
		updateCells(window,group);
		updateHeroesAvilable(hs);
		 	    
	    
		this.setOnKeyPressed(new EventHandler<>() {

				public void handle(KeyEvent e) {
					try {
						int x=(int)h.getLocation().getX();
						int y=(int)h.getLocation().getY();
						switch(e.getCode()) {
							case UP : trapDamage(x,y+1);h.move(Direction.RIGHT); break;
							case DOWN:trapDamage(x,y-1);h.move(Direction.LEFT); break;
							case RIGHT:trapDamage(x+1,y);h.move(Direction.UP); break;
							case LEFT:trapDamage(x-1,y);h.move(Direction.DOWN); break;
							case E :try {
								Game.endTurn();
							} catch (InvalidTargetException e1) {
								MessageBox.display("Invalid Target!","ok");
							} break ;
							case A : try {
								h.attack();
							} catch (InvalidTargetException e1) {
								MessageBox.display("Invalid Target!","ok");
							} break ;
							case C : try {
								h.cure();
							} catch (NoAvailableResourcesException e1) {
								MessageBox.display("No Vaccine!","ok");
							} catch (InvalidTargetException e1) {
								MessageBox.display("Invalid Target!","ok");
							} break ;
							case S : try {
								h.useSpecial();
							} catch (NoAvailableResourcesException e1) {
								MessageBox.display("No Supplies!","ok");
							} catch (InvalidTargetException e1) {
								MessageBox.display("Invalid Target!","ok");
							} break ;
						    default:break;
						}
					}catch(NotEnoughActionsException e1) {
						MessageBox.display("you don't have enough actions!","ok");
					}catch(MovementException ex) {
						MessageBox.display("you made an invalid move!","ok");
					}
					updateCells(window,group);
					updateHeroesAvilable(hs);
				}
				
		});
	    	    


		Label l = new Label("\n\nPress A to attack \nPress C to cure \nPress E to end turn \nPress S to use Special Action  \nUse Arrows to Move "); 
		l.setTextFill(Color.WHITE);
		l.setFont(new Font("Arial",24));
		VBox root5=new VBox(10);
		root5.getChildren().add(l);
		root5.setAlignment(Pos.TOP_CENTER);
		b3.setRight(root5);
		b3.setCenter(group);
		b3.setLeft(hs);
	}
	
	public void updateHeroesAvilable(VBox hs) {
		hs.getChildren().clear();
		Label sel = new Label(" Select a Hero to Use");
		sel.setTextFill(Color.WHITE);
		sel.setFont(new Font("Arial",24));
		hs.getChildren().add(sel);
		hs.setAlignment(Pos.CENTER);
		for(int i=0;i<Game.heroes.size();i++) {
			Button btn=new Button("Type: "+Game.heroes.get(i).getClass().getSimpleName()+ "\n" + "Name: "+Game.heroes.get(i).getName()+"\n"+ "HP: "+Game.heroes.get(i).getCurrentHp()+"\n"+"AttackDamage: "+ Game.heroes.get(i).getAttackDmg()+"\n" +"ActionsAvailable: "+Game.heroes.get(i).getActionsAvailable()+"\n"+"Supplies: "+Game.heroes.get(i).getSupplyInventory().size()+"\n"+"Vaccines: "+Game.heroes.get(i).getVaccineInventory().size());
			btn.setStyle("-fx-background-color:#000000;");
			btn.setTextFill(Color.WHITE);
			btn.setFont(Font.font("Arial",14));
			btn.setFocusTraversable(false);
			btn.setId(new Integer(i).toString());
			hs.getChildren().add(btn);
			btn.setOnAction(e -> {
			h = Game.heroes.get(Integer.parseInt(btn.getId()));
		    });
		}	
	}

	public void updateCells(Stage window,BorderPane root){
		GridPane g = new GridPane();
		g.setHgap(1);
		g.setVgap(2);
		for (int i =0 ;i <15 ;i++){
			for (int j =0 ;j <15 ;j++){
		        Button b = new Button();
		        b.setFocusTraversable(false);
		        b.setMaxSize(40, 40);
		        b.setMinSize(40, 40);
		        if(Game.map[i][14-j].isVisible()) {
					b.setStyle("-fx-background-color:#52a447;");
					if(Game.map[i][14-j] instanceof CharacterCell) {
						if(((CharacterCell)Game.map[i][14-j]).getCharacter() != null) {
							CharacterCell c=((CharacterCell)Game.map[i][14-j]);
							b.setOnAction(e ->{
								h.setTarget(c.getCharacter());
								});
							if(((CharacterCell)Game.map[i][14-j]).getCharacter() instanceof Hero) {
								Hero h =(Hero) ((CharacterCell)Game.map[i][14-j]).getCharacter();
								if(h instanceof Medic) {
									Image medimage =new Image("med.png",40,40,false,false);
									ImageView medview =new ImageView(medimage);
									b.setGraphic(medview);
								}
								if(h instanceof Explorer) {
									Image expimage =new Image("exp.png",40,40,false,false);
									ImageView expview =new ImageView(expimage);
									b.setGraphic(expview);
								}
								if(h instanceof Fighter) {
									Image fightimage =new Image("fight.png",40,40,false,false);
									ImageView fightview =new ImageView(fightimage);
									b.setGraphic(fightview);
								}
							}
							else {
								Image zombieimage =new Image("zombie.jpg",40,40,false,false);
								ImageView zombieview =new ImageView(zombieimage);
								b.setGraphic(zombieview);
							}
						}
					}
					else
						if(Game.map[i][14-j] instanceof CollectibleCell) {
							Collectible c = ((CollectibleCell)Game.map[i][14-j]).getCollectible() ;
							if(c instanceof Supply) {
								Image Supplyimage =new Image("Supply.png",40,40,false,false);
								ImageView Supplyview =new ImageView(Supplyimage);
								b.setGraphic(Supplyview);
							}
							else {
								Image Vaccineimage =new Image("Vaccine.png",40,40,false,false);
								ImageView Vaccineview =new ImageView(Vaccineimage);
								b.setGraphic(Vaccineview);
							}
						}
				}
				else {
			     b.setStyle("-fx-background-color: #D7D7D8;"); 
				}
 		        g.add(b, i, j);
 			}
 		}
		g.setAlignment(Pos.CENTER);
 		root.setCenter(g);
 		if(Game.checkWin()) 
			MessageBox.display(window,"Congratulations,You Won!","Exit Game");
 		else {
 			if(Game.checkGameOver()) {
 				MessageBox.display(window,"Game Over You Lost!","Exit Game");				
 			}
 		}
	}
	
	public void trapDamage(int x,int y) {
		if(x>=0&&x<=14) {
			if(y>=0&&y<=14) {
				if(Game.map[x][y] instanceof TrapCell) {
					TrapCell c=(TrapCell)(Game.map[x][y]);
					int dam=c.getTrapDamage();
					MessageBox.display("You have entered trap cell and you had lose"+dam+" health points ", "ok");
				}
			}
		}
	}
	
	
	

	

	

}
