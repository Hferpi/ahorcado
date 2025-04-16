package igu;
public class TestSQLite {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("✅ Driver SQLite cargado correctamente");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: Driver no encontrado");
        }
    }
}