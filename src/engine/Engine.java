package engine;

import javax.swing.*;


public class Engine implements Runnable {

    private boolean running = true;
    private Scene scene;

    public Engine(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        long last = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            double delta = (now - last) / 1_000_000_000.0;
            last = now;

            scene.update(delta);
            scene.render();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
