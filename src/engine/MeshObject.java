package engine;

import Renderer.SoftwareRenderer;
import camera.Camera;
import mesh.Mesh;

public class MeshObject extends GameObject {

    private SoftwareRenderer renderer;
    private Camera camera;

    public MeshObject(Mesh mesh, SoftwareRenderer renderer, Camera camera) {
        this.mesh = mesh;
        this.renderer = renderer;
        this.camera = camera;
    }

    @Override
    public void update(double delta) {
        rotation.y += delta;
        // 回転処理（今は直書き）
    }

    @Override
    public void render() {
        renderer.renderMesh(mesh, camera);
    }
}
