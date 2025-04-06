package com.example.gravityandorbits;

import javafx.scene.paint.Color;

public class Planet {
    double x, y;
    double mass;
    double velocityX, velocityY;
    javafx.scene.paint.Color color = Color.RED;

    Planet(double x, double y, double mass, double velocityX, double velocityY) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
