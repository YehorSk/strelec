package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Level extends Group {
	
	private static int levels_amount = 2;
    private int current_level = 1; 
    private FileReader fr;
    private BufferedReader br;
    private char[][] lvl;
    private int x, y;
    private int pos_x, pos_y;
    private BorderPane root;
    private String rows = "";
    private char[][] lvl_copy;
    private Scene scene;
    private Stage primaryStage;
    private String row;
    private List<Ground> ground = new ArrayList<>();
    private List<ItemHeart> hearts = new ArrayList<>();
    private List<ItemCoin> coins = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Invisible> invisible = new ArrayList<>();

    public Level(Stage primaryStage,BorderPane root) {
    	this.root = root;
        this.primaryStage = primaryStage;
        setLevel();
    }
    

	public void setLevel() {
        
        x = 0;
        y = 0;
        readFile();
        lvl = new char[x][y];
        scene = new Scene(root, x * 50, y * 50);
        primaryStage.setScene(scene);

        primaryStage.setMinWidth(x * 50+10);
        primaryStage.setMinHeight(y * 50+30);
        primaryStage.setMaxWidth(x * 50+10);
        primaryStage.setMaxHeight(y * 50+30);
        
        fill();
        printCubes(lvl);
        printItems(lvl);
        printEnemies(lvl);
        printPlayer(lvl);
        this.requestFocus();
    }
	
	public Scene getNewScene() {
		return scene;
	}
    
	 public void nextLevel() { 
	        if (current_level + 1 <= levels_amount) {
	            current_level++;
	        }
	        setLevel();
	    }
	
    public void readFile() { 
    	rows = "";
        try {
            fr = new FileReader(new File("levels/lvl_1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(fr);
        row = "";
        try {
            while ((row = br.readLine()) != null) {
                x = Math.max(x, row.length());
                y++;
                rows += row + " ";
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void fill() {
        String[] new_r = rows.split(" ");
        for (int i = 0; i < new_r.length; i++) {
            for (int j = 0; j < new_r[i].length(); j++) {
                lvl[j][i] = new_r[i].charAt(j);
            }
        }
    }
    
    public void printCubes(char[][] lvl) { 
        root.getChildren().clear();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (lvl[i][j]) {
                    case '1':
                    	Ground g = new Ground(i, j);
                    	ground.add(g);
                        root.getChildren().add(g);
                        break;   
                }
            }
        }
    }
    public void printItems(char[][] lvl) { 
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (lvl[i][j]) {
                    case '3':
                    	ItemHeart item = new ItemHeart(i, j);
                        root.getChildren().add(item);
                        hearts.add(item);
                        break;   
                    case '4':
                    	ItemCoin coin = new ItemCoin(i, j);
                        root.getChildren().add(coin);
                        coins.add(coin);
                        break;   
                }
            }
        }
    }
    public void printEnemies(char[][] lvl) { 
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (lvl[i][j]) {
                    case '5':
                    	Enemy enemy = new Enemy(i, j,scene,this.ground,root);
                        root.getChildren().add(enemy);
                        enemies.add(enemy);
                        break;    
                }
                switch (lvl[i][j]) {
                case '6':
                	Invisible inv = new Invisible(i, j);
                    root.getChildren().add(inv);
                    invisible.add(inv);
                    break;    
            }
            }
        }
    }
    public void printPlayer(char[][] lvl) { 
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (lvl[i][j]) {
                    case '2':
                        root.getChildren().add(new Player( scene, root, i, j,this.ground,this.hearts,this.coins,this.enemies));
                        break;  
                }
            }
        }
    }
    
}
