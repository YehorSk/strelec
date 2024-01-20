package application;

import java.io.File;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class Enemy extends ImageView{
	private Image img;
	private Timeline t;
	private boolean direction;
	private Scene scene;
	private List<Ground> ground;
	private BorderPane root;
	
	public Enemy(float x,float y, Scene scene,List<Ground> ground,BorderPane root) {
		super();
		img = new Image(new File("resources/enemy.png").toURI().toString(), 50, 50, false, false);
		setImage(img);
		this.setLayoutX(x*50);
		this.setLayoutY(y*50);
		this.ground = ground;
		this.scene = scene;
		this.root = root;
		t = new Timeline(new KeyFrame(Duration.millis(100),e->move()));
		t.setCycleCount(Animation.INDEFINITE);
		t.play();
	}
	
	public void changeDir() {
		direction = !direction;
	}
	
	public void die() {
		this.root.getChildren().remove(this);
	}
	
	public void move() {
		if(direction) {
			if(this.getLayoutX()+5>=scene.getWidth()-50) {
				direction = !direction;
			}else {
				this.setLayoutX(this.getLayoutX()+5);
			}
		}else {
			if(this.getLayoutX()-5<=0) {
				direction = !direction;
			}else {
				this.setLayoutX(this.getLayoutX()-5);
			}
		}
		for(Ground g : ground) {
        	if(intersectsGround(this.getLayoutX() + 5,this.getLayoutY(),g)) {
        		direction = !direction;
        	}
        }

	}
	
	public boolean intersectsGround(double newX, double y, Ground otherSprite) {
		double d1 = getLayoutX() - otherSprite.getLayoutX();
		double d2 = getLayoutY() - otherSprite.getLayoutY();
		if(((Math.abs(d1)<50) && Math.abs(d2)<50)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean endGround(Ground otherSprite) {
		double d1 = getLayoutY() - otherSprite.getLayoutY();
		if(((Math.abs(d1)<50))) {
			return true;
		}else {
			return false;
		}
	}
	
}
