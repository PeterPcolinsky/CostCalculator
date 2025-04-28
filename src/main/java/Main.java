import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> incomes = new ArrayList<>();
        ArrayList<Double> expenses = new ArrayList<>();

        System.out.println("Welcome to Cost Calculator!");

        while (true) {
            System.out.println();
            System.out.println("Choose one of the options:");
            System.out.println("1 - Add income");
            System.out.println("2 - Add an expense");
            System.out.println("3 - Show amount");
            System.out.println("0 - Exit the program");
            System.out.println("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

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
                    double totalIncome = 0;
                    for ( double income : incomes){
                        totalIncome += income;
                    }
                    double totalExpenses = 0;
                    for (double expense : expenses) {
                        totalExpenses += expense;
                    }
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
}