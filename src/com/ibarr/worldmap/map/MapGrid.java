package com.ibarr.worldmap.map;

import com.ibarr.worldmap.Worldmap;
import java.awt.Graphics;
import java.security.SecureRandom;

/**
 *
 * @author iain
 */
public class MapGrid {
	
    private int cols, rows;
	private Tile[][] tiles;
	private Worldmap w;
	private SecureRandom mapRng;

	public MapGrid(Worldmap w) {
		this.w = w;
		cols = w.getSettings().getMapColumns();
		rows = w.getSettings().getMapRows();
		
		mapRng = new SecureRandom();
		
		tiles = new Tile[cols][rows];
		
		// need to replace this with a recursive call to the start tile to generate neighbours
//		for(int y = 0; y < rows; y++) {
//            for(int x = 0; x < cols; x++) {
//                tiles[x][y] = new Tile(this, x, y, w.getSettings().getWindowWidth()/cols, w.getSettings().getWindowHeight()/rows);
//            }
//        }

		tiles[cols/2][rows/2] = new ElevationTile(this, cols/2, rows/2, w.getSettings().getWindowWidth()/cols, w.getSettings().getWindowHeight()/rows, 7);
		tiles[cols/2][rows/2].generateMissingNeighbours(true);

	}
	
	public void render(Graphics g) {
		for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                tiles[x][y].render(g);
            }
        }
	}
	
	public Tile getTile(int x, int y) {
		if(x >= 0 && y >= 0 && x < cols && y < rows) {
			return tiles[x][y];
		}
		return null;
	}
	
	public void setTile(int x, int y, Tile t) {
		tiles[x][y] = t;
	}
	
    public int getCols() {
        return cols;
    }
    
    public int getRows() {
        return rows;
    }
    
	public Worldmap getWorld() {
		return w;
	}
	
	public SecureRandom getRng() {
		return mapRng;
	}
	
}
