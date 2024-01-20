package application;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player extends ImageView {
    
    private Image player_img = new Image(new File("resources/player.png").toURI().toString(), 50, 50, false, false);
    private Scene scene;
    private BorderPane root;
    private List<Ground> ground;
    private List<ItemHeart> hearts;
    private List<ItemCoin> coins;
    private List<Enemy> enemies;
    private boolean direction = true;
    private int hearts_am = 3;
    private int coins_am = 0;
    private static int max_hearts = 3;

    public Player(Scene scene, BorderPane root, int x, int y, List<Ground> ground,List<ItemHeart> hearts,List<ItemCoin> coins,List<Enemy> enemies) {
        super();
        this.setLayoutX(x * 50);
        this.setLayoutY(y * 50);
        this.ground = ground;
        this.scene = scene;
        this.root = root;
        this.hearts_am = 3;
        this.hearts = hearts;
        this.coins = coins;
        this.enemies = enemies;
        setImage(player_img);
        scene.setOnKeyPressed((evt) -> move(evt.getCode().toString())); 
        drawHearts();
        drawCoins();
    }

    public void move(String key) {
        Iterator<ItemHeart> heartIterator = hearts.iterator();
        Iterator<ItemCoin> coinIterator = coins.iterator();
        
        switch (key) {
            case "RIGHT":
                move(5);
                direction = true;
                break;
            case "LEFT":
                move(-5);
                direction = false;
                break;
            case "UP":
                jump();
                break;
            case "SPACE":
                root.getChildren().add(new Bullet(this.scene, this.root, this.getLayoutX(), this.getLayoutY() + 20, direction, this.ground,enemies));
                break;
        }

        while (heartIterator.hasNext()) {
            ItemHeart item = heartIterator.next();
            if (intersectsHeart(item)) {
                root.getChildren().remove(item);
                heartIterator.remove(); 
                hearts_am++;
                drawHearts();
            }
        }
        while (coinIterator.hasNext()) {
        	ItemCoin item = coinIterator.next();
            if (intersectsCoin(item)) {
                root.getChildren().remove(item);
                coinIterator.remove(); 
                coins_am++;
                drawCoins();
            }
        }

    }

    public void move(int value) {
    	
    	for(Ground g : ground) {
        	if(intersectsGround(g)) {
        		this.setLayoutX(this.getLayoutX() -1);
        		return;
        	}
        }
    	for(Enemy e : enemies) {
        	if(intersectsEnemy(e)) {
        		e.changeDir();
        		hearts_am-=1;
        		drawHearts();
        		return;
        	}
        }
    	
        this.setLayoutX(this.getLayoutX() + value);
    }
    
    public void drawHearts() {
        root.getChildren().removeIf(node -> node instanceof Heart);

        for (int i = 0; i < hearts_am; i++) {
            root.getChildren().add(new Heart(i));
        }
    }
    public void drawCoins() {
        root.getChildren().removeIf(node -> node instanceof Coin);

        for (int i = 0; i < coins_am; i++) {
            root.getChildren().add(new Coin(i));
        }
    }
    
	public boolean intersectsHeart(ItemHeart otherSprite) {
		double d1 = getLayoutX() - otherSprite.getLayoutX();
		double d2 = getLayoutY() - otherSprite.getLayoutY();
		if(((Math.abs(d1)<50) && Math.abs(d2)<50)) {
			return true;
		}else {
			return false;
		}
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
	
	public boolean intersectsCoin(ItemCoin otherSprite) {
		double d1 = getLayoutX() - otherSprite.getLayoutX();
		double d2 = getLayoutY() - otherSprite.getLayoutY();
		if(((Math.abs(d1)<50) && Math.abs(d2)<50)) {
			return true;
		}else {
			return false;
		}
	}
	public boolean intersectsGround( Ground otherSprite) {
		double d1 = getLayoutX() - otherSprite.getLayoutX();
		double d2 = getLayoutY() - otherSprite.getLayoutY();
		if(((Math.abs(d1)<50) && Math.abs(d2)<50)) {
			return true;
		}else {
			return false;
		}
	}

    
	public void jump() {

	}

      
    


}
