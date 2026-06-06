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

    private static final int OPTION_EXIT = 0;
    private static final int OPTION_ADD_INCOME = 1;
    private static final int OPTION_ADD_EXPENSE = 2;
    private static final int OPTION_SHOW_BALANCE = 3;

    private static final int MIN_MENU_OPTION = 0;
    private static final int MAX_MENU_OPTION = 3;

    private static final String MESSAGE_WELCOME =
            "Welcome to Cost Calculator!";

    private static final String MESSAGE_INCOME_ADDED = "Income added successfully!";
    private static final String MESSAGE_EXPENSE_ADDED = "Expense added successfully!";
    private static final String MESSAGE_INVALID_NUMBER = "Invalid input. Please enter a number.";

    private static final String MESSAGE_INVALID_AMOUNT =
            "Invalid input. Please enter a valid number.";

    private static final String MESSAGE_NEGATIVE_AMOUNT =
            "Amount cannot be negative.";

    private static final String MESSAGE_SELECTED_ADD_INCOME =
            "Selected option: Add income";

    private static final String MESSAGE_SELECTED_ADD_EXPENSE =
            "Selected option: Add expense";

    private static final String MESSAGE_SELECTED_SHOW_BALANCE =
            "Selected option: Show amount";

    private static final String MESSAGE_EXIT =
            "I'm ending the program...";

    private static final String MESSAGE_WRONG_MENU_OPTION =
            "Wrong number. Please select only 1 to 3 or 0. Thank you";

    private static final String PROMPT_INCOME_AMOUNT =
            "Enter income amount:";

    private static final String PROMPT_EXPENSE_AMOUNT =
            "Enter expense amount:";

    /**
     * Entry point of the application.
     *
     * <p>Creates required objects and starts the application loop.</p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> incomes = new ArrayList<>();
        List<Double> expenses = new ArrayList<>();

        runApplication(scanner, incomes, expenses);
    }

    /**
     * Runs the main application loop.
     *
     * @param scanner scanner used for reading user input
     * @param incomes list of incomes
     * @param expenses list of expenses
     */
    private static void runApplication(Scanner scanner,
                                       List<Double> incomes,
                                       List<Double> expenses) {

        System.out.println(MESSAGE_WELCOME);

        while (true) {
            printMenu();

            int choice = getValidChoice(scanner);

            switch (choice) {
                case OPTION_ADD_INCOME:
                    handleAddIncome(scanner, incomes);
                    break;

                case OPTION_ADD_EXPENSE:
                    handleAddExpense(scanner, expenses);
                    break;

                case OPTION_SHOW_BALANCE:
                    handleShowBalance(incomes, expenses);
                    break;

                case OPTION_EXIT:
                    handleExit();
                    return;

                default:
                    System.out.println(MESSAGE_WRONG_MENU_OPTION);
                    break;
            }
        }
    }

    /**
     * Handles the flow for adding income.
     *
     * @param scanner scanner used for reading user input
     * @param incomes list of incomes
     */
    private static void handleAddIncome(Scanner scanner, List<Double> incomes) {
        System.out.println(MESSAGE_SELECTED_ADD_INCOME);

        double incomeAmount =
                getValidAmount(scanner, PROMPT_INCOME_AMOUNT);

        incomes.add(incomeAmount);

        System.out.println(MESSAGE_INCOME_ADDED);
    }

    /**
     * Handles the flow for adding an expense.
     *
     * @param scanner scanner used for reading user input
     * @param expenses list of expenses
     */
    private static void handleAddExpense(Scanner scanner, List<Double> expenses) {
        System.out.println(MESSAGE_SELECTED_ADD_EXPENSE);

        double expenseAmount =
                getValidAmount(scanner, PROMPT_EXPENSE_AMOUNT);

        expenses.add(expenseAmount);

        System.out.println(MESSAGE_EXPENSE_ADDED);
    }

    /**
     * Handles the flow for displaying balance information.
     *
     * @param incomes list of incomes
     * @param expenses list of expenses
     */
    private static void handleShowBalance(List<Double> incomes,
                                          List<Double> expenses) {

        System.out.println(MESSAGE_SELECTED_SHOW_BALANCE);

        double totalIncome = calculateSum(incomes);
        double totalExpenses = calculateSum(expenses);
        double balance = totalIncome - totalExpenses;

        printBalance(totalIncome, totalExpenses, balance);
    }

    /**
     * Prints balance information.
     *
     * @param totalIncome total income amount
     * @param totalExpenses total expense amount
     * @param balance current balance
     */
    private static void printBalance(double totalIncome,
                                     double totalExpenses,
                                     double balance) {

        System.out.println("Total income: " + totalIncome);
        System.out.println("Total expenses: " + totalExpenses);
        System.out.println("Current balance: " + balance);
    }

    /**
     * Handles application exit.
     */
    private static void handleExit() {
        System.out.println(MESSAGE_EXIT);
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

                if (choice >= MIN_MENU_OPTION
                        && choice <= MAX_MENU_OPTION) {

                    return choice;

                } else {

                    System.out.println(
                            "Please enter a number between "
                                    + MIN_MENU_OPTION
                                    + " and "
                                    + MAX_MENU_OPTION
                                    + ".");
                }

            } catch (NumberFormatException e) {
                System.out.println(MESSAGE_INVALID_NUMBER);
            }
        }
    }

    /**
     * Reads and validates a numeric amount from user input.
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
                    System.out.println(MESSAGE_NEGATIVE_AMOUNT);
                }

            } catch (NumberFormatException e) {
                System.out.println(MESSAGE_INVALID_AMOUNT);
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