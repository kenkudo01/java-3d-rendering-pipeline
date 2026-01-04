package renderer;

import camera.Camera;
import math.Vec3;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DebugDrawer {

    private final SoftwareRenderer renderer;
    private final BufferedImage canvas;

    // ===== 色定義（Blender風）=====
    private static final Color AXIS_X = new Color(255, 80, 80, 180);
    private static final Color AXIS_Y = new Color(80, 255, 80, 180);
    private static final Color AXIS_Z = new Color(80, 80, 255, 180);

    private static final Color GRID_MAJOR = new Color(200, 200, 200, 120);
    private static final Color GRID_MINOR = new Color(200, 200, 200, 60);

    public DebugDrawer(SoftwareRenderer renderer) {
        this.renderer = renderer;
        this.canvas = renderer.getCanvas();
    }

    // =========================
    // 軸描画
    // =========================
    public void drawAxes(Camera cam) {
        double len = 2.5;

        drawWorldLine(
                new Vec3(0, 0, 0),
                new Vec3(len, 0, 0),
                cam, AXIS_X
        );

        drawWorldLine(
                new Vec3(0, 0, 0),
                new Vec3(0, len, 0),
                cam, AXIS_Y
        );

        drawWorldLine(
                new Vec3(0, 0, 0),
                new Vec3(0, 0, len),
                cam, AXIS_Z
        );
    }

    // =========================
    // グリッド描画（XZ平面）
    // =========================
    public void drawGrid(Camera cam, int halfSize, double spacing) {

        for (int i = -halfSize; i <= halfSize; i++) {
            boolean major = (i % 5 == 0);
            Color color = major ? GRID_MAJOR : GRID_MINOR;

            double v = i * spacing;

            // X方向の線
            drawWorldLine(
                    new Vec3(-halfSize * spacing, 0, v),
                    new Vec3( halfSize * spacing, 0, v),
                    cam, color
            );

            // Z方向の線
            drawWorldLine(
                    new Vec3(v, 0, -halfSize * spacing),
                    new Vec3(v, 0,  halfSize * spacing),
                    cam, color
            );
        }
    }

    // =========================
    // ワールド座標線描画
    // =========================
    private void drawWorldLine(Vec3 from, Vec3 to, Camera cam, Color color) {

        Vec3 p0 = renderer.worldToScreen(from, cam);
        Vec3 p1 = renderer.worldToScreen(to, cam);

        if (p0 == null || p1 == null) return;

        Graphics2D g = canvas.createGraphics();
        g.setColor(color);
        g.drawLine((int) p0.x, (int) p0.y, (int) p1.x, (int) p1.y);
        g.dispose();
    }
}
