import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Orders {
    protected static void insertOrder(Connection connection,
                                      int orderId,
                                      int customerId,
                                      String orderDate,
                                      double totalAmount) throws SQLException {
        String insertQuery = "INSERT INTO Orders (order_id, customer_id, order_date, total_amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setDate(3, java.sql.Date.valueOf(orderDate));
            preparedStatement.setDouble(4, totalAmount);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order inserted successfully!");
            } else {
                System.out.println("Failed to insert order!");
            }
        }
    }
}
