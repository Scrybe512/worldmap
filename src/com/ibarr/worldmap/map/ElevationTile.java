package com.ibarr.worldmap.map;

import java.awt.Color;

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
	
	@Override
	protected Color getColor() {
		if(elevation > 0) {
			return Color.DARK_GRAY;
		} else if(elevation < 0) {
			return Color.BLUE;
		}
		return Color.WHITE;	// this is the default colour
	}

	@Override
	protected ElevationTile getNewTile(MapGrid m, int x, int y, int w, int h) {
		return new ElevationTile(m, x, y, w, h, 1);
	}
	
}
