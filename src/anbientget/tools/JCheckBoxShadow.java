package tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;

public class JCheckBoxShadow extends javax.swing.JCheckBox {

    private Color shadowColor;

    public JCheckBoxShadow(String text) {
        super(text);
        setInitialBackgroundColor();
    }

    public JCheckBoxShadow(Icon icon) {
        super(icon);
        setInitialBackgroundColor();
    }

    public JCheckBoxShadow() {
        setInitialBackgroundColor();
    }

    private final void setInitialBackgroundColor() {
        setShadowColor(Color.BLACK);
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D graphics = (Graphics2D) g;
// Remember current graphics parameters
        final Object oldTextAntialiasingHint = graphics.getRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING);
        final Color oldForeground = getForeground();
// Set rendering quality
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        {// Paint the shadow
            final Graphics2D shadowGraphics = (Graphics2D) graphics.create();
            shadowGraphics.translate(getShadowOFFSET_X(), getShadowOFFSET_Y());
            shadowGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.7f));
            setForeground(this.getShadowColor());
            super.paintComponent(shadowGraphics);
            shadowGraphics.dispose();
        }
// Paint the text
        setForeground(oldForeground);
        super.paintComponent(graphics);
// Restore rendering quality
        if (null != oldTextAntialiasingHint) {
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    oldTextAntialiasingHint);
        } else {
// do nothing
        }
    }
    private int shadowOFFSET_X = 1;
    private int shadowOFFSET_Y = 2;

    public int getShadowOFFSET_X() {
        return shadowOFFSET_X;
    }

    public void setShadowOFFSET_X(int shadowOFFSET_X) {
        this.shadowOFFSET_X = shadowOFFSET_X;
    }

    public int getShadowOFFSET_Y() {
        return shadowOFFSET_Y;
    }

    public void setShadowOFFSET_Y(int shadowOFFSET_Y) {
        this.shadowOFFSET_Y = shadowOFFSET_Y;
    }
}
