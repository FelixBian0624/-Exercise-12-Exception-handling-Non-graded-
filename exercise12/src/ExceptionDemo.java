import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class MissingExtensionException extends Exception {
    public MissingExtensionException(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;
    private Scanner input = new Scanner(System.in);

    public void divide() {
        System.out.print("Enter a number for divisor: ");
        divisor = input.nextDouble();
        System.out.print("Enter a number for dividend: ");
        dividend = input.nextDouble();
        result = divisor / dividend;
        System.out.println("Result: " + result);
    }

    public void goToDivideMethod() {
        divide();
    }

    public void readAFile() throws IOException, MissingExtensionException {
        System.out.print("Enter the path to the file: ");
        String filePath = input.next();

        if (!filePath.contains(".")) {
            throw new MissingExtensionException("File path missing extension");
        }
        
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        System.out.println("First line from file: " + line);
        br.close();
    }

    public void displayChoices() {
        while (true) {
            System.out.println("\n1. Divide");
            System.out.println("2. Read from a file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = input.nextInt();
                
                switch (choice) {
                    case 1:
                        goToDivideMethod();
                        break;
                    case 2:
                        readAFile();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Exception " + e + " occurred. A number was expected, but wasn’t provided");
                input.next(); 
            } catch (ArithmeticException e) {
                System.out.println("Exception " + e + " occurred. Division by zero was attempted");
            } catch (IOException e) {
                System.out.println("Exception " + e + " occurred. File operation failed");
            } catch (MissingExtensionException e) {
                System.out.println("Exception " + e + " occurred: " + e.getMessage());
            } finally {
                System.out.println("Closing all the resources");
            }
        }
    }

    public static void main(String[] args) {
        ExceptionDemo demo = new ExceptionDemo();
        demo.displayChoices();
    }
}