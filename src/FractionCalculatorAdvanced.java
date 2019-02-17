import java.util.Scanner;

public class FractionCalculatorAdvanced {

    static {
        System.out.println("This is a fraction calculator" +
                "\nIt will add, subtract, multiply and divide fractions until you type Q to quit." +
                "\nValid operations are of the form \"[FRAC] [OPERATION] [FRAC]\"." +
                "\n[FRAC] can be either a single integer or two integers separated by \"/\"." +
                "\n[OPERATION] can be +, -, *, / or =.");
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("Please enter an operation. (Q to quit): ");
            choice = getOperation(in);

            String aFrac = choice.substring(0, choice.indexOf(" "));
            String bFrac = choice.substring(choice.lastIndexOf(" ") + 1, choice.length());
            choice = choice.substring(choice.indexOf(" ") + 1,choice.lastIndexOf(" "));
            Fraction a = getFraction(aFrac);
            Fraction b = getFraction(bFrac);

            Fraction sum = new Fraction();
            if (choice.equals("=")) {
                System.out.printf("%s %s %s = %s", a.toString(), choice, b.toString(),
                        "is " + String.valueOf(a.equals(b)) + "\n");
            } else {
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

        } while (!choice.equalsIgnoreCase("Q"));
        in.close();
    }

    private static void printF(Fraction a, Fraction b, String op, String r) {
        System.out.printf("%s %s %s = %s\n", a.toString(), op, b.toString(), r);
    }


    public static String getOperation(Scanner in) {
        boolean valid = true;
        String op;
        do {
            op = in.nextLine();
            if (op.equalsIgnoreCase("q")) {
                System.exit(0);
            } else if (!op.matches("(([0-9])+[/]?([0-9])?)+( [-+/*=] )([0-9]+[/]?[0-9]?)+"))  {
                valid = false;
                System.out.print("Invalid operation. Must be a " +
                        "\"[FRAC] [OPERATION] [FRAC]\"" +
                        "\nEnter an operation (q to quit)");
            } else
                valid = true;
        } while (!valid);
        return op;
    }


    private static Fraction getFraction(String fraction) {

        if (fraction.matches("[-+]?[0-9]+")) {
            return new Fraction(Integer.parseInt(fraction));
        }
        int num, den;
        num = Integer.parseInt(fraction.substring(0, fraction.indexOf("/")));
        den = Integer.parseInt(fraction.substring(fraction.indexOf("/") + 1,
                fraction.length()));
        return new Fraction(num, den);
    }
}


