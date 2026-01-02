package mesh;

public class Vec3 {
    public double x, y, z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 v) { return new Vec3(x+v.x, y+v.y, z+v.z); }
    public Vec3 sub(Vec3 v) { return new Vec3(x-v.x, y-v.y, z-v.z); }
    public Vec3 mul(double s) { return new Vec3(x*s, y*s, z*s); }
}
