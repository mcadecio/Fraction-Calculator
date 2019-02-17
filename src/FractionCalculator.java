public class FractionCalculator {

    public static void main(String[] args){
        System.out.println(gcd(105, 147));
    }
    public static int gcd(int num, int den){

        while (num != 0|| den != 0){
            int remainder = num % den;
            num = den;
            den = remainder;
        }
        return num;
    }
}
