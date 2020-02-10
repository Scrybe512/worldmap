package com.ibarr.worldmap.map;

import java.awt.Color;
import java.util.Iterator;
import java.util.Random;

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

	public ElevationTile(MapGrid m, int x, int y, int w, int h) {
		super(m, x, y, w, h);
		// get highest and lowest neighbour
		if(neighbours.isEmpty()) {
			this.elevation = 0;
		} else {
			int highest = -99;
			int lowest = 99;
			Iterator it = neighbours.iterator();
			while(it.hasNext()) {
				ElevationTile t = (ElevationTile) it.next();
				int e = t.getElevation();
				if(e > highest) {
					highest = e;
				}
				if(e < lowest) {
					lowest = e;
				}
			}
			
			Random r = new Random();
			// if high is 1 more than low then 50% of either
			if(highest == lowest + 1) {
				this.elevation = lowest + r.nextInt(1);
			}
			// if high is 2+ more than low then pick median
			else if(highest > lowest + 1) {
				this.elevation = lowest + ((highest - lowest) / 2);
			}
			// if high = low then can vary either way by 1 based on chance
			else {
				if(r.nextInt(m.getWorld().getSettings().getElevationChangeProbability()) == 0) {
					if(r.nextBoolean()) {
						this.elevation = lowest + 1;
					} else {
						this.elevation = lowest - 1;
					}
				} else {
					this.elevation = lowest;
				}
			}
		}
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
		return new ElevationTile(m, x, y, w, h);
	}
	
}
