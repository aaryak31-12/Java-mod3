import java.math.BigInteger;
import java.util.Scanner;

public class Exercise13_15 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // entering the first rational number
        System.out.print(
            "Enter rational r1 with numerator and denominator separated by a space: ");

        BigInteger numerator1 = input.nextBigInteger();
        BigInteger denominator1 = input.nextBigInteger();

        Rational r1 = new Rational(numerator1, denominator1);

        // entering the second rational number
        System.out.print(
            "Enter rational r2 with numerator and denominator separated by a space: ");

        BigInteger numerator2 = input.nextBigInteger();
        BigInteger denominator2 = input.nextBigInteger();

        Rational r2 = new Rational(numerator2, denominator2);

        // displaying addition, subtraction, multiplication, and division
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));

        // displaying the decimal value of r2
        System.out.println(r2 + " is " + r2.doubleValue());
    }


    
}






// rational class
class Rational extends Number implements Comparable<Rational> {

    private BigInteger numerator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    public Rational(BigInteger numerator, BigInteger denominator) {

        BigInteger gcd = numerator.gcd(denominator);

        if (denominator.compareTo(BigInteger.ZERO) > 0) {
            this.numerator = numerator.divide(gcd);
        }
        else {
            this.numerator = numerator.divide(gcd).negate();
        }

        this.denominator = denominator.abs().divide(gcd);
    }

    //returning numerator
    public BigInteger getNumerator() {
        return numerator;
    }

    //returning denomerator
    public BigInteger getDenominator() {
        return denominator;
    }

    //adding  a rational number to this rational
    public Rational add(Rational secondRational) {

        BigInteger n =
            numerator.multiply(secondRational.getDenominator())
            .add(denominator.multiply(secondRational.getNumerator()));

        BigInteger d =
            denominator.multiply(secondRational.getDenominator());

        return new Rational(n, d);
    }

    // subracting a rational number from this rational
    public Rational subtract(Rational secondRational) {

        BigInteger n =
            numerator.multiply(secondRational.getDenominator())
            .subtract(denominator.multiply(secondRational.getNumerator()));

        BigInteger d =
            denominator.multiply(secondRational.getDenominator());

        return new Rational(n, d);
    }

    //multiplying
    public Rational multiply(Rational secondRational) {

        BigInteger n =
            numerator.multiply(secondRational.getNumerator());

        BigInteger d =
            denominator.multiply(secondRational.getDenominator());

        return new Rational(n, d);
    }

    //dividing
    public Rational divide(Rational secondRational) {

        BigInteger n =
            numerator.multiply(secondRational.getDenominator());

        BigInteger d =
            denominator.multiply(secondRational.getNumerator());

        return new Rational(n, d);
    }

    @Override
    public String toString() {

        if (denominator.equals(BigInteger.ONE))
            return numerator + "";
        else
            return numerator + "/" + denominator;
    }

    // Overriding the equals method in the Object class
    @Override 
    public boolean equals(Object other) {

        if (this.subtract((Rational) other)
                .getNumerator().equals(BigInteger.ZERO))
            return true;
        else
            return false;
    }

    //implementing the abstract intValue method in number
    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    //implementing the abstract floatValue method in number
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    //implementing the doubleValue method in number
    @Override
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    //implementing the abstract longValue method in number
    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    //implementing the compareTO method in comparable
    @Override
    public int compareTo(Rational o) {

        BigInteger result = this.subtract(o).getNumerator();

        if (result.compareTo(BigInteger.ZERO) > 0)
            return 1;
        else if (result.compareTo(BigInteger.ZERO) < 0)
            return -1;
        else
            return 0;
 
 
 
    }












}