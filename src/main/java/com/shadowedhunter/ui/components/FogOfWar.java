package com.shadowedhunter.ui.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class FogOfWar {

    /**
     * Covers the (width x height) area in darkness, except for a soft circle of light.
     *
     * @param centerX centre of the light, in canvas coordinates
     * @param centerY centre of the light, in canvas coordinates
     * @param radius distance at which the fog becomes fully opaque
     */
    public void render(
            GraphicsContext gc,
            double width,
            double height,
            double centerX,
            double centerY,
            double radius) {
        if (width <= 0 || height <= 0) return;

        // Clear at the centre, fading to solid black at the radius and beyond
        gc.setFill(
                new RadialGradient(
                        0,
                        0,
                        centerX,
                        centerY,
                        radius,
                        false,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, Color.TRANSPARENT),
                        new Stop(1, Color.BLACK)));
        gc.fillRect(0, 0, width, height);
    }
}
