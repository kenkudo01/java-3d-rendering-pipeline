import camera.Camera;
import mesh.*;
import Renderer.Renderer;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel implements Runnable {

    // ===== 設定 =====
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // ===== CGエンジン構成要素 =====
    private Renderer renderer;
    private Camera camera;
    private Mesh mesh;

    // ===== 状態 =====
    private double angle = 0.0;

    public Main() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        renderer = new Renderer(WIDTH, HEIGHT);
        camera = new Camera(new Vec3(0, 0, -5), Math.toRadians(90));
        mesh = Mesh.createCube();
    }

    // =====================
    // ゲームループ
    // =====================
    @Override
    public void run() {
        while (true) {
            update();
            repaint();

            try {
                Thread.sleep(16); // 約60FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // =====================
    // 状態更新
    // =====================
    private void update() {
        angle += 0.01;

        // y軸回転（Meshの全Vertexを直接回す）
        for (Vertex v : mesh.vertices) {
            Vec3 p = v.position;

            double cos = Math.cos(angle);
            double sin = Math.sin(angle);

            double x = p.x * cos - p.z * sin;
            double z = p.x * sin + p.z * cos;

            v.position = new Vec3(x, p.y, z);
        }
    }

    // =====================
    // 描画
    // =====================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderer.clear();
        renderer.renderMesh(mesh, camera);

        g.drawImage(renderer.getCanvas(), 0, 0, null);
    }

    // =====================
    // エントリーポイント
    // =====================
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minimal Java CG Engine");
        Main panel = new Main();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(panel).start();
    }
}
