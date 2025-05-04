package com.example.gravityandorbits;

public class Vector {
    private final double x;
    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Accessors
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Add two vectors
    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    }

    // Subtract another vector
    public Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }

    // Multiply by scalar
    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    // Divide by scalar
    public Vector divide(double scalar) {
        if (scalar == 0) throw new ArithmeticException("Division by zero in vector division");
        return new Vector(this.x / scalar, this.y / scalar);
    }

    // Magnitude (length of the vector)
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Magnitude squared (faster, if you don't need sqrt)
    public double magnitudeSquared() {
        return x * x + y * y;
    }

    // Normalize vector (make it length 1)
    public Vector normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vector(0, 0);  // Avoid division by zero
        return divide(mag);
    }

    // Dot product
    public double dot(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    // Distance between two vectors
    public double distanceTo(Vector other) {
        return this.subtract(other).magnitude();
    }

    // String representation
    @Override
    public String toString() {
        return String.format("Vector(%.3f, %.3f)", x, y);
    }

    // Equals override
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }
}