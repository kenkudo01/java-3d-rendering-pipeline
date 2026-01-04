package engine;

import camera.Camera;
import math.Matrix4;
import math.Vec3;
import mesh.Edge;
import mesh.Mesh;
import renderer.SoftwareRenderer;

public class MeshObject extends GameObject {

    private final Mesh mesh;
    private final SoftwareRenderer renderer;
    private final Camera camera;

    private double speedY = 1.0;   // 移動速度
    private double minY = 0.0;
    private double maxY = 2.0;
    private double time = 0;

    public MeshObject(Mesh mesh, SoftwareRenderer renderer, Camera camera) {
        this.mesh = mesh;
        this.renderer = renderer;
        this.camera = camera;
    }

    @Override
    public void update(double dt) {
        time += dt;
        // 回転
        transform.rotation.y += dt;

        // 移動
        transform.position.y = 1.0 + Math.sin(time) * 0.5;

        // 上限・下限で反転
        if (transform.position.y > maxY) {
            transform.position.y = maxY;
            speedY *= -1;
        }
        if (transform.position.y < minY) {
            transform.position.y = minY;
            speedY *= -1;
        }
    }

    @Override
    public void render() {
        Matrix4 model = transform.getMatrix();

        for (Edge e : mesh.edges) {
            Vec3 p0 = model.multiply(e.v0.position);
            Vec3 p1 = model.multiply(e.v1.position);

            renderer.drawWorldLine(p0, p1, camera);
        }
    }
}
