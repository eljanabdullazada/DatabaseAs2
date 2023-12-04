import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer {
    protected static void insertCustomer(Connection connection,
                                       int customerId,
                                       String firstName,
                                       String lastName,
                                       String email,
                                       String phone) throws SQLException {
        String insertQuery = "INSERT INTO Customers (customer_id, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer inserted successfully!");
            } else {
                System.out.println("Failed to insert customer!");
            }
        }
    }
}
