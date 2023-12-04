import java.sql.*;

public class Metadata {

    public static void displayTableNamesAndStructures(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

        System.out.println("Table Names and Structures:");
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println("Table Name: " + tableName);

            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            System.out.println("Columns:");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                System.out.println("\tColumn Name: " + columnName + ", Type: " + columnType + ", Size: " + columnSize);
            }
            System.out.println();
        }
    }

    public static void displayPrimaryKeys(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

        System.out.println("Primary Keys:");
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet primaryKey = metaData.getPrimaryKeys(null, null, tableName);

            while (primaryKey.next()) {
                String primaryKeyColumnName = primaryKey.getString("COLUMN_NAME");
                System.out.println("Table: " + tableName + ", Primary Key Column: " + primaryKeyColumnName);
            }
        }
    }

    public static void displayForeignKeys(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

        System.out.println("Foreign Keys:");
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);

            while (foreignKeys.next()) {
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");

                System.out.println("Table: " + tableName + ", FK Column: " + fkColumnName +
                        ", PK Table: " + pkTableName + ", PK Column: " + pkColumnName);
            }
        }
    }
}