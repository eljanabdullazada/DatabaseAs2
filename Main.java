public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        db.connect_to_db("assignment", "postgres", "12345678");
    }
}
