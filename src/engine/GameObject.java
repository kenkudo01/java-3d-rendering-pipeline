package engine;

import mesh.Mesh;
import math.Vec3;

public abstract class GameObject {

    protected Mesh mesh;
    protected Vec3 position = new Vec3(0, 0, 0);
    protected Vec3 rotation = new Vec3(0, 0, 0);

    public abstract void update(double delta);
    public abstract void render();
}
