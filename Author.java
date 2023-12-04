import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Author {
    protected static void insertAuthor(Connection connection,
                                     int authorId,
                                     String authorName,
                                     String birthDate,
                                     String country) throws SQLException {
        String insertQuery = "INSERT INTO Authors (author_id, author_name, birth_date, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, authorId);
            preparedStatement.setString(2, authorName);
            preparedStatement.setDate(3, java.sql.Date.valueOf(birthDate));
            preparedStatement.setString(4, country);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Author inserted successfully!");
            } else {
                System.out.println("Failed to insert author!");
            }
        }
    }
}
