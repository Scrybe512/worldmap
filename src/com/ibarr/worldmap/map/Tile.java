package com.ibarr.worldmap.map;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Integer.max;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	protected final Set<Tile> neighbours;

	public Tile(MapGrid m, int x, int y, int w, int h) {
		this.m = m;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		neighbours = new HashSet<>();
		getNeighbours().forEach(t -> {
			t.addNeighbour(this);
		});
		if(m.getWorld().getSettings().getDebugLevel() > 2) {
			Logger.getLogger("Debug").log(Level.INFO, "Tile created at ({0},{1}) with {2} neighbours", new Object[] {x, y, neighbours.size()});
		}
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

	public final Set<Tile> getNeighbours() {
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
	
	public void addNeighbour(Tile t) {
		neighbours.add(t);
	}
	
	public void generateMissingNeighbours(boolean recurse) {
		Set<Tile> newNeighbours = new HashSet<>();
		for(int xx = max(0, x-1); xx < x+2 && xx < m.getCols(); xx++) {
			for(int yy = max(0, y-1); yy < y + 2 && yy < m.getRows(); yy++) {
				if(m.getTile(xx, yy) == null) {
					Tile t = getNewTile(m, xx, yy, w, h);
					m.setTile(xx, yy, t);
					newNeighbours.add(t);
				}
			}
		}
		if(recurse) {
			newNeighbours.forEach(t -> {
				t.generateMissingNeighbours(recurse);
			});
		}
	}
	
	protected Tile getNewTile(MapGrid m, int x, int y, int w, int h) {
		return new Tile(m, x, y, w, h);
	}
	
}
