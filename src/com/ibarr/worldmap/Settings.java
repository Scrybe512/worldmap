package com.ibarr.worldmap;

/**
 *
 * @author iain
 */
public class Settings {

	private int windowWidth = 500;		// width in pixels
	private int windowHeight = 500;		// height in pixels
	private int mapColumns = 100;		// number of columns in the map
	private int mapRows = 100;			// number of rows in the map
	private int fps = 60;				// speed of program
	private boolean useDiagonals = true;

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

}
