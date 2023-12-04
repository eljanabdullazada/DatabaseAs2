import java.sql.*;

public class BookMethods {
     protected static void insertBook(Connection connection,
                                   int bookId,
                                   String title,
                                   int authorId,
                                   String genre,
                                   double price,
                                   int stockQuantity) throws SQLException {
        String insertQuery = "INSERT INTO Books (book_id, title, author_id, genre, price, stock_quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, authorId);
            preparedStatement.setString(4, genre);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, stockQuantity);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book inserted successfully!");
            } else {
                System.out.println("Failed to insert book!");
            }
        }
    }

    protected static void retrieveBooks(Connection connection) throws SQLException {
        String retrieveQuery = "SELECT * FROM Books INNER JOIN Authors ON Books.author_id = Authors.author_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(retrieveQuery)) {
            while (resultSet.next()) {
                System.out.println("Book ID: " + resultSet.getInt("book_id"));
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Author: " + resultSet.getString("author_name"));
            }
        }
    }

    protected static void updateBook(Connection connection,
                                   int bookId,
                                   String newTitle,
                                   String newGenre,
                                   double newPrice,
                                   int newStockQuantity) throws SQLException {
        String updateQuery = "UPDATE Books SET title = ?, genre = ?, price = ?, stock_quantity = ? WHERE book_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newGenre);
            preparedStatement.setDouble(3, newPrice);
            preparedStatement.setInt(4, newStockQuantity);
            preparedStatement.setInt(5, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Failed to update book!");
            }
        }
    }

    protected static void deleteBook(Connection connection, int bookId) throws SQLException {
        String deleteQuery = "DELETE FROM Books WHERE book_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Failed to delete book!");
            }
        }
    }
}
