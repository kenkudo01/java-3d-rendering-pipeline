package engine;

import math.Matrix4;
import math.Vec3;

public class Transform {

    public Vec3 position = new Vec3(0, 0, 0);
    public Vec3 rotation = new Vec3(0, 0, 0);
    public Vec3 scale    = new Vec3(1, 1, 1);

    public Matrix4 getMatrix() {
        Matrix4 t = Matrix4.translation(position);
        Matrix4 r = Matrix4.rotationY(rotation.y);
        Matrix4 s = Matrix4.scale(scale);

        // ⚠ 順序重要: T × R × S
        return t.multiply(r).multiply(s);
    }
}
