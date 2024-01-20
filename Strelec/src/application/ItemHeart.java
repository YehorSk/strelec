package application;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemHeart extends ImageView{
	
	private Image img = new Image(new File("resources/heart.png").toURI().toString(), 50, 50, false, false);
	
	private Random random = new Random();
	private String type;
	
	public ItemHeart(float x,float y) {
		super();
		type = "H";
		setImage(img);
		this.setLayoutX(x*50);
		this.setLayoutY(y*50);
	}
	public String getType() {
		return type;
	}

}
