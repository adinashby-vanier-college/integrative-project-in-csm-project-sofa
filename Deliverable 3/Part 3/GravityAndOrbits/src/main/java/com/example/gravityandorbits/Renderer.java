package com.example.gravityandorbits;

import javafx.scene.paint.Color;

public class Renderer {
    //Put code for drawing planets here

    public void drawPlanet() {

    }

    public void showGridLines() {
        UI ui = new UI();
        ui.gc.setStroke(new Color(1.0, 1.0, 1.0, 0.5));
        ui.gc.setLineWidth(2);
        int gridSize=25;

        for (int x = 5; x <= ui.canvas.getWidth(); x=x+gridSize) {
            ui.gc.strokeLine(x, 0, x, ui.canvas.getHeight());
        }
        for (int y = 5; y <= ui.canvas.getHeight(); y=y+gridSize) {
            ui.gc.strokeLine(0,y ,ui.canvas.getWidth(),y );

        }

    }
}
