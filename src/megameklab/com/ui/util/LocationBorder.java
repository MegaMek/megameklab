package megameklab.com.ui.util;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Path2D;

public class LocationBorder extends AbstractBorder {

    private final static int cl = 40;
    private final static int hcl = 10;
    private final static int hch = 5;

    /** Thickness of the border. */
    protected float thickness;

    /** Color of the border. */
    protected Color lineColor;

    public LocationBorder(Color color, float thickness)  {
        this.thickness = thickness;
        lineColor = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if ((this.thickness > 0) && (g instanceof Graphics2D)) {
            Graphics2D g2d = (Graphics2D) g;
            Color oldColor = g2d.getColor();
            Stroke oldStroke = g2d.getStroke();
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(this.lineColor);
            g2d.setStroke(new BasicStroke(thickness));

            Path2D.Float line = new Path2D.Float();
            float xc = x + thickness;
            float xw = x + width - thickness;
            float yc = y + thickness;
            float yh = y + height - thickness;
            line.moveTo(xc, yc + hch);
            line.curveTo(xc + hcl, yc, xc + hcl, yc, xc + cl, yc);
            line.lineTo(xw - cl, yc);
            line.curveTo(xw - hcl, yc, xw - hcl, yc, xw, yc + hch);
            line.lineTo(xw, yh - hch);
            line.curveTo(xw - hcl, yh, xw - hcl, yh, xw - cl, yh);
            line.lineTo(xc + cl, yh);
            line.curveTo(xc + hcl, yh, xc + hcl, yh, xc, yh - hch);
            line.closePath();
            g2d.draw(line);

            g2d.setStroke(oldStroke);
            g2d.setColor(oldColor);
        }
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(5, (int) (2 * thickness), 5, (int) (2 * thickness));
        return insets;
    }

}
