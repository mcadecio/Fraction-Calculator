import java.util.Scanner;

public class FractionCalculator {

    static {
        System.out.println("This is a fraction calculator" +
                "\nIt will add, subtract, multiply and divide fractions until you type Q to quit." +
                "\nPlease enter you fraction in the form a/b, where a and b are integers.");
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("Please enter an Operation" +
                    " (+, -, /, *, = or Q to quit): ");
            choice = getOperation(in);
            Fraction a = getFraction(in);
            Fraction b = getFraction(in);

            Fraction sum = new Fraction();
            if (choice.equals("=")){
                System.out.printf("%s %s %s = %s", a.toString(), choice, b.toString(),
                        "is " + String.valueOf(a.equals(b)) + "\n");
            }else {
                switch (choice) {
                    case "+":
                        sum = a.add(b);
                        break;
                    case "-":
                        sum = a.sub(b);
                        break;
                    case "/":
                        sum = a.divide(b);
                        break;
                    case "*":
                        sum = a.multiply(b);
                        break;
                }
                sum.toLowestTerms();
                printF(a, b, choice, sum.toString());
            }

        }while (!choice.equalsIgnoreCase("Q"));
        in.close();
    }

    private static void printF(Fraction a, Fraction b, String op, String r){
        System.out.printf("%s %s %s = %s\n", a.toString(), op, b.toString(), r);
    }


    public static String getOperation(Scanner in){
        boolean valid = true;
        String op;
        do {
            op = in.next();
            if (!op.matches("[-+/*=Qq]")){
                valid = false;
                System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            }else if (op.equalsIgnoreCase("q")){
                System.exit(0);
            }else
                valid = true;
        }while (!valid);
        return op;
    }

    public static boolean validFraction(String input) {
        if (input.charAt(0) == '-' || input.charAt(0) == '+') {
            input = input.substring(1, input.length());
        }

        if (!isValid(input)) return false;

        int den;
        if (input.contains("/")) {
            den = Integer.parseInt(input.substring(input.indexOf("/") + 1,
                    input.length()));
            if (den == 0) return false;
        }
        return true;
    }

    private static boolean isValid(String s) {
        if (s.equals("") || s.equals(null)) return false;
//        if (s.contains("-")) {
//            return false;
//        }
        if (s.matches("((([-+]?[0-9]?)+)(\\/[-+]?[0-9]+)?)")) {
            return true;
        }
        return false;
    }

    private static Fraction getFraction(Scanner in){
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String fraction = in.next();
        while (!validFraction(fraction)) {
            System.out.print("Invalid fraction." +
                    " Please enter (a/b) or (a), where a and b are integers " +
                    "and b is not zero: ");
            fraction = in.next();

        }
        if (fraction.matches("[-+]?[0-9]+")){
            return new Fraction(Integer.parseInt(fraction));
        }
        int num, den;
        num = Integer.parseInt(fraction.substring(0, fraction.indexOf("/")));
        den = Integer.parseInt(fraction.substring(fraction.indexOf("/") + 1,
                fraction.length()));
        return new Fraction(num, den);
    }
}
