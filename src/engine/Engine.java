package engine;

import camera.Camera;
import renderer.DebugDrawer;
import renderer.SoftwareRenderer;


public class Engine implements Runnable {

    private final Scene scene;
    private final SoftwareRenderer renderer;
    private final DebugDrawer debug;
    private final Camera camera;
    private Runnable repaint;

    public Engine(Scene scene,
                  SoftwareRenderer renderer,
                  DebugDrawer debug,
                  Camera camera) {
        this.scene = scene;
        this.renderer = renderer;
        this.debug = debug;
        this.camera = camera;
    }

    public void setRepaint(Runnable r) {
        repaint = r;
    }

    @Override
    public void run() {
        while (true) {

            // ① 前フレームを消す
            renderer.clear();

            // ② Grid（背景）
//            debug.drawGrid(camera, 20, 1.0);

            // ③ 軸
//            debug.drawAxes(camera);

            // ④ シーン更新 & 描画
            scene.update(0.016);
            scene.render();

            // ⑤ Swing に表示
            if (repaint != null) repaint.run();

            try {
                Thread.sleep(16);
            } catch (InterruptedException ignored) {}
        }
    }
}
