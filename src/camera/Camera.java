package camera;

import math.Vec3;

public class Camera {
    public Vec3 position;
    public double yaw = 0;    // 左右
    public double pitch = 0;  // 上下
    public double fov;

    public Camera(){

    }
    public Camera(Vec3 position, double fov) {
        this.position = position;
        this.fov = fov;
    }
}
