package app;

import camera.Camera;
import engine.*;
import math.Vec3;
import mesh.Mesh;
import renderer.SoftwareRenderer;
import renderer.DebugDrawer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // ===== Renderer & Camera =====
        SoftwareRenderer renderer = new SoftwareRenderer(800, 600);
        Camera camera = new Camera(new Vec3(0, 3, -6), Math.toRadians(90));
        camera.position = new Vec3(0, 4, -7);
        camera.yaw = 0;
        camera.pitch = Math.toRadians(-0);


        // ===== Scene =====
        Scene scene = new Scene();
        scene.add(new MeshObject(Mesh.createCube(), renderer, camera));

        // ===== Debug =====
        DebugDrawer debug = new DebugDrawer(renderer);

        // ===== Engine =====
        Engine engine = new Engine(scene, renderer, debug, camera);

        // ===== Swing =====
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("CG Engine");
            RenderPanel p = new RenderPanel(renderer);

            f.add(p);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);

            engine.setRepaint(p::repaint);
            new Thread(engine).start();
        });
    }
}
