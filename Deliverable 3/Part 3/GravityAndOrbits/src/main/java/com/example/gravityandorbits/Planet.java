package com.example.gravityandorbits;

import javafx.scene.paint.Color;

public class Planet {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private double mass;
    private double radius;
    private Color color;
    private String planetName;

    public Planet(String name, double x, double y, double mass, double radius, double vx, double vy) {
        this.planetName = name;
        this.position = new Vector(x, y);
        this.velocity = new Vector(vx, vy);
        this.acceleration = new Vector(0, 0);
        this.mass = mass;
        this.radius = radius;
        this.color = Color.RED;
    }

    // Getters
    public Vector getPosition() { return position; }
    public Vector getVelocity() { return velocity; }
    public Vector getAcceleration() { return acceleration; }
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
    public Color getColor() { return color; }

    // Setters
    public void setPosition(Vector position) { this.position = position; }
    public void setVelocity(Vector velocity) { this.velocity = velocity; }
    public void setAcceleration(Vector acceleration) { this.acceleration = acceleration; }
    public void setMass(double mass) { this.mass = mass; }
    public void setRadius(double radius) { this.radius = radius; }
    public void setColor(Color color) { this.color = color; }
}