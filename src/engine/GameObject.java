package engine;

import mesh.Mesh;
import math.Vec3;

public abstract class GameObject {

    public Transform transform = new Transform();

    public abstract void update(double delta);
    public abstract void render();
}
