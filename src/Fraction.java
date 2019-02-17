public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int num, int den){

        if (den == 0){
            throw new IllegalArgumentException("Invalid");
        }else if (den < 0){
            num *= -1;
            den = Math.abs(den);
        }
        this.numerator = num;
        this.denominator = den;
    }

    public Fraction(int num){
        this.numerator = num;
        this.denominator = 1;
    }

    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    public double toDouble(){
        return numerator / denominator;
    }

    public Fraction add(Fraction other){
        int den = denominator, num = numerator;
        if (denominator != other.denominator){
            den = denominator * other.denominator;
            other.numerator = other.numerator * denominator;
            num = (numerator * other.denominator);
        }
        num = num + other.numerator;
        return new Fraction(num,den);
    }

    public Fraction sub(Fraction other){
        int den = denominator, num = numerator;
        if (denominator != other.denominator){
            den = denominator * other.denominator;
            num = (numerator * other.denominator);
        }
        num = num - other.numerator;
        return new Fraction(num,den);
    }

    public Fraction multiply(Fraction other){
        int den = denominator * other.denominator;
        int num = numerator * other.numerator;
        return new Fraction(num, den);
    }

    public Fraction divide(Fraction other){
        if (other.numerator == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        int num = numerator * other.denominator;
        int den = denominator * other.numerator;
        return new Fraction(num, den);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        if (other.toDouble() != this.toDouble())
            return false;
        return true;
    }

    public void toLowestTerms(){
        int gcd = gcd(this.numerator, this.denominator);
        this.numerator = this.numerator / gcd;
        this.denominator = this.denominator /gcd;

    }

    public static int gcd(int num, int den){

        while (num != 0 && den != 0){
            int remainder = num % den;
            num = den;
            den = remainder;
        }
        return num;
    }
}
