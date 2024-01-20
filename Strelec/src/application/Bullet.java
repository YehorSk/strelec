package application;

import java.io.File;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class Bullet extends ImageView{
	private Image bullet_img = new Image(new File("resources/bullet.png").toURI().toString(), 10, 10, false, false);
	private Scene scene;
	private Timeline t;
	private BorderPane root;
	private List<Ground> ground;
	private double x;
	private double y;
	private boolean direction;
	private List<Enemy> enemies;
	
	public Bullet(Scene scene,BorderPane root,double x, double y, boolean direction,List<Ground> ground,List<Enemy> enemies) {
		super();
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setImage(bullet_img);
		this.root = root;
		this.scene = scene;
		this.direction = direction;
		this.ground = ground;
		this.enemies = enemies;
		if(this.direction) {
			setRotate(90);
		}else {
			setRotate(270);
		}
		t = new Timeline(new KeyFrame(Duration.millis(100),e->move()));
		t.setCycleCount(Animation.INDEFINITE);
		t.play();
	}
	public void move() {
	    if (this.direction) {
	        if (this.getLayoutX() < scene.getWidth()) {
	            this.setLayoutX(this.getLayoutX() + 20);
	        } else {
	            this.t.stop();
	            this.root.getChildren().remove(this);
	        }
	    } else {
	        if (this.getLayoutX() != 0) {
	            this.setLayoutX(this.getLayoutX() - 20);
	        } else {
	            this.t.stop();
	            this.root.getChildren().remove(this);
	        }
	    }

	    for (Ground g : ground) {
	        if (intersectsGround(g)) {
	            this.t.stop();
	            this.root.getChildren().remove(this);
	            break;
	        }
	    }
    	for(Enemy e : enemies) {
        	if(intersectsEnemy(e)) {
        		e.die();
        		this.root.getChildren().remove(this);
        		return;
        	}
        }
	}

	
	public boolean intersectsGround(Ground ground) {
	    return this.getBoundsInParent().intersects(ground.getBoundsInParent());
	}

	
	public boolean intersectsEnemy(Enemy otherSprite) {
		double d1 = getLayoutX() - otherSprite.getLayoutX();
		double d2 = getLayoutY() - otherSprite.getLayoutY();
		if(((Math.abs(d1)<50) && Math.abs(d2)<50)) {
			return true;
		}else {
			return false;
		}
	}


}
