package com.example.gravityandorbits;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private double mass;
    private double radius;
    private Color color;
    private String planetName;
    public Image image;
    private double baseMass;
    private List<Vector> orbitPath = new ArrayList<>();

    public Planet(String name, double x, double y, double mass, double radius, double vx, double vy) {
        this.planetName = name;
        this.position = new Vector(x, y);
        this.velocity = new Vector(vx, vy);
        this.acceleration = new Vector(0, 0);
        this.mass = mass;
        this.baseMass = mass; // Initialize baseMass once
        this.radius = radius;
        switch(name){
            case "Sun":
                this.image=new Image("sun.png");
                break;
            case "Earth":
                this.image=new Image("earth.png");
                break;
            case "Moon":
                this.image=new Image("moon.png");
                break;
            case "Mars":
                this.image=new Image("mars.png");
                break;
            case "Venus":
                this.image=new Image("venus.png");
                break;
            case "Neptune":
                this.image=new Image("neptune.png");
            default:
               this.color=Color.RED;
        }
    }

    // Getters
    public Vector getPosition() { return position; }
    public Vector getVelocity() { return velocity; }
    public Vector getAcceleration() { return acceleration; }
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
    public Color getColor() { return color; }
    public String getName() { return planetName; }
    public double getMassMultiplier() { return mass/baseMass; }
    public double getBaseMass() { return baseMass; }

    // Setters
    public void setPosition(Vector position) { this.position = position; }
    public void setVelocity(Vector velocity) { this.velocity = velocity; }
    public void setAcceleration(Vector acceleration) { this.acceleration = acceleration; }
    public void setMass(double mass) { this.mass = mass; }
    public void setRadius(double radius) { this.radius = radius; }
    public void setColor(Color color) { this.color = color; }
    public void setVelFromDrag(double vx, double vy) {this.velocity = new Vector(vx/Renderer.VEL_SCALE, vy/Renderer.VEL_SCALE);}


    public List<Vector> getOrbitPath() {
        return orbitPath;
    }
    public void updateOrbitPath() {
        orbitPath.add(new Vector(position.getX(), position.getY()));
        if (orbitPath.size() > 1000) {
            orbitPath.remove(0); // delete the first one to keep memory usage low
        }
    }
    // Override toString method
    @Override
    public String toString() {
        return this.planetName;
    }
}