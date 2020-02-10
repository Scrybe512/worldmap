package com.ibarr.worldmap;

/**
 *
 * @author iain
 */
public class Settings {

	private int debugLevel = 1;			// 0 = off, 1 = minimal, 2 = more, 3 = everything
	private int windowWidth = 500;		// width in pixels
	private int windowHeight = 500;		// height in pixels
	private int mapColumns = 100;		// number of columns in the map
	private int mapRows = 100;			// number of rows in the map
	private int fps = 60;				// speed of program
	private boolean useDiagonals = true;
	private int elevationChangeProbability = 20;	// 1 in n chance of changing
	private long mapSeed = System.currentTimeMillis();

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public int getMapColumns() {
		return mapColumns;
	}

	public void setMapColumns(int mapColumns) {
		this.mapColumns = mapColumns;
	}

	public int getMapRows() {
		return mapRows;
	}

	public void setMapRows(int mapRows) {
		this.mapRows = mapRows;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public boolean isUseDiagonals() {
		return useDiagonals;
	}

	public void setUseDiagonals(boolean useDiagonals) {
		this.useDiagonals = useDiagonals;
	}

	public int getElevationChangeProbability() {
		return elevationChangeProbability;
	}

	public void setElevationChangeProbability(int elevationChangeProbability) {
		this.elevationChangeProbability = elevationChangeProbability;
	}

	public int getDebugLevel() {
		return debugLevel;
	}

	public void setDebugLevel(int debugLevel) {
		this.debugLevel = debugLevel;
	}

	public long getMapSeed() {
		return mapSeed;
	}

	public void setMapSeed(long mapSeed) {
		this.mapSeed = mapSeed;
	}

}
