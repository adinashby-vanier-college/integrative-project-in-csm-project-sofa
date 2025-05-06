package com.example.gravityandorbits;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Planet {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private double mass;
    private double radius;
    private Color color;
    private String planetName;
    public Image image;

    public Planet(String name, double x, double y, double mass, double radius, double vx, double vy) {
        this.planetName = name;
        this.position = new Vector(x, y);
        this.velocity = new Vector(vx, vy);
        this.acceleration = new Vector(0, 0);
        this.mass = mass;
        this.radius = radius;
        //this.color = Color.RED;
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

    // Setters
    public void setPosition(Vector position) { this.position = position; }
    public void setVelocity(Vector velocity) { this.velocity = velocity; }
    public void setAcceleration(Vector acceleration) { this.acceleration = acceleration; }
    public void setMass(double mass) { this.mass = mass; }
    public void setRadius(double radius) { this.radius = radius; }
    public void setColor(Color color) { this.color = color; }
    public void setVelFromDrag(double vx, double vy) {this.velocity = new Vector(vx/Renderer.VEL_SCALE, vy/Renderer.VEL_SCALE);}
}