// previous version without transaction functionality added to the code

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Orders {
//    protected static void insertOrder(Connection connection,
//                                      int orderId,
//                                      int customerId,
//                                      String orderDate,
//                                      double totalAmount) throws SQLException {
//        String insertQuery = "INSERT INTO Orders (order_id, customer_id, order_date, total_amount) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//            preparedStatement.setInt(1, orderId);
//            preparedStatement.setInt(2, customerId);
//            preparedStatement.setDate(3, java.sql.Date.valueOf(orderDate));
//            preparedStatement.setDouble(4, totalAmount);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Order inserted successfully!");
//            } else {
//                System.out.println("Failed to insert order!");
//            }
//        }
//    }
//}

// here the above commented code is updated to perform transaction operations
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Orders {
    protected static void insertOrder(Connection connection,
                                      int orderId,
                                      int customerId,
                                      String orderDate,
                                      int bookId,
                                      int quantity) throws SQLException {

        try {
            connection.setAutoCommit(false);

            if (!isStockAvailable(connection, bookId, quantity)) {
                System.out.println("Not enough stock available for this book!");
                connection.rollback();
                return;
            }

            String insertOrderQuery = "INSERT INTO Orders (order_id, customer_id, order_date) VALUES (?, ?, ?)";
            try (PreparedStatement orderStatement = connection.prepareStatement(insertOrderQuery)) {
                orderStatement.setInt(1, orderId);
                orderStatement.setInt(2, customerId);
                orderStatement.setDate(3, java.sql.Date.valueOf(orderDate));

                int orderRowsAffected = orderStatement.executeUpdate();
                if (orderRowsAffected > 0) {
                    System.out.println("Order inserted successfully!");


                    updateBookStock(connection, bookId, quantity);


                    connection.commit();
                } else {
                    System.out.println("Failed to insert order!");
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Check if the given number of books available in stock
    private static boolean isStockAvailable(Connection connection, int bookId, int requiredQuantity) throws SQLException {
        String checkStockQuery = "SELECT stock_quantity FROM Books WHERE book_id = ?";
        try (PreparedStatement stockStatement = connection.prepareStatement(checkStockQuery)) {
            stockStatement.setInt(1, bookId);
            try (var resultSet = stockStatement.executeQuery()) {
                if (resultSet.next()) {
                    int availableQuantity = resultSet.getInt("stock_quantity");
                    return availableQuantity >= requiredQuantity;
                }
            }
        }
        return false;
    }

    // After order process books are updated using this method
    private static void updateBookStock(Connection connection, int bookId, int soldQuantity) throws SQLException {
        String updateStockQuery = "UPDATE Books SET stock_quantity = stock_quantity - ? WHERE book_id = ?";
        try (PreparedStatement stockUpdateStatement = connection.prepareStatement(updateStockQuery)) {
            stockUpdateStatement.setInt(1, soldQuantity);
            stockUpdateStatement.setInt(2, bookId);
            stockUpdateStatement.executeUpdate();
        }
    }
}
