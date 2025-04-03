package com.example.gravityandorbits;

public class Planet {
    double x, y;
    double mass;
    double velocityX, velocityY;

    Planet(double x, double y, double mass, double velocityX, double velocityY) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
}
