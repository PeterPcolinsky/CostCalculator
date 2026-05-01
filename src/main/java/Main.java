import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple console application for tracking incomes and expenses.
 *
 * <p>The program allows the user to:
 * <ul>
 *     <li>Add income</li>
 *     <li>Add expense</li>
 *     <li>Display total income, expenses and balance</li>
 * </ul>
 *
 * <p>Data is stored in memory using Lists and is not persisted.</p>
 */
public class Main {

    /**
     * Entry point of the application.
     *
     * <p>Handles user interaction through console menu,
     * processes user input and performs selected operations.</p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> incomes = new ArrayList<>();
        List<Double> expenses = new ArrayList<>();

        System.out.println("Welcome to Cost Calculator!");

        while (true) {
            printMenu();

            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    System.out.println("Selected option: Add income");
                    double incomeAmount = getValidAmount(scanner, "Enter income amount:");
                    incomes.add(incomeAmount);
                    System.out.println("Income added successfully!");
                    break;

                case 2:
                    System.out.println("Selected option: Add expense");
                    double expenseAmount = getValidAmount(scanner, "Enter expense amount:");
                    expenses.add(expenseAmount);
                    System.out.println("Expense added successfully!");
                    break;

                case 3:
                    System.out.println("Selected option: Show amount");

                    double totalIncome = calculateSum(incomes);
                    double totalExpenses = calculateSum(expenses);
                    double balance = totalIncome - totalExpenses;

                    System.out.println("Total income: " + totalIncome);
                    System.out.println("Total expenses: " + totalExpenses);
                    System.out.println("Current balance: " + balance);
                    break;

                case 0:
                    System.out.println("I'm ending the program...");
                    break;

                default:
                    System.out.println("Wrong number. Please select only 1 to 3 or 0. Thank you");
                    break;
            }

            if (choice == 0) {
                break;
            }
        }
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("Choose one of the options:");
        System.out.println("1 - Add income");
        System.out.println("2 - Add an expense");
        System.out.println("3 - Show amount");
        System.out.println("0 - Exit the program");
        System.out.println("Enter your choice: ");
    }

    /**
     * Reads and validates menu choice input from the user.
     *
     * <p>The method ensures that the input is a number between 0 and 3.</p>
     *
     * @param scanner scanner used for reading user input
     * @return valid menu choice
     */
    private static int getValidChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 0 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 0 and 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Reads and validates a numeric amount from user input.
     *
     * <p>The method repeatedly asks the user for input until a valid
     * non-negative number is entered.</p>
     *
     * @param scanner scanner used for reading user input
     * @param message message displayed to the user
     * @return valid non-negative amount
     */
    public static double getValidAmount(Scanner scanner, String message) {
        double amount = -1;

        while (amount < 0) {
            System.out.println(message);

            try {
                amount = Double.parseDouble(scanner.nextLine());

                if (amount < 0) {
                    System.out.println("Amount cannot be negative.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return amount;
    }

    /**
     * Calculates the sum of all values in the given list.
     *
     * @param list list of numeric values
     * @return sum of all values
     */
    private static double calculateSum(List<Double> list) {
        double sum = 0;

        for (double value : list) {
            sum += value;
        }

        return sum;
    }
}