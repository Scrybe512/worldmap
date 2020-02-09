package com.ibarr.worldmap.map;

/**
 *
 * @author Iain
 */
public class ElevationTile extends Tile {
	
	private final int elevation;
	
	public ElevationTile(MapGrid m, int x, int y, int w, int h, int elevation) {
		super(m, x, y, w, h);
		this.elevation = elevation;
	}

	public int getElevation() {
		return elevation;
	}
	
}
