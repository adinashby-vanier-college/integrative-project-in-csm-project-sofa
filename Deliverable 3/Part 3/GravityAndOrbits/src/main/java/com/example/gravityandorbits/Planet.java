package com.example.gravityandorbits;

import javafx.scene.paint.Color;

public class Planet {
    double x, y;
    double mass;
    double radius;
    double velocity;
    double velocityX;
    double velocityY;
    javafx.scene.paint.Color color = Color.RED;

    Planet(double x, double y, double mass, double velocity, double radius) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.radius = radius;
        this.velocity = velocity;
        this.velocityX = velocity * Math.cos(0);
        this.velocityY = velocity * Math.sin(0);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
