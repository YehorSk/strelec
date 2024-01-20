package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heart extends ImageView{
	private Image img;
	
	public Heart(float x) {
		super();
		img = new Image(new File("resources/heart.png").toURI().toString(), 50, 50, false, false);
		setImage(img);
		this.setLayoutX(x*50);
		this.setLayoutY(10);
	}
}
