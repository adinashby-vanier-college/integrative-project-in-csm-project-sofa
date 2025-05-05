package com.example.gravityandorbits;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Renderer {
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color GRID_COLOR       = new Color(1,1,1,0.3);
    private static final Color ACCEL_COLOR      = Color.LIGHTGREEN;
    private static final int   GRID_SPACING     = 25;
    private static final double ACCEL_SCALE     = 0.001;

    /**
     * Renders one frame:
     *  1) clear to black
     *  2) draw grid (if showGrid)
     *  3) draw each planet & its accel vector
     */
    public void renderFrame(GraphicsContext gc,
                            List<Planet> planets,
                            boolean showGrid,
                            double width,
                            double height) {
        clearCanvas(gc, width, height);
        if (showGrid) drawGrid(gc, width, height);
        drawPlanets(gc, planets);
    }

    private void clearCanvas(GraphicsContext gc, double w, double h) {
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, w, h);
    }

    private void drawGrid(GraphicsContext gc, double w, double h) {
        gc.setStroke(GRID_COLOR);
        gc.setLineWidth(1);
        for (int x = 0; x <= w; x += GRID_SPACING) {
            gc.strokeLine(x, 0, x, h);
        }
        for (int y = 0; y <= h; y += GRID_SPACING) {
            gc.strokeLine(0, y, w, y);
        }
    }

    private void drawPlanets(GraphicsContext gc, List<Planet> planets) {
        for (Planet p : planets) {
            double x = p.getPosition().getX();
            double y = p.getPosition().getY();
            double r = p.getRadius();

            // Planet body
            gc.setFill(p.getColor());
            gc.fillOval(x - r, y - r, r * 2, r * 2);

            // Acceleration vector
            Vector a = p.getAcceleration().multiply(ACCEL_SCALE);
            gc.setStroke(ACCEL_COLOR);
            gc.setLineWidth(1);
            gc.strokeLine(x, y, x + a.getX(), y + a.getY());
        }
    }
}
