package com.ibarr.worldmap.map;

import com.ibarr.pathfinder.Pathfinding;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author iain
 */
public class Tile {

	private final int x;
	private final int y;
	private final int w;
	private final int h;
	private final MapGrid m;
	private final Set<Tile> neighbours;

	public Tile(MapGrid m, int x, int y, int w, int h) {
		this.m = m;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		neighbours = new HashSet<>();
	}

	public void render(Graphics g) {
		g.setColor(getColor());
		g.fillRect(x * w, y * h, w, h);
		g.setColor(Color.BLACK);
		g.drawRect(x * w, y * h, w, h);

	}

	public void renderPath(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x * w, y * h, w, h);
		g.setColor(Color.BLACK);
		g.drawRect(x * w, y * h, w, h);

	}

	protected Color getColor() {
		return Color.WHITE;	// this is the default colour
	}

	public Set<Tile> getNeighbours() {
		if (neighbours.isEmpty()) {
			Tile t;
			t = m.getTile(x - 1, y);
			if (t != null) {
				neighbours.add(t);
			}
			t = m.getTile(x + 1, y);
			if (t != null) {
				neighbours.add(t);
			}
			t = m.getTile(x, y - 1);
			if (t != null) {
				neighbours.add(t);
			}
			t = m.getTile(x, y + 1);
			if (t != null) {
				neighbours.add(t);
			}
			if (m.getWorld().getSettings().isUseDiagonals()) {
				t = m.getTile(x - 1, y - 1);
				if (t != null) {
					neighbours.add(t);
				}
				t = m.getTile(x + 1, y - 1);
				if (t != null) {
					neighbours.add(t);
				}
				t = m.getTile(x - 1, y + 1);
				if (t != null) {
					neighbours.add(t);
				}
				t = m.getTile(x + 1, y + 1);
				if (t != null) {
					neighbours.add(t);
				}
			}
		}
		return neighbours;
	}
}
