package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Invisible extends ImageView{
	private Image img;
	
	public Invisible(float x,float y) {
		super();
		img = new Image(new File("resources/invisible.png").toURI().toString(), 50, 50, false, false);
		setImage(img);
		this.setLayoutX(x*50);
		this.setLayoutY(y*50);
	}
}
