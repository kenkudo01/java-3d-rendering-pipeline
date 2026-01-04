package renderer;

import camera.Camera;
import math.Vec3;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SoftwareRenderer {

    private static final Color BG_COLOR = new Color(30, 30, 35);
    private final BufferedImage canvas;

    public SoftwareRenderer(int w,int h){
        canvas = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage getCanvas(){ return canvas; }

    public void clear(){
        Graphics2D g = canvas.createGraphics();
        g.setColor(BG_COLOR); // ← ここ
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        g.dispose();
    }

    public Vec3 worldToScreen(Vec3 v, Camera cam) {

        // ① カメラ位置へ移動
        Vec3 p = v.sub(cam.position);

        // ② yaw（Y軸回転：左右）
        double cosY = Math.cos(-cam.yaw);
        double sinY = Math.sin(-cam.yaw);

        double x1 =  p.x * cosY - p.z * sinY;
        double z1 =  p.x * sinY + p.z * cosY;
        double y1 =  p.y;

        // ③ pitch（X軸回転：上下） ← ★ここが肝
        double cosP = Math.cos(-cam.pitch);
        double sinP = Math.sin(-cam.pitch);

        double y2 = y1 * cosP - z1 * sinP;
        double z2 = y1 * sinP + z1 * cosP;

        // ④ カメラの後ろは描かない
        if (z2 <= 0.1) return null;

        // ⑤ 透視投影
        double f = 1.0 / Math.tan(cam.fov * 0.5);
        double sx = (x1 * f / z2 + 1) * canvas.getWidth() * 0.5;
        double sy = (1 - y2 * f / z2) * canvas.getHeight() * 0.5;

        return new Vec3(sx, sy, z2);
    }



    public void drawWorldLine(Vec3 a, Vec3 b, Camera cam){
        Vec3 p0=worldToScreen(a,cam), p1=worldToScreen(b,cam);
        if(p0==null||p1==null) return;
        Graphics2D g=canvas.createGraphics();
        g.setColor(Color.WHITE);
        g.drawLine((int)p0.x,(int)p0.y,(int)p1.x,(int)p1.y);
        g.dispose();
    }
}
