public class Circle extends GeometricObject
        implements Comparable<Circle> {

    // storing the radius of the circle
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    // Returning the radius
    public double getRadius() {
        return radius;
    }

    // Setting a new radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Returning the area of the circle
    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    // returning the diameter of the circle
    public double getDiameter() {
        return 2 * radius;
    }

    // returning the perimeter of the circle
    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    public void printCircle() {
        System.out.println("The circle is created " + getDateCreated()
                + " and the radius is " + radius);
    }

    // Comparing this circle with another circle by radius
    @Override
    public int compareTo(Circle o) {
        if (radius > o.radius) {
            return 1;
        }
        else if (radius < o.radius) {
            return -1;
        }
        else {
            return 0;
        }
    }

    // Checking whether two Circle objects are equal
    @Override
    public boolean equals(Object o) {
        if (o instanceof Circle) {
            return radius == ((Circle) o).radius;
        }
        else {
            return false;


        }

        
    }





}