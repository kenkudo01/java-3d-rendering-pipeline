package camera;

import mesh.Vec3;

public class Camera {

    public Vec3 position;
    public double fov;      // 視野角（ラジアン）
    public double near;     // 近クリップ（今回は軽く使うだけ）

    public Camera(Vec3 position, double fov) {
        this.position = position;
        this.fov = fov;
        this.near = 0.1;
    }
}
