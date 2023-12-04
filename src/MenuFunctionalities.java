import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuFunctionalities {
    protected static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Author");
        System.out.println("2. Add Book");
        System.out.println("3. Add Customer");
        System.out.println("4. Display Books");
        System.out.println("5. Update Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Order Book");
        System.out.println("8. Display Table Names");
        System.out.println("9. Display Table Details");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    protected static void addAuthor(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Author ID: ");
            int authorId = scanner.nextInt();

            System.out.println("Enter Author Name: ");
            String authorName = scanner.next();

            System.out.println("Enter Birth Date (YYYY-MM-DD): ");
            String birthDate = scanner.next();

            System.out.println("Enter Country: ");
            String country = scanner.next();

            Author.insertAuthor(connection, authorId, authorName, birthDate, country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void addBook(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Book ID: ");
            int bookId = scanner.nextInt();

            System.out.println("Enter Book Title: ");
            String title = scanner.next();

            System.out.println("Enter Author ID: ");
            int authorId = scanner.nextInt();

            System.out.println("Enter Genre: ");
            String genre = scanner.next();

            System.out.println("Enter Price: ");
            double price = scanner.nextDouble();

            System.out.println("Enter Stock Quantity: ");
            int stockQuantity = scanner.nextInt();

            BookMethods.insertBook(connection, bookId, title, authorId, genre, price, stockQuantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void addCustomer(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Customer ID: ");
            int customerId = scanner.nextInt();

            System.out.println("Enter First Name: ");
            String firstName = scanner.next();

            System.out.println("Enter Last Name: ");
            String lastName = scanner.next();

            System.out.println("Enter Email: ");
            String email = scanner.next();

            System.out.println("Enter Phone: ");
            String phone = scanner.next();

            Customer.insertCustomer(connection, customerId, firstName, lastName, email, phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected static void displayBooks(Connection connection) {
        try {
            BookMethods.retrieveBooks(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void updateBook(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Book ID to update: ");
            int bookId = scanner.nextInt();

            System.out.println("Enter New Title: ");
            String newTitle = scanner.next();

            System.out.println("Enter New Genre: ");
            String newGenre = scanner.next();

            System.out.println("Enter New Price: ");
            double newPrice = scanner.nextDouble();

            System.out.println("Enter New Stock Quantity: ");
            int newStockQuantity = scanner.nextInt();

            BookMethods.updateBook(connection, bookId, newTitle, newGenre, newPrice, newStockQuantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void deleteBook(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Book ID to delete: ");
            int bookId = scanner.nextInt();

            BookMethods.deleteBook(connection, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void placeOrder(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter Order ID: ");
            int orderId = scanner.nextInt();

            System.out.println("Enter Customer ID: ");
            int customerId = scanner.nextInt();

            System.out.println("Enter Order Date (YYYY-MM-DD): ");
            String orderDate = scanner.next();

            System.out.println("Enter Total Amount: ");
            double totalAmount = scanner.nextDouble();

            System.out.println("Enter Book ID: ");
            int bookId = scanner.nextInt();

            System.out.println("Enter Quantity: ");
            int quantity = scanner.nextInt();

            Orders.insertOrder(connection, orderId, customerId, orderDate, totalAmount, bookId, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void displayTableNames(Connection connection) {
        try {
            Metadata.displayTableNamesAndStructures(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void displayTableDetails(Connection connection) {
        System.out.println("\nPK stands for Primary Key and FK stands for Foreign Key");
        try {
            Metadata.displayPrimaryKeys(connection);
            Metadata.displayForeignKeys(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
