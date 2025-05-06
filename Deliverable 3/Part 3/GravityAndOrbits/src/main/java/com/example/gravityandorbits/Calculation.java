package com.example.gravityandorbits;

import java.util.List;

public class Calculation {

    //universal gravitational constant
    private static final double G = 10;

    //compute net force on each planet, then update their respective acceleration
    public static void computeAccelerations(List<Planet> planets) {
        for (Planet p : planets) {
            Vector netForce = new Vector(0, 0);
            for (Planet other : planets) {
                if (p != other) {
                    Vector delta = other.getPosition().subtract(p.getPosition());
                    double distSq = delta.magnitudeSquared();
                    if (distSq > 0) {
                        double forceMag = G * p.getMass() * other.getMass() / distSq;
                        netForce = netForce.add(delta.normalize().multiply(forceMag));
                    }
                }
            }
            Vector acceleration = netForce.divide(p.getMass());
            p.setAcceleration(acceleration);
        }
    }

    //for all planets, apply velocity & position updates according to their own acceleration
    public static void applyMotion(List<Planet> planets, double dt) {
        for (Planet p : planets) {
            // v = v + a*dt
            Vector newV = p.getVelocity().add(p.getAcceleration().multiply(dt));
            p.setVelocity(newV);

            // x = x + v*dt
            Vector newPos = p.getPosition().add(newV.multiply(dt));
            p.setPosition(newPos);
        }
    }

    //wrapper method
    public static void updateAll(List<Planet> planets, double dt) {
        computeAccelerations(planets);
        applyMotion(planets, dt);
    }
}
