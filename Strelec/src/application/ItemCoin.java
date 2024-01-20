package application;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemCoin extends ImageView{
	
	private Image img = new Image(new File("resources/coin_1.png").toURI().toString(), 50, 50, false, false);
	
	private Random random = new Random();
	private String type;
	
	public ItemCoin(float x,float y) {
		super();
		type = "C";
		setImage(img);
		this.setLayoutX(x*50);
		this.setLayoutY(y*50);
	}
	public String getType() {
		return type;
	}

}
