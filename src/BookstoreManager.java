import java.sql.*;
import java.util.Scanner;
public class BookstoreManager {

    public static void main(String[] args) {
        ConnectToDatabase connector = new ConnectToDatabase();
          // as the methods are static methods we no longer need to create an object of each class to use their methods
//        Author author = new Author();
//        BookMethods bm = new BookMethods();
//        Customer customer = new Customer();
//        Orders orders = new Orders();

        try (Connection connection = connector.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Successfully connected to the database!\n");
                System.out.println("If you need to go back to main menu again from any step of the operation rerun the code");
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    MenuFunctionalities.displayMenu();

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> MenuFunctionalities.addAuthor(connection, scanner);
                        case 2 -> MenuFunctionalities.addBook(connection, scanner);
                        case 3 -> MenuFunctionalities.addCustomer(connection, scanner);
                        case 4 -> MenuFunctionalities.displayBooks(connection);
                        case 5 -> MenuFunctionalities.updateBook(connection, scanner);
                        case 6 -> MenuFunctionalities.deleteBook(connection, scanner);
                        case 7 -> MenuFunctionalities.placeOrder(connection, scanner);
                        case 8 -> MenuFunctionalities.displayTableNames(connection);
                        case 9 -> MenuFunctionalities.displayTableDetails(connection);
                        case 10 -> {
                            return;
                        }
                        default -> System.out.println("Invalid choice! Please enter a valid option.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
