package math;

public class Matrix4 {

    public double[][] m = new double[4][4];

    // =====================
    // 単位行列
    // =====================
    public static Matrix4 identity() {
        Matrix4 r = new Matrix4();
        for (int i = 0; i < 4; i++) {
            r.m[i][i] = 1.0;
        }
        return r;
    }

    // =====================
    // 行列 × 行列
    // =====================
    public Matrix4 multiply(Matrix4 o) {
        Matrix4 r = new Matrix4();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int k = 0; k < 4; k++) {
                    r.m[row][col] += this.m[row][k] * o.m[k][col];
                }
            }
        }
        return r;
    }

    // =====================
    // 行列 × Vec3
    // =====================
    public Vec3 multiply(Vec3 v) {
        double x =
                m[0][0]*v.x + m[0][1]*v.y + m[0][2]*v.z + m[0][3];
        double y =
                m[1][0]*v.x + m[1][1]*v.y + m[1][2]*v.z + m[1][3];
        double z =
                m[2][0]*v.x + m[2][1]*v.y + m[2][2]*v.z + m[2][3];
        return new Vec3(x, y, z);
    }

    // =====================
    // 変換行列生成
    // =====================
    public static Matrix4 translation(Vec3 t) {
        Matrix4 r = identity();
        r.m[0][3] = t.x;
        r.m[1][3] = t.y;
        r.m[2][3] = t.z;
        return r;
    }

    public static Matrix4 scale(Vec3 s) {
        Matrix4 r = identity();
        r.m[0][0] = s.x;
        r.m[1][1] = s.y;
        r.m[2][2] = s.z;
        return r;
    }

    public static Matrix4 rotationY(double rad) {
        Matrix4 r = identity();
        double c = Math.cos(rad);
        double s = Math.sin(rad);

        r.m[0][0] =  c;
        r.m[0][2] = -s;
        r.m[2][0] =  s;
        r.m[2][2] =  c;
        return r;
    }
}
