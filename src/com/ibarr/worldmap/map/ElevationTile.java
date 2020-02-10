package com.ibarr.worldmap.map;

import java.awt.Color;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Iterator;

/**
 *
 * @author Iain
 */
public class ElevationTile extends Tile {

	private final int MAX_ELEVATION = 10;
	private final int MIN_ELEVATION = -10;

	private final Color SEA10 = new Color(0, 0, 102);		// dark blue
	private final Color SEA9 = new Color(0, 0, 153);
	private final Color SEA8 = new Color(0, 0, 204);
	private final Color SEA7 = new Color(0, 0, 255);
	private final Color SEA6 = new Color(0, 102, 204);
	private final Color SEA5 = new Color(0, 128, 255);
	private final Color SEA4 = new Color(51, 153, 255);
	private final Color SEA3 = new Color(102, 178, 255);
	private final Color SEA2 = new Color(153, 204, 255);
	private final Color SEA1 = new Color(204, 204, 255);		// light blue
	private final Color LAND0 = new Color(153, 255, 153);		// green
	private final Color LAND1 = new Color(76, 153, 0);
	private final Color LAND2 = new Color(102, 102, 0);
	private final Color LAND3 = new Color(102, 51, 0);		// brown
	private final Color LAND4 = new Color(102, 72, 41);
	private final Color LAND5 = new Color(102, 87, 72);
	private final Color LAND6 = new Color(96, 96, 96);
	private final Color LAND7 = new Color(128, 128, 128);		// grey
	private final Color LAND8 = new Color(192, 192, 192);
	private final Color LAND9 = new Color(224, 224, 224);
	private final Color LAND10 = new Color(255, 255, 255);	// white

	private final int elevation;

	public ElevationTile(MapGrid m, int x, int y, int w, int h, int elevation) {
		super(m, x, y, w, h);
		this.elevation = elevation;
	}

	public ElevationTile(MapGrid m, int x, int y, int w, int h) {
		super(m, x, y, w, h);
		// get highest and lowest neighbour
		if (neighbours.isEmpty()) {
			this.elevation = 0;
		}
		else {
			int highest = -99;
			int lowest = 99;
			Iterator it = neighbours.iterator();
			while (it.hasNext()) {
				ElevationTile t = (ElevationTile) it.next();
				int e = t.getElevation();
				if (e > highest) {
					highest = e;
				}
				if (e < lowest) {
					lowest = e;
				}
			}

			// if high is 1 more than low then 50% of either
			if (highest == lowest + 1) {
				this.elevation = min(lowest + m.getRng().nextInt(1), MAX_ELEVATION);
			}
			// if high is 2+ more than low then pick median
			else if (highest > lowest + 1) {
				this.elevation = min(lowest + ((highest - lowest) / 2), MAX_ELEVATION);
			}
			// if high = low then can vary either way by 1 based on chance
			else {
				if (m.getRng().nextInt(m.getWorld().getSettings().getElevationChangeProbability()) == 0) {
					if (m.getRng().nextBoolean()) {
						this.elevation = min(lowest + 1, MAX_ELEVATION);
					}
					else {
						this.elevation = max(lowest - 1, MIN_ELEVATION);
					}
				}
				else {
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
		switch (elevation) {
			case -10:
				return SEA10;
			case -9:
				return SEA9;
			case -8:
				return SEA8;
			case -7:
				return SEA7;
			case -6:
				return SEA6;
			case -5:
				return SEA5;
			case -4:
				return SEA4;
			case -3:
				return SEA3;
			case -2:
				return SEA2;
			case -1:
				return SEA1;
			case 0:
				return LAND0;
			case 1:
				return LAND1;
			case 2:
				return LAND2;
			case 3:
				return LAND3;
			case 4:
				return LAND4;
			case 5:
				return LAND5;
			case 6:
				return LAND6;
			case 7:
				return LAND7;
			case 8:
				return LAND8;
			case 9:
				return LAND9;
			case 10:
				return LAND10;
		}
		return Color.BLACK;	// this is the default colour, but should never get used
	}

	@Override
	protected ElevationTile getNewTile(MapGrid m, int x, int y, int w, int h) {
		return new ElevationTile(m, x, y, w, h);
	}

}
