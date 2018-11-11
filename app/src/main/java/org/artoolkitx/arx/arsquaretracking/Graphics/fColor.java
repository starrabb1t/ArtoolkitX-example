package org.artoolkitx.arx.arsquaretracking.Graphics;

public class fColor {

    public fColor(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public fColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 1.0F;
    }

    public float red;
    public float green;
    public float blue;
    public float alpha;
}
