package common;

import java.awt.*;

public class RectDimension {
    private int x;
    private int y;
    private int width;
    private int height;

    private int relativeX;
    private int relativeY;
    private int relativeWidth;
    private int relativeHeight;

    Dimension screen;

    // gamescreen specific
    private int widthIndentRight;
    private int heightIndentBottom;

    public RectDimension(int x, int y, int width, int height, double relativeX, double relativeY, double relativeWidth, double relativeHeight, Dimension screen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.relativeX = (int) (screen.getWidth() * relativeX);
        this.relativeY = (int) (screen.getHeight() * relativeY);
        this.relativeWidth = (int) (screen.getWidth() * relativeWidth);
        this.relativeHeight = (int) (screen.getHeight() * relativeHeight);
        this.screen = screen;
    }

    // gamescreen constructor
    public RectDimension(int x, int y, int width, int height, double relativeX, double relativeY, double relativeWidth, double relativeHeight, int widthIndentRight, int heightIndentBottom, Dimension screen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.relativeX = (int) (screen.getWidth() * relativeX);
        this.relativeY = (int) (screen.getHeight() * relativeY);
        this.relativeWidth = (int) (screen.getWidth() * relativeWidth);
        this.relativeHeight = (int) (screen.getHeight() * relativeHeight);
        this.widthIndentRight = widthIndentRight;
        this.heightIndentBottom = heightIndentBottom;
        this.screen = screen;
    }

    public double getX() {
        return x;
    }

    public Dimension getScreen() {
        return screen;
    }

    public int getWidthIndentRight() {
        return widthIndentRight;
    }

    public int getHeightIndentBottom() {
        return heightIndentBottom;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRelativeX() {
        return relativeX;
    }

    public double getRelativeY() {
        return relativeY;
    }

    public double getRelativeWidth() {
        return relativeWidth;
    }

    public double getRelativeHeight() {
        return relativeHeight;
    }

    @Override
    public String toString() {
        return "RectDimension{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", relativeX=" + relativeX +
                ", relativeY=" + relativeY +
                ", relativeWidth=" + relativeWidth +
                ", relativeHeight=" + relativeHeight +
                '}';
    }
}
