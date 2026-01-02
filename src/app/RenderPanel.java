package app;

import Renderer.SoftwareRenderer;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    private final SoftwareRenderer renderer;

    public RenderPanel(SoftwareRenderer renderer) {
        this.renderer = renderer;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(renderer.getCanvas(), 0, 0, null);
    }
}
