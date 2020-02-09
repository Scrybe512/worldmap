package com.ibarr.worldmap;

import com.ibarr.worldmap.display.Display;
import com.ibarr.worldmap.map.MapGrid;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iain
 */
public class Worldmap implements Runnable {

	private Settings settings;

	private boolean running = false;
	private Thread thread;

	public final String title;
	private Display display;
	private BufferStrategy bs;
	private Graphics g;

	private MapGrid map;
	
	public Worldmap(String title) {
		this.title = title;
	}

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(Worldmap.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void init() {
		settings = new Settings();

		display = new Display(title, settings.getWindowWidth(), settings.getWindowHeight());
		
		map = new MapGrid(this);
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, settings.getWindowWidth(), settings.getWindowHeight());
        
//        if(State.getState() != null) {
//            State.getState().render(g);
//        }

		map.render(g);
        
        bs.show();
        g.dispose();
	}

	private void tick() {

	}

	@Override
	public void run() {
		init();
		render();

		int fps = settings.getFps();
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		int ticks = 0;
		int timer = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println(ticks + " fps");
				ticks = 0;
				timer = 0;
			}
		}

		render();
		stop();

	}

	public Settings getSettings() {
		return settings;
	}
}
