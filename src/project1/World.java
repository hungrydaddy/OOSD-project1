package project1;

//import com.sun.java.util.jar.pack.Instruction;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class World {

	// tiles
	private Image tile_floor;
	private Image tile_stone;
	private Image tile_target;
	private Image tile_wall;
	private Image tile_player_left;

	// map info
	ArrayList<String> mapInfo;
	Integer X_offset;
	Integer Y_offset;




	public World() throws SlickException {
		mapInfo = loadLevel(0);
		setupTiles();
		setupOffsets();

		mapInfo.get(0);
		mapInfo.remove(0);
	}



	public void update(Input input, int delta) throws SlickException {

	}



	public void render(Graphics g) throws SlickException {

		for (int i = 0;i<mapInfo.size();i++) {
			// seperate a line with comma
			String[] line = mapInfo.get(i).split(",");
			Integer x = Integer.parseInt(line[1]) * 32;
			Integer y = Integer.parseInt(line[2]) * 32;

			switch (line[0]) {
				case "wall":
					tile_wall.draw(X_offset + x, Y_offset + y);
					break;
				case "floor":
					tile_floor.draw(X_offset + x, Y_offset + y);
					break;
				case "stone":
					tile_stone.draw(X_offset + x, Y_offset + y);
					break;
				case "target":
					tile_target.draw(X_offset + x, Y_offset + y);
					break;
				case "player":
					tile_player_left.draw(X_offset + x, Y_offset + y);
					break;
			}
		}
	}










	/* ------------------------------------------------------------------------------------  */
	/* ------------------------------------------------------------------------------------  */
	/* decomposed methods below */

	private void setupTiles() throws SlickException {
		// initialising all tile components
		tile_floor = new Image("res/floor.png");
		tile_stone = new Image("res/stone.png");
		tile_target = new Image("res/target.png");
		tile_wall = new Image("res/wall.png");
		tile_player_left = new Image("res/player_left.png");
	}


	private void setupOffsets() throws SlickException {
		String[] mapSize = mapInfo.get(0).split(",");

		//TODO: Screen size calculation
		X_offset = 20 * 32;
		Y_offset = 10 * 32;
		//X_offset = Integer.parseInt(mapSize[0]) * 32;
		//Y_offset = Integer.parseInt(mapSize[1]) * 32;
	}


	// loading the required level
	private ArrayList<String> loadLevel(Integer levelNumber) throws  SlickException {
		String lvlFilePath = "res/levels/" + levelNumber + ".lvl";
		ArrayList<String> lvlInfo = new ArrayList<String>();


		String line = "";
		// try reading the csv file and append it to an array for later usage
		try (BufferedReader reader = new BufferedReader(new FileReader(lvlFilePath))) {
			while ((line = reader.readLine()) != null) {
				lvlInfo.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lvlInfo;
	}




}
