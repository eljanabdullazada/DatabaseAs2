import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null){
                System.out.println("Successfully connected to database");
            }
            else{
                System.out.println("Could not connect to database");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
