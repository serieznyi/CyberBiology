package org.cyberbiology.domain;

public class Size {
    private double width;
    private double height;

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public final double getWidth() {
        return this.width;
    }

    public final double getHeight() {
        return this.height;
    }
}
