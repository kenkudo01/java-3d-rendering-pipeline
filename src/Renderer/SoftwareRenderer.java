package Renderer;

import mesh.Edge;
import math.Vec3;
import camera.Camera;
import mesh.Mesh;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SoftwareRenderer {

    private int width, height;
    private BufferedImage canvas;

    public SoftwareRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        this.canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getCanvas() {
        return canvas;
    }

    public void clear() {
        Graphics2D g = canvas.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.dispose();
    }

    // =========================
    // 描画エントリーポイント
    // =========================
    public void renderMesh(Mesh mesh, Camera cam) {
        for (Edge e : mesh.edges) {
            Vec3 p0 = project(e.v0.position, cam);
            Vec3 p1 = project(e.v1.position, cam);

            if (p0 != null && p1 != null) {
                drawLine(
                        (int) p0.x, (int) p0.y,
                        (int) p1.x, (int) p1.y,
                        Color.WHITE
                );
            }
        }
    }
    private Vec3 project(Vec3 v, Camera cam) {
        // カメラ座標系へ変換（位置のみ）
        Vec3 p = v.sub(cam.position);

        // カメラの後ろは描かない
        if (p.z <= cam.near) return null;

        // 透視投影
        double f = 1.0 / Math.tan(cam.fov * 0.5);
        double x = (p.x * f) / p.z;
        double y = (p.y * f) / p.z;

        // 正規化 → スクリーン変換
        int sx = (int) ((x + 1) * 0.5 * width);
        int sy = (int) ((1 - y) * 0.5 * height);

        return new Vec3(sx, sy, p.z);
    }

    private void drawLine(int x0, int y0, int x1, int y1, Color c) {
        Graphics2D g = canvas.createGraphics();
        g.setColor(c);
        g.drawLine(x0, y0, x1, y1);
        g.dispose();
    }
}

