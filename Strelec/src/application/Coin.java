package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coin extends ImageView{
	private Image img;
	
	public Coin(float x) {
		super();
		img = new Image(new File("resources/coin_1.png").toURI().toString(), 50, 50, false, false);
		setImage(img);
		this.setLayoutX(x*50);
		this.setLayoutY(60);
	}
}
