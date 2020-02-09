package com.ibarr.worldmap.map;

import com.ibarr.worldmap.Worldmap;
import java.awt.Graphics;

/**
 *
 * @author iain
 */
public class MapGrid {
	
    private int cols, rows;
	private Tile[][] tiles;
	private Worldmap w;

	public MapGrid(Worldmap w) {
		this.w = w;
		cols = w.getSettings().getMapColumns();
		rows = w.getSettings().getMapRows();
		
		tiles = new Tile[cols][rows];
		
		for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                tiles[x][y] = new Tile(this, x, y, w.getSettings().getWindowWidth()/cols, w.getSettings().getWindowHeight()/rows);
            }
        }
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
	
    public int getCols() {
        return cols;
    }
    
    public int getRows() {
        return rows;
    }
    
	public Worldmap getWorld() {
		return w;
	}
}
