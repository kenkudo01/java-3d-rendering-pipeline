package app;

import camera.Camera;
import engine.Engine;
import engine.MeshObject;
import engine.Scene;
import math.Vec3;
import mesh.Mesh;
import Renderer.SoftwareRenderer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SoftwareRenderer renderer = new SoftwareRenderer(800, 600);
        Camera camera = new Camera(new Vec3(0,0,-5), Math.toRadians(90));

        Scene scene = new Scene();
        scene.add(new MeshObject(Mesh.createCube(), renderer, camera));

        Engine engine = new Engine(scene);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java CG Engine");
            RenderPanel panel = new RenderPanel(renderer);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // 🔴 エンジン開始はウィンドウ生成後
            new Thread(engine).start();
        });
    }
}
