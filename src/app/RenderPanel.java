package app;

import renderer.SoftwareRenderer;
import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    private final SoftwareRenderer renderer;

    public RenderPanel(SoftwareRenderer r){
        this.renderer=r;
        setBackground(new Color(30,30,35));
        setPreferredSize(new Dimension(800,600));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(renderer.getCanvas(),0,0,null);
    }
}
